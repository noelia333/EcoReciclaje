package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class registro_materiales extends AppCompatActivity {

    ImageView imagenRetroceder;
    TextView tvFecha;
    TextInputLayout etIngreseKg, etValorkg;
    Button btnAgregar;
    TableLayout tabla;
    Spinner materialSpinner;

    private double valorTotal = 0.0;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_materiales);

        imagenRetroceder = findViewById(R.id.imvRetroceder);
        materialSpinner = findViewById(R.id.choose_item);
        tvFecha = findViewById(R.id.tvFecha);
        etIngreseKg = findViewById(R.id.etIngreseKg);
        btnAgregar = findViewById(R.id.btnAgregar);
        etValorkg = findViewById(R.id.etValorkg);
        tabla = findViewById(R.id.tlTabla);

        // Agregar el encabezado de la tabla
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        String[] headers = {"Dia", "Mes", "Material", "Cantidad en Kg", "Valor total kg"};

        for (String header : headers) {
            headerRow.addView(createTextView(header, true));
        }

        tabla.addView(headerRow);

        // SPINNER
        String[] opcionesMateriales = {"Seleccionar material", "Papel y Cartón", "Plástico", "Vidrio", "Metal"};
        // FECHA
        String fechaActual = obtenerFechaActual();
        tvFecha.setText("Fecha: " + fechaActual);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesMateriales);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        materialSpinner.setAdapter(adapter);

        // SELECCIÓN DEL SPINNER
        materialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    Toast.makeText(registro_materiales.this, "Por favor, selecciona un material válido", Toast.LENGTH_SHORT).show();
                } else {
                    String materialSeleccionado = opcionesMateriales[position];
                    Toast.makeText(registro_materiales.this, "Material seleccionado: " + materialSeleccionado, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        // Cargar datos existentes al iniciar la aplicación
        loadDataFromFile();

        imagenRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en el botón de retroceder
                Intent retroceder = new Intent(getApplicationContext(), Perfil_usuario.class);
                startActivity(retroceder);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String material = materialSpinner.getSelectedItem().toString();
                String cantidadKg = etIngreseKg.getEditText().getText().toString();
                String valorKG = etValorkg.getEditText().getText().toString();

                String dia = obtenerDiaActual();
                String mes = obtenerMesActual();

                if (cantidadKg.isEmpty() || valorKG.isEmpty() || material.equals(opcionesMateriales[0])) {
                    Toast.makeText(registro_materiales.this, "Por favor, ingresa todos los datos.", Toast.LENGTH_LONG).show();
                } else {
                    if (checkStoragePermission()) {
                        saveDataToFile(dia, mes, material, cantidadKg, valorKG);
                    } else {
                        requestStoragePermission();
                    }

                    addRow(dia, mes, material, cantidadKg, valorKG);

                    etIngreseKg.getEditText().getText().clear();
                    etValorkg.getEditText().getText().clear();
                }
            }
        });

        // Listener para las filas de la tabla
        for (int i = 1; i < tabla.getChildCount(); i++) {
            View rowView = tabla.getChildAt(i);
            if (rowView instanceof TableRow) {
                TableRow row = (TableRow) rowView;
                setTableRowClickListener(row);
            }
        }
    }

    private void loadDataFromFile() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            // El archivo existe, procede a leer los datos y agregar las filas a la tabla
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    addRow(datos[0], datos[1], datos[2], datos[3], datos[4]);
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //archivo txt
    private boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
    }



    private void saveDataToFile(String dia, String mes, String material, String cantidadKg, String valorKG) {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        try {
            FileWriter writer = new FileWriter(materialsFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(
                    dia + "," +
                            mes + "," +
                            material + "," +
                            cantidadKg + "," +
                            valorKG
            );
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void addRow(String dia, String mes, String material, String cantidadKg, String valorKG) {
        try {
            double valor = Double.parseDouble(valorKG);
            valorTotal += valor;

            TableRow newRow = new TableRow(this);
            newRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            newRow.addView(createTextView(dia, false));
            newRow.addView(createTextView(mes, false));
            newRow.addView(createTextView(material, false));
            newRow.addView(createTextView(cantidadKg, false));
            newRow.addView(createTextView(valorKG, false));

            ImageView deleteIcon = createDeleteIcon();
            newRow.addView(deleteIcon);

            setTableRowClickListener(newRow);

            tabla.addView(newRow);

            TextView tvValorTotal = findViewById(R.id.tvValorTotal);
            tvValorTotal.setText("Valor total: " + String.valueOf(valorTotal));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al convertir a número: " + valorKG, Toast.LENGTH_SHORT).show();
        }
    }

    private ImageView createDeleteIcon() {
        ImageView deleteIcon = new ImageView(this);
        deleteIcon.setImageResource(R.drawable.ic_delete); // Asegúrate de tener esta imagen en res/drawable
        deleteIcon.setClickable(true);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow parentRow = (TableRow) v.getParent();
                tabla.removeView(parentRow);
                Toast.makeText(registro_materiales.this, "Fila eliminada", Toast.LENGTH_SHORT).show();
            }
        });

        return deleteIcon;
    }

    private void setTableRowClickListener(TableRow row) {
        for (int i = 0; i < row.getChildCount(); i++) {
            View childView = row.getChildAt(i);
            childView.setClickable(true);
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Si se hace clic en la imagen de la caneca de basura, eliminar la fila
                    if (v instanceof ImageView) {
                        TableRow parentRow = (TableRow) v.getParent();
                        double valorEliminado = Double.parseDouble(((TextView) parentRow.getChildAt(4)).getText().toString());
                        valorTotal -= valorEliminado;

                        // Actualizar el TextView con el nuevo valor total
                        TextView tvValorTotal = findViewById(R.id.tvValorTotal);
                        tvValorTotal.setText("Valor total: " + String.valueOf(valorTotal));

                        // Eliminar la fila visualmente
                        tabla.removeView(parentRow);

                        // Eliminar la fila del archivo
                        removeFromFile(parentRow);

                        Toast.makeText(registro_materiales.this, "Fila eliminada", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void removeFromFile(TableRow row) {
        File materialsFile = new File(getFilesDir(), "materials.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
            List<String> lines = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                if (!datos[0].equals(((TextView) row.getChildAt(0)).getText().toString())
                        || !datos[1].equals(((TextView) row.getChildAt(1)).getText().toString())
                        || !datos[2].equals(((TextView) row.getChildAt(2)).getText().toString())
                        || !datos[3].equals(((TextView) row.getChildAt(3)).getText().toString())
                        || !datos[4].equals(((TextView) row.getChildAt(4)).getText().toString())) {
                    lines.add(line);
                }
            }

            reader.close();

            // Sobrescribir el archivo con las líneas restantes
            FileWriter writer = new FileWriter(materialsFile, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (String newLine : lines) {
                bufferedWriter.write(newLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al actualizar el archivo", Toast.LENGTH_SHORT).show();
        }
    }

    private TextView createTextView(String text, boolean isHeader) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(8, 8, 8, 8);

        if (isHeader) {
            textView.setBackgroundColor(Color.TRANSPARENT);
            textView.setTextColor(Color.BLACK);
        } else {
            textView.setBackgroundColor(Color.LTGRAY);
        }

        return textView;
    }

    private String obtenerFechaActual() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return formatoFecha.format(calendar.getTime());
    }

    private String obtenerDiaActual() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
        SimpleDateFormat formatoDia = new SimpleDateFormat("dd", Locale.getDefault());
        return formatoDia.format(calendar.getTime());
    }

    private String obtenerMesActual() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
        SimpleDateFormat formatoMes = new SimpleDateFormat("MM", Locale.getDefault());
        return formatoMes.format(calendar.getTime());
    }


    // Método que se ejecutará cuando se obtengan resultados de la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, realiza la acción que requería el permiso.
                // Puedes llamar a la función que realiza la acción después de obtener el permiso aquí.
            } else {
                // Permiso denegado, muestra un mensaje o realiza alguna acción adecuada.
                Toast.makeText(this, "Permiso de escritura en almacenamiento externo denegado.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

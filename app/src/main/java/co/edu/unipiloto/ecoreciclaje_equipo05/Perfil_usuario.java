package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Perfil_usuario extends AppCompatActivity {

    ImageView imvConsejos,imvMaterialReciclable,imvEstadistica;

    TextView tvConsejos,tvMaterialRecicable,tvEstadistica, tvTotalTodosKg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        tvTotalTodosKg = findViewById(R.id.tw0000);

        // Mostrar la suma total de kg al iniciar la aplicación
        mostrarTotalTodosKg();

        //captura nombre usuario
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("username")) {
            String username = intent.getStringExtra("username");

            // Establecer el nombre de usuario en el TextView
            TextView textViewUsername = findViewById(R.id.textView22);
            textViewUsername.setText(username);
        }



        // IMAGEN CONSEJOS
        imvConsejos = findViewById(R.id.imvConsejos);
        Intent pantalla_consejos = new Intent(getApplicationContext(), pantalla_consejos.class);
        imvConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(pantalla_consejos);
            }
        });

        // TEXTO CONSEJOS
        tvConsejos = findViewById(R.id.tvConsejos);
        tvConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(pantalla_consejos);
            }
        });


        // IMAGEN MATERIAL RECICLABLE
        imvMaterialReciclable  = findViewById(R.id.imvMaterialReciclable);
        Intent registro_materiales= new Intent(getApplicationContext(),registro_materiales.class);
        imvMaterialReciclable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registro_materiales);
            }
        });

        // TEXTO MATERIAL RECICLABLE
        tvMaterialRecicable = findViewById(R.id.tvMaterialRecicable);
        tvMaterialRecicable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registro_materiales);
            }
        });


        // IMAGEN ESTADISTICAS

        imvEstadistica=findViewById(R.id.imvEstadistica);


        Intent pantalla_estadistica= new Intent(getApplicationContext(), pantalla_estadisticas.class);
        imvEstadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(pantalla_estadistica);

            }
        });


        // TEXTO ESTADISTICAS

        tvEstadistica=findViewById(R.id.tvEstadistica);

        tvEstadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(pantalla_estadistica);
            }
        });




    }
    private void mostrarTotalTodosKg() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double totalTodosKg = 0.0;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Plástico".equals(datos[2].trim()) || "Metal".equals(datos[2].trim()) ||
                            "Vidrio".equals(datos[2].trim()) || "Papel y Cartón".equals(datos[2].trim())) {
                        totalTodosKg += Double.parseDouble(datos[3].trim());
                    }
                }

                reader.close();

                // Muestra el resultado en el TextView
                tvTotalTodosKg.setText(String.format(Locale.getDefault(), "%.2f", totalTodosKg));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Log.d("MostrarTotalTodosKg", "El archivo no existe en la ubicación esperada.");
        }
    }

    // Resto de tu código...
}
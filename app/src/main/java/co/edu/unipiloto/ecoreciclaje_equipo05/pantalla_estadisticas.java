package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class pantalla_estadisticas extends AppCompatActivity {

    ImageView img_volver, imgMetales, imgPlastico, imgVidrio, imgCartonPapel;
    TextView tvNombreMaterial, tvNameMetales, tvNamePlastico, tvNameVidrio, tvNameCartonPapel, tvFlKg, tvFlIngreso,tvFlMaxima, tvFlMinimo,tvTotalTodosKG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_estadisticas);

        img_volver = findViewById(R.id.img_voler);
        imgMetales = findViewById(R.id.imgMetales);
        imgPlastico = findViewById(R.id.imgPlastico);
        imgVidrio = findViewById(R.id.imgVidrio);
        imgCartonPapel = findViewById(R.id.imgCartonPapel);
        tvNombreMaterial = findViewById(R.id.tvNombreMaterial);
        tvNameMetales = findViewById(R.id.tvnameMetales);
        tvNamePlastico = findViewById(R.id.tvnamePlastico);
        tvNameVidrio = findViewById(R.id.tvnameVidrio);
        tvNameCartonPapel = findViewById(R.id.tvnameCartonPapel);
        tvFlKg = findViewById(R.id.tvFlKg);
        tvFlIngreso = findViewById(R.id.tvFlIngreso);
        tvFlMaxima = findViewById(R.id.tvFlMaxima);
        tvFlMinimo = findViewById(R.id.tvFlMinimo);
        tvTotalTodosKG = findViewById(R.id.tvTotalTodosKg);

        mostrarTotalTodosKG();

        Intent volver = new Intent(getApplicationContext(), Perfil_usuario.class);


        img_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Perfil_usuario.class));
            }
        });

        imgMetales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Metales");
                mostrarEstadisticasMetales();
                mostrarKgMaxMinMetales();


            }
        });

        imgPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Plástico");
                mostrarEstadisticasPlastico();
                mostrarKgMaxMinPlastico();


            }
        });

        imgVidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Vidrio");
                mostrarEstadisticasVidrio();
                mostrarKgMaxMinVidrio();


            }
        });

        imgCartonPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("CartonPapel");
                mostrarEstadisticasCartonPapel();
                mostrarKgMaxMinCartonPapel();


            }
        });

        tvNameMetales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Metales");
            }
        });

        tvNamePlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Plástico");
            }
        });

        tvNameVidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Vidrio");
            }
        });

        tvNameCartonPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Cartón y Papel");
            }
        });
    }

    private void updateMaterialName(String materialName) {
        tvNombreMaterial.setText(materialName);
    }

    private void mostrarEstadisticasMetales() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double totalCantidadMetales = 0.0;
                double totalIngresoMetales = 0.0;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Metal".equals(datos[2].trim())) {
                        totalCantidadMetales += Double.parseDouble(datos[3].trim());
                        totalIngresoMetales += Double.parseDouble(datos[4].trim());
                        System.out.println("holalllll que hay en " + totalCantidadMetales);
                    }
                }

                reader.close();

                // Muestra el resultado en el TextView
                System.out.println("que hay en" + totalCantidadMetales);
                tvFlKg.setText(totalCantidadMetales+"");
                tvFlIngreso.setText(totalIngresoMetales+"");

            } catch (IOException e) {
                e.printStackTrace();
                // Log.d("EstadisticasMetales", "Error al leer el archivo.", e);
            }
        } else {
            // Log.d("EstadisticasMetales", "El archivo no existe en la ubicación esperada.");
        }
    }

    private void mostrarEstadisticasCartonPapel() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double totalCantidadCartonPapel = 0.0;
                double totalIngresoCartonPapel = 0.0;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Papel y Cartón".equals(datos[2].trim())) {
                        totalCantidadCartonPapel += Double.parseDouble(datos[3].trim());
                        totalIngresoCartonPapel += Double.parseDouble(datos[4].trim());
                        System.out.println("holalllll que hay en " + totalCantidadCartonPapel);
                    }
                }

                reader.close();

                // Muestra el resultado en el TextView
                System.out.println("que hay en" + totalCantidadCartonPapel);
                tvFlKg.setText(totalCantidadCartonPapel+"");
                tvFlIngreso.setText(totalIngresoCartonPapel+"");

            } catch (IOException e) {
                e.printStackTrace();
                // Log.d("EstadisticasMetales", "Error al leer el archivo.", e);
            }
        } else {
            // Log.d("EstadisticasMetales", "El archivo no existe en la ubicación esperada.");
        }
    }

    private void mostrarEstadisticasPlastico() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double totalCantidadPlastico = 0.0;
                double totalIngresoPlastico = 0.0;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Plástico".equals(datos[2].trim())) {
                        totalCantidadPlastico += Double.parseDouble(datos[3].trim());
                        totalIngresoPlastico += Double.parseDouble(datos[4].trim());
                        System.out.println("holalllll que hay en " + totalCantidadPlastico);
                    }
                }

                reader.close();

                // Muestra el resultado en el TextView
                System.out.println("que hay en" + totalCantidadPlastico);
                tvFlKg.setText(totalCantidadPlastico+"");
                tvFlIngreso.setText(totalIngresoPlastico+"");

            } catch (IOException e) {
                e.printStackTrace();
                // Log.d("EstadisticasMetales", "Error al leer el archivo.", e);
            }
        } else {
            // Log.d("EstadisticasMetales", "El archivo no existe en la ubicación esperada.");
        }
    }

    private void mostrarEstadisticasVidrio() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double totalCantidadVidrio = 0.0;
                double totalIngresoVidrio = 0.0;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Vidrio".equals(datos[2].trim())) {
                        totalCantidadVidrio += Double.parseDouble(datos[3].trim());
                        totalIngresoVidrio += Double.parseDouble(datos[4].trim());
                        System.out.println("holalllll que hay en " + totalCantidadVidrio);
                    }
                }

                reader.close();

                // Muestra el resultado en el TextView
                System.out.println("que hay en" + totalCantidadVidrio);
                tvFlKg.setText(totalCantidadVidrio+"");
                tvFlIngreso.setText(totalIngresoVidrio+"");

            } catch (IOException e) {
                e.printStackTrace();
                // Log.d("EstadisticasMetales", "Error al leer el archivo.", e);
            }
        } else {
            // Log.d("EstadisticasMetales", "El archivo no existe en la ubicación esperada.");
        }
    }
    private void mostrarKgMaxMinMetales() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double maxKg = Double.MIN_VALUE;
                double minKg = Double.MAX_VALUE;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Metal".equals(datos[2].trim())) {
                        double cantidadKg = Double.parseDouble(datos[3].trim());
                        if (cantidadKg > maxKg) {
                            maxKg = cantidadKg;
                        }
                        if (cantidadKg < minKg) {
                            minKg = cantidadKg;
                        }
                    }
                }

                reader.close();

                // Muestra el resultado en los TextView
                tvFlMaxima.setText(String.format(Locale.getDefault(), "%.2f", maxKg));
                tvFlMinimo.setText(String.format(Locale.getDefault(), "%.2f", minKg));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Log.d("MostrarKgMaxMinMetales", "El archivo no existe en la ubicación esperada.");
        }
    }

    private void mostrarKgMaxMinCartonPapel() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double maxKg = Double.MIN_VALUE;
                double minKg = Double.MAX_VALUE;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Papel y Cartón".equals(datos[2].trim())) {
                        double cantidadKg = Double.parseDouble(datos[3].trim());
                        if (cantidadKg > maxKg) {
                            maxKg = cantidadKg;
                        }
                        if (cantidadKg < minKg) {
                            minKg = cantidadKg;
                        }
                    }
                }

                reader.close();

                // Muestra el resultado en los TextView
                tvFlMaxima.setText(String.format(Locale.getDefault(), "%.2f", maxKg));
                tvFlMinimo.setText(String.format(Locale.getDefault(), "%.2f", minKg));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Log.d("MostrarKgMaxMinMetales", "El archivo no existe en la ubicación esperada.");
        }
    }

    private void mostrarKgMaxMinPlastico() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double maxKg = Double.MIN_VALUE;
                double minKg = Double.MAX_VALUE;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Plástico".equals(datos[2].trim())) {
                        double cantidadKg = Double.parseDouble(datos[3].trim());
                        if (cantidadKg > maxKg) {
                            maxKg = cantidadKg;
                        }
                        if (cantidadKg < minKg) {
                            minKg = cantidadKg;
                        }
                    }
                }

                reader.close();

                // Muestra el resultado en los TextView
                tvFlMaxima.setText(String.format(Locale.getDefault(), "%.2f", maxKg));
                tvFlMinimo.setText(String.format(Locale.getDefault(), "%.2f", minKg));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Log.d("MostrarKgMaxMinMetales", "El archivo no existe en la ubicación esperada.");
        }
    }

    private void mostrarKgMaxMinVidrio() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double maxKg = Double.MIN_VALUE;
                double minKg = Double.MAX_VALUE;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Vidrio".equals(datos[2].trim())) {
                        double cantidadKg = Double.parseDouble(datos[3].trim());
                        if (cantidadKg > maxKg) {
                            maxKg = cantidadKg;
                        }
                        if (cantidadKg < minKg) {
                            minKg = cantidadKg;
                        }
                    }
                }

                reader.close();

                // Muestra el resultado en los TextView
                tvFlMaxima.setText(String.format(Locale.getDefault(), "%.2f", maxKg));
                tvFlMinimo.setText(String.format(Locale.getDefault(), "%.2f", minKg));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Log.d("MostrarKgMaxMinMetales", "El archivo no existe en la ubicación esperada.");
        }
    }

    private void mostrarTotalTodosKG() {
        File materialsFile = new File(getFilesDir(), "materials.txt");

        if (materialsFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(materialsFile));
                String line;
                double totalTodosKG = 0.0;

                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if ("Plástico".equals(datos[2].trim()) || "Metal".equals(datos[2].trim()) ||
                            "Vidrio".equals(datos[2].trim()) || "Papel y Cartón".equals(datos[2].trim())) {
                        totalTodosKG += Double.parseDouble(datos[3].trim());
                    }
                }

                reader.close();

                // Muestra el resultado en el TextView
                tvTotalTodosKG.setText(String.format(Locale.getDefault(), "%.2f", totalTodosKG));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Log.d("MostrarTotalTodosKG", "El archivo no existe en la ubicación esperada.");
        }
    }


}






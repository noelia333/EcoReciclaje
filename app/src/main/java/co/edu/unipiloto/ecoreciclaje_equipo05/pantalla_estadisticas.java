package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class pantalla_estadisticas extends AppCompatActivity {

    ImageView img_volver, imgMetales, imgPlastico, imgVidrio, imgCartonPapel;
    TextView tvNombreMaterial, tvNameMetales, tvNamePlastico, tvNameVidrio, tvNameCartonPapel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_estadisticas);

        img_volver=findViewById(R.id.img_voler);
        imgMetales = findViewById(R.id.imgMetales);
        imgPlastico = findViewById(R.id.imgPlastico);
        imgVidrio = findViewById(R.id.imgVidrio);
        imgCartonPapel = findViewById(R.id.imgCartonPapel);
        tvNombreMaterial = findViewById(R.id.tvNombreMaterial);
        tvNameMetales = findViewById(R.id.tvnameMetales);
        tvNamePlastico = findViewById(R.id.tvnamePlastico);
        tvNameVidrio = findViewById(R.id.tvnameVidrio);
        tvNameCartonPapel = findViewById(R.id.tvnameCartonPapel);


        Intent volver=new Intent(getApplicationContext(), Perfil_usuario.class);



        img_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(volver);
            }
        });

        imgMetales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Metales");
            }
        });

        imgPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Plástico");
            }
        });

        imgVidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Vidrio");
            }
        });

        imgCartonPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMaterialName("Cartón y Papel");
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
        tvNombreMaterial.setText( materialName);

    }
}

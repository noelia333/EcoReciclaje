package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Perfil_usuario extends AppCompatActivity {

    ImageView imvConsejos;

    ImageView imvMaterialReciclable;

    TextView tvConsejos;

    TextView tvMaterialRecicable;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        // IMAGEN CONSEJOS
        imvConsejos=findViewById(R.id.imvConsejos);
        imvConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perfil_usuario=new Intent(getApplicationContext(),pantalla_consejos.class);

                startActivity(Perfil_usuario);
            }
        });

        // TEXTO CONSEJOS
        tvConsejos=findViewById(R.id.tvConsejos);
        tvConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perfil_usuario=new Intent(getApplicationContext(),pantalla_consejos.class);

                startActivity(Perfil_usuario);
            }
        });

        // IMAGEN MATERIAL RECICLABLE

        imvMaterialReciclable=findViewById(R.id.imvMaterialReciclable);
        imvMaterialReciclable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perfil_usuario=new Intent(getApplicationContext(),registro_materiales.class);

                startActivity(Perfil_usuario);
            }
        });

        // TEXTO MATERIAL RECICLABLE
        tvMaterialRecicable=findViewById(R.id.tvMaterialRecicable);
        tvMaterialRecicable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perfil_usuario=new Intent(getApplicationContext(),registro_materiales.class);
                startActivity(Perfil_usuario);
            }
        });

    }
}
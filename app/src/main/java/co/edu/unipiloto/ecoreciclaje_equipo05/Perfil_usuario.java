package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Perfil_usuario extends AppCompatActivity {

    ImageView imvConsejos;

    TextView tvConsejos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        imvConsejos=findViewById(R.id.imvConsejos);
        imvConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perfil_usuario=new Intent(getApplicationContext(),pantalla_consejos.class);

                startActivity(Perfil_usuario);
            }
        });

        tvConsejos=findViewById(R.id.tvConsejos);
        tvConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perfil_usuario=new Intent(getApplicationContext(),pantalla_consejos.class);

                startActivity(Perfil_usuario);
            }
        });
    }
}
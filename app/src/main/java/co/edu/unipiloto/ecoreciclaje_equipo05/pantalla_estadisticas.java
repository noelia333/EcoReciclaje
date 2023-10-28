package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;

public class pantalla_estadisticas extends AppCompatActivity {

    ImageView img_volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_estadisticas);

        img_volver=findViewById(R.id.img_voler);

        Intent volver=new Intent(getApplicationContext(), Perfil_usuario.class);

        img_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(volver);
            }
        });
    }
}
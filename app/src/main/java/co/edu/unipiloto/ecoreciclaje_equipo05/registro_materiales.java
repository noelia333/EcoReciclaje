package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class registro_materiales extends AppCompatActivity {

    ImageView imagenRetroceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_materiales);

        imagenRetroceder = findViewById(R.id.imvRetroceder);

        imagenRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent retroceder = new Intent(getApplicationContext(), Perfil_usuario.class);
                startActivity(retroceder);
            }
        });


    }
}
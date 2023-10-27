package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class pantalla_consejos extends AppCompatActivity {

    ImageView ivDevolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_consejos);

        ivDevolver=findViewById(R.id.ivDevolver);
        ivDevolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantalla_consejos=new Intent(getApplicationContext(),Perfil_usuario.class);
                startActivity(pantalla_consejos);
            }
        });


    }
}
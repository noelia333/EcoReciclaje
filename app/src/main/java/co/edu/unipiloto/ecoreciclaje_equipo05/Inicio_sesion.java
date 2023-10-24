package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Inicio_sesion extends AppCompatActivity {

    TextView registrarte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        registrarte=findViewById(R.id.txt_registrate);

        Intent regis=new Intent(getApplicationContext(), registro_usuario.class);

        registrarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(regis);
            }
        });


    }
}
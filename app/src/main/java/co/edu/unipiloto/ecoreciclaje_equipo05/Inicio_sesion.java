package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio_sesion extends AppCompatActivity {

    EditText ext_name, ext_password;
    TextView registrarte;
    Button iniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        registrarte=findViewById(R.id.txt_registrate);
        iniciarSesion=findViewById(R.id.btn_iniciar_sesion);
        ext_name=findViewById(R.id.ext_name);
        ext_password=findViewById(R.id.ext_password);


        Intent regis=new Intent(getApplicationContext(), registro_usuario.class);
        Intent logi=new Intent(getApplicationContext(), Perfil_usuario.class);

        registrarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(regis);
            }
        });

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText=ext_name.getText().toString();
                String passwordText=ext_password.getText().toString();
                if (nameText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getBaseContext(),"Todos los campos deben llenarse",Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(logi);
                }
            }
        });




    }
}
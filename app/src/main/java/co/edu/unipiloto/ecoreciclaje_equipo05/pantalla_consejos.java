package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class pantalla_consejos extends AppCompatActivity {

    ImageView ivDevolver;


    private TextView tvinformacion;
    private Button btnConsejo1, btnConsejo2, btnConsejo3, btnConsejo4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_consejos);

        ivDevolver=findViewById(R.id.ivDevolver);

        tvinformacion = findViewById(R.id.tvinformacion);
        btnConsejo1 = findViewById(R.id.btn_consejo1);
        btnConsejo2 = findViewById(R.id.btn_consejo2);
        btnConsejo3 = findViewById(R.id.btn_consejo3);
        btnConsejo4 = findViewById(R.id.btn_consejo4);

        ivDevolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent pantalla_consejos=new Intent(getApplicationContext(),Perfil_usuario.class);
                startActivity(pantalla_consejos);
            }
        });

        btnConsejo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInformacion("coloca los residuos reciclables separados de los que son organicos");
            }
        });

        btnConsejo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInformacion("procura reducir (dentro de lo posible) la cantidad de residuos que generas en casa o Reutiliza");
            }
        });

        btnConsejo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInformacion("Deposita los residuos en el contenedor correspondiente" +
                        "esta acción facilita que los residuos se incorporen antes a la cadena de reciclaje");
            }
        });

        btnConsejo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInformacion("Reduce el tamaño de botellas y bricks" +
                        "Un truco para optimizar nuestros espacios de almacenaje es «aplastar» los bricks y las botellas de plástico");
            }
        });
    }
    private void mostrarInformacion(String texto) {
        tvinformacion.setText(texto);
    }
}



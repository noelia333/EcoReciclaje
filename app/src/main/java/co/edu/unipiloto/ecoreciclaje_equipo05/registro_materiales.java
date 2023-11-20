package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class registro_materiales extends AppCompatActivity {

    ImageView imagenRetroceder;

    TextView tvTotal;
    EditText Valorpeso, Valorprecio;

    Button btn_registrar_material;
    Spinner Spi_intem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_materiales);

        imagenRetroceder = findViewById(R.id.imvRetroceder);
        Spi_intem=findViewById(R.id.choose_item);
        tvTotal=findViewById(R.id.tvtotal);
        Valorpeso=findViewById(R.id.Peso);
        Valorprecio=findViewById(R.id.Valor);
        btn_registrar_material=findViewById(R.id.btn_registe_material);

        imagenRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent retroceder = new Intent(getApplicationContext(), Perfil_usuario.class);
                startActivity(retroceder);
            }
        });

        //String[] material={"Carton","Plastico","Vidrio", "Papel"};
        ArrayAdapter<CharSequence> Adaptador= ArrayAdapter.createFromResource
                (this, R.array.material,android.R.layout.simple_spinner_item);
        Adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //(this, R.array.material, android.R.layout.simple_spinner_item);

        Spi_intem.setAdapter(Adaptador);

        btn_registrar_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calularValor();
            }
        });
    }

    public void calularValor() {
        int peso,valor;
        int carton, plastico, vidrio, metales;
        String select;
        peso = Integer.parseInt(Valorpeso.getText().toString());
        valor = Integer.parseInt(Valorprecio.getText().toString());


        select = Spi_intem.getSelectedItem().toString();

        if (select.equals("Papel y carton")) {
            carton = valor * peso;
            tvTotal.setText(String.valueOf(carton));

        } else {
            if (select.equals("Plasticos")) {
                System.out.println(select);
                plastico = valor * peso;
                tvTotal.setText(String.valueOf(plastico));

            } else {
                if (select.equals("Vidrio")) {
                    System.out.println(select);
                    vidrio = valor * peso;
                    tvTotal.setText(String.valueOf(vidrio));

                } else {
                    if (select.equals("Metales")) {
                        metales = valor * peso;
                        tvTotal.setText(String.valueOf(metales));
                    }
                }
            }
        }
    }
}
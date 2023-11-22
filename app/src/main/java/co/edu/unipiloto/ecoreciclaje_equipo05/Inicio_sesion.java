package co.edu.unipiloto.ecoreciclaje_equipo05;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


import co.edu.unipiloto.ecoreciclaje_equipo05.models.User;

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
        TextInputLayout name = findViewById(R.id.ext_name);
        TextInputLayout password = findViewById(R.id.ext_password);

        ext_name = name.getEditText();
        ext_password = password.getEditText();

        //ext_name=findViewById(R.id.ext_name);
        //ext_password=findViewById(R.id.ext_password);


        Intent regis=new Intent(getApplicationContext(), registro_usuario.class);
        Intent logi=new Intent(getApplicationContext(), Perfil_usuario.class);


        File fileUser=new File(getFilesDir(),"user.txt");

        ArrayList<User> users= ListUser(fileUser);




        registrarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(regis);
            }
        });

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String nameText=ext_name.getText().toString();
                String passwordText=ext_password.getText().toString();
                if (nameText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getBaseContext(),"Todos los campos deben llenarse",Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(logi);
                }*/

                boolean state=false;
                if (ext_name.getText().toString().isEmpty() || ext_password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"todos los campos deben llenarse",Toast.LENGTH_LONG).show();
                }else {
                    String userLogin=ext_name.getText().toString();
                    for (User i:users){
                        if(i.getEtCorreo().equals(userLogin) || i.getEtCedula().equals(userLogin) || i.getEtNombres().equals(userLogin)){
                            state=true;
                            if (i.getEtContraseña().equals(ext_password.getText().toString())){
                                String nombreUsuario = i.getEtNombres();
                                logi.putExtra("etCedula", i.getEtCedula());
                                logi.putExtra("nombreUsuario", nombreUsuario);
                                logi.putExtra("username", nombreUsuario);
                                startActivity(logi);
                                break;
                            }else{
                                Toast.makeText(getApplicationContext(), "contraseña incorrecta",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    if (!state){
                        Toast.makeText(getApplicationContext(),"Usuario no existe",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

    public ArrayList<User>ListUser(File data){
        ArrayList<User> list=new ArrayList<>();

        try {
            FileReader reader=new FileReader(data);
            BufferedReader bufferedReader=new BufferedReader(reader);
            String user;


            while ((user=bufferedReader.readLine())!=null){
                String[] userData=user.split(",");
                String etNombres=userData[0];
                String etCedula=userData[1];
                String etCorreo=userData[2];
                String etContraseña=userData[3];

                User userObject=new User(etNombres,etCedula,etCorreo,etContraseña);
                list.add(userObject);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }







}

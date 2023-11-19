package co.edu.unipiloto.ecoreciclaje_equipo05;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


import co.edu.unipiloto.ecoreciclaje_equipo05.models.User;


public class registro_usuario extends AppCompatActivity {
    Button btnRegistrarse;

    EditText etNombres, etApellidos, etCedula, etCorreo, etContraseña, etValidacion;

    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        btnRegistrarse=findViewById(R.id.btn_registrarse);

        etNombres = findViewById(R.id.etIngreNombre);
        etApellidos = findViewById(R.id.etIngreApellidos);
        etCedula = findViewById(R.id.etIngreID);
        etCorreo = findViewById(R.id.etIngreCorreo);
        etContraseña = findViewById(R.id.etIngreContraseña);
        etValidacion = findViewById(R.id.etConfiContraseña);

        check = findViewById(R.id.check_terminos);

        Intent registroUsuario=new Intent(getApplicationContext(),Inicio_sesion.class);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String nombres = etNombres.getText().toString();
                String apellidos = etApellidos.getText().toString();
                String cedula = etCedula.getText().toString();
                String correo = etCorreo.getText().toString();
                String contraseña = etContraseña.getText().toString();
                String validacionContraseña = etValidacion.getText().toString();*/

                /*if(check.isSelected() && nombres.isEmpty() || apellidos.isEmpty() || cedula.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || validacionContraseña.isEmpty()  ) {
                    Toast.makeText(getBaseContext(), "Todos los campos deben llenarse", Toast.LENGTH_LONG).show();
                } else if (!check.isChecked()) {
                    Toast.makeText(getBaseContext(), "Debes aceptar los terminos y condiciones", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getBaseContext(),"Usuario creado con exito",Toast.LENGTH_LONG).show();
                    startActivity(registroUsuario);
                }*/

                /*if (nombres.isEmpty() || apellidos.isEmpty() || cedula.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || validacionContraseña.isEmpty() ) {
                    Toast.makeText(registro_usuario.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else if (!check.isChecked()) {
                    Toast.makeText(registro_usuario.this, "Falta aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(registro_usuario.this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                    startActivity(registroUsuario);
                }*/

                if (validarUsuario()){
                    User user=crearUsuario();
                    SaveUser(user);
                    Toast.makeText(getApplicationContext(),
                            "Usuario creado con éxito",Toast.LENGTH_LONG).show();

                    try {
                        sleep( 500);
                        startActivity(registroUsuario);
                        finish();
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }else{
                    Toast.makeText(getApplicationContext()," Todos los campos deben estar diligenciados",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    // Metodo para validar si el espacio esta vacio o lleno
    public boolean validarUsuario(){
        boolean validarDate=true;

        if (etNombres.getText().toString().isEmpty()){
            etNombres.setBackgroundColor(Color.RED);
            validarDate=false;
        }
        if (etApellidos.getText().toString().isEmpty()){
            etApellidos.setBackgroundColor(Color.RED);
            validarDate=false;
        }
        if (etCedula.getText().toString().isEmpty()){
            etCedula.setBackgroundColor(Color.RED);
            validarDate=false;
        }
        if (etCorreo.getText().toString().isEmpty()){
            etCorreo.setBackgroundColor(Color.RED);
            validarDate=false;
        }
        if (etContraseña.getText().toString().isEmpty()){
            etContraseña.setBackgroundColor(Color.RED);
            validarDate=false;
        }
        if (etValidacion.getText().toString().isEmpty()){
            etValidacion.setBackgroundColor(Color.RED);
            validarDate=false;
        }
        if (!etContraseña.getText().toString().equals(etValidacion.getText().toString())){
            etContraseña.setBackgroundColor(Color.RED);
            etValidacion.setBackgroundColor(Color.RED);
            validarDate=false;
        }
        if (!check.isChecked()){
            check.setBackgroundColor(Color.RED);
            validarDate=false;
        }

        return validarDate;

    }

    //Metodo que solicita los datos
    public User crearUsuario(){
        String NombreUser,ApellidosUser, CedulaUser, CorreoUser, ContraseñaUser;

        NombreUser=etNombres.getText().toString();
        ApellidosUser=etApellidos.getText().toString();
        CedulaUser=etCedula.getText().toString();
        CorreoUser=etCorreo.getText().toString();
        ContraseñaUser=etContraseña.getText().toString();

        User user=new User(NombreUser,ApellidosUser,CedulaUser,CorreoUser,ContraseñaUser);

        return user;
    }


    //Crear el archivo plano para almacenar el usuario
    public void SaveUser(User user){

        File fileUser=new File(getFilesDir(), "user.txt");

        try {
            FileWriter writer=new FileWriter(fileUser,true);

            BufferedWriter bufferedWriter=new BufferedWriter(writer);
            bufferedWriter.write(
                    user.getEtNombres()+","+
                        user.getEtApellidos()+","+
                        user.getEtCedula()+","+
                        user.getEtCorreo()+","+
                        user.getEtContraseña()
            );
            bufferedWriter.newLine();
            bufferedWriter.close();

        }catch (Exception error){
            error.printStackTrace();
        }

    }






}
package co.edu.unipiloto.ecoreciclaje_equipo05.models;



public class User {

    private String etNombres, etApellidos, etCedula, etCorreo, etContraseña;

    public User(String etNombres, String etApellidos, String etCedula, String etCorreo, String etContraseña) {
        this.etNombres = etNombres;
        this.etApellidos = etApellidos;
        this.etCedula = etCedula;
        this.etCorreo = etCorreo;
        this.etContraseña = etContraseña;
    }

    public String getEtNombres() {
        return etNombres;
    }

    public void setEtNombres(String etNombres) {
        this.etNombres = etNombres;
    }

    public String getEtApellidos() {
        return etApellidos;
    }

    public void setEtApellidos(String etApellidos) {
        this.etApellidos = etApellidos;
    }

    public String getEtCedula() {
        return etCedula;
    }

    public void setEtCedula(String etCedula) {
        this.etCedula = etCedula;
    }

    public String getEtCorreo() {
        return etCorreo;
    }

    public void setEtCorreo(String etCorreo) {
        this.etCorreo = etCorreo;
    }

    public String getEtContraseña() {
        return etContraseña;
    }

    public void setEtContraseña(String etContraseña) {
        this.etContraseña = etContraseña;
    }

}

package clases;

public class Postulacion {

    private final String emailUsuario;
    private String puestoTrabajo;


    public Postulacion(String emailUsuario, String puestoTrabajo) {
        this.emailUsuario = emailUsuario;
        this.puestoTrabajo = puestoTrabajo;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }
}
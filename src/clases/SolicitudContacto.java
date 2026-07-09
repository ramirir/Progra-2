package clases;

public class SolicitudContacto {

    private String remitente;
    private String destinatario;

    public SolicitudContacto(String remitente, String destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }
}
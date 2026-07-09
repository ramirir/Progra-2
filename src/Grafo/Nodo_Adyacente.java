package Grafo;

public class Nodo_Adyacente {

    private String idDestino;
    private String tipoRelacion;   // "CONTACTO" o "SIGUE"
    private Nodo_Adyacente siguiente;

    public Nodo_Adyacente(String idDestino, String tipoRelacion) {
        this.idDestino = idDestino;
        this.tipoRelacion = tipoRelacion;
        this.siguiente = null;
    }

    public String getIdDestino() {
        return idDestino;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public Nodo_Adyacente getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_Adyacente siguiente) {
        this.siguiente = siguiente;
    }
}
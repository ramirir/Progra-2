package Grafo;

public class Nodo_Adyacente {
    private String idDestino;
    private Nodo_Adyacente siguiente;

    public Nodo_Adyacente(String idDestino) {
        this.idDestino = idDestino;
        this.siguiente = null;
    }

    public String getIdDestino() { return idDestino; }
    public Nodo_Adyacente getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo_Adyacente siguiente) { this.siguiente = siguiente; }
}
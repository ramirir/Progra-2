package clases;

public class Nodo_Cola {
    private Postulacion info;
    private Nodo_Cola siguiente;

    public Nodo_Cola(Postulacion info){
        this.info = info;
        this.siguiente = null;
    }

    public Postulacion getInfo() { return info; }
    public void setInfo(Postulacion info) { this.info = info; }

    // Corregido: Nodo_cola -> Nodo_Cola
    public Nodo_Cola getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo_Cola siguiente){ this.siguiente = siguiente; }
}
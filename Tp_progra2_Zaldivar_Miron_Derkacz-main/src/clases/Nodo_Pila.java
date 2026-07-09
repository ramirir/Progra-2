package clases;

public class Nodo_Pila {
    private String info;
    private Nodo_Pila siguiente;

    public Nodo_Pila(String info) {
        this.info = info;
        this.siguiente = null;
    }

    //Metodos para acceder a info que esta privada

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Nodo_Pila getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_Pila siguiente) {
        this.siguiente = siguiente;
    }
}
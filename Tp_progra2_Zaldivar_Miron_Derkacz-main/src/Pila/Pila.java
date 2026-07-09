package Pila;
import clases.Nodo_Pila;

public class Pila implements Ipila {
    private Nodo_Pila tope;

    public Pila() {
        this.tope = null;
    }

    @Override
    public void apilar(String elemento) {
        Nodo_Pila nuevo = new Nodo_Pila(elemento);
        nuevo.setSiguiente(tope);
        tope = nuevo;
    }

    @Override
    public void desapilar() {
        if (!esta_vacia()) {
            tope = tope.getSiguiente();
        }
    }

    @Override
    public String vertope() {
        if (esta_vacia()) {
            return null;
        }
        return tope.getInfo();
    }

    @Override
    public boolean esta_vacia() {
        return tope == null;
    }
}
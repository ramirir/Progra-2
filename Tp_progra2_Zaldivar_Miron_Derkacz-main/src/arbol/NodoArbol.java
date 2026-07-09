package arbol;

public class NodoArbol {

    String habilidad;
    NodoArbol padre;
    Lista<NodoArbol> hijos;
    Lista<String> profesionales;

    public NodoArbol(String habilidad) {
        this.habilidad = habilidad;
        this.padre = null;
        this.hijos = new Lista<>();
        this.profesionales = new Lista<>();
    }

    public NodoArbol(String habilidad, NodoArbol padre) {
        this.habilidad = habilidad;
        this.padre = padre;
        this.hijos = new Lista<>();
        this.profesionales = new Lista<>();
    }
}

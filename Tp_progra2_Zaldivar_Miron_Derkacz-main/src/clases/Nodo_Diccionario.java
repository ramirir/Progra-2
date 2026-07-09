package clases;
import Grafo.Nodo_Adyacente;

public class Nodo_Diccionario {
    private String clave;                  // El ID o Email del usuario
    private Clase_Perfil valor;                  // El objeto Perfil que creamos
    private Nodo_Diccionario siguiente;   // Puntero al siguiente nodo
    private Nodo_Adyacente adyacentes;
    private boolean visitado;

    public Nodo_Diccionario(String clave, Clase_Perfil valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
        this.adyacentes = null;
        this.visitado = false;
    }

//Metodos para acceder a claves y valores privados

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Clase_Perfil getValor() {
        return valor;
    }

    public void setValor(Clase_Perfil valor) {
        this.valor = valor;
    }

    public Nodo_Diccionario getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_Diccionario siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo_Adyacente getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(Nodo_Adyacente adyacentes) {
        this.adyacentes = adyacentes;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}
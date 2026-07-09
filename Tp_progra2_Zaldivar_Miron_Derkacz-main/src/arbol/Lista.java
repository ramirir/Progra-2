package arbol;

public class Lista<T> {

    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private int tamanio;

    public Lista() {
        this.cabeza = null;
        this.tamanio = 0;
    }


    public void add(T elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamanio++;
    }


    public T get(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public int size() {
        return tamanio;
    }

    public boolean isEmpty() {
        return tamanio == 0;
    }

    public boolean contains(T elemento) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(elemento)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }


    public T remove(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
        T eliminado;
        if (indice == 0) {
            eliminado = cabeza.dato;
            cabeza = cabeza.siguiente;
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            eliminado = actual.siguiente.dato;
            actual.siguiente = actual.siguiente.siguiente;
        }
        tamanio--;
        return eliminado;
    }


    public boolean remove(T elemento) {
        if (cabeza == null) {
            return false;
        }
        if (cabeza.dato.equals(elemento)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.equals(elemento)) {
                actual.siguiente = actual.siguiente.siguiente;
                tamanio--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public String toString() {
        String resultado = "[";
        Nodo actual = cabeza;
        while (actual != null) {
            resultado += actual.dato;
            if (actual.siguiente != null) {
                resultado += ", ";
            }
            actual = actual.siguiente;
        }
        resultado += "]";
        return resultado;
    }
}

package Diccionario;
import clases.Nodo_Diccionario;
import clases.Clase_Perfil;

public class Diccionario implements Idiccionario {
    private Nodo_Diccionario origen; // El primer nodo de la lista


    public Diccionario() {
        this.origen = null;
    }

    @Override
    public void insertar(String clave, Clase_Perfil valor) {
        Nodo_Diccionario nuevo = new Nodo_Diccionario(clave, valor);

        // Lista vacia o el nuevo va primero
        if (origen == null || clave.compareTo(origen.getClave()) < 0) {
            nuevo.setSiguiente(origen);
            origen = nuevo;
            return;
        }

        Nodo_Diccionario actual = origen;
        Nodo_Diccionario anterior = null;

        // Recorremos la lista mientras la clave sea mayor
        while (actual != null && clave.compareTo(actual.getClave()) > 0) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        // Caso donde claves sean iguales
        if (actual != null && clave.equalsIgnoreCase(actual.getClave())) {
            System.out.println("\n  [ Error en el registro ]");
            System.out.println("   El email '" + clave + "' ya se encuentra registrado en la plataforma.");
            System.out.println("   Por favor utilice otro Mail para el registro.");
        }
        // Si no existe, se inserta normalmente
        else {
            nuevo.setSiguiente(actual);
            if (anterior != null) {
                anterior.setSiguiente(nuevo);
            } else {
                origen = nuevo;
            }
        }
    }

    @Override
    public void eliminar(String clave) {
        // Si la lista está vacía, no hay nada que borrar
        if (origen == null) {
            return;
        }

        // El nodo a borrar es el primero de todos
        if (origen.getClave().equals(clave)) {
            origen = origen.getSiguiente(); // La nueva entrada es el segundo nodo
            return;
        }


        Nodo_Diccionario actual = origen;
        Nodo_Diccionario anterior = null;

        // Recorremos la lista buscando la clave
        while (actual != null && !actual.getClave().equals(clave)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual != null) {
            anterior.setSiguiente(actual.getSiguiente()); // el anterior ahora apunta al siguiente del actual
        }
    }

    @Override
    public Clase_Perfil recuperar(String clave) {
        Nodo_Diccionario actual = origen;

        while (actual != null) {

            // Si nos pasamos, cortamos la busqueda
            if (clave.compareTo(actual.getClave()) < 0) {
                break;
            }

            // Si lo encuentra, devuelve el Perfil
            if (clave.equals(actual.getClave())) {
                return actual.getValor();
            }
            actual = actual.getSiguiente();
        }
        return null; // Si no existe
    }

    @Override
    public Nodo_Diccionario obtenerNodo(String clave) {
        Nodo_Diccionario actual = origen;
        while (actual != null) {
            if (clave.equals(actual.getClave())) {
                return actual;
            }
            if (clave.compareTo(actual.getClave()) < 0) {
                break;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public void limpiarVisitados() {
        Nodo_Diccionario actual = origen;
        while (actual != null) {
            actual.setVisitado(false);
            actual = actual.getSiguiente();
        }
    }

}
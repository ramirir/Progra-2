package arbol;

public class ArbolHabilidades implements IArbolHabilidades {

    private NodoArbol raiz;

    public ArbolHabilidades() {
        this.raiz = null;
    }

    @Override
    public void agregarRaiz(String habilidad) {
        if (raiz != null) {
            System.out.println("El árbol ya tiene raíz: " + raiz.habilidad);
            return;
        }
        raiz = new NodoArbol(habilidad);
        System.out.println("Raíz creada: " + habilidad);
    }
    @Override
    public void agregarHabilidad(String nombrePadre, String nuevaHabilidad) {
        NodoArbol padre = buscarNodo(raiz, nombrePadre);

        if (padre == null) {
            System.out.println("No se encontró la habilidad padre: " + nombrePadre);
            return;
        }

        if (buscarNodo(raiz, nuevaHabilidad) != null) {
            System.out.println("La habilidad '" + nuevaHabilidad + "' ya existe en el árbol.");
            return;
        }

        NodoArbol nuevo = new NodoArbol(nuevaHabilidad, padre);
        padre.hijos.add(nuevo);
        System.out.println("Habilidad '" + nuevaHabilidad + "' agregada bajo '" + nombrePadre + "'.");
    }

    @Override
    public void asociarProfesional(String habilidad, String idProfesional) {
        NodoArbol nodo = buscarNodo(raiz, habilidad);

        if (nodo == null) {
            System.out.println("Habilidad no encontrada: " + habilidad);
            return;
        }

        if (nodo.profesionales.contains(idProfesional)) {
            System.out.println("El profesional " + idProfesional + " ya está asociado a '" + habilidad + "'.");
            return;
        }

        nodo.profesionales.add(idProfesional);
        System.out.println("Profesional " + idProfesional + " asociado a '" + habilidad + "'.");
    }
    @Override
    public void desasociarProfesional(String habilidad, String idProfesional) {
        NodoArbol nodo = buscarNodo(raiz, habilidad);

        if (nodo == null) {
            System.out.println("Habilidad no encontrada: " + habilidad);
            return;
        }

        boolean eliminado = nodo.profesionales.remove(idProfesional);

        if (eliminado) {
            System.out.println("Profesional " + idProfesional + " desasociado de '" + habilidad + "'.");
        } else {
            System.out.println("El profesional " + idProfesional + " no estaba asociado a '" + habilidad + "'.");
        }
    }

    private NodoArbol buscarNodo(NodoArbol actual, String habilidad) {
        if (actual == null) {
            return null;
        }

        if (actual.habilidad.equalsIgnoreCase(habilidad)) {
            return actual;
        }

        for (int i = 0; i < actual.hijos.size(); i++) {
            NodoArbol resultado = buscarNodo(actual.hijos.get(i), habilidad);
            if (resultado != null) {
                return resultado;
            }
        }

        return null;
    }

    @Override
    public boolean existeHabilidad(String habilidad) {
        return buscarNodo(raiz, habilidad) != null;
    }

    @Override
    public Lista<String> buscarProfesionalesPorHabilidad(String habilidad) {
        NodoArbol nodo = buscarNodo(raiz, habilidad);

        if (nodo == null) {
            System.out.println("Habilidad no encontrada: " + habilidad);
            return new Lista<>();
        }

        Lista<String> resultado = new Lista<>();
        recolectarProfesionales(nodo, resultado);

        if (resultado.isEmpty()) {
            System.out.println("No hay profesionales asociados a '" + habilidad + "' ni sus subcategorías.");
        } else {
            System.out.println("Profesionales encontrados bajo '" + habilidad + "': " + resultado);
        }

        return resultado;
    }

    private void recolectarProfesionales(NodoArbol nodo, Lista<String> acumulador) {
        if (nodo == null) {
            return;
        }

        for (int i = 0; i < nodo.hijos.size(); i++) {
            recolectarProfesionales(nodo.hijos.get(i), acumulador);
        }

        for (int i = 0; i < nodo.profesionales.size(); i++) {
            String id = nodo.profesionales.get(i);
            if (!acumulador.contains(id)) {
                acumulador.add(id);
            }
        }
    }
    @Override
    public void mostrarPreOrden() {
        System.out.print("PreOrden: ");
        preOrdenRecursivo(raiz, 0);
        System.out.println();
    }

    private void preOrdenRecursivo(NodoArbol nodo, int nivel) {
        if (nodo == null) {
            return;
        }

        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.println(nodo.habilidad);

        for (int i = 0; i < nodo.hijos.size(); i++) {
            preOrdenRecursivo(nodo.hijos.get(i), nivel + 1);
        }
    }
    @Override
    public void mostrarPostOrden() {
        System.out.print("PostOrden: ");
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(NodoArbol nodo) {
        if (nodo == null) {
            return;
        }

        for (int i = 0; i < nodo.hijos.size(); i++) {
            postOrdenRecursivo(nodo.hijos.get(i));
        }

        System.out.print(nodo.habilidad + " ");
    }
    @Override
    public void mostrarPorNiveles() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        System.out.println("Recorrido por niveles (BFS):");

        Lista<NodoArbol> cola = new Lista<>();
        cola.add(raiz);

        int nivel = 0;

        while (!cola.isEmpty()) {
            int cantNivel = cola.size();
            System.out.print("  Nivel " + nivel + ": ");

            for (int i = 0; i < cantNivel; i++) {
                NodoArbol actual = cola.remove(0);
                System.out.print(actual.habilidad + " ");

                for (int j = 0; j < actual.hijos.size(); j++) {
                    cola.add(actual.hijos.get(j));
                }
            }

            System.out.println();
            nivel++;
        }
    }
    @Override
    public int contarNodos() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }

        int total = 1;
        for (int i = 0; i < nodo.hijos.size(); i++) {
            total += contarRecursivo(nodo.hijos.get(i));
        }
        return total;
    }

    @Override
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }

        if (nodo.hijos.isEmpty()) {
            return 1;
        }

        int maxHijo = 0;
        for (int i = 0; i < nodo.hijos.size(); i++) {
            int h = alturaRecursiva(nodo.hijos.get(i));
            if (h > maxHijo) {
                maxHijo = h;
            }
        }

        return 1 + maxHijo;
    }
    @Override
    public void mostrarCamino(String habilidad) {
        Lista<String> camino = new Lista<>();
        boolean encontrado = buscarCamino(raiz, habilidad, camino);

        if (!encontrado) {
            System.out.println("Habilidad no encontrada: " + habilidad);
            return;
        }

        System.out.print("Camino: ");
        for (int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i));
            if (i < camino.size() - 1) {
                System.out.print(" > ");
            }
        }
        System.out.println();
    }

    private boolean buscarCamino(NodoArbol actual, String habilidad, Lista<String> camino) {
        if (actual == null) {
            return false;
        }

        camino.add(actual.habilidad);

        if (actual.habilidad.equalsIgnoreCase(habilidad)) {
            return true;
        }

        for (int i = 0; i < actual.hijos.size(); i++) {
            if (buscarCamino(actual.hijos.get(i), habilidad, camino)) {
                return true;
            }
        }

        camino.remove(camino.size() - 1);
        return false;
    }
    @Override
    public void mostrarHojas() {
        System.out.print("Habilidades hoja: ");
        mostrarHojasRecursivo(raiz);
        System.out.println();
    }

    private void mostrarHojasRecursivo(NodoArbol nodo) {
        if (nodo == null) {
            return;
        }

        if (nodo.hijos.isEmpty()) {
            System.out.print(nodo.habilidad + " ");
            return;
        }

        for (int i = 0; i < nodo.hijos.size(); i++) {
            mostrarHojasRecursivo(nodo.hijos.get(i));
        }
    }
    @Override
    public boolean esVacio() {
        return raiz == null;
    }
}

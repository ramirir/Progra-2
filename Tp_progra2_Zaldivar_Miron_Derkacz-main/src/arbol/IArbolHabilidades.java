package arbol;

public interface IArbolHabilidades {

    void agregarRaiz(String habilidad);

    void agregarHabilidad(String nombrePadre, String nuevaHabilidad);

    void asociarProfesional(String habilidad, String idProfesional);

    void desasociarProfesional(String habilidad, String idProfesional);

    boolean existeHabilidad(String habilidad);

    Lista<String> buscarProfesionalesPorHabilidad(String habilidad);

    void mostrarPreOrden();

    void mostrarPostOrden();

    void mostrarPorNiveles();

    int contarNodos();

    int altura();

    void mostrarCamino(String habilidad);

    void mostrarHojas();

    boolean esVacio();
}
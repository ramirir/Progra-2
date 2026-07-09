package Diccionario;

import clases.Clase_Perfil;
import clases.Nodo_Diccionario;

public interface Idiccionario {

    void insertar(String clave, Clase_Perfil valor);

    void eliminar(String clave);

    Clase_Perfil recuperar(String clave);

    Nodo_Diccionario obtenerNodo(String clave);

    void limpiarVisitados();

}
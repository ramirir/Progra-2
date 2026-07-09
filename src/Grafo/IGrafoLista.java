package Grafo;

import arbol.Lista;
import clases.Clase_Perfil;

public interface IGrafoLista {

    void conectar(String email1, String email2);

    int calcularGradoSeparacion(String origen, String destino);


    Lista<String> obtenerSugerencias(String email);

    void mostrarContactos(String email);

    void seguir(String emailOrigen, String emailDestino);

    void mostrarSeguidos(String email);

    void enviarSolicitudContacto(String emailOrigen, String emailDestino);

    void aceptarSolicitud(Clase_Perfil usuarioReceptor, int indiceSolicitud);

    void rechazarSolicitud(Clase_Perfil usuarioReceptor, int indiceSolicitud);

}
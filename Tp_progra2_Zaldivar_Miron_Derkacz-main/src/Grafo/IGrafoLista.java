package Grafo;

import arbol.Lista;

public interface IGrafoLista {

    void conectar(String email1, String email2);

    int calcularGradoSeparacion(String origen, String destino);

    void sugerirContactos(String email);

    Lista<String> obtenerSugerencias(String email);

    void mostrarContactos(String email);
}
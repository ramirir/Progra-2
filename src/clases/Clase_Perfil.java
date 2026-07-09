package clases;

import Pila.Pila;   // Importar Pila para el historial
import arbol.Lista;


public class Clase_Perfil {
    private String id;        // Clave única del usuario
    private String nombre;
    private String profesion;
    private Pila historialCambios;     // Para Deshacer
    private Lista<SolicitudContacto> solicitudesPendientes;

    public Clase_Perfil(String id, String nombre, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.profesion = profesion;
        this.historialCambios = new Pila();         // Cada perfil nace con su propia pila
        this.solicitudesPendientes = new Lista<>();
    }


    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;}

    public String getProfesion() {
        return profesion;
    }


    public void agregarSolicitud(SolicitudContacto solicitud) {
        solicitudesPendientes.add(solicitud);
    }

    public Lista<SolicitudContacto> getSolicitudesPendientes() {
        return solicitudesPendientes;
    }




    public void actualizarProfesion(String nuevaProfesion) {

        // Guardamos el estado anterior en la Pila antes de pisarlo con el estado nuevo

        this.historialCambios.apilar(this.profesion);
        this.profesion = nuevaProfesion;
    }

    public boolean deshacerUltimoCambio() {
        if (!this.historialCambios.esta_vacia()) {
            this.profesion = this.historialCambios.vertope();
            this.historialCambios.desapilar();
            return true;
        }
        return false;
    }




    public void mostrarSolicitudesPendientes() {

        if (solicitudesPendientes.isEmpty()) {
            System.out.println("No tienes solicitudes pendientes.");
            return;
        }

        System.out.println("\nSOLICITUDES DE CONTACTO:");

        for (int i = 0; i < solicitudesPendientes.size(); i++) {

            SolicitudContacto solicitud =
                    solicitudesPendientes.get(i);

            System.out.println(
                    (i + 1) + " - " + solicitud.getRemitente()
            );
        }
    }


    public SolicitudContacto obtenerSolicitud(int indice) {

        if (indice < 0 || indice >= solicitudesPendientes.size()) {
            return null;
        }

        return solicitudesPendientes.get(indice);
    }

    public void eliminarSolicitud(int indice) {

        if (indice >= 0 && indice < solicitudesPendientes.size()) {
            solicitudesPendientes.remove(indice);
        }
    }
}

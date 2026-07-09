package clases;

import Pila.Pila;     // Importar Pila para el historial

public class Clase_Perfil {
    private String id;        // Clave única del usuario
    private String nombre;
    private String profesion;
    private Pila historialCambios; // Para Deshacer

    public Clase_Perfil(String id, String nombre, String profesion) {
        this.id = id;
        this.nombre = nombre;
        this.profesion = profesion;
        this.historialCambios = new Pila(); // Cada perfil nace con su propia pila
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;}

    public String getNombre() {
        return nombre;}

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Pila getHistorialCambios() {
        return historialCambios;
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
}

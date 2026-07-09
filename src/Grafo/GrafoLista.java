package Grafo;

import Diccionario.Diccionario;
import clases.Clase_Perfil;
import clases.Nodo_Diccionario;
import arbol.Lista;
import clases.SolicitudContacto;

public class GrafoLista implements IGrafoLista {

    private Diccionario usuarios;

    public GrafoLista(Diccionario usuarios) {
        this.usuarios = usuarios;
    }

    @Override         //Sucede cuando un usuario acepta la solicitud
    public void conectar(String email1, String email2) {

        Nodo_Diccionario n1 = usuarios.obtenerNodo(email1);
        Nodo_Diccionario n2 = usuarios.obtenerNodo(email2);

        if (n1 != null && n2 != null) {

            agregarAdyacente(n1, email2, "CONTACTO");
            agregarAdyacente(n2, email1, "CONTACTO");

        }
    }

    @Override      // Unicamente direccional para un lado
    public void seguir(String emailOrigen, String emailDestino) {

        if (emailOrigen.equalsIgnoreCase(emailDestino)) {
            System.out.println("No puedes seguirte a ti mismo.");
            return;
        }

        Nodo_Diccionario origen = usuarios.obtenerNodo(emailOrigen);
        Nodo_Diccionario destino = usuarios.obtenerNodo(emailDestino);

        if (origen == null || destino == null) {
            System.out.println("El usuario no existe.");
            return;
        }

        Nodo_Adyacente ady = origen.getAdyacentes();


        // Verificaciones

        while (ady != null) {

            if (ady.getIdDestino().equalsIgnoreCase(emailDestino)) {

                if (ady.getTipoRelacion().equals("SIGUE")) {
                    System.out.println("Ya sigues a este usuario.");
                    return;
                }

                if (ady.getTipoRelacion().equals("CONTACTO")) {
                    System.out.println("Este usuario ya es tu contacto.");
                    return;
                }
            }

            ady = ady.getSiguiente();
        }

        agregarAdyacente(origen, emailDestino, "SIGUE");

        System.out.println("Ahora sigues a " + destino.getValor().getNombre() + ".");
    }


    // Creacion de nodo adyacente para la lista de los usuarios

    private void agregarAdyacente(Nodo_Diccionario origen, String idDestino, String tipoRelacion) {

        Nodo_Adyacente nuevo = new Nodo_Adyacente(idDestino, tipoRelacion);

        if (origen.getAdyacentes() == null) {
            origen.setAdyacentes(nuevo);
        } else {
            Nodo_Adyacente actual = origen.getAdyacentes();

            while (actual.getSiguiente() != null) {

                if (actual.getIdDestino().equals(idDestino)
                        && actual.getTipoRelacion().equals(tipoRelacion)) {
                    return;
                }

                actual = actual.getSiguiente();
            }

            if (!(actual.getIdDestino().equals(idDestino)
                    && actual.getTipoRelacion().equals(tipoRelacion))) {
                actual.setSiguiente(nuevo);
            }
        }
    }

    @Override
    public int calcularGradoSeparacion(String origen, String destino) {
        if (origen.equals(destino)) return 0;

        usuarios.limpiarVisitados();

        Nodo_Diccionario nodoOrigen = usuarios.obtenerNodo(origen);
        if (nodoOrigen == null) return -1;

        ColaPropia cola = new ColaPropia();
        nodoOrigen.setVisitado(true);
        cola.encolar(nodoOrigen, 0);

        while (!cola.estaVacia()) {
            NodoCola actual = cola.desencolar();

            if (actual.nodo.getClave().equals(destino)) {
                return actual.distancia;
            }

            Nodo_Adyacente ady = actual.nodo.getAdyacentes();

            while (ady != null) {
                Nodo_Diccionario vecino = usuarios.obtenerNodo(ady.getIdDestino());

                if (vecino != null && !vecino.isVisitado()) {
                    vecino.setVisitado(true);
                    cola.encolar(vecino, actual.distancia + 1);
                }

                ady = ady.getSiguiente();
            }
        }

        return -1;
    }


    @Override
    public Lista<String> obtenerSugerencias(String email) {

        Lista<String> lista = new Lista<>();

        Nodo_Diccionario origen = usuarios.obtenerNodo(email);

        if (origen == null) return lista;

        usuarios.limpiarVisitados();
        origen.setVisitado(true);

        Nodo_Adyacente ady = origen.getAdyacentes();

        while (ady != null) {
            Nodo_Diccionario amigo = usuarios.obtenerNodo(ady.getIdDestino());
            if (amigo != null) amigo.setVisitado(true);
            ady = ady.getSiguiente();
        }

        ady = origen.getAdyacentes();

        while (ady != null) {

            Nodo_Diccionario amigo = usuarios.obtenerNodo(ady.getIdDestino());

            if (amigo != null) {

                Nodo_Adyacente ady2 = amigo.getAdyacentes();

                while (ady2 != null) {

                    Nodo_Diccionario sugerencia =
                            usuarios.obtenerNodo(ady2.getIdDestino());

                    if (sugerencia != null && !sugerencia.isVisitado()) {
                        lista.add(sugerencia.getClave());
                        sugerencia.setVisitado(true);
                    }

                    ady2 = ady2.getSiguiente();
                }
            }

            ady = ady.getSiguiente();
        }

        return lista;
    }

    @Override  //Recorrer la lista ignorando los seguidos
    public void mostrarContactos(String email) {

        Nodo_Diccionario usuario = usuarios.obtenerNodo(email);

        if (usuario == null) return;

        Nodo_Adyacente ady = usuario.getAdyacentes();

        System.out.println("\nMIS CONTACTOS:");

        while (ady != null) {

            Nodo_Diccionario contacto =
                    usuarios.obtenerNodo(ady.getIdDestino());

            if (contacto != null && ady.getTipoRelacion().equals("CONTACTO")) {

                System.out.println("- " + contacto.getValor().getNombre());
            }

            ady = ady.getSiguiente();
        }
    }

    @Override  //Recorre lista ignorando los contactos

    public void mostrarSeguidos(String email) {

        Nodo_Diccionario usuario = usuarios.obtenerNodo(email);

        if (usuario == null) {
            return;
        }

        Nodo_Adyacente ady = usuario.getAdyacentes();

        System.out.println("\nUSUARIOS QUE SIGUES:");

        boolean hay = false;

        while (ady != null) {

            if (ady.getTipoRelacion().equals("SIGUE")) {

                Nodo_Diccionario seguido =
                        usuarios.obtenerNodo(ady.getIdDestino());

                if (seguido != null) {
                    System.out.println("- " + seguido.getValor().getNombre());
                    hay = true;
                }
            }

            ady = ady.getSiguiente();
        }

        if (!hay) {
            System.out.println("No sigues a ningún usuario.");
        }
    }

    // =====================
    // Cola interna BFS
    // =====================

    private class NodoCola {
        Nodo_Diccionario nodo;
        int distancia;
        NodoCola siguiente;

        public NodoCola(Nodo_Diccionario n, int d) {
            this.nodo = n;
            this.distancia = d;
        }
    }

    private class ColaPropia {

        NodoCola frente, fin;

        public void encolar(Nodo_Diccionario n, int dist) {
            NodoCola nuevo = new NodoCola(n, dist);
            if (frente == null) {
                frente = fin = nuevo;
            } else {
                fin.siguiente = nuevo;
                fin = nuevo;
            }
        }

        public NodoCola desencolar() {
            if (frente == null) return null;
            NodoCola aux = frente;
            frente = frente.siguiente;
            if (frente == null) fin = null;
            return aux;
        }

        public boolean estaVacia() {
            return frente == null;
        }
    }



    @Override        //Crea una solicitud de contacto al usuario de destino
    public void enviarSolicitudContacto(String emailOrigen, String emailDestino) {

        Nodo_Diccionario origen = usuarios.obtenerNodo(emailOrigen);
        Nodo_Diccionario destino = usuarios.obtenerNodo(emailDestino);

        if (origen == null || destino == null) {
            System.out.println("El usuario no existe.");
            return;
        }


        if (emailOrigen.equalsIgnoreCase(emailDestino)) {
            System.out.println("No puedes enviarte una solicitud a ti mismo.");
            return;
        }


        Nodo_Adyacente ady = origen.getAdyacentes();

        while (ady != null) {

            if (ady.getIdDestino().equalsIgnoreCase(emailDestino)
                    && ady.getTipoRelacion().equals("CONTACTO")) {

                System.out.println("Ya son contactos.");
                return;
            }

            ady = ady.getSiguiente();
        }


        SolicitudContacto solicitud =

                new SolicitudContacto(emailOrigen, emailDestino);


        destino.getValor().agregarSolicitud(solicitud);


        System.out.println("Solicitud enviada correctamente.");

    }

    @Override
    public void aceptarSolicitud(Clase_Perfil usuarioReceptor, int indiceSolicitud) {

        SolicitudContacto solicitud = usuarioReceptor.obtenerSolicitud(indiceSolicitud);


        if (solicitud == null) {
            System.out.println("Solicitud inexistente.");
            return;
        }


        conectar(solicitud.getRemitente(), solicitud.getDestinatario());


        // Elimina la solicitud pendiente

        usuarioReceptor.eliminarSolicitud(indiceSolicitud);


        System.out.println("Solicitud aceptada. Ahora son contactos.");
    }

    @Override
    public void rechazarSolicitud(Clase_Perfil usuarioReceptor, int indiceSolicitud) {

        SolicitudContacto solicitud =
                usuarioReceptor.obtenerSolicitud(indiceSolicitud);


        if (solicitud == null) {
            System.out.println("Solicitud inexistente.");
            return;
        }


        usuarioReceptor.eliminarSolicitud(indiceSolicitud);


        System.out.println("Solicitud rechazada.");
    }
}
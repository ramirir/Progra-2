import java.util.Scanner;
import Diccionario.Diccionario;
import clases.Clase_Perfil;
import Cola.Cola;
import clases.Postulacion;
import Grafo.GrafoLista;
import arbol.ArbolHabilidades;
import arbol.Lista;

public class Main {
    public static void main(String[] args) {
        Diccionario plataforma = new Diccionario();
        GrafoLista redContactos = new GrafoLista(plataforma);
        Cola colaJava = new Cola();
        Cola colaSistemas = new Cola();
        Cola colaLinux = new Cola();
        ArbolHabilidades arbolHabilidades = inicializarArbolHabilidades();
        Scanner teclado = new Scanner(System.in);

        //Usuarios ya creados

        Clase_Perfil user1 = new Clase_Perfil("juan@mail.com", "Juan Perez", "Desarrollador Java");
        Clase_Perfil user2 = new Clase_Perfil("ana@mail.com", "Ana Gomez", "Analista de Sistemas");
        Clase_Perfil user3 = new Clase_Perfil("lucas@mail.com", "Lucas Diaz", "Administrador Linux");

        plataforma.insertar(user1.getId(), user1);
        plataforma.insertar(user2.getId(), user2);
        plataforma.insertar(user3.getId(), user3);

        arbolHabilidades.asociarProfesional("Java", user1.getId());
        arbolHabilidades.asociarProfesional("Analisis de Sistemas", user2.getId());
        arbolHabilidades.asociarProfesional("Linux", user3.getId());

        //Establecemos conexiones para asi probar funcionalidades
        redContactos.conectar(user1.getId(), user2.getId());
        redContactos.conectar(user2.getId(), user3.getId());

        //Usuarios ya encolados

        colaJava.encolar(new Postulacion(user1.getId(), "Desarrollador de Java Backend"));
        colaSistemas.encolar(new Postulacion(user2.getId(), "Analista de Sistemas "));


        int opcionPrincipal;

        do {
            System.out.println("\n-----------------------------------------------");
            System.out.println("          PLATAFORMA LABORAL - MENÚ PRINCIPAL    ");
            System.out.println("-------------------------------------------------");
            System.out.println("1- Altas");
            System.out.println("2- Bajas");
            System.out.println("3- Consultas y Reportes del Sistema");
            System.out.println("4- Salir");
            System.out.println("-------------------------------------------------");
            System.out.print("Elija una opción del menú principal: ");
            opcionPrincipal = leerEntero(teclado);;
            teclado.nextLine();

            if (opcionPrincipal == 1) {
                int opcionAltas;
                System.out.println("\n SUBMENU 1- ALTAS");
                System.out.println(" 1- Registrar usuario");
                System.out.println(" 2- Iniciar sesion de usuario");
                System.out.print("Seleccione una opcion (1 o 2): ");
                opcionAltas = leerEntero(teclado);
                teclado.nextLine();

                if (opcionAltas == 1) {

                    System.out.println("Ingrese Email :");
                    String id = teclado.nextLine();

                    if (!esEmailValido(id)) {
                        System.out.println("Email inválido.");
                        continue;
                    }


                    if (plataforma.recuperar(id) != null) {
                        System.out.println("Ese email ya se encuentra registrado.");
                        continue;
                    }

                    System.out.println("Ingrese Nombre completo:");
                    String nombre = teclado.nextLine();

                    System.out.println("Ingrese Profesion actual:");
                    String prof = teclado.nextLine();

                    Clase_Perfil nuevo = new Clase_Perfil(id, nombre, prof);
                    plataforma.insertar(id, nuevo);

                    System.out.println("\nSeleccione una habilidad:");

                    arbolHabilidades.mostrarPreOrden();
                    String habilidad;

                    do {
                        System.out.print("Habilidad: ");
                        habilidad = teclado.nextLine();

                        if (!arbolHabilidades.existeHabilidad(habilidad)) {
                            System.out.println("La habilidad no existe. Intente nuevamente.");
                        }

                    } while (!arbolHabilidades.existeHabilidad(habilidad));

                    arbolHabilidades.asociarProfesional(habilidad, nuevo.getId());

                    System.out.println("Habilidad asociada correctamente.");
                    System.out.println("-> ¡Usuario registrado con éxito!");


                } else if (opcionAltas == 2) {        // Se piden datos de ingreso al usuario
                    System.out.println("Ingrese su Email para ingresar:");
                    String loginId = teclado.nextLine();

                    Clase_Perfil UsuarioLogueado = plataforma.recuperar(loginId);
                    if (UsuarioLogueado != null) {
                        System.out.println("-> ¡Bienvenido " + UsuarioLogueado.getNombre() + "!");   // Usuario ingresa correctamente
                        menuUsuario(UsuarioLogueado, plataforma, redContactos, colaJava, colaSistemas, colaLinux, arbolHabilidades, teclado);  // Llamamos a la funcion del menu usuario
                    } else {
                        System.out.println("-> Error: El email no coincide con ningún usuario registrado.");
                    }
                }
            } else if (opcionPrincipal == 2) {// Llamamos a la funcion de bajas

                menuBajas(plataforma, teclado);

            } else if (opcionPrincipal == 3) {

                menuConsultas(plataforma, colaJava, colaSistemas, colaLinux, arbolHabilidades, teclado);
            }

        } while (opcionPrincipal != 4);

        teclado.close();
    }

    //-------------------------
    //    Menu de usuario
    //-------------------------

    public static void menuUsuario(Clase_Perfil usuario, Diccionario plataforma, GrafoLista redContactos, Cola colaJava, Cola colaSistemas, Cola colaLinux, ArbolHabilidades arbolHabilidades, Scanner teclado) {
        int opcionUsuario;
        do {
            System.out.println("\n    -------------------------------------------------");
            System.out.println("    PANEL DE USUARIO: " + usuario.getNombre().toUpperCase());
            System.out.println("    -------------------------------------------------");
            System.out.println("    1- Ver mis Datos del Perfil");
            System.out.println("    2- Modificar Carrera / Profesión");
            System.out.println("    3- Postularse a una Oferta de Empleo");
            System.out.println("    4- Deshacer última modificación del perfil (Pila)");
            System.out.println("    5- Ver mis contactos");
            System.out.println("    6- Ver contactos recomendados");
            System.out.println("    7- Calcular grado separacion");
            System.out.println("    8- Asociar habilidad");
            System.out.println("    9- Cerrar Sesión");
            System.out.print("    Elija una opción de su cuenta: ");
            opcionUsuario = leerEntero(teclado);
            teclado.nextLine();

            if (opcionUsuario == 1) {
                System.out.println("\n    [ Tus Datos Actuales ]");
                System.out.println("    Email/ID: " + usuario.getId());
                System.out.println("    Nombre:   " + usuario.getNombre());
                System.out.println("    Carrera:  " + usuario.getProfesion());

            } else if (opcionUsuario == 2) {
                System.out.println("    Carrera actual: " + usuario.getProfesion());
                System.out.print("    Ingrese su NUEVA carrera: ");
                String nuevaProf = teclado.nextLine();

                usuario.actualizarProfesion(nuevaProf);
                System.out.println("    Carrera actualizada. Cambio guardado.");

            } else if (opcionUsuario == 3) {
                System.out.println("\n    [ OFERTAS DE TRABAJO DISPONIBLES ]");
                System.out.println("    1- Desarrollador de Java Backend");
                System.out.println("    2- Analista de Sistemas / Funcional");
                System.out.println("    3- Administrador de Servidores Linux");
                System.out.print("    Seleccione el puesto al que desea aplicar (1-3): ");

                int puestoElegido = leerEntero(teclado);
                teclado.nextLine();

                if (puestoElegido == 1) {
                    Postulacion nuevaSolicitud = new Postulacion(usuario.getId(), "Desarrollador de Java Backend");
                    colaJava.encolar(nuevaSolicitud);
                    System.out.println(" ¡Postulación exitosa! Entraste a la cola de Desarrollador de Java.");

                } else if (puestoElegido == 2) {
                    Postulacion nuevaSolicitud = new Postulacion(usuario.getId(), "Analista de Sistemas / Funcional");
                    colaSistemas.encolar(nuevaSolicitud);
                    System.out.println(" ¡Postulación exitosa! Entraste a la cola de Analista de Sistemas.");

                } else if (puestoElegido == 3) {
                    Postulacion nuevaSolicitud = new Postulacion(usuario.getId(), "Administrador de Servidores Linux");
                    colaLinux.encolar(nuevaSolicitud);
                    System.out.println(" ¡Postulación exitosa! Entraste a la cola de Administrador de Servidores de Linux.");
                } else {
                    System.out.println(" Opción de puesto inválida.");
                }


            } else if (opcionUsuario == 4) {
                System.out.println("    Carrera actual: " + usuario.getProfesion());
                if (usuario.deshacerUltimoCambio()) {
                    System.out.println("  ¡Cambio deshecho! Carrera restaurada.");
                    System.out.println("    Carrera actual: " + usuario.getProfesion());
                } else {
                    System.out.println("  No hay modificaciones guardadas en el historial.");
                }

            } else if (opcionUsuario == 5) {
                redContactos.mostrarContactos(usuario.getId());
            } else if (opcionUsuario == 6) {
                mostrarContactosRecomendados(usuario,plataforma, redContactos, teclado);
            } else if (opcionUsuario == 7) {
                calcularGradoSeparacion(usuario, redContactos, teclado);
            } else if (opcionUsuario == 8) {
                System.out.println("\nHABILIDADES DISPONIBLES:");
                arbolHabilidades.mostrarPreOrden();

                System.out.print("Ingrese la habilidad: ");
                String habilidad = teclado.nextLine();

                if (arbolHabilidades.existeHabilidad(habilidad)) {
                    arbolHabilidades.asociarProfesional(
                            habilidad,
                            usuario.getId());
                } else {
                    System.out.println("La habilidad no existe.");
                }
            } else if (opcionUsuario == 9) {

                System.out.println("  Cerrando sesion de" + usuario.getNombre() + "...");
            } else {
                System.out.println("  Opcion Invalida");
            }
        } while (opcionUsuario != 9); //  Cerrar Sesión
    }
    // -----------------------------------------
    //              Menu de bajas
    // -----------------------------------------

    public static void menuBajas(Diccionario plataforma, Scanner teclado) {
        System.out.println("\n  [ SUBMENÚ 2 - BAJAS DEL SISTEMA ]");
        System.out.print("  Ingrese el Email del usuario que desea eliminar: ");
        String bajaId = teclado.nextLine();

        Clase_Perfil pBaja = plataforma.recuperar(bajaId);

        if (pBaja != null) {
            System.out.println("\n ¡Usuario encontrado!");
            System.out.println("  Nombre: " + pBaja.getNombre());
            System.out.println("  Carrera: " + pBaja.getProfesion());
            System.out.print("\n  ¿Está seguro de que desea eliminar este perfil permanentemente? (S/N): ");
            String confirmacion = teclado.nextLine();

            if (confirmacion.equalsIgnoreCase("S")) {
                plataforma.eliminar(bajaId); // Desenlaza el nodo
                System.out.println("  ¡Usuario \"" + pBaja.getNombre() + "\" dado de baja correctamente!");
            } else {
                System.out.println(" Operación cancelada. El usuario no fue eliminado.");
            }
        } else {
            System.out.println("  Error: No se encontró ningún usuario con el email: " + bajaId);
        }
    }

    //Menu de consultas


    public static void menuConsultas(Diccionario plataforma, Cola colaJava, Cola colaSistemas, Cola colaLinux, ArbolHabilidades arbolHabilidades, Scanner teclado) {
        int opcionConsultas;

        System.out.println("\n  [ SUBMENÚ 3 - CONSULTAS Y REPORTES ]");
        System.out.println("  1- Buscar Perfil (Identificación Inmediata)");
        System.out.println("  2- Procesar Siguiente Postulante en la Cola (Orden de llegada)");
        System.out.println("  3- Buscar profesionales por habilidad");
        System.out.print("  Seleccione una opción (1 a 3): ");
        opcionConsultas = teclado.nextInt();
        teclado.nextLine(); // Limpiar el buffer

        if (opcionConsultas == 1) {
            System.out.print("\n  Ingrese el Email del usuario a consultar: ");
            String buscarId = teclado.nextLine();

            Clase_Perfil enc = plataforma.recuperar(buscarId);

            if (enc != null) {
                System.out.println("\n  [ Datos del Perfil Encontrado ]");
                System.out.println("  ID/Email: " + enc.getId());
                System.out.println("  Nombre:   " + enc.getNombre());
                System.out.println("  Carrera:  " + enc.getProfesion());
            } else {
                System.out.println(" El perfil no existe en el sistema.");
            }

        } else if (opcionConsultas == 2) {
            System.out.println("\n  [ Seleccione qué cola de puesto desea procesar ]");
            System.out.println("  1- Cola Desarrollador Java Backend");
            System.out.println("  2- Cola Analista de Sistemas / Funcional");
            System.out.println("  3- Cola Administrador de Servidores Linux");
            System.out.print("  Elija una opción (1-3): ");
            int seleccion = teclado.nextInt();
            teclado.nextLine();

            Cola colaElegida = null;
            if (seleccion == 1) colaElegida = colaJava;
            else if (seleccion == 2) colaElegida = colaSistemas;
            else if (seleccion == 3) colaElegida = colaLinux;
             else {
                System.out.println("  Opción inválida. Volviendo al menú ");
                return;
            }
            if (!colaElegida.esta_vacia()) {
                // 1. Capturamos la postulación del frente
                Postulacion solicitudActual = colaElegida.ver_primero();


                String emailPostulante = solicitudActual.getEmailUsuario();
                String puestoPostulado = solicitudActual.getPuestoTrabajo();

                //  Buscamos a la persona real en el Diccionario
                Clase_Perfil pPrimero = plataforma.recuperar(emailPostulante);


                if (pPrimero != null) {
                    System.out.println("\n  [ Evaluando Siguiente Solicitud por Orden de Llegada ]");
                    System.out.println("  PUESTO APLICADO:         " + puestoPostulado.toUpperCase());
                    System.out.println("  ------------------------------------------------------------");
                    System.out.println("  Postulante a entrevista: " + pPrimero.getNombre());
                    System.out.println("  Especialidad/Carrera:    " + pPrimero.getProfesion());
                    System.out.println("  Email de contacto:       " + emailPostulante);
                } else {
                    // Si el perfil dio null es porque el usuario fue eliminado del diccionario
                    System.out.println("\n  [Alerta]");
                    System.out.println("  La postulación para el puesto " + puestoPostulado + "' pertenece al email (" + emailPostulante + "), pero el usuario ya no existe en el sistema (fue dado de baja).");
                }

                // desencolamos para que la fila no se quede trabada para siempre
                colaElegida.desencolar();
                System.out.println("\n Solicitud removida de la Cola de espera con éxito.");

            } else {
                System.out.println(" No hay solicitudes pendientes en la cola de postulaciones.");
            }

        } else if (opcionConsultas == 3) {

            System.out.print("Ingrese habilidad: ");
            String habilidad = teclado.nextLine();

            Lista<String> profesionales =
                    arbolHabilidades.buscarProfesionalesPorHabilidad(habilidad);

            for (int i = 0; i < profesionales.size(); i++) {

                Clase_Perfil perfil =
                        plataforma.recuperar(profesionales.get(i));

                if (perfil != null) {
                    System.out.println(
                            perfil.getNombre()
                                    + " - "
                                    + perfil.getProfesion()
                    );
                }
            }
        }
    }


    public static void mostrarContactosRecomendados(Clase_Perfil usuario,Diccionario plataforma,  GrafoLista redContactos, Scanner teclado) {

        System.out.println("\n    [ CONTACTOS RECOMENDADOS ]");

        Lista<String> sugerencias =
                redContactos.obtenerSugerencias(usuario.getId());

        if (sugerencias.isEmpty()) {
            System.out.println("No hay sugerencias.");
            return;
        }

        for (int i = 0; i < sugerencias.size(); i++) {

            Clase_Perfil perfil =
                    plataforma.recuperar(sugerencias.get(i));

            if (perfil != null) {
                System.out.println(
                        (i + 1) + " - " +
                                perfil.getNombre()
                );
            }
        }

        System.out.print("\n¿Desea agregar alguno? (S/N): ");
        String respuesta = teclado.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {

            System.out.print("Seleccione una opción: ");
            int opcion = teclado.nextInt();
            teclado.nextLine();

            if (opcion >= 1 && opcion <= sugerencias.size()) {

                String emailElegido =
                        sugerencias.get(opcion - 1);

                redContactos.conectar(
                        usuario.getId(),
                        emailElegido
                );

                System.out.println(
                        "Contacto agregado correctamente."
                );

            } else {
                System.out.println("Opción inválida.");
            }
        }
    }


    public static void calcularGradoSeparacion(Clase_Perfil usuario, GrafoLista redContactos, Scanner teclado) {
        System.out.println("\n    [ GRADO DE SEPARACIÓN ]");
        System.out.print("    Ingrese el Email de destino a consultar (ej: lucas@mail.com): ");
        String emailDestino = teclado.nextLine();

        int grado = redContactos.calcularGradoSeparacion(usuario.getId(), emailDestino);
        if (grado == -1) {
            System.out.println("  No existe un camino o vínculo que te conecte con ese usuario.");
        } else if (grado == 0) {
            System.out.println(" Ese eres tu mismo.");
        } else {
            System.out.println("  Grado de separación: " + grado + " saltos.");
        }
    }

    public static ArbolHabilidades inicializarArbolHabilidades() {

        ArbolHabilidades arbol = new ArbolHabilidades();

        arbol.agregarRaiz("Habilidades");

        arbol.agregarHabilidad("Habilidades", "Programacion");
        arbol.agregarHabilidad("Habilidades", "Sistemas");
        arbol.agregarHabilidad("Habilidades", "Infraestructura");

        arbol.agregarHabilidad("Programacion", "Java");
        arbol.agregarHabilidad("Programacion", "Backend");

        arbol.agregarHabilidad("Sistemas", "Analisis de Sistemas");
        arbol.agregarHabilidad("Sistemas", "Analisis Funcional");

        arbol.agregarHabilidad("Infraestructura", "Linux");
        arbol.agregarHabilidad("Infraestructura", "Servidores");

        return arbol;
    }

    public static boolean esEmailValido(String email) {

        return email.contains("@") && email.contains(".");
    }

    public static int leerEntero(Scanner teclado) {

        while (!teclado.hasNextInt()) {
            System.out.println("Debe ingresar un número.");
            teclado.nextLine();
        }

        int numero = teclado.nextInt();
        return numero;
    }
}



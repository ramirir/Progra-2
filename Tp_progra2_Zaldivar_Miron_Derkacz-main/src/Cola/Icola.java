package Cola;

import clases.Postulacion;

public interface Icola {
    void encolar(Postulacion elemento);

    void desencolar();

    Postulacion ver_primero();

    boolean esta_vacia();
}


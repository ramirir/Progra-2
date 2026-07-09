package Cola;
import clases.Postulacion;
import clases.Nodo_Cola;

public class Cola implements Icola{
    private Nodo_Cola primero;
    private Nodo_Cola ultimo;

    public Cola() {
        this.primero=null;
        this.ultimo=null;
    }
    @Override
    public void encolar(Postulacion dato) {
        Nodo_Cola nuevo = new Nodo_Cola(dato);

        if (esta_vacia()) {
            primero = nuevo;
        }
        else {
            ultimo.setSiguiente(nuevo);
        }

        ultimo= nuevo;
    }

    @Override
    public void desencolar() {
        if (!esta_vacia()){
            primero= primero.getSiguiente();

            if (primero==null){
                ultimo=null;
            }

        }

    }

    @Override
    public Postulacion ver_primero() {
        if (esta_vacia()) {
            return null;
        }
        return primero.getInfo();
    }

    @Override
    public boolean esta_vacia() {
        return primero == null;
    }
}

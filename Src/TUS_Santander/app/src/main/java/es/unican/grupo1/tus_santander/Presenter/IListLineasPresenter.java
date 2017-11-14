package es.unican.grupo1.tus_santander.Presenter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;


/**
 * Define los metodos basicos qeu tiene que implementar el presenter de lineas.
 */
public interface IListLineasPresenter {

    /**
     * Método a través del cual se almacenan las lineas de buses en el atributo listaLineasBus
     * de esta clase. Para realizar esto internamente realiza una llamada a la función
     * getJSON (RemoteFetch) para seguidamente parsear el JSON obtenido con la llamada
     * a readArrayLineasBus (ParserJSON)
     *
     * @return true en caso de que lo haya obtenido con exito, false en caso contrario
     */
    boolean obtenLineas();

    /**
     * Observador de listaLineasBus.
     *
     * @return lista de lineas
     */
    List<Linea> getListaLineasBus();

    /**
     * Método para obtener un cadena de texto con todas las lineas. En esta cadena
     * se muestra unicamente el nombre de la linea
     *
     * @return String con todas las gasolineras separadas por un doble salto de línea
     */
    String getTextoLineas();
}

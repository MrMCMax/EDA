package aplicaciones.primitiva;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;

/** 
 * ApuestaPrimitiva: representa una apuesta aleatoria de La Primitiva, 
 * o combinacion de 6 numeros distintos entre el 1 y el 49 elegidos  
 * de forma aleatoria.
 * 
 * @version Febrero 2019
 */

public class ApuestaPrimitiva {

    // Una Primitiva TIENE UNA Lista con PI que almacena
    // una combinacion de 6 numeros de La Primitiva
    private ListaConPI<NumeroPrimitiva> combinacion;

    /**
     * Crea una ApuestaPrimitiva, o una combinacion de  
     * seis numeros aleatorios con valores distintos y   
     * en el intervalo [1, 49].
     * 
     * @param ordenada Un boolean que indica si la combinacion,  
     *                 sus 6 numeros, debe estar ordenada Asc.
     *                 (true) o no (false).           
     */
    public ApuestaPrimitiva(boolean ordenada) {
        if (ordenada) {
            combinacion = new LEGListaConPIOrdenada<NumeroPrimitiva>();
        } else {
            combinacion = new LEGListaConPI<NumeroPrimitiva>();
        }
        combinacion.inicio();
        while (combinacion.talla() < 6) {
            NumeroPrimitiva n = new NumeroPrimitiva();
            if (posicionDe(n) == -1) {
                combinacion.insertar(n);
            }
        }
    }

    /**
     * Devuelve la posicion del numero n en una ApuestaPrimitiva, 
     * o -1 si n no forma parte de la combinacion. 
     * IMPORTANTE: se asume que el primer elemento de una combinacion 
     * esta en su posicion 0 y el ultimo en la 5.
     * 
     * @param n un int
     * @return  la posicion de n en una combinacion, un valor int
     *          en el intervalo [0, 5] si n esta en la combinacion      
     *          o -1 en caso contrario
     */
    protected int posicionDe(NumeroPrimitiva n) {
        combinacion.inicio();
        int i = 0;
        while (!combinacion.esFin() && !combinacion.recuperar().equals(n)) {
            i++;
            combinacion.siguiente();
        }
        return i == combinacion.talla() ? -1 : i;
    }

    /**
     * Devuelve el String que representa una ApuestaPrimitiva en el formato
     * texto que muestra el siguiente ejemplo: "16, 25, 28, 49, 9, 20"
     * 
     * @return el String con la ApuestaPrimitiva en el formato texto dado. 
     */
    public String toString() {
        combinacion.inicio();
        String res = "";
        if (combinacion.talla() > 0) {
            res += combinacion.recuperar();
            combinacion.siguiente();
            while (!combinacion.esFin()) {
                res += ", " + combinacion.recuperar();
                combinacion.siguiente();
            }
        }
        return res;
    }
    
    // public String toString() {
        // combinacion.inicio();
        // String res = "";
        // int penultimoIndice = combinacion.talla() - 1;
        // for (int indice = 0; indice < penultimoIndice; indice++) {
            // res += combinacion.recuperar() + ", ";
            // combinacion.siguiente();
        // }
           // res += combinacion.recuperar();
        // return res;
    // }
}

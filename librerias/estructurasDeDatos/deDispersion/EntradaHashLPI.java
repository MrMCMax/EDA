package librerias.estructurasDeDatos.deDispersion;

/**
 * Nodo de una Lista con PI 
 * con la que se implementa una cubeta de una TablaHash: 
 * TIENE la clave y el valor de una Entrada
 */

class EntradaHashLPI<C, V> {    
    C clave;
    V valor;

    EntradaHashLPI(C clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }
	
	/*******************************
     * SOLO PARA EJEMPLOS DE TEORIA
     *******************************/
    public String toString() { 
        return "(" + this.clave + ", " + this.valor + ")"; 
    }
}
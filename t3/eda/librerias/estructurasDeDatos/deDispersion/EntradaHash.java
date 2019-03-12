package librerias.estructurasDeDatos.deDispersion;

/**
 * Nodo de una Lista Enlazada Directa 
 * con la que se implementa una cubeta de una TablaHash: 
 * TIENE la clave y el valor de una Entrada 
 * y TIENE una referencia al siguiente nodo
 */

class EntradaHash<C, V> {    
    C clave;
    V valor;
    EntradaHash<C, V> siguiente;
    
    public EntradaHash(C clave, V valor, EntradaHash<C, V> siguiente) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = siguiente;
    }
    
    /*******************************
     * SOLO PARA EJEMPLOS DE TEORIA
     *******************************/
    public String toString() { 
        return "(" + this.clave + ", " + this.valor + ")"; 
    }
} 
package librerias.estructurasDeDatos.deDispersion;

import librerias.estructurasDeDatos.modelos.Map;

// para implementar toClaves
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

/**
 * Implementacion de una TablaHash Enlazada 
 * con Listas con PI y SIN REHASHING
 */

public class TablaHashLPI<C, V> implements Map<C, V> {
    
    // Una Tabla Hash de Entradas de Clave de tipo C y  Valor de tipo V ...
    
    /** El valor (float) del factor de carga de una Tabla Hash 
     *  (valor por defecto en la clase java.util.HashMap) 
     */
	public static final double FACTOR_DE_CARGA = 0.75;
    
	/** TIENE UN array de Listas Con PI de Tipo Generico EntradaHashLPI<C, V>:
     *  - elArray[h] representa una cubeta, 
     *    o lista de colisiones asociadas al indice Hash h
     *  - elArray[h] contiene la referencia a la Lista Con PI 
     *    donde se encuentran todas las 
     *    Entradas cuya Clave tiene un indice Hash h 
     */
    protected ListaConPI<EntradaHashLPI<C,V>> elArray[];
    
    /** TIENE UNA talla que representa el numero de Entradas 
     *  almacenadas en una Tabla Hash o, si se prefiere, en sus cubetas
     */
    protected int talla; 
    
    /** Devuelve el indice Hash de la Clave c de una Entrada, i.e. 
     *  la cubeta en la que se debe encontrar la Entrada de clave c
     *  *** SIN ESTE METODO NO SE TIENE UNA TABLA HASH, SOLO UN ARRAY ***
     */
    protected int indiceHash(C c) {
      int indiceHash = c.hashCode() % this.elArray.length;
      if (indiceHash < 0) indiceHash += this.elArray.length; 
      return indiceHash;
    }    
    
    /** Crea una Tabla Hash vacia, con una capacidad (inicial) maxima  
     *  de tallaMaximaEstimada entradas y factor de carga 0.75
     */
    @SuppressWarnings("unchecked") 
    public TablaHashLPI(int tallaMaximaEstimada) {
        int capacidad = siguientePrimo((int) (tallaMaximaEstimada / FACTOR_DE_CARGA));
        elArray = new LEGListaConPI[capacidad];
        for (int i = 0; i < elArray.length; i++) 
            elArray[i] = new LEGListaConPI<EntradaHashLPI<C,V>>();
        talla = 0;
    }
	
    // Devuelve un numero primo MAYOR o IGUAL a n, i.e. el primo que sigue a n
     public static final int siguientePrimo(int n) {
         if (n % 2 == 0) n++;
         for ( ; !esPrimo(n); n += 2); 
         return n;
     } 
	 
     // Comprueba si n es un numero primo
     protected static final boolean esPrimo(int n) {
         for (int i = 3 ; i * i <= n; i += 2) 
            if (n % i == 0) return false; // n NO es primo
         return true; // n SI es primo
     } 
    
    /** Devuelve el valor de la entrada con clave c de una Tabla Hash,
     *  o null si no hay una entrada con clave c en la Tabla
     */
    public V recuperar(C c) {
        //int pos = indiceHash(c);
        //ListaConPI<EntradaHashLPI<C,V>> cubeta = elArray[pos];
        ListaConPI<EntradaHashLPI<C, V>> cubeta = localizar(c);
        V valor = null;
        /* Completar */
        if (!cubeta.esFin()) {
        	valor = cubeta.recuperar().valor;
        }
        return valor;
    }
    
    /** Elimina la entrada con clave c de una Tabla Hash y devuelve
     *  su valor asociado, o null si no hay ninguna entrada con 
     *  clave c en la Tabla
     */
    public V eliminar(C c) {
        //int pos = indiceHash(c);
        //ListaConPI<EntradaHashLPI<C,V>> cubeta = elArray[pos];
        ListaConPI<EntradaHashLPI<C, V>> cubeta = localizar(c);
        V valor = null;
        if (!cubeta.esFin()) {
        	valor = cubeta.recuperar().valor;
        	cubeta.eliminar();
        	talla--;	//Nº de elementos en el map
        }
        return valor;
    }
    
    /**
     * Devuelve la cubeta con el PI sobre el elemento a localizar (o en el fin si no se encontró)
     * @param c el elemento a buscar
     * @return la cubeta con el PI sobre c
     */
    public ListaConPI<EntradaHashLPI<C, V>> localizar(C c) {
    	int pos = indiceHash(c);
        ListaConPI<EntradaHashLPI<C,V>> cubeta = elArray[pos];
        cubeta.inicio();
        /**
        while (!cubeta.esFin() && !cubeta.recuperar().equals(c)) {
        	cubeta.siguiente();
        }
        */
        for (cubeta.inicio(); !cubeta.esFin() && !cubeta.recuperar().equals(c); cubeta.siguiente());
        
    	return cubeta;
    }
        
    /** Inserta la entrada (c, v)  a una Tabla Hash y devuelve  
     *  el antiguo valor asociado a c, o null si no hay ninguna 
     *  entrada con clave c en la Tabla
     */
    // NO HACE REHASHING. En la practica 3 se modificara este metodo
    // de forma que el rehashing se efectua cuando tras insertar una 
    // nueva entrada en la correspondiente cubeta (lista enlazada 
    // directa) de elArray, e incrementar la talla, ...
    // factorDeCarga() > FACTOR_DE_CARGA.
    // Ello equivale, b�sicamente, a que la talla actual 
    // supere la tallaMaximaEstimada.
    public V insertar(C c, V v) {
        //int pos = indiceHash(c);
        //ListaConPI<EntradaHashLPI<C,V>> cubeta = elArray[indiceHash(c)];
        ListaConPI<EntradaHashLPI<C,V>> cubeta = localizar(c);
        V antiguoValor = null;
        if (!cubeta.esFin()) {
        	antiguoValor = cubeta.recuperar().valor;
        	cubeta.recuperar().valor = v;
        } else {
        	//Aquí se haría rehashing si fuera necesario
        	cubeta.insertar(new EntradaHashLPI<C, V>(c, v));
        	talla++;	//Nº de elementos en el map
        }
        return antiguoValor;
    }
     
    /** Comprueba si una Tabla Hash esta vacia, i.e. si tiene 0 entradas.
     */
    public boolean esVacio() { 
        return talla == 0; 
    }
    
    /** Devuelve la talla, o numero de entradas, de una Tabla Hash.
     */
    public int talla() { 
        return talla; 
    } 

    /** Devuelve una ListaConPI con las talla() claves de una Tabla Hash
     */
    public ListaConPI<C> claves() {
        ListaConPI<C> l = new LEGListaConPI<C>();
        /*COMPLETAR*/
        return l;
    }
    
    /** Devuelve el factor de carga actual de una Tabla Hash, i.e. la longitud
     *  media de sus cubetas
     */
    // RECUERDA: este metodo tiene 
    // un coste INDEPENDIENTE de la talla del problema
    // NO hace falta calcular con un bucle la longitud media de las cubetas!!!
    public final double factorDeCarga() {
        return /*COMPLETAR*/;
    }
    
    /*******************************
     * SOLO PARA EJEMPLOS DE TEORIA
     *******************************/
	/** Devuelve un String con las Entradas de una Tabla Hash 
     */
    // RECUERDA: se usa la clase StringBuilder porque es mas eficiente
    public final String toString() {
        StringBuilder res = new StringBuilder();
        /*COMPLETAR*/
        return res.toString(); 
    }
}
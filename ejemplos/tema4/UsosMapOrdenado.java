package ejemplos.tema4;

import librerias.estructurasDeDatos.modelos.MapOrdenado;
import librerias.estructurasDeDatos.modelos.EntradaMap;
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI; 
import librerias.estructurasDeDatos.jerarquicos.ABBMapOrdenado;

/**
 * class UsosMapOrdenado.
 * 
 * @author FTG 
 * @version 1.0
 */

public class UsosMapOrdenado {
    
    /** Diseñar un metodo estatico, generico e iterativo entradas 
     *  que devuelva una ListaConPI con las Entradas de un Map m 
     *  ordenadas ascendentemente. 
     */
    public static <C extends Comparable<C>, V> 
    ListaConPI<EntradaMap<C, V>> entradas(MapOrdenado<C, V> m) 
    {
        ListaConPI<EntradaMap<C, V>> l = new LEGListaConPI<EntradaMap<C, V>>();
        // COMPLETAR
        
        /** Obtener primera Entrada del Map Ordenado por claves,
         *  i.e. la Entrada de clave minima del Map 
         */
        EntradaMap<C, V> e = m.recuperarEntradaMin();
        
        /** Insertar primer elemento de la lista de Entradas 
         *  ordenada ascendentemente por clave 
         */
        l.insertar(e);
        
        /** Para obtener siguientes Entradas de la lista resultado,
         *  recorrer el Map Ordenado por claves 
         */
        for (int i = 1; i < m.talla(); i++ ) {
            /** Obtener siguiente Entrada del Map Ordenado por claves,
             *  i.e. el sucesor de la Entrada e 
             */
            e = m.sucesorEntrada(e.getClave());
            
            /** Insertar siguiente elemento de la lista de Entradas
             *  ordenada ascendentemente por clave 
             */
            l.insertar(e);
        }
        
        return l;
    }
    
    /** Diseñar un metodo estatico, generico e iterativo mapSort 
     *  que, con la ayuda de un MapOrdenado, 
     *  ordene los elementos (Comparable) de un array v.  
     */
    public static <C extends Comparable<C>> void mapSort(C[] v) {
        MapOrdenado<C, C> m = new ABBMapOrdenado<C, C>();
        for (int i = 0; i < v.length; i++) {
        	C x = m.insertar(v[i], v[i]);
        }
        C x = m.recuperarMin();
    	v[0] = x;
    	for (int i = 1; i < v.length; i++) {
    		x = m.sucesor(x);
    		v[i] = x;
    	}
    }
    
    /** Diseñar un metodo estatico, generico e iterativo hayDosQueSuman 
     *  que, dados un array v de enteros y un entero k, 
     *  determine si existen en v dos numeros cuya suma sea k. 
     *  Usar un Map Ordenado como EDA auxiliar.
     */
    public static boolean hayDosQueSuman(Integer[] v, int k) {
    	MapOrdenado<Integer, Integer> m = new ABBMapOrdenado<Integer, Integer>();
    	for (int i = 0; i < v.length; i++) {
    		m.insertar(v[i], v[i]);
    	}
    	Integer min = m.recuperarMin();
    	Integer max = m.recuperarMax();
    	for (int i = 2; i <= m.talla(); i++) {
    		int suma = min + max;
    		if (suma == k) {
    			return true;
    		} else if (suma < k) {
    			min = m.sucesor(min);
    		} else {
    			max = m.predecesor(max);
    		}
    	}
        return false;
    }
}

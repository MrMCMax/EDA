package ejemplos.tema2;

/**
 * Write a description of class DyV here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DyV
{
    public static <E> E mayoritario(E[] v) {
        return mayoritario(v, 0, v.length - 1);
    }

    private static <E> E mayoritario(E[] v, int i, int f) {
        if (i == f) {
            return v[i];
        } else if (f - i == 1) {
            return v[i].equals(v[f]) ? v[i] : null;
        } else {
            int m = (f + i) / 2;
            E mayorIzq = mayoritario(v, i, m);
            if (validate(v, mayorIzq, i, f) != null) {
                return mayorIzq;
            } else {
                E mayorDer = mayoritario(v, m + 1, f);
                return validate(v, mayorDer, i, f);
            }
        }
    }

    private static <E> E validate(E[] array, E mayor, int i, int f) {
        if (mayor != null && 
            count(array, mayor, i, f) > ((f - i + 1) / 2)) {
            return mayor;
        } else {
            return null;
        }
    }

    private static <E> int count(E[] v, E e, int i, int f) {
        int res = 0;
        for (int ix = i; ix <= f; ix++) {
            if (v[ix].equals(e)) {
                res++;
            }
        }
        return res;
    }
}

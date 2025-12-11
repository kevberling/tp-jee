/**
 * 
 */
package fr.ig2i.calculator;

/**
 * @author kberling
 *
 */
public final class CalculUtils {
	
	public static int addition(int entier1, int entier2) {
		return Math.addExact(entier1, entier2);
	}
	
	public static int soustraction(int entier1, int entier2) {
		return Math.subtractExact(entier1, entier2);
	} 

}

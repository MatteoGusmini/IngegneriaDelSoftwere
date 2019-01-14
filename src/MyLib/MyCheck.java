package MyLib;

/**
 * MyCheck contiene una raccolta di metodi di utilità per controlli ricorrenti
 *
 * @author Valtu
 *
 */
public class MyCheck {
	public final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * Controlla se il carattere passato e' una lettera dell'alfabeto
	 * @param carattere carattere da controllare
	 * @return <strong>true</strong> se il carattere inserito e' una lettera dell'alfabeto, <strong>false</strong> se il carattere inserito non e' una lettera dell'alfabeto
	 */
	public static boolean checkLettera(char carattere){
		if((carattere>='A' && carattere<='Z') || (carattere>='a' && carattere<='z'))
			return true;		
		else 
			return false;
	}
	
	/**
	 * Controlla se il carattere passato e' un numero
	 * @param carattere carattere da controllare
	 * @return <strong>true</strong> se il carattere inserito e' un numero, <strong>false</strong> se il carattere inserito non e' un numero
	 */
	public static boolean checkNumero(char carattere){
		if(carattere>='0' && carattere<='9')
			return true;		
		else 
			return false;
	}
}

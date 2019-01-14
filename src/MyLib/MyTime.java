package MyLib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Gestisce i valori relativi al tempo ed alle date
 * @author Valtulini Claudio
 *
 */
public class MyTime {
	public final static String PATTERN_DATA = "dd/MM/yyyy";
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	
	
	/**
	 * Converte i secondi in una stringa nel formato mm:ss
	 * 
	 * @param seconds	il numero di secondi da convertire in mm:ss
	 * @return			Una stringa contenente il valore convertito in mm:ss
	 */
	public static String secondsToString(int seconds) {
	    return String.format("%02d:%02d", seconds / 60, seconds % 60);
	}
	
	public static Date creaData(String data){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat();
		
			sdf.applyPattern(PATTERN_DATA);
			Date daRitornare = sdf.parse(data);
			return daRitornare;
		}
		catch(ParseException e){
			e.getStackTrace();
			return null;
		}
	}
	
	public static String toStringData(Date data){
		SimpleDateFormat sdf = new SimpleDateFormat();
		
		sdf.applyPattern(PATTERN_DATA);
		return sdf.format(data);
	}
	
	
}

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import MyLib.Utility;

public class Partita extends Categoria {
	private Campo genere=new Campo("Genere","Indica se i partecipanti sono maschi o femmine",true,STRING);
	private Campo fasciaEta=new Campo("Fascia di età","Indica l'età massima e minima per partecipare all'evento",true,STRING);
	private ArrayList<Campo> elencoCampi = new ArrayList<>();
	
	
	
	
	public Partita(){
		super("Partita di Calcio","Evento sportivo che prevede un match di 90 minuti");
		elencoCampi.add(genere);
		elencoCampi.add(fasciaEta);
	}

	
	
	//Metodo che visualizza tutti i campi della categoria partita
    public void visualizzaCampi(){
		super.visualizzaCampi();
		for (int i=0; i< elencoCampi.size(); i++){
			
			System.out.println(elencoCampi.get(i).visualizzaCampo());
		}
	}
	
    
    //metodo che fa inserire i valori ai campi di partita
    public void inserisciCampi()throws Exception{
    	super.inserisciCampi();
    	for (int i=0; i< elencoCampi.size(); i++){
			elencoCampi.get(i).inserisciValore();
			
		}
	}
	
	// Getters and Setters generati automaticamente

	public Campo getGenere() {
		return genere;
	}

	public Campo getFasciaEta() {
		return fasciaEta;
	}

	public void setGenere(Campo genere) {
		this.genere = genere;
	}

	public void setFasciaEta(Campo fasciaEta) {
		this.fasciaEta = fasciaEta;
	}


	public ArrayList<Campo> getElencoCampi() {
		return elencoCampi;
	}


	public void setElencoCampi(ArrayList<Campo> elencoCampi) {
		this.elencoCampi = elencoCampi;
	}

	
	
	
}


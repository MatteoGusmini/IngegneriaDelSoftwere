import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import MyLib.Utility;

public class Evento implements Serializable{
	
	//Attributi
	private Categoria categoria;
	private Boolean validità;
	private String creatore;
	private ArrayList <String> elencoIscritti = new ArrayList<>();
	private String stato;
	
	
	//Costruttori
	public Evento(Categoria _categoria, String _creatore){
		categoria= _categoria;
		creatore=_creatore;
		validità = false;
		stato= "Aperta";
	}
	
	
	
	//Metodi
	
	
	//Metodo che verifica che tutti i campi obbligatori abbiano inserito un valore
	public void isValido(){
		validità=true;
		
		for (int i=0; i<categoria.getElencoCampi().size(); i++){
			if(categoria.getElencoCampi().get(i).getObbligatorio()&& !categoria.getElencoCampi().get(i).getValore().getInserito()){
				validità=false;
			}
		}
		
	}
	
	
	// Metodo che permette di inserire i valori a campi dell'evento
	public void inserisciDettagliEvento()throws Exception{
		categoria.inserisciCampi();
	}
	
	
	
	
	public Boolean giàIscritto(String utente) {
		Boolean iscritto= false;
		
		for(int i=0; i< elencoIscritti.size(); i++){
			if (utente.equals(elencoIscritti.get(i))){
				iscritto= true;
			}
		}
		
		
		return iscritto;
	}
	
	
	public void controlloNPartecipanti(){
		if (getPostiLiberi()==0){
			stato= "Chiusa";
		}
	}
	
	public void controlloData(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		
		if(categoria.getDataFine().getValore().getInserito()){
			if( ((Date) categoria.getDataFine().getValore().getValore()).before(date)){
				if (getPostiLiberi()==0){
					stato= "Conclusa";
				}
				else{
					stato="Fallità";
				}
			}
		}
		else{
			if( ((Date) categoria.getData().getValore().getValore()).before(date)){
				if (getPostiLiberi()==0){
					stato= "Conclusa";
				}
				else{
					stato="Fallità";
				}
			}
		}
		
		
		if( ((Date) categoria.getTermineIscrizione().getValore().getValore()).before(date)){
			if (getPostiLiberi()!=0){
				stato="Fallita";
			}
		}
		
		
	}
	
	public int getPostiLiberi(){
		return (int) categoria.getnPartecipanti().getValore().getValore()- elencoIscritti.size();
	}
	
	

	
	
	
	

	
	// Getters and Setters generati automaticamente
	public Categoria getCategoria() {
		return categoria;
	}

	

	public Boolean getValidità() {
		return validità;
	}




	public String getCreatore() {
		return creatore;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public void setValidità(Boolean validità) {
		this.validità = validità;
	}
	


	public void setCreatore(String creatore) {
		this.creatore = creatore;
	}



	public ArrayList<String> getElencoIscritti() {
		return elencoIscritti;
	}



	public void setElencoIscritti(ArrayList<String> elencoIscritti) {
		this.elencoIscritti = elencoIscritti;
	}



	public String getStato() {
		return stato;
	}



	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
	
	
	

}

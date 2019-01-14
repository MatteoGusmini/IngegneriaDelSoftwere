import java.io.Serializable;

import MyLib.Utility;

public class Evento implements Serializable{
	
	//Attributi
	private Categoria categoria;
	private Boolean validitÓ;
	private Boolean insBacheca;
	private String creatore;
	
	
	//Costruttori
	public Evento(Categoria _categoria, String _creatore){
		categoria= _categoria;
		creatore=_creatore;
		validitÓ = false;
		insBacheca = false;
	}
	
	
	
	//Metodi
	
	
	//Metodo che verifica che tutti i campi obbligatori abbiano inserito un valore
	public void isValido(){
		validitÓ=true;
		
		for (int i=0; i<categoria.getElencoCampi().size(); i++){
			if(categoria.getElencoCampi().get(i).getObbligatorio()&& !categoria.getElencoCampi().get(i).getValore().getInserito()){
				validitÓ=false;
			}
		}
		
	}
	
	
	// Metodo che permette di inserire i valori a campi dell'evento
	public void inserisciDettagliEvento()throws Exception{
		categoria.inserisciCampi();
	}



	
	// Getters and Setters generati automaticamente
	public Categoria getCategoria() {
		return categoria;
	}



	public Boolean getValiditÓ() {
		return validitÓ;
	}



	public String getCreatore() {
		return creatore;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public void setValiditÓ(Boolean validitÓ) {
		this.validitÓ = validitÓ;
	}
	
		



	public Boolean getInsBacheca() {
		return insBacheca;
	}



	public void setInsBacheca(Boolean insBacheca) {
		this.insBacheca = insBacheca;
	}



	public void setCreatore(String creatore) {
		this.creatore = creatore;
	}
	
	
	
	

}

import MyLib.Utility;

public class Evento {
	
	//Attributi
	private Categoria categoria;
	private Boolean validità= false;
	private String creatore;
	
	
	//Costruttori
	public Evento(Categoria _categoria, String _creatore){
		categoria= _categoria;
		creatore=_creatore;
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
	
	
	
	

}

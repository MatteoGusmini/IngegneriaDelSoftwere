import java.util.*;
import java.text.SimpleDateFormat;

import MyLib.Utility;

public class Campo {
	
	//Attributi
	private String nome;
	private String descrizione;
	private Boolean obbligatorio;
	private Valore valore;
	
	
	//Costruttori
	public Campo(String _nome, String _descrizione, Boolean _obbligatorio, int _valore){
		nome =_nome;
		descrizione= _descrizione;
		obbligatorio = _obbligatorio;
		valore= new Valore(_valore);
	}
	
	//Metodi
	
	// Metodo che restituisce una stringa che descrive il campo
	public String visualizzaCampo(){
		String s= new String("Nome: "+ nome +"\n" +"Descrizione: " + descrizione+ "\n"+ "Obbligatoria: " + obbligatorio +"\n");
		return s;
	}
	
	// Metodo che inserisce assegna un valore al campo
	public void inserisciValore()throws Exception{
		
	
		if(!valore.getInserito()){
			int inserimento= Utility.leggiIntero(0,1, "Vuoi inserire "+ nome+ "? Digita 1 per SI e 0 pre NO");
			if(inserimento==0){
			
			}
			else{
				switch(valore.getTipo()){
				case 0:
					valore.setValore(Utility.leggiStringa("inserisci "+ nome));
					break;
				case 1:
					valore.setValore(Utility.leggiIntero(0, 9999999, "inserisci "+ nome));
					break;
				case 2: 
					String data=Utility.leggiData("inserisci "+ nome+ "(gg/mm/aaaa)");
					Date date=new SimpleDateFormat("dd/MM/yyyy").parse(data);	
					break;
				}
			}
		}	
	}
	
	
	
	
	// Getters and Setters generati automaticamente
	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Boolean getObbligatorio() {
		return obbligatorio;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setObbligatorio(Boolean obbligatorio) {
		this.obbligatorio = obbligatorio;
	}

	public Valore getValore() {
		return valore;
	}

	public void setValore(Valore valore) {
		this.valore = valore;
	}
	
	
	
	
	
}


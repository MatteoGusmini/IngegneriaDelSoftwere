import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import MyLib.Utility;

public class Evento implements Serializable{
	
	
	
	final String[] TESTOCHIUSURA={"L'evento "," ha raggiunto un numero sufficiente di iscrizioni e si terra dunque in data "," alle ore "," presso ",". Si ricorda che � necessatrio versare la quota di iscrizione di "," Euro."};
	final String[] TESTOFALLITO={"L'evento "," NON ha raggiunto un numero sufficiente di iscrizioni ed � quindi stato cancellato."};
	
	
	//Attributi
	private Categoria categoria;
	private Boolean validita;
	private Utente creatore;
	private ArrayList <Utente> elencoIscritti = new ArrayList<>();
	private String stato;
	
	
	//Costruttori
	public Evento(Categoria _categoria, Utente _creatore){
		categoria= _categoria;
		creatore=_creatore;
		validita = false;
		stato= "Aperta";
	}
	
	
	
	//Metodi
	
	
	//Metodo che verifica che tutti i campi obbligatori abbiano inserito un valore
	public void isValido(){
		validita=true;
		
		for (int i=0; i<categoria.getElencoCampi().size(); i++){
			if(categoria.getElencoCampi().get(i).getObbligatorio()&& !categoria.getElencoCampi().get(i).getValore().getInserito()){
				validita=false;
			}
		}
		
	}
	
	
	// Metodo che permette di inserire i valori a campi dell'evento
	public void inserisciDettagliEvento()throws Exception{
		categoria.inserisciCampi();
	}
	
	
	
	// Metodo che controlla se un utente � gi� iscritto ad un evento
	public Boolean giaIscritto(Utente utente) {
		Boolean iscritto= false;
		
		for(int i=0; i< elencoIscritti.size(); i++){
			if (utente.equals(elencoIscritti.get(i))){
				iscritto= true;
			}
		}
		
		
		return iscritto;
	}
	
	
	// Controlla che le date siano inserite in maniera coerente con il loro significato
	public Boolean controlloDate() {
		Boolean valido= true;
		Date termIsc= (Date) categoria.getTermineIscrizione().getValore().getValore();
		Date dataEv= (Date) categoria.getData().getValore().getValore();
		if(categoria.getDataFine().getValore().getInserito()){
			Date dataConc= (Date) categoria.getDataFine().getValore().getValore();;			
			if(termIsc.after(dataEv)||termIsc.after(dataConc)||dataEv.after(dataConc)){
				valido=false;
			}
		}
		else{
			if(termIsc.after(dataEv))
				valido=false;
		}
				
		return valido;
	}
	
	// Metodo che controlla se il numero di partecipanti di un evento ha raggiunto il limite e se � vero genere i messaggi
	public ArrayList<Messaggio> controlloNPartecipanti(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<Messaggio> messaggiStato = new ArrayList<>();
		
		if (getPostiLiberi()==0 && stato.equalsIgnoreCase("Aperta")){
			stato= "Chiusa";
			for (int i=0;i< elencoIscritti.size();i++){
				
				Utente nomeUtente= elencoIscritti.get(i);
				String testo= TESTOCHIUSURA[0] +categoria.getTitolo().getValore().getValore() + TESTOCHIUSURA[1] + dateFormat.format(categoria.getData().getValore().getValore())+ TESTOCHIUSURA[2] + categoria.getOra().getValore().getValore()+ TESTOCHIUSURA[3] + categoria.getLuogo().getValore().getValore() +TESTOCHIUSURA[4] + categoria.getQuotaIndividuale().getValore().getValore()+ TESTOCHIUSURA[5];                               	
				Messaggio msg =new Messaggio(nomeUtente,testo);
				
				messaggiStato.add(msg);
	
			}
		
		}
		
		return messaggiStato;
		
	}
	
	// Metodo che controlla se si � superata la dta di termine iscrizione o quella di svolgimento dell'evento
	public ArrayList<Messaggio> controlloData(){
		
		// Data odierna per effettuare il confronto
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date("22/02/2021");
		
		
		ArrayList<Messaggio> messaggiStato = new ArrayList<>();
		
		// Verifica se � stata passata la data conclusiva dell'evento (nel caso sia inserita)o la data dell'evento
		if(categoria.getDataFine().getValore().getInserito()){
			if( ((Date) categoria.getDataFine().getValore().getValore()).before(date)){
				if (getPostiLiberi()==0){
					stato= "Conclusa";
				}
			}
		}
		else{
			if( ((Date) categoria.getData().getValore().getValore()).before(date)){
				if (getPostiLiberi()==0){
					stato= "Conclusa";
				}
			}
		}
		
		// Controla se � stata superata la data di termine delle iscrizioni senza aver raggiunto il numero minimo di iscritti
		// Genera dei messaggi in caso affermativo
		if( ((Date) categoria.getTermineIscrizione().getValore().getValore()).before(date)){
			if (getPostiLiberi()!=0){
				stato="Fallita";
				
				for (int i=0;i< elencoIscritti.size();i++){
					Utente nomeUtente= elencoIscritti.get(i);
					String testo= TESTOFALLITO[0] +categoria.getTitolo().getValore().getValore() + TESTOFALLITO[1]; 
					Messaggio msg =new Messaggio(nomeUtente,testo);
					messaggiStato.add(msg);
				}
			}
		}
		
		
		return messaggiStato;
	}
	
	
	// Metodo che ritorna il numero di posti liberi di un evento
	public int getPostiLiberi(){
		return (int) categoria.getnPartecipanti().getValore().getValore()- elencoIscritti.size();
	}
	
	

	
	
	
	

	
	// Getters and Setters generati automaticamente
	public Categoria getCategoria() {
		return categoria;
	}

	

	public Boolean getvalidita() {
		return validita;
	}




	public Utente getCreatore() {
		return creatore;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public void setvalidita(Boolean validita) {
		this.validita = validita;
	}
	


	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}



	public ArrayList<Utente> getElencoIscritti() {
		return elencoIscritti;
	}



	public void setElencoIscritti(ArrayList<Utente> elencoIscritti) {
		this.elencoIscritti = elencoIscritti;
	}



	public String getStato() {
		return stato;
	}



	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
	
	
	

}

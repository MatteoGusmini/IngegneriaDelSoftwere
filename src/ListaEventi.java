import java.io.Serializable;
import java.util.ArrayList;

public class ListaEventi implements Serializable {
	
	private ArrayList<Evento> elencoEventi = new ArrayList<>();
	
	
	public ListaEventi()
	{
		
	}
	
	
	// Metodo per il controllo degli eventi
	public ArrayList<Messaggio> controlloEventi(){
		ArrayList<Messaggio> messaggi = new ArrayList<>();
		
		
		for(int i=0; i<elencoEventi.size();i++){
			ArrayList<Messaggio> messaggiStato1 = new ArrayList<>(elencoEventi.get(i).controlloNPartecipanti());
			ArrayList<Messaggio> messaggiStato2 = new ArrayList<>(elencoEventi.get(i).controlloData());
			messaggi.addAll(messaggiStato1);
			messaggi.addAll(messaggiStato2);
		}
		
		return messaggi;
		
	}
	
	

	public ArrayList<Evento> getElencoEventi() {
		return elencoEventi;
	}

	public void setElencoEventi(ArrayList<Evento> elencoEventi) {
		this.elencoEventi = elencoEventi;
	}

	
	
	
	
}

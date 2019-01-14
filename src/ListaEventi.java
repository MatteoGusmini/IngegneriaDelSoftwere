import java.io.Serializable;
import java.util.ArrayList;

public class ListaEventi implements Serializable {
	
	private ArrayList<Evento> elencoEventi = new ArrayList<>();
	
	
	public ListaEventi()
	{
		
	}
	
	
	public void controlloEventi(){
		for(int i=0; i<elencoEventi.size();i++){
			elencoEventi.get(i).controlloNPartecipanti();
			elencoEventi.get(i).controlloData();
		}
	}
	
	

	public ArrayList<Evento> getElencoEventi() {
		return elencoEventi;
	}

	public void setElencoEventi(ArrayList<Evento> elencoEventi) {
		this.elencoEventi = elencoEventi;
	}

	
	
	
	
}

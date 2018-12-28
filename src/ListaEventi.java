import java.io.Serializable;
import java.util.ArrayList;

public class ListaEventi implements Serializable {
	
	private ArrayList<Evento> elencoEventi = new ArrayList<>();
	
	public ListaEventi()
	{
		
	}
	
	

	public ArrayList<Evento> getElencoEventi() {
		return elencoEventi;
	}

	public void setElencoEventi(ArrayList<Evento> elencoEventi) {
		this.elencoEventi = elencoEventi;
	}

	
	
	
	
}

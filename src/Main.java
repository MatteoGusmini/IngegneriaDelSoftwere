
import java.io.*;
import java.util.ArrayList;

import MyLib.Men�;
import MyLib.Utility;
import MyLib.ServizioFile;


public class Main {

	public static void main(String[] args) throws Exception{
		
	
		
		// Costanti che contengono i messaggi da visualizzare in Output

		final String MSGBENVENUTO="Benvenuto nella social di gestione eventi";
		final String MSGLOGIN="Inserisci il tuo nome utente per effettuare il login";
		final String NOMEMENU="GESTIONE Eventi";
		final String[] OPZIONI={"Visualizza Categorie Disponibili","Crea un nuovo evento","Visualizza i miei eventi","Pubblica eventi","Visualizza Bacheca"};
		final String NOME="Nome categoria: ";
		final String DESCRIZIONE="Descrizione: ";
		final String SCELTACATEGORIA="Quale categoria vuoi vedere in dettaglio?";
		final String SCELTACATEGORIAEVENTO="Quale categoria di evento vuoi creare?";
		final String SCELTAEVENTOPUBBLICAZIONE ="Quale evento vuoi pubblicare?";
		final String NOMEEVENTO="Nome evento: ";
		final String VALIDITAPUBBLICAZIONE = "L'evento selezionato � valido, � stato pubblicato ed � visibile sulla bacheca.";
		final String NONVALIDITAPUBBLICAZIONE = "L'evento selezionato non � valido! Selezionare un altro evento. \n (Un Evento � valido solo se � stato assegnato un valore a tutti i campi obbligatori)";
		final String BACHECAVUOTA = "Non vi sono eventi validi pubblicati.";
		final String path="C:\\Progetto\\Evento1";
		
		File evento1 = new File("eventi.txt");
		File eventiPubblicati = new File ("EventiPubblicati.txt");
		
		System.out.println(MSGBENVENUTO);
		String utente= Utility.leggiStringa(MSGLOGIN);
	
		
		
		ArrayList<Categoria> categorie=new ArrayList<>();
		ListaEventi eventi=new ListaEventi();
		ListaEventi eventiValidi =new ListaEventi();
		
		eventi= (ListaEventi) ServizioFile.caricaSingoloOggetto(evento1);
		eventiValidi = (ListaEventi) ServizioFile.caricaSingoloOggetto(eventiPubblicati);
		
		
		Partita partita= new Partita();
		categorie.add(partita);
		
		
		Men� myMenu= new Men�(NOMEMENU,OPZIONI);
		int scelta;
		
		
		do{
			scelta=myMenu.scegli();
			switch(scelta)
			{
			case 0:
				break;
			
			case 1:
				// Visualizza categorie
				
				for(int i=0; i<categorie.size();i++){
					System.out.println(i+1+")");
					System.out.println(NOME + categorie.get(i).getNome());
					System.out.println(DESCRIZIONE + categorie.get(i).getDescrizione()+"\n");
				}
				int numCat=Utility.leggiIntero(1, categorie.size()+1, SCELTACATEGORIA);
				categorie.get(numCat-1).visualizzaCampi();
				
				
				break;
			case 2:
				// Crea nuovo evento
				for(int i=0; i<categorie.size();i++){
					System.out.println(i+1+")");
					System.out.println(NOME + categorie.get(i).getNome());
					System.out.println(DESCRIZIONE + categorie.get(i).getDescrizione()+"\n");
				}
				
				int numCatEvento=Utility.leggiIntero(1, categorie.size()+1, SCELTACATEGORIAEVENTO);
				
				Evento evento= new Evento(partita,utente);
				evento.inserisciDettagliEvento();
				
				eventi.getElencoEventi().add(evento);
				
				ServizioFile.salvaSingoloOggetto(evento1, eventi);
				
				
			
				break;
				
				
				
				
			case 3:
				// Visualizza i miei eventi
				
				for(int i=0; i<eventi.getElencoEventi().size();i++){
					if(eventi.getElencoEventi().get(i).getCreatore().equals(utente)){
						
					
					System.out.println(i+1 +")");
					if (eventi.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getInserito()){
						System.out.println(NOMEEVENTO + eventi.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());	
					}
					else {
						System.out.println(NOMEEVENTO + "Titolo non ancora inserito");
					}
					System.out.println(NOME + eventi.getElencoEventi().get(i).getCategoria().getNome());
					}
				}
				
				break;
			case 4:
				// Pubblica eventi 
				
				for(int i=0; i<eventi.getElencoEventi().size();i++){
					if(eventi.getElencoEventi().get(i).getCreatore().equals(utente)){
						
					
					System.out.println(i+1 +")");
					if (eventi.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getInserito()){
						System.out.println(NOMEEVENTO + eventi.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());	
					}
					else {
						System.out.println(NOMEEVENTO + "Titolo non ancora inserito");
					}
					System.out.println(NOME + eventi.getElencoEventi().get(i).getCategoria().getNome());
					}
				}
				
				int numEventoPubblicato=Utility.leggiIntero(1, eventi.getElencoEventi().size(), SCELTAEVENTOPUBBLICAZIONE);
				
				if(eventi.getElencoEventi().get(numEventoPubblicato).getValidit�() == true){
					System.out.println(VALIDITAPUBBLICAZIONE);
					eventiValidi.getElencoEventi().add(eventi.getElencoEventi().get(numEventoPubblicato));
					
					ServizioFile.salvaSingoloOggetto(eventiPubblicati, eventiValidi);
				}else
					System.out.println(NONVALIDITAPUBBLICAZIONE);
				
				break;
			case 5:
				// Visualizza Bacheca
				
				if(eventiValidi.getElencoEventi().size() == 0){
					System.out.println(BACHECAVUOTA);
				}else{
				for(int i=0; i<eventiValidi.getElencoEventi().size();i++){
					System.out.println(i+1 +")");
					System.out.println(NOMEEVENTO + eventi.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
					System.out.println(NOME + eventiValidi.getElencoEventi().get(i).getCategoria().getNome());
					}
				}	
				break;

			}
		}while(scelta!=0);
		
	}
	
	
	
}

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import MyLib.Menù;
import MyLib.Utility;
import MyLib.ServizioFile;


public class Main {

	public static void main(String[] args) throws Exception{
		
	
		
		// Costanti che contengono i messaggi da visualizzare in Output

		final String MSGBENVENUTO="Benvenuto nella social di gestione eventi";
		final String MSGLOGIN="Inserisci il tuo nome utente per effettuare il login";
		final String NOMEMENU="GESTIONE Eventi";
		final String[] OPZIONI={"Visualizza Categorie Disponibili","Crea un nuovo evento","Visualizza i miei eventi non ancora pubblicati","Pubblica eventi","Visualizza Bacheca","Partecipa a evento"};
		final String NOME="Nome categoria: ";
		final String STATO="Stato: ";
		final String DESCRIZIONE="Descrizione: ";
		final String SCELTACATEGORIA="Quale categoria vuoi vedere in dettaglio?";
		final String SCELTACATEGORIAEVENTO="Quale categoria di evento vuoi creare?";
		final String SCELTAISCEVENTO="A quale evento desideri iscriverti?";
		final String SCELTAEVENTOPUBBLICAZIONE ="Quale evento vuoi pubblicare?";
		final String NOMEEVENTO="Nome evento: ";
		final String VALIDITAPUBBLICAZIONE = "L'evento selezionato è valido, è stato pubblicato ed è visibile sulla bacheca.";
		final String NONVALIDITAPUBBLICAZIONE = "L'evento selezionato non è valido! Selezionare un altro evento. \n (Un Evento è valido solo se è stato assegnato un valore a tutti i campi obbligatori)";
		final String BACHECAVUOTA = "Non vi sono eventi validi pubblicati.";
		final String path="C:\\Progetto\\Evento1";
		
		
		// Creazione file per il salvataggio dei dati
		File evento1 = new File("eventi.txt");
		File filebacheca = new File ("Bacheca.txt");
		
		
		
		System.out.println(MSGBENVENUTO);
		String utente= Utility.leggiStringa(MSGLOGIN);
	
		
		
		ArrayList<Categoria> categorie=new ArrayList<>();
		ListaEventi eventi=new ListaEventi();
		ListaEventi eventiValidi =new ListaEventi();
		ListaEventi bacheca = new ListaEventi();
		
		
		
		
		
		
		// Caricamento dati del programma
		eventi= (ListaEventi) ServizioFile.caricaSingoloOggetto(evento1);
		//bacheca= (ListaEventi) ServizioFile.caricaSingoloOggetto(filebacheca);
				

	
		
		
		Partita partita= new Partita();
		categorie.add(partita);
		
		
		Menù myMenu= new Menù(NOMEMENU,OPZIONI);
		int scelta;
		
		
		do{
			scelta=myMenu.scegli();
			
			// Controlli su eventi in Bacheca
			
			//bacheca.controlloEventi();
			
			
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
				evento.getElencoIscritti().add(utente);
				
				ServizioFile.salvaSingoloOggetto(evento1, eventi);
				
				
			
				break;
				
				
				
				
			case 3:
				// Visualizza i miei eventi non pubblicati
				
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
				System.out.println("0) Esci");	
					for(int i=0; i<eventi.getElencoEventi().size();i++){
						if(eventi.getElencoEventi().get(i).getCreatore().equals(utente)){
							
						
						System.out.println(i +1 +")");
						if (eventi.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getInserito()){
							System.out.println(NOMEEVENTO + eventi.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore() );	
						}
						else {
							System.out.println(NOMEEVENTO + "Titolo non ancora inserito");
						}
						System.out.println(NOME + eventi.getElencoEventi().get(i).getCategoria().getNome());
						}
					}
				
				int numEventoPubblicato=Utility.leggiIntero(0, eventi.getElencoEventi().size(), SCELTAEVENTOPUBBLICAZIONE);
				
				if(numEventoPubblicato!=0){
				
					Evento eventop = eventi.getElencoEventi().get(numEventoPubblicato -1);
					
					eventop.isValido();
				
					if(eventop.getValidità() == true){
						System.out.println(VALIDITAPUBBLICAZIONE);
						
						bacheca.getElencoEventi().add(eventop);
						eventi.getElencoEventi().remove(numEventoPubblicato-1);
						
						
						ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
						ServizioFile.salvaSingoloOggetto(evento1, eventi);
					}
					else{
						
					
						System.out.println(NONVALIDITAPUBBLICAZIONE);
						int inserimento= Utility.leggiIntero(0,1, "Vuoi inserire completare l'evento? Digita 1 per SI e 0 pre NO");
						if (inserimento==1){
							eventop.inserisciDettagliEvento();
						}
						ServizioFile.salvaSingoloOggetto(evento1, eventi);
						ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
					}
				
				}
				break;
			case 5:
				// Visualizza Bacheca
				
				if(bacheca.getElencoEventi().size() != 0){
					for(int i=0; i<bacheca.getElencoEventi().size();i++){
						System.out.println(i+1 +")");
						System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
						System.out.println(NOME + bacheca.getElencoEventi().get(i).getCategoria().getNome());
						System.out.println(STATO + bacheca.getElencoEventi().get(i).getStato());
						}
					
				}else{
					System.out.println(BACHECAVUOTA);
				}	
				break;
				
			case 6:
				// Partecipa a evento
				
				if(bacheca.getElencoEventi().size() != 0){

					System.out.println("0) Esci");
					for(int i=0; i<bacheca.getElencoEventi().size();i++){
						
						if(!bacheca.getElencoEventi().get(i).giàIscritto(utente)&& bacheca.getElencoEventi().get(i).getStato().equalsIgnoreCase("Aperta")){						
							System.out.println(i+1 +")");
							System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
							System.out.println(NOME + bacheca.getElencoEventi().get(i).getCategoria().getNome());
						}
					
					}
					int numIscEvento=Utility.leggiIntero(0, categorie.size()+1, SCELTAISCEVENTO);
					
					if (numIscEvento!=0){
						bacheca.getElencoEventi().get(numIscEvento-1).getElencoIscritti().add(utente);
					}
					
				}else{
					System.out.println(BACHECAVUOTA);
				}	
				
				
				
				ServizioFile.salvaSingoloOggetto(evento1, eventi);
				ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
				
			
				break;

			}
		}while(scelta!=0);
		
	}
	
	
	
}
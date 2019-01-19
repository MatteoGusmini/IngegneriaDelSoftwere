
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import MyLib.Menu;
import MyLib.ServizioFile;
import MyLib.Utility;



public class Main {

	public static void main(String[] args) throws Exception{
	
		
		// Costanti che contengono i messaggi da visualizzare in Output

		final String MSGBENVENUTO="Benvenuto nella social di gestione eventi";
		final String MSGLOGIN="Inserisci il tuo nome utente per effettuare il login";
		final String NOMEMENU="GESTIONE Eventi";
		final String NOMEMENUMSG="GESTIONE Messaggi";
		final String[] OPZIONIMSG={"Visualizza messaggi", "Elimina messaggi"};
		final String[] OPZIONI={"Visualizza Categorie Disponibili","Crea un nuovo evento","Visualizza i miei eventi non ancora pubblicati","Pubblica eventi","Visualizza Bacheca","Partecipa a evento","Gestione Messaggi"};
		final String NOME="Nome categoria: ";
		final String STATO="Stato: ";
		final String POSTILIBERI="Posti liberi: ";
		final String DESCRIZIONE="Descrizione: ";
		final String SCELTACATEGORIA="Quale categoria vuoi vedere in dettaglio?";
		final String SCELTACATEGORIAEVENTO="Quale categoria di evento vuoi creare?";
		final String SCELTAISCEVENTO="A quale evento desideri iscriverti?";
		final String SCELTAEVENTOPUBBLICAZIONE ="Quale evento vuoi pubblicare?";
		final String SCELTAMSG ="Quale messaggio vuoi eliminare?";
		final String NOMEEVENTO="Nome evento: ";
		final String VALIDITAPUBBLICAZIONE = "L'evento selezionato � valido, � stato pubblicato ed � visibile sulla bacheca.";
		final String NONVALIDITAPUBBLICAZIONE = "L'evento selezionato non � valido! Selezionare un altro evento. \n (Un Evento � valido solo se � stato assegnato un valore a tutti i campi obbligatori)";
		final String BACHECAVUOTA = "Non vi sono eventi validi pubblicati.";
		final String EVENTIVUOTI = "Non ci sono eventi creati e non acora pubblicati in bacheca.";
		final String MESSAGGIVUOTI = "Non ci sono messaggi.";
		final String MSGEVENTO="Evento creato con successo";
		final String MSGPROBDATE="Le date non sono in ordine logico. DATE CANCELLATE";
		
		
		// Creazione file per il salvataggio dei dati
		File filebacheca = new File ("Bacheca.txt");
		File fileutenti = new File ("Utenti.txt");
		
		
		
		// Creazione degli oggetti principali per l'esecuzione del programma
		ArrayList<Categoria> categorie=new ArrayList<>();
		ListaEventi bacheca = new ListaEventi();
		ArrayList<Utente> elencoUtenti=new ArrayList<>();

		
		
		
		
		// Caricamento dati del programma
		if(ServizioFile.esistenzaF() == 0) {
			ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
		}else
			elencoUtenti=  (ArrayList<Utente>) ServizioFile.caricaSingoloOggetto(fileutenti);

		if(ServizioFile.esistenzaF() == 0) {
			ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
		}else
			bacheca= (ListaEventi) ServizioFile.caricaSingoloOggetto(filebacheca);

		
		
		
		// Messaggio di benvenuto e richiesta nome per login
		System.out.println(MSGBENVENUTO);
		String utente= Utility.leggiStringa(MSGLOGIN);
		
		
		// Controllo se utente gi� esistente 
		Boolean esistente =false;
		int numUtente=0;
		for(int i=0; i<elencoUtenti.size();i++){
			if (elencoUtenti.get(i).getNomeUtente().equalsIgnoreCase(utente)){
				esistente =true;
				numUtente=i;
			}	
		}
		// Se non esiste ne creo uno nuovo
		if (!esistente){
			Utente nuovoUtente= new Utente(utente);
			elencoUtenti.add(nuovoUtente);
			numUtente=elencoUtenti.size()-1;
		}
		ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
	
		// Creazione delle categorie di cui possono essere i vari eventi
		Partita partita= new Partita();
		categorie.add(partita);
		
		
		Menu myMenu= new Menu(NOMEMENU,OPZIONI);
		int scelta;
		
		
		do{
			scelta=myMenu.scegli();
			
			// Controlli su eventi in Bacheca e generazione di eventuali messaggi
			
			ArrayList<Messaggio> messaggiStato = new ArrayList<>(bacheca.controlloEventi());
			for(int i=0;i<messaggiStato.size();i++){
				for(int j=0; j<elencoUtenti.size();j++){
					if(messaggiStato.get(i).getDestinatario().getNomeUtente().equalsIgnoreCase(elencoUtenti.get(j).getNomeUtente())){
						elencoUtenti.get(j).getMessaggiUtente().add(messaggiStato.get(i));
					}
				}
			}
			
			ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
			
			
			// Eliminazione eventi Falliti, Chiusi, Conclusi
			for(int i=0; i< bacheca.getElencoEventi().size();i++){
				if(!bacheca.getElencoEventi().get(i).getStato().equalsIgnoreCase("Aperta") ){
					bacheca.getElencoEventi().remove(i);
				}
			}
			
			ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
			
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
				
				
				// Elnco delle categorie a cui pu� appartenere l'evento
				for(int i=0; i<categorie.size();i++){
					System.out.println(i+1+")");
					System.out.println(NOME + categorie.get(i).getNome());
					System.out.println(DESCRIZIONE + categorie.get(i).getDescrizione()+"\n");
				}
				
				int numCatEvento=Utility.leggiIntero(1, categorie.size()+1, SCELTACATEGORIAEVENTO);
				
				// Richiesta inserimento dettaglio eventi
				
				Evento evento= new Evento(partita,elencoUtenti.get(numUtente));
				evento.inserisciDettagliEvento();
				
				evento.getElencoIscritti().add(elencoUtenti.get(numUtente));
				elencoUtenti.get(numUtente).getEventiUtente().add(evento);
				System.out.println(MSGEVENTO);
				
				
				// Salvataggio file
				ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
				
				
			
				break;
				
				
				
				
			case 3:
				// Visualizza i miei eventi non pubblicati
				if(elencoUtenti.get(numUtente).getEventiUtente().size()!=0){
					for(int i=0; i<elencoUtenti.get(numUtente).getEventiUtente().size();i++){
						System.out.println(i+1 +")");
						if (elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getTitolo().getValore().getInserito()){
							System.out.println(NOMEEVENTO + elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getTitolo().getValore().getValore());	
						}
						else {
							System.out.println(NOMEEVENTO + "Titolo non ancora inserito");
						}
						System.out.println(NOME + elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getNome());
					}
				}else {
					System.out.println(EVENTIVUOTI);
				}
				break;
			case 4:
				// Pubblica eventi 
				
				// Visualizza i propri eventi non ancora inseriti
				if(elencoUtenti.get(numUtente).getEventiUtente().size()!=0){
						System.out.println("0) Esci");	
						for(int i=0; i<elencoUtenti.get(numUtente).getEventiUtente().size();i++){
								System.out.println(i +1 +")");
								if (elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getTitolo().getValore().getInserito()){
									System.out.println(NOMEEVENTO + elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getTitolo().getValore().getValore() );	
								}
								else {
									System.out.println(NOMEEVENTO + "Titolo non ancora inserito");
								}
								System.out.println(NOME + elencoUtenti.get(numUtente).getEventiUtente().get(i).getCategoria().getNome());
						}
											
											// Scelta evento da pubblicare
						int numEventoPubblicato=Utility.leggiIntero(0, elencoUtenti.get(numUtente).getEventiUtente().size(), SCELTAEVENTOPUBBLICAZIONE);
											
											
						if(numEventoPubblicato!=0){
											
							Evento eventop = elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1);
											
							
								eventop.isValido();
								// Controllo validita evento
									if(eventop.getvalidita() == true){
										if(eventop.controlloDate()){
											System.out.println(VALIDITAPUBBLICAZIONE);
															
											// Pubblicazione evento
											bacheca.getElencoEventi().add(eventop);
											elencoUtenti.get(numUtente).getEventiUtente().remove(numEventoPubblicato-1);
										}
										else{
											elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getData().getValore().removeValore();
											elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getDataFine().getValore().removeValore();
											elencoUtenti.get(numUtente).getEventiUtente().get(numEventoPubblicato -1).getCategoria().getTermineIscrizione().getValore().removeValore();													
											System.out.println(MSGPROBDATE);
											}
									}
									else{
															
									    // Evento non valido
										System.out.println(NONVALIDITAPUBBLICAZIONE);
															
										// Possibilit� di inserire altri dettagli all'evento
										int inserimento= Utility.leggiIntero(0,1, "Vuoi inserire completare l'evento? Digita 1 per SI e 0 pre NO");
										if (inserimento==1){
											eventop.inserisciDettagliEvento();
										}					
								}
							}
							
						
				}
				else {
					System.out.println(EVENTIVUOTI);
				}
				
				ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
				ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
				break;
			case 5:
				// Visualizza Bacheca
				
				if(bacheca.getElencoEventi().size() != 0){
					for(int i=0; i<bacheca.getElencoEventi().size();i++){
						System.out.println(i+1 +")");
						System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
						System.out.println(NOME + bacheca.getElencoEventi().get(i).getCategoria().getNome());
						System.out.println(STATO + bacheca.getElencoEventi().get(i).getStato());
						System.out.println(POSTILIBERI + bacheca.getElencoEventi().get(i).getPostiLiberi());
						}
					
				}else{
					System.out.println(BACHECAVUOTA);
				}	
				break;
				
			case 6:
				// Partecipa a evento
				
				if(bacheca.getElencoEventi().size() != 0){
					// Visualizzazione eventi presenti in bacheca
					
					System.out.println("0) Esci");
					for(int i=0; i<bacheca.getElencoEventi().size();i++){
						
						if(!bacheca.getElencoEventi().get(i).giaIscritto(elencoUtenti.get(numUtente))&& bacheca.getElencoEventi().get(i).getStato().equalsIgnoreCase("Aperta")){
							System.out.println(i+1 +")");
							System.out.println(NOMEEVENTO + bacheca.getElencoEventi().get(i).getCategoria().getTitolo().getValore().getValore());
							System.out.println(NOME + bacheca.getElencoEventi().get(i).getCategoria().getNome());
							System.out.println(POSTILIBERI + bacheca.getElencoEventi().get(i).getPostiLiberi());
						}
					
					}
					
					// Scelta eventi
					int numIscEvento=Utility.leggiIntero(0, bacheca.getElencoEventi().size() +1, SCELTAISCEVENTO);
					
					if (numIscEvento!=0){
						bacheca.getElencoEventi().get(numIscEvento-1).getElencoIscritti().add(elencoUtenti.get(numUtente));
					}
					
				}else{
					System.out.println(BACHECAVUOTA);
				}	
				
				
				
				ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
				ServizioFile.salvaSingoloOggetto(filebacheca, bacheca);
				
			
				break;
				
			case 7:
				// Visualiza i miei messaggi
				Menu Menumsg= new Menu(NOMEMENUMSG,OPZIONIMSG);
				int sceltamsg;
				
					do{
						sceltamsg=Menumsg.scegli();
						
						switch(sceltamsg)
						{
							case 0:
								break;
								
							case 1:
								// Visualzza i miei messaggi
								
								if(elencoUtenti.get(numUtente).getMessaggiUtente().size()!=0){
									
									for(int i=0; i<elencoUtenti.get(numUtente).getMessaggiUtente().size();i++){	
										System.out.println(i+1 +")");
										System.out.println(elencoUtenti.get(numUtente).getMessaggiUtente().get(i).getTesto());
									}
								}else {
									System.out.println(MESSAGGIVUOTI);
								}
								break;
							
							case 2:
								// Eliminazione messaggi
								
								// Visualizzazione dei miei Messagi
								if(elencoUtenti.get(numUtente).getMessaggiUtente().size()!=0){
									System.out.println("0) Esci");
									for(int i=0; i<elencoUtenti.get(numUtente).getMessaggiUtente().size();i++){
										System.out.println(i+1 +")");
										System.out.println(elencoUtenti.get(numUtente).getMessaggiUtente().get(i).getTesto());
									}
									
									// Scelta messaggio da eliminare
									int numMsg=Utility.leggiIntero(0, elencoUtenti.get(numUtente).getMessaggiUtente().size(), SCELTAMSG);
									
									
									if(numMsg!=0){
										elencoUtenti.get(numUtente).getMessaggiUtente().remove(numMsg-1);
											
										ServizioFile.salvaSingoloOggetto(fileutenti, elencoUtenti);
									}
								}else {
									System.out.println(MESSAGGIVUOTI);
								}
								break;
						}
					}while(sceltamsg !=0);
				
				break;

			}
		}while(scelta!=0);
		
	}
	
	
	
}
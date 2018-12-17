
import java.io.*;
import java.util.ArrayList;

import MyLib.Menù;
import MyLib.Utility;


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
		final String NOMEEVENTO="Nome evento: ";
		
		
		
		System.out.println(MSGBENVENUTO);
		String utente= Utility.leggiStringa(MSGLOGIN);
	
		
		
		ArrayList<Categoria> categorie=new ArrayList<>();
		ArrayList<Evento> eventi=new ArrayList<>();
		
			
		
		
		Partita partita= new Partita();
		categorie.add(partita);
		
		
		Menù myMenu= new Menù(NOMEMENU,OPZIONI);
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
				
				eventi.add(evento);
				
				
			
				break;
				
				
				
				
			case 3:
				// Visualizza i miei eventi
				for(int i=0; i<eventi.size();i++){
					if(eventi.get(i).getCreatore().equals(utente)){
						
					
					System.out.println(i+1+")");
					if (eventi.get(i).getCategoria().getTitolo().getValore().getInserito()){
						System.out.println(NOMEEVENTO + eventi.get(i).getCategoria().getTitolo());	
					}
					System.out.println(NOMEEVENTO + "Titolo non ancora inserito");
					System.out.println(NOME + eventi.get(i).getCategoria().getNome());
					}
				}
				
				break;
			case 4:
				break;
			case 5:
				break;

			}
		}while(scelta!=0);
		
	}
	
	
	
}
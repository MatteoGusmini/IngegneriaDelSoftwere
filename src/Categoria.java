import java.util.ArrayList;


import java.util.ArrayList;


public class Categoria {
	
	//Costanti
	public static final int STRING = 0;
	public static final int INT = 1;
	public static final int DATE = 2;
	
	
	
	
	private String nome;
	private String descrizione;
	private Campo titolo=new Campo("Titolo","Consiste in un nome di fantasia attribuito all’evento",false,STRING);
	private Campo nPartecipanti=new Campo("Numero di partecipanti"," Stabilisce il numero di persone da coinvolgere nell’evento",true,INT);
	private Campo termineIscrizione=new Campo("Termine ultimo iscrizione","Indica l'ultimo giorno utile per iscriversi all’evento",true,DATE);
	private Campo luogo=new Campo("Luogo","Indica l'indirizzo in cui si svolgerà l’evento oppure il punto di ritrovo",true,STRING);
	private Campo ora=new Campo("Ora","Indica l'ora in cui i partecipanti dovranno recarisi nel luogo prestabilito",true,INT);
	private Campo durata=new Campo("Durata","Indica la durata in termini di numero(approssimativo)di ore e minuti, per gli eventi che si esauriscono in un sol giorno, o in termini di numero esatto di giorni, per gli eventi che occupano più giorni consecutivi",false,INT);
	private Campo quotaIndividuale=new Campo("Quota individuale"," indica la spesa (o una stima della stessa) che ogni partecipante all’iniziativa dovrà sostenere (si noti che la spesa può anche essere nulla)",true,INT);
	private Campo compresoQuota=new Campo("Compreso nella quota"," indica tutte le voci di spesa comprese nell’ammontare indicato nella “Quota individuale”",false,STRING);
	private Campo dataFine=new Campo("Data conclusiva"," Indica la data di fine dell’evento",false,DATE);
	private Campo oraFine=new Campo("Ora conclusiva"," Indica l'ora di fine dell’evento",false,INT);
	private Campo note=new Campo("Note"," Contiene informazioni aggiuntive circa l'evento",false,STRING);
	private ArrayList<Campo> elencoCampi = new ArrayList<>();
	
	
	//Costruttori
	public Categoria(String _nome, String _descrizione){
		nome=_nome;
		descrizione= _descrizione;
		elencoCampi.add(titolo);
		elencoCampi.add(nPartecipanti);
		elencoCampi.add(termineIscrizione);
		elencoCampi.add(luogo);
		elencoCampi.add(ora);
		elencoCampi.add(durata);
		elencoCampi.add(quotaIndividuale);
		elencoCampi.add(compresoQuota);
		elencoCampi.add(dataFine);
		elencoCampi.add(oraFine);
		elencoCampi.add(note);
	}

	//Metodi
	
	
	
	//Metodo che permette di inserire i valori ai campi comuni a tutte le categorie
	public void inserisciCampi()throws Exception{
		for (int i=0; i< elencoCampi.size(); i++){
			elencoCampi.get(i).inserisciValore();
			
		}
	}
	
	
	// Visualizza tutti i campi di una categoria
	public void visualizzaCampi(){
		
		for (int i=0; i< elencoCampi.size(); i++){
			
			System.out.println(elencoCampi.get(i).visualizzaCampo());
		}
		
	}
	
	// Getters and Setters generati automaticamente
	public String getNome() {
		return nome;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public Campo getTitolo() {
		return titolo;
	}


	public Campo getnPartecipanti() {
		return nPartecipanti;
	}


	public Campo getTermineIscrizione() {
		return termineIscrizione;
	}


	public Campo getLuogo() {
		return luogo;
	}


	public Campo getOra() {
		return ora;
	}


	public Campo getDurata() {
		return durata;
	}


	public Campo getQuotaIndividuale() {
		return quotaIndividuale;
	}


	public Campo getCompresoQuota() {
		return compresoQuota;
	}


	public Campo getDataFine() {
		return dataFine;
	}


	public Campo getOraFine() {
		return oraFine;
	}


	public Campo getNote() {
		return note;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public void setTitolo(Campo titolo) {
		this.titolo = titolo;
	}


	public void setnPartecipanti(Campo nPartecipanti) {
		this.nPartecipanti = nPartecipanti;
	}


	public void setTermineIscrizione(Campo termineIscrizione) {
		this.termineIscrizione = termineIscrizione;
	}


	public void setLuogo(Campo luogo) {
		this.luogo = luogo;
	}


	public void setOra(Campo ora) {
		this.ora = ora;
	}


	public void setDurata(Campo durata) {
		this.durata = durata;
	}


	public void setQuotaIndividuale(Campo quotaIndividuale) {
		this.quotaIndividuale = quotaIndividuale;
	}


	public void setCompresoQuota(Campo compresoQuota) {
		this.compresoQuota = compresoQuota;
	}


	public void setDataFine(Campo dataFine) {
		this.dataFine = dataFine;
	}


	public void setOraFine(Campo oraFine) {
		this.oraFine = oraFine;
	}


	public void setNote(Campo note) {
		this.note = note;
	}

	public ArrayList<Campo> getElencoCampi() {
		return elencoCampi;
	}

	public void setElencoCampi(ArrayList<Campo> elencoCampi) {
		this.elencoCampi = elencoCampi;
	}

	
	
}

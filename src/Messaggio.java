import java.io.Serializable;

public class Messaggio implements Serializable{
	//Attributi
		private Utente destinatario;
		private String testo;
		
		
		//Costruttori
		public Messaggio(Utente _destinatario, String _testo){
			destinatario =_destinatario;
			testo= _testo;
			
		}

		
		
		
		// Getters and Setters generati automaticamente
		public Utente getDestinatario() {
			return destinatario;
		}


		public String getTesto() {
			return testo;
		}


		public void setDestinatario(Utente destinatario) {
			this.destinatario = destinatario;
		}


		public void setTesto(String testo) {
			this.testo = testo;
		}
		
		
		
		
		
}

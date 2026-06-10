package bin;

import java.time.LocalDateTime;


public class menuCliente{
	
	//campi
	private Utente utente;
	private Prenotazione prenotazione;
	
	
	//costruttore
	public menuCliente(Utente utente, Prenotazione prenotazione){
		this.utente = utente;
		this.prenotazione = prenotazione;
	}
	
	//metodi
	public Prenotazione visualizzaPrenotazione(){
		return prenotazione;
	}
	
	public void creaPrenotazione(int numeroBiglietto){
		if(numeroBiglietto < 200-numeroBiglietto) //se numero di posti richiesti è minore del numero di posti disponibili
			this.prenotazione = prenotazione;
			else
				System.out.print("il numero dei biglietti eccede il numero di posti disponibili");
	}
	
	//metodo che calcola la data odierna per compararla con la data delle prenotazioni
	public LocalDateTime dataOdierna(){
		return LocalDateTime.now();
	}
	
	public Prenotazione modificaPrenotazione(Proiezione proiezione, LocalDateTime data){
		if((proiezione.getDataOra().isAfter(dataOdierna())) &&  (data.isAfter(dataOdierna()))){
			this.prenotazione = prenotazione;
		}
		return prenotazione;
	}
	
	public void eliminaPrenotazione(Prenotazione prenotazione){
		if(prenotazione.getProiezione().getDataOra().compareTo(dataOdierna())>0)
			this.prenotazione = null;
	}
}
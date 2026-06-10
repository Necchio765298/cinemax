package bin;


 public class menuBigliettaio {
  
  
  //CAMPI
  
  private Prenotazione[] prenotazioni;
  private int numeroPrenotazioni;
  
  
  //COSTRUTTORE
  
  public menuBigliettaio() {
   prenotazioni = new Prenotazione[10]; //l'array memorizza al massimo 10 prenotazioni
   numeroPrenotazioni = 0;
   }
   
  
  //METODI
  
  //aggiunge una prenotazione
  public void aggiungiPrenotazione(Prenotazione p) {
   prenotazioni[numeroPrenotazioni] = p;
   numeroPrenotazioni++;
   }
   
   //visualizza prenotazioni
   public void visualizzaPrenotazioni() {
    for(int i=0; i<numeroPrenotazioni; i++) {
	 System.out.println("ecco le prenotazioni: ");
	 System.out.println(prenotazioni[i]);
	 System.out.println("costo totale: " + prenotazioni[i].getCostoTotale() + "€");
	 }
   }
   
   //modifica numero biglietti
   public void modificaNumeroBiglietti(int i, int nuovoNumero) {
    prenotazioni[i].setNumeroBiglietto(nuovoNumero);
	}

	//cerca prenotazione
	public Prenotazione cercaPrenotazione(String codice) {
		for(int i=0; i<numeroPrenotazioni; i++) {
			if(prenotazioni[i].getCodice().equals(codice)) {
				return prenotazioni[i];
			}
		}
		return null;
	}
}

package bin;

 public class menuProiezionista {
  
  
  //CAMPI
 
 private Proiezione[] proiezioni;
 private int numeroProiezioni;
  
  
  //COSTRUTTORE
  
  public menuProiezionista() {
   this.proiezioni = new Proiezione[10]; //l'array memorizza al massimo 10 proiezioni
   this.numeroProiezioni = 0;
   }
   
   
   //METODI
   
   //aggiunge una proiezione
   public void aggiungiProiezione(Proiezione p) {
    proiezioni[numeroProiezioni] = p;
	numeroProiezioni++;
	}
	
   //visualizza tutte le proiezioni
   public void visualizzaProiezioni() {
    for(int i=0; i<numeroProiezioni; i++) {
	 System.out.println("ecco l'elenco delle proiezioni: ");
	 System.out.println(proiezioni[i]);
	 }
   }
   
   //modifica il prezzo di una proiezione
   public void modificaPrezzo(int i, double nuovoPrezzo) {
    proiezioni[i].setPrezzoBiglietto(nuovoPrezzo);
	}

   //modifica una proiezione
   public void modificaProiezione(int i, Proiezione nuovaProiezione) {
	   proiezioni[i] = nuovaProiezione;
   }

	//elimina una proiezione
	 public void eliminaProiezione(int i) {
		 for(int j = i; j<numeroProiezioni-1; j++) {
			 proiezioni[j] = proiezioni[j+1];
		 }
		 proiezioni[numeroProiezioni-1] = null;
		 numeroProiezioni--;
	 }
  }
   

package bin;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

 public class menuProiezionista {
  //CAMPI
 
 private Proiezione[] proiezioni;
 private int numeroProiezioni;

  //COSTRUTTORE
  public menuProiezionista() {
   this.proiezioni = new Proiezione[10]; //l'array memorizza al massimo 10 proiezioni
   this.numeroProiezioni = 0;
   }
   
   //data_ora_proiezione, titolo_film, genere, regista, anno, durata_minuti, eta_minima, prezzo_biglietto
   
   //METODI
   //aggiunge una proiezione
   public void aggiungiProiezione(Proiezione p) throws IOException{		
	    FileWriter fwt = new FileWriter("proiezioni.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		while(brd.readLine() != null){
			brd.readLine();
	//aggiunge proiezione se non si accavalla con una già esistente
			public boolean siAccavalla(Proiezione p) {
				for(int i=0; i<numeroProiezioni; i++) {
				
			if(siAccavalla(p)) {
				System.out.println("la proiezione si accavalla");
			}
		}
		bwt.write(p.toString());
		bwt.newLine();
		bwt.close();
		fwt.close();
	}
	/*
   //visualizza tutte le proiezioni
   public void visualizzaProiezioni() {
    for(int i=0; i<numeroProiezioni; i++) {
	 System.out.println("ecco l'elenco delle proiezioni: ");
	 System.out.println(proiezioni[i]);
	 }
   }
   
   /*
   //modifica il prezzo di una proiezione
   public void modificaPrezzo(int i, double nuovoPrezzo) {
    proiezioni[i].setPrezzoBiglietto(nuovoPrezzo);
	}
	*/
	
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
   

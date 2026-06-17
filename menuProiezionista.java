//package bin;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

 public class menuProiezionista {
  //CAMPI
 
 private Proiezione proiezione;
 

  //COSTRUTTORE
public menuProiezionista() {
	this.proiezione = proiezione;
}
   
   
   //METODI
   //aggiunge una proiezione
   public static void aggiungiProiezione(Proiezione p) throws IOException{		
	    FileWriter fwt = new FileWriter("proiezioni.csv", true);
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String proiezione;
		while((proiezione = brd.readLine()) != null){
			if (proiezione.contains(p.toString()))
				System.out.println("la proiezione inserita si accavalla con una già esistente");
		}
			bwt.newLine();
			bwt.write(p.toString());
		
		bwt.close();
		fwt.close();
	}

	 //modifica con gli stream
	public static void modificaProiezione(Proiezione vecchiaProiezione, Proiezione nuovaProiezione) throws IOException{	
		FileWriter fwt = new FileWriter("proiezioni.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String proiezione;
		while((proiezione = brd.readLine()) != null) {
			if(proiezione.contains(vecchiaProiezione.toString())) {
				bwt.write(nuovaProiezione.toString());
			}
			
		}
	    bwt.close();
		fwt.close();
		brd.close();
		frd.close();
	}

	 //elimina con gli stream
	public static void eliminaProiezione(Proiezione proiezioneDaEliminare) throws IOException {
		FileWriter fwt = new FileWriter("proiezioni.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String proiezione;
		while((proiezione = brd.readLine()) != null ) {
			if(proiezione == proiezioneDaEliminare.toString())
				bwt.write(" ");
		}
		
		bwt.close();
		fwt.close(); 
		brd.close();
		frd.close();
	}

}
   

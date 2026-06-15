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
	    FileWriter fwt = new FileWriter("proiezioni.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		while(brd.readLine() != null){
			brd.readLine();
			if (brd.readLine().contains(p.toString()))
				System.out.println("la proiezione inserita si accavalla con una già esistente");
			else
				bwt.write(p.toString());
		}
		bwt.close();
		fwt.close();
	}

	 //modifica con gli stream
	public static void modificaProiezione(Proiezione vecchiaProiezione, Proiezione nuovaProiezione) throws IOException{	
		FileWriter fwt = new FileWriter("proiezioni.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		while((brd.readLine()) != null) {
			if(brd.readLine().contains(vecchiaProiezione.toString())) {
				bwt.write(nuovaProiezione.toString());
			}
			bwt.newLine();
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
		while((brd.readLine()) != proiezioneDaEliminare.toString()) {
			brd.readLine();
		}
		bwt.write(" ");
		bwt.close();
		fwt.close(); 
		brd.close();
		frd.close();
	}

}
   

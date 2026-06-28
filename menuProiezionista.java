//package bin;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.time.LocalDateTime;


 public class menuProiezionista {
  //CAMPI
 
 private Proiezione proiezione;
 

  //COSTRUTTORE
public menuProiezionista() {
	this.proiezione = proiezione;
}
   
   
   //METODI
   //aggiunge una proiezione
   public static void aggiungiProiezione() throws IOException{		
	    FileWriter fwt = new FileWriter("../data/proiezioni.csv", true);
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		Console cons = System.console();
		System.out.println("Inserire dati del film");
		String titolo = cons.readLine("Titolo: ");
		String genere = cons.readLine("Genere: ");
		String regista = cons.readLine("Regista: ");
		String Anno = cons.readLine("Anno: ");
		int anno = Integer.parseInt(Anno);
		String DurataMinuti = cons.readLine("Durata in minuti: ");
		int durataMinuti = Integer.parseInt(DurataMinuti);
		String EtaMinima = cons.readLine("Età minima: ");
		int etaMinima = Integer.parseInt(EtaMinima);
		Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
		
		System.out.println(" ");
		System.out.println("Inserire dati della proiezione");
		
		String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
		LocalDateTime dataOra = LocalDateTime.parse(DataOra);
		String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
		double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
		Proiezione p = new Proiezione(film, dataOra, prezzoBiglietto);
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
	public static void modificaProiezione() throws IOException{	
		FileWriter fwt = new FileWriter("../data/proiezioni.csv", true);
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		Console cons = System.console();
		System.out.println("Quale proiezione modificare? Inserire i dati della proiezione per ricercarla");
		String titolo = cons.readLine("Titolo: ");
		String genere = cons.readLine("Genere: ");
		String regista = cons.readLine("Regista: ");
		String Anno = cons.readLine("Anno: ");
		int anno = Integer.parseInt(Anno);
		String DurataMinuti = cons.readLine("Durata in minuti: ");
		int durataMinuti = Integer.parseInt(DurataMinuti);
		String EtaMinima = cons.readLine("Età minima: ");
		int etaMinima = Integer.parseInt(EtaMinima);
		Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
		
		String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
		LocalDateTime dataOra = LocalDateTime.parse(DataOra);
		String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
		double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
		Proiezione vecchiaProiezione = new Proiezione(film, dataOra, prezzoBiglietto);
		String vecchia = csvReader.cercaProiezione(vecchiaProiezione.toString());
		do{
			if(vecchia == brd.readLine()){
				String proiez = brd.readLine();
				proiez= " ";
				bwt.write(proiez);
			}
		}while(brd.readLine() != null);
		System.out.println(" ");
		
		System.out.println("Inserire ora i nuovi dati della proiezione");
		titolo = cons.readLine("Titolo: ");
		genere = cons.readLine("Genere: ");
		regista = cons.readLine("Regista: ");
		Anno = cons.readLine("Anno: ");
		anno = Integer.parseInt(Anno);
		DurataMinuti = cons.readLine("Durata in minuti: ");
		durataMinuti = Integer.parseInt(DurataMinuti);
		EtaMinima = cons.readLine("Età minima: ");
		etaMinima = Integer.parseInt(EtaMinima);
		film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
		
		DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
		dataOra = LocalDateTime.parse(DataOra);
		PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
		prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
		Proiezione nuovaProiezione = new Proiezione(film, dataOra, prezzoBiglietto);
		
		String nuova = nuovaProiezione.toString();
		bwt.newLine();
		bwt.write(nuova);
	    bwt.close();
		fwt.close();
		brd.close();
		frd.close();
	}

	 //elimina con gli stream
	public static void eliminaProiezione() throws IOException {
		FileWriter fwt = new FileWriter("../data/proiezioni.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		Console cons = System.console();
		System.out.println("Quale proiezione eliminare? Inserire i dati della proiezione per ricercarla");
		String titolo = cons.readLine("Titolo: ");
		String genere = cons.readLine("Genere: ");
		String regista = cons.readLine("Regista: ");
		String Anno = cons.readLine("Anno: ");
		int anno = Integer.parseInt(Anno);
		String DurataMinuti = cons.readLine("Durata in minuti: ");
		int durataMinuti = Integer.parseInt(DurataMinuti);
		String EtaMinima = cons.readLine("Età minima: ");
		int etaMinima = Integer.parseInt(EtaMinima);
		Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
		
		String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
		LocalDateTime dataOra = LocalDateTime.parse(DataOra);
		
		String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
		double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
		Proiezione proiezioneDaEliminare = new Proiezione(film, dataOra, prezzoBiglietto);
		String proiezionedaeliminare = csvReader.cercaProiezione(proiezioneDaEliminare.toString());
		do{
			if(proiezionedaeliminare == brd.readLine()){
				String proiez = brd.readLine();
				proiez= " ";
				bwt.write(proiez);
			}
		}while(brd.readLine() != null);
		System.out.println(" ");
		
		bwt.close();
		fwt.close(); 
		brd.close();
		frd.close();
	}

}
   

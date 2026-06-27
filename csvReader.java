//package bin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
public class csvReader{
	//campi
	private static String proiezione;
	private Utente utente;
	
	//costruttori
	public csvReader(String proiezione){
		this.proiezione = proiezione;
	}
	
	public csvReader(Utente utente){
		this.utente = utente;
	}
	
	//metodi
	public static String cercaProiezione(String args) throws IOException{
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String proiezione;
		while((proiezione = brd.readLine()) != null){
			if(proiezione.contains(args))
				System.out.println(proiezione);
			
		}
		brd.close();
		frd.close();
		return proiezione;
	}
	
	public  static void cercaProiezione(LocalDate dataInizio, LocalDate dataFine) throws IOException{
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String Date;
		while((Date = brd.readLine()) != null) {
			String Date1 = Date.substring(1,11);
			LocalDate data = LocalDate.parse(Date1);
			if(data.isAfter(dataInizio) && data.isBefore(dataFine))
				System.out.println(Date.toString());
		}
		brd.close();
		frd.close();
	}
	
	/*public String visualizzaProiezione() throws IOException{
		return cercaProiezione(proiezione);
	}
	*/
	
	public static void registraCliente(Utente utente) throws IOException{
		FileWriter fwt = new FileWriter("../data/utenti.csv", true);
		BufferedWriter bwt = new BufferedWriter(fwt);
		
		bwt.write(utente.toString());
		bwt.newLine();
		bwt.close();
		fwt.close();
	}
}

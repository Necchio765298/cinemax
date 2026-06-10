package bin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class csvReader{
	//campi
	private Proiezione proiezione;
	private Utente utente;
	
	//costruttori
	public csvReader(Proiezione proiezione){
		this.proiezione = proiezione;
	}
	
	public csvReader(Utente utente){
		this.utente = utente;
	}
	
	//metodi
	public String cercaProiezione(Object... args) throws IOException{
		FileReader frd = new FileReader("proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		
		while(!(brd.readLine().contains(args.toString()))){
			brd.readLine();
		}
		String proiezione = brd.readLine();
		brd.close();
		frd.close();
		return proiezione;
	}
	
	public String visualizzaProiezione() throws IOException{
		return cercaProiezione(proiezione).toString();
	}
	
	
	public static void registraCliente(Utente utente) throws IOException{
		FileWriter fwt = new FileWriter("utenti.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		while(brd.readLine() != null){
			brd.readLine();
		}
		bwt.write(utente.toString());
		bwt.newLine();
		bwt.close();
		fwt.close();
	}
}

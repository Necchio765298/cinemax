package bin;

import java.time.LocalDateTime;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.util.UUID;

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
	public void visualizzaPrenotazione() throws IOException{
		Console cons = System.console();
		FileWriter fwt = new FileWriter("prenotazioni.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("prenotazioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		
		String nome = cons.readLine("inserire il nome del cliente per cui cercare le prenotazioni associate");
		String cognome = cons.readLine("inserire adesso il cognome");
		while(brd.readLine() != null){
			String prenotazione = new String();
			if(brd.readLine().contains(nome) && brd.readLine().contains(cognome))
				
				prenotazione = brd.readLine();
				System.out.println(prenotazione);
		}
	}
	
	public void creaPrenotazione(int numeroBiglietto) throws IOException{
		if(numeroBiglietto < 200-numeroBiglietto){ //se numero di posti richiesti è minore del numero di posti disponibili
			Console cons = System.console();
			String nome = cons.readLine("inserire il prorio nome");
			String cognome = cons.readLine("inserire il proprio cognome");
			String titolo = cons.readLine("inserire titolo del film");
			String DataOra = cons.readLine("inserire la data nel formato aaaa-mm-gg hh:mm:ss");
			String NumBiglietto = cons.readLine("inserire il numero di biglietti da acquistare");
			String codice = UUID.randomUUID().toString().substring(0,8).toUpperCase(); 

			String prenotazione = nome+"," + cognome+","  + titolo +"," + DataOra+"," +  NumBiglietto +"," + codice;

			FileWriter fwt = new FileWriter("prenotazioni.csv");
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			while(brd.readLine() != null){
				brd.readLine();
			}
			bwt.write(prenotazione);
		}else{
			System.out.print("il numero dei biglietti eccede il numero di posti disponibili");
		}
	}
	
	public static void modificaPrenotazione(Prenotazione prenotazione, LocalDateTime data) throws IOException{
		if((prenotazione.getProiezione().getDataOra().isAfter(LocalDateTime.now())) &&  (data.isAfter(LocalDateTime.now()))){
			FileWriter fwt = new FileWriter("prenotazioni.csv");
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			
			while(brd.readLine() != prenotazione.toString()){
				brd.readLine();
			}
			prenotazione.getProiezione().setDataOra(data);
			bwt.write(prenotazione.toString());
		}else{
			System.out.println("la data vecchia e quella inserita sono antecedenti la data odierna");
		}
	}
	
	public static void eliminaPrenotazione(Prenotazione prenotazione) throws IOException{
		if(prenotazione.getProiezione().getDataOra().compareTo(LocalDateTime.now())>0){
			FileWriter fwt = new FileWriter("prenotazioni.csv");
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			
			while(brd.readLine() != prenotazione.toString()){
				brd.readLine();
			}
			bwt.write(" ");
		}else{
			System.out.println("la data della proiezione non è successiva alla data odierna");
		}
	}
}
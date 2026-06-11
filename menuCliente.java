package bin;

import java.time.LocalDateTime;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

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
		FileWriter fwt = new FileWriter("prenotazioni.csv");
		BufferedWriter bwt = new BufferedWriter(fwt);
		FileReader frd = new FileReader("prenotazioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		
		String nome = System.in("inserire il nome del cliente per cui cercare le prenotazioni associate");
		String cognome = System.in("inserire adesso il cognome");
		while(brd.readLine() != null){
			if(brd.readLine().contains(nome) && brd.readLine().contains(cognome))
				String prenotazione = brd.readLine();
				System.out.println(prenotazione);
		}
	}
	
	public String creaPrenotazione(int numeroBiglietto) throws IOException{
		if(numeroBiglietto < 200-numeroBiglietto) //se numero di posti richiesti è minore del numero di posti disponibili
			String nome = System.in("inserire il prorio nome");
			String cognome = System.in("inserire il proprio cognome");
			String titolo = System.in("inserire titolo del film");
			LocalDateTime dataOra = System.in("inserire la data nel formato aaaa-mm-gg hh:mm:ss");
			int numBiglietto = System.in("inserire il numero di biglietti da acquistare");
			String codice = System.in("inserire codice della prenotazione");
			
			Prenotazione prenotazione = new Prenotazione();
			
			FileWriter fwt = new FileWriter("prenotazioni.csv");
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			while(brd.readLine() != null){
				brd.readLine();
			}
			bwt.write(prenotazione.toString());
		else
			System.out.print("il numero dei biglietti eccede il numero di posti disponibili");
	}
	
	//metodo che calcola la data odierna per compararla con la data delle prenotazioni
	public LocalDateTime dataOdierna(){
		return LocalDateTime.now();
	}
	
	public static void modificaPrenotazione(Prenotazione prenotazione, LocalDateTime data) throws IOException{
		if((prenotazione.getProiezione().getDataOra().isAfter(dataOdierna())) &&  (data.isAfter(dataOdierna())))
			FileWriter fwt = new FileWriter("prenotazioni.csv");
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			
			while(brd.readLine() != prenotazione.toString()){
				brd.readLine();
			}
			prenotazione.getProiezione.setDataOra(data);
			bwt.write(prenotazione.toString());
		else
			System.out.println("la data vecchia e quella inserita sono antecedenti la data odierna");
	}
	
	public static void eliminaPrenotazione(Prenotazione prenotazione) throws IOException{
		if(prenotazione.getProiezione().getDataOra().compareTo(dataOdierna())>0)
			FileWriter fwt = new FileWriter("prenotazioni.csv");
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			
			while(brd.readLine() != prenotazione.toString()){
				brd.readLine();
			}
			bwt.write(null);
		else
			System.out.println("la data della proiezione non è successiva alla data odierna");
	}
}
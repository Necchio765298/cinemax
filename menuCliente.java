//package bin;
import java.lang.Integer;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.io.File;
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
	public static void visualizzaPrenotazione() throws IOException{
		try{
		Console cons = System.console();
		
		FileReader frd = new FileReader("../data/prenotazioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		
		String nome = cons.readLine("inserire il nome del cliente per cui cercare le prenotazioni associate");
		String cognome = cons.readLine("inserire adesso il cognome");
		String prenotazione;
		while((prenotazione = brd.readLine()) != null){
			
			if(prenotazione.contains(nome) && prenotazione.contains(cognome))
				System.out.println(prenotazione);
		}
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("Nome o cognome inseriti non corretti");
		}
	}
	
	public static void creaPrenotazione(int numeroBiglietto) throws IOException{
		try{
		if(numeroBiglietto < 200-numeroBiglietto){ //se numero di posti richiesti è minore del numero di posti disponibili
			Console cons = System.console();
			String nome = cons.readLine("inserire il prorio nome");
			String cognome = cons.readLine("inserire il proprio cognome");
			String titolo = cons.readLine("inserire titolo del film");
			String DataOra = cons.readLine("inserire la data nel formato aaaa-mm-ggThh:mm:ss");
			//String NumBiglietto = cons.readLine("inserire il numero di biglietti da acquistare");
			
			String codice = UUID.randomUUID().toString().substring(0,8).toUpperCase(); 

			String prenotazione = DataOra+"," + nome+","+ cognome+","  + titolo +","  +  numeroBiglietto +"," + codice;

			FileWriter fwt = new FileWriter("../data/prenotazioni.csv", true);
			BufferedWriter bwt = new BufferedWriter(fwt);
			bwt.write(prenotazione);
			bwt.newLine();
			
			System.out.println("la prenotazione è stata registrata");
			bwt.close();
			fwt.close();
		}else{
			System.out.print("il numero dei biglietti eccede il numero di posti disponibili");
		}
		}catch(Exception e){
			System.out.println("Un dato inserito non è valido");
		}
	}
	
	public static void modificaPrenotazione(Prenotazione prenotazioneVecchia, LocalDateTime dataVecchia) throws IOException{
		Console cons = System.console();
		System.out.println("Inserire i dati relativi alla prenotazione che si intende confermare");
		System.out.println("Dati relativi all'utente");
		String nome = cons.readLine("Nome? ");
		String cognome = cons.readLine("Cognome?");
		String username = cons.readLine("Username? ");
		String password = cons.readLine("Password? ");
		String DataNascita = cons.readLine("Data di nascita nel formato AAAA-MM-GG");
		LocalDate dataNascita = LocalDate.parse(DataNascita);
		String domicilio = cons.readLine("Domicilio? ");
		String ruolo = "Cliente";
		Utente utente = new Utente(nome, cognome, username, password, dataNascita, domicilio, ruolo);
		System.out.println(" ");
		System.out.println("Dati relativi alla proiezione");
		
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
		
		String DataNuova = cons.readLine("Data e ora della nuova proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
		LocalDateTime dataNuova = LocalDateTime.parse(DataNuova);
		String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
		double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
		Proiezione proiezione = new Proiezione(film, dataNuova, prezzoBiglietto);
		
		System.out.println(" ");
		String NumeroBiglietto = cons.readLine("Quanti biglietti si desidera acquistare? ");
		int numeroBiglietto = Integer.parseInt(NumeroBiglietto);
		
		System.out.println(" ");
		System.out.println("Modifica prenotazione ");
		Prenotazione prenotazioneNuova = new Prenotazione(utente, proiezione, numeroBiglietto);
		
		try{
		if((dataVecchia.isAfter(LocalDateTime.now())) &&  (dataNuova.isAfter(LocalDateTime.now()))){
			
			File temp = File.createTempFile("pro", "csv");
			File vecchio = new File("../data/prenotazioni.csv");
			FileWriter fwt = new FileWriter("pro.csv", true);
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("../data/prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			
			String Prenotazione = " ";
			while((Prenotazione = brd.readLine()) != null){
				if(!(Prenotazione.equals(prenotazioneVecchia.toString())))
					bwt.write(Prenotazione);
				else
					bwt.write(prenotazioneNuova.toString());
			}
			vecchio.delete();
			temp.renameTo(vecchio);
			brd.close();
			bwt.close();
			frd.close();
			fwt.close();
		}else
			System.out.println("la data vecchia e quella inserita sono antecedenti la data odierna");
		}catch(Exception e){
			System.out.println("Un campo dei precedenti richiesti non è stato compilato correttamente");
		}
	}
	
	public static void eliminaPrenotazione(Prenotazione prenotazione) throws IOException{
		if(prenotazione.getProiezione().getDataOra().compareTo(LocalDateTime.now())>0){
			FileWriter fwt = new FileWriter("../data/prenotazioni.csv");
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("../data/prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			String Prenotazione;
			while((Prenotazione = brd.readLine()) != null){
				if(Prenotazione == prenotazione.toString()){
					Prenotazione = " ";
					bwt.write(Prenotazione);
				}
			}
			brd.close();
			bwt.close();
			frd.close();
			fwt.close();
		}else{
			System.out.println("la data della proiezione non è successiva alla data odierna");
		}
	}
}
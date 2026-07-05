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

/**
 * Gestisce le funzionalità riservate agli utenti con ruolo di cliente. 
 * La classe consente la creazione, la visualizzazione, la modifica e l'eliminazione delle prenotazioni.
 * @author
 */
public class menuCliente{
	
	//campi
	/** Utente che utilizza il menu cliente. */
	private Utente utente;
	/** Prenotazione gestita dal cliente. */
	private Prenotazione prenotazione;
	
	//costruttore
	/**
 * Costruisce un nuovo oggetto menuCliente.
 * @param <utente> utente che utilizza il menu
 * @param <prenotazione> prenotazione associata al cliente
 */
	public menuCliente(Utente utente, Prenotazione prenotazione){
		this.utente = utente;
		this.prenotazione = prenotazione;
	}
	
	//metodi
	/**
 * Visualizza le prenotazioni associate al cliente.
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
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

	/**
 * Crea una nuova prenotazione verificando la disponibilità dei posti nella sala cinematografica.
 * @param <numeroBiglietto> numero di biglietti richiesti
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
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

	/**
 * Modifica una prenotazione esistente. Il metodo richiede di 
 * inserire una nuova prenotazione che vada a sostiuire quella esistente.
 * @param <prenotazione> prenotazione da modificare
 * @param <data> vecchia data e ora della prenotazione
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
	
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
			File file = new File("../data");
			File temp = File.createTempFile("pro", "csv", file);
			File vecchio = new File("../data/prenotazioni.csv");
			FileWriter fwt = new FileWriter(temp, true);
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("../data/prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			
			String Prenotazione = " ";
			while((Prenotazione = brd.readLine()) != null){
				if(!(Prenotazione.equals(prenotazioneVecchia.toString()))){
					bwt.write(Prenotazione);
					bwt.newLine();
				}else{
					bwt.write(prenotazioneNuova.toString());
					bwt.newLine();
				}
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
			

	/**
 * Elimina una prenotazione dal sistema.
 * @param <prenotazione> prenotazione da eliminare
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
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

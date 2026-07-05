//package bin;
import java.lang.Integer;
import java.time.LocalDateTime;
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
 * Costruisce un nuovo oggetto MenuCliente.
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
 * Modifica una prenotazione esistente.
 * @param <prenotazione> prenotazione da modificare
 * @param <data> nuova data e ora della proiezione
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
	public static void modificaPrenotazione(Prenotazione prenotazione, LocalDateTime data) throws IOException{
		if((prenotazione.getProiezione().getDataOra().isAfter(LocalDateTime.now())) &&  (data.isAfter(LocalDateTime.now()))){
			FileWriter fwt = new FileWriter("prenotazioni.csv");
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			
			String Prenotazione;
			while((Prenotazione = brd.readLine()) != null){
				if(Prenotazione == prenotazione.toString()){
					prenotazione.getProiezione().setDataOra(data);
					bwt.write(prenotazione.toString());
				}
			}
			brd.close();
			bwt.close();
			frd.close();
			fwt.close();
		}else
			System.out.println("la data vecchia e quella inserita sono antecedenti la data odierna");
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

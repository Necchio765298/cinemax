package cinemax;
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

/** Gestisce le funzionalità riservate agli utenti con ruolo di cliente. 
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
	/** Costruisce un nuovo oggetto menuCliente.
 * @param <utente> utente che utilizza il menu
 * @param <prenotazione> prenotazione associata al cliente
 */
	public menuCliente(Utente utente, Prenotazione prenotazione){
		this.utente = utente;
		this.prenotazione = prenotazione;
	}
	
	//metodi
	/** Visualizza le prenotazioni associate al cliente.
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	public static void visualizzaPrenotazione(long id) throws IOException{
		try{
			Prenotazione p = Prenotazione.cercaPrenotazione(id);
			String prenotazione = p.toString();
			
			brd.close();
			frd.close();
			System.out.println("Prenotazione trovata: "+ prenotazione);
		}catch(Exception e){
			e.getMessage();
		}
	}

	/** Crea una nuova prenotazione verificando la disponibilità dei posti nella sala cinematografica.
 * @param <numeroBiglietto> numero di biglietti richiesti
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
	public static Prenotazione creaPrenotazione(LocalDateTime orario) throws IOException{
		try{
			Console cons = System.console();
			String id = cons.readLine("inserire il proprio codice identificativo");
			long identificativo =(long) Integer.parseInt(id);
			Utente u = Utente.getUtente(identificativo);
			Proiezione p = Proiezione.getProiezione(orario);
			String biglietti = cons.readLine("Inserire il numero dei biglietti da acquistare: ")
			int numeroBiglietto = Integer.parseInt(biglietti);
			if(numeroBiglietto < 200-numeroBiglietto){ //se numero di posti richiesti è minore del numero di posti disponibili	
				Prenotazione prenotazione =  new Prenotazione(u, p, numeroBiglietto);
				Prenotazione.registraPrenotazione(prenotazione);
				return prenotazione;
			}else{
				System.out.print("il numero dei biglietti eccede il numero di posti disponibili");
			}
		}catch(Exception e){
			System.out.println("Un dato inserito non è valido");
		}
	}

	/** Modifica una prenotazione esistente. Il metodo richiede di 
 * inserire una nuova prenotazione che vada a sostiuire quella esistente.
 * @param <prenotazione> prenotazione da modificare
 * @param <data> vecchia data e ora della prenotazione
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
	
	public static void modificaPrenotazione(LocalDateTime dataVecchia, LocalDateTime dataNuova) throws IOException{
		Prenotazione preVecchia = Prenotazione.cercaPrenotazione(dataVecchia);
		
		Prenotazione preNuova = creaPrenotazione(dataNuova);
		
		try{
		if((dataVecchia.isAfter(LocalDateTime.now())) &&  (dataNuova.isAfter(LocalDateTime.now()))){
			File file = new File("../data");
			File temp = File.createTempFile("pro", ".csv", file);
			File vecchio = new File("../data/prenotazioni.csv");
			try{
			FileWriter fwt = new FileWriter(temp, true);
			BufferedWriter bwt = new BufferedWriter(fwt);
			FileReader frd = new FileReader("../data/prenotazioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			
			String Prenotazione = " ";
			while((Prenotazione = brd.readLine()) != null){
				if(!((preVecchia.toString()).equals(preNuova.toString()))){
					bwt.write(preVecchia.toString());
				}else{
					bwt.write(preNuova.toString());
				}
				bwt.newLine();
			}
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
			brd.close();
			bwt.close();
			frd.close();
			fwt.close();
			vecchio.delete();
			temp.renameTo(vecchio);
		}else
			System.out.println("la data vecchia e quella inserita sono antecedenti la data odierna");
		}catch(Exception e){
			System.out.println("Un campo dei precedenti richiesti non è stato compilato correttamente");
		}
	}
			

	/** Elimina una prenotazione dal sistema.
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

package cinemax;
import java.lang.Integer;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.util.UUID;
import java.util.ArrayList;

/** Gestisce le funzionalità riservate agli utenti con ruolo di cliente 
 * La classe consente la creazione, la visualizzazione, la modifica e l'eliminazione delle prenotazioni
 * @author Arianna Necchio
 * @author Gaia Galimberti
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

	/** Visualizza le prenotazioni associate all'utente specificato.
 * @param <id> identificativo dell'utente
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
 
	public static void visualizzaPrenotazione(ArrayList<Prenotazione> memo) throws IOException{
		try{
			System.out.println("Prenotazioni trovate: ");
			for(Prenotazione pre : memo)
					System.out.println(pre.toStringEsistente());
		}catch(Exception e){
			e.getMessage();
		}
	}

	/** Crea una nuova prenotazione per la proiezione prevista nell'orario specificato se il 
 * numero di posti richiesti è minore del numero di posti disponibili.
 * @param <orario> data e ora della proiezione
 * @return la prenotazione creata
 * @throws <IOExceptio> se si verifica un errore durante la gestione del file
 */
	public static Prenotazione creaPrenotazione(LocalDateTime orario, Utente u) throws IOException{
		try{
			Console cons = System.console();
			System.out.println("Utente: "+ u.toStringEsistente());
			Proiezione p = Proiezione.getProiezione(orario);
			System.out.println("Proiezione: " + p.toString());
			System.out.println("");
			String biglietti = cons.readLine("Inserire il numero dei biglietti da acquistare: ");
			int numeroBiglietto = Integer.parseInt(biglietti);
			Prenotazione prenotazione =  new Prenotazione(u, p, numeroBiglietto);
			System.out.println("Prenotazione: "+prenotazione.toString());
			System.out.println("");
			if(numeroBiglietto < 200-Prenotazione.getTotaleBiglietti(orario)){	
				Prenotazione.registraPrenotazione(prenotazione);
				System.out.println("Il codice della prenotazione è "+ prenotazione.getCodice());
				return prenotazione;
			}else{
				System.out.print("il numero dei biglietti eccede il numero di posti disponibili");
			}
			System.out.println("");
		}catch(Exception e){
			System.out.println("Un dato inserito non è valido" + e.getMessage());
		}
		return null;
	}

	/** Modifica la data e l'ora di una prenotazione esistente
 * @param <dataVecchia> data e ora della proiezione originaria
 * @param <dataNuova> nuova data e ora della proiezione
 * @param <codice> codice identificativo della prenotazione
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura del file
 */
	
	public static void modificaPrenotazione(LocalDateTime dataVecchia, LocalDateTime dataNuova, Utente u) throws IOException{	
		try{
			Console cons = System.console();
			if((dataVecchia.isAfter(LocalDateTime.now())) &&  (dataNuova.isAfter(LocalDateTime.now()))){
				File file = new File("data");
				File temp = File.createTempFile("pre", ".csv", file);
				File vecchio = new File("data/prenotazioni.csv");
				
					FileWriter fwt = new FileWriter(temp, true);
					BufferedWriter bwt = new BufferedWriter(fwt);
					FileReader frd = new FileReader("data/prenotazioni.csv");
					BufferedReader brd = new BufferedReader(frd);
				try{	
					ArrayList<Prenotazione> preVecchia = menuBigliettaio.cercaPrenotazione(dataVecchia, 0, "", "", "", 0, "");
					
					Prenotazione preOttenuta= preVecchia.get(0);
					System.out.println(" ");
					Prenotazione preNuova = menuCliente.creaPrenotazione(dataNuova, u);
					
					String linea;
					while((linea = brd.readLine())!= null){
						
						String[] dati = linea.split(",");
						String codice = dati[6];
						int numBiglietti = Integer.parseInt(dati[5]);
						Proiezione p = Proiezione.getProiezione(dataVecchia);
						Prenotazione daLinea = new Prenotazione(codice, u, p, numBiglietti);
						
						if(!(preOttenuta).equals(daLinea)){
							bwt.write(daLinea.toStringEsistente());
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
				System.out.println("La data vecchia o quella nuova non sono successive alla data odierna, pertanto non è possibile modificare la prenotazione");
		}catch(Exception e){
			e.getMessage();
		}	
	}
			

	/** Elimina una prenotazione dal sistema
 * @param <dataVecchia> data e ora della proiezione della prenotazione da eliminare
 * @param <codice> codice identificativo della prenotazione
 * @throws <IOException> se si verifica un errore durante la gestione del file
 */
	public static void eliminaPrenotazione(LocalDateTime dataVecchia, String codice) throws IOException{
		try{
				Console cons = System.console();
			if(dataVecchia.compareTo(LocalDateTime.now())>0){
				File file = new File("data");
				File temp = File.createTempFile("pre", ".csv", file);
				File vecchio = new File("data/prenotazioni.csv");
				
					FileWriter fwt = new FileWriter(temp, true);
					BufferedWriter bwt = new BufferedWriter(fwt);
					FileReader frd = new FileReader("data/prenotazioni.csv");
					BufferedReader brd = new BufferedReader(frd);
				try{	
					ArrayList<Prenotazione> preDelete = menuBigliettaio.cercaPrenotazione(null, 0, "", "", "", 0, codice);
					System.out.println("Digitare il numero relativo alla prenotazione da eliminare tra queele ricercate");
					for(Prenotazione pre : preDelete){
						int i=0;
						System.out.println(i++ +pre.toString());
					}
					String Numero = cons.readLine("Scelta numero prenotazione: ");
					int numero = Integer.parseInt(Numero);
					Prenotazione preOttenuta= preDelete.get(numero);
					String linea;
					while((linea = brd.readLine())!= null){
						String[] dati= linea.split(",");
						Proiezione p = Proiezione.getProiezione(LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
						Utente u = Utente.getUtente(Long.parseLong(dati[1]));
						Prenotazione preAltra = new Prenotazione(dati[6], u, p, Integer.parseInt(dati[5]));
						
						if(!(preOttenuta).equals(preAltra)){
							bwt.write(preOttenuta.toString());
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
				System.out.println("La data della prenotazione è antecedente la data odierna, pertanto non è stato possibile eliminarla");
		}catch(Exception e){
			e.getMessage();
		}	
	}
	
}

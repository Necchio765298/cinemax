package cinemax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.Math;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/** Rappresenta un utente registrato all'interno dell'applicazione.
 * La classe memorizza le informazioni anagrafiche, le credenziali di accesso e il ruolo associato all'utente.
 * @author Arianna Necchio
 * @author Gaia Galimberti
 */
public class Utente {
/** Codice identificativo dell'utente */
private long ID;
/** Nome dell'utente. */
private String nome;
/** Cognome dell'utente. */
private String cognome;
/** Username utilizzato per l'accesso. */
private String username;
/** Password dell'utente. */
private String password;
/** Data di nascita dell'utente. */
private LocalDate dataNascita;
/** Domicilio dell'utente. */
private String domicilio;
/** Ruolo dell'utente all'interno dell'applicazione. */
private String ruolo;

/** Costruisce un nuovo utente.
 * @param <ID> identificativo dell'utente
 * @param <nome> nome dell'utente
 * @param <cognome> cognome dell'utente
 * @param <username> username dell'utente
 * @param <password> password dell'utente
 * @param <dataNascita> data di nascita dell'utente
 * @param <domicilio> domicilio dell'utente
 * @param <ruolo> ruolo dell'utente
 */
    public Utente(String nome, String cognome, String username, String password, LocalDate dataNascita, String domicilio, String ruolo){
        this.ID= (long) Math.random();
		this.nome=nome;
        this.cognome = cognome ;
        this.username = username ;
        this.password=password;
        this.dataNascita=dataNascita;
        this.domicilio=domicilio;
        this.ruolo=ruolo;
    }
	
	/** Il metodo consente di ottenere un oggetto di tipo Utente specificandone il codice identificativo; se l'utente non viene trovato, il metodo solleva un'eccezione opportuna.
	* Il metodo è utile per creare un parametro formale di tipo Utente fornito in argomento al costruttore della classe Prenotazione.
	* @param <id> identificativo dell'utente
	* @return l'utente
	* @throws <UtenteNonEsistenteException> se l'utente non è stato ancora registrato
	* @throws <IOException> se si verifica un errore durante la scrittura del file
	*/
	public static Utente getUtente(long id) throws UtenteNonEsistenteException, IOException{
		
		FileReader frd = new FileReader("../data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String riga;
		while ((riga = brd.readLine()) != null) {
			String[] dati = riga.split(",");
			if(((long)Integer.parseInt(dati[0]))==id){
				return new Utente(dati[1], dati[2], dati[3], dati[4], LocalDate.parse(dati[5], DateTimeFormatter.ofPattern("yyyy-MM-dd")), dati[6], dati[7]);
			}
		}
		throw new UtenteNonEsistenteException(id);
	}
	/** Registra un nuovo utente nel file csv.
	 * @param <utente> utente da registrare
	 * @throws <IOException> se si verifica un errore durante la scrittura del file
	 */
	public static void registraUtente(Utente utente) throws IOException{
		try{
			FileWriter fwt = new FileWriter("../data/utenti.csv", true);
			BufferedWriter bwt = new BufferedWriter(fwt);
			
			bwt.write(utente.toString());
			bwt.newLine();
			bwt.close();
			fwt.close();
		}catch(Exception e){
			System.out.println("Utente non opportunamente registrato");
		}
	}

	/** Restituisce il codice identificativo dell'utente.
    * @return il codice identificativo dell'utente
    */
	public long getID(){
		return ID;
	}
	
/** Restituisce il nome dell'utente.
 * @return il nome dell'utente
 */
    public String getNome() {
        return nome;
    }


/** Restituisce il cognome dell'utente.
 * @return il cognome dell'utente
 */
    public String getCognome() {
        return cognome;
    }


/** Restituisce lo username dell'utente.
 * @return lo username dell'utente
 */
    public String getUsername() {
        return username;
    }


/** Restituisce la password dell'utente.
 * @return la password dell'utente
 */
    private String getPassword() {
        return password;
    }


/** Restituisce la data di nascita dell'utente.
 * @return la data di nascita dell'utente
 */
    public LocalDate getDataNascita() {
        return dataNascita;
    }


/** Restituisce il domicilio dell'utente.
 * @return il domicilio dell'utente
 */
    public String getDomicilio() {
        return domicilio;
    }


/** Restituisce il ruolo associato all'utente.
 * @return il ruolo dell'utente
 */
    public String getRuolo() {
        return ruolo;
    }

	/** Restituisce una rappresentazione testuale dell'oggetto Utente, utilizzata per la visualizzazione delle informazioni e per la memorizzazione dei dati nel file csv.
 * @return una stringa contenente i dati dell'utente
 */
    @Override
    public String toString(){
		return getID()+","+nome + ","+ cognome + ","+ username+"," + password +"," + dataNascita +"," +domicilio +"," +ruolo;
    }
}

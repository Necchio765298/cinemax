//Necchio Arianna, matricola: 765298, sede: Como

package cinemax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**La classe fornisce la rappresentazione di un oggetto di tipo <code>Utente</code> registrato all'applicazione.
 * La classe memorizza le informazioni anagrafiche (nome, cognome, data di nascita, domicilio), le credenziali di accesso 
 * e il ruolo associato all'utente (cliente, bigliettaio o proiezionista).
 * @author Arianna Necchio
 * @version 2.1
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
/** Password cifrata. */
private String password;
/** Data di nascita. */
private LocalDate dataNascita;
/** Domicilio. */
private String domicilio;
/** Ruolo all'interno dell'applicazione. */
private String ruolo;

/** Costruisce un nuovo oggetto di tipo <code>Utente</code> da memorizzare nel file Utenti.csv.
 * @param ID codice identificativo generato secondo una numerazione progressiva
 * @param nome nome
 * @param cognome cognome
 * @param username username
 * @param password password
 * @param dataNascita data di nascita
 * @param domicilio domicilio
 * @param ruolo ruolo
 */
    public Utente(String nome, String cognome, String username, String password, LocalDate dataNascita, String domicilio, String ruolo) throws IOException{
        
		this.ID= Utente.generaID();
		System.out.println("Il tuo ID è: " + ID);
		this.nome=nome;
        this.cognome = cognome;
        this.username = username;
        this.password=Utente.passwordHash(password);
        this.dataNascita=dataNascita;
        this.domicilio=domicilio;
        this.ruolo=ruolo;
    }
	
	/** Costruisce un nuovo oggetto di tipo <code>Utente</code> una volta letta una stringa dal file Utenti.csv.
 * @param ID codice identificativo letto dal file
 * @param nome nome
 * @param cognome cognome
 * @param username username
 * @param password password
 * @param dataNascita data di nascita
 * @param domicilio domicilio
 * @param ruolo ruolo
 */
	public Utente(long idEsistente, String nome, String cognome, String username, String password, LocalDate dataNascita, String domicilio, String ruolo) throws IOException{
		this.ID = idEsistente;
		this.nome=nome;
        this.cognome = cognome;
        this.username = username;
        this.password=Utente.passwordHash(password);
        this.dataNascita=dataNascita;
        this.domicilio=domicilio;
        this.ruolo=ruolo;
    }
	
	/** Metodo utilizzato per la generazione del codice identificativo secondo una numerazione progressiva,
	* incrementando di uno il valore del codice dell'ultimo utente registrato e andandolo ad assegnare al
	* nuovo oggetto di tipo <code>Utente</code> da memorizzare su file.
	* Il valore generato è di tipo <code>Long</code>.
	* @return idNuovo numero Long progressivo
	* @throws IOException eccezione che si solleva se si verificano problemi con gli stream
	* @throws Exception eccezione generica e che intercetta tutte le eccezioni a meno di IOException
	*/
	public static long generaID(){
		FileReader frd = null;
		BufferedReader brd = null;
		String riga =null;
		long idLetto =0;
		long idNuovo =0;
		String[] dati= null;
		try{
			frd= new FileReader("data/utenti.csv");
			brd= new BufferedReader(frd);
			try{
				while ((riga = brd.readLine()) != null) {
					dati = riga.split(",");
					idLetto = Long.parseLong(dati[0]);
				}
			}catch(Exception e){
				System.out.println("Non ci sono id disponibili");
			}
		}catch(IOException eFile){
			System.out.println("File non disponibile");
		}
		idNuovo = ++idLetto;
		return idNuovo;
	}
			
	
	/** Il metodo consente di ottenere un oggetto di tipo <code>Utente</code> specificandone il codice identificativo; 
	* se l'utente non viene trovato, il metodo solleva un'eccezione opportuna.
	* Il metodo è utile per creare un parametro formale di tipo <code>Utente</code> fornito in argomento al costruttore della classe @see Prenotazione.
	* @param id identificativo dell'utente
	* @return oggetto_Utente nuovo oggetto di tipo <code>Utente</code>
	* @throws UtenteNonEsistenteException eccezione che si solleva se l'utente non è stato ancora registrato
	* @throws IOException eccezione che si solleva se si verifica un errore durante la lettura/scrittura del file
	*/
	public static Utente getUtente(long id) throws UtenteNonEsistenteException, IOException{
		
		FileReader frd = new FileReader("data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String riga;
		while ((riga = brd.readLine()) != null) {
			String[] dati = riga.split(",");
			if((Long.parseLong(dati[0]))==id){
				return new Utente(id, dati[1], dati[2], dati[3], dati[4], LocalDate.parse(dati[5], DateTimeFormatter.ofPattern("yyyy-MM-dd")), dati[6], dati[7]);
			}
		}
		throw new UtenteNonEsistenteException(id);
	}
	/** Registra un nuovo oggetto di tipo <code>Utente</code> nel file Utenti.csv.
	 * @param utente oggetto di tipo <code>Utente</code> da registrare
	 * @throws IOException eccezione che si solleva se si verifica un errore durante la lettura/scrittura del file
	 *@throws Exception eccezione generica che si solleva e che intercetta tutte le eccezioni a meno di IOException
	 */
	public static void registraUtente(Utente utente) throws IOException{
		try{
			FileWriter fwt = new FileWriter("data/utenti.csv", true);
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
 * @return ID codice ID dell'utente
 */
	public long getID() {
        return ID;
    }

/** Restituisce il nome dell'utente.
 * @return nome nome dell'utente
 */
    public String getNome() {
        return nome;
    }


/** Restituisce il cognome dell'utente.
 * @return cognome cognome dell'utente
 */
    public String getCognome() {
        return cognome;
    }


/** Restituisce lo username dell'utente.
 * @return username username
 */
    public String getUsername() {
        return username;
    }


/** Restituisce la password dell'utente. Metodo non utilizzabile fuori dalla classe <code>Utente</code>
 * @return password password cifrata
 */
    private String getPassword() {
        return password;
    }


/** Restituisce la data di nascita dell'utente.
 * @return dataNascita data di nascita
 */
    public LocalDate getDataNascita() {
        return dataNascita;
    }


/** Restituisce il domicilio dell'utente.
 * @return domicilio domicilio
 */
    public String getDomicilio() {
        return domicilio;
    }


/** Restituisce il ruolo associato all'utente.
 * @return il ruolo
 */
    public String getRuolo() {
        return ruolo;
    }

	/** Restituisce una rappresentazione testuale dell'oggetto <code>Utente</code>, utilizzata per la visualizzazione 
	delle relatice informazioni e per la sua memorizzazione nel file Utenti.csv.
 * @return stringa_Utente  stringa contenente i dati dell'utente
 */
    public String toString(){
		return ID + "," +nome + ","+ cognome + ","+ username+"," + password +"," + dataNascita +"," +domicilio +"," +ruolo;
    }
	
	/**
	Metodo utilizzato per cifrare le password degli utenti registrati. Restituisce una stringa di 64 caratteri esadecimali.
	@param password password in chiaro da cifrare
	@throws IOException eccezione che si solleva se si verifica un errore durante la lettura/scrittura del file
	@throws Exception eccezione generica che si solleva e che intercetta tutte le eccezioni a meno di IOException
	*/
	public static String passwordHash(String password) throws IOException{
		try{ 
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
			
            for(byte b : hashBytes){
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
					hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString(); 
            
        }catch(Exception e){
            System.out.println("Algoritmo di hashing non trovato" + e.getMessage());
        }
		return null;
	}
	
	
}

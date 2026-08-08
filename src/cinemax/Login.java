package cinemax;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/** Gestisce l'autenticazione degli utenti dell'applicazione
 * La classe verifica le credenziali di accesso e determina il ruolo associato all'utente
 * @author Arianna Necchio
 * @author Gaia Galimberti
 */
public class Login{
	//campi
	/** Nome utente utilizzato per l'accesso. */
	private String username;
	/** Password associata all'utente. */
	private String password;
	private String hash;

	//costruttore
	/** Costruisce un nuovo oggetto Login.
 * @param <username> nome utente
 * @param <password> password dell'utente
 */
	public Login(String username, String password) throws IOException{
		this.username = username;
		this.password = password;
		this.hash = Utente.passwordHash(password);
	}

	//metodi

	/** Restituisce una rappresentazione testuale dell'oggetto Login.
 * @return una stringa contenente le informazioni dell'oggetto
 */
	public String toString(){
		return username + "," + password;
	}
	
	public String getUsername(){
		return username;
	}
	
	private String getPassword(){
		return password;
	}
	
	public String getHash(){
		return hash;
	}

	/** Verifica la validità delle credenziali inserite confrontandole con quelle memorizzate nel file degli utenti.
 * @param <login> oggetto contenente le credenziali da verificare
 * @return true se l'autenticazione va a buon fine, false altrimenti
 * @throws <IOException> se si verifica un errore durante la lettura del file
 * @throws <UtenteNonEsistenteException> se l'utente non è presente nel sistema
 */
	public static Utente login(Login login){
		Utente utente = null;
		try{
		FileReader frd = new FileReader("data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String persona;
		long idUtente = 0;
		
		while((persona = brd.readLine()) != null){
			String[] dati = persona.split(",");
			if(login.getUsername().equals(dati[3].trim()) && login.getHash().equalsIgnoreCase(dati[4].trim())){
				idUtente = Long.parseLong(dati[0]);
				utente = Utente.getUtente(idUtente);
				return utente;
			}
		}
		brd.close();
		frd.close();
		}catch(UtenteNonEsistenteException eUtente){
			System.out.println("L'utente non esiste" + eUtente.getMessage());
		}catch(IOException eFile){
			System.out.println("Il file non è disponibile");
		}
		catch(Exception e){
			System.out.println("Un valore inserito non è nel formato valido");
		}
		return null;
	}

}

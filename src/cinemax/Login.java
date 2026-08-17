//Necchio Arianna, matricola: 765298, sede: Como
package cinemax;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/** La classe Login gestisce l'autenticazione degli utenti all'applicazione.
 * La classe verifica le credenziali di accesso (username e password) dell'utente e ne determina il ruolo.
 * @author Arianna Necchio
 * @version 2.1
 */
public class Login{
	//campi
	/** Nome utente (username) utilizzato per l'accesso. */
	private String username;
	/** Password utilizzata per l'accesso associata all'utente. */
	private String password;
	private String hash;

	//costruttore
	/** Costruisce un nuovo oggetto di tipo <code> Login </code>.
 * @param username nome utente
 * @param password password dell'utente
 @throws IOException se si verifica un errore durante il recupero delle credenziali
 */
	public Login(String username, String password) throws IOException{
		this.username = username;
		this.password = password;
		this.hash = Utente.passwordHash(password);
	}

	//metodi

	/** Restituisce una rappresentazione testuale dell'oggetto Login.
 * @return una stringa contenente le informazioni dell'oggetto: username e password cifrata
 */
	public String toString(){
		return username + "," + password;
	}
	
	/** Restituisce l'username dell'utente
	@return username username
	*/
	public String getUsername(){
		return username;
	}
	
	/** Restituisce la password dell'utente. Metodo non utilizzabile fuori dalla classe <code>Login</code>
	@return password password
	*/
	private String getPassword(){
		return password;
	}
	
	/** Restituisce la password cifrata dell'utente
	@return hash password cifrata
	*/
	public String getHash(){
		return hash;
	}

	/** Verifica la validità delle credenziali inserite confrontandole con quelle memorizzate nel file Utenti.csv.
 * @param login oggetto di tipo <code> Login </code> contenente le credenziali da verificare
 * @return <code>true</code> se l'autenticazione va a buon fine, <code>false</code> altrimenti
 * @throws IOException se si verifica un errore durante la lettura del file
 * @throws UtenteNonEsistenteException se l'utente non è presente nel sistema
 @throws Exception eccezione generica per intercettare tutte le eccezioni generate che non rientrano in quelle sopra citate.
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

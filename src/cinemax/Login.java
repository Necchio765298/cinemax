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

	//costruttore
	/** Costruisce un nuovo oggetto Login.
 * @param <username> nome utente
 * @param <password> password dell'utente
 */
	public Login(String username, String password){
		this.username = username;
		this.password = password;
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

	/** Verifica la validità delle credenziali inserite confrontandole con quelle memorizzate nel file degli utenti.
 * @param <login> oggetto contenente le credenziali da verificare
 * @return true se l'autenticazione va a buon fine, false altrimenti
 * @throws <IOException> se si verifica un errore durante la lettura del file
 * @throws <UtenteNonEsistenteException> se l'utente non è presente nel sistema
 */
	public static boolean login(Login login) throws UtenteNonEsistenteException, IOException{
		FileReader frd = new FileReader("data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String persona;
		boolean accesso = false;
		try{
		while((persona = brd.readLine()) != null){
			String[] dati = persona.split(",");
			if(login.getUsername().equals(dati[3]) && login.getPassword().equals(dati[4]))
				accesso = true;
		}	
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("Un valore inserito non è nel formato valido");
		}
		return accesso;
	}

	/** Restituisce il ruolo associato all'utente autenticato.
 * @param <login> oggetto contenente le credenziali dell'utente
 * @param <accesso> esito dell'autenticazione
 * @return il ruolo dell'utente
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	public static String ruolo(Login login, boolean accesso) throws IOException{
		FileReader frd = new FileReader("data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String ruolo = "non specificato";
		String persona;
		try{
		while((persona= brd.readLine()) != null){
			String[] dati = persona.split(",");
			if(login.getUsername().equals(dati[3]) && login.getPassword().equals(dati[4])){
				if(dati[7].equals("Cliente"))
					ruolo = "Cliente";
				else if(dati[7].equals("Bigliettaio"))
					ruolo = "Bigliettaio";
				else
					ruolo = "Proiezionista";
			}
		}
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("ruolo inserito in fase di registrazione diverso da  \" Cliente \" , \" Bigliettaio \", \"Proiezionista \" ");
		}
		return ruolo;
	}

	/** Restituisce l'identificativo univoco dell'utente autenticato.
 * Il metodo ricerca l'utente nel file CSV e ne restituisce l'ID.
 * @param <login> credenziali dell'utente da ricercare
 * @return l'identificativo dell'utente
 * @throws <UtenteNonEsistenteException> se l'utente non è presente nel sistema
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	public static long getIdUtente(Login login) throws IOException{
		FileReader frd = new FileReader("data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String riga;
		long identificativo = 0;
		try{
			while ((riga = brd.readLine()) != null) {
				String[] dati = riga.split(",");
				if(login.getUsername().equals(dati[3]) && login.getPassword().equals(dati[4])){
					identificativo = (long) Double.parseDouble(dati[0]);
				}
			}
		}catch(IOException e){
			e.getMessage();
		}
		return identificativo;
	}
}

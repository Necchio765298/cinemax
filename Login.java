//package bin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Gestisce l'autenticazione degli utenti dell'applicazione.
 * La classe verifica le credenziali di accesso e determina il ruolo associato all'utente.
 * @author 
 */
public class Login{
	//campi
	/** Nome utente utilizzato per l'accesso. */
	String username;
	/** Password associata all'utente. */
	String password;

	/**
 * Costruisce un nuovo oggetto Login.
 * @param username nome utente
 * @param password password dell'utente
 */
	//costruttore
	public Login(String username, String password){
		this.username = username;
		this.password = password;
	}

	//metodi

	/**
 * Restituisce una rappresentazione testuale dell'oggetto Login.
 * @return una stringa contenente le informazioni dell'oggetto
 */
	public String toString(){
		return username + "," + password;
	}

	/**
 * Verifica la validità delle credenziali inserite confrontandole con quelle memorizzate nel file degli utenti.
 * @param login oggetto contenente le credenziali da verificare
 * @return true se l'autenticazione va a buon fine, false altrimenti
 * @throws IOException se si verifica un errore durante la lettura del file
 */
	public static boolean login(Login login) throws IOException{
		FileReader frd = new FileReader("../data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String persona;
		boolean accesso = false;
		try{
		while((persona = brd.readLine()) != null){
			
			if(persona.contains(login.toString()))
				accesso = true;
		}	
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("Un valore inserito non è nel formato valido");
		}
		return accesso;
	}

	/**
 * Restituisce il ruolo associato all'utente autenticato.
 * @param login oggetto contenente le credenziali dell'utente
 * @param accesso esito dell'autenticazione
 * @return il ruolo dell'utente
 * @throws IOException se si verifica un errore durante la lettura del file
 */
	public static String ruolo(Login login, boolean accesso) throws IOException{
		FileReader frd = new FileReader("../data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String ruolo = "non specificato";
		String persona;
		try{
		while((persona= brd.readLine()) != null){
			if(persona.contains(login.toString())){
				if(persona.contains("Cliente"))
					ruolo = "Cliente";
				else if(persona.contains("Bigliettaio"))
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
}

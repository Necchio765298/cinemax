//Necchio Arianna, matricola: 765298, sede: Como


package cinemax;

/** Classe di eccezioni che gestisce gli utenti inesistenti ricercati nel file "Utenti.csv"
 * @author Arianna Necchio
 * @version 2.1
 */
public class UtenteNonEsistenteException extends Exception{

	/** Costruisce un nuovo oggetto di tipo <code>UtenteNonEsistenteException</code> quando si tenta di ricercare un 
utente non registrato all'applicazione.
 * @param id codice identificativo dell'utente da ricercare
 */
	public UtenteNonEsistenteException(long id){
		System.out.println("L'utente con ID " + id+ " non esiste");
	}
}

package cinemax;

/** Eccezione personalizzata lanciata quando un utente richiesto non è presente nel sistema.
 * @author 
 */
public class UtenteNonEsistenteException extends Exception{

	/** Costruisce una nuova eccezione relativa a un utente non presente nel sistema.
 * @param <id> identificativo dell'utente non trovato
 */
	public UtenteNonEsistenteException(long id){
		System.out.println("L'utente con ID " + id+ " non esiste");
	}
}

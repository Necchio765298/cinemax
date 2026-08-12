package cinemax;
import java.time.LocalDateTime;

/** Eccezione personalizzata lanciata quando una proiezione richiesta non è presente nel sistema
 * @author Arianna Necchio
 * @author Gaia Galimberti
 */
public class ProiezioneNonEsistenteException extends Exception{

	/** Costruisce una nuova eccezione relativa a una proiezione non esistente
 * @param <Idt> data e ora della proiezione non trovata
 */
	public ProiezioneNonEsistenteException(LocalDateTime ldt){
		System.out.println("La proiezione con data e ora " + ldt + " non esiste");
	}
	
	public ProiezioneNonEsistenteException(boolean ricerca){
		if(ricerca)
			System.out.println("La proiezione ricercata non è stata trovata");	
		else
			System.out.println("La proiezione non esiste");
	}
}

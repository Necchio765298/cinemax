//Necchio Arianna, matricola: 765298, sede: Como


package cinemax;
import java.time.LocalDateTime;

/**Classe di eccezioni che gestisce le proiezioni inesistenti ricercate nel file "Proiezioni.csv"
 * @author Arianna Necchio
 * @version 2.1
 */
public class ProiezioneNonEsistenteException extends Exception{

	/** Costruisce un nuovo oggetto di tipo <code>ProiezioneNonEsistenteException</code>
quando si tenta di ricercare una proiezione non esistente.
 * @param ldt data e ora della proiezione
 */
	public ProiezioneNonEsistenteException(LocalDateTime ldt){
		System.out.println("La proiezione con data e ora " + ldt + " non esiste");
	}
	
	/** Costruisce un nuovo oggetto di tipo <code>ProiezioneNonEsistenteException</code>
quando si tenta di ricercare una proiezione non esistente.
 * @param ricerca <code>true</code> o <code>false</code> a seconda che la proiezione sia stata trovata o no
 */	
	public ProiezioneNonEsistenteException(boolean ricerca){
		if(ricerca)
			System.out.println("La proiezione ricercata non è stata trovata");	
		else
			System.out.println("La proiezione non esiste");
	}
}

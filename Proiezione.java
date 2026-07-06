//package bin;
import java.time.LocalDateTime;
import java.io.Console;

/** Rappresenta una proiezione cinematografica programmata dal cinema.
 * La classe memorizza il film proiettato, la data e l'ora della proiezione e il prezzo del biglietto.
 * @author 
 */
public class Proiezione {
   /** Film associato alla proiezione. */
	private Film film;
    /** Data e ora della proiezione. */
	private LocalDateTime dataOra;
    /** Prezzo del biglietto della proiezione. */
	private double prezzoBiglietto;

	/** Costruisce una nuova proiezione.
 * @param <film> film associato alla proiezione
 * @param <dataOra> data e ora della proiezione
 * @param <prezzoBiglietto> prezzo del biglietto
 */
    public Proiezione(Film film, LocalDateTime dataOra , double prezzoBiglietto){
        this.film = film;
        this.dataOra = dataOra ;
        this.prezzoBiglietto=prezzoBiglietto;
    }

	/** Restituisce il film associato alla proiezione.
 * @return il film della proiezione
 */
    public Film getFilm(){
        return film;
    }

	/** Restituisce la data e l'ora della proiezione.
 * @return la data e l'ora della proiezione
 */
    public LocalDateTime getDataOra(){
        return dataOra;
    }

	/** Restituisce il prezzo del biglietto della proiezione.
 * @return il prezzo del biglietto
 */
    public double getPrezzoBiglietto(){
        return prezzoBiglietto;
    }

	/** Modifica la data e l'ora della proiezione.
 * @param <dataOra> nuova data e ora della proiezione
 */
    public void setDataOra(LocalDateTime dataOra){
        this.dataOra=dataOra;
    }

	/** Modifica il prezzo del biglietto della proiezione.
 * @param <prezzoBiglietto> nuovo prezzo del biglietto
 */
    public void setPrezzoBiglietto(double prezzoBiglietto){
        this.prezzoBiglietto = prezzoBiglietto;
    }

	/** Restituisce una rappresentazione testuale della proiezione, utilizzata per la visualizzazione delle informazioni e per la memorizzazione dei dati nel file csv.
 * @return una stringa contenente i dati della proiezione
 */
    public String toString(){
        return dataOra+ ","+ film.toString()+ "," +prezzoBiglietto;
    }

}

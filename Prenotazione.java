//package bin;
import java.util.UUID;

/** Rappresenta una prenotazione effettuata da un utente per una determinata proiezione cinematografica.
 * La classe memorizza il codice identificativo, l'utente, la proiezione e il numero di biglietti acquistati.
 * @author 
 */
public class Prenotazione {

    /** Codice identificativo della prenotazione. */
    private String codice;
   /** Utente che ha effettuato la prenotazione. */
    private Utente utente;
    /** Proiezione associata alla prenotazione. */
    private Proiezione proiezione;
    /** Numero di biglietti acquistati. */
    private int numeroBiglietto;

    /** Costruisce una nuova prenotazione.
 * Il codice identificativo viene generato automaticamente.
 * @param <utente> utente che effettua la prenotazione
 * @param <proiezione> proiezione selezionata
 * @param <numeroBiglietto> numero di biglietti acquistati
 */
    public Prenotazione(Utente utente , Proiezione proiezione , int numeroBiglietto){
        this.codice = UUID.randomUUID().toString().substring(0,8).toUpperCase();
        this.utente = utente;
        this.proiezione=proiezione;
        this.numeroBiglietto=numeroBiglietto;
    }

    /** Modifica la proiezione associata alla prenotazione.
 * @param <proiezione> nuova proiezione
 */
    public void setProiezione(Proiezione proiezione) {
        this.proiezione = proiezione;
    }

    /** Modifica il numero di biglietti della prenotazione.
 * @param <numeroBiglietto> nuovo numero di biglietti
 */
    public void setNumeroBiglietto(int numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }

    /** Restituisce il codice identificativo della prenotazione.
 * @return il codice della prenotazione
 */
    public String getCodice() {
        return codice;
    }

    /** Restituisce l'utente associato alla prenotazione.
 * @return l'utente della prenotazione
 */
    public Utente getUtente() {
        return utente;
    }

    /** Restituisce la proiezione associata alla prenotazione.
 * @return la proiezione della prenotazione
 */
    public Proiezione getProiezione() {
        return proiezione;
    }

    /** Restituisce il numero di biglietti acquistati.
 * @return il numero di biglietti
 */
    public int getNumeroBiglietto() {
        return numeroBiglietto;
    }

/** Calcola il costo totale della prenotazione.
 * @return il costo totale della prenotazione
 */
    public double getCostoTotale(){
        return proiezione.getPrezzoBiglietto()*numeroBiglietto;
    }

    /** Restituisce una rappresentazione testuale della prenotazione, utilizzata per la visualizzazione delle informazioni e per la memorizzazione dei dati nel file csv.
 * @return una stringa contenente i dati della prenotazione
 */
    public String toString() {
        return proiezione.getDataOra() +"," + utente.getNome() +"," + utente.getCognome() +"," + proiezione.getFilm().getTitolo() +"," +  numeroBiglietto + ","+ codice;
    }


}

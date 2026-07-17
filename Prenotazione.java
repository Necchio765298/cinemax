package cinemax;
import java.util.UUID;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/** Rappresenta una prenotazione effettuata da un utente per una determinata proiezione cinematografica.
 * La classe memorizza il codice identificativo, l'utente, la proiezione e il numero di biglietti acquistati.
 * @author Arianna Necchio
 * @author Gaia Galimberti
 */
public class Prenotazione {
    /** Codice identificativo della prenotazione. Identifica univocamente una prenotazione */
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


	/** Registra una prenotazione nel file csv delle prenotazioni.
 * @param <prenotazione> prenotazione da registrare
 * @throws <IOException> se si verifica un errore durante la scrittura del file
 */
	public static void registraPrenotazione(Prenotazione prenotazione) throws IOException{
		FileWriter fwt = new FileWriter("../data/prenotazioni.csv", true);
		BufferedWriter bwt = new BufferedWriter(fwt);
		bwt.write(prenotazione.toString());
		System.out.println("la prenotazione è stata registrata");
		bwt.newLine();
		bwt.close();
		fwt.close();
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
	
	
	public Utente getUtente(){
		return utente;
	}
	
	
	public Proiezione getProiezione(){
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
        return proiezione.getDataOra() +"," + utente.getID() + "," +utente.getNome() +"," + utente.getCognome() +"," + proiezione.getTitolo() +"," +  numeroBiglietto + ","+ codice;
    }

	/** Confronta l'oggetto corrente con un altro oggetto per verificarne l'uguaglianza.
 * @param <obj> oggetto da confrontare con la prenotazione corrente
 * @return {@code true} se i due oggetti sono considerati uguali, {@code false} altrimenti
 * @throws <RuntimeException> se si verifica un errore durante il confronto
 */
	public boolean equals(Object obj) throws RuntimeException{
		if(obj instanceof Prenotazione){
			Prenotazione p=(Prenotazione) obj;
			if(p.getCodice()==this.codice && p.getUtente()== this.utente && p.getProiezione() == this.proiezione && p.getNumeroBiglietto() == this.numeroBiglietto){
				return true;
			}
		}
		return false;
	}

}

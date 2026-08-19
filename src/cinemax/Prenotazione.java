//Necchio Arianna, matricola: 765298, sede: Como


package cinemax;
import java.util.UUID;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/** La classe Prenotazione costruisce oggetti di tipo <code>Prenotazione</code>, che rappresentano le prenotazioni create dagli utenti.
 * Una prenotazione è costituita dal proprio codice identifiativo, da un oggetto di tipo <code>Utente</code> che ha prenotato,
 * da un altro oggetto di tipo <code>Proiezione</code> che individua la proiezione che l'utente intende visionare e il numero dei biglietti acquistati.
 * @author Arianna Necchio
 * @version 2.1
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

    /** Costruisce un nuovo oggetto di tipo <code>Prenotazione</code>.
 * Il codice identificativo viene generato automaticamente.
 * @param utente utente che effettua la prenotazione
 * @param proiezione proiezione da visionare
 * @param numeroBiglietto numero di biglietti acquistati
 */
    public Prenotazione(Utente utente , Proiezione proiezione , int numeroBiglietto){
        this.codice = UUID.randomUUID().toString().substring(0,8).toUpperCase();
        this.utente = utente;
        this.proiezione=proiezione;
        this.numeroBiglietto=numeroBiglietto;
    }
	
   /** Costruisce un nuovo oggetto di tipo <code>Prenotazione</code>.
 * Il codice identificativo viene letto dal file Proiezioni.csv e memorizzato nel campo <code>codice</code>.
 * @param codiceEsistente codice letto dal file
 * @param utente utente che effettua la prenotazione
 * @param proiezione proiezione da visionare
 * @param numeroBiglietto numero di biglietti acquistati
 */	
	public Prenotazione(String codiceEsistente, Utente utente , Proiezione proiezione , int numeroBiglietto){
        this.codice = codiceEsistente;
        this.utente = utente;
        this.proiezione=proiezione;
        this.numeroBiglietto=numeroBiglietto;
    }


	/** Registra una prenotazione nel file Prenotazoni.csv.
 * @param prenotazione prenotazione da registrare
 * @throws IOException eccezione che si verifica un errore durante la scrittura del file
 */
	public static void registraPrenotazione(Prenotazione prenotazione) throws IOException{
		FileWriter fwt = new FileWriter("data/prenotazioni.csv", true);
		BufferedWriter bwt = new BufferedWriter(fwt);
		bwt.write(prenotazione.toString());
		System.out.println("la prenotazione è stata registrata");
		bwt.newLine();
		bwt.close();
		fwt.close();
	}

    /** Modifica la proiezione associata alla prenotazione.
 * @param proiezione nuova proiezione
 */
    public void setProiezione(Proiezione proiezione) {
        this.proiezione = proiezione;
    }

    /** Modifica il numero di biglietti della prenotazione.
 * @param numeroBiglietto nuovo numero di biglietti
 */
    public void setNumeroBiglietto(int numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }

    /** Restituisce il codice identificativo della prenotazione.
 * @return codice il codice della prenotazione
 */
    public String getCodice() {
        return codice;
    }
	
	 /** Restituisce un oggetto di tipo <code>Utente</code> che ha effettuato la prenotazione.
 * @return utente utente che ha prenotato
 */
	public Utente getUtente(){
		return utente;
	}
	
	 /** Restituisce un oggetto di tipo <code>Proiezione</code>.
 * @return proiezione proiezione da visualizzare
 */
	public Proiezione getProiezione(){
		return proiezione;
	}

    /** Restituisce il numero di biglietti acquistati.
 * @return numeroBiglietto il numero di biglietti
 */
    public int getNumeroBiglietto() {	
        return numeroBiglietto;
    }

/** Calcola il costo totale dei biglietti per una prenotazione.
 * @return costo_totale costo della prenotazione
 */
    public double getCostoTotale(){
        return proiezione.getPrezzoBiglietto()*numeroBiglietto;
    }
	
	/**
	*Restituisce il numero totale dei biglietti acquistati per una prenotazione al fine di calcolare il numero di posti occupati.
	*@param orario orario che identifica una proiezione
	*@return biglietti numero totale dei biglietti
	*@throws ProiezioneNonEsistenteException proiezione di cui si vuole calcolare il totale dei biglietti che non esiste
	*@throws IOException eccezione che si può sollevare nel caso si verifichino errori con gli stream
	*/
	public static int getTotaleBiglietti(LocalDateTime orario) throws ProiezioneNonEsistenteException, IOException{
		int biglietti = 0;
		FileReader frd = null;
		BufferedReader brd= null;
		try{
			frd = new FileReader("data/prenotazioni.csv");
			brd = new BufferedReader(frd);
			String riga;
			while((riga =brd.readLine()) != null){
				String[] dati = riga.split(",");
				if(LocalDateTime.parse(dati[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).equals(Proiezione.getProiezione(orario).getDataOra())){
					biglietti = biglietti + Integer.parseInt(dati[5]);
				}
			}
			
		}catch(Exception e){
			System.err.println("Errore negli stream "+e.getMessage());
		}finally{
			brd.close();
			frd.close();
		}
		return biglietti;
	}

    /** Restituisce una rappresentazione testuale della prenotazione, utilizzata per la visualizzazione delle informazioni 
	* e per la memorizzazione dei dati nel file Prenotazioni.csv.
	* @return stringa_prenotazione stringa contenente i dati della prenotazione in formato testuale
	*/
    public String toString(){
        return proiezione.getDataOra().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +"," + utente.getID() + "," +utente.getNome() +"," + utente.getCognome() +"," + proiezione.getTitolo() +"," +  numeroBiglietto + ","+ codice;
    }
	

	/**
	* Confronta la prenotazione corrente con un'altra per verificare che si tratti dello stesso oggetto.
	* @param obj prenotazione da confrontare con la prenotazione corrente
	* @return <code>true</code> se i due oggetti sono considerati uguali, <code> false</code> altrimenti
	* @throws RuntimeException eccezione sollevata se si verifica un errore durante il confronto
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

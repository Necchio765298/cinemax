//Necchio Arianna, matricola: 765298, sede: Como

package cinemax;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;


/** La classe Proiezione costruisce oggetti di tipo <code>Proiezione</code>, che rappresentano le proiezioni inserite nel palinsesto del cinema.
 * Una proiezione è costituita dal titolo del film proiettato, 
 la data e l'ora, il genere del film, il regista, l'anno di produzione, la durata in minuti del film,
 l'età minima consentita di visualizzazione e il prezzo del biglietto.
 * @author Arianna Necchio
 * @version 
 */
public class Proiezione {
     /** Data e ora della proiezione. Questo campo è l'identificativo della proiezione 
	*dal momento che in un certo orario ho una sola proiezione che viene riprodotta. */
	private LocalDateTime dataOra;
	/** Titolo del film. */
    private String titolo ;
    /** Genere del film. */
    private String genere;
    /** Regista del film. */
    private String regista;
    /** Anno di uscita del film. */
    private int anno;
    /** Durata in minuti del film. */
    private int durataMinuti;
    /** Età minima del film. */
    private int etaMinima;
    /** Prezzo del biglietto della proiezione. */
	private double prezzoBiglietto;
	
	

	/** Costruisce una nuova proiezione.
 * @param dataOra data e ora della proiezione
 * @param titolo titolo del film
 * @param genere genere del film
 * @param regista regista del film
 * @param anno anno di produzione
 * @param durataMinuti durata del film in minuti
 * @param etaMinima età minima consigliata
 * @param prezzoBiglietto prezzo del biglietto
 */
    public Proiezione(LocalDateTime dataOra, String titolo, String genere, String regista, int anno, int durataMinuti, int etaMinima, double prezzoBiglietto){
        this.dataOra = dataOra;
		this.titolo = titolo;
		this.genere = genere;
		this.regista = regista;
		this.anno = anno;
		this.durataMinuti= durataMinuti;
		this.etaMinima = etaMinima;
        this.prezzoBiglietto=prezzoBiglietto;
    }

/**
* Il metodo consente di ottenere un oggetto di tipo <code>Proiezione</code> fornendo come argomento un oggetto di tipo <code>LocalDateTime</code>, 
ovvero la relativa data e ora.

* @param ldt la data e l'ora associata alla proiezione da ricercare
* @return Proiezione oggetto di tipo <code>Proiezione</code>
* @throws ProiezioneNonEsistenteException se la proiezione ricercata non esiste.
@throws IOException se si verifica un errore nella gestione degli stream.
*/
	public static Proiezione getProiezione(LocalDateTime ldt) throws ProiezioneNonEsistenteException, IOException{
		FileReader frd = new FileReader("data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String proiezione;
		try{
			while ((proiezione = brd.readLine()) != null) {
				String[] dati = proiezione.split(",");
				String dataPulita = dati[0].replace("\"", "").trim();
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				if(LocalDateTime.parse(dataPulita, formato).isEqual(ldt)){
					return new Proiezione(ldt, dati[1].replace("\"", "").trim(), dati[2].trim(), dati[3].replace("\"", "").trim(), Integer.parseInt(dati[4].trim()), Integer.parseInt(dati[5].trim()), Integer.parseInt(dati[6].trim()), Double.parseDouble(dati[7].replace("\"", "").trim()));
				}
			}	
		}catch(Exception e){
			System.err.println("Errore di recupero proiezione: " + e.getMessage());
		}
		throw new ProiezioneNonEsistenteException(ldt);
	}

/** Ricerca una o più proiezioni nel file Proiezioni.csv in base ai criteri di ricerca specificati.
* Il metodo riceve in argomento dati di tipo String, LocalDateTime e double, in particolare si può ricercare una proiezione per:
* - data e ora;
* - titolo;
* - genere;
* - nome e cognome del regista;
* - anno di produzione;
* - durata in minuti;
* - età minima di visualizzazione;
* - prezzo del biglietto.
* @param data data e ora della proiezione in formato yyyy-MM-dd HH-mm-ss
* @param titolo titolo del film
* @param genere genere del film, ad esempio Fantasy, Adventure...
* @param regista nome e cognome del regista
* @param anno anno di produzione cinematografica
* @param durata durata in minuti del film
* @param eta età minima consigliata per la visualizzazione
* @param prezzo prezzo del biglietto per una persona
* @return memo lista di proiezioni trovate, sotto un oggetto di tipo <code>ArrayList</code>
* @throws ProiezioneNonEsistenteException se la proiezione non esiste
* @throws IOException se si verifica un errore durante la lettura del file
* @throws NumberFormatException se il formato della data inserita non è corretto
*/
	public static ArrayList<Proiezione> cercaProiezione(LocalDateTime data, String titolo, String genere, String regista, int anno, int durata, int eta, double prezzo) throws ProiezioneNonEsistenteException{
		Proiezione p= null;
		FileReader frd = null;
		BufferedReader brd= null;
		ArrayList<Proiezione> memo= null;
		String riga;
		LocalDateTime loc = null;
		String t = null;
		String reg = null;
		String gen = null;
		Double prez = 0.0;
		String[] dati=null;
		try{	
			frd = new FileReader("data/proiezioni.csv");
			brd = new BufferedReader(frd);
			memo = new ArrayList<Proiezione>();
		}catch(Exception eFile){
			System.err.println("Errore nell'apertura del file");
		}
		try{	
			while((riga = brd.readLine()) != null){
				dati = riga.split(","); 
				loc=LocalDateTime.parse(dati[0].replace("\"", "").trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				t=dati[1].replaceAll("\"", "").trim();
				gen =dati[2].trim();
				reg = dati[3].replaceAll("\"", "").trim();
				prez=Double.parseDouble(dati[7].replace("\"", "").trim());
				if(loc.isEqual(data)
					|| (t.toLowerCase().equals(titolo.toLowerCase()))
					|| (gen.toLowerCase().equals(genere.toLowerCase()))
					|| (reg.toLowerCase().equals(regista.toLowerCase()))
					|| (Integer.parseInt(dati[4].trim())==anno)
					|| (Integer.parseInt(dati[5].trim())==durata)
					|| (Integer.parseInt(dati[6].trim())==eta)
					|| (prez==prezzo)){
						
						p= new Proiezione(loc, t, gen, reg, Integer.parseInt(dati[4].trim()), Integer.parseInt(dati[5].trim()), Integer.parseInt(dati[6].trim()), prez);
						memo.add(p);
				}
			}
			brd.close();
			frd.close();
			if(memo.isEmpty()){
				throw new ProiezioneNonEsistenteException(true);
			}
			
		}catch(IOException e){
			System.out.println("Si è verificato un problema nella ricerca" + e.getMessage());
		}catch(NumberFormatException eFormato){
			System.out.println("Formato data non valido "+ eFormato.getMessage());
		}
		return memo;
	}

	/** Ricerca una o più proiezioni nel file Proiezioni.csv comprese in un determinato intervallo di date.
 * @param dataInizio data iniziale dell'intervallo
 * @param dataFine data finale dell'intervallo
 * @return memo lista di proiezioni trovate, sotto un oggetto di tipo <code>ArrayList</code>
 * @throws ProiezioneNonEsistenteException se la proiezione non esiste
 * @throws IOException se si verifica un errore durante la lettura del file
 * @throws NumberFormatException se il formato della data inserita non è corretto
 */
	public static ArrayList<Proiezione> cercaProiezione(LocalDate dataInizio, LocalDate dataFine) throws ProiezioneNonEsistenteException{
		Proiezione trovata= null;
		FileReader frd = null;
		BufferedReader brd= null;
		ArrayList<Proiezione> memo = null;
		String riga;
		LocalDateTime loc = null;
		String t = null;
		String reg = null;
		String gen = null;
		Double prez = 0.0;
		String[] dati = null;
		try{
		frd = new FileReader("data/proiezioni.csv");
		brd = new BufferedReader(frd);
		memo = new ArrayList<Proiezione>();
		}catch(Exception eFile){
			System.err.println("Errore nell'apertura del file");
		}
		try{
			
			while((riga = brd.readLine()) != null) {
				dati = riga.split(",");
				loc=LocalDateTime.parse(dati[0].replace("\"", "").trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				t=dati[1].replaceAll("\"", "").trim();
				gen =dati[2].trim();
				reg = dati[3].replaceAll("\"", "").trim();
				prez=Double.parseDouble(dati[7].replace("\"", "").trim());
				
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDate data = LocalDateTime.parse(dati[0].replace("\"", "").trim(), formato).toLocalDate();
					if((dataInizio.isBefore(data)) && (dataFine.isAfter(data))){
						trovata= new Proiezione(loc, t, gen, reg, Integer.parseInt(dati[4].trim()), Integer.parseInt(dati[5].trim()), Integer.parseInt(dati[6].trim()), prez);
						memo.add(trovata);
					}
			}
			brd.close();
			frd.close();
			if(memo.isEmpty()){
				throw new ProiezioneNonEsistenteException(true);
			}
			
		}catch(IOException e){
			System.out.println("Si è verificato un problema nella ricerca");
		}catch(NumberFormatException eFormato){
			System.out.println("Formato data non valido");
		}
		return memo;
	}

	/** Restituisce una rappresentazione testuale delle proiezioni memorizzate all'interno della lista creata con 
 * @see cercaProiezione(LocalDateTime data, String titolo, String genere, String regista, int anno, int durata, int eta, double prezzo) e con 
 * @see cercaProiezione(LocalDate dataInizio, LocalDate dataFine).
 * @param memo lista di tipo <code>ArrayList</code> contenente le proiezioni da visualizzare
 * @throws IOException nel caso la lista sia inesistente o sollevi qualsiasi eccezione di altro tipo
 */
	public static void visualizzaProiezione(ArrayList<Proiezione> memo) throws IOException{
		System.out.println("Proiezioni trovate: ");
		for(Proiezione pro : memo)
			System.out.println(pro.toString());
	}
		
	
	
	/** Restituisce la data e l'ora della proiezione.
 * @return dataOra la data e l'ora della proiezione
 */
    public LocalDateTime getDataOra(){
		return dataOra;
    }

	/** Restituisce il prezzo del biglietto della proiezione.
 * @return prezzoBiglietto il prezzo del biglietto per una persona
 */
    public double getPrezzoBiglietto(){
        return prezzoBiglietto;
    }

	/** Modifica la data e l'ora della proiezione.
 * @param dataOra nuova data e ora della proiezione
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
	
	/** Restituisce il titolo del film.
 * @return titolo titolo
 */
	public String getTitolo(){
		return titolo;
	}
	
    /** Restituisce il genere del film.
 * @return genere genere del film
 */
    public String getGenere(){
        return genere;
    }

    /** Restituisce il regista del film.
 * @return regista regista del film
 */
    public String getRegista(){
        return regista;
    }

    /** Restituisce l'anno del film.
 * @return anno anno del film
 */
    public int getAnno(){
        return anno;
    }

    /** Restituisce la durata in minuti del film.
 * @return durataMinuti la durata in minuti del film
 */
    public int getDurataMinuti(){
        return durataMinuti;
    }

    /** Restituisce l'età minima del film.
 * @return etaMinima l'età minima del film
 */
    public int getEtaMinima(){
        return etaMinima;
    }
	
	/** Restituisce una rappresentazione testuale della proiezione, utilizzata per la visualizzazione 
 * delle informazioni e per la memorizzazione dei dati nel file Proiezioni.csv.
 * @return stringa_proiezione proiezione nel formato testuale
 */
    public String toString(){
        return dataOra.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+ ","+ titolo + "," + genere + "," + regista + "," + anno+ "," + durataMinuti+ "," + etaMinima+ "," +prezzoBiglietto;
    }

	/** 
	@deprecated 
	Confronta la proiezione corrente con un'altra per verificare che si tratti dello stesso oggetto.
 * @param obj proiezione da confrontare con la proiezione corrente
 * @return <code>true</code> se i due oggetti sono considerati uguali, <code> false</code> altrimenti
 * @throws RuntimeException eccezione sollevata se si verifica un errore durante il confronto
 */
	public boolean equals(Object obj) throws RuntimeException{
		if(obj instanceof Proiezione){
			Proiezione p=(Proiezione) obj;
			if(p.getDataOra()==this.dataOra && p.getTitolo()== this.titolo && p.getGenere() == this.genere && p.getRegista() == this.regista && p.getAnno()==this.anno && p.getDurataMinuti()== this.durataMinuti && p.getEtaMinima() == this.etaMinima && p.getPrezzoBiglietto() == this.prezzoBiglietto){
				return true;
			}
		}
		return false;
	}
}

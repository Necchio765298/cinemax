package cinemax;
import java.time.LocalDateTime;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/** Rappresenta una proiezione cinematografica programmata dal cinema.
 * La classe memorizza il film proiettato, la data e l'ora della proiezione e il prezzo del biglietto.
 * @author 
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
 * @param <dataOra> data e ora della proiezione
 * @param <titolo> titolo del film
 * @param <genere> genere del film
 * @param <regista> regista del film
 * @param anno anno di uscita
 * @param <durataMinuti> durata del film in minuti
 * @param <etaMinima> età minima consigliata
 * @param <prezzoBiglietto> prezzo del biglietto
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
* Il metodo consente di ottenere un oggetto di tipo Proiezione fornendo come argomento un oggetto di tipo LocalDateTime.
* Il metodo è utile per creare un parametro formale di tipo Proiezione da fornire in argomento al costruttore della classe Prenotazione.
* @param <ldt> la data e l'ora associata alla proiezione da ricercare
* @return una Proiezione
* @throws ProiezioneNonEsistenteException se la proiezione ricercata non esiste.
*/
	public static Proiezione getProiezione(LocalDateTime ldt){
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String proiezione;
		while ((proiezione = brd.readLine()) != null) {
			String[] dati = proiezione.split(",");
			if(dati[0]==ldt){
				return new Proiezione(ldt, dati[1], dati[2], dati[3], int Integer.parseInt(dati[4]), int Integer.parseInt(dati[5]), int Integer.parseInt(dati[6]), double Double.parseDouble(dati[7]));
			}
			throw new ProiezioneNonEsistenteException(ldt);
		}
	}

/** Ricerca una o più proiezioni nel file csv in base ai criteri di ricerca specificati.
* Il metodo riceve in argomento dati di tipo String, LocalDateTime e double.
 * @param <arg> criterio utilizzato per la ricerca
 * @return le proiezioni trovate
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	public static Proiezione cercaProiezione(Object arg) throws IOException{
		try{
			FileReader frd = new FileReader("../data/proiezioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			if(arg instanceof LocalDateTime){
				p = (LocalDateTime) arg;
				Proiezione.getProiezione(p);
			}else{
				if(arg instanceof String)
					p = (String) arg;
				else
					p= (double) arg;
				String proiezione;
				while((proiezione = brd.readLine()) != null){
					String[] dati = riga.split(",");
					if(((dati[1] || dati[2]) == p ) || (dati[7] == p))
						return new Proiezione(dati[0], dati[1], dati[2], dati[3], int Integer.parseInt(dati[4]), int Integer.parseInt(dati[5]), int Integer.parseInt(dati[6]), double Double.parseDouble(dati[7]));
				}
			}
			brd.close();
			frd.close();
		}catch(Exception e){
			System.out.println("Criterio inserito non valido");
		}
	}

	/** Ricerca le proiezioni comprese in un determinato intervallo di date.
 * @param <dataInizio> data iniziale dell'intervallo
 * @param <dataFine> data finale dell'intervallo
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	public static Proiezione cercaProiezione(LocalDate dataInizio, LocalDate dataFine) throws IOException{
		try{
		FileReader frd = new FileReader("../data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String Date;
		while((Date = brd.readLine()) != null) {
			String[] dati = riga.split(",");
			LocalDate data = LocalDate.parse(dati[0]);
			if(data.isAfter(dataInizio) && data.isBefore(dataFine))
				return new Proiezione(dati[0], dati[1], dati[2], dati[3], int Integer.parseInt(dati[4]), int Integer.parseInt(dati[5]), int Integer.parseInt(dati[6]), double Double.parseDouble(dati[7]));
		}
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("Formato della data inserita non corretto");
		}
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
	
	public String getTitolo(){
		return titolo;
	}
	
    /** Restituisce il genere del film.
 * @return il genere del film
 */
    public String getGenere(){
        return genere;
    }

    /** Restituisce il regista del film.
 * @return il regista del film
 */
    public String getRegista(){
        return regista;
    }

    /** Restituisce l'anno del film.
 * @return l'anno del film
 */
    public int getAnno(){
        return anno;
    }

    /** Restituisce la durata in minuti del film.
 * @return la durata in minuti del film
 */
    public int getDurataMinuti(){
        return durataMinuti;
    }

    /** Restituisce l'età minima del film.
 * @return l'età minima del film
 */
    public int getEtaMinima(){
        return etaMinima;
    }
	
	/** Restituisce una rappresentazione testuale della proiezione, utilizzata per la visualizzazione delle informazioni e per la memorizzazione dei dati nel file csv.
 * @return una stringa contenente i dati della proiezione
 */
    public String toString(){
        return dataOra+ ","+ titolo + "," + genere + "," + regista + "," + anno+ "," + durataMinuti+ "," + etaMinima+ "," +prezzoBiglietto;
    }
	
	public boolean equals(Object obj) throws RuntimeException{
		if(obj instanceof Proiezione){
			p=(Proiezione) obj;
			if(p.getDataOra()==this.dataOra && p.getTitolo()== this.titolo && p.getGenere() == this.genere && p.getRegista() == this.regista && p.getAnno()==this.anno && p.durataMinuti()== this.durataMinuti && p.getEtaMinima() == this.etaMinima && p.getPrezzoBiglietto() == this.prezzoBiglietto){
				return true;
			}
		}
		return false;
	}
}

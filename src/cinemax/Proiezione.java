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

/** Rappresenta una proiezione cinematografica programmata dal cinema
 * La classe memorizza il film proiettato, la data e l'ora della proiezione e il prezzo del biglietto
 * @author Arianna Necchio
 * @author Gaia Galimberti
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
 * @param <anno> anno di uscita
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
* @throws <ProiezioneNonEsistenteException> se la proiezione ricercata non esiste.
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

/** Ricerca una o più proiezioni nel file csv in base ai criteri di ricerca specificati.
* Il metodo riceve in argomento dati di tipo String, LocalDateTime e double.
 * @param <arg> criterio utilizzato per la ricerca
 * @return le proiezioni trovate
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	public static Proiezione cercaProiezione(Object arg) throws IOException{
		try{
			FileReader frd = new FileReader("data/proiezioni.csv");
			BufferedReader brd = new BufferedReader(frd);
			if(arg instanceof LocalDateTime){
				LocalDateTime p = (LocalDateTime) arg;
				Proiezione.getProiezione(p);
			}else{
				String riga;
				while((riga = brd.readLine()) != null){
					String[] dati = riga.split(","); 
					if((dati[1].equals(arg)) || (dati[2].equals(arg)) || (dati[3].equals(arg)) || (dati[4]==arg) || (dati[5]==arg) || (dati[6]==arg) || (dati[7]==arg))
						return new Proiezione(LocalDateTime.parse(dati[0]), dati[1], dati[2], dati[3], Integer.parseInt(dati[4]), Integer.parseInt(dati[5]), Integer.parseInt(dati[6]), Double.parseDouble(dati[7]));
				}
			}
			brd.close();
			frd.close();
		}catch(Exception e){
			System.out.println("Criterio inserito non valido");
		}	
		return null;
	}

	/** Ricerca le proiezioni comprese in un determinato intervallo di date.
 * @param <dataInizio> data iniziale dell'intervallo
 * @param <dataFine> data finale dell'intervallo
 * @throws <IOException> se si verifica un errore durante la lettura del file
 */
	public static Proiezione cercaProiezione(LocalDate dataInizio, LocalDate dataFine) throws IOException{
		FileReader frd = new FileReader("data/proiezioni.csv");
		BufferedReader brd = new BufferedReader(frd);
		String riga;
		Proiezione trovata = null;
		try{
			try{
			while((riga = brd.readLine()) != null) {
				String[] dati = riga.split(",");
				try{
				String d = dati[0].substring(0, 10);
				LocalDate data = LocalDate.parse(d);
				
					if((dataInizio.isBefore(data)) && (dataFine.isAfter(data))){
						DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						trovata = new Proiezione(LocalDateTime.parse(dati[0], formato), dati[1], dati[2], dati[3], Integer.parseInt(dati[4]), Integer.parseInt(dati[5]), Integer.parseInt(dati[6]), Double.parseDouble(dati[7]));
					}
				}catch(Exception e){
							System.out.println("Errore");
				}	
			}
			}catch(Exception e){
					System.out.println("data non valida");
			}
			brd.close();
			frd.close();
		}catch(Exception e){
			System.out.println("Formato della data inserita non corretto");
		}
		return trovata;
	}

	/** Restituisce una rappresentazione testuale della proiezione.
 * @param <args> criteri utilizzati per individuare la proiezione
 * @return una stringa contenente le informazioni della proiezione
 */
	public String visualizzaProiezione(Object... args) throws IOException{
		Proiezione p = Proiezione.cercaProiezione(args);
		return p.toString();
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

	/** Confronta l'oggetto corrente con un altro oggetto per verificarne l'uguaglianza.
 * @param <obj> oggetto da confrontare con la proiezione corrente
 * @return {@code true} se i due oggetti sono considerati uguali, {@code false} altrimenti
 * @throws <RuntimeException> se si verifica un errore durante il confronto
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

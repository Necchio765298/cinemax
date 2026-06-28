//package bin;
import java.time.LocalDateTime;
import java.io.Console;

public class Proiezione {
    private Film film;
    private LocalDateTime dataOra;
    private double prezzoBiglietto;

    public Proiezione(Film film, LocalDateTime dataOra , double prezzoBiglietto){
        this.film = film;
        this.dataOra = dataOra ;
        this.prezzoBiglietto=prezzoBiglietto;
    }

    public Film getFilm(){
        return film;
    }

    public LocalDateTime getDataOra(){
        return dataOra;
    }

    public double getPrezzoBiglietto(){
        return prezzoBiglietto;
    }

    public void setDataOra(LocalDateTime dataOra){
        this.dataOra=dataOra;
    }

    public void setPrezzoBiglietto(double prezzoBiglietto){
        this.prezzoBiglietto = prezzoBiglietto;
    }

    public String toString(){
		/*
		Console cons = System.console();
		String titolo = cons.readLine("inserire il titolo");
		String genere = cons.readLine("inserire il genere del film");
		String regista = cons.readLine("inserire il regista");
		String Anno = cons.readLine("inserire l'anno");
		int anno = Integer.parseInt(Anno);
		String DurataMinuti = cons.readLine("inserire la durata del film");
		int durataMinuti = Integer.parseInt(DurataMinuti);
		String EtaMinima = cons.readLine("inseire l'età minima consentita");
		int etaMinima = Integer.parseInt(EtaMinima);
		Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
		*/
        return dataOra+ ","+ film.toString()+ "," +prezzoBiglietto;
    }
	


}

package bin;
import java.time.LocalDateTime;


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

    @Override
    public String toString(){
	String titolo = System.in("inserire il titolo");
	String genere = System.in("inserire il genere del film");
	String regista = System.in("inserire il regista");
	int anno = System.in("inserire l'anno");
	int durataMinuti = System.in("inserire la durata del film");
	int etaMinima = System.in("inseire l'età minima consentita");
	Film film = new Film(String titolo,String genere,String registra,int anno,int durataMinuti,int etaMinima);
        return dataOra+ ","+ film.toString()+ "," +prezzoBiglietto;
    }



}

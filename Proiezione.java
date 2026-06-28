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
        return dataOra+ ","+ film.toString()+ "," +prezzoBiglietto;
    }
	


}

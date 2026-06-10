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
        return film.getTitolo()+"\n Data: "+dataOra.toLocalDate()+"\n Ora: "+dataOra.toLocalTime()+"\n Prezzo: "+getPrezzoBiglietto()+"€";
    }



}

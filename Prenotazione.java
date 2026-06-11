package bin;
import java.util.UUID;

public class Prenotazione {

    private String codice;
    private Utente utente;
    private Proiezione proiezione;
    private int numeroBiglietto;
    
    public Prenotazione(Utente utente , Proiezione proiezione , int numeroBiglietto){
        this.codice = UUID.randomUUID().toString().substring(0,8).toUpperCase();
        this.utente = utente;
        this.proiezione=proiezione;
        this.numeroBiglietto=numeroBiglietto;
    }

    public void setProiezione(Proiezione proiezione) {
        this.proiezione = proiezione;
    }

    public void setNumeroBiglietto(int numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }

    public String getCodice() {
        return codice;
    }

    public Utente getUtente() {
        return utente;
    }

    public Proiezione getProiezione() {
        return proiezione;
    }

    public int getNumeroBiglietto() {
        return numeroBiglietto;
    }


    public double getCostoTotale(){
        return proiezione.getPrezzoBiglietto()*numeroBiglietto;
    }

    public String toString() {
        return utente.getNome() +"," + utente.getCognome() +"," + proiezione.getFilm().getTitolo() +"," + proiezione.getDataOra() +","+ numeroBiglietto + ","+ codice;
    }





}

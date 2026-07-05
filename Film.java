//package bin;
/**
 * Rappresenta un film presente nel catalogo del cinema.
 * La classe memorizza le informazioni principali di ciascun film.
 *
 *  @author
 */

public class Film {
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

    /**
 * Costruisce un nuovo film.
 *
 * @param titolo titolo del film
 * @param genere genere del film
 * @param regista regista del film
 * @param anno anno di uscita
 * @param durataMinuti durata del film in minuti
 * @param etaMinima età minima consigliata
 */

    public Film(String titolo,String genere,String regista,int anno,int durataMinuti,int etaMinima){
        this.titolo=titolo;
        this.genere=genere;
        this.regista=regista;
        this.anno=anno;
        this.durataMinuti= durataMinuti;
        this.etaMinima=etaMinima;
    }
   
    /**
 * Restituisce il titolo del film.
 *
 * @return il titolo del film
 */
    public String getTitolo(){
        return titolo;
    }

    /**
 * Restituisce il genere del film.
 *
 * @return il genere del film
 */
    public String getGenere(){
        return genere;
    }

    /**
 * Restituisce il regista del film.
 *
 * @return il regista del film
 */
    public String getRegista(){
        return regista;
    }

    /**
 * Restituisce l'anno del film.
 *
 * @return l'anno del film
 */
    public int getAnno(){
        return anno;
    }

    /**
 * Restituisce la durata in minuti del film.
 *
 * @return la durata in minuti del film
 */
    public int getDurataMinuti(){
        return durataMinuti;
    }

    /**
 * Restituisce l'età minima del film.
 *
 * @return l'età minima del film
 */
    public int getEtaMinima(){
        return etaMinima;
    }

    /**
 * Restituisce una rappresentazione testuale dell'oggetto Film,
 * utilizzata per la visualizzazione delle informazioni e per
 * la memorizzazione dei dati nel file CSV.
 *
 * @return la rappresentazione testuale del film
 */
    @Override
    public String toString(){
        return " \" " +titolo + " \" " +"," +  genere +"," + " \" "+regista + " \" " +","+ anno +"," + durataMinuti + "," + etaMinima;

    }


}

package bin;

public class Film {
    private String titolo ;
    private String genere;
    private String registra;
    private int anno;
    private int durataMinuti;
    private int etaMinima;

    public Film(String titolo,String genere,String registra,int anno,int durataMinuti,int etaMinima){
        this.titolo=titolo;
        this.genere=genere;
        this.registra=registra;
        this.anno=anno;
        this.durataMinuti= durataMinuti;
        this.etaMinima=etaMinima;
    }
    
    public String getTitolo(){
        return titolo;
    }

    public String getGenere(){
        return genere;
    }

    public String getRegista(){
        return registra;
    }

    public int getAnno(){
        return anno;
    }

    public int getDurataMinuti(){
        return durataMinuti;
    }

    public int getEtaMinima(){
        return etaMinima;
    }

    @Override
    public String toString(){
        return "Titolo: "+titolo+"\n Genere: "+genere+"\n Registra: "+registra+"\n anno: "+anno+"\n durataMinuti: "+durataMinuti+"\n etaMinima: "+etaMinima;
    }


}

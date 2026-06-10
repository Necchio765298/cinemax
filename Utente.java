package bin;
import java.time.LocalDate;

public class Utente {
    
    private String nome ;
    private String cognome;
    private String username;
    private String password;
    private LocalDate dataNascita;
    private String domicilio;
    private String ruolo;



    public Utente(String nome , String cognome , String username , String password , LocalDate dataNascita , String domicilio , String ruolo ){
        this.nome=nome;
        this .cognome = cognome ;
        this.username = username ;
        this.password=password;
        this.dataNascita=dataNascita;
        this.domicilio=domicilio;
        this.ruolo=ruolo;
    }



    public String getNome() {
        return nome;
    }



    public String getCognome() {
        return cognome;
    }



    public String getUsername() {
        return username;
    }



    public String getPassword() {
        return password;
    }



    public LocalDate getDataNascita() {
        return dataNascita;
    }



    public String getDomicilio() {
        return domicilio;
    }



    public String getRuolo() {
        return ruolo;
    }

    @Override
    public String toString(){
        return "nome : "+ nome +" "+cognome+"\n ruolo : "+ruolo+"\n Domicilio : "+domicilio;
    }

}

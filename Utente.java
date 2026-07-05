//package bin;
import java.time.LocalDate;

/**
 * Rappresenta un utente registrato all'interno dell'applicazione.
 * La classe memorizza le informazioni anagrafiche, le credenziali
 * di accesso e il ruolo associato all'utente.
 * @author 
 */
public class Utente {
    /** Nome dell'utente. */
private String nome;
/** Cognome dell'utente. */
private String cognome;
/** Username utilizzato per l'accesso. */
private String username;
/** Password dell'utente. */
private String password;
/** Data di nascita dell'utente. */
private LocalDate dataNascita;
/** Domicilio dell'utente. */
private String domicilio;
/** Ruolo dell'utente all'interno dell'applicazione. */
private String ruolo;

/**
 * Costruisce un nuovo utente.
 * @param nome nome dell'utente
 * @param cognome cognome dell'utente
 * @param username username dell'utente
 * @param password password dell'utente
 * @param dataNascita data di nascita dell'utente
 * @param domicilio domicilio dell'utente
 * @param ruolo ruolo dell'utente
 */
    public Utente(String nome , String cognome , String username , String password , LocalDate dataNascita , String domicilio , String ruolo ){
        this.nome=nome;
        this .cognome = cognome ;
        this.username = username ;
        this.password=password;
        this.dataNascita=dataNascita;
        this.domicilio=domicilio;
        this.ruolo=ruolo;
    }


/**
 * Restituisce il nome dell'utente.
 * @return il nome dell'utente
 */
    public String getNome() {
        return nome;
    }


/**
 * Restituisce il cognome dell'utente.
 * @return il cognome dell'utente
 */
    public String getCognome() {
        return cognome;
    }


/**
 * Restituisce lo username dell'utente.
 * @return lo username dell'utente
 */
    public String getUsername() {
        return username;
    }


/**
 * Restituisce la password dell'utente.
 * @return la password dell'utente
 */
    public String getPassword() {
        return password;
    }


/**
 * Restituisce la data di nascita dell'utente.
 * @return la data di nascita dell'utente
 */
    public LocalDate getDataNascita() {
        return dataNascita;
    }


/**
 * Restituisce il domicilio dell'utente.
 * @return il domicilio dell'utente
 */
    public String getDomicilio() {
        return domicilio;
    }


/**
 * Restituisce il ruolo associato all'utente.
 * @return il ruolo dell'utente
 */
    public String getRuolo() {
        return ruolo;
    }

	/**
 * Restituisce una rappresentazione testuale dell'oggetto Utente,
 * utilizzata per la visualizzazione delle informazioni e per
 * la memorizzazione dei dati nel file csv.
 * @return una stringa contenente i dati dell'utente
 */
    @Override
    public String toString(){
		return nome + ","+ cognome + ","+ username+"," + password +"," + dataNascita +"," +domicilio +"," +ruolo;
    }

}

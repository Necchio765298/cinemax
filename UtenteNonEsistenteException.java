package cinemax;


public class UtenteNonEsistenteException extends Exception{
	
	public UtenteNonEsistenteException(long id){
		System.out.println("L'utente con ID " + id+ " non esiste");
	}
}
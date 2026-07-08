package cinemax;

public class ProiezioneNonEsistenteException extends Exception{

	public ProiezioneNonEsistenteException(LocalDateTime ldt){
		System.out.println("La proiezione con data e ora " + ldt+ " non esiste");
	}
}
//package bin;

import java.io.Console;
import java.time.LocalDate;
import java.io.IOException;
//import java.util.List;

public class CineMax {

	public static void main(String[] args) throws IOException {
		Console cons = System.console();
		int scelta;

			function_benvenuto: {
				System.out.println("== Benvenuto su CineMax ==");
				System.out.println("1. Login");
				System.out.println("2. Registrati");
				System.out.println("3. Continua come Guest");
				System.out.println("0. Esci");
				String Scelta = cons.readLine("Scelta: ");
				scelta = Integer.parseInt(Scelta);
				String ruoloUtente;
				String username;
				String password;
				
				switch(scelta){
					case 1:
						function_login: {
							username = cons.readLine("inserire il proprio nome utente: ");
							password = cons.readLine("inserire la password: ");
							Login login = new Login(username, password);
							boolean accesso;
							accesso = Login.login(login);
							if(accesso == true){
								System.out.print("Accesso consentito!");
								ruoloUtente = Login.ruolo(login, accesso);	//ruoloUtente serve per sapere il ruolo dell'utente loggato e per accedere alle sue funzionalità
									//creare il menu con le funzionalità di ogni utente
								switch(ruoloUtente){
									case "Cliente":
											String exit;
											int alternativa;
										do{	
											function_cliente:{	
												System.out.println("cosa si desidera fare?");
												System.out.println("1. creare una nuova prenotazione");
												System.out.println("2. visualizzare le proprie prenotazioni");
												System.out.println("3. modificare una prenotazione");
												System.out.println("4. eliminare una prenotazione");
												String Alternativa = cons.readLine("Scelta: ");
												alternativa = Integer.parseInt(Alternativa);
											
												if(alternativa == 1){
													//crea nuova prenotazione
													System.out.println("quanti biglietti si desidera acquistare?");
													String Biglietti = cons.readLine("Numero: ");
													int biglietti = Integer.parseInt(Biglietti);
													menuCliente.creaPrenotazione(biglietti);
												
													System.out.println("restare loggati come cliente? Digitare S per rimanere loggati, N per il logout");
													exit = cons.readLine();
													if(exit == "S")
														break function_cliente;
													else if(exit == "N")
														break function_benvenuto;
												
												}else if(alternativa == 2){
													//visualizza le prenotazioni
													menuCliente.visualizzaPrenotazione();
													
													System.out.println("restare loggati come cliente? Digitare S per rimanere loggati, N per il logout");
													exit = cons.readLine();
													if(exit == "S")
														break function_cliente;
													else if(exit == "N")
														break function_benvenuto;
												}else if(alternativa == 3){
													//modifica una prenotazione
													
												}else if(alternativa == 4){
													//elimina una prenotazione
												}
											}
										}while(alternativa != 1 && alternativa != 2 && alternativa != 3 && alternativa != 4 );	
										break;
									case "Bigliettaio":
										do{
											String exit;
											function_bigliettaio:{
												System.out.println("cosa si desidera fare?");
												System.out.println("1. cercare una prenotazione");
												System.out.println("2. visualizzare una prenotazione");
												String Alternativa = cons.readLine("Scelta: ");
												alternativa = Integer.parseInt(Alternativa);
											
												if(alternativa == 1){
													//cerca una prenotazione
													System.out.println("come cercare la prenotazione?");
													System.out.println("1. codice prenotazione");
													System.out.println("2. nome e cognome cliente");
													System.out.println("3. titolo film");
													System.out.println("4. intervallo date");
													String ricerca = cons.readLine("Scelta: ");

													switch(ricerca) {
														case "1":
															String codice = cons.readLine("Codice: ");
															menuBigliettaio.cercaPrenotazione(codice);
															break;
														case "2":
															String nome = cons.readLine("Nome: ");
															String cognome = cons.readLine("Cognome: ");
															menuBigliettaio.cercaPrenotazione(nome, cognome);
															break;
														case "3":
															String titolo = cons.readLine("Titolo: ");
															menuBigliettaio.cercaPrenotazione(titolo);
															break;
														case "4":
															LocalDateTime inizio = cons.readLine("data di inizio: ");
															LocalDateTime fine = cons.readLine("data di fine: ");
															menuBigliettaio.cercaPrenotazione(inizio, fine);
															break;
													}
													System.out.println("restare loggati come cliente? Digitare S per rimanere loggati, N per il logout");
													exit = cons.readLine();
													if(exit == "S")
														break function_bigliettaio;
													else if(exit == "N")
														break function_benvenuto;
													} 
												   else if(alternativa == 2){
													//visualizza una prenotazione
													   menuBigliettaio.visualizzaPrenotazione();
													   System.out.println("Film: ");
													   Proiezione.getFilm();
													   System.out.println("Codice prenotazione: ");
													   Prenotazione.getCodice();
													   System.out.println("Nome e cognome cliente: ");
													   Utente.getNome();
													   Utente.getCognome();
													   System.out.println("Data e ora proiezione: ");
													   Proiezione.getDataOra();
													   System.out.println("Numero Biglietti: ");
													   Prenotazione.getNumeroBiglietto();
													   System.out.println("Prezzo unitario: ");
													   Proiezione.getPrezzoBiglietto();
													   System.out.println("Prezzo totale: ");
													   Prenotazione.getCostoTotale();

													   System.out.println("restare loggati come cliente? Digitare S per rimanere loggati, N per il logout");
													exit = cons.readLine();
													if(exit == "S")
														break function_bigliettaio;
													else if(exit == "N")
														break function_benvenuto;
												   }
											}
										}while(alternativa != 1 && alternativa != 2);			   
										break;
										
									case "Proiezionista":
										do{
											String exit;
											function_proiezionista:{
												System.out.println("cosa si desidera fare?");
												System.out.println("1. aggiungere una proiezione");
												System.out.println("2. modificare una proiezione");
												System.out.println("3. eliminare una proiezione");
												String Alternativa = cons.readLine("Scelta: ");
												alternativa = Integer.parseInt(Alternativa);

												if(alternativa == 1){
													//aggiunge proiezione
													System.out.println("Inserire dati del film");
													System.out.println("Titolo: ");
													String titolo = cons.readLine();
													System.out.println("Genere: ");
													String genere = cons.readLine();
													System.out.println("Regista: ");
													String regista = cons.readLine();
													System.out.println("Anno: ");
													int anno = cons.readInt();
													System.out.println("Durata in minuti: ");
													int durataMinuti = cons.readInt();
													System.out.println("Età minima: ");
													int etaMinima = cons.readInt();
													Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
													System.out.println("Inserire dati della proiezione");
													System.out.println("Data e ora: ");
													LocalDateTime dataOra = cons.readLine();
													System.out.println("Prezzo biglietto: ");
													double prezzoBiglietto = cons.readDouble();
													Proiezione p = new Proiezione(film, dataOra, prezzoBiglietto);
													menuProiezioinista.aggiungiProiezione(p);

													System.out.println("restare loggati come cliente? Digitare S per rimanere loggati, N per il logout");
													exit = cons.readLine();
													if(exit == "S")
														break function_proiezionista;
													else if(exit == "N")
														break function_benvenuto;
												   }
												else if(alternativa == 2){
												//modifica proiezione
												System.out.println("Quale proiezione modificare? Inserire i dati della proiezione per ricercarla");
													System.out.println("Titolo: ");
													String titolo = cons.readLine();
													System.out.println("Genere: ");
													String genere = cons.readLine();
													System.out.println("Regista: ");
													String regista = cons.readLine();
													System.out.println("Anno: ");
													int anno = cons.readInt();
													System.out.println("Durata in minuti: ");
													int durataMinuti = cons.readInt();
													System.out.println("Età minima: ");
													int etaMinima = cons.readInt();
													Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
													System.out.println("Data e ora: ");
													LocalDateTime dataOra = cons.readLine();
													System.out.println("Prezzo biglietto: ");
													double prezzoBiglietto = cons.readDouble();
													Proiezione vecchiaProiezione = new Proiezione(film, dataOra, prezzoBiglietto);
													System.out.println("Inserire ora i nuovi dati della proiezione");
													System.out.println("Titolo: ");
													String titolo = cons.readLine();
													System.out.println("Genere: ");
													String genere = cons.readLine();
													System.out.println("Regista: ");
													String regista = cons.readLine();
													System.out.println("Anno: ");
													int anno = cons.readInt();
													System.out.println("Durata in minuti: ");
													int durataMinuti = cons.readInt();
													System.out.println("Età minima: ");
													int etaMinima = cons.readInt();
													Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
													System.out.println("Data e ora: ");
													LocalDateTime dataOra = cons.readLine();
													System.out.println("Prezzo biglietto: ");
													double prezzoBiglietto = cons.readDouble();
													Proiezione nuovaProiezione = new Proiezione(film, dataOra, prezzoBiglietto);
													menuProiezionista.modificaProiezione(vecchiaProiezione, nuovaProiezione);

												   System.out.println("restare loggati come cliente? Digitare S per rimanere loggati, N per il logout");
													exit = cons.readLine();
													if(exit == "S")
														break function_proiezionista;
													else if(exit == "N")
														break function_benvenuto;
												   } 
												else if(alternativa == 3){
												//elimina proiezione
												  System.out.println("Quale proiezione eliminare? Inserire i dati della proiezione per ricercarla");
													System.out.println("Titolo: ");
													String titolo = cons.readLine();
													System.out.println("Genere: ");
													String genere = cons.readLine();
													System.out.println("Regista: ");
													String regista = cons.readLine();
													System.out.println("Anno: ");
													int anno = cons.readInt();
													System.out.println("Durata in minuti: ");
													int durataMinuti = cons.readInt();
													System.out.println("Età minima: ");
													int etaMinima = cons.readInt();
													Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
													System.out.println("Data e ora: ");
													LocalDateTime dataOra = cons.readLine();
													System.out.println("Prezzo biglietto: ");
													double prezzoBiglietto = cons.readDouble();
													Proiezione p = new Proiezione(film, dataOra, prezzoBiglietto);
													menuProiezionista.eliminaProiezione(p);
												
													System.out.println("restare loggati come cliente? Digitare S per rimanere loggati, N per il logout");
													exit = cons.readLine();
													if(exit == "S")
														break function_proiezionista;
													else if(exit == "N")
														break function_benvenuto;
												   }		
									
											}		
										}while(alternativa != 1 && alternativa != 2 && alternativa != 3);
									break;
									case 0:
										System.out.println("Arrivederci!");
									default:
										System.out.println("scelta non valida");
										break function_benvenuto;
				}
				
			}		
		
	}

}

}

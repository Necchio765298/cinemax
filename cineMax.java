//package bin;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;
//import java.util.List;

public class cineMax {

	public static void main(String[] args) throws IOException {
		Console cons = System.console();
		int scelta;
			do{
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
					boolean accesso;
						do{
							username = cons.readLine("inserire il proprio nome utente: ");
							password = cons.readLine("inserire la password: ");
							Login login = new Login(username, password);
							
							accesso = Login.login(login);
							if(accesso == true){
								System.out.print("Accesso consentito!");
								System.out.println(" ");
								ruoloUtente = Login.ruolo(login, accesso);	//ruoloUtente serve per sapere il ruolo dell'utente loggato e per accedere alle sue funzionalità
									//creare il menu con le funzionalità di ogni utente
								int alternativa;
								switch(ruoloUtente){
									case "Cliente":
											String exit;
										do{	
											System.out.println("cosa si desidera fare?");
											System.out.println("1. creare una nuova prenotazione");
											System.out.println("2. visualizzare le proprie prenotazioni");
											System.out.println("3. modificare una prenotazione");
											System.out.println("4. eliminare una prenotazione");
											System.out.println("0. per uscire");
											String Alternativa = cons.readLine("Scelta: ");
											alternativa = Integer.parseInt(Alternativa);
										
											if(alternativa == 1){
												//crea nuova prenotazione
												System.out.println(" ");
												System.out.println("quanti biglietti si desidera acquistare?");
												String Biglietti = cons.readLine("Numero: ");
												int biglietti = Integer.parseInt(Biglietti);
												menuCliente.creaPrenotazione(biglietti);
											
											}else if(alternativa == 2){
												//visualizza le prenotazioni
												menuCliente.visualizzaPrenotazione();
												
											}else if(alternativa == 3){
												//modifica una prenotazione
												System.out.println(" ");
												System.out.println("Inserire i dati richiesti della prenotazione da modificare");
												System.out.println("Dati relativi all'utente");
												String nome = cons.readLine("Nome? ");
												String cognome = cons.readLine("Cognome?");
												username = cons.readLine("Username? ");
												password = cons.readLine("Password? ");
												String DataNascita = cons.readLine("Data di nascita nel formato AAAA-MM-GG");
												LocalDate dataNascita = LocalDate.parse(DataNascita);
												String domicilio = cons.readLine("Domicilio? ");
												String ruolo = "Cliente";
												Utente utente = new Utente(nome, cognome, username, password, dataNascita, domicilio, ruolo);
												System.out.println(" ");
												System.out.println("Dati relativi alla proiezione");
												
												String titolo = cons.readLine("Titolo: ");
												String genere = cons.readLine("Genere: ");
												String regista = cons.readLine("Regista: ");
												
												String Anno = cons.readLine("Anno: ");
												int anno = Integer.parseInt(Anno);
												
												String DurataMinuti = cons.readLine("Durata in minuti: ");
												int durataMinuti = Integer.parseInt(DurataMinuti);
												
												String EtaMinima = cons.readLine("Età minima: ");
												int etaMinima = Integer.parseInt(EtaMinima);
												
												Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
												
												String DataOra = cons.readLine("Data e ora della proiezione prenotata nel formato AAAA-MM-GGTHH-MM-SS ");
												LocalDateTime dataOra = LocalDateTime.parse(DataOra);
												
												String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
												double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
												Proiezione proiezione = new Proiezione(film, dataOra, prezzoBiglietto);
												
												System.out.println(" ");
												String NumeroBiglietto = cons.readLine("Quanti biglietti ha acquistato? ");
												int numeroBiglietto = Integer.parseInt(NumeroBiglietto);
												
												System.out.println(" ");
												Prenotazione prenotazione = new Prenotazione(utente, proiezione, numeroBiglietto);
												
												menuCliente.modificaPrenotazione(prenotazione, dataOra);
												
											}else if(alternativa == 4){
												//elimina una prenotazione
												System.out.println(" ");
												System.out.println("Inserire i dati richiesti");
												System.out.println("Dati relativi all'utente");
												String nome = cons.readLine("Nome? ");
												String cognome = cons.readLine("Cognome?");
												username = cons.readLine("Username? ");
												password = cons.readLine("Password? ");
												String DataNascita = cons.readLine("Data di nascita nel formato AAAA-MM-GG");
												LocalDate dataNascita = LocalDate.parse(DataNascita);
												String domicilio = cons.readLine("Domicilio? ");
												String ruolo = "Cliente";
												Utente utente = new Utente(nome, cognome, username, password, dataNascita, domicilio, ruolo);
												
												System.out.println(" ");
												System.out.println("Dati relativi alla proiezione");
												String titolo = cons.readLine("Titolo: ");
												String genere = cons.readLine("Genere: ");
												String regista = cons.readLine("Regista: ");
												String Anno = cons.readLine("Anno: ");
												int anno = Integer.parseInt(Anno);
												String DurataMinuti = cons.readLine("Durata in minuti: ");
												int durataMinuti = Integer.parseInt(DurataMinuti);
												String EtaMinima = cons.readLine("Età minima: ");
												int etaMinima = Integer.parseInt(EtaMinima);
												Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
												
												String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
												LocalDateTime dataOra = LocalDateTime.parse(DataOra);
												String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
												double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
												Proiezione proiezione = new Proiezione(film, dataOra, prezzoBiglietto);
												
												System.out.println(" ");
												String NumeroBiglietto = cons.readLine("Inserire il numero dei biglietti acquistati per questa prenotazione ");
												int numeroBiglietto = Integer.parseInt(NumeroBiglietto);
												
												System.out.println(" ");
												Prenotazione prenotazione = new Prenotazione(utente, proiezione, numeroBiglietto);
												System.out.println("La prenotazione verrà eliminata");
												menuCliente.eliminaPrenotazione(prenotazione);
											}
										}while(alternativa != 0);	
										break;
									case "Bigliettaio":
										do{
												System.out.println(" ");
												System.out.println("cosa si desidera fare?");
												System.out.println("1. cercare una prenotazione");
												System.out.println("0. per uscire");
												String Alternativa = cons.readLine("Scelta: ");
												alternativa = Integer.parseInt(Alternativa);
											
												if(alternativa == 1){
													//cerca una prenotazione
													System.out.println(" ");
													System.out.println("come cercare la prenotazione?");
													System.out.println("1. codice prenotazione");
													System.out.println("2. nome e cognome cliente");
													System.out.println("3. titolo film");
													System.out.println("4. intervallo date");
													
													String ricerca = cons.readLine("Scelta: ");

													switch(ricerca) {
														case "1":
															System.out.println(" ");
															String codice = cons.readLine("Codice: ");
															menuBigliettaio.cercaPrenotazione(codice);
															break;
														case "2":
															System.out.println(" ");
															String nome = cons.readLine("Nome: ");
															String cognome = cons.readLine("Cognome: ");
															String user = nome + "," + cognome;
															menuBigliettaio.cercaPrenotazione(user);
															break;
														case "3":
															System.out.println(" ");
															String titolo = cons.readLine("Titolo: ");
															menuBigliettaio.cercaPrenotazione(titolo);
															break;
														case "4":
															System.out.println(" ");
															String Inizio = cons.readLine("data di inizio nel formato aaaa-mm-ggThh:mm:ss ");
															LocalDateTime inizio = LocalDateTime.parse(Inizio);
															String Fine = cons.readLine("data di fine nel formato aaaa-mm-ggThh:mm:ss ");
															LocalDateTime fine = LocalDateTime.parse(Fine);
															menuBigliettaio.cercaPrenotazione(inizio, fine);
															break;
														
													}
														
												} 
												   
											
										}while(alternativa != 0);			   
										break;
									case "Proiezionista":
										do{
												System.out.println(" ");
												System.out.println("cosa si desidera fare?");
												System.out.println("1. aggiungere una proiezione");
												System.out.println("2. modificare una proiezione");
												System.out.println("3. eliminare una proiezione");
												System.out.println("0. per uscire");
												String Alternativa = cons.readLine("Scelta: ");
												alternativa = Integer.parseInt(Alternativa);

												if(alternativa == 1){
													//aggiunge proiezione
													
													menuProiezionista.aggiungiProiezione();
													System.out.println("La proiezione è stata aggiunta");

												   }
												else if(alternativa == 2){
												//modifica proiezione
													System.out.println(" ");
													/*
													System.out.println("Quale proiezione modificare? Inserire i dati della proiezione per ricercarla");
													String titolo = cons.readLine("Titolo: ");
													String genere = cons.readLine("Genere: ");
													String regista = cons.readLine("Regista: ");
													String Anno = cons.readLine("Anno: ");
													int anno = Integer.parseInt(Anno);
													String DurataMinuti = cons.readLine("Durata in minuti: ");
													int durataMinuti = Integer.parseInt(DurataMinuti);
													String EtaMinima = cons.readLine("Età minima: ");
													int etaMinima = Integer.parseInt(EtaMinima);
													Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
													
													String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
													LocalDateTime dataOra = LocalDateTime.parse(DataOra);
													String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
													double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
													Proiezione vecchiaProiezione = new Proiezione(film, dataOra, prezzoBiglietto);
													
													System.out.println(" ");
													
													System.out.println("Inserire ora i nuovi dati della proiezione");
													titolo = cons.readLine("Titolo: ");
													genere = cons.readLine("Genere: ");
													regista = cons.readLine("Regista: ");
													Anno = cons.readLine("Anno: ");
													anno = Integer.parseInt(Anno);
													DurataMinuti = cons.readLine("Durata in minuti: ");
													durataMinuti = Integer.parseInt(DurataMinuti);
													EtaMinima = cons.readLine("Età minima: ");
													etaMinima = Integer.parseInt(EtaMinima);
													film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
													
													DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
													dataOra = LocalDateTime.parse(DataOra);
													
													PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
													prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
													Proiezione nuovaProiezione = new Proiezione(film, dataOra, prezzoBiglietto);
													*/
													menuProiezionista.modificaProiezione();
													System.out.println("La proiezione è stata modificata");
												   } 
												else if(alternativa == 3){
												//elimina proiezione
												/*
													System.out.println(" ");
												    System.out.println("Quale proiezione eliminare? Inserire i dati della proiezione per ricercarla");
													String titolo = cons.readLine("Titolo: ");
													String genere = cons.readLine("Genere: ");
													String regista = cons.readLine("Regista: ");
													String Anno = cons.readLine("Anno: ");
													int anno = Integer.parseInt(Anno);
													String DurataMinuti = cons.readLine("Durata in minuti: ");
													int durataMinuti = Integer.parseInt(DurataMinuti);
													String EtaMinima = cons.readLine("Età minima: ");
													int etaMinima = Integer.parseInt(EtaMinima);
													Film film = new Film(titolo, genere, regista, anno, durataMinuti, etaMinima);
													
													String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
													LocalDateTime dataOra = LocalDateTime.parse(DataOra);
													
													String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
													double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
													Proiezione p = new Proiezione(film, dataOra, prezzoBiglietto);
												*/
													menuProiezionista.eliminaProiezione();
													System.out.println("La proiezione è stata eliminata");
												
												}		
											
										}while(alternativa != 0);
										break;
									default:
										break;
								}
							}else
								System.out.println(" ");
								System.out.println("Username, password non corretti, oppure non ci si è ancora registrati");
						}while(accesso == false);
						break;
					case 2:
						System.out.println(" ");
						System.out.println("Procedura di registrazione: inserire i dati richiesti");
						String nome =cons.readLine("nome? ");
						String cognome = cons.readLine("cognome? ");
						username = cons.readLine("username? ");
						password = cons.readLine("password? ");
						String DataNascita = cons.readLine("data di nascita? Richiesta nel formato aaaa-mm-gg ");
						LocalDate dataNascita = LocalDate.parse(DataNascita);
						String domicilio = cons.readLine("domicilio? ");
						String ruolo = cons.readLine("ruolo? scrivere Cliente, Bigliettaio o Proiezionista ");
						Utente nuovoUtente = new Utente(nome, cognome, username, password, dataNascita, domicilio, ruolo);
						csvReader.registraCliente(nuovoUtente);
						break;

					case 3:
						System.out.println(" ");
						System.out.println("Funzionalità di ricerca di una proiezione");
						
						System.out.println(" ");
						System.out.println("come cercare la proiezione?");
						System.out.println("1. data_ora_proiezione");
						System.out.println("2. titolo_film");
						System.out.println("3. genere");
						System.out.println("4. regista");
						System.out.println("5. anno");
						System.out.println("6. durata_minuti");
						System.out.println("7. eta_minima");
						System.out.println("8. prezzo_biglietto");
						System.out.println("9. intervallo di date");
						
						String ricerca = cons.readLine("Scelta: ");

						switch(ricerca) {
							case "1":
								System.out.println(" ");
								String dataora = cons.readLine("data_ora_proiezione nel formato aaaa-mm-gg hh:mm:ss ");
								csvReader.cercaProiezione(dataora);
								break;
							case "2":
								System.out.println(" ");
								String titolo = cons.readLine("titolo: ");
								csvReader.cercaProiezione(titolo);
								break;
							case "3":
								System.out.println(" ");
								String genere = cons.readLine("Genere: ");
								csvReader.cercaProiezione(genere);
								break;
							case "4":
								System.out.println(" ");
								String regista = cons.readLine("Regista: ");
								csvReader.cercaProiezione(regista);
								break;
							case "5":
								System.out.println(" ");
								String anno = "," + cons.readLine("Anno: ") +",";
								
								csvReader.cercaProiezione(anno);
								break;
							case "6":
								System.out.println(" ");
								String minuti = "," + cons.readLine("Durata minuti: ") +",";
								csvReader.cercaProiezione(minuti);
								break;
							case "7":
								System.out.println(" ");
								String eta = "," + cons.readLine("Età minima: ")+ ",";
								csvReader.cercaProiezione(eta);
								break;
							case "8":
								System.out.println(" ");
								String prezzo = "," +cons.readLine("Prezzo biglietto: ")+ ",";
								csvReader.cercaProiezione(prezzo);
								break;
							case "9":
								System.out.println(" ");
								String Inizio = cons.readLine("data di inizio nel formato aaaa-mm-gg: ");
								LocalDate inizio = LocalDate.parse(Inizio);
								String Fine = cons.readLine("data di fine nel formato aaaa-mm-gg: ");
								LocalDate fine = LocalDate.parse(Fine);
								csvReader.cercaProiezione(inizio, fine);
								break;
						}
						break;
					default:
						break;
				}
			}while(scelta != 0);
				System.out.println(" ");
				System.out.println("Arrivederci!");
	}

}

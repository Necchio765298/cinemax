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
								int alternativa;
								switch(ruoloUtente){
									case "Cliente":
											String exit;
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
													System.out.print(" ");
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
													
													
													String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GG HH-MM-SS ");
													LocalDateTime dataOra = LocalDateTime.parse(DataOra);
													
													String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
													double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
													Proiezione proiezione = new Proiezione(film, dataOra, prezzoBiglietto);
													
													System.out.println(" ");
													String NumeroBiglietto = cons.readLine("Quanti biglietti si desidera acquistare? ");
													int numeroBiglietto = Integer.parseInt(NumeroBiglietto);
													
													System.out.println(" ");
													System.out.println("Modifica prenotazione ");
													Prenotazione prenotazione = new Prenotazione(utente, proiezione, numeroBiglietto);
													String Data = cons.readLine("Inserire la data di modifica della prenotazione");
													LocalDateTime data = LocalDateTime.parse(Data);
													menuCliente.modificaPrenotazione(prenotazione, data);
													
												}else if(alternativa == 4){
													//elimina una prenotazione
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
													
													System.out.print(" ");
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
													
													String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GG HH-MM-SS ");
													LocalDateTime dataOra = LocalDateTime.parse(DataOra);
													String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
													double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
													Proiezione proiezione = new Proiezione(film, dataOra, prezzoBiglietto);
													
													System.out.println(" ");
													String NumeroBiglietto = cons.readLine("Inserire il numero dei biglietti acquistati pre questa prenotazione ");
													int numeroBiglietto = Integer.parseInt(NumeroBiglietto);
													
													Prenotazione prenotazione = new Prenotazione(utente, proiezione, numeroBiglietto);
													System.out.println("La prenotazione verrà eliminata");
													menuCliente.eliminaPrenotazione(prenotazione);
												}
											}
										}while(alternativa != 1 && alternativa != 2 && alternativa != 3 && alternativa != 4 );	
										break;
									case "Bigliettaio":
										do{
											
											int alternativa;
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
															String Inizio = cons.readLine("data di inizio: ");
															LocalDateTime inizio = LocalDateTime.parse(Inizio);
															String Fine = cons.readLine("data di fine: ");
															LocalDateTime fine = LocalDateTime.parse(Fine);
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
													   menuBigliettaio mb = new menuBigliettaio();
													   String visProiezione = mb.visualizzaPrenotazione();
													   System.out.println("Film: ");
													   Proiezione pro = new Proiezione();
													   Film film = p.getFilm();
													   System.out.println("Codice prenotazione: ");
													   Prenotazione pre = new Prenotazione();
													   pre.getCodice();
													   System.out.println("Nome e cognome cliente: ");
													   Utente u = new Utente();
													   u.getNome();
													   u.getCognome();
													   System.out.println("Data e ora proiezione: ");
													   pro.getDataOra();
													   System.out.println("Numero Biglietti: ");
													   pre.getNumeroBiglietto();
													   System.out.println("Prezzo unitario: ");
													   pro.getPrezzoBiglietto();
													   System.out.println("Prezzo totale: ");
													   pre.getCostoTotale();

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
											
											int alternativa;
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
													
													System.out.println("Inserire dati della proiezione");
													
													String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GG HH-MM-SS ");
													LocalDateTime dataOra = LocalDateTime.parse(DataOra);
													String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
													double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
													Proiezione p = new Proiezione(film, dataOra, prezzoBiglietto);
													
													menuProiezionista.aggiungiProiezione(p);

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
													
													String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GG HH-MM-SS ");
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
													
													DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GG HH-MM-SS ");
													dataOra = LocalDateTime.parse(DataOra);
													
													PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
													prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
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
													
													String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GG HH-MM-SS ");
													LocalDateTime dataOra = LocalDateTime.parse(DataOra);
													
													String PrezzoBiglietto = cons.readLine("Prezzo biglietto: ");
													double prezzoBiglietto = Double.parseDouble(PrezzoBiglietto);
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
									default:
										break;
								}
							}else{
								System.out.println("Nome utente o password non corretti oppure non si è stati ancora registrati");
								System.out.println("Inserire S per ritentare il login, N per tornare al menù di avvio");
								exit = cons.readLine();
								do{
									if(exit == "S")
										break function_login;
									else if(exit == "N")
										break function_benvenuto;
								}while(exit != "S" && exit != "N");
							}
						}	
					case 2:
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
						break function_benvenuto;

					case 3:
						System.out.println("Funzionalità di ricerca di una proiezione");
						System.out.println("Cercare proiezione per intervallo di date o per altri criteri di ricerca?");
						String ricerca = cons.readLine("Scrivere DATE per ricercare per intervallo di date, altrimenti scrivere ALTRO");
						if(ricerca == "DATE"){
							Object argomenti = cons.readLine("inserire titolo del film, genere, costo biglietto o combinazione dei criteri di ricerca");
							
							System.out.println("ecco le proiezioni trovate: ");
							csvReader.cercaProiezione(argomenti);
						}else if(ricerca == "ALTRO"){
							String DataInizio = cons.readLine("inserire la data di inizio ricerca nel formato aaaa-mm-gg");
							String DataFine = cons.readLine("inserire la data di fine ricerca nel formato aaaa-mm-gg");
							LocalDate dataInizio = LocalDate.parse(DataInizio);
							LocalDate dataFine = LocalDate.parse(DataFine);
							csvReader.cercaProiezione(dataInizio, dataFine);
						}else
							System.out.println("digitazione non valida");
						break function_benvenuto;

					case 0:
						System.out.println("Arrivederci!");
					default:
						System.out.println("scelta non valida");
						break function_benvenuto;
				}
			}

	}

}

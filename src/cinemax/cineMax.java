package cinemax;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;
import java.time.format.DateTimeFormatter;



/** Classe principale dell'applicazione
 * Contiene il metodo main(), responsabile dell'avvio del programma, della gestione del menu principale e dell'interazione con l'utente
 * In base alle operazioni selezionate richiama le funzionalità implementate nelle altre classi del progetto
 * @author Arianna Necchio
 * @author Gaia Galimberti
 */
public class cineMax {

	/** Avvia l'applicazione e gestisce il flusso principale del programma.
 * Visualizza il menu iniziale, acquisisce le scelte dell'utente, gestisce l'autenticazione e richiama le funzionalità disponibili per il ruolo selezionato.
 * @param <args> argomenti passati dalla riga di comando
 * @throws <IOException> se si verifica un errore durante la lettura o la scrittura dei file
 */
	public static void main(String[] args) throws IOException, UtenteNonEsistenteException {
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
					Utente u= null;
						do{
							username = cons.readLine("inserire il proprio nome utente: ");
							password = cons.readLine("inserire la password: ");
							username = username.trim();
							password = password.trim();
							Login login = new Login(username, password);
							u = Login.login(login);
								
							if(u != null){
								System.out.print("Accesso consentito!");
								System.out.println(" ");
								ruoloUtente = u.getRuolo();	//ruoloUtente serve per sapere il ruolo dell'utente loggato e per accedere alle sue funzionalità
									
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
												System.out.println("A che data e ora prenotare?");
												String DataOra = cons.readLine("Data e ora della proiezione da prenotare nel formato AAAA-MM-GG HH-MM-SS ");
												LocalDateTime dataOra = LocalDateTime.parse(DataOra, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
												menuCliente.creaPrenotazione(dataOra,u);
											
											}else if(alternativa == 2){
												//visualizza le prenotazioni
												System.out.println("Elenco delle proprie prenotazioni: ");
												long ident = u.getID();
												menuCliente.visualizzaPrenotazione(ident);
												
											}else if(alternativa == 3){
												//modifica una prenotazione
												System.out.println(" ");
												
												String vecchia = cons.readLine("Data e ora della proiezione prenotata nel formato AAAA-MM-GGTHH-MM-SS ");
												LocalDateTime dataVecchia = LocalDateTime.parse(vecchia);
												String nuova = cons.readLine("Data e ora della proiezione da prenotare nel formato AAAA-MM-GGTHH-MM-SS ");
												LocalDateTime dataNuova = LocalDateTime.parse(nuova);
												String codice = cons.readLine("Inserire il codice della prenotazione: ");
												menuCliente.modificaPrenotazione(dataVecchia, dataNuova, codice, u);
												
											}else if(alternativa == 4){
												//elimina una prenotazione
												String vecchia = cons.readLine("Data e ora della proiezione prenotata nel formato AAAA-MM-GGTHH-MM-SS ");
												LocalDateTime dataVecchia = LocalDateTime.parse(vecchia);
												String codice = cons.readLine("Inserire il codice della prenotazione: ");
												System.out.println("La prenotazione verrà eliminata");
												menuCliente.eliminaPrenotazione(dataVecchia, codice);
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
													System.out.println("1. data e ora");
													System.out.println("2. ID utente");
													System.out.println("3. nome cliente");
													System.out.println("4. cognome cliente");
													System.out.println("5. titolo film");
													System.out.println("6. numero biglietti prenotati");
													System.out.println("7. codice prenotazione");
													
													System.out.println("8. intervallo date");
													String ricerca = cons.readLine("Scelta: ");
													LocalDateTime dataFinta = LocalDateTime.parse("2000-01-01 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

													switch(ricerca) {
														case "1":
															System.out.println(" ");
															String data = cons.readLine("Data e ora nel formato AAAA-MM-GG HH-MM-SS: ");
															LocalDateTime dataOra = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
															menuBigliettaio.cercaPrenotazione(dataOra, 0, "", "", "", 0, "");
															break;
														case "2":
															System.out.println(" ");
															String id = cons.readLine("id utente: ");
															long ID = Long.parseLong(id);
															menuBigliettaio.cercaPrenotazione(dataFinta, ID, "", "", "", 0, "");
															break;
														case "3":
															System.out.println(" ");
															String nome = cons.readLine("Nome: ");
															menuBigliettaio.cercaPrenotazione(dataFinta, 0, nome, "", "", 0, "");
															break;	
														case "4":
															System.out.println(" ");
															String cognome = cons.readLine("Cognome: ");
															menuBigliettaio.cercaPrenotazione(dataFinta, 0, "", cognome, "", 0, "");
															break;		
														case "5":
															System.out.println(" ");
															String titolo = cons.readLine("Titolo: ");
															menuBigliettaio.cercaPrenotazione(dataFinta, 0, "", "", titolo, 0, "");
															break;
														case "6":
															System.out.println(" ");
															String Biglietti = cons.readLine("numero biglietti: ");
															int biglietti =Integer.parseInt(Biglietti);
															menuBigliettaio.cercaPrenotazione(dataFinta, 0, "", "", "", biglietti, "");
															break;
														case "7":
															System.out.println(" ");
															String codice = cons.readLine("Codice: ");
															menuBigliettaio.cercaPrenotazione(dataFinta, 0, "", "", "", 0, codice);
															break;
														
														case "8":
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
													menuProiezionista.aggiungiProiezione();
													System.out.println("La proiezione è stata aggiunta");
												   }
												else if(alternativa == 2){
													String DataOra = cons.readLine("Data e ora della proiezione da modificare nel formato AAAA-MM-GGTHH-MM-SS ");
													LocalDateTime dataOra = LocalDateTime.parse(DataOra);
													menuProiezionista.modificaProiezione(dataOra);
													System.out.println("La proiezione è stata modificata");
												   } 
												else if(alternativa == 3){
													String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GGTHH-MM-SS ");
													LocalDateTime dataOra = LocalDateTime.parse(DataOra);
													menuProiezionista.eliminaProiezione(dataOra);
													System.out.println("La proiezione è stata eliminata");
												}		
											
										}while(alternativa != 0);
										break;
									default:
										break;
								}
							}else{
								System.out.println(" ");
								System.out.println("Username, password non corretti, oppure non ci si è ancora registrati");
							}
						}while(u == null);
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
						Utente.registraUtente(nuovoUtente);
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
						Proiezione p= null;

						switch(ricerca) {
							case "1":
								System.out.println(" ");
								String dataora = cons.readLine("data_ora_proiezione nel formato aaaa-mm-gg hh:mm:ss ");
								LocalDateTime DataOra= LocalDateTime.parse(dataora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
								p = Proiezione.cercaProiezione(DataOra, " "," "," ",0,0,0,0);
								System.out.println("Proiezione trovata: "+ p.toString());
								break;
							case "2":
								System.out.println(" ");
								String titolo = cons.readLine("titolo: ");
								p = Proiezione.cercaProiezione(null, titolo, " ", " ",0,0,0,0);
								System.out.println("Proiezione trovata: "+ p.toString());
								break;
							case "3":
								System.out.println(" ");
								String genere = cons.readLine("Genere: ");
								p = Proiezione.cercaProiezione(null, " ", genere, " ",0,0,0,0);
								System.out.println("Proiezione trovata: "+ p.toString());
								break;
							case "4":
								System.out.println(" ");
								String regista = cons.readLine("Regista: ");
								p = Proiezione.cercaProiezione(null, " ", " ", regista, 0,0,0,0);
								System.out.println("Proiezione trovata: "+ p.toString());
								break;
							case "5":
								System.out.println(" ");
								String anno = cons.readLine("Anno: ");
								int Anno =Integer.parseInt(anno);
								Proiezione.cercaProiezione(null," "," "," ", Anno, 0,0,0);
								System.out.println("Proiezione trovata: "+ p.toString());
								break;
							case "6":
								System.out.println(" ");
								String minuti = cons.readLine("Durata minuti: ");
								int Minuti =Integer.parseInt(minuti);
								p = Proiezione.cercaProiezione(null," "," "," ",0, Minuti, 0,0);
								System.out.println("Proiezione trovata: "+ p.toString());
								break;
							case "7":
								System.out.println(" ");
								String eta = cons.readLine("Età minima: ");
								int Eta=Integer.parseInt(eta);
								p = Proiezione.cercaProiezione(null," "," "," ",0, 0, Eta,0);
								System.out.println("Proiezione trovata: "+ p.toString());
								break;
							case "8":
								System.out.println(" ");
								String prezzo =cons.readLine("Prezzo biglietto: ");
								Double Prezzo=Double.parseDouble(prezzo);
								p = Proiezione.cercaProiezione(null," "," "," ",0,0,0,Prezzo);
								System.out.println("Proiezione trovata: "+ p.toString());
								break;
							case "9":
								System.out.println(" ");
								String Inizio = cons.readLine("data di inizio nel formato aaaa-mm-gg: ");
								LocalDate inizio = LocalDate.parse(Inizio);
								String Fine = cons.readLine("data di fine nel formato aaaa-mm-gg: ");
								LocalDate fine = LocalDate.parse(Fine);
								p = Proiezione.cercaProiezione(inizio, fine);
								System.out.println("Proiezione trovata: "+ p.toString());
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

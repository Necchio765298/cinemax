package cinemax;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
												Prenotazione daRegistrare = menuCliente.creaPrenotazione(dataOra,u);
												Prenotazione.registraPrenotazione(daRegistrare);
											}else if(alternativa == 2){
												//visualizza le prenotazioni
												System.out.println("Elenco delle proprie prenotazioni: ");
												long ident = u.getID();
												LocalDateTime dataFinta = LocalDateTime.parse("2000-01-01 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
												ArrayList<Prenotazione> memo = menuBigliettaio.cercaPrenotazione(dataFinta, ident, "", "", "", 0, "");
												menuCliente.visualizzaPrenotazione(memo);
												
											}else if(alternativa == 3){
												//modifica una prenotazione
												System.out.println(" ");
												
												String vecchia = cons.readLine("Data e ora della proiezione prenotata nel formato AAAA-MM-GG HH-MM-SS ");
												LocalDateTime dataVecchia = LocalDateTime.parse(vecchia, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
												String nuova = cons.readLine("Data e ora della proiezione da prenotare nel formato AAAA-MM-GG HH-MM-SS ");
												LocalDateTime dataNuova = LocalDateTime.parse(nuova, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
												System.out.println("La prenotazione verrà modificata");
												menuCliente.modificaPrenotazione(dataVecchia, dataNuova, u);
												
											}else if(alternativa == 4){
												//elimina una prenotazione
												String vecchia = cons.readLine("Data e ora della proiezione prenotata nel formato AAAA-MM-GGTHH-MM-SS ");
												LocalDateTime dataVecchia = LocalDateTime.parse(vecchia, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
												String codice = cons.readLine("Inserire il codice della prenotazione: ");
												codice.toUpperCase();
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
													ArrayList<Prenotazione> memo = null;

													switch(ricerca) {
														case "1":
															System.out.println(" ");
															String DataOra = cons.readLine("Data e ora della proiezione nel formato AAAA-MM-GG HH-MM-SS ");
															LocalDateTime dataOra = LocalDateTime.parse(DataOra, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
															memo = menuBigliettaio.cercaPrenotazione(dataOra, 0, "", "", "", 0, "");
															menuBigliettaio.visualizzaPrenotazione(memo);
															break;
														case "2":
															System.out.println(" ");
															String id = cons.readLine("id utente: ");
															long ID = Long.parseLong(id);
															memo = menuBigliettaio.cercaPrenotazione(dataFinta, ID, "", "", "", 0, "");
															menuBigliettaio.visualizzaPrenotazione(memo);
															break;
														case "3":
															System.out.println(" ");
															String nome = cons.readLine("Nome: ");
															memo = menuBigliettaio.cercaPrenotazione(dataFinta, 0, nome, "", "", 0, "");
															menuBigliettaio.visualizzaPrenotazione(memo);
															break;	
														case "4":
															System.out.println(" ");
															String cognome = cons.readLine("Cognome: ");
															memo = menuBigliettaio.cercaPrenotazione(dataFinta, 0, "", cognome, "", 0, "");
															menuBigliettaio.visualizzaPrenotazione(memo);
															break;		
														case "5":
															System.out.println(" ");
															String titolo = cons.readLine("Titolo: ");
															memo = menuBigliettaio.cercaPrenotazione(dataFinta, 0, "", "", titolo, 0, "");
															menuBigliettaio.visualizzaPrenotazione(memo);
															break;
														case "6":
															System.out.println(" ");
															String Biglietti = cons.readLine("numero biglietti: ");
															int biglietti =Integer.parseInt(Biglietti);
															memo = menuBigliettaio.cercaPrenotazione(dataFinta, 0, "", "", "", biglietti, "");
															menuBigliettaio.visualizzaPrenotazione(memo);
															break;
														case "7":
															System.out.println(" ");
															String codice = cons.readLine("Codice: ");
															codice.toUpperCase();
															memo = menuBigliettaio.cercaPrenotazione(dataFinta, 0, "", "", "", 0, codice);
															menuBigliettaio.visualizzaPrenotazione(memo);
															break;
														
														case "8":
															System.out.println(" ");
															String Inizio = cons.readLine("data di inizio nel formato aaaa-mm-gg hh:mm:ss ");
															LocalDateTime inizio = LocalDateTime.parse(Inizio, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
															String Fine = cons.readLine("data di fine nel formato aaaa-mm-gg hh:mm:ss ");
															LocalDateTime fine = LocalDateTime.parse(Fine, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
															memo = menuBigliettaio.cercaPrenotazione(inizio, fine);
															menuBigliettaio.visualizzaPrenotazione(memo);
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
													Proiezione creata = menuProiezionista.creaProiezione();
													menuProiezionista.aggiungiProiezione(creata);
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
						String ruolo = null;
						do{
							String numeroscelta = cons.readLine("ruolo? digitare 1. Cliente, 2. Bigliettaio, 3. Proiezionista ");
							int numRuolo = Integer.parseInt(numeroscelta);
							if(numRuolo == 1)
								ruolo = "Cliente";
							else if(numRuolo == 2)
								ruolo = "Bigliettaio";
							else if(numRuolo == 3)
								ruolo = "Proiezionista";
						}while(ruolo != "Cliente" && ruolo != "Bigliettaio" && ruolo != "Proiezionista");
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
						LocalDateTime dataFinta = LocalDateTime.parse("2000-01-01 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						ArrayList<Proiezione> memo = null;
						
						switch(ricerca) {
							case "1":
								System.out.println(" ");
								boolean riprova;
								do{
								riprova = false;
								String dataora = cons.readLine("data_ora_proiezione nel formato aaaa-mm-gg hh:mm:ss ");
								LocalDateTime DataOra= null;
								boolean dateCorrect=true;
								
								
									do{
										try{
											DataOra=LocalDateTime.parse(dataora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
										}catch(Exception e){
											System.out.println("La data inserita non è corretta");
											dateCorrect=false;
										}
									}while(!dateCorrect);
									try{
										memo = Proiezione.cercaProiezione(DataOra, " "," "," ",0,0,99,0);
									}catch(ProiezioneNonEsistenteException ePro){
										System.out.println("Si desidera ricercare la proiezione? Digitare S/N");
										String risposta = cons.readLine().trim().toUpperCase();
										if(risposta.equals("S")){
											riprova = true;
										}
										continue;
									}
								Proiezione.visualizzaProiezione(memo);	
								}while(riprova == true);
								
								break;
							case "2":
								System.out.println(" ");
								
								do{
								riprova = false;
								String titolo = cons.readLine("titolo: ");
								
								try{
									memo = Proiezione.cercaProiezione(dataFinta, titolo, " ", " ",0,0,99,0);
								}catch(ProiezioneNonEsistenteException ePro){
										System.out.println("Si desidera ricercare la proiezione? Digitare S/N");
										String risposta = cons.readLine().trim().toUpperCase();
										if(risposta.equals("S")){
											riprova = true;
										}
										continue;
									}
								Proiezione.visualizzaProiezione(memo);	
								}while(riprova == true);
								
								break;
							case "3":
								System.out.println(" ");
								
								do{
								riprova = false;
								String genere = cons.readLine("Genere: ");
								
								try{
									memo = Proiezione.cercaProiezione(dataFinta, " ", genere, " ",0,0,99,0);
								}catch(ProiezioneNonEsistenteException ePro){
										System.out.println("Si desidera ricercare la proiezione? Digitare S/N");
										String risposta = cons.readLine().trim().toUpperCase();
										if(risposta.equals("S")){
											riprova = true;
										}
										continue;
									}
								Proiezione.visualizzaProiezione(memo);
								}while(riprova == true);
								
								break;
							case "4":
								System.out.println(" ");
								
								do{
								riprova = false;
								String regista = cons.readLine("Regista: ");
								
								try{
									memo = Proiezione.cercaProiezione(dataFinta, " ", " ", regista, 0,0,99,0);
								}catch(ProiezioneNonEsistenteException ePro){
										System.out.println("Si desidera ricercare la proiezione? Digitare S/N");
										String risposta = cons.readLine().trim().toUpperCase();
										if(risposta.equals("S")){
											riprova = true;
										}
										continue;
									}
								Proiezione.visualizzaProiezione(memo);	
								}while(riprova== true);
								
								break;
							case "5":
								System.out.println(" ");
								
								do{
								riprova = false;
								String anno = cons.readLine("Anno: ");
								int Anno =Integer.parseInt(anno);
								
								
								try{
									memo = Proiezione.cercaProiezione(dataFinta," "," "," ", Anno, 0,99,0);
								}catch(ProiezioneNonEsistenteException ePro){
										System.out.println("Si desidera ricercare la proiezione? Digitare S/N");
										String risposta = cons.readLine().trim().toUpperCase();
										if(risposta.equals("S")){
											riprova = true;
										}
										continue;
									}
								Proiezione.visualizzaProiezione(memo);
								}while(riprova==true);
								
								break;
							case "6":
								System.out.println(" ");
								
								do{
								riprova = false;
								String minuti = cons.readLine("Durata minuti: ");
								int Minuti =Integer.parseInt(minuti);
								
								
								try{
									memo = Proiezione.cercaProiezione(dataFinta," "," "," ",0, Minuti, 99,0);
								}catch(ProiezioneNonEsistenteException ePro){
										System.out.println("Si desidera ricercare la proiezione? Digitare S/N");
										String risposta = cons.readLine().trim().toUpperCase();
										if(risposta.equals("S")){
											riprova = true;
										}
										continue;
									}
								Proiezione.visualizzaProiezione(memo);
								}while(riprova==true);
								
								break;
							case "7":
								System.out.println(" ");
								
								do{
								riprova = false;
								String eta = cons.readLine("Età minima: ");
								int Eta=Integer.parseInt(eta);
								
								
								try{
									memo = Proiezione.cercaProiezione(dataFinta," "," "," ",0, 0, Eta,0);
								}catch(ProiezioneNonEsistenteException ePro){
										System.out.println("Si desidera ricercare la proiezione? Digitare S/N");
										String risposta = cons.readLine().trim().toUpperCase();
										if(risposta.equals("S")){
											riprova = true;
										}
										continue;
									}
								Proiezione.visualizzaProiezione(memo);
								}while(riprova==true);
								
								break;
							case "8":
								System.out.println(" ");
								
								do{
								riprova = false;
								String prezzo =cons.readLine("Prezzo biglietto: ");
								Double Prezzo=Double.parseDouble(prezzo);
								
								
								try{
								memo = Proiezione.cercaProiezione(dataFinta," "," "," ",0,0,99,Prezzo);
								}catch(ProiezioneNonEsistenteException ePro){
										System.out.println("Si desidera ricercare la proiezione? Digitare S/N");
										String risposta = cons.readLine().trim().toUpperCase();
										if(risposta.equals("S")){
											riprova = true;
										}
										continue;
									}
								Proiezione.visualizzaProiezione(memo);
								}while(riprova==true);
								
								break;
							case "9":
								System.out.println(" ");
								
								do{
								riprova = false;
								String Inizio = cons.readLine("data di inizio nel formato aaaa-mm-gg: ");
								LocalDate inizio = LocalDate.parse(Inizio);
								String Fine = cons.readLine("data di fine nel formato aaaa-mm-gg: ");
								LocalDate fine = LocalDate.parse(Fine);
								
								
								try{
									memo = Proiezione.cercaProiezione(inizio, fine);
								}catch(ProiezioneNonEsistenteException ePro){
										System.out.println("Si desidera ricercare la proiezione? Digitare S/N");
										String risposta = cons.readLine().trim().toUpperCase();
										if(risposta.equals("S")){
											riprova = true;
										}
										continue;
									}
								Proiezione.visualizzaProiezione(memo);
								}while(riprova== true);
								
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

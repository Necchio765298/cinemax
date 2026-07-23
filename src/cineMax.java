package cinemax;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;
//import java.util.List;

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
					boolean accesso;
						do{
							username = cons.readLine("inserire il proprio nome utente: ");
							password = cons.readLine("inserire la password: ");
							Login login = new Login(username, password);
							accesso = Login.login(login);
								
							long id = Login.getIdUtente(login);
							if(accesso == true){
								System.out.print("Accesso consentito!");
								System.out.println(" ");
								ruoloUtente = Login.ruolo(login, accesso);	//ruoloUtente serve per sapere il ruolo dell'utente loggato e per accedere alle sue funzionalità
									
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
												String DataOra = cons.readLine("Data e ora della proiezione da prenotare nel formato AAAA-MM-GGTHH-MM-SS ");
												LocalDateTime dataOra = LocalDateTime.parse(DataOra);
												menuCliente.creaPrenotazione(dataOra);
											
											}else if(alternativa == 2){
												//visualizza le prenotazioni
												System.out.println("Elenco delle proprie prenotazioni: ");
												menuCliente.visualizzaPrenotazione(id);
												
											}else if(alternativa == 3){
												//modifica una prenotazione
												System.out.println(" ");
												
												String vecchia = cons.readLine("Data e ora della proiezione prenotata nel formato AAAA-MM-GGTHH-MM-SS ");
												LocalDateTime dataVecchia = LocalDateTime.parse(vecchia);
												String nuova = cons.readLine("Data e ora della proiezione da prenotare nel formato AAAA-MM-GGTHH-MM-SS ");
												LocalDateTime dataNuova = LocalDateTime.parse(nuova);
												String codice = cons.readLine("Inserire il codice della prenotazione: ");
												menuCliente.modificaPrenotazione(dataVecchia, dataNuova, codice);
												
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

						switch(ricerca) {
							case "1":
								System.out.println(" ");
								String dataora = cons.readLine("data_ora_proiezione nel formato aaaa-mm-gg hh:mm:ss ");
								Proiezione.cercaProiezione(dataora);
								break;
							case "2":
								System.out.println(" ");
								String titolo = cons.readLine("titolo: ");
								Proiezione.cercaProiezione(titolo);
								break;
							case "3":
								System.out.println(" ");
								String genere = cons.readLine("Genere: ");
								Proiezione.cercaProiezione(genere);
								break;
							case "4":
								System.out.println(" ");
								String regista = cons.readLine("Regista: ");
								Proiezione.cercaProiezione(regista);
								break;
							case "5":
								System.out.println(" ");
								String anno = "," + cons.readLine("Anno: ") +",";
								
								Proiezione.cercaProiezione(anno);
								break;
							case "6":
								System.out.println(" ");
								String minuti = "," + cons.readLine("Durata minuti: ") +",";
								Proiezione.cercaProiezione(minuti);
								break;
							case "7":
								System.out.println(" ");
								String eta = "," + cons.readLine("Età minima: ")+ ",";
								Proiezione.cercaProiezione(eta);
								break;
							case "8":
								System.out.println(" ");
								String prezzo = "," +cons.readLine("Prezzo biglietto: ")+ ",";
								Proiezione.cercaProiezione(prezzo);
								break;
							case "9":
								System.out.println(" ");
								String Inizio = cons.readLine("data di inizio nel formato aaaa-mm-gg: ");
								LocalDate inizio = LocalDate.parse(Inizio);
								String Fine = cons.readLine("data di fine nel formato aaaa-mm-gg: ");
								LocalDate fine = LocalDate.parse(Fine);
								Proiezione.cercaProiezione(inizio, fine);
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

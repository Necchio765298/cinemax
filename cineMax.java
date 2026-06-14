package bin;

import java.io.Console;
import java.time.LocalDate;
//import java.util.List;

public class CineMax {

public static void main(String[] args) {
	Console cons = System.console();
	int scelta;

	do {
		menu_benvenuto: {
			System.out.println("== Benvenuto su CineMax ==");
			System.out.println("1. Login");
			System.out.println("2. Registrati");
			System.out.println("3. Continua come Guest");
			System.out.println("0. Esci");
			String Scelta = cons.readLine("Scelta: ");
			scelta = Integer.parseInt(Scelta);
		}
		switch(scelta){
			case 1:
				function_login: {
					String username = cons.readLine("inserire il proprio nome utente: ");
					String password = cons.readLine("inserire la password: ");
					Login login = new Login(username, password);
					boolean accesso;
					accesso = Login.login(login);
					if(accesso == true){
						System.out.print("Accesso consentito!");
					}else{
						System.out.println("username o password non corretti");
					}
					String exit;
					do{
						System.out.println("Ritentare l'accesso? digitare 'S' per loggarsi; 'N' per tornare al menù di benvenuto");
						exit = cons.readLine();
						if(exit == "S")
							break menu_benvenuto;
						else if(exit == "N")
							break function_login;
					}while(exit != "S" || exit != "N");
				}
			case 2:
				System.out.println("Procedura di registrazione: inserire i dati richiesti");
				String nome =cons.readLine("nome? ");
				String cognome = cons.readLine("cognome? ");
				String username = cons.readLine("username? ");
				String password = cons.readLine("password? ");
				String DataNascita = cons.readLine("data di nascita? Richiesta nel formato aaaa-mm-gg");
				LocalDate dataNascita = LocalDate.parse(DataNascita);
				String domicilio = cons.readLine("domicilio? ");
				String ruolo = cons.readLine("ruolo? scrivere Cliente, Bigliettaio o Proiezionista");
				Utente nuovoUtente = new Utente(nome, cognome, username, password, dataNascita, domicilio, ruolo);
				csvReader.registraCliente(nuovoUtente);
				break;
			
			case 3:	//creare il menu con le funzionalità di ogni utente
				Object argomenti = cons.readLine("inserire titolo del film, genere, intervallo di date, costo bilgietto o combianzione dei criteri di ricerca");
				String proiezione = csvReader.cercaProiezione(argomenti).visualizzaProiezione();
				System.out.println("ecco la proiezione trovata: ");
				System.out.println(proiezione);
				break;
				
			case 0:
				System.out.println("Arrivederci!");
				break;
				
			default:
				System.out.println("scelta non valida");
				break menu_benvenuto;
		}		
	}while(scelta != 0);
		
	}

	
	
	}

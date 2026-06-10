package bin;

import java.util.Scanner;
import java.util.List;

public class CineMax {

public static void main(String[] args) {
	//Scanner scanner = new Scanner(System.in);
	int scelta;

	do {
		menu_benvenuto:
			System.out.println("== Benvenuto su CineMax ==");
			System.out.println("1. Login");
			System.out.println("2. Registrati");
			System.out.println("3. Continua come Guest");
			System.out.println("0. Esci");
			int scelta = System.in("Scelta: ");
			break;
			
		switch(scelta){
			case 1:
				function_login:
					String username = System.in("inserire il proprio nome utente: ");
					String password = System.in("inserire la password: ");
					Login login = new Login(username, password);
					boolean accesso;
					accesso.login(login);
					if(accesso == true)
						System.out.print("Accesso consentito!");
					else
						System.out.println("username o password non corretti");
					do{
						System.out.println("Ritentare l'accesso? digitare 'S' per loggarsi; 'N' per tornare al menù di benvenuto");
						char exit = System.in();
						if(exit == 'S')
							break menu_benvenuto;
						else if(exit == 'N')
							break function_login;
					}while(exit != 'S' || exit != 'N');
			
			case 2:
				System.out.println("Procedura di registrazione: inserire i dati richiesti");
				String nome = System.in("nome? ");
				String cognome = System.in("cognome? ");
				String username = System.in("username? ");
				String password = System.in("password? ");
				LocalDate dataNascita = System.in("data di nascita? Richiesta nel formato aaaa-mm-gg");
				String domicilio = System.in("domicilio? ");
				String ruolo = System.in("ruolo? scrivere Cliente, Bigliettaio o Proiezionista");
				Utente nuovoUtente = new Utente(nome, cognome, username, password, dataNascita, domicilio, ruolo);
				csvReader.registraCliente(nuovoUtente);
				break;
			
			case 3:	//creare il menu con le funzionalità di ogni utente
				Object argomenti = System.in("inserire titolo del film, genere, intervallo di date, costo bilgietto o combianzione dei criteri di ricerca");
				String proiezione = cercaProiezione(argomenti).visualizzaProiezione();
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

package bin;

import java.util.Scanner;
import java.util.List;

public class CineMax {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int scelta;

		do {
		System.out.println("\n== Benvenuto su CineMax ==");
		System.out.println("1. Login");
		System.out.println("2. Registrati");
		System.out.println("3. Continua come Guest");
		System.out.println("0. Esci");
		System.out.print("Scelta: ");

		scelta = scanner.nextInt();
		scanner.nextLine();

		if(scelta == 1){
			System.out.println("login da implementare");
		}
		else if(scelta == 2){
				System.out.println("registrazione da implementare");
		}
		else if(scelta == 3){
			menuGuest(scanner);
		}
		else if(scelta == 0){
			System.out.println("Arrivederci!");
		}
		else{
			System.out.println("scelta non valida");
		}

		} while(scelta != 0);
		scanner.close();
	}

	private static void menuGuest(Scanner scanner){
		int scelta;
		do{
		System.out.println("\n== Menu Guest ==");
		System.out.println("1. Cerca film");
		System.out.println("0. Indietro");
		System.out.print("Scelta: ");
		scelta = scanner.nextInt();
		scanner.nextLine();

		if(scelta == 1){
			cercaFilm(scanner);
		} else if(scelta == 0){
			System.out.println("torno al menu principale");
		} else {
			System.out.println("scelta non valida");
		}
		} while(scelta != 0);
	}

	private static void cercaFilm(Scanner scanner){
		System.out.print("Inserisci titolo film: ");
		String titolo = scanner.nextLine().toLowerCase();

		List<Proiezione> risultati = CsvReader.cercaPerTitolo("data/proiezioni.csv", titolo);

		if(risultati.isEmpty()){
			System.out.println("nessun film trovato");
			return;
		}

		for(int i=0; i<risultati.size(); i++){
			System.out.println((i+1) + ". " + risultati.get(i).getFilm().getTitolo()
				+ " " + risultati.get(i).getDataOra().toLocalDate());
		}

		System.out.print("seleziona numero per dettagli (0 per tornare): ");
		int sel = scanner.nextInt();
		scanner.nextLine();

		if(sel > 0 && sel <= risultati.size()){
			System.out.println(risultati.get(sel-1).toString());
		}
	}
}
//package bin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Login{
	//campi
	String username;
	String password;

	//costruttore
	public Login(String username, String password){
		this.username = username;
		this.password = password;
	}

	//metodi
	public String toString(){
		return username + "," + password;
	}
	
	public static boolean login(Login login) throws IOException{
		FileReader frd = new FileReader("../data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String persona;
		boolean accesso = false;
		try{
		while((persona = brd.readLine()) != null){
			
			if(persona.contains(login.toString()))
				accesso = true;
		}	
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("Un valore inserito non è nel formato valido");
		}
		return accesso;
	}
	
	public static String ruolo(Login login, boolean accesso) throws IOException{
		FileReader frd = new FileReader("../data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String ruolo = "non specificato";
		String persona;
		try{
		while((persona= brd.readLine()) != null){
			if(persona.contains(login.toString())){
				if(persona.contains("Cliente"))
					ruolo = "Cliente";
				else if(persona.contains("Bigliettaio"))
					ruolo = "Bigliettaio";
				else
					ruolo = "Proiezionista";
			}
		}
		brd.close();
		frd.close();
		}catch(Exception e){
			System.out.println("ruolo inserito in fase di registrazione diverso da  \" Cliente \" , \" Bigliettaio \", \"Proiezionista \" ");
		}
		return ruolo;
	}
}

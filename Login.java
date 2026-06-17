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
		while((persona = brd.readLine()) != null){
			
			if(persona.contains(login.toString()))
				brd.close();
				frd.close();
				return true;
			
		}		
		brd.close();
		frd.close();
		return false;
	}
	
	public static String ruolo(Login login) throws IOException{
		FileReader frd = new FileReader("../data/utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		String ruolo = "non specificato";
		String persona;
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
		return ruolo;
	}
}
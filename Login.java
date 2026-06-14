package bin;

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
		return username + ", " + password;
	}
	
	public static boolean login(Login login) throws IOException{
		FileReader frd = new FileReader("utenti.csv");
		BufferedReader brd = new BufferedReader(frd);
		
		while(brd.readLine() != null){
			if(brd.readLine().contains(login.toString()))
				brd.close();
				frd.close();
				return true;	
		}
		System.out.println("username o password non corretti");
		brd.close();
		frd.close();
		return false;
	}
}
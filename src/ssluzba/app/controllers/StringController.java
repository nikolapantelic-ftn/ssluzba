package ssluzba.app.controllers;

public class StringController {

	public static boolean proveriString(String string) {
		if(string.matches("([a-zA-Z0-9 ]+:[a-zA-Z0-9]+;?)+"))
				return true;
		else return false;
	}

}

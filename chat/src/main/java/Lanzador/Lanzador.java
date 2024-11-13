package Lanzador;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Aplicacion.Login;

public class Lanzador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login login = new Login();
		DateTimeFormatter solo_hora = DateTimeFormatter.ofPattern("HH:mm");

		//System.out.println(LocalDateTime.now().format(solo_hora));
	}
}
package facade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {
	public static String getKosId() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");  
		LocalDateTime now = LocalDateTime.now(); 
		
		return dtf.format(now) + (int)(Math.floor(Math.random() * 100000));
	}
	
	public static String getUserId() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");  
		LocalDateTime now = LocalDateTime.now(); 
		
		return dtf.format(now) + (int)(Math.floor(Math.random() * 1000));
	}
}

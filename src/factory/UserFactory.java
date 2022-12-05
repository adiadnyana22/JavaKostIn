package factory;

import model.PemilikKos;
import model.PencariKos;

public class UserFactory {
	public PencariKos getPencariKos(String username, String password) {
		return new PencariKos(username, password);
	}
	
	public PemilikKos getPemilikKos(String username, String password) {
		return new PemilikKos(username, password);
	}
}

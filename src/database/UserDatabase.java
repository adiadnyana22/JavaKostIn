package database;

import java.util.ArrayList;

import factory.UserFactory;
import model.User;

public class UserDatabase {
	private ArrayList<User> userDatabase;
	private static UserDatabase instance;
	
	public static UserDatabase getInstance() {
		if(instance == null) instance = new UserDatabase();
		
		return instance;
	}
	
	private UserDatabase() {
		userDatabase = new ArrayList<>();
		
		addDummyData();
	}
	
	public void addUser(User user) {
		userDatabase.add(user);
	}
	
	public User getUserByIndex(int index) {
		return userDatabase.get(index);
	}
	
	public User login(String username, String password) {
		for (User user : userDatabase) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		
		return null;
	}
	
	public int getLength() {
		return userDatabase.size();
	}
	
	private void addDummyData() {
		UserFactory userFactory = new UserFactory();
		
		userDatabase.add(userFactory.getPemilikKos("adiadnyana22", "adiadnyana22"));
		userDatabase.add(userFactory.getPemilikKos("adiadnyana33", "adiadnyana33"));
		userDatabase.add(userFactory.getPencariKos("adiaja22", "adiaja22"));
		userDatabase.add(userFactory.getPencariKos("adiaja33", "adiaja33"));
	}
}

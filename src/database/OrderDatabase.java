package database;

import java.util.ArrayList;

import model.Order;

public class OrderDatabase {
	private ArrayList<Order> orderDatabase;
	private static OrderDatabase instance;
	
	public static OrderDatabase getInstance() {
		if(instance == null) instance = new OrderDatabase();
		
		return instance;
	}
	
	private OrderDatabase() {
		orderDatabase = new ArrayList<>();
	}
	
	public void addOrder(Order order) {
		orderDatabase.add(order);
	}
	
	public void printOrderByUserId(String userId) {
		if(orderDatabase.size() == 0) System.out.println("\nNo Data\n");
		else {
			System.out.println("=============================================");
			for (Order order : orderDatabase) {
				if(order.getUserId().equals(userId)) {
					System.out.println("Kos Id : " + order.getKosId());
					System.out.println("Jangka Waktu Booking (dalam Bulan) : " + order.getJangkaWaktuBooking());
					System.out.println("=============================================");
				}
			}
		}
	}
}

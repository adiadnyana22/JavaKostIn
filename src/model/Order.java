package model;

public class Order {
	private String userId;
	private String kosId;
	private int jangkaWaktuBooking;
	
	public Order(String userId, String kosId, int jangkaWaktuBooking) {
		this.userId = userId;
		this.kosId = kosId;
		this.jangkaWaktuBooking = jangkaWaktuBooking;
	}

	public String getUserId() {
		return userId;
	}

	public String getKosId() {
		return kosId;
	}

	public int getJangkaWaktuBooking() {
		return jangkaWaktuBooking;
	}
}

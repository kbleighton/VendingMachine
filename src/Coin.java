
public enum Coin {
	
	NICKEL(5, "Nickel"), DIME(10, "Dime"), QUARTER(25, "Quarter"); 
	
	private int value;
	private String name;
	
	private Coin(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getValue() {
		return value;
	}
	
}

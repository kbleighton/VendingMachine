
public enum Item {
	
	ONE(55, "Item One", 1), TWO(70, "Item Two", 2), THREE(75, "Item Three", 3);
	
	private int cost, ID;
	private String name;
	
	private Item(int cost, String name, int ID) {
		this.cost = cost;
		this.name = name;
		this.ID = ID;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}
	
}
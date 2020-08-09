import java.util.HashMap;

public class Inventory {
	
	// map of all items in VM with quantity
	private HashMap<Item, Integer> itemMap = new HashMap<Item, Integer>();
	
	// map of all coins in VM with quantity
	private HashMap<Coin, Integer> coinMap = new HashMap<Coin, Integer>();
	
	public Inventory() {
		
	}
	
	/**
	 * Iterates the list of items
	 * @return String containing ID, name, cost, and quantity with html line break between items
	 */
	public String getItemsString() {
		String output = "";
		for (HashMap.Entry<Item, Integer> entry : itemMap.entrySet()) {
		    Item key = entry.getKey();
		    Integer value = entry.getValue();
			output += key.getID() + ":   " + key.getName() + ",  " + key.getCost() + " cents,  Qty: " + value + "<br/>";
		}
		return output;
	}
	
	/**
	 * Adds item to itemMap in specific quantity
	 * @param item
	 * @param quantity
	 */
	public void addItem(Item item, int quantity) {
		if(itemMap.containsKey(item)) {
			int value = itemMap.get(item);
			itemMap.put(item, value + quantity);
		}
		else {
			itemMap.put(item, quantity);
		}
	}
	
	/**
	 * Add a single item to the itemMap
	 * @param item
	 */
	public void addItem(Item item) {
		if(itemMap.containsKey(item)) {
			int value = itemMap.get(item);
			itemMap.put(item, value + 1);
		}
		else {
			itemMap.put(item, 1);
		}
	}
	
	public int getItemQuantity(Item item) {
		int value = itemMap.get(item);
		return value;
	}
	
	public boolean removeItem(Item item) {
		if(hasItem(item)) {
			int value = itemMap.get(item);
			itemMap.put(item, value - 1);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean hasItem(Item item) {
		return getItemQuantity(item) > 0;
	}

	public void addCoin(Coin coin) {
		if(coinMap.containsKey(coin)) {
			int value = coinMap.get(coin);
			coinMap.put(coin, value + 1);
		}
		else {
			coinMap.put(coin, 1);
		}
		
	}

	public void emptyCoins() {
		coinMap = new HashMap<Coin, Integer>();
	}
	
}

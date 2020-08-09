public class VendingMachine {
    private final int maxBalance = 100;
    private final int initialCount = 10;
    private int balance = 0;
    private Inventory inventory = new Inventory();
    
    public VendingMachine() {
    	launch();
    }
    
    /**
     * Initialize vending machine and inventory
     */
    private void launch() {
    	inventory.addItem(Item.ONE, initialCount);
    	inventory.addItem(Item.TWO, initialCount);
    	inventory.addItem(Item.THREE, initialCount);
    }
    
    public void addItem(Item item) {
    	inventory.addItem(item);
    }
    
    public boolean canAddCoin(Coin coin) {
    	if(this.balance + coin.getValue() <= maxBalance) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void addCoin(Coin coin) {
    	if(canAddCoin(coin)) {
        	inventory.addCoin(coin);
        	this.balance += coin.getValue();
    	}
    	System.out.println(coin.getName() + " inserted.");
    }
    
    public int getBalance() {
        return this.balance;
    }
    
	public void returnCoins() {
		inventory.emptyCoins();
		this.balance = 0;
		System.out.println("Coins returned.");
	}

	public String getInventory() {
		return inventory.getItemsString();
	}
	
	public boolean hasItem(Item item) {
		return inventory.hasItem(item);
	}
	
	public boolean hasSufficientFunds(Item item) {
		return this.balance >= item.getCost();
	}
	
	/**
	 * @return true if purchase is successful (has enough money and item is not sold out)
	 * @param item
	 */
	public boolean purchaseItem(Item item) {
		if (!hasSufficientFunds(item)) {
			returnCoins();
			System.out.println("Insufficient Funds. Coins returned.");
			return false;
		}
		else if(hasItem(item)){
			inventory.removeItem(item);
			balance -= item.getCost();
			System.out.println(item.getName() + " Purchased.");
			return true;
		}
		else {
			System.out.println("Item not in stock.");
			return false;
		}
	}
    
    public int getMaxBalance() {
    	return this.maxBalance;
    }
    
}

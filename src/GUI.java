import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.JOptionPane.showMessageDialog;


public class GUI implements KeyListener, ActionListener {
	
	private JLabel balanceLabel;
	private JLabel inventoryLabel;
	private JLabel instructionsLabel;
	private JFrame frame;
	private JPanel panel;
	private JButton coinReturnButton;
	
	VendingMachine vm;
	
	public GUI() {
		
		vm = new VendingMachine();
    	
		addComponents();
		
		showMessageDialog(null, "Vending Machine Instructions\n\n" 
				+ "Press N Key to insert Nickel\n"
				+ "Press D Key to insert Dime\n"
				+ "Press Q Key to insert Quarter\n\n"
				+ "Press Coin Return button or Spacebar to return coins\n"
				+ "Press corresonding number Key to purchase an item\n"
				+ "  (ie. Press 1 to purchase Item One)\n\n"
				+ "Press F1 to reset Simulation\n"
				+ "Press ESC to exit"
				);
    	
    	frame.pack();
    	frame.setVisible(true);
    	
	}
	
    public static void main(String[] args) {
    	new GUI();
	}

    private void addComponents() {
    	frame = new JFrame();
    	
    	coinReturnButton = new JButton("Coin Return");
    	coinReturnButton.addActionListener(this);
    	coinReturnButton.addKeyListener(this);
    	
    	balanceLabel = new JLabel("Balance: " + vm.getBalance() + " cents");
    	inventoryLabel = new JLabel("<html>Inventory<br/>" + vm.getInventory() + "</html>");
    	instructionsLabel = new JLabel("<html>Press N Key to insert Nickel<br/>"
				+ "Press D Key to insert Dime<br/>"
				+ "Press Q Key to insert Quarter<br/><br/>"
				+ "Press Coin Return button or Spacebar to return coins<br/>"
				+ "Press corresonding number Key to purchase an item<br/>"
				+ "  (ie. Press 1 to purchase Item One)<br/><br/>"
				+ "Press F1 to reset Simulation<br/>"
				+ "Press ESC to exit<br/><br/></html>"
				);
    	
    	panel = new JPanel();
    	panel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
    	panel.setLayout(new GridLayout(0, 1));
    	panel.add(instructionsLabel);
    	panel.add(coinReturnButton);
    	panel.add(balanceLabel);
    	panel.add(inventoryLabel);
    	
    	frame.add(panel, BorderLayout.CENTER);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("Vending Machine");
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		vm.returnCoins();
		updateBalanceDisplay();
	}
	
	public void updateInventoryDisplay() {
		inventoryLabel.setText("<html>Inventory<br/>" + vm.getInventory() + "</html>");
	}
	
	public void updateBalanceDisplay() {
		balanceLabel.setText("Balance: " + vm.getBalance() + " cents");
	}
    
	public void addCoin(Coin coin) {
		if(vm.canAddCoin(coin)) {
			vm.addCoin(coin);
		}
		else {
			showMessageDialog(null, "Coin returned. Balance would have exceeded max balance of "
					+ vm.getMaxBalance() + " cents");
		}
	}
	
	/**
	 * Purchase item only if in stock and have sufficient funds
	 * @param item - item to be purchased
	 */
	public void purchaseItem(Item item) {
		if(vm.hasItem(item))
			if(vm.hasSufficientFunds(item)) {
				vm.purchaseItem(item);
				showMessageDialog(null, item.getName() + " purchased.");
				if(vm.getBalance() > 0) {
					showMessageDialog(null, "Dispensing Change.");
					vm.returnCoins();
				}
			}
			else{
				vm.returnCoins();
				showMessageDialog(null, "Insufficient Funds. Coins returned.");
			}
		else {
			showMessageDialog(null, "Item is sold out. Please select another item.");
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_N) {
			addCoin(Coin.NICKEL);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			addCoin(Coin.DIME);
		}
		else if(e.getKeyCode() == KeyEvent.VK_Q) {
			addCoin(Coin.QUARTER);
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			// This will trigger the Coin Return Button
		}
		else if(e.getKeyCode() == KeyEvent.VK_1) {
			purchaseItem(Item.ONE);
		}
		else if(e.getKeyCode() == KeyEvent.VK_2) {
			purchaseItem(Item.TWO);
		}
		else if(e.getKeyCode() == KeyEvent.VK_3) {
			purchaseItem(Item.THREE);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			// Exit
			System.exit(0);
		}
		else if(e.getKeyCode() == KeyEvent.VK_F1) {
			// New Game
			vm = new VendingMachine();
		}
		else {
			showMessageDialog(null, "Invalid Input. Please Try Again");
		}
		updateBalanceDisplay();
		updateInventoryDisplay();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

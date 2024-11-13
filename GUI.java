package solitaire;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	Blackjack game;
	
	JPanel dealerArea = new JPanel();
	JPanel gameArea = new JPanel();
	JPanel playerArea = new JPanel();
	JPanel deckArea = new JPanel();
	JPanel aceChoice = new JPanel();
	JPanel playerChoices = new JPanel();

	public GUI(Blackjack game) {
		this.game = game;
		// Create and set up the window.
		setTitle("Solitaire");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// this supplies the background
		/*try {
			System.out.println(getClass().toString());
			//Image blackImg = ImageIO.read(getClass().getResource("background.jpg"));
			//setContentPane(new ImagePanel(blackImg));

		} catch (IOException e) {
			e.printStackTrace();
		}*/

		// setting up the areas of the game
		gameArea.setOpaque(false);
		gameArea.setLayout(new BoxLayout(gameArea, BoxLayout.PAGE_AXIS));
		gameArea.setSize(new Dimension(898, 565));
		gameArea.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));

		dealerArea.setOpaque(false);
		dealerArea.setLayout(new BoxLayout(dealerArea, BoxLayout.PAGE_AXIS));
		dealerArea.setBounds(350, 0, 200, 282);
		dealerArea.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

		playerArea.setOpaque(false);
		playerArea.setLayout(new BoxLayout(playerArea, BoxLayout.PAGE_AXIS));
		playerArea.setBounds(350, 283, 200, 282);
		playerArea.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		deckArea.setOpaque(false);
		deckArea.setLayout(new BoxLayout(deckArea, BoxLayout.PAGE_AXIS));
		deckArea.setBounds(700, 0, 200, 250);
		deckArea.setBorder(BorderFactory.createDashedBorder(Color.BLACK, (float) 4.5, (float) 2.0));
		/*
		 * aceChoice.setOpaque(false); aceChoice.setLayout(new GridLayout(2,2));
		 * aceChoice.setBounds(2,1, 200,250);
		 * aceChoice.setBorder(BorderFactory.createDashedBorder(Color.BLACK,(float)4.5,(
		 * float)2.0));
		 */
		playerChoices.setOpaque(false);
		playerChoices.setLayout(new GridLayout(1, 3));
		playerChoices.setBounds(0, 565, 900, 125);
		// sets up player input buttons to be added to player choices
		// initializing button choices
		JButton hit = new JButton("Hit");
		JButton stand = new JButton("Stand");
		hit.setSize(100, 50);
		stand.setSize(100, 50);
		playerChoices.add(hit);
		playerChoices.add(stand);
		
		// action listerners
		hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Blackjack.drawNextCard();
				updateScreen();
			}
		});
		stand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// endPlayerTurn();
				updateScreen();
			}
		});

		// setting
		/*******
		 * This is just a test to make sure images are being read correctly on your
		 * machine. Please replace once you have confirmed that the card shows up
		 * properly. The code below should allow you to play the solitare game once it's
		 * fully created.
		 */
		this.add(gameArea);
		this.add(dealerArea);
		this.add(playerArea);
		this.add(deckArea);
		this.add(aceChoice);
		this.add(playerChoices);
		this.setVisible(true);
		gameArea.setVisible(true);
		dealerArea.setVisible(true);

		Stack<Card> stackIn = new Stack<Card>();
		stackIn.push(new Card(10, Card.Suit.Spades));
		stackIn.push(new Card(4, Card.Suit.Clubs));
		stackIn.push(new Card(9, Card.Suit.Hearts));
		stackIn.push(new Card(6, Card.Suit.Diamonds));
		playerArea.add(drawPile(stackIn));
		this.setVisible(true);
	}

//Bruno Lilje 
	// precondition: input needs to not be null;
	// postcondition: return Jlayeredpane and shows the cards stacked in reverse
	// order they were added in.
	public JLayeredPane drawPile(ArrayList<Card> stackIn) {
		JLayeredPane layers = new JLayeredPane();
		Object cards[];
		int x = 20;
		int y = 20;
		int layerDistance = 0;
		cards = stackIn.toArray(); // please note we convert this stack to an array so that we can iterate through
									// it backwards while drawing. You’ll need to cast each element inside cards to
									// a <Card> in order to use the methods to adjust their position
		// for loops starts from the back of the list
		for (int i = cards.length - 1; i >= 0; i--) {
			Card tempholder = (Card) cards[i];
			layers.add(tempholder, new Integer(layerDistance));
			tempholder.setBounds(x, y, 100, 137);
			x += 20;
			layerDistance++;
		}
		layers.setVisible(true);
		return layers;
	}
	
	public JLayeredPane drawPile(Stack<Card> stackIn) {
		JLayeredPane layers = new JLayeredPane();
		Object cards[];
		int x = 20;
		int y = 20;
		int layerDistance = 0;
		cards = stackIn.toArray(); // please note we convert this stack to an array so that we can iterate through
									// it backwards while drawing. You’ll need to cast each element inside cards to
									// a <Card> in order to use the methods to adjust their position
		// for loops starts from the back of the list
		for (int i = cards.length - 1; i >= 0; i--) {
			Card tempholder = (Card) cards[i];
			layers.add(tempholder, new Integer(layerDistance));
			tempholder.setBounds(x, y, 100, 137);
			x += 20;
			layerDistance++;
		}
		layers.setVisible(true);
		return layers;
	}

	public void updateScreen() {
		playerArea.add(drawPile(Blackjack.playerHand));
		dealerArea.add(drawPile(Blackjack.dealerHand));
		// nends to show playeyer cards
		// shows dealer cards
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
package solitaire;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Card class to store the information of single card
 * @member {Suit} suit The suit of the card (Spades,Hearts,Diamonds,Clubs)
 * @member {Integer} value The value of the card (1->13)
 */
public class Card extends JPanel{
		// Members
		public int value;
		public int gameValue;
		public Suit suit;
		private BufferedImage image;
		private BufferedImage backImage;
		boolean isReversed;
		Point positionOffset;
		
		/**
		 * Enum to store the suit values
		 */
		public enum Suit {
			Spades(1, false),
			Hearts(2, true),
			Diamonds(3, true),
			Clubs(4, false);
			
			public int value;
			public boolean isRed;
			
			private Suit(int value, boolean isRed) {
				this.value = value;
				this.isRed = isRed;
			}
		};
		
		/**
		 * Converts the value of the card to a string
		 * @param {Integer} value The value of the card 
		 */
		public static String valueString(int value) {
								
			if(value == 11) return "J";
			if(value == 12) return "Q";
			if(value == 13) return "K";
			if(value == 1) return "A";
			
			// Value between 2 and 10
			return Integer.toString(value);
		}

		/**
		 * Converts the value of the card to a int
		 * @param {String} value The value of the card 
		 */
		public int valueInt(String value) {
			
			if(value.equals("J")) return 10;
			if(value.equals("Q")) return 10;
			if(value.equals("K")) return 10;
			if(value.equals("A")) return gameValue;
			
			return Integer.parseInt(value);
		}
		
		public void setAceGameValue(int val) {
			if (valueString(value) == "A") {
				gameValue = val;
			}
		}
		
		/**
		 * toString method, eg: "K of Diamonds"
		 * @return {String} Description of the current card
		 */
		public String toString() {
			return valueString(value) + " of " + suit.name();
		}
		
		/**
		 * Returns a string that can be used to re-initialize the card
		 * @return {String} Class properties, " of " separated.
		 */
		public String saveAsString() {
			return valueString(value) + " of " + suit.name() + " of " + isReversed;
		}
		
		/**
		 * Class constructor
		 * @param {Integer} value The value of the card, in [1,13]
		 * @param {Suit} suit The suit of the card
		 */
		public Card(int value, Suit suit) {
			this.value = value;
			this.gameValue = value;;
			this.suit = suit;		
			isReversed = false;
			
			if (value >= 11) {
				gameValue = 10;
			}
			
			if (value == 1) {
				gameValue = 11;
			}
			
			try {
				// Load the image for the current file
				URL url = getClass().getResource("../solitaire/images/cards/" + this.toString() +".png");
				//System.out.println(this.getClass().getResource(""));
				image = ImageIO.read(url);
				//System.out.println(url);
				URL urlback = getClass().getResource("../solitaire/images/cards/back.png"); 
				backImage = ImageIO.read(urlback);
				
				setBounds(0, 0, image.getWidth(), image.getHeight());
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			positionOffset = new Point(0,0);
			setSize(new Dimension(100, 145));
			setOpaque(false);
		}
		
		/**
		 * Turns the card with the back up
		 */
		public void hide() {
			isReversed = true;
		}
		
		/**
		 * Turns the card with the face up
		 */
		public void show() {
			isReversed = false;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			BufferedImage img = image;
			if(isReversed) img = backImage;

			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	
}

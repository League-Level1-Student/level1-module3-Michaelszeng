	import java.applet.AudioClip;
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Component;
	import java.awt.GridLayout;
	import java.awt.Toolkit;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.net.URL;

	import javax.sound.sampled.AudioInputStream;
	import javax.sound.sampled.AudioSystem;
	import javax.sound.sampled.Clip;
	import javax.swing.BoxLayout;
	import javax.swing.Icon;
	import javax.swing.ImageIcon;
	import javax.swing.JApplet;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;

	import java.io.File;

	/* This recipe is to be used with the Jeopardy Handout: http://bit.ly/1bvnvd4 */

	public class JeopardyPractice implements ActionListener {
		private JButton firstButton;
		private JButton secondButton;
		private JButton thirdButton, fourthButton, fifthButton;

		private JPanel quizPanel;
		int score = 0;
		JLabel scoreBox = new JLabel("0");
		int buttonCount = 0;

		public static void main(String[] args) {
			new JeopardyPractice().start();
		}

		private void start() {
			JFrame frame = new JFrame();
			quizPanel = new JPanel();
			frame.setLayout(new BorderLayout());
			

			// 1. Make the frame show up
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// 2. Give your frame a title
			frame.setTitle("FRAME");
			// 3. Create a JPanel variable to hold the header using the createHeader method
			JPanel panel;
			panel=createHeader("Dogs");
			// 4. Add the header component to the quizPanel
			quizPanel.add(panel);
			// 5. Add the quizPanel to the frame
			frame.add(quizPanel);
			// 6. Use the createButton method to set the value of firstButton
			firstButton=createButton("$100");
			// 7. Add the firstButton to the quizPanel
			quizPanel.add(firstButton);
			// 8. Write the code inside the createButton() method below. Check that your
			// game looks like Figure 1 in the Jeopardy Handout - http://bit.ly/1bvnvd4.
			
			// 9. Use the secondButton variable to hold a button using the createButton
			// method
			secondButton=createButton("$200");
			// 10. Add the secondButton to the quizPanel
			quizPanel.add(secondButton);
			// 11. Add action listeners to the buttons (2 lines of code)
			firstButton.addActionListener(this);
			secondButton.addActionListener(this);
			thirdButton=createButton("$300");
			quizPanel.add(thirdButton);
			thirdButton.addActionListener(this);
			fourthButton=createButton("$400");
			quizPanel.add(fourthButton);
			fourthButton.addActionListener(this);
			fifthButton=createButton("$500");
			quizPanel.add(fifthButton);
			fifthButton.addActionListener(this);
			// 12. Fill in the actionPerformed() method below

			frame.pack();
			quizPanel.setLayout(new GridLayout(buttonCount + 1, 3));
			frame.add(makeScorePanel(), BorderLayout.NORTH);
			frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().height,
					Toolkit.getDefaultToolkit().getScreenSize().width);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		/*
		 * 13. Use the method provided to play the Jeopardy theme music
		 * 
		 * 14. Add buttons so that you have $200, $400, $600, $800 and $1000 questions
		 *
		 * [optional] Use the showImage or playSound methods when the user answers a
		 * question
		 */

		private JButton createButton(String dollarAmount) {
			// Create a new JButton and set the value to "dollarAmount"
			JButton buttony = new JButton(dollarAmount);

			// Increment the buttonCount (this should make the layout vertical)
			buttonCount = buttonCount+1;
			// Return your new button instead of the temporary button
			//return new JButton("temporary button");
			return buttony;
		}

		public void actionPerformed(ActionEvent arg0) {
			// Remove this temporary message:
			
			// Use the method that plays the jeopardy theme music.
			playJeopardyTheme();
			JButton buttonPressed = (JButton) arg0.getSource();
			// If the buttonPressed was the firstButton
			if (buttonPressed==firstButton) {
				askQuestion("How do you spell dog", "dog", 100);
				firstButton.setText("");
			}
			// Call the askQuestion() method

			// Fill in the askQuestion() method. When you play the game, the score should
			// change.

			// Or if the buttonPressed was the secondButton
			if (buttonPressed==secondButton) {
				askQuestion("What is the second letter in dog?", "g", 200);
			
			// Call the askQuestion() method with a harder question

			// Clear the button text (set the button text to nothing)
			secondButton.setText("");
			}
			if (buttonPressed==thirdButton) {
				askQuestion("What is the fourth letter in dog?", "c", 300);
				thirdButton.setText("");
			}
			if (buttonPressed==fourthButton) {
				askQuestion("How many dogs are there?", "d", 400);
				fourthButton.setText("");
			}
			if (buttonPressed==fifthButton) {
				askQuestion("What is the 17th letter in dog?", "q", 500);
				fifthButton.setText("");
			}
		}

		private void askQuestion(String question, String correctAnswer, int prizeMoney) {
			// Remove this temporary message
			// Use a pop up to ask the user the question
			String answer = JOptionPane.showInputDialog(question);
			// If the answer is correct
			if (correctAnswer.equals(answer)) {
				
			
			// Increase the score by the prizeMoney
			score = score+prizeMoney;
			// Call the updateScore() method
			updateScore();
			// Pop up a message to tell the user they were correct
			JOptionPane.showMessageDialog(null, "Good jorb you were right");
			}
			// Otherwise
			else {
				
			// Decrement the score by the prizeMoney
				score = score-prizeMoney;
			// Pop up a message to tell the user the correct answer
				JOptionPane.showMessageDialog(null, "wrung. The correct answer is: "+correctAnswer);
			// Call the updateScore() method
				updateScore();
			}

		}

		public void playJeopardyTheme() {
			try {
				AudioClip sound = JApplet.newAudioClip(getClass().getResource("jeopardy.wav"));
				sound.play();
				Thread.sleep(3400);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		private void playSound(String fileName) {
			AudioClip scream = JApplet.newAudioClip(getClass().getResource(fileName));
			scream.play();
		}

		private Component makeScorePanel() {
			JPanel panel = new JPanel();
			panel.add(new JLabel("score:"));
			panel.add(scoreBox);
			panel.setBackground(Color.CYAN);
			return panel;
		}

		private void updateScore() {
			scoreBox.setText("" + score);
		}

		private JPanel createHeader(String topicName) {
			JPanel panelj = new JPanel();
			panelj.setLayout(new BoxLayout(panelj, BoxLayout.PAGE_AXIS));
			JLabel l1 = new JLabel(topicName);
			l1.setAlignmentX(Component.CENTER_ALIGNMENT);
			panelj.add(l1);
			return panelj;
		}

		void showCorrectImage() {
			showImage("correct.jpg");
		}

		void showIncorrectImage() {
			showImage("incorrect.jpg");
		}

		private void showImage(String fileName) {
			JFrame frame = new JFrame();
			URL imageURL = getClass().getResource(fileName);
			Icon icon = new ImageIcon(imageURL);
			JLabel image = new JLabel(icon);
			frame.add(image);
			frame.setVisible(true);
			frame.pack();
		}
	}

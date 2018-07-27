import java.util.Random;

import javax.swing.JOptionPane;

public class Magic8Ball {
	public static void main(String[] args) {
		int number=new Random().nextInt(4);
		System.out.println(number);
		String i = JOptionPane.showInputDialog("Enter a Question.");
		if (number==0) {
			System.out.println("yes");
		}
		if (number==1) {
			System.out.println("no");
		}
		if (number==2) {
			System.out.println("googly it");
		}
		if (number==3) {
			System.out.println("I am useless");
		}
	}
}

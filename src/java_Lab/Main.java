package java_Lab;
import javax.swing.SwingUtilities;

import GUI.Controller;

public class Main {
	public static void main(String... args) {
		SwingUtilities.invokeLater(Controller::new);
	}
}
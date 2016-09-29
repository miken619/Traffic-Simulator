package finalProject.src.main;

import finalProject.src.ui.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		UI ui;
		ui = new PopupUI();
		    
		Control control = new Control (ui);
		control.run();
		

	}

}

package Main;

import Controller.Controller;
import View.ViewGUI;

public class Main {
    public static void main(String[] args) {
    	
    	ViewGUI swingGui = new ViewGUI();
    	Controller controller = new Controller(swingGui);
    	
    	controller.runChrono();
    }
}
package Main;

import java.awt.*;
import java.awt.event.*;

public class Bot {
	public static void main(String[] args)
            throws AWTException{
		
		Robot myRobot = new Robot();
		 for(int i  = 0; i<100; i++) {  
		        myRobot.delay(5);
		        myRobot.mouseMove(1005,i);
		 }

}}

package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import objects.GameObject;
import objects.ObjectHandler;
import objects.ObjectID;

public class KeyInput extends KeyAdapter {
	
	 private ObjectHandler objectHandler;
	 
	 public KeyInput(ObjectHandler objectHandler) {
		 this.objectHandler = objectHandler;
	 }
	 
	 public void keyPressed(KeyEvent event) {
		 int pressedKey = event.getKeyCode();
		 
		 for (int curObj = 0; curObj < objectHandler.objectList.size(); curObj++) {
			 GameObject tempObject = objectHandler.objectList.get(curObj);
			 
			 if (tempObject.getID() == ObjectID.Player) {
				 tempObject.keyPressed(pressedKey);
			 }
		 }
	 }
	 
	 public void keyReleased(KeyEvent event) {
		 int releasedKey = event.getKeyCode();
		 
		 for (int curObj = 0; curObj < objectHandler.objectList.size(); curObj++) {
			 GameObject tempObject = objectHandler.objectList.get(curObj);
			 
			 if (tempObject.getID() == ObjectID.Player) {
				 tempObject.keyReleased(releasedKey);
			 }
		 }
	 }
}

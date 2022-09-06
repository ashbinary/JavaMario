package objects;

import java.awt.Graphics;
import java.util.LinkedList;

//	Handles the objects used in the game (current frame, current vars) along with adding and removing them.

public class ObjectHandler {

	public LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	
	public void tick() {
		for (int curObj = 0; curObj < objectList.size(); curObj++) {
			GameObject tempObj = objectList.get(curObj);
			tempObj.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int curObj = 0; curObj < objectList.size(); curObj++) {
			GameObject tempObj = objectList.get(curObj);
			tempObj.render(g);
		}
	}
	
	public void addObj(GameObject object) {
		this.objectList.add(object);
	}
	
	public void removeObj(GameObject object) {
		this.objectList.remove(object);
	}
}

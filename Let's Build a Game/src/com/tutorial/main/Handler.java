package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler{
	
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();

	public int spd = 5;
	
	public synchronized void tick() {
		
		for(int i = 0; i<object.size(); i++) {
			
		    GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
		
	}
	
	public synchronized void render(Graphics g) {
		
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = null;
		    try {
		    	 tempObject = object.get(i);
		    } catch(IndexOutOfBoundsException e) {
		    	break;
		    }
			
			
			if(tempObject != null) {
				tempObject.render(g);
			}
		}
		
	}
	
	public synchronized void clearEnemys() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				object.clear();
				if(Game.gameState != Game.STATE.End)
				addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
			}
		}
	}
	
	public synchronized void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public synchronized void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	

}

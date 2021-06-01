package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	private BufferedImage player_image;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		player_image = ss.grabImage(1, 1, 31, 31);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 31, 31);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH -45);
		y = Game.clamp(y, 0, Game.HEIGHT - 68);
		
		//handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 31, 31, 0.05f, handler));
		
		collision();
	}
	
	private void collision(){
		for(int i = 0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.HEALTH -=2;
				}
			}
			
			if(tempObject.getId() == ID.EnemyBoss) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -=10;
				}
			}
			if(tempObject.getId() == ID.HardEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 3;
				}
			}
		}
	}

	public void render(Graphics g) {
		
		//g.setColor(Color.white);
		//g.fillRect((int)x, (int)y, 31, 31);
		g.drawImage(player_image, (int)x, (int)y, null);
		
		
		
	}
	
	

}

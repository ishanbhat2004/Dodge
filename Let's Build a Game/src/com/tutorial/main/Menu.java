package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
		
			//Play Button
			if(mouseOver(mx,my, 210, 150, 200, 64)) {
				
				Game.gameState = STATE.Select;
				
				AudioPlayer.getSound("menu_sound").play();
				return;
				
			}
		
			//Help Button
			if(mouseOver(mx,my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
			}
		
		
			//Quit Button
			if(mouseOver(mx,my, 210, 350, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				System.exit(1);
			}
	   }
		
		if(Game.gameState == STATE.Select) {
			
			//normal Button
			if(mouseOver(mx,my, 210, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				game.diff = 0;
				
				AudioPlayer.getSound("menu_sound").play();
				
			}
		
			//Hard Button
			if(mouseOver(mx,my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
				
				game.diff = 1;
				
				AudioPlayer.getSound("menu_sound").play();
			}
		
		
			//Back Button
			if(mouseOver(mx,my, 210, 350, 200, 64)) {
				 Game.gameState = STATE.Menu;
				 AudioPlayer.getSound("menu_sound").play();
			     return;
			}
	   }
		
	   //Back Button for Help
	   if(Game.gameState == STATE.Help) {
		   if(mouseOver(mx,my, 210, 350,200, 64)) {
			   Game.gameState = STATE.Menu;
			   AudioPlayer.getSound("menu_sound").play();
			   return;
		   }
	   }
	   
	   //Back Button for Help
	   if(Game.gameState == STATE.End) {
		   if(mouseOver(mx,my, 210, 350,200, 64)) {
			   Game.gameState = STATE.Menu;
			   hud.setLevel(1);
	    	   hud.setScore(0);
		       AudioPlayer.getSound("menu_sound").play();
		   }
	    }
    }
	
	public void mouseReleased(MouseEvent e) {
	
	} 
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}

	
	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Wave",240, 70);
	
		g.setFont(fnt2);
		g.drawRect(210, 150, 200, 64);
		g.drawString("Play",280, 190);
		
		g.drawRect(210, 250, 200, 64);
		g.drawString("Help",280, 290);

		g.drawRect(210, 350, 200, 64);
		g.drawString("Quit",280, 390);
		}else if(Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help",250, 70);
			
			g.setFont(fnt3);
			g.drawString("Use W,A,S,D keys to move player and dodge enemies", 70, 230);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 215, 64);
			g.drawString("Back",280, 390);
		}else if(Game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over",180, 70);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 175, 230);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 215, 64);
			g.drawString("Try Again",245, 390);
		}else if(Game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY",70, 70);
		
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal",260, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard",280, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Back",280, 390);
		}
	
	}
	
}

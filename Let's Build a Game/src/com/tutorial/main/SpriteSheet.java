package com.tutorial.main;

import java.awt.image.BufferedImage;

public class SpriteSheet{
	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage ss)
	{
		this.sprite = ss;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		BufferedImage img = sprite.getSubimage((col*31)-31, (row*31)-31, width, height);
	    return img;
	}
}

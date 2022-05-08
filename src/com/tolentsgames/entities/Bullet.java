package com.tolentsgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.tolentsgames.main.Game;

public class Bullet extends Entity {

	private int type;
	private int xStep = 0;
	private int maxXStep = 4;
	
	public Bullet(double x, double y, int width, int height, double speed, BufferedImage sprite, int bulletType) {
		super(x, y, width, height, speed, sprite);
		this.type = bulletType;
	}
	
	public void tick() {

		y -= speed;
		if(y < 0) {
			Game.entities.remove(this);
			return;
		}
		
		if(type == 1) {
			xStep++;
			if(xStep == maxXStep) {
				xStep = 0;
				x -= speed; 
			}
			
			if(x < 0) {
				Game.entities.remove(this);
				return;
			}
		}
		
		if(type == 2) {
			xStep++;
			if(xStep == maxXStep) {
				xStep = 0;
				x += speed; 
			}
			if(x > (Game.WIDTH * Game.SCALE)) {
				Game.entities.remove(this);
				return;
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.getX(), this.getY(), width, height);
	}

}

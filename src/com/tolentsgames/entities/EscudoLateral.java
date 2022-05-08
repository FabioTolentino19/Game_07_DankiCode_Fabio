package com.tolentsgames.entities;

import java.awt.image.BufferedImage;

import com.tolentsgames.main.Game;

public class EscudoLateral extends Entity {
	
	public boolean left = false;
	
	public EscudoLateral(double x, double y, int width, int height, double speed, BufferedImage sprite, boolean left) {
		super(x, y, width, height, speed, sprite);
		this.left = left;
	}
	
	public void tick() {
		if(Player.JaTemEscudo) {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					if(left)
						this.setX(e.getX() - 2);
					else
						this.setX(e.getX() + 16);
				}
			}
		}
	}
}


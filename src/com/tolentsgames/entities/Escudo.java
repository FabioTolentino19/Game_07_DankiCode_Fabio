package com.tolentsgames.entities;

import java.awt.image.BufferedImage;

import com.tolentsgames.main.Game;

public class Escudo extends Entity {
	
	public Escudo(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(Player.JaTemEscudo) {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player) {
					this.setX(e.getX() - 4);
				}
			}
		}
	}
}

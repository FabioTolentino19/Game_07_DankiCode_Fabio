package com.tolentsgames.entities;

import java.awt.image.BufferedImage;

import com.tolentsgames.main.EnemySpawn;
import com.tolentsgames.main.FuelSpawn;
import com.tolentsgames.main.Game;
import com.tolentsgames.main.Sound;

public class Fuel extends Entity {

	public int life = 1;
	
	public Fuel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		y += speed;
		if(y >= Game.HEIGHT) {
			Game.entities.remove(this);
			return;
		}
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Bullet) {
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(e);
					life--;
					if(life == 0) {
						Explosion explosion = new Explosion(x, y, 16, 16, 1, null, 0);
						Game.entities.add(explosion);
						Sound.explosionAsteroid[EnemySpawn.lastExploUsed].play(Sound.effectsVolume);
						if(EnemySpawn.lastExploUsed < EnemySpawn.maxExploUsed)
							EnemySpawn.lastExploUsed++;
						else
							EnemySpawn.lastExploUsed = 0;
						FuelSpawn.numFuel--;
						Game.entities.remove(this);
						return;
					}
					break;
				}
			}
		}
	}
}

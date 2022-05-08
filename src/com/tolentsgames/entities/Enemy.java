package com.tolentsgames.entities;

import java.awt.image.BufferedImage;

import com.tolentsgames.main.EnemySpawn;
import com.tolentsgames.main.Game;
import com.tolentsgames.main.Sound;

public class Enemy extends Entity {
	
	public int life;
	public int type;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite, int life, int type) {
		super(x, y, width, height, speed, sprite);
		this.life = life;
		this.type = type;
	}
	
	public void tick() {
		y += speed;
		
		if((type >= 0 && type <= 5) || (type >= 11 && type <= 20)) {
			
			if(type >= 11 && type <= 20) {
				if(type % 2 == 0) {
					x += speed/4;
					if((x + 14) >= Game.WIDTH)
						type--;
				} else {
					x -= speed/4;
					if((x + 3) <= 0)
						type++;
				}
						
//				if((x + 14) >= Game.WIDTH || (x + 3) <= 0) {
//					 if(Game.life - (3 * life) <= 0)
//						Game.life = 0;
//					else
//						Game.life -= (3 * life);
//					 EnemySpawn.numEnemies--;			 			 
//					 Game.entities.remove(this);
//					 return;
//				}
			}
		
			if(y >= Game.HEIGHT) {
				 if(Game.life - (3 * life) <= 0)
					Game.life = 0;
				else
					Game.life -= (3 * life);
				 EnemySpawn.numEnemies--;			 			 
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
							Game.score++;
							EnemySpawn.numEnemies--;
							Game.entities.remove(this);
							return;
						} else {
							Sound.explosionAsteroid2[EnemySpawn.lastExploUsed].play(Sound.effectsVolume);
							if(EnemySpawn.lastExploUsed < EnemySpawn.maxExploUsed)
								EnemySpawn.lastExploUsed++;
							else
								EnemySpawn.lastExploUsed = 0;							
							Game.score++;
						}
						break;
					}
				}
				if(e instanceof Escudo || e instanceof EscudoLateral) {
					if(Entity.isColidding(this, e)) {
						Explosion explosion = new Explosion(x, y, 16, 16, 1, null, 0);
						Game.entities.add(explosion);
						Sound.explosionAsteroid[EnemySpawn.lastExploUsed].play(Sound.effectsVolume);
						if(EnemySpawn.lastExploUsed < EnemySpawn.maxExploUsed)
							EnemySpawn.lastExploUsed++;
						else
							EnemySpawn.lastExploUsed = 0;							
						Game.score++;
						EnemySpawn.numEnemies--;
						Game.entities.remove(this);
						return;
					}
				}
			}
		}
		
		if(type >= 6 && type <= 10) {
			
			if(y >= Game.HEIGHT) {
				 if(Game.life - (5 * life) <= 0)
					Game.life = 0;
				else
					Game.life -= (5 * life);
				 EnemySpawn.numEnemies--;			 			 
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
							Explosion explosion = new Explosion(x, y, 16, 16, 1, null, 2);
							Game.entities.add(explosion);
							Sound.explosionAsteroid[EnemySpawn.lastExploUsed].play(Sound.effectsVolume);
							if(EnemySpawn.lastExploUsed < EnemySpawn.maxExploUsed)
								EnemySpawn.lastExploUsed++;
							else
								EnemySpawn.lastExploUsed = 0;							
							Game.score++;
							
							if(type == 6) {
								Enemy enemy1 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(16, 0, 16, 16), 1, 11);
								Game.entities.add(enemy1);
								EnemySpawn.numEnemies++;
								
								Enemy enemy2 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(16, 0, 16, 16), 1, 12);
								Game.entities.add(enemy2);
								EnemySpawn.numEnemies++;								
							} else if(type == 7) {
								Enemy enemy1 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(32, 0, 16, 16), 2, 13);
								Game.entities.add(enemy1);
								EnemySpawn.numEnemies++;
								
								Enemy enemy2 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(32, 0, 16, 16), 2, 14);
								Game.entities.add(enemy2);
								EnemySpawn.numEnemies++;								
							} else if(type == 8) {
								Enemy enemy1 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(48, 0, 16, 16), 3, 15);
								Game.entities.add(enemy1);
								EnemySpawn.numEnemies++;
								
								Enemy enemy2 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(48, 0, 16, 16), 3, 16);
								Game.entities.add(enemy2);
								EnemySpawn.numEnemies++;								
							} else if(type == 9) {
								Enemy enemy1 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(64, 0, 16, 16), 4, 17);
								Game.entities.add(enemy1);
								EnemySpawn.numEnemies++;
								
								Enemy enemy2 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(64, 0, 16, 16), 4, 18);
								Game.entities.add(enemy2);
								EnemySpawn.numEnemies++;								
							} else if(type == 10) {
								Enemy enemy1 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(80, 0, 16, 16), 5, 19);
								Game.entities.add(enemy1);
								EnemySpawn.numEnemies++;
								
								Enemy enemy2 = new Enemy(x, y, 16, 16, speed, Game.spritesheet.getSprite(80, 0, 16, 16), 5, 20);
								Game.entities.add(enemy2);
								EnemySpawn.numEnemies++;								
							}
							
							EnemySpawn.numEnemies--;
							Game.entities.remove(this);
							return;
						} else {
							Sound.explosionAsteroid2[EnemySpawn.lastExploUsed].play(Sound.effectsVolume);
							if(EnemySpawn.lastExploUsed < EnemySpawn.maxExploUsed)
								EnemySpawn.lastExploUsed++;
							else
								EnemySpawn.lastExploUsed = 0;							
							Game.score++;
						}
						break;
					}
				}
				if(e instanceof Escudo || e instanceof EscudoLateral) {
					if(Entity.isColidding(this, e)) {
						Explosion explosion = new Explosion(x, y, 16, 16, 1, null, 2);
						Game.entities.add(explosion);
						Sound.explosionAsteroid[EnemySpawn.lastExploUsed].play(Sound.effectsVolume);
						if(EnemySpawn.lastExploUsed < EnemySpawn.maxExploUsed)
							EnemySpawn.lastExploUsed++;
						else
							EnemySpawn.lastExploUsed = 0;							
						Game.score++;
						EnemySpawn.numEnemies--;
						Game.entities.remove(this);
						return;
					}
				}
			}
		}
	}	
}

package com.tolentsgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.tolentsgames.main.EnemySpawn;
import com.tolentsgames.main.Game;
import com.tolentsgames.main.Sound;

public class Player extends Entity {

	public boolean right, left;
	public int lastBullet = 0;
	public int maxBullet = 11;
	public boolean isShooting = false;
	public int arma = 1;
	public int fuelFrame = 0;
	public int maxFuelFrame = 30;
	public static boolean escudo = false;
	public static boolean JaTemEscudo = false;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}
	
	public void tick() {
		if(right) {
			x += speed;
		} else if(left) {
			x -= speed;
		}
		
		if(x >= Game.WIDTH) {
			x = -16;
		} else if(x + 16 < 0) {
			x = Game.WIDTH;
		}
		
		fuelFrame++;
		if(fuelFrame > maxFuelFrame) {
			fuelFrame = 0;
			if(Game.fuel > 0)
				Game.fuel--;
		}
		
		if(escudo) {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Escudo) {
					JaTemEscudo = true;
					break;
				} else {
					JaTemEscudo = false;					
				}
			}
			if(!JaTemEscudo) {
				Escudo escudo = new Escudo(x - 4, y - 4, 24, 20, 1, Game.spritesheet.getSprite(60, 44, 24, 20));
				Game.entities.add(escudo);
				// Escudo lateral substituido por escudo único
				//EscudoLateral escudoesquerda = new EscudoLateral(x - 2, y, 2, 16, 1, Game.spritesheet.getSprite(84, 16, 2, 16), true);
				//Game.entities.add(escudoesquerda);
				//EscudoLateral escudodireita = new EscudoLateral(x + 16, y, 2, 16, 1, Game.spritesheet.getSprite(84, 16, 2, 16), false);
				//Game.entities.add(escudodireita);
			}
		}
		
		if(!escudo) {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Escudo || e instanceof EscudoLateral) {
					Game.entities.remove(e);
				}
			}
		}
		
		
		
		//verificar colisão com Fuel, munição, vida e asteroide (colisão com asteroide mata o player)
		
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Fuel) {
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(e);
					if(Game.fuel + 20 > Game.maxFuel)
						Game.fuel = Game.maxFuel;
					else
						Game.fuel += 20;
					Sound.bipFuel.play(Sound.effectsVolume);
				}
			}
			if(e instanceof Municao) {
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(e);
					Game.municao += 100;
					Sound.bipMunicao.play(Sound.effectsVolume);
				}
			}
			if(e instanceof Vida) {
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(e);
					if(Game.life + 10 > Game.maxLife)
						Game.life = Game.maxLife;
					else
						Game.life += 10;
					Sound.bipVida.play(Sound.effectsVolume);
				}
			}
			if(e instanceof Enemy) {
				if(Entity.isColidding(this, e)) {
					Explosion explosion1 = new Explosion(e.getX(), e.getY(), 16, 16, 1, null, 0);
					Game.entities.remove(e);
					Explosion explosion2 = new Explosion(x, y, 16, 16, 1, null, 1);
					Game.entities.add(explosion1);
					Game.entities.add(explosion2);
					Sound.explosionAsteroid[EnemySpawn.lastExploUsed].play(Sound.effectsVolume);
					if(EnemySpawn.lastExploUsed < EnemySpawn.maxExploUsed)
						EnemySpawn.lastExploUsed++;
					else
						EnemySpawn.lastExploUsed = 0;
					Game.life = 0;
					Game.entities.remove(this);
					return;
				}
			}
		}
	
		
		// sistema de tiro
		
		if(isShooting && Game.municao > 0) {
			isShooting = false;
			Game.municao--;
			
			switch(arma) {
			
			case 1:
				Sound.Shoot1[lastBullet].play(Sound.effectsVolume);
				if(lastBullet < maxBullet)
					lastBullet++;
				else
					lastBullet = 0;
				int x1 = this.getX() + 7;
				int y1 = this.getY() + 4;
				Bullet bullet1 = new Bullet(x1, y1, 2, 2, 4, null, 0);
				Game.entities.add(bullet1);
				break;
				
			case 2:
				Sound.Shoot2[lastBullet].play(Sound.effectsVolume);
				if(lastBullet < maxBullet)
					lastBullet++;
				else
					lastBullet = 0;
				int x2 = this.getX() + 5;
				int y2 = this.getY() + 4;
				Bullet bullet2 = new Bullet(x2, y2, 2, 2, 4, null, 0);
				Game.entities.add(bullet2);
				x2 = this.getX() + 9;
				Bullet bullet3 = new Bullet(x2, y2, 2, 2, 4, null, 0);
				Game.entities.add(bullet3);
				break;
				
			case 3:
				Sound.Shoot3[lastBullet].play(Sound.effectsVolume);
				if(lastBullet < maxBullet)
					lastBullet++;
				else
					lastBullet = 0;
				/* original com tiro diagonal: 
				int x3 = this.getX() + 1;
				int y3 = this.getY() + 4;
				Bullet bullet4 = new Bullet(x3, y3, 2, 2, 4, null, 1);
				Game.entities.add(bullet4);
				x3 = this.getX() + 5;
				Bullet bullet5 = new Bullet(x3, y3, 2, 2, 4, null, 0);
				Game.entities.add(bullet5);
				x3 = this.getX() + 9;
				Bullet bullet6 = new Bullet(x3, y3, 2, 2, 4, null, 0);
				Game.entities.add(bullet6);
				x3 = this.getX() + 13;
				Bullet bullet7 = new Bullet(x3, y3, 2, 2, 4, null, 2);
				Game.entities.add(bullet7);
				*/
				
				// com 3 tiros para matar asteroid grande sem sobra de tiro
				int x3 = this.getX() + 7;
				int y3 = this.getY();
				Bullet bullet4 = new Bullet(x3, y3, 2, 2, 4, null, 0);
				Game.entities.add(bullet4);
				x3 = this.getX() + 5;
				y3 = this.getY() + 4;
				Bullet bullet5 = new Bullet(x3, y3, 2, 2, 4, null, 0);
				Game.entities.add(bullet5);
				x3 = this.getX() + 9;
				Bullet bullet6 = new Bullet(x3, y3, 2, 2, 4, null, 0);
				Game.entities.add(bullet6);
				//x3 = this.getX() + 13;
				//Bullet bullet7 = new Bullet(x3, y3, 2, 2, 4, null, 2);
				//Game.entities.add(bullet7);
				
				break;

				
			case 4:
				Sound.Shoot3[lastBullet].play(Sound.effectsVolume);
				if(lastBullet < maxBullet)
					lastBullet++;
				else
					lastBullet = 0;
				int x4 = this.getX() + 1;
				int y4 = this.getY() + 4;
				Bullet bullet8 = new Bullet(x4, y4, 2, 2, 4, null, 0);
				Game.entities.add(bullet8);
				x4 = this.getX() + 5;
				Bullet bullet9 = new Bullet(x4, y4, 2, 2, 4, null, 0);
				Game.entities.add(bullet9);
				x4 = this.getX() + 9;
				Bullet bullet10 = new Bullet(x4, y4, 2, 2, 4, null, 0);
				Game.entities.add(bullet10);
				x4 = this.getX() + 13;
				Bullet bullet11 = new Bullet(x4, y4, 2, 2, 4, null, 0);
				Game.entities.add(bullet11);
				break;
				
			case 5:
				Sound.Shoot3[lastBullet].play(Sound.effectsVolume);
				if(lastBullet < maxBullet)
					lastBullet++;
				else
					lastBullet = 0;
				int x5 = this.getX() + 1;
				int y5 = this.getY() + 4;
				Bullet bullet12 = new Bullet(x5, y5, 2, 2, 4, null, 0);
				Game.entities.add(bullet12);
				Bullet bullet13 = new Bullet(x5, y5, 2, 2, 4, null, 1);
				Game.entities.add(bullet13);
				x5 = this.getX() + 5;
				Bullet bullet14 = new Bullet(x5, y5, 2, 2, 4, null, 0);
				Game.entities.add(bullet14);
				x5 = this.getX() + 9;
				Bullet bullet15 = new Bullet(x5, y5, 2, 2, 4, null, 0);
				Game.entities.add(bullet15);
				x5 = this.getX() + 13;
				Bullet bullet16 = new Bullet(x5, y5, 2, 2, 4, null, 0);
				Game.entities.add(bullet16);
				Bullet bullet17 = new Bullet(x5, y5, 2, 2, 4, null, 2);
				Game.entities.add(bullet17);
				break;
			}
		}
	}
}

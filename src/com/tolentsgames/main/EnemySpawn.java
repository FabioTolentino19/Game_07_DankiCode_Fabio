package com.tolentsgames.main;

import com.tolentsgames.entities.Enemy;
import com.tolentsgames.entities.Entity;

public class EnemySpawn {
	
	public int targetTime = 60 * 2;
	public int curTime = 0;
	public int currentEnemy = 0;
	public int lastEnemy = 0;
	public int changeEnemy = 7;
	public static int numEnemies = 0;
	public static int lastExploUsed = 0;
	public static int maxExploUsed = 5;
	public static int level = 1;
	public static int maxLevel = 2;
	
	public void tick() {
		curTime++;
		if(curTime == targetTime) {
			targetTime = Entity.rand.nextInt(100) + 1;
			curTime = 0;
			currentEnemy++;
			int yy = 0;
			int xx = 0;
			
			xx = Entity.rand.nextInt(Game.WIDTH - 16);
			
			if(level == 1) {
			
				if(currentEnemy == changeEnemy && lastEnemy == 0) {
					currentEnemy = 0;
					lastEnemy++;
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(32, 0, 16, 16), 2, 2);
					Game.entities.add(enemy);
				} else if(currentEnemy == changeEnemy && lastEnemy == 1) {
					currentEnemy = 0;
					lastEnemy++;
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(48, 0, 16, 16), 3, 3);
					Game.entities.add(enemy);
				} else if(currentEnemy == changeEnemy && lastEnemy == 2) {
					currentEnemy = 0;
					lastEnemy++;
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(64, 0, 16, 16), 4, 4);
					Game.entities.add(enemy);
				} else if(currentEnemy == changeEnemy && lastEnemy == 3) {
					currentEnemy = 0;
					lastEnemy = 0;
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(80, 0, 16, 16), 5, 5);
					Game.entities.add(enemy);
				} else {
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(16, 0, 16, 16), 1, 1);
					Game.entities.add(enemy);
				}
			}
			
			if(level == 2) {
				
				if(currentEnemy == changeEnemy && lastEnemy == 0) {
					currentEnemy = 0;
					lastEnemy++;
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(16, 64, 16, 16), 1, 7);
					Game.entities.add(enemy);
				} else if(currentEnemy == changeEnemy && lastEnemy == 1) {
					currentEnemy = 0;
					lastEnemy++;
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(32, 64, 16, 16), 1, 8);
					Game.entities.add(enemy);
				} else if(currentEnemy == changeEnemy && lastEnemy == 2) {
					currentEnemy = 0;
					lastEnemy++;
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(48, 64, 16, 16), 1, 9);
					Game.entities.add(enemy);
				} else if(currentEnemy == changeEnemy && lastEnemy == 3) {
					currentEnemy = 0;
					lastEnemy = 0;
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(64, 64, 16, 16), 1, 10);
					Game.entities.add(enemy);
				} else {
					Enemy enemy = new Enemy(xx, yy, 16, 16, Entity.rand.nextInt(2-1)+1, Game.spritesheet.getSprite(0, 64, 16, 16), 1, 6);
					Game.entities.add(enemy);
				}
			}
			
			numEnemies++;
		}
	}
}

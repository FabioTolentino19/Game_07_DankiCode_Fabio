package com.tolentsgames.main;

import com.tolentsgames.entities.Entity;
import com.tolentsgames.entities.Fuel;
import com.tolentsgames.entities.Municao;

public class MunicaoSpawn {
		
	public int targetTime = 60 * 2;
	public int curTime = 0;
	public static int numFuel = 0;
	public static int lastExploUsed = 0;
	public static int maxExploUsed = 5;
	
	public void tick() {
		curTime++;
		if(curTime == targetTime) {
			targetTime = Entity.rand.nextInt(300) + 200;
			curTime = 0;
			int yy = 0;
			int xx = Entity.rand.nextInt(Game.WIDTH - 16);
			Municao municao = new Municao(xx, yy, 16, 16, Entity.rand.nextInt(3-1)+1, Game.spritesheet.getSprite(16, 32, 16, 16));
			Game.entities.add(municao);
			numFuel++;
		}
	}
}
package excx.entities.boss;

import excx.collision.CollisionRotatedAABB;
import excx.entities.BasicBullet;
import excx.entities.patterns.ShotPatternDelayer;
import excx.entities.patterns.ShotPatternSwirl;

public class BossOldMiniBoss extends Boss{
	
	public BossOldMiniBoss() {
		super(0,-64);
		this.setCollisionType(new CollisionRotatedAABB(48,48));
		this.getCollisionType().setRotation(-Math.PI/4);
		
	}
	
	@Override
	public void goToNextStage(){
		switch(deaths){
		case 1:
			tarY= getWorld().getHeight()/2;
			break;
		case 2:
			tarY = 100;
			break;
		}
	}
	
	@Override
	public void tick() {
		if(getTimer() == 0){
			this.setX(getWorld().getWidth()/2);
			tarX = getWorld().getWidth()/2;
			tarY = 100;
			
			/**
			 * BossContainer holds the boss' current health, current attack pattern, and recursively prepares the next BossContainer.
			 * If no BossContainer remains, the boss is dead.
			 * 
			 */
			setCurrentBossContainer(new BossContainer(500,new ShotPatternSwirl(getWorld(), 2, 2, 2, 1 , 93, new BasicBullet(getX(),getY(), null)),
									new BossContainer(500,new ShotPatternSwirl(getWorld(), 4, 2, 2, 1 , 360, new BasicBullet(getX(),getY(), null)),
									new BossContainer(500,new ShotPatternDelayer(getWorld(), new ShotPatternSwirl(getWorld(), 8, 1.5, 1,8.0, 30, new BasicBullet(getX(),getY(), null)),30), null))));
		}
		super.tick();
	}

	@Override
	public Object clone() {
		BossOldMiniBoss b = new BossOldMiniBoss();
		return b;
	}
	
	public String toString(){
		return super.toString()+"_old";
	}

}

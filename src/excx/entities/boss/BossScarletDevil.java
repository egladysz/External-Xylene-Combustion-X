package excx.entities.boss;

import excx.entities.BasicBullet;
import excx.entities.patterns.ShotPatternSwirl;
import excx.movement.Movement;

public class BossScarletDevil extends Boss{

	public BossScarletDevil() {
		super(0, -64);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void goToNextStage() {
		switch(deaths){
		case 1:
			//TODO:set tarX and tarY.
			break;
		case 2:
			//TODO:etc...
			break;
		}
	}

	@Override
	public void tick() {
		if(getTimer() == 0){
			this.setX(getWorld().getWidth()/2);// standard to enter from center?
			tarX = 0;//TODO: Set initial values.
			tarY = 0;
			
			//TODO: Make unique stage.
			setCurrentBossContainer(new BossContainer(500,new ShotPatternSwirl(getWorld(), 6, 2, 2, 1, 360, new BasicBullet(getX(),getY(), new Movement(null,null))),
									new BossContainer(500,new ShotPatternSwirl(getWorld(), 1, 2, 2, 1, 360, new BasicBullet(getX(),getY(), new Movement(null,null))),
									new BossContainer(500,new ShotPatternSwirl(getWorld(), 2, 2, 2, 1, 93, new BasicBullet(getX(),getY(), new Movement(null,null))), null))));
		}
		super.tick();
	}

	@Override
	public Object clone() {
		return new BossScarletDevil();
	}
	
	public String toString(){
		return super.toString()+"_scarlet";
	}
}

package excx.entities.remilia;

import excx.collision.CollisionAABB;
import excx.data.SpriteSheet;
import excx.entities.BasicBullet;
import excx.entities.Bullet;
import excx.entities.Enemy;
import excx.entities.Turret;
import excx.entities.items.ItemPowerUp;
import excx.movement.Movement;
import excx.state.StateGame;

public class Ted extends Enemy{
	private boolean isLeft;
	
	private double tarX;
	private double tarY;
	Bullet b = new BasicBullet(getX(), getY(), new Movement(null,null));
	Turret[] guns = {new Turret(b),new Turret((Bullet) b.clone()), new Turret((Bullet) b.clone())};
	
	public Ted(boolean left) {
		super(0, -50);
		setLeft(left);
		this.setCollisionType(new CollisionAABB(64,64));
		this.setHealth(50);
		
	}

	@Override
	public void tick() {
		setImageNode(SpriteSheet.getInstance().getSpriteNode(toString()));
		if(getTimer() == 0){
			tarX = 50;
			tarY = 50;
			if(!isLeft()){tarX = getWorld().getWidth()-50;}
			setX(tarX);
			setY(-50);
			getMovement().setXVelocity(0);
			getMovement().setYVelocity((tarY-getY())/60D);
		}
		setTimer(getTimer() + 1);
		if(getTimer()%240==60){
			tarX = getWorld().getWidth()/2 + (isLeft()?-50:50);
			getMovement().setXVelocity((tarX-getX())/60D);
			getMovement().setYVelocity((tarY-getY())/60D);
		}
		else if(getTimer()%240==120){
			tarY = getY()+100;
			getMovement().setXVelocity((tarX-getX())/60D);
			getMovement().setYVelocity((tarY-getY())/60D);
		}
		else if(getTimer()%240==180){
			tarX = (!isLeft()?getWorld().getWidth()-50:50);
			getMovement().setXVelocity((tarX-getX())/60D);
			getMovement().setYVelocity((tarY-getY())/60D);
		}
		else if(getTimer()%240==0){
			tarY = getY()-100;
			getMovement().setXVelocity((tarX-getX())/60D);
			getMovement().setYVelocity((tarY-getY())/60D);
		}
		
		if(getTimer() % 60 == 0){
			for(int i = 0; i < guns.length;i++){
				guns[i].aim.setMagnitude(2);
				guns[i].aim.setAngle(Math.atan2(StateGame.you.getY()-getY(),StateGame.you.getX()-getX()), false);
				guns[i].aim.setAngle(guns[i].aim.getAngleDegrees()+(30*(i-1)), true);
				guns[i].shoot(getX(), getY(), getWorld(), getTeam());
			}
		}
		setX(getX()+getMovement().getXVelocity());
		setY(getY()+getMovement().getYVelocity());
		checkCollision(getWorld());
	}
	
	

	@Override
	public Object clone() {
		return new Ted(isLeft());
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	@Override
	public void die() {
		super.die();
		for(int i = 0; i < 7; i++){
			getWorld().add(new ItemPowerUp(getX()+Math.random()*64-32,getY()+Math.random()*64-32));
		}
		
	}
	public String toString(){
		return super.toString() + "_remilia_ted";
	}
}

package excx.entities.player;

import excx.collision.CollisionCircle;
import excx.collision.CollisionType;
import excx.data.Keyboard;
import excx.entities.exception.HomingBullet;
import excx.movement.Movement;

/**
 * Inspiration: Reimu
 * Slow and controlled.
 * Attack is designed to always hit, using wide attacks and homing misiles.
 * Special: MISSILE BARRAGE
 */


public class PlayerMeta extends Player{
	CollisionType copy;
	private HomingBullet shotType2 = new HomingBullet(0,0, new Movement(null,null),true);
	
	public PlayerMeta(double x, double y) {
		super(x, y);
		this.setSpeed(4);
		this.setMaxPower(30);
		copy = getCollisionType();
		shotType = new HomingBullet(0,0, new Movement(null,null),false);
		
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return super.toString() + "_meta";
	}

	@Override
	public void shoot() {
		
		if(getCooldown()==0){
			switch(getPower()/10){
			case 3:
				HomingBullet a = (HomingBullet)shotType2.clone();
				a.setOn(Keyboard.getInstance().shiftPressed);
				a.getMovement().setYVelocity(-6);
				a.getMovement().setVelocityAngle(-30, true);
				a.setX(getX());
				a.setY(getY());
				a.setTeam(getTeam());
				getWorld().add(a);
				a = (HomingBullet)a.clone();
				a.setOn(Keyboard.getInstance().shiftPressed);
				a.setTeam(getTeam());
				a.getMovement().setXVelocity(-a.getMovement().getXVelocity());
				getWorld().add(a);
			case 2:
				HomingBullet b = (HomingBullet)shotType2.clone();
				b.setOn(Keyboard.getInstance().shiftPressed);
				b.getMovement().setYVelocity(-6);
				b.getMovement().setVelocityAngle(-60, true);
				b.setX(getX());
				b.setY(getY());
				b.setTeam(getTeam());
				getWorld().add(b);
				b = (HomingBullet)b.clone();
				b.setTeam(getTeam());
				b.getMovement().setXVelocity(-b.getMovement().getXVelocity());
				b.setOn(Keyboard.getInstance().shiftPressed);
				getWorld().add(b);
			case 1:
				HomingBullet c = (HomingBullet)shotType2.clone();
				c.setOn(Keyboard.getInstance().shiftPressed);
				c.getMovement().setYVelocity(-6);
				c.getMovement().setVelocityAngle(-90, true);
				c.setX(getX());
				c.setY(getY());
				c.setTeam(getTeam());
				getWorld().add(c);
				c = (HomingBullet)c.clone();
				c.setOn(Keyboard.getInstance().shiftPressed);
				c.setTeam(getTeam());
				getWorld().add(c);
			}
			setCooldown(15);
		}
		
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bomb(int i) {
		// TODO Make bomb.
		HomingBullet c = (HomingBullet)shotType2.clone();
		c.setOn(true);
		c.getMovement().setYVelocity(-6);
		c.getMovement().setVelocityAngle(Math.random()*Math.PI*2,false);
		c.setX(getX());
		c.setY(getY());
		c.setTeam(getTeam());
		getWorld().add(c);
		this.setCollisionType(new CollisionCircle((180-i)*2));
		if(i == 180){
			this.setCollisionType(copy);
			this.setPlayerState(new PlayerStateNominal(this));
		}
	}
}

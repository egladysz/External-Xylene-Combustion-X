package com.ExceptionX.SCP.entities.player;

import com.ExceptionX.SCP.collision.CollisionCircle;
import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.entities.BasicBullet;
import com.ExceptionX.SCP.entities.Bullet;
import com.ExceptionX.SCP.movement.Movement;

/**
 * 
 * ~~~~~~~~~~~~~~~~~~~
 *
 */

public class PlayerXylol extends Player{

	public PlayerXylol(double x, double y) {
		super(x, y);
		this.setSpeed(4);
		this.setCollisionType(new CollisionCircle(1));
		setMovement(new Movement(null,null));
		this.setLives(5799316);
		shotType = new BasicBullet(0,0, new Movement(null,null));
		shotType.setImageNode(SpriteSheet.getInstance().getSpriteNode("bullet_xylol"));
		this.setMaxPower(20);
		this.setPower(20);
	}

	@Override
	public void shoot() {
		if (getCooldown() == 0){
			setCooldown(2);
			Bullet b = (Bullet) shotType.clone();
			b.setX(this.getX());
			b.setY(this.getY());
			b.getMovement().setVelocity(8);
			b.getMovement().setVelocityAngle(Math.random()*Math.PI*2, false);
			getWorld().add(b);
		}
	}

	@Override
	public Object clone() {
		return new PlayerXylol(this.getX(),this.getY());
	}
	
	public String toString(){
		return super.toString() + "_xylol";
	}

	@Override
	public void bomb(int i) {
		this.setLives(1);
		this.setPlayerState(new PlayerStateDying(this));
	}
}

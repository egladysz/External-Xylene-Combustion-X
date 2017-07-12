package com.ExceptionX.SCP.entities;

import java.awt.Graphics2D;

import com.ExceptionX.SCP.collision.CollisionCircle;
import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.movement.Movement;



public class BasicBullet extends Bullet {
	
	public BasicBullet(double x, double y, Movement type) {
		super(x, y, type);
		this.setImageNode(SpriteSheet.getInstance().getSpriteNode("bullet_circle_red_medium"));
		setCollisionType(new CollisionCircle(getImageNode().getImage().getWidth(null)));
	}
	
	@Override
	public Object clone() {
		BasicBullet b = new BasicBullet(this.getX(),this.getY(),(Movement)(this.getMovement().clone()));
		b.setImageNode(getImageNode());
		return b;
	}
	@Override
	public void render(Graphics2D g) {
		render(0,0,g);
	}

	@Override
	public void checkCollision(GameField world) {
		
	}

	@Override
	public void tick() {
		setTimer(getTimer() + 1);
		this.getCollisionType().setRotation(Math.PI/2-getMovement().getVelocityAngleRadians());
		if(getMovement().getVelocity() ==0 && getMovement().getAcceleration() ==0) {setDeletable(true); return;}
		if(getX()<0-getImageNode().getImage().getWidth(null)-100 ||getX()>getWorld().getWidth() +getImageNode().getImage().getWidth(null) +100) {setDeletable(true); return;}
		if(getY()<0-getImageNode().getImage().getHeight(null)-100||getY()>getWorld().getHeight()+getImageNode().getImage().getHeight(null)+100) {setDeletable(true); return;}
		setLocation(getX()+getMovement().getXVelocity(),getY()+getMovement().getYVelocity());
		movement.update();
	}
}

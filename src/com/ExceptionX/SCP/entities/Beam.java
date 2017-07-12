package com.ExceptionX.SCP.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ExceptionX.SCP.collision.CollisionAABB;
import com.ExceptionX.SCP.movement.Movement;

public class Beam extends BasicBullet{

	public Beam(double x, double y, Movement type) {
		super(x, y/2, type);
		this.setCollisionType(new CollisionAABB(6,y));
		this.setDeletable(true);
	}
	@Override
	public void render(double x2, double y2, Graphics2D g){
		g.setColor(Color.CYAN);
		g.fillRect((int)(getX()+x2-3), (int)y2, 6, (int)getY()*2);
	}
	public void tick(){
		
	}
	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return new Beam(getX(), getY()*2, (Movement) getMovement().clone());
	}

}

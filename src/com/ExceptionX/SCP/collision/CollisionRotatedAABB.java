package com.ExceptionX.SCP.collision;

public class CollisionRotatedAABB extends CollisionAABB{
	
	public CollisionRotatedAABB(double w, double h) {
		super(w, h);
	}
	@Override
	public void setRotation(double ang){
		rotation = ang;
		angles[0] = 0+rotation;
		angles[1] = Math.PI/2+rotation;
	}
	
}

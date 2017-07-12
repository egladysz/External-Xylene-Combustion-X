package com.ExceptionX.SCP.collision;

import java.awt.Graphics2D;

import com.ExceptionX.SCP.movement.Vector;

public abstract class CollisionType {
	public abstract Vector[] getProjAngles();
	public abstract double getRadius(double angle);
	public abstract Vector getVectorDistance(double angle);
	public abstract void render(double x2, double y2, Graphics2D g, double x, double y);
	public void render(Graphics2D g, double x, double y){
		render(0,0,g,x,y);
	}
	public void setRotation(double angle){
		
	}
	public abstract Vector getLongestVectorDistance(double angleRadians, Vector v);
}

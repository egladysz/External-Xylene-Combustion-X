package com.ExceptionX.SCP.collision;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ExceptionX.SCP.movement.Vector;
import com.ExceptionX.SCP.movement.VectorMath;

public class CollisionAABB extends CollisionType{
	public double width;
	public double height;
	public double angleToCorner;
	double rotation = 0;
	double[] angles = {Math.PI/2+rotation,rotation};
	public double[] relX = {Math.cos(angleToCorner+rotation)*width/2,Math.cos(Math.PI-angleToCorner+rotation)*width/2,Math.cos(Math.PI+angleToCorner+rotation)*width/2,Math.cos(-angleToCorner+rotation)*width/2};
	public double[] relY = {Math.sin(angleToCorner+rotation)*height/2,Math.sin(Math.PI-angleToCorner+rotation)*height/2,Math.sin(Math.PI+angleToCorner+rotation)*height/2,Math.sin(-angleToCorner+rotation)*height/2};
	
	public CollisionAABB(double w, double h){
		width = w/2;
		height = h/2;
		angleToCorner = Math.atan2(height, width);
	}
	
	public double getRadius(double ang) {
		ang = ang + rotation;
		while(ang >= Math.PI){
			ang -= Math.PI;
		}
		while(ang <= -Math.PI){
			ang += Math.PI;
		}
		if((Math.abs(ang) < this.angleToCorner)
		|| (Math.abs(ang-Math.PI) < this.angleToCorner)
		|| (Math.abs(ang+Math.PI) < this.angleToCorner)){
			return Math.abs(1.0*this.width/Math.cos(ang));
		} else {
			return Math.abs(1.0*this.height/Math.sin(ang));
		}
	}

	@Override
	public Vector getVectorDistance(double angle) {
		Vector v =  new Vector(0,0);
		v.setXMagnitude(Math.cos(angle+rotation)*getRadius(angle+rotation));
		v.setYMagnitude(Math.sin(angle+rotation)*getRadius(angle+rotation));
		return v;
	}

	@Override
	public Vector[] getProjAngles() {
		Vector[] vec = new Vector[angles.length];
		for(int i = 0;i < vec.length; i++){
			vec[i] = new Vector(0,1);
			vec[i].setAngle(angles[i], false);
		}
		return vec;
	}
	
	public String toString(){
		String s = this.getClass().getSimpleName();
		for (Vector v: getProjAngles()){
			s = s  +"\n\t" + v;
		}
		return s + "\n";
	}

	@Override
	public void render(double x2, double y2, Graphics2D g, double x, double y) {
		g.setColor(Color.MAGENTA);
		for(int i = 0; i < 360;i++){
			g.drawLine((int)(x+x2), (int)(y+y2), (int)(x+x2+Math.cos(Math.toRadians(i))*getRadius(Math.toRadians(i))), (int)(y+y2+Math.sin(Math.toRadians(i))*getRadius(Math.toRadians(i))));
		}
	}

	@Override
	public Vector getLongestVectorDistance(double angleRadians, Vector v) {
		Vector a = VectorMath.projection(getVectorDistance(angleToCorner),v);
		Vector b = VectorMath.projection(getVectorDistance(-angleToCorner),v);
		return a.getMagnitude()>b.getMagnitude()?a:b;
	}
}

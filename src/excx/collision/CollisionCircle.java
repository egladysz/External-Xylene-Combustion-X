package excx.collision;

import java.awt.Color;
import java.awt.Graphics2D;

import excx.movement.Vector;

public class CollisionCircle extends CollisionType{
	
	private double radius;
	
	public CollisionCircle(double d){
		radius = d/2;
	}
	
	@Override
	public double getRadius(double angle) {
		return radius;
	}

	@Override
	public Vector getVectorDistance(double angle) {
		// TODO Auto-generated method stub
		Vector v =  new Vector(0,0);
		v.setXMagnitude(Math.cos(angle)*getRadius(angle));
		v.setYMagnitude(Math.sin(angle)*getRadius(angle));
		return v;
	}

	@Override
	public Vector[] getProjAngles() {
		return new Vector[0];
	}

	@Override
	public void render(double x2, double y2, Graphics2D g, double x, double y) {
		g.setColor(Color.MAGENTA);
		g.fillOval((int)(x2+x-radius), (int)(y2+y-radius), (int)(radius*2), (int)(radius*2));
	}

	@Override
	public Vector getLongestVectorDistance(double angleRadians, Vector v) {
		return getVectorDistance(angleRadians);
	}
	
}

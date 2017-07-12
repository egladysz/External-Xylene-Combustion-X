package com.ExceptionX.SCP.collision;

import java.awt.Graphics2D;

import com.ExceptionX.SCP.movement.Vector;

public class CollisionNo extends CollisionType{

	public double getRadius(double angle) {
		return -1;
	}

	@Override
	public Vector getVectorDistance(double angle) {
		return null;
	}
	public Vector getLongestVectorDistance(double angle, Vector v) {
		return null;
	}

	@Override
	public Vector[] getProjAngles() {
		return new Vector[0];
	}

	@Override
	public void render(double x2, double y2, Graphics2D g, double x, double y) {
		//Do Nothing
	}

}

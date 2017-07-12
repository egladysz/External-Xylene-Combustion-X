package com.ExceptionX.SCP.movement;

public abstract class VectorMath {
	
	public static Vector add(Vector a, Vector b){ // {aX + bX, aY + bY}
		return new Vector(a.getXMagnitude()+b.getXMagnitude(),a.getYMagnitude()+b.getYMagnitude());
	}
	
	public static Vector scalarMultiply(Vector a,double b){ // {b*aX, b*aY}
		return new Vector(a.getXMagnitude()*b,a.getYMagnitude()*b);
	}
	
	public static double dotProduct(Vector a, Vector b){ // aX*bX + aY*bY
		return a.getXMagnitude()*b.getXMagnitude() + a.getYMagnitude()*b.getYMagnitude();
	}
	
	public static Vector projection(Vector a, Vector b){ // (aX*bX + aY*bY)/||b|| in direction b
		double s = dotProduct(a,b)/b.getMagnitude();
		Vector v = (Vector)(b.clone());
		v.setMagnitude(s);
		return v;
	}
	
	public static Vector rejection(Vector a, Vector b){ // a-a[projection]b
		return VectorMath.add(a, VectorMath.scalarMultiply(VectorMath.projection(a, b), -1));
	}
	public static Vector normalize(Vector a){
		Vector b = (Vector) a.clone();
		b.setMagnitude(1);
		return b;
		
	}
	
}

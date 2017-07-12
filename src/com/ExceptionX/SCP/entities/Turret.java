package com.ExceptionX.SCP.entities;
import com.ExceptionX.SCP.movement.Vector;


public class Turret {
	Bullet ammo;
	public Vector aim;
	
	
	public Turret(Bullet b){
		this(b,new Vector(0,1));
	}
	public Turret(Bullet b, Vector v){
		ammo = b;
		aim = v;
	}
	public void shoot(double x, double y,GameField f, Team team){
		Bullet b = (Bullet) ammo.clone();
		b.setLocation(x,y);
		b.setTeam(team);
		b.getMovement().setVelocityVector((Vector) aim.clone());
		f.add(b);
	}
}

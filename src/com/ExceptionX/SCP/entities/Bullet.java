package com.ExceptionX.SCP.entities;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import com.ExceptionX.SCP.movement.Movement;


public abstract class Bullet extends Entity{
	//private Image picture;
	
	public Bullet(double x, double y, Movement type){
		super(x,y);
		
		setMovement(type);
		if(type == null){
			setMovement(new Movement(null,null));
		}
	}
	@Override
	public void render(double x2, double y2, Graphics2D g){
		AffineTransform af = new AffineTransform();
		af.translate(x2+getX(), y2+getY());
		af.rotate(getMovement().getVelocityAngleRadians()+Math.PI/2);
		g.transform(af);
		g.drawImage(getImageNode().getImage(),-(getImageNode().getImage().getWidth(null))/2,-(getImageNode().getImage().getHeight(null))/2,null);
		try {
			g.transform(af.createInverse());
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//g.drawImage(getImageNode().getImage(),(int)(getX()+x2-getImageNode().getImage().getWidth(null)/2),(int)(getY()+y2-getImageNode().getImage().getHeight(null)/2), null);
	}
	public String toString(){
		return "bullet";
		
	}
}

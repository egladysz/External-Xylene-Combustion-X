package com.ExceptionX.SCP.entities;

import java.awt.Graphics2D;

import com.ExceptionX.SCP.data.Keyboard;
import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.movement.Movement;

public class OrbitalBullet extends BasicBullet{
	Entity target;
	public OrbitalBullet(double x, double y, Movement type, Entity e) {
		super(x, y, type);
		setImageNode(SpriteSheet.getInstance().getSpriteNode("bullet_triangle_red_medium"));
		target = e;
	}
	public void tick(){
		setTimer(getTimer() + 1);
		//setLocation((getX()+getMovement().getXVelocity())%getWorld().getWidth(),(getY()+getMovement().getYVelocity())%getWorld().getHeight());
		//if(getX()<0)setX(getX()+getWorld().getWidth());
		//if(getY()<0)setY(getY()+getWorld().getHeight());
		movement.setAccelerationAngle(Math.atan2(target.getY()-getY(), target.getX()-getX()), false);
		double dx = getX()-target.getX();
		double x = dx*dx;
		double dy = getY()-target.getY();
		double y = dy*dy;
		double z = Math.sqrt(x+y);
		movement.setAcceleration(40D/(z*z));
		if(Keyboard.getInstance().shiftPressed){
			movement.setAcceleration(0);
		}
		
		
		
		if(Keyboard.getInstance().xPressed){
			movement.setAcceleration(-movement.getAcceleration());
			movement.update();
			setLocation((getX()-getMovement().getXVelocity())%getWorld().getWidth(),(getY()-getMovement().getYVelocity())%getWorld().getHeight());
			if(getX()<0)setX(getX()+getWorld().getWidth());
			if(getY()<0)setY(getY()+getWorld().getHeight());
			movement.setAcceleration(-movement.getAcceleration());
			//movement.setVelocity(0);
			//movement.setVelocityAngle(Math.atan2(target.getY()-getY(), target.getX()-getX()), false);
		} else{
			movement.update();
			setLocation((getX()+getMovement().getXVelocity())%getWorld().getWidth(),(getY()+getMovement().getYVelocity())%getWorld().getHeight());
			if(getX()<0)setX(getX()+getWorld().getWidth());
			if(getY()<0)setY(getY()+getWorld().getHeight());
		}
	}
	@Override
	public void render(double x2, double y2, Graphics2D g) {
		/*g.setColor(Color.RED);
		double rx = StateGame.you.getX()-this.getX();
		double ry = StateGame.you.getY()-this.getY();
		double r = Math.sqrt(rx*rx+ry*ry);
		g.drawOval((int)(StateGame.you.getX()+x2-r),(int)(StateGame.you.getY()+y2-r),(int)(r*2),(int)(r*2));*/
		super.render(x2, y2, g);
	}
	public Object clone() {
		OrbitalBullet b = new OrbitalBullet(this.getX(),this.getY(),new Movement(null,null),this.target);
		return b;
	}

}

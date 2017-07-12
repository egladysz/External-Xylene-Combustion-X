package com.ExceptionX.SCP.entities.player;

import java.util.LinkedList;

import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.entities.BasicBullet;
import com.ExceptionX.SCP.entities.Bullet;
import com.ExceptionX.SCP.entities.Enemy;
import com.ExceptionX.SCP.entities.Entity;
import com.ExceptionX.SCP.entities.GameField;
import com.ExceptionX.SCP.entities.Sprite;
import com.ExceptionX.SCP.entities.TargetTurret;
import com.ExceptionX.SCP.movement.Movement;

public class OrthoBot extends Entity{
	
	TargetTurret gun;
	Enemy target;
	PlayerOrtho player;
	double targetX, targetY;
	double relativeX, relativeY;
	public boolean isActive;
	
	public OrthoBot(PlayerOrtho p, double rx, double ry) {
		super(p.getX()+rx,p.getY());
		player = p;
		this.setMovement(new Movement(null,null));
		this.setTeam(p.getTeam());
		setImageNode(SpriteSheet.getInstance().getSpriteNode("orthobot"));
		Bullet gunAmmo = new BasicBullet(0,0,new Movement(null,null));
		gunAmmo.setImageNode(SpriteSheet.getInstance().getSpriteNode("bullet_demo"));
		gun = new TargetTurret(gunAmmo, null, 8);
		targetX = p.getX()+rx;
		targetY = p.getY()+ry;
		relativeX = rx;
		relativeY = ry;
		setWorld(p.getWorld());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		targetX = player.getX()+relativeX;
		targetY = player.getY()+relativeY;
		getMovement().setXVelocity((targetX-getX())/10);
		getMovement().setYVelocity((targetY-getY())/10);
		setLocation(getX()+getMovement().getXVelocity(),getY()+getMovement().getYVelocity());
		LinkedList<Enemy> enemies = new LinkedList<Enemy>();
		if(true){
			for(Sprite e:getWorld().getEntityList()){
				if (e instanceof Enemy){
					enemies.add((Enemy)e);
				}
			}
			int minCopy = 100;
			for(int i = 0; i < enemies.size();i++){
				int copy = 0;
				for(OrthoBot b:player.bots){
					if ((!b.equals(this)) && b.isActive && b.target != null && b.target.equals(enemies.get(i))){
						copy++;
					}
				}
				if(copy<=minCopy){
					minCopy=copy;
				}
			}
			for(int i = 0; i < enemies.size();i++){
				int copy = 0;
				for(OrthoBot b:player.bots){
					if ((!b.equals(this)) && b.isActive && b.target != null && b.target.equals(enemies.get(i))){
						copy++;
					}
				}
				if(copy>minCopy){
					enemies.remove(i);
					i--;
				}
			}
			Enemy closest = null;
			double sqrDistance = 0;
			for(int i = 0; i < enemies.size();i++){
				if(closest == null){
					closest = enemies.get(i);
					sqrDistance = Math.pow(getX()-enemies.get(i).getX(),2)+Math.pow(getY()-enemies.get(i).getY(),2);
				}
				if(sqrDistance > Math.pow(getX()-enemies.get(i).getX(),2)+Math.pow(getY()-enemies.get(i).getY(),2)){
					closest = (Enemy) enemies.get(i);
					sqrDistance = Math.pow(getX()-enemies.get(i).getX(),2)+Math.pow(getY()-enemies.get(i).getY(),2);
				}
			}
			
			
			target = closest;
		}
		gun.target = target;
	}

	@Override
	public void checkCollision(GameField world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object clone() {
		return new OrthoBot(player, relativeX, relativeX);
	}

	public void shoot() {
		gun.aim.setAngle(270, true);
		gun.shoot(getX(), getY(), getWorld(), getTeam());
	}
	
}

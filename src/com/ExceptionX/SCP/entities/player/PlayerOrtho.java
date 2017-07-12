package com.ExceptionX.SCP.entities.player;

import java.awt.Graphics2D;

import com.ExceptionX.SCP.collision.CollisionCircle;
import com.ExceptionX.SCP.collision.CollisionType;
import com.ExceptionX.SCP.data.Keyboard;
import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.entities.BasicBullet;
import com.ExceptionX.SCP.entities.GameField;
import com.ExceptionX.SCP.movement.Movement;


/**
 * Inspiration: Alice/Sakuya
 * Of a standard speed
 * Attack is precise and directed strictly towards enemies.
 * Special: SATTELITE BOMB
 */
public class PlayerOrtho extends Player{
	
	OrthoBot[] bots;
	CollisionType copy;
	public PlayerOrtho(double x, double y) {
		super(x, y);
		this.setSpeed(4);
		this.setMaxPower(80);
		this.setPower(10);
		bots = new OrthoBot[getMaxPower()/10];
		for(int i = 0; i <bots.length; i++){
			bots[i] = new OrthoBot(this, 0,0);
		}
		shotType = new BasicBullet(0,0, new Movement(null,null));
		shotType.setImageNode(SpriteSheet.getInstance().getSpriteNode("bullet_demo"));
		copy = getCollisionType();
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return super.toString() + "_ortho";
	}

	@Override
	public void shoot() {
		if(getCooldown()==0){
			for(int i = 0; i < bots.length;i++){
				if(bots[i].isActive){
					if(Keyboard.getInstance().shiftPressed){
						bots[i].gun.target=null;
						bots[i].gun.aim.setAngle(270, true);
					}
					
					bots[i].shoot();
				}
			}
			setCooldown(18);
		}
	}
	
	public void tick(){
		super.tick();
		Keyboard c = Keyboard.getInstance();
		for(int i = 0; i < bots.length; i++){
			bots[i].relativeX = Math.sin((i*1.0/((int)(getPower()/10)))*Math.PI*2+(getTimer()/Math.PI/40))*(c.shiftPressed?-25:50);
			bots[i].relativeY = Math.cos((i*1.0/((int)(getPower()/10)))*Math.PI*2+(getTimer()/Math.PI/40))*(c.shiftPressed?-25:50);
		}
		for(int i = 0; i < getPower()/10;i++){
			bots[i].isActive = true;
		}
		for(int i = getPower()/10; i < bots.length;i++){
			bots[i].isActive = false;
			bots[i].setX(getX());
			bots[i].setY(getY());
		}
		
		for(int i = 0; i < bots.length; i++){
			bots[i].tick();
		}
		
		
	}
	
	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bomb(int i) {
		if(i == 0){
			OrthoBot b = bots[(getPower()/10)];
			getWorld().add(new OrthoBomb(b.getX(),b.getY(),(Movement) b.getMovement().clone()));
		}
		this.setCollisionType(new CollisionCircle((180-i)*2));
		if(i == 180){
			this.setCollisionType(copy);
			this.setPlayerState(new PlayerStateNominal(this));
		}
	}
	
	public void setWorld(GameField world) {
		super.setWorld(world);
		for(OrthoBot b: bots){
			b.setWorld(world);
		}
	}
	public void render(double dx, double dy, Graphics2D g){
		super.render(dx,dy,g);
		for(int i = 0; i < bots.length;i++){
			if(bots[i].isActive){
				bots[i].render(dx, dy, g);
			}
		}
	}
}

package com.ExceptionX.SCP.entities.player;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.ExceptionX.SCP.data.Keyboard;
import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.entities.Entity;
import com.ExceptionX.SCP.entities.GameField;
import com.ExceptionX.SCP.entities.Sprite;
import com.ExceptionX.SCP.entities.Team;

public class PlayerStateNominal extends PlayerState {
	
	
	public PlayerStateNominal(Player me) {
		super(me);
		System.out.println("Nominal...");
		getMe().setImageNode(SpriteSheet.getInstance().getSpriteNode(getMe().toString() + "_idle"));
	}
	@Override
	public void tick() {
		setTimer(getTimer() + 1);
		if(getTimer() %3 == 0){
			getMe().setImageNode(getMe().getImageNode().getNext());
		}
		int xa = 0, ya = 0;
		double tempSpeed = getMe().getSpeed();
		if(Keyboard.getInstance().upPressed)  ya--;
		if(Keyboard.getInstance().downPressed)ya++;
		if(Keyboard.getInstance().leftPressed)xa--;
		if(Keyboard.getInstance().rightPressed)xa++;
		if(Keyboard.getInstance().shiftPressed)tempSpeed/=2;
		getMe().getMovement().setXVelocity(xa*tempSpeed);
		getMe().getMovement().setYVelocity(ya*tempSpeed);
		getMe().move();
		this.checkCollision(getMe().getWorld());
		getMe().getMovement().setVelocity(0);
		
		if(Keyboard.getInstance().xTyped&&getMe().getPower()>=20){
			getMe().setPower(getMe().getPower()-10);
			getMe().setPlayerState(new PlayerStateBomb(getMe()));
		}
		if(Keyboard.getInstance().zPressed){
			getMe().shoot();
		}
	}

	@Override
	public void render(double dx, double dy, Graphics2D g) {
		g.drawImage(getMe().getImageNode().getImage(),(int)(getMe().getX()+dx-getMe().getImageNode().getImage().getWidth(null)/2),(int)(getMe().getY()+dy-getMe().getImageNode().getImage().getHeight(null)/2), null);
		if(Keyboard.getInstance().shiftPressed){
			getMe().getCollisionType().render(dx, dy, g, getMe().getX(), getMe().getY());
		}
	}

	@Override
	public void checkCollision(GameField world) {
		ArrayList<Sprite> copy = new ArrayList<Sprite>();
		copy.addAll(world.getEntityList());
		for(Sprite e:copy){
			if(e != getMe()&& e instanceof Entity &&(e.getTeam() == Team.Evil || e.getTeam() == Team.Nature) &&getMe().isColliding((Entity)e)){
				if(getMe().getLives()==1 && getMe().getPower()>=20){
					getMe().setPower(getMe().getPower()-10);
					getMe().setPlayerState(new PlayerStateBomb(getMe()));
					return;
				}
				getMe().setPlayerState(new PlayerStateDying(getMe()));
				return;
			}
		}
	}

}

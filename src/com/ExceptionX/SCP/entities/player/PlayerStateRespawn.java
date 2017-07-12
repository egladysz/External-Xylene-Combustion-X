package com.ExceptionX.SCP.entities.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.ExceptionX.SCP.ExcX;
import com.ExceptionX.SCP.collision.CollisionCircle;
import com.ExceptionX.SCP.collision.CollisionType;
import com.ExceptionX.SCP.data.Keyboard;
import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.entities.Bullet;
import com.ExceptionX.SCP.entities.Entity;
import com.ExceptionX.SCP.entities.GameField;
import com.ExceptionX.SCP.entities.Sprite;
import com.ExceptionX.SCP.entities.Team;
import com.ExceptionX.SCP.state.StateMainMenu;

public class PlayerStateRespawn extends PlayerState {
	CollisionType c;
	public PlayerStateRespawn(Player me) {
		super(me);
		c = getMe().getCollisionType();
		getMe().setLives(getMe().getLives()-1);
		if(getMe().getLives() ==0){
			ExcX.setState(new StateMainMenu());
		}
		System.out.println("Respawning...");
		getMe().setImageNode(SpriteSheet.getInstance().getSpriteNode(getMe().toString() + "_idle"));
		getMe().setX(getMe().getWorld().getWidth()/2);
		getMe().setY(getMe().getWorld().getWidth()*7/8);
	}

	@Override
	public void tick() {
		setTimer(getTimer() + 1);
		getMe().setCollisionType(new CollisionCircle(360-getTimer()*2));
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
		checkCollision(getMe().getWorld());
		if(getTimer() == 180/*TODO: decide on invuln time*/){
			getMe().setCollisionType(c);
			getMe().setPlayerState(new PlayerStateNominal(getMe()));;
		}
		if(Keyboard.getInstance().zPressed){
			getMe().shoot();
		}
	}

	@Override
	public void checkCollision(GameField world) {
		ArrayList<Sprite> copy = new ArrayList<Sprite>();
		copy.addAll(world.getEntityList());
		for(Sprite e:copy){
			if(e != getMe()&& e instanceof Entity &&(e.getTeam() == Team.Nature || e.getTeam() == Team.Evil) &&getMe().isColliding((Entity)e)){
				if(e instanceof Bullet)
					world.remove(e);
				continue;
			}
		}
	}

	@Override
	public void render(double dx, double dy, Graphics2D g) {
		g.setColor(Color.CYAN);
		g.drawOval((int)(dx+getMe().getX()-(180-getTimer())), (int)(dy+getMe().getY()-(180-getTimer())), (360-getTimer()*2), (360-getTimer()*2));
		if(getTimer() %8 >= 4){
			g.drawImage(getMe().getImageNode().getImage(),(int)(getMe().getX()+dx-getMe().getImageNode().getImage().getWidth(null)/2),(int)(getMe().getY()+dy-getMe().getImageNode().getImage().getHeight(null)/2), null);
		}
	}

}

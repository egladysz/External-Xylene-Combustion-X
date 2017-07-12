package excx.entities.player;

import java.awt.Color;
import java.awt.Graphics2D;

import excx.data.Keyboard;
import excx.data.SpriteSheet;
import excx.entities.GameField;

public class PlayerStateDying extends PlayerState{
	public PlayerStateDying(Player me) {
		super(me);
		System.out.println("Dying...");
		getMe().setImageNode(SpriteSheet.getInstance().getSpriteNode(getMe().toString() + "_dying"));
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
		
		if(getTimer() == 3*16/* TODO: replace zero with number of frames*/){
			getMe().setPlayerState(new PlayerStateRespawn(getMe()));
		}
	}
	@Override
	public void checkCollision(GameField world) {
		//do nothing
	}
	@Override
	public void render(double dx, double dy, Graphics2D g) {
		g.setColor(Color.red);
		g.fillOval((int)(dx+getMe().getX()-getTimer()*2), (int)(dy+getMe().getY()-getTimer()*2), getTimer()*4, getTimer()*4);
		g.setColor(Color.YELLOW);
		g.fillOval((int)(dx+getMe().getX()-getTimer()*2)+15, (int)(dy+getMe().getY()-getTimer()*2)+15, getTimer()*4-30, getTimer()*4-30);
		g.setColor(Color.WHITE);
		g.fillOval((int)(dx+getMe().getX()-getTimer()*2)+45, (int)(dy+getMe().getY()-getTimer()*2)+45, getTimer()*4-90, getTimer()*4-90);
		
		g.drawImage(getMe().getImageNode().getImage(),(int)(getMe().getX()+dx-getMe().getImageNode().getImage().getWidth(null)/2),(int)(getMe().getY()+dy-getMe().getImageNode().getImage().getHeight(null)/2), null);
	}

}

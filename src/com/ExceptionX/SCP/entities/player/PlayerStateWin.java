package com.ExceptionX.SCP.entities.player;

import java.awt.Graphics2D;

import com.ExceptionX.SCP.ExcX;
import com.ExceptionX.SCP.entities.GameField;

public class PlayerStateWin extends PlayerState{

	public PlayerStateWin(Player me) {
		super(me);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		getMe().setY(getMe().getY()-2);
		if(getMe().getY()<-32){
			ExcX.setState(ExcX.win);
		}
	}

	@Override
	public void render(double dx, double dy, Graphics2D g) {
		g.drawImage(getMe().getImageNode().getImage(),(int)(getMe().getX()+dx-getMe().getImageNode().getImage().getWidth(null)/2),(int)(getMe().getY()+dy-getMe().getImageNode().getImage().getHeight(null)/2), null);
	}

	@Override
	public void checkCollision(GameField world) {
		//Nothing.
		
	}

}

package com.ExceptionX.SCP.entities.boss;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ExceptionX.SCP.entities.Sprite;

public class Kaboom extends Sprite {
	int alpha = 255;
	public Kaboom(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(double x2, double y2, Graphics2D g) {
		g.setColor(new Color(255,255,255,alpha));
		g.fillOval((int)(getX()-(255-alpha)+x2),(int)(getY()-(255-alpha)+y2), (255-alpha)*2, (255-alpha)*2);
	}

	@Override
	public void tick() {
		alpha -= 5;
		if (alpha == 0){
			this.setDeletable(true);
		}
	}

	@Override
	public Object clone() {
		return new Kaboom(getX(),getY());
	}

}

package com.ExceptionX.SCP.entities.boss;

import java.awt.Color;
import java.awt.Graphics2D;

public class TinyKaboom extends Kaboom{

	int alpha = 255;
	public TinyKaboom(double x, double y) {
		super(x, y);
	}

	@Override
	public void render(double x2, double y2, Graphics2D g) {
		g.setColor(new Color(255,0,0,alpha));
		g.fillOval((int)(getX()-((255-alpha)/4)+x2),(int)(getY()-((255-alpha)/4)+y2), (255-alpha)/2, ((255-alpha)/2));
	}

	@Override
	public void tick() {
		alpha -= 10;
		if (alpha <= 0){
			this.setDeletable(true);
		}
	}

	@Override
	public Object clone() {
		return new Kaboom(getX(),getY());
	}

}

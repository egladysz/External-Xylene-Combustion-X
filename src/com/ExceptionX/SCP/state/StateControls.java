package com.ExceptionX.SCP.state;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ExceptionX.SCP.ExcX;
import com.ExceptionX.SCP.data.Keyboard;

public class StateControls extends State{

	@Override
	public void tick() {
		if(Keyboard.getInstance().escapePressed){
			ExcX.setState(ExcX.mainMenu);
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(40.0F));
		g.drawString("MENU", 50, 100);
		g.setFont(g.getFont().deriveFont(20.0F));
		g.drawString("ARROW KEYS: Change Selection", 60, 120);
		g.drawString("Z/ENTER: Select", 60, 140);
		g.setFont(g.getFont().deriveFont(40.0F));
		g.drawString("GAME", 50, 200);
		g.setFont(g.getFont().deriveFont(20.0F));
		g.drawString("ARROW KEYS: Movement", 60, 220);
		g.drawString("Z: Shoot", 60, 240);
		g.drawString("X: Special Attack", 60, 260);
		g.drawString("SHIFT: Precision while held", 60, 280);
		g.drawString("ESCAPE: Pause menu", 60, 300);
		g.setFont(g.getFont().deriveFont(15.0F));
		g.drawString("Press ESCAPE to return", 200, 350);
		
	}

	@Override
	public void prepare() {
		// Nothing to prepare...
	}

}

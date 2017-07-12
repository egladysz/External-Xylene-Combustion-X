package com.ExceptionX.SCP.state;

import java.awt.Graphics2D;
import java.awt.Image;

import com.ExceptionX.SCP.ExcX;
import com.ExceptionX.SCP.data.Keyboard;
import com.ExceptionX.SCP.data.SpriteSheet;

public class StateWin extends State{
	Image img = SpriteSheet.getInstance().getSpriteNode("win").getImage();
	@Override
	public void tick() {
		if(Keyboard.getInstance().escapePressed){
			ExcX.setState(ExcX.mainMenu);
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(img, 0, 0, null);
	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		
	}

}

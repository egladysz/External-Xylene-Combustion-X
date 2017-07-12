package com.ExceptionX.SCP.state;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ExceptionX.SCP.IO.ImageCollector;
import com.ExceptionX.SCP.data.Keyboard;
import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.buttons.ButtonCommandGameState;
import com.ExceptionX.SCP.buttons.ButtonCommandKill;
import com.ExceptionX.SCP.buttons.MainMenuButton;

public class StateMainMenu extends State {
	
	private MenuNode currentSelection;
	private MenuNode top;
	
	public StateMainMenu(){
		if (SpriteSheet.getInstance().getSpriteNode("title_full") == null)SpriteSheet.getInstance().addSpriteGroup("title_full", ImageCollector.getInstance().getImage("title_full"));
		
		MenuNode a = new MenuNode(new MainMenuButton(100,284,"PLAY",new ButtonCommandGameState(StateType.PLAYERSELECT)),null,null);//tp player select
		MenuNode b = new MenuNode(new MainMenuButton(120,314,"CONTROLS",new ButtonCommandGameState(StateType.CONTROLS)),null,a);//to options
		MenuNode d = new MenuNode(new MainMenuButton(140,344,"EXIT",new ButtonCommandKill()),a,b);//leave
		
		a.setNext(b);
		a.setPrevious(d);
		b.setNext(d);
		top = a;
		currentSelection = a;
		currentSelection.getValue().beSelected();
		a.getValue().setOffColor(Color.BLACK);
		a.getValue().setOnColor(Color.RED);
		while (b != currentSelection){
			if(b != null){
				b.getValue().setOffColor(Color.BLACK);
				b.getValue().setOnColor(Color.RED);
			}
			b = b.getNext();
		}
	}
	
	
	@Override
	public void tick() {
		if(Keyboard.getInstance().downTyped){
			currentSelection.getValue().beUnSelected();
			currentSelection = currentSelection.getNext();
			currentSelection.getValue().beSelected();
		}
		if(Keyboard.getInstance().upTyped){
			currentSelection.getValue().beUnSelected();
			currentSelection = currentSelection.getPrevious();
			currentSelection.getValue().beSelected();
		}
		if(Keyboard.getInstance().zTyped){
			if(currentSelection.getValue()!= null){
				currentSelection.getValue().press();
			}
				
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(SpriteSheet.getInstance().getSpriteNode("title_full").getImage(),0,0,null);
		MenuNode b = currentSelection.getNext();
		currentSelection.getValue().render(g);
		while (b != currentSelection){
			if(b != null)
				b.getValue().render(g);
			b = b.getNext();
		}
	}


	@Override
	public void prepare() {
		currentSelection.getValue().beUnSelected();
		currentSelection = top;
		currentSelection.getValue().beSelected();
	}

}

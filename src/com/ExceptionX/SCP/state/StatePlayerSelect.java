package com.ExceptionX.SCP.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import com.ExceptionX.SCP.buttons.ButtonCommandGameState;
import com.ExceptionX.SCP.buttons.ButtonCommandPlayerSelect;
import com.ExceptionX.SCP.buttons.MainMenuButton;
import com.ExceptionX.SCP.data.Keyboard;
import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.entities.player.PlayerType;

public class StatePlayerSelect extends State{
	MenuNode currentSelection;
	MenuNode top;
	Image img = SpriteSheet.getInstance().getSpriteNode("player_select").getImage();
	public StatePlayerSelect(){
		MenuNode a = new MenuNode(new MainMenuButton(60,300,"PARAXYLENE",new ButtonCommandPlayerSelect(PlayerType.PARAXYLENE)),null,null);
		MenuNode b = new MenuNode(new MainMenuButton(70,330,"METAXYLENE",new ButtonCommandPlayerSelect(PlayerType.METAXYLENE)),null,a);
		MenuNode c = new MenuNode(new MainMenuButton(80,360,"ORTHOXYLENE",new ButtonCommandPlayerSelect(PlayerType.ORTHOXYLENE)),null,b);
		MenuNode d = new MenuNode(new MainMenuButton(100,420,"BACK",new ButtonCommandGameState(StateType.MAINMENU)),a,c);
		a.setNext(b);
		b.setNext(c);
		c.setNext(d);
		a.setPrevious(d);
		top = a;
		a.getValue().setOffColor(Color.WHITE);
		a.getValue().setOnColor(Color.CYAN);
		currentSelection = a;
		while (b != currentSelection){
			if(b != null){
				b.getValue().setOffColor(Color.WHITE);
				b.getValue().setOnColor(Color.CYAN);
			}
			b = b.getNext();
		}
		a.getValue().beSelected();
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
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(40.0F));
		g.drawString("PLAYER SELECT",100,100);
		currentSelection.getValue().render(g);
		MenuNode b = currentSelection.getNext();
		while (b != currentSelection){
			if(b != null){
				b.getValue().render(g);
			}
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

package com.ExceptionX.SCP.buttons;

import com.ExceptionX.SCP.ExcX;
import com.ExceptionX.SCP.entities.player.PlayerType;

public class ButtonCommandPlayerSelect extends ButtonCommand{
	PlayerType p;
	
	public ButtonCommandPlayerSelect(PlayerType pl){
		p = pl;
	}
	
	@Override
	void execute() {
		ExcX.game.setPlayer(p);
		ExcX.game.prepare();
		ExcX.setState(ExcX.game);
	}

}

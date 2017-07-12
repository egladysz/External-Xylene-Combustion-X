package com.ExceptionX.SCP.buttons;

import com.ExceptionX.SCP.state.StateGame;

public class ButtonCommandUnpause extends ButtonCommand{
	
	StateGame game;
	
	public ButtonCommandUnpause(StateGame game){
		this.game = game;
	}
	
	@Override
	void execute() {
		game.setPaused(false);
	}

}

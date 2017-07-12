package com.ExceptionX.SCP.buttons;

import com.ExceptionX.SCP.ExcX;
import com.ExceptionX.SCP.state.StateType;


public class ButtonCommandGameState extends ButtonCommand{
	StateType gameState;
	public ButtonCommandGameState(StateType state){
		gameState = state;
	}

	@Override
	void execute() {
		switch(gameState){
		case GAME:
			ExcX.game.setPlayer(ExcX.game.yourName);
			ExcX.game.prepare();
			ExcX.setState(ExcX.game);
			break;
		case MAINMENU: default:
			ExcX.mainMenu.prepare();
			ExcX.setState(ExcX.mainMenu);
			break;
		case PLAYERSELECT:
			ExcX.playerSelect.prepare();
			ExcX.setState(ExcX.playerSelect);
			break;
		case CONTROLS:
			ExcX.controls.prepare();
			ExcX.setState(ExcX.controls);
			break;
			
		}
	}

}

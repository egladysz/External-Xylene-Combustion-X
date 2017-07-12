package com.ExceptionX.SCP.levelcommands;

import com.ExceptionX.SCP.entities.Sprite;
import com.ExceptionX.SCP.state.StateGame;

public class WorldCommandClear implements WorldCommand{

	@Override
	public void execute() {
		Sprite p = StateGame.field.getEntityList().get(0);
		StateGame.field.getEntityList().clear();
		StateGame.field.getEntityList().add(p);
	}

}

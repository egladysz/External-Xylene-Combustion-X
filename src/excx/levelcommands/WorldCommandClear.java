package excx.levelcommands;

import excx.entities.Sprite;
import excx.state.StateGame;

public class WorldCommandClear implements WorldCommand{

	@Override
	public void execute() {
		Sprite p = StateGame.field.getEntityList().get(0);
		StateGame.field.getEntityList().clear();
		StateGame.field.getEntityList().add(p);
	}

}

package excx.buttons;

import excx.ExcX;
import excx.entities.player.PlayerType;

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

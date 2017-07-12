package excx.buttons;

import excx.state.StateGame;

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

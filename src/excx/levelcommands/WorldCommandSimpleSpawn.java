package excx.levelcommands;

import excx.entities.Sprite;
import excx.state.StateGame;

public class WorldCommandSimpleSpawn implements WorldCommand{
	
	public Sprite sprite;
	
	public WorldCommandSimpleSpawn(Sprite e){
		sprite = e;
	}
	
	@Override
	public void execute() {
		StateGame.field.add((Sprite) sprite.clone());
		//System.out.println(sprite.toString());
	}
	
}

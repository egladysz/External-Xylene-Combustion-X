package excx.levelcommands;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import excx.entities.Entity;

public class WorldCommandSpawnMany implements WorldCommand{
	
	Queue<Entity> entities;
	
	public WorldCommandSpawnMany(){
		entities = new LinkedList<Entity>();
	}
	
	public WorldCommandSpawnMany(List<Entity> ls){
		entities = new LinkedList<Entity>();
		entities.addAll(ls);
	}
	public void addEntities(List<Entity> ls){
		entities.addAll(ls);
	}
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	
	@Override
	public void execute() {
		while(!entities.isEmpty()){
			entities.peek().getWorld().add(entities.remove());
		}
	}

}

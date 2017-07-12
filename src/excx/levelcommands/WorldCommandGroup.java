package excx.levelcommands;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WorldCommandGroup implements WorldCommand{
	public Queue<WorldCommand> commands;
	
	public WorldCommandGroup(){
		commands = new LinkedList<WorldCommand>();
	}
	public WorldCommandGroup(List<WorldCommand> list){
		commands = new LinkedList<WorldCommand>();
		commands.addAll(list);
	}
	public void addCommand(WorldCommand wc){
		commands.add(wc);
	}
	
	@Override
	public void execute() {
		Queue<WorldCommand> message = new LinkedList<WorldCommand>();
		message.addAll(commands);
		while(!message.isEmpty()){
			message.remove().execute();
		}
	}
	
}

package excx.leveldata;

import java.util.Map;
import java.util.TreeMap;

import excx.levelcommands.WorldCommand;
import excx.levelcommands.WorldCommandGroup;


public class CommandMaker {
	Map<Integer,WorldCommand> commands = new TreeMap<Integer,WorldCommand>();

	public void addCommand(int time,WorldCommand c){
		if(commands.containsKey(time)){
			WorldCommand wc1 = commands.get(time);
			WorldCommandGroup cg = new WorldCommandGroup();
			cg.addCommand(wc1);
			cg.addCommand(c);
			commands.put(time, cg);
		} else {
			commands.put(time,c);
		}
	}
	
	public Map<Integer,WorldCommand> completeCommand(){
		Map<Integer,WorldCommand> k = commands;
		commands = new TreeMap<Integer,WorldCommand>();
		return k;
	}
	
	
	
	static CommandMaker self;
	private CommandMaker(){
		//HAHA! I am a singleton!
	}
	
	public static CommandMaker getInstance(){
		if(self == null){
			synchronized(CommandMaker.class){
				if (self == null){
					self = new CommandMaker();
				}
			}
		}
		return self;
	}
}

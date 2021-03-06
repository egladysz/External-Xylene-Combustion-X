package excx.leveldata;

import java.util.Map;

import excx.entities.BasicBullet;
import excx.entities.GameField;
import excx.entities.boss.BossOldMiniBoss;
import excx.entities.exception.WolfeBot;
import excx.entities.patterns.ShotPatternTargetCone;
import excx.entities.remilia.Ted;
import excx.levelcommands.WorldCommand;
import excx.levelcommands.WorldCommandEnd;
import excx.levelcommands.WorldCommandSimpleSpawn;
import excx.levelcommands.WorldCommandWaitKill;
import excx.movement.Movement;
import excx.movement.Vector;
import excx.state.StateGame;

public class LevelData {
	
	CommandMaker builder = CommandMaker.getInstance();
	GameField field = StateGame.field;
	
	public Map<Integer,WorldCommand> makeLevel(int num){
		switch(num){
		case 1:
			
			builder.addCommand(90, new WorldCommandSimpleSpawn(new WolfeBot(-44, 200,4, new Movement(new Vector(1,-1), new Vector(0,2.0/(StateGame.field.getWidth()+88))), new ShotPatternTargetCone(field, 7, 3, 10, 60, new BasicBullet(0, 0, null)))));
			builder.addCommand(90, new WorldCommandSimpleSpawn(new WolfeBot(StateGame.field.getWidth()+44, 200,4, new Movement(new Vector(-1,-1), new Vector(0,2.0/(StateGame.field.getWidth()+88))), new ShotPatternTargetCone(field, 7, 3, 10, 60, new BasicBullet(0, 0, null)))));
			builder.addCommand(271, new WorldCommandWaitKill());
			builder.addCommand(280, new WorldCommandSimpleSpawn(new Ted(true)));
			builder.addCommand(280, new WorldCommandSimpleSpawn(new Ted(false)));
			builder.addCommand(400, new WorldCommandSimpleSpawn(new Ted(true)));
			builder.addCommand(400, new WorldCommandSimpleSpawn(new Ted(false)));
			builder.addCommand(401, new WorldCommandWaitKill());
			builder.addCommand(500, new WorldCommandSimpleSpawn(new Ted(true)));
			builder.addCommand(500, new WorldCommandSimpleSpawn(new Ted(false)));
			builder.addCommand(560, new WorldCommandSimpleSpawn(new Ted(true)));
			builder.addCommand(560, new WorldCommandSimpleSpawn(new Ted(false)));
			builder.addCommand(620, new WorldCommandSimpleSpawn(new Ted(true)));
			builder.addCommand(620, new WorldCommandSimpleSpawn(new Ted(false)));
			builder.addCommand(680, new WorldCommandSimpleSpawn(new Ted(true)));
			builder.addCommand(680, new WorldCommandSimpleSpawn(new Ted(false)));
			builder.addCommand(681, new WorldCommandWaitKill());
			builder.addCommand(860, new WorldCommandSimpleSpawn(new BossOldMiniBoss()));
			builder.addCommand(861, new WorldCommandWaitKill());
			builder.addCommand(960, new WorldCommandEnd());
			//builder.addCommand(120, new WorldCommandSimpleSpawn(new FairyBot(-50, 200, new Movement(new Vector(2,-1), new Vector(0,4.0/(StateGame.field.getWidth()+100))), null)));
			//builder.addCommand(120, new WorldCommandSimpleSpawn(new FairyBot(StateGame.field.getWidth()+50, 200, new Movement(new Vector(-2,-1), new Vector(0,4.0/(StateGame.field.getWidth()+100))), null)));
			break;
			
		case 2:
			builder.addCommand(90, new WorldCommandSimpleSpawn(new WolfeBot(-44, 200,4, new Movement(new Vector(1,-1), new Vector(0,2.0/(StateGame.field.getWidth()+88))), new ShotPatternTargetCone(field, 6, 3, 30, 10, new BasicBullet(0, 0, null)))));
			builder.addCommand(90, new WorldCommandSimpleSpawn(new WolfeBot(StateGame.field.getWidth()+44, 200,4, new Movement(new Vector(-1,-1), new Vector(0,2.0/(StateGame.field.getWidth()+88))), new ShotPatternTargetCone(field, 6, 3, 30, 10, new BasicBullet(0, 0, null)))));
			builder.addCommand(150, new WorldCommandSimpleSpawn(new WolfeBot(-44, 200,4, new Movement(new Vector(1,-1), new Vector(0,2.0/(StateGame.field.getWidth()+88))), new ShotPatternTargetCone(field, 6, 3, 30, 10, new BasicBullet(0, 0, null)))));
			builder.addCommand(150, new WorldCommandSimpleSpawn(new WolfeBot(StateGame.field.getWidth()+44, 200,4, new Movement(new Vector(-1,-1), new Vector(0,2.0/(StateGame.field.getWidth()+88))), new ShotPatternTargetCone(field, 6, 3, 30, 10, new BasicBullet(0, 0, null)))));
			builder.addCommand(210, new WorldCommandSimpleSpawn(new WolfeBot(-44, 200,4, new Movement(new Vector(1,-1), new Vector(0,2.0/(StateGame.field.getWidth()+88))), new ShotPatternTargetCone(field, 6, 3, 30, 10, new BasicBullet(0, 0, null)))));
			builder.addCommand(210, new WorldCommandSimpleSpawn(new WolfeBot(StateGame.field.getWidth()+44, 200,4, new Movement(new Vector(-1,-1), new Vector(0,2.0/(StateGame.field.getWidth()+88))), new ShotPatternTargetCone(field, 6, 3, 30, 10, new BasicBullet(0, 0, null)))));
			//builder.addCommand(200, new WorldCommandSimpleSpawn(new BossScarletDevil()));
			builder.addCommand(211, new WorldCommandWaitKill());
			builder.addCommand(280, new WorldCommandEnd());
			break;
			
		case 3:
			
			break;
			
		case 4:
			
			break;
			
		case 5:
			
			break;
			
		case 6:
			
			break;
			
		default:
			System.out.println("YOU NEED\nTO SECURE\nYOUR DATABASES\nYOU MORONS\n(add more levels)");
			break;	
		}
		
		return builder.completeCommand();
	}
	
	
	static LevelData self;
	private LevelData(){
		//HAHA! I am a singleton!
	}
	
	public static LevelData getInstance(){
		if(self == null){
			synchronized(LevelData.class){
				if (self == null){
					self = new LevelData();
				}
			}
		}
		return self;
	}
	
}

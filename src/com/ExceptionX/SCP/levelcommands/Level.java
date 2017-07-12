package com.ExceptionX.SCP.levelcommands;

import java.util.LinkedList;
import java.util.Map;

import com.ExceptionX.SCP.entities.Enemy;
import com.ExceptionX.SCP.entities.GameField;
import com.ExceptionX.SCP.entities.Sprite;


public class Level {
	
	public LinkedList<Map<Integer,WorldCommand>> orders;
	public int timer;
	private int wave;
	public boolean isOver;
	private GameField world;
	
	public void tick(){
		if(isOver){
			return;
		}
		
		timer++;
		if(orders.get(wave).containsKey(timer)){
			if(!(orders.get(wave).get(timer) instanceof WorldCommandWaitKill)){
				orders.get(wave).get(timer).execute();
				if(orders.get(wave).get(timer) instanceof WorldCommandEnd){
					isOver = true;
					wave++;
					timer = 0;
					if(wave == orders.size()){
						wave = 0;
						isOver = true;
					}
				}
			} else{
				for(Sprite s:getWorld().getEntityList()){
					if(s instanceof Enemy){
						timer--;
						break;
					}
				}
				
			}
		}
	}

	public Level(Map<Integer, WorldCommand> makeLevel) {
		this();
		orders.add(makeLevel);
	}
	public Level() {
		wave = 0;
		orders = new LinkedList<Map<Integer,WorldCommand>>();
	}

	public void setWorld(GameField gameField) {
		world = gameField;
	}
	public GameField getWorld(){
		return world;
	}
	
	public Object clone(){
		Level me = new Level();
		me.setWorld(getWorld());
		me.orders.addAll(orders);
		return me;
		
	}
}

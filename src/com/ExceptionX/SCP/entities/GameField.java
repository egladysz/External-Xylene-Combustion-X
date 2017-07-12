package com.ExceptionX.SCP.entities;


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.ExceptionX.SCP.ExcX;
import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.entities.player.PlayerStateBomb;
import com.ExceptionX.SCP.entities.player.PlayerStateWin;
import com.ExceptionX.SCP.levelcommands.Level;
import com.ExceptionX.SCP.state.StateGame;


public class GameField extends Sprite{
	
	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	private int width, height;
	private Level level;
	boolean restarting;
	
	
	
	public GameField(double x, double y, int w, int h) {
		super(x,y);
		setDimensions(w,h);
		this.setImageNode(SpriteSheet.getInstance().getSpriteNode("background"));
	}
	
	public void add(Sprite e){
		e.setWorld(this);
		spriteList.add(e);
		//TODO: Kill this
		if(e instanceof Enemy) Enemy.enemies++;
	}
	
	public void remove(Sprite e){
		spriteList.remove(e);
	}
	
	public void setDimensions(int w,int h){
		setWidth(w);
		setHeight(h);
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public ArrayList<Sprite> getEntityList(){
		return spriteList;
	}

	@Override
	public void render(double x2, double y2, Graphics2D g) {
		
		//TODO: Draw background
		int i = 5+((StateGame.you.getPlayerState() instanceof PlayerStateBomb && !ExcX.game.isPaused())?(int)(Math.random()*10-5):0);
		g.drawImage(this.getImageNode().getImage(), (int)(x2+getX()-1-i), (int)(y2+getY()-1+(getTimer()%this.getImageNode().getImage().getHeight(null))), null);
		g.drawImage(this.getImageNode().getImage(), (int)(x2+getX()-1-i), (int)(y2+getY()-1+(getTimer()%this.getImageNode().getImage().getHeight(null))-this.getImageNode().getImage().getHeight(null)), null);
		if(getTimer()<=100){
			g.setColor(new Color(255,255,255,(int)(255-(255.0*getTimer()/100.0))));
			g.fillRect((int)(x2+getX()-1), (int)(y2+getY()-1), getWidth()+2, getHeight()+2);
		}
		
		
		for(Sprite e: spriteList){
			e.render(x2+getX(),y2+getY(),g);
			/*if(e instanceof Entity){
				((Entity) e).getCollisionType().render(x2+getX(), y2+getY(), g, e.getX(), e.getY());
			}*/
		}
		
	}

	@Override
	public Object clone() {
		GameField f = new GameField(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		ArrayList<Sprite> copy = new ArrayList<Sprite>();
		copy.addAll(spriteList);
		for(int i = 0; i < copy.size(); i++){
			f.add((Sprite) copy.get(i).clone());
		}
		return null;
	}

	@Override
	public void tick() {
		if(level.isOver){
			setTimer(getTimer()-1);
			if(StateGame.you.getPlayerState() instanceof PlayerStateWin){
				
			}
		}
		setTimer(getTimer()+1);
		level.tick();
		ArrayList<Sprite> copy = new ArrayList<Sprite>();
		copy.addAll(this.getEntityList());
		for(int i = 0; i < copy.size(); i++){
			copy.get(i).tick();
			if(copy.get(i).isDeletable()){
				//TODO: Kill this
				if(copy.get(i) instanceof Enemy) Enemy.enemies--;
				this.remove(copy.get(i));
			}
		}
	}

	public void setLevel(Level currentLevel) {
		level = currentLevel;
		setTimer(0);
		level.setWorld(this);
	}
	public Level getLevel(){
		return level;
	}

	public void add(int i, Sprite s) {
		spriteList.add(0, s);
	}
	
}

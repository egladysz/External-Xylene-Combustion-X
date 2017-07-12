package com.ExceptionX.SCP.entities;

import java.awt.Graphics2D;

import com.ExceptionX.SCP.data.SpriteNode;
import com.ExceptionX.SCP.movement.Movement;
import com.ExceptionX.SCP.movement.Vector;

public abstract class Sprite {

	private double x;
	private double y;
	private boolean deletable;
	Movement movement;
	private GameField world;
	private SpriteNode imageNode;
	private int timer;
	private Team team;
	
	
	public void render(Graphics2D g){render(0,0,g);}
	public abstract void render(double x2, double y2, Graphics2D g);
	public abstract void tick();
	
	public Sprite(double x, double y){
		setLocation(x,y);
		setTeam(Team.Passive);
	}
	
	public abstract Object clone();
	
	public void setLocation(double x, double y){
		setX(x);
		setY(y);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public boolean isDeletable() {
		return deletable;
	}
	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}
	public void setMovement(Movement m) {
		movement = m;
	}
	public Movement getMovement() {
		return movement;
	}
	public GameField getWorld() {
		return world;
	}
	public void setWorld(GameField world) {
		this.world = world;
	}
	public SpriteNode getImageNode() {
		return imageNode;
	}
	public void setImageNode(SpriteNode imageNode) {
		this.imageNode = imageNode;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public Vector getVectorToTarget(Sprite s){
		return new Vector(s.getX()-this.getX(),s.getY()-this.getY());
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
}

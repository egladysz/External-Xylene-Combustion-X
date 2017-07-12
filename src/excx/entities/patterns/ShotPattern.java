package excx.entities.patterns;

import excx.entities.GameField;
import excx.entities.Team;

public abstract class ShotPattern {
	private int timer;
	private int cycles;
	private GameField world;
	public ShotPattern(GameField world){
		this.setWorld(world);
		this.setTimer(0);
	}
	public abstract void tick(double d, double e, Team team);
	public GameField getWorld() {
		return world;
	}
	public void setWorld(GameField world) {
		this.world = world;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public int getCycles() {
		return cycles;
	}
	public void setCycles(int cycles) {
		this.cycles = cycles;
	}
}

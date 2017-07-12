package excx.entities.player;

import java.awt.Graphics2D;

import excx.entities.GameField;

public abstract class PlayerState {
	private int timer;
	private Player me;
	public PlayerState(Player me) {
		setMe(me);
		timer = 0;
	}
	public abstract void tick();
	public abstract void render(double x2, double y2, Graphics2D g);
	public void render(Graphics2D g){
		render(0,0,g);
	}
	public Player getMe() {
		return me;
	}
	public void setMe(Player me) {
		this.me = me;
	}
	public abstract void checkCollision(GameField world);
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
}

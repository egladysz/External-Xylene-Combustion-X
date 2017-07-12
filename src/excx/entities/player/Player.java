package excx.entities.player;



import java.awt.Graphics2D;

import excx.collision.CollisionCircle;
import excx.entities.BasicBullet;
import excx.entities.Entity;
import excx.entities.GameField;
import excx.entities.Team;
import excx.entities.items.Item;
import excx.movement.Movement;

public abstract class Player extends Entity{
	
	private double speed;
	private int power;
	private int maxPower;
	private GameField world;
	public BasicBullet shotType;
	private PlayerState playerState;
	private int cooldown;
	private int lives;
	
	
	public abstract void shoot();
	public abstract void bomb(int i);
	
	public Player(double x, double y){
		super(x,y);
		this.power=10;
		setTeam(Team.Good);
		setMovement(new Movement(null,null));
		setLives(5);
		setCollisionType(new CollisionCircle(5));
		setPlayerState(new PlayerStateNominal(this));
		this.cooldown = 60;
	}
	@Override
	public void render(double x2, double y2, Graphics2D g) {
		getPlayerState().render(x2,y2,g);
		/*for(Sprite e:StateGame.field.getEntityList()){
			g.drawLine((int)(x2+getX()), (int)(y2+getY()), (int)(x2+e.getX()), (int)(y2+e.getY()));
		}*/
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public GameField getWorld() {
		return world;
	}

	public void setWorld(GameField world) {
		this.world = world;
	}
	public void tick(){
		setTimer(getTimer()+1);
		if(getCooldown()>0){
			setCooldown(getCooldown()-1);
		}
		getPlayerState().tick();
	}
	public void checkCollision(GameField world){
		
	}
	public void move() {
		this.getMovement().update();
		if(this.getMovement().getVelocity() == 0) return;
		double dx = this.getMovement().getXVelocity();
		double dy = this.getMovement().getYVelocity();
		if (this.getX()+dx>this.getWorld().getWidth()) dx = (this.getWorld().getWidth()-this.getX());
		if(this.getX()+dx<0) dx = -this.getX();
		if (this.getY()+dy>this.getWorld().getHeight()) dy = (this.getWorld().getHeight()-this.getY());
		if(this.getY()+dy<0) dy = -this.getY();
		this.setX(this.getX()+dx);
		this.setY(this.getY()+dy);
		
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public PlayerState getPlayerState() {
		return playerState;
	}

	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
	}
	public String toString(){
		return "player";
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		if(power <10){
			this.power = 10;
		}else{
			this.power = (power>maxPower)?maxPower:power;
		}
	}

	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	public void upgrade(Item item) {
		item.execute(this);
		
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
}
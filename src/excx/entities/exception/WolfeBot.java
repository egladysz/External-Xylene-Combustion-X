package excx.entities.exception;

import excx.data.SpriteSheet;
import excx.entities.Enemy;
import excx.entities.items.ItemPowerUp;
import excx.entities.patterns.ShotPattern;
import excx.movement.Movement;

public class WolfeBot extends Enemy{
	
	private ShotPattern shot;
	
	public WolfeBot(double x, double y, int heal, Movement movement, ShotPattern p) {
		super(x, y);
		this.setHealth(heal);
		this.setMovement(movement);
		this.setShot(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void die() {
		super.die();
		getWorld().add(new ItemPowerUp(getX(),getY()));
	}

	@Override
	public void tick() {
		setTimer(getTimer()+1);
		if(getShot()!= null){
			getShot().tick(getX(), getY(), getTeam());
		}
		if(getTimer() >= 60){
			if(getX()<-100 || getX() > getWorld().getWidth()+100){
				this.setDeletable(true);
			}
			if(getY()<-100 || getY() > getWorld().getHeight()+100){
				this.setDeletable(true);
			}
		}
		setImageNode(SpriteSheet.getInstance().getSpriteNode(toString()));
		this.setLocation(getX()+getMovement().getXVelocity(), getY()+getMovement().getYVelocity());
		this.getMovement().update();
		this.checkCollision(getWorld());
	}

	@Override
	public Object clone() {
		return new WolfeBot(getX(),getY(),this.getHealth(),(Movement) getMovement().clone(),getShot());
	}

	public ShotPattern getShot() {
		return shot;
	}

	public void setShot(ShotPattern shot) {
		this.shot = shot;
	}
	
	public String toString(){
		return super.toString()+"_fairybot";
	}
}

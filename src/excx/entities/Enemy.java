package excx.entities;


import java.util.ArrayList;

import excx.collision.CollisionCircle;
import excx.data.SpriteSheet;
import excx.entities.boss.Boss;
import excx.entities.boss.TinyKaboom;
import excx.movement.Movement;
import excx.movement.Vector;

public abstract class Enemy extends Entity{
	
	public static int enemies = 0;
	private int health;
	
	
	
	
	public Enemy(double x, double y) {
		super(x, y);
		setTeam(Team.Evil);
		setCollisionType(new CollisionCircle(32));
		movement = new Movement(new Vector(0,0),null);
		setImageNode(SpriteSheet.getInstance().getSpriteNode(toString()));
	}

	@Override
	public void checkCollision(GameField world) {
		ArrayList<Sprite> copy = new ArrayList<Sprite>();
		copy.addAll(world.getEntityList());
		for(Sprite e:copy){
			if(e instanceof Bullet && (e.getTeam() == Team.Good || e.getTeam() == Team.Nature) && e != this&&isColliding((Bullet)e)){
				e.setDeletable(true);
				this.setHealth(getHealth()-1);
				if(!(this instanceof Boss)){
					setImageNode(SpriteSheet.getInstance().getSpriteNode(toString()+"_hurt"));
				}
				if(getHealth()==0){
					this.setDeletable(true);
					this.die();
				}
			}
		}
	}

	public void die(){
		getWorld().add(new TinyKaboom(getX(),getY()));
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public String toString(){
		return "enemy";
	}
}

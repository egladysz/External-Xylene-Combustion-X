package excx.entities.items;

import java.util.ArrayList;

import excx.collision.CollisionCircle;
import excx.collision.CollisionType;
import excx.data.SpriteSheet;
import excx.entities.Entity;
import excx.entities.GameField;
import excx.entities.Sprite;
import excx.entities.Team;
import excx.entities.player.Player;
import excx.movement.Movement;
import excx.movement.Vector;

public abstract class Item extends Entity{
	
	CollisionType cBig;
	CollisionType cSmall;
	
	public Item(double x, double y) {
		super(x, y);
		setTeam(Team.Good);
		setMovement(new Movement(new Vector(0,-2),new Vector(0,1/16D)));
		setImageNode(SpriteSheet.getInstance().getSpriteNode("player_demo_idle"));
		cBig = new CollisionCircle(200);
		cSmall = new CollisionCircle(10);
		setCollisionType(cBig);
	}

	@Override
	public void tick() {
		this.getMovement().update();
		if(getMovement().getYVelocity()>3){
			getMovement().setAcceleration(0);
		}
		
		setLocation(this.getX()+getMovement().getXVelocity(),getY()+getMovement().getYVelocity());
		checkCollision(this.getWorld());
	}

	@Override
	public void checkCollision(GameField world) {
		if(getY()-this.getCollisionType().getRadius(Math.PI/2*3)>getWorld().getHeight()){
			this.setDeletable(true);
			return;
		}
		ArrayList<Sprite> copy = new ArrayList<Sprite>();
		copy.addAll(world.getEntityList());
		for(Sprite e:copy){
			if(e instanceof Player && isColliding((Player) e)){
				getMovement().setVelocityAngle(Math.atan2(e.getY()-getY(), e.getX()-getX()), false);
				getMovement().setVelocity(((Player)e).getSpeed()+2);
				setCollisionType(cSmall);
				if(isColliding((Player) e)){
					((Player)(e)).upgrade(this);
					this.setDeletable(true);
				}
				setCollisionType(cBig);
				return;
			}
		}
	}
	public abstract void execute(Player player);
}

package excx.entities.player;


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import excx.collision.CollisionCircle;
import excx.collision.CollisionNo;
import excx.data.SpriteSheet;
import excx.entities.Bullet;
import excx.entities.Entity;
import excx.entities.GameField;
import excx.entities.Sprite;
import excx.entities.Team;
import excx.movement.Movement;

public class OrthoBomb extends Bullet{

	public OrthoBomb(double x, double y, Movement m) {
		super(x, y, m);
		this.setImageNode(SpriteSheet.getInstance().getSpriteNode("orthobomb"));
		this.setCollisionType(new CollisionNo());
		this.setTeam(Team.Good);
	}

	@Override
	public void tick() {
		this.setTimer(getTimer()+1);
		this.setLocation(getX()+getMovement().getXVelocity(), getY()+getMovement().getYVelocity());
		this.getMovement().setVelocity(this.getMovement().getVelocity()*.9);
		this.setDeletable(false);
		if(this.getTimer()>=120){
			this.getMovement().setVelocity(0);
			this.setCollisionType(new CollisionCircle(16*(getTimer()-120)));
		}
		if(this.getTimer()>= 180){
			this.setDeletable(true);
		}
		this.checkCollision(getWorld());
	}
	
	@Override
	public void render(double x2, double y2, Graphics2D g){
		if(getTimer()>=120){
			g.setColor(new Color(255,0,0,255-255*(getTimer()-120)/120));
			g.fillOval((int)(x2+getX()-(getTimer()-120)*8),(int)(y2+getY()-(getTimer()-120)*8),(int)(getTimer()-120)*16,(int)(getTimer()-120)*16);
			return;
		}
		g.drawImage(getImageNode().getImage(),(int)(getX()+x2-getImageNode().getImage().getWidth(null)/2),(int)(getY()+y2-getImageNode().getImage().getHeight(null)/2), null);
	}

	@Override
	public Object clone() {
		return new OrthoBomb(getX(),getY(),getMovement());
	}

	@Override
	public void checkCollision(GameField world) {
		ArrayList<Sprite> copy = new ArrayList<Sprite>();
		copy.addAll(world.getEntityList());
		for(Sprite e:copy){
			if(e != this&& e instanceof Entity &&(e.getTeam() == Team.Evil || e.getTeam() == Team.Nature) &&this.isColliding((Entity)e)){
				if(e instanceof Bullet)
					world.remove(e);
				continue;
			}
		}
	}

}

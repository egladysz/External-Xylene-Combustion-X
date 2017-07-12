package com.ExceptionX.SCP.entities;

import java.awt.Graphics2D;

import com.ExceptionX.SCP.collision.CollisionAABB;
import com.ExceptionX.SCP.collision.CollisionCircle;
import com.ExceptionX.SCP.collision.CollisionNo;
import com.ExceptionX.SCP.collision.CollisionType;
import com.ExceptionX.SCP.data.SpriteNode;
import com.ExceptionX.SCP.movement.Vector;
import com.ExceptionX.SCP.movement.VectorMath;

public abstract class Entity extends Sprite{

	private CollisionType collision;
	private GameField world;
	private SpriteNode imageNode;
	
	
	public abstract void tick();
	public abstract void checkCollision(GameField world);
	
	public Entity(double x, double y){
		super(x,y);
	}
	@Override
	public void render(double x2, double y2, Graphics2D g){
		g.drawImage(getImageNode().getImage(),(int)(getX()+x2-getImageNode().getImage().getWidth(null)/2),(int)(getY()+y2-getImageNode().getImage().getHeight(null)/2), null);
	}
	
	public boolean isColliding(Entity e){
		if(this.getCollisionType() == null || e.getCollisionType()==null) return false;
		if(this.getCollisionType() instanceof CollisionNo || e.getCollisionType() instanceof CollisionNo) return false;
		
		Vector d = this.getVectorToTarget(e);
		Vector[] circOnly = new Vector[0];
		if(this.getCollisionType() instanceof CollisionCircle && e.getCollisionType() instanceof CollisionCircle){
			circOnly = new Vector[1];
			circOnly[0]= (Vector)d.clone();
		}
		Vector[] ourNormals = this.getCollisionType().getProjAngles();
		Vector[] theirNormals = e.getCollisionType().getProjAngles();
		Vector[] c = new Vector[0];
		Vector[] f = new Vector[0];
		/*if(this.getCollisionType() instanceof CollisionAABB && (e.getCollisionType() instanceof CollisionCircle)){
			c = new Vector[4];
			c[0] = new Vector( (((CollisionAABB)this.getCollisionType()).relX[0]+this.getX())-e.getX(), (((CollisionAABB)this.getCollisionType()).relY[0]+this.getY())-e.getY());
			c[1] = new Vector( (((CollisionAABB)this.getCollisionType()).relX[1]+this.getX())-e.getX(), (((CollisionAABB)this.getCollisionType()).relY[1]+this.getY())-e.getY());
			c[2] = new Vector( (((CollisionAABB)this.getCollisionType()).relX[2]+this.getX())-e.getX(), (((CollisionAABB)this.getCollisionType()).relY[2]+this.getY())-e.getY());
			c[3] = new Vector( (((CollisionAABB)this.getCollisionType()).relX[3]+this.getX())-e.getX(), (((CollisionAABB)this.getCollisionType()).relY[3]+this.getY())-e.getY());
		}
		if(e.getCollisionType() instanceof CollisionAABB && (this.getCollisionType() instanceof CollisionCircle)){
			f = new Vector[4];
			f[0] = new Vector( (((CollisionAABB)e.getCollisionType()).relX[0]+e.getX())-this.getX(), (((CollisionAABB)e.getCollisionType()).relY[0]+e.getY())-this.getY());
			f[1] = new Vector( (((CollisionAABB)e.getCollisionType()).relX[1]+e.getX())-this.getX(), (((CollisionAABB)e.getCollisionType()).relY[1]+e.getY())-this.getY());
			f[2] = new Vector( (((CollisionAABB)e.getCollisionType()).relX[2]+e.getX())-this.getX(), (((CollisionAABB)e.getCollisionType()).relY[2]+e.getY())-this.getY());
			f[3] = new Vector( (((CollisionAABB)e.getCollisionType()).relX[3]+e.getX())-this.getX(), (((CollisionAABB)e.getCollisionType()).relY[3]+e.getY())-this.getY());
		}*/
		
		
		Vector[][] vec = {ourNormals,theirNormals,c,f,circOnly};
		
		for(Vector[] v: vec){
			for(int i = 0; i < v.length; i++){
				double ourP = this.getCollisionType().getLongestVectorDistance(v[i].getAngleRadians(),v[i]).getMagnitude();
				double theirP =  e.getCollisionType().getLongestVectorDistance(v[i].getAngleRadians(),v[i]).getMagnitude();
				if(VectorMath.projection(d, v[i]).getMagnitude()> ourP+ theirP){
					return false;
				}
			}
		}
		if(e.getCollisionType() instanceof CollisionAABB && (this.getCollisionType() instanceof CollisionAABB)){
			//System.out.println(this.getCollisionType()+ " " + e.getCollisionType());
		}
		return true;
	}
	
	public CollisionType getCollisionType() {
		return collision;
	}
	public void setCollisionType(CollisionType collision) {
		this.collision = collision;
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
}

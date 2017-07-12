package excx.entities.exception;

import excx.data.SpriteSheet;
import excx.entities.BasicBullet;
import excx.entities.Enemy;
import excx.entities.GameField;
import excx.entities.Sprite;
import excx.movement.Movement;
import excx.movement.Vector;
import excx.movement.VectorMath;

public class HomingBullet extends BasicBullet{
	
	Enemy target;
	private boolean on;
	
	public HomingBullet(double x, double y, Movement type,boolean homing) {
		super(x, y, type);
		this.setImageNode(SpriteSheet.getInstance().getSpriteNode(this.toString()));
		setOn(homing);
	}

	@Override
	public void tick() {
		if(!isOn()){
			super.tick();
			return;
		}
		else{
			Enemy closest = null;
			double sqrDistance = 0;
			for(Sprite e:getWorld().getEntityList()){
				if (e instanceof Enemy){
					if(closest == null){
						closest = (Enemy) e;
						sqrDistance = Math.pow(getX()-e.getX(),2)+Math.pow(getY()-e.getY(),2);
					}
					if(sqrDistance > Math.pow(getX()-e.getX(),2)+Math.pow(getY()-e.getY(),2)){
						closest = (Enemy) e;
						sqrDistance = Math.pow(getX()-e.getX(),2)+Math.pow(getY()-e.getY(),2);
					}
				}
			}
			target = closest;
		}
		if(target == null){
			super.tick();
			return;
		}
		double angleToTarget= this.getVectorToTarget(target).getAngleRadians();
		double angleOfMe= getMovement().getVelocityAngleRadians();
		double consta = 1;//60D/60D;
		double fast = this.getMovement().getVelocity();
		
		
		Vector dir = this.getVectorToTarget(target);
		Vector vel = (Vector) this.getMovement().getVelocityVector().clone();
		if(Math.abs(angleToTarget-angleOfMe)>Math.PI/2){
			//TODO: Test this
			
			Vector yay = VectorMath.rejection(dir, vel);
			yay.setMagnitude(consta);
			this.getMovement().setAccelerationVector(yay);
		} else{
			Vector yay = VectorMath.rejection(VectorMath.normalize(dir), vel);
			yay = VectorMath.scalarMultiply(yay, consta);
			this.getMovement().setAccelerationVector(yay);
		}
		
		
		super.tick();
		this.getMovement().setVelocity(fast);
		this.getMovement().setAcceleration(0);
		//TODO: Configure angle rotation.
			/* 
			 * Projection of desired velocity over perpendicular velocity.
			 * Overridden by generic maximum angle.
			 * 
			 */
	}

	@Override
	public Object clone() {
		return new HomingBullet(getX(),getY(),(Movement)getMovement().clone(),isOn());
	}
	public String toString(){
		return super.toString() + "_homing";
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	@Override
	public void checkCollision(GameField world) {
		// TODO Auto-generated method stub
		
	}

}

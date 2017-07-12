package excx.movement;


public class Movement {
	private Vector velocity;
	private Vector acceleration;
	public Movement(Vector velVect, Vector accVect) {
		if(velVect == null) velVect = new Vector(0,0);
		if(accVect == null) accVect = new Vector(0,0);
		setVelocityVector(velVect);
		setAccelerationVector(accVect);
	}
	public Vector getVelocityVector(){
		return velocity;
	}
	public double getVelocity(){
		return velocity.getMagnitude();
	}
	public double getXVelocity(){
		return velocity.getXMagnitude();
	}
	public double getYVelocity(){
		return velocity.getYMagnitude();
	}
	public double getVelocityAngleRadians(){
		return velocity.getAngleRadians();
	}
	public double getVelocityAngleDegrees(){
		return velocity.getAngleDegrees();
	}
	public Vector getAccelerationVector(){
		return acceleration;
	}
	public double getAcceleration(){
		return acceleration.getMagnitude();
	}
	public double getXAcceleration(){
		return acceleration.getXMagnitude();
	}
	public double getYAcceleration(){
		return acceleration.getYMagnitude();
	}
	public double getAccelerationAngleRadians(){
		return acceleration.getAngleRadians();
	}
	public double getAccelerationAngleDegrees(){
		return acceleration.getAngleDegrees();
	}
	public void setVelocityVector(Vector v){
		velocity = v;
	}
	public void setVelocity(double vel){
		velocity.setMagnitude(vel);
	}
	public void setXVelocity(double vel){
		velocity.setXMagnitude(vel);
	}
	public void setYVelocity(double vel){
		velocity.setYMagnitude(vel);
	}
	public void setVelocityAngle(double ang, boolean degrees){
		velocity.setAngle(ang, degrees);
	}
	public void setAccelerationVector(Vector v){
		acceleration = v;
	}
	public void setAcceleration(double vel){
		acceleration.setMagnitude(vel);
	}
	public void setXAcceleration(double acc){
		acceleration.setXMagnitude(acc);
	}
	public void setYAcceleration(double acc){
		acceleration.setYMagnitude(acc);
	}
	public void setAccelerationAngle(double ang, boolean degrees){
		acceleration.setAngle(ang, degrees);
	}
	
	public void update(){
		velocity = VectorMath.add(velocity,acceleration);
	}
	public Object clone(){
		return new Movement((Vector) this.velocity.clone(),(Vector) this.acceleration.clone());
	}
	
}

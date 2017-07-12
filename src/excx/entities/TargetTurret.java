package excx.entities;
import excx.movement.Vector;


public class TargetTurret extends Turret{
	
	public Entity target;
	
	public TargetTurret(Bullet b, Entity e, int power) {
		super(b);
		aim.setMagnitude(power);
		target = e;
	}
	@Override
	public void shoot(double x, double y, GameField f, Team team){
		Bullet b = (Bullet) ammo.clone();
		b.setLocation(x, y);
		b.setTeam(team);
		if(target != null)
			aim.setAngle(Math.atan2(target.getY()-y,target.getX()-x),false);
		b.getMovement().setVelocityVector((Vector)aim.clone());
		f.add(b);
	}
}

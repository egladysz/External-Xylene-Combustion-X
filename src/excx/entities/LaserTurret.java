package excx.entities;

import excx.collision.CollisionAABB;
import excx.movement.Movement;

public class LaserTurret extends Turret{
	public LaserTurret(){
		super(new Beam(0, 0, new Movement(null,null)));
	}
	@Override
	public void shoot(double x, double y, GameField f, Team team){
		Bullet b = (Bullet) ammo.clone();
		b.getMovement().setYVelocity(-0.1);
		b.setTeam(team);
		b.setLocation(x, y/2);
		b.setCollisionType(new CollisionAABB(6,y));
		f.add(b);
	}
}

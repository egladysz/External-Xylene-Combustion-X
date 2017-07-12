package com.ExceptionX.SCP.entities.patterns;

import com.ExceptionX.SCP.entities.Bullet;
import com.ExceptionX.SCP.entities.GameField;
import com.ExceptionX.SCP.entities.Team;
import com.ExceptionX.SCP.entities.player.Player;
import com.ExceptionX.SCP.state.StateGame;

public class ShotPatternTargetCone extends ShotPattern{
	
	Player target = StateGame.you;
	int shots;
	double angle;
	Bullet ammo;
	int delay;
	double speed;
	
	public ShotPatternTargetCone(GameField world, int shots, double speed, double thetaDegrees, int delay, Bullet shot) {
		super(world);
		this.shots = shots;
		this.angle = Math.toRadians(thetaDegrees);
		this.delay = delay;
		ammo = (Bullet) shot.clone();
		this.speed = speed;
	}

	@Override
	public void tick(double x, double y, Team team) {
		target = StateGame.you;
		setTimer(getTimer()+1);
		if(getTimer()%delay ==0){
			setCycles(getCycles()+1);
			double ang = Math.atan2(target.getY()-y, target.getX()-x);
			double iteration = angle/shots;
			double realAngle = ang - (angle/2)-iteration/2;
			for(int i = 1; i <= shots; i++){
				realAngle += iteration;
				Bullet b = (Bullet) ammo.clone();
				b.setTeam(team);
				b.getMovement().setVelocity(speed);
				b.getMovement().setVelocityAngle(realAngle, false);
				b.setLocation(x, y);
				getWorld().add(b);
			}
		}
	}

}

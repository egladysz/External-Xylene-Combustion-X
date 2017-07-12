package com.ExceptionX.SCP.entities.patterns;

import com.ExceptionX.SCP.entities.Bullet;
import com.ExceptionX.SCP.entities.GameField;
import com.ExceptionX.SCP.entities.Team;

public class ShotPatternSwirl extends ShotPattern{
	private int swirls;
	private double velocity;
	private int delay;
	private int cycleTime;
	private double angle;
	Bullet ammo;
	
	public ShotPatternSwirl(GameField world,int shots, double speed, int delay, double angle, int cycleTime, Bullet shotType){
		super(world);
		swirls = shots;
		velocity = speed;
		this.delay = delay;
		this.cycleTime = cycleTime;
		this.angle = angle;
		ammo = (Bullet) shotType.clone();
	}

	@Override
	public void tick(double x, double y, Team team) {
		setTimer(getTimer()+1);
		
		if(getTimer() % cycleTime == 0){
			setCycles(getCycles()+1);
		}
		if(getTimer() % delay == 0){
			for(int i = 0; i < swirls; i++){
				Bullet b = (Bullet) ammo.clone();
				b.setLocation(x, y);
				b.setTeam(team);
				b.getMovement().setVelocity(velocity);
				b.getMovement().setVelocityAngle((getTimer()*1.0/(cycleTime*angle) + i*1.0/swirls)*360 , true);
				getWorld().add(b);
			}
		}
	}
}

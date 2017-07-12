package com.ExceptionX.SCP.entities.player;

import com.ExceptionX.SCP.data.Keyboard;
import com.ExceptionX.SCP.entities.BasicBullet;
import com.ExceptionX.SCP.entities.Turret;
import com.ExceptionX.SCP.movement.Movement;
import com.ExceptionX.SCP.movement.Vector;

/**
 * Inspiration: N/A
 * Very similar to how I want paraxylene to act.
 * Quick and agile, with damage focused straight upwards.
 * Special: N/A
 */

public class PlayerDemo extends Player{
	
	Turret[] guns;
	
	public PlayerDemo(double x, double y) {
		super(x, y);
		setSpeed(6);
		setMaxPower(50);
		setPower(10);
		shotType = new BasicBullet(0,0, new Movement(null,null));
		guns = new Turret[5];
		for(int i = 0; i < guns.length;i++){
			guns[i] = new Turret(shotType,new Vector(0,-7));
		}
	}
	
	@Override
	public Object clone() {
		return new PlayerDemo(this.getX(),this.getY());
	}

	@Override
	public void shoot() {
		if (getCooldown()==0){
			int p = getPower()/10;
			int i = (Keyboard.getInstance().shiftPressed)?1:-2;
			switch(p){
			case 5 :
				guns[0].aim.setAngle(270+ 10*i, true);
				guns[4].aim.setAngle(270- 10*i, true);
				guns[0].shoot(getX()-4, getY()+8,getWorld(), getTeam());
				guns[4].shoot(getX()+4, getY()+8,getWorld(), getTeam());
			case 3 :
				guns[1].aim.setAngle(270+ 15*i, true);
				guns[3].aim.setAngle(270- 15*i, true);
				guns[1].shoot(getX()-2, getY(),getWorld(), getTeam());
				guns[3].shoot(getX()+2, getY(),getWorld(), getTeam());
			default:
				guns[2].shoot(this.getX(), this.getY()-8,getWorld(), getTeam());
				break;
			case 4 :
				guns[0].aim.setAngle(270+ 10*i, true);
				guns[4].aim.setAngle(270- 10*i, true);
				guns[0].shoot(getX()-4, getY()+8,getWorld(), getTeam());
				guns[4].shoot(getX()+4, getY()+8,getWorld(), getTeam());
			case 2 :
				guns[1].aim.setAngle(270+ 10, true);
				guns[3].aim.setAngle(270- 10, true);
				guns[1].shoot(getX()-2, getY(),getWorld(), getTeam());
				guns[3].shoot(getX()+2, getY(),getWorld(), getTeam());
			}
			setCooldown(6);
		}
	}
	public String toString(){
		return super.toString() + "_demo";
	}

	@Override
	public void bomb(int i) {
		// Demo got no.
		this.setPlayerState(new PlayerStateNominal(this));
	}
}

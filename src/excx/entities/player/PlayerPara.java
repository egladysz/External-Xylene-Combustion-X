package excx.entities.player;

import java.awt.Color;
import java.awt.Graphics2D;

import excx.collision.CollisionAABB;
import excx.collision.CollisionCircle;
import excx.collision.CollisionType;
import excx.data.Keyboard;
import excx.data.SpriteSheet;
import excx.entities.BasicBullet;
import excx.entities.Beam;
import excx.entities.Bullet;
import excx.entities.LaserTurret;
import excx.entities.Turret;
import excx.movement.Movement;
import excx.movement.Vector;


/**
 * inspiration: Marisa
 * Quick and mobile.
 * Attack is concentrated in front, so positioning is important.
 * Special: DEATH LAZER
 */


public class PlayerPara extends Player{
	
	LaserTurret focus;
	Turret[] guns;
	CollisionType copy;
	public PlayerPara(double x, double y) {
		super(x, y);
		setSpeed(6);
		this.setMaxPower(50);
		shotType = new BasicBullet(0,0, new Movement(null,null));
		shotType.setImageNode(SpriteSheet.getInstance().getSpriteNode("bullet_demo"));
		focus = new LaserTurret();
		focus.aim.setMagnitude(0);
		guns = new Turret[5];
		for(int i = 0; i < guns.length;i++){
			guns[i] = new Turret(shotType,new Vector(0,-12));
		}
		copy = getCollisionType();
	}

	@Override
	public void shoot() {
		if(getCooldown()==0){
			if(Keyboard.getInstance().shiftPressed){
				focus.shoot(getX(), getY(), getWorld(), getTeam());
				setCooldown(2*4*(getMaxPower()/10)/(getPower()/10));
			}
			else{
				int p = getPower()/10;
				switch(p){
				case 5 :
					guns[0].aim.setAngle(270+ 5, true);
					guns[4].aim.setAngle(270- 5, true);
					guns[0].shoot(getX()-8, getY()+8,getWorld(), getTeam());
					guns[4].shoot(getX()+8, getY()+8,getWorld(), getTeam());
				case 3 :
					guns[1].aim.setAngle(270+ 2, true);
					guns[3].aim.setAngle(270- 2, true);
					guns[1].shoot(getX()-4, getY(),getWorld(), getTeam());
					guns[3].shoot(getX()+4, getY(),getWorld(), getTeam());
				default:
					guns[2].shoot(this.getX(), this.getY()-8,getWorld(), getTeam());
					break;
				case 4 :
					guns[0].aim.setAngle(270+ 5, true);
					guns[4].aim.setAngle(270- 5, true);
					guns[0].shoot(getX()-8, getY()+8,getWorld(), getTeam());
					guns[4].shoot(getX()+8, getY()+8,getWorld(), getTeam());
				case 2 :
					guns[1].aim.setAngle(270+ 2, true);
					guns[3].aim.setAngle(270- 2, true);
					guns[1].shoot(getX()-4, getY(),getWorld(), getTeam());
					guns[3].shoot(getX()+4, getY(),getWorld(), getTeam());
				}
				setCooldown(10);
			}
		}
	}
	public void render(double x2, double y2, Graphics2D g){
		if(Keyboard.getInstance().shiftPressed&&Keyboard.getInstance().zPressed){
			Color c = new Color(0,255,255,64);
			g.setColor(c);
			g.fillRect((int)(getX()+x2-2),  (int)(y2), 4, (int)(getY()));
			/*g.drawLine((int)(getX()+x2-2), (int)(getY()+y2), (int)(getX()+x2-2), 0);
			g.drawLine((int)(getX()+x2-1), (int)(getY()+y2), (int)(getX()+x2-1), 0);
			g.drawLine((int)(getX()+x2)-0, (int)(getY()+y2), (int)(getX()+x2+0), 0);
			g.drawLine((int)(getX()+x2)+1, (int)(getY()+y2), (int)(getX()+x2+1), 0);
			g.drawLine((int)(getX()+x2)+2, (int)(getY()+y2), (int)(getX()+x2+2), 0);*/
		}
		super.render(x2, y2, g);
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		return super.toString() + "_para";
	}

	@Override
	public void bomb(int i) {
		this.setCollisionType(new CollisionCircle((180-i)*2));
		if(i == 180){
			this.setCollisionType(copy);
			this.setPlayerState(new PlayerStateNominal(this));
		}
		
		Bullet b = new Beam(0, 0, new Movement(null,null));
		b.getMovement().setYVelocity(-0.1);
		b.setLocation(getX(), getY()/2);
		b.setTeam(getTeam());
		b.setCollisionType(new CollisionAABB(6,getY()));
		getWorld().add(b);
	}
	
}

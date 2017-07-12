package com.ExceptionX.SCP.entities.boss;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.ExceptionX.SCP.IO.ImageCollector;
import com.ExceptionX.SCP.data.SpriteNode;
import com.ExceptionX.SCP.entities.Bullet;
import com.ExceptionX.SCP.entities.Enemy;
import com.ExceptionX.SCP.entities.GameField;
import com.ExceptionX.SCP.entities.Sprite;
import com.ExceptionX.SCP.entities.Team;
import com.ExceptionX.SCP.entities.player.Player;
import com.ExceptionX.SCP.state.StateGame;

public abstract class Boss extends Enemy{
	double tarX = 0;
	double tarY = 0;
	int deaths = 0;
	private int maxHealth;
	private BossContainer stage;
	SpriteNode aura = ImageCollector.getInstance().getImage("boss_aura");
	
	public Boss(double x, double y) {
		super(x, y);
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void render(double dx, double dy, Graphics2D g){
		
		super.render(dx,dy,g);
		g.drawImage(aura.getImage(),(int)(getX()+dx-aura.getImage().getWidth(null)/2),(int)(getY()+dy-aura.getImage().getHeight(null)/2), null);
		g.setColor(Color.WHITE);
		BossContainer bc = setCurrentBossContainer();
		int delta = 8;
		//Draws the health bar(s)
		while(bc != null){
			g.drawLine((int)(8+dx),(int)(delta-1+dy),(int)(8+((this.getWorld().getWidth()-16)*bc.getHealth()*1.0/bc.getMaxHealth())+dx), (int)(delta-1+dy));
			g.drawLine((int)(8+dx),(int)(delta+dy),(int)(8+((this.getWorld().getWidth()-16)*bc.getHealth()*1.0/bc.getMaxHealth())+dx), (int)(delta+dy));
			g.drawLine((int)(8+dx),(int)(delta+1+dy),(int)(8+((this.getWorld().getWidth()-16)*bc.getHealth()*1.0/bc.getMaxHealth())+dx), (int)(delta+1+dy));
			delta += 8;
			bc = bc.getNext();
		}

	}
	
	public String toString(){
		return "boss";
	}
	
	@Override
	public void checkCollision(GameField world) {
		ArrayList<Sprite> copy = new ArrayList<Sprite>();
		copy.addAll(world.getEntityList());
		for(Sprite e:copy){
			if(e instanceof Bullet && (e.getTeam() == Team.Good || e.getTeam() == Team.Nature) && e != this&&isColliding((Bullet)e)){
				e.setDeletable(true);
				this.setCurrentBossContainer().setHealth(this.setCurrentBossContainer().getHealth()-1);
				if(this.setCurrentBossContainer().getHealth()==0){
					this.die();
				}
			}
		}
	}
	
	public void die(){
		this.setDeletable(false);
		if(setCurrentBossContainer().getNext()==null){
			//Clears the GameField of everything except the player.
			Player o = StateGame.you;
			getWorld().getEntityList().clear();
			getWorld().add(o);
			getWorld().add(new BossKaboom(getX(),getY()));
			this.setDeletable(true);
		} else{
			//Prepares the next boss form (sets the new current BossContainer)
			deaths++;
			setCurrentBossContainer(setCurrentBossContainer().getNext());
			getWorld().add(new Kaboom(getX(),getY()));
			this.goToNextStage();
		}
	}

	public BossContainer setCurrentBossContainer() {
		return stage;
	}

	public void setCurrentBossContainer(BossContainer stage) {
		this.stage = stage;
	}

	public abstract void goToNextStage();
	
	public void tick(){
		
		if(getTimer()%3==0 && this.getImageNode().getNext()!= null){
			this.setImageNode(getImageNode().getNext());
		}
		
		this.setTimer(getTimer()+1);
		getMovement().setXVelocity((tarX-getX())/60);
		getMovement().setYVelocity((tarY-getY())/60);
		setLocation(getX()+getMovement().getXVelocity(),getY()+getMovement().getYVelocity());
		setCurrentBossContainer().getPattern().tick(getX(), getY(), getTeam());
		this.setY(getY()+getMovement().getYVelocity());
		this.checkCollision(getWorld());
	}
}

package com.ExceptionX.SCP.entities.boss;

import com.ExceptionX.SCP.entities.patterns.ShotPattern;

public class BossContainer {
	private BossContainer next;
	private int maxHealth;
	private int health;
	private ShotPattern pattern;
	
	public BossContainer(int health, ShotPattern pattern, BossContainer next){
		this.setHealth(health);
		this.setPattern(pattern);
		this.setNext(next);
		this.setMaxHealth(health);
	}

	public BossContainer getNext() {
		return next;
	}

	public void setNext(BossContainer next) {
		this.next = next;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public ShotPattern getPattern() {
		return pattern;
	}

	public void setPattern(ShotPattern pattern) {
		this.pattern = pattern;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
}

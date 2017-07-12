package com.ExceptionX.SCP.state;

import java.awt.Graphics2D;

public abstract class State {
	public abstract void tick();
	public abstract void render(Graphics2D g);
	public abstract void prepare();
}

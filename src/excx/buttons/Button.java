package excx.buttons;

import java.awt.Graphics2D;

public abstract class Button {
	
	private ButtonCommand command;
	
	public Button(){}
	public Button(ButtonCommand c){
		setCommand(c);
	}
	
	private boolean isSelected;
	private int x;
	private int y;
	
	public abstract void beSelected();
	public abstract void beUnSelected();
	public abstract void press();
	public abstract void render(double x2, double y2, Graphics2D g);
	
	public  void render(Graphics2D g){
		render(0,0,g);
	}
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ButtonCommand getCommand() {
		return command;
	}
	public void setCommand(ButtonCommand command) {
		this.command = command;
	}
}

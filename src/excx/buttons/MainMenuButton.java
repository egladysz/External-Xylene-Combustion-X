package excx.buttons;

import java.awt.Color;
import java.awt.Graphics2D;

public class MainMenuButton extends Button{
	private Color offColor;
	private Color  onColor;
	String name;
	public MainMenuButton(int x, int y, String name, ButtonCommand command) {
		super(command);
		setX(x);
		setY(y);
		offColor = Color.BLACK;
		onColor = Color.WHITE;
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void beSelected() {
		// TODO Auto-generated method stub
		setSelected(true);
	}

	@Override
	public void beUnSelected() {
		// TODO Auto-generated method stub
		setSelected(false);
	}

	@Override
	public void press() {
		getCommand().execute();
	}

	@Override
	public void render(double x2, double y2, Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(isSelected()?onColor:offColor);
		g.setFont(g.getFont().deriveFont(20.0F));
		g.drawString(name,getX(),getY());
	}

	public Color getOnColor() {
		return onColor;
	}

	public void setOnColor(Color onColor) {
		this.onColor = onColor;
	}

	public Color getOffColor() {
		return offColor;
	}

	public void setOffColor(Color offColor) {
		this.offColor = offColor;
	}

}

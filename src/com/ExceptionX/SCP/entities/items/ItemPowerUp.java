package com.ExceptionX.SCP.entities.items;

import com.ExceptionX.SCP.data.SpriteSheet;
import com.ExceptionX.SCP.entities.player.Player;

public class ItemPowerUp extends Item{

	public ItemPowerUp(double x, double y) {
		super(x, y);
		setImageNode(SpriteSheet.getInstance().getSpriteNode("bullet_triangle_red_medium"));
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(Player player) {
		player.setPower(player.getPower()+1);
	}

}

package com.ExceptionX.SCP.data;

import java.awt.Image;

public class SpriteNode {
	private Image img;
	private SpriteNode next;
	
	public SpriteNode(Image img, SpriteNode node){
		setImage(img);
		setNext(node);
	}
	
	public Image getImage() {
		return img;
	}
	public void setImage(Image img) {
		this.img = img;
	}
	public SpriteNode getNext() {
		return next;
	}
	public void setNext(SpriteNode next) {
		this.next = next;
	}
}

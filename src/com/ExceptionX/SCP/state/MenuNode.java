package com.ExceptionX.SCP.state;

import com.ExceptionX.SCP.buttons.MainMenuButton;

public class MenuNode {
	private MenuNode previous;
	private MenuNode next;
	private MainMenuButton button;
	
	public MenuNode(MainMenuButton b, MenuNode next, MenuNode previous){
		setNext(next);
		this.previous = previous;
		this.button = b;
	}

	public void setNext(MenuNode next){
		this.next = next;
	}
	public void setPrevious(MenuNode prev){
		this.previous = prev;
	}
	public void setValue(MainMenuButton val){
		this.button = val;
	}
	public MenuNode getNext(){
		return next;
	}
	public MenuNode getPrevious(){
		return previous;
	}
	public MainMenuButton getValue(){
		return button;
	}
	
}

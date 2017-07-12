package com.ExceptionX.SCP.data;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	private static Keyboard me = new Keyboard();
	
	private static boolean[] keys = new boolean[128];
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, zPressed, xPressed, shiftPressed, escapePressed;
	public boolean upTyped, downTyped, leftTyped, rightTyped, zTyped, xTyped, shiftTyped, escapeTyped;

	
	
	private Keyboard(){
		
	}
	public static Keyboard getInstance(){
		return me;
	}
	
	public void update() {
		upTyped=downTyped=leftTyped=rightTyped=zTyped=xTyped=shiftTyped=escapeTyped=false;
		
		upTyped=!upPressed&&keys[KeyEvent.VK_UP];
		downTyped=!downPressed&&keys[KeyEvent.VK_DOWN];
		leftTyped=!leftPressed&&keys[KeyEvent.VK_LEFT];
		rightTyped=!rightPressed&&keys[KeyEvent.VK_RIGHT];
		zTyped=!zPressed&&(keys[KeyEvent.VK_Z]||keys[KeyEvent.VK_ENTER]);
		xTyped=!xPressed&&keys[KeyEvent.VK_X];
		shiftTyped=!shiftPressed&&keys[KeyEvent.VK_SHIFT];
		escapeTyped=!escapePressed&&keys[KeyEvent.VK_ESCAPE];
		
		upPressed = keys[KeyEvent.VK_UP];
		downPressed = keys[KeyEvent.VK_DOWN];
		leftPressed = keys[KeyEvent.VK_LEFT];
		rightPressed = keys[KeyEvent.VK_RIGHT];
		zPressed = keys[KeyEvent.VK_Z] || keys[KeyEvent.VK_ENTER];
		xPressed = keys[KeyEvent.VK_X];
		shiftPressed = keys[KeyEvent.VK_SHIFT];
		escapePressed = keys[KeyEvent.VK_ESCAPE];
	}
	
	
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override //currently unused.
	public void keyTyped(KeyEvent e) {
		
	}

}

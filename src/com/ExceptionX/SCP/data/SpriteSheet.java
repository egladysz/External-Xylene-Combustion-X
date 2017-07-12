package com.ExceptionX.SCP.data;

import java.util.HashMap;

import com.ExceptionX.SCP.IO.ImageCollector;

public class SpriteSheet {
	private static HashMap<String, SpriteNode> spriteGroups;
	
	private static SpriteSheet me;
	
	private SpriteSheet(){
		spriteGroups  = new HashMap<String,SpriteNode>();
	}
	
	public void addSpriteGroup(String s, SpriteNode n){
		spriteGroups.put(s, n);
	}
	
	public SpriteNode getSpriteNode(String key){
		if (spriteGroups.get(key) == null) addSpriteGroup(key, ImageCollector.getInstance().getImage(key));
		return spriteGroups.get(key);
	}
	
	public static SpriteSheet getInstance(){
		if(me == null){
			synchronized(SpriteSheet.class){
				if(me == null){
					me = new SpriteSheet();
				}
			}
		}
		return me;
	}
}

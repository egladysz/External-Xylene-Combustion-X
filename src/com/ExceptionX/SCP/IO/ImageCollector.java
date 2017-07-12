package com.ExceptionX.SCP.IO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.ExceptionX.SCP.data.SpriteNode;
import com.ExceptionX.SCP.data.SpriteSheet;

public class ImageCollector {
	
	private static ImageCollector me;
	
	public SpriteNode getImage(String name){
		Image i = null;
		try{
			i = ImageIO.read(ImageCollector.class.getResource("/"+ name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Image not found.");
		}
		SpriteNode n = new SpriteNode(i,null);
		n.setNext(n);
		return n;
	}
	
	public SpriteNode getImageLoop(String name, int w, int h, int row, int count){
		ArrayList<SpriteNode> ns = new ArrayList<SpriteNode>();
		for(int i = 0; i < count;i++){
			ns.add(getImage(name,w*i,h*row,w,h));
		}
		for(int i = 0; i < ns.size()-1;i++){
			ns.get(i).setNext(ns.get(i+1));
		}
		ns.get(ns.size()-1).setNext(ns.get(0));
		
		return ns.get(0);
		
	}
	
	public SpriteNode getImage(String name, int dx, int dy, int w, int h){
		BufferedImage i = null;
		try{
			i = ImageIO.read(ImageCollector.class.getResource("/"+ name + ".png")).getSubimage(dx, dy, w, h);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Image not found.");
		}
		SpriteNode n = new SpriteNode(i,null);
		n.setNext(n);
		return n;
	}

	public static ImageCollector getInstance() {
		if(me == null){
			synchronized(SpriteSheet.class){
				if(me == null){
					me = new ImageCollector();
				}
			}
		}
		return me;
	}
}

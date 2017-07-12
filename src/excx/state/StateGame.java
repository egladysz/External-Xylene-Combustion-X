package excx.state;

import java.awt.Color;
import java.awt.Graphics2D;

import excx.ExcX;
import excx.IO.ImageCollector;
import excx.buttons.ButtonCommandGameState;
import excx.buttons.ButtonCommandUnpause;
import excx.buttons.MainMenuButton;
import excx.data.Keyboard;
import excx.data.SpriteSheet;
import excx.entities.*;
import excx.entities.player.Player;
import excx.entities.player.PlayerDemo;
import excx.entities.player.PlayerMeta;
import excx.entities.player.PlayerOrtho;
import excx.entities.player.PlayerPara;
import excx.entities.player.PlayerStateWin;
import excx.entities.player.PlayerType;
import excx.entities.player.PlayerXylol;
import excx.levelcommands.Level;
import excx.leveldata.LevelData;


public class StateGame extends State{
	
	public static GameField field = new GameField(ExcX.WIDTH/20,ExcX.WIDTH/40,ExcX.WIDTH/5*3, ExcX.HEIGHT/15*14);;
	public static Player you;
	public PlayerType yourName;
	private MenuNode top;
	private MenuNode currentSelection;
	
	private boolean paused;
	SpriteSheet spriteSheet = SpriteSheet.getInstance();
	ImageCollector imager = ImageCollector.getInstance();
	String[] images = {"item_life","orthobot","orthobomb","bullet_xylol","player_xylol_idle","player_xylol_dying","bullet_demo","enemy_remilia_ted","bullet_triangle_red_medium","game_two","player_demo_idle","bullet_circle_red_medium","bullet_circle_medium"};
	Level currentLevel;
	int levelIndex;
	Level[] levels;
	
	public StateGame(){
		MenuNode a = new MenuNode(new MainMenuButton(50,300,"CONTINUE",new ButtonCommandUnpause(this)),null,null);//tp player select
		MenuNode b = new MenuNode(new MainMenuButton(50,320,"RESTART",new ButtonCommandGameState(StateType.GAME)),null,a);//to scores
		MenuNode c = new MenuNode(new MainMenuButton(50,340,"PLAYER SELECT",new ButtonCommandGameState(StateType.PLAYERSELECT)),null,b);//to options
		MenuNode d = new MenuNode(new MainMenuButton(50,360,"MAIN MENU",new ButtonCommandGameState(StateType.MAINMENU)),a,c);
		a.setNext(b);
		a.setPrevious(d);
		b.setNext(c);
		c.setNext(d);
		a.getValue().setOffColor(Color.WHITE);
		a.getValue().setOffColor(Color.CYAN);
		currentSelection = a;
		while (b != currentSelection){
			if(b != null){
				b.getValue().setOffColor(Color.WHITE);
				b.getValue().setOffColor(Color.CYAN);
			}
			b = b.getNext();
		}
		top = a;
		field.getEntityList().clear();
		currentSelection.getValue().beSelected();
		
		//image creation
		for(String s: images){
			if (spriteSheet.getSpriteNode(s) == null)spriteSheet.addSpriteGroup(s, imager.getImage(s));
		}
		String[] playernames = {"demo","para","meta","ortho"};
		for(String s: playernames){
			spriteSheet.addSpriteGroup("player_"+s+"_idle", imager.getImageLoop("player_"+s+"_idle", 32, 32, 0, 4));
			spriteSheet.addSpriteGroup("player_"+s+"_bomb", imager.getImageLoop("player_"+s+"_bomb", 32, 32, 0, 4));
			spriteSheet.addSpriteGroup("player_"+s+"_dying", imager.getImageLoop("player_"+s+"_dying", 32, 32, 0, 4));
		}
		for(int i = 0; i <= 10; i++){
			if (spriteSheet.getSpriteNode("item_power_"+i) == null)spriteSheet.addSpriteGroup("item_power_"+i, imager.getImage("item_power_"+i));
		}
		spriteSheet.addSpriteGroup("boss_old", imager.getImageLoop("boss_old", 64, 64, 0, 16));
		
		LevelData data = LevelData.getInstance();
		Level[] levl = {
				new Level(data.makeLevel(2)),
				new Level(data.makeLevel(1))
			};//TODO: Add more levels
		levels = levl;
	}
	public void setPlayer(PlayerType player){
		yourName = player;
		switch(player){
		case DEMO:
			you = new PlayerDemo(field.getWidth()/2,7*field.getHeight()/8);
			break;
		case PARAXYLENE:
			you = new PlayerPara(field.getWidth()/2,7*field.getHeight()/8);
			break;
		case METAXYLENE:
			you = new PlayerMeta(field.getWidth()/2,7*field.getHeight()/8);
			break;
		case ORTHOXYLENE:
			you = new PlayerOrtho(field.getWidth()/2,7*field.getHeight()/8);
			break;
		case XYLOL:
			you = new PlayerXylol(field.getWidth()/2,7*field.getHeight()/8);
			break;
		default:
			System.out.println("Error: Player not a valid type.");
		}
	}
	public void prepare(){
		levelIndex = 0;
				
		field.setTimer(0);
		setPaused(false);
		currentSelection.getValue().beUnSelected();
		currentSelection = top;
		currentSelection.getValue().beSelected();
		field.getEntityList().clear();
		field.add(you);
		currentLevel = (Level)levels[levelIndex].clone();
		field.setLevel(currentLevel);
	}

	@Override
	public void tick() {
		if (paused){
			if(Keyboard.getInstance().escapeTyped){
				setPaused(false);
			}
			if(Keyboard.getInstance().downTyped){
				currentSelection.getValue().beUnSelected();
				currentSelection = currentSelection.getNext();
				currentSelection.getValue().beSelected();
			}
			if(Keyboard.getInstance().upTyped){
				currentSelection.getValue().beUnSelected();
				currentSelection = currentSelection.getPrevious();
				currentSelection.getValue().beSelected();
			}
			if(Keyboard.getInstance().zTyped){
				if(currentSelection.getValue()!= null)
				currentSelection.getValue().press();
			}
			return;
		}
		if(Keyboard.getInstance().escapeTyped){
			setPaused(true);
			currentSelection.getValue().beUnSelected();
			currentSelection = top;
			currentSelection.getValue().beSelected();
			return;
		}
		field.tick();
		if(levelIndex >= levels.length) return;
		if( currentLevel.isOver){
			levelIndex++;
			if(levelIndex >= levels.length){
				you.setPlayerState(new PlayerStateWin(you));
			}
			else{
				currentLevel = (Level)levels[levelIndex].clone();
				field.setLevel(currentLevel);
				field.setTimer(0);
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		field.render(g);
		
		g.drawImage(spriteSheet.getSpriteNode("game_two").getImage(), 0, 0, null);
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(24.0F));
		int lifeDistance = (int) g.getFontMetrics().getStringBounds("Lives: ", g).getWidth();
		g.drawString("Lives: ", (int) (520), (int) ((ExcX.HEIGHT)-260 + g.getFontMetrics().getStringBounds("Lives: ", g).getHeight()/4));
		for(int i = 0; i< you.getLives(); i++){
			g.drawImage(spriteSheet.getSpriteNode("item_life").getImage(), 450+lifeDistance + 17*i, (ExcX.HEIGHT)-240-8, null);
		}
		String s = "Power: ";
		g.drawString(s, (int) (520), (int) ((ExcX.HEIGHT)-160 + g.getFontMetrics().getStringBounds(s, g).getHeight()/4));
		for(int i = 10; i<you.getMaxPower(); i+=10){
			int power = (i>you.getPower())?0:((i+10<=you.getPower())?10:you.getPower()-i);
			g.drawImage(spriteSheet.getSpriteNode("item_power_"+power).getImage(), 450+lifeDistance + 17*((i/10)-1), (ExcX.HEIGHT)-140-8, null);
		}
		
		if (paused){
			g.setColor(new Color(200,105,255,64));
			g.fillRect((int) field.getX()-1, (int) field.getY()-1, field.getWidth()+2, field.getHeight()+2);
			g.setColor(Color.white);
			
			g.setFont(g.getFont().deriveFont(40.0F));
			g.drawString("PAUSED", (int) (50), (int) ((ExcX.HEIGHT/2) + g.getFontMetrics().getStringBounds("PAUSED", g).getHeight()/4));
			g.drawLine(50,(int) ((int) (ExcX.HEIGHT/2)+5+g.getFontMetrics().getStringBounds("PAUSED", g).getHeight()/4),(int) ((int) 50+g.getFontMetrics().getStringBounds("PAUSED", g).getWidth()),(int) ((ExcX.HEIGHT/2) +5+ g.getFontMetrics().getStringBounds("PAUSED", g).getHeight()/4));
			
			g.setFont(g.getFont().deriveFont(12.0F));
			MenuNode b = currentSelection.getNext();
			currentSelection.getValue().render(g);
			while (b != currentSelection){
				if(b != null)
					b.getValue().render(g);
				b = b.getNext();
			}
		}
	}
	public boolean isPaused(){
		return paused;
	}
	public void setPaused(boolean ps){
		paused = ps;
	}
}

package excx.entities.patterns;

import excx.entities.GameField;
import excx.entities.Team;

public class ShotPatternDelayer extends ShotPattern{
	
	int delayTime;
	ShotPattern pattern;
	int coolDown;
	
	public ShotPatternDelayer(GameField f, ShotPattern pattern, int delayTime){
		super(f);
		this.delayTime = delayTime;
		this.pattern = pattern;
		coolDown = 0;
	}

	@Override
	public void tick(double d, double e, Team team) {
		if(coolDown == 0){
			int initCycle = pattern.getCycles();
			pattern.tick(d, e, team);
			//System.out.println(initCycle + " : " + pattern.getCycles());
			if(initCycle != pattern.getCycles()){
				coolDown = delayTime;
			}
			
			return;
		}
		coolDown--;
	}
	
}

package Minigame.Ladder;

import java.util.HashMap;
import java.util.Map;

public class Dot {
	int X,Y;
	int linkedX, linkedY;
	LadderLine line;
	boolean drawFlag=false;
	Map<Integer, Integer> dot = new HashMap<Integer, Integer>();
	
	public Dot(int y, LadderLine line) {
		this.Y = y;
		this.X = line.DotX;
		this.line = line;
		
		dot.put(this.X, this.Y);
	}	
	public void setFlag(boolean flag) {
		drawFlag = flag;
	}
	
	public boolean isDraw() {
		return drawFlag;
	}
	public Map<Integer, Integer> getDot(){
		return dot;
	}
	
}

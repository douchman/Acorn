package Minigame.Ladder;

import java.util.ArrayList;
import java.util.List;

import Minigame.Component;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class LadderThread2 extends Thread{
	
	private AnchorPane ladderField;
	private LadderBtn btn;
	private int startX, startY;
	private LadderLine2 line;
	private int endFlag=500;
	private List<Line> listLine;
	private Component compo;
	private int processY;
	public LadderThread2(LadderBtn btn, LadderLine2 line, 
			AnchorPane ladderField) {
		this.ladderField = ladderField;
		this.btn = btn;
		this.line = line;
		compo = new Component();
		listLine = new ArrayList<Line>();
		startX = btn.getCenterX();
		startY = btn.getCenterY();		
		this.processY = startY;
		
	}
	
	@Override
	public void run() {
		//Dot tmpDot = new Dot(startX, processY);
		while(true) {		
			try {
				if(processY == endFlag)
					break;
				Thread.sleep(10);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						//System.out.println("chk");
						
						ladderField.getChildren().add(
								compo.getThreadLine(startX, processY, startX, processY));
						//if(processY == line.)
					}
				});
				processY+=2;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

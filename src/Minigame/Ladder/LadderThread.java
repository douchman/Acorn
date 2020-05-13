package Minigame.Ladder;

import java.util.ArrayList;
import java.util.List;

import Minigame.Component;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class LadderThread extends Thread{
	
	private int ENDVALUE;
	private AnchorPane ladderField;
	private LadderLine line;
	private List<Line> listLine;
	private Component compo;
	private int processY;
	private int processX;
	
	public LadderThread(LadderBtn btn,LadderLabel lbl, LadderLine line, 
			AnchorPane ladderField) {
		this.ladderField = ladderField;
		this.line = line;
		this.processY = btn.getCenterY();
		this.processX = line.DotX;
		listLine = new ArrayList<Line>();
		compo = new Component();	
		ENDVALUE = lbl.getCneterY();
	}
		
	Runnable move = new Runnable() {
		@Override
		public void run() {
			Line line = compo.getThreadLine(processX, processY, processX, processY);

			/*
			ladderField.getChildren().add(
					compo.getThreadLine(processX, processY, processX, processY));
			*/
			
			ladderField.getChildren().add(line);
			listLine.add(line);
			
		}		
	};
	
	Runnable removeLine = new Runnable() {
		@Override
		public void run() {
			ladderField.getChildren().removeAll(listLine);	
		}		
	};

	@Override
	public void run() {
		while(true) {
			if(processY == ENDVALUE) {
				//Platform.runLater(removeLine);
				/*
				 * 
				 * 이부분에서 종료시 어떻게 처리 할 지  생각해보자
				 * 
				 * 
				 * */
				
				break;
			}
			try {
				Thread.sleep(5);
				Platform.runLater(move);
				for(Dot dot : line.listLinkedDot){
					if(dot.Y == processY) {					
						if(processX > dot.X) {								
							for(processX=line.DotX; processX >=dot.X; processX--) {							
								Thread.sleep(5);		
								Platform.runLater(move);
								}						
							}							
						else if(processX < dot.X) {
							for(processX=line.DotX; processX <= dot.X; processX++) {
								Thread.sleep(5);		
								Platform.runLater(move);
								}
							}
						line = dot.line;	
						}
					}
					processY+=1;				
			}
			catch (Exception e) {
				e.printStackTrace();
				}
			}
		
	}
}

package Minigame.Ladder;

import java.util.ArrayList;
import java.util.List;

import Minigame.Component;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LadderThread extends Thread{
	
	private int ENDVALUE;
	private AnchorPane ladderField;
	private LadderLine line;
	private List<Line> listLine;
	private List<LadderLabel> listEndLabel;
	private Component compo;
	private int processY;
	private int processX;
	private Parent root;
	private Stage resultStage;
	
	public LadderThread(LadderBtn btn,LadderLabel lbl, LadderLine line, 
			AnchorPane ladderField, Parent root, List<LadderLabel> listEndLabel) {
		this.listEndLabel = listEndLabel;
		this.root = root;
		this.ladderField = ladderField;
		this.line = line;
		this.processY = btn.getCenterY();
		this.processX = line.DotX;
		listLine = new ArrayList<Line>();
		compo = new Component();	
		ENDVALUE = lbl.getCneterY();
		resultStage = new Stage();
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
				 * �̺κп��� ����� ��� ó�� �� ��  �����غ���
				 * 
				 * 
				 * */
				try {
					
					System.out.println("process"+processX);
					for(LadderLabel lbl : listEndLabel) {
						System.out.println(lbl.getCenterX());
						if(lbl.getCenterX() == (processX)) {
							Label start = (Label)root.lookup("#startTxt");
							Label end = (Label)root.lookup("#endTxt");
							resultStage.setScene(new Scene(root));
							
							start.setText(line.getLadderBtn().getText()+"��~");
							end.setText(lbl.getText()+ " ��÷!");
							resultStage.show();
						}
							
						
							
					}
					Thread.sleep(4000);
					Platform.runLater(removeLine);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
							//processX-=1;
							}							
						else if(processX < dot.X) {
							for(processX=line.DotX; processX <= dot.X; processX++) {
								Thread.sleep(5);		
								Platform.runLater(move);
								}
							//processX-=1;
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
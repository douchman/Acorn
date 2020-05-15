package Minigame.Ladder;

import java.util.ArrayList;
import java.util.List;

import Minigame.Component;
import Minigame.Service.MinigameComponentImpl;
import Minigame.ServiceImpl.MinigameComponent;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LadderThread extends Thread{
	
	private int ENDVALUE;
	private AnchorPane ladderField;
	private LadderLine line;
	private Button startBtn;
	private List<Line> listLine;
	private List<LadderLabel> listEndLabel;
	//private Component compo;
	private int processY;
	private int processX;
	private Parent root;
	private MinigameComponent componentServ;
	
	public LadderThread(LadderBtn btn,LadderLabel lbl, LadderLine line, 
			AnchorPane ladderField, Parent root, List<LadderLabel> listEndLabel) {
		this.listEndLabel = listEndLabel;
		this.root = root;
		this.ladderField = ladderField;
		this.startBtn = btn;
		// 시작값얻기
		this.line = line;
		this.processY = btn.getCenterY();
		this.processX = line.DotX;
		listLine = new ArrayList<Line>();
		//compo = new Component();	
		componentServ = new MinigameComponentImpl();
		ENDVALUE = lbl.getCneterY();
		//resultStage = new Stage();
		//resultStage.setScene(new Scene(root));
	}
		
	Runnable move = new Runnable() {
		@Override
		public void run() {
			Line line = componentServ.getThreadLine(processX, processY, processX, processY);

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
				try {
					
					System.out.println("process"+processX);
					for(LadderLabel lbl : listEndLabel) {
						System.out.println(lbl.getCenterX());
						if(lbl.getCenterX() == (processX)) {
							Label start = (Label)root.lookup("#startTxt");
							Label end = (Label)root.lookup("#endTxt");
							Platform.runLater(new Runnable() {
								
								@Override
								public void run() {
									Stage resultStage = (Stage)root.getScene().getWindow();
									resultStage.show();
									start.setText(startBtn.getText()+"님~");
									end.setText(lbl.getText()+ " 당첨!");
									
								}
							});
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
							processX=dot.X;
							}							
						else if(processX < dot.X) {
							for(processX=line.DotX; processX <= dot.X; processX++) {
								Thread.sleep(5);		
								Platform.runLater(move);
								}
							processX=dot.X;
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

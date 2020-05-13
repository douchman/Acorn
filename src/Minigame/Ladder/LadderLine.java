package Minigame.Ladder;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LadderLine extends Line{

	int DotX;
	List<Dot> listDot;
	List<Dot> listLinkedDot;
	private List<LadderLabel> endLabel;
	private LadderLabel lbl;
	private LadderBtn btn;
	private AnchorPane ladderField;
	private LadderThread ladderT;
	private Parent root;
	// 사다리 결과 처리를 위한 루트
	// Ladder 클래스에서 로드되어짐
	public LadderLine(AnchorPane ladderField, LadderBtn btn, LadderLabel lbl, Parent root, List<LadderLabel> endLabel) {
		/*
		this.setStartX(startX);
		this.setStartY(startY);
		this.setEndX(endX);
		this.setEndY(endY);
		*/
		listDot = new ArrayList<Dot>();
		listLinkedDot = new ArrayList<Dot>();
		this.endLabel = endLabel;
		this.root = root;
		this.setStartX(btn.getCenterX());
		this.setStartY(btn.getCenterY());
		this.setEndX(lbl.getCenterX());
		this.setEndY(lbl.getCneterY());
		this.ladderField = ladderField;
		this.lbl = lbl;
		this.btn = btn;
		this.DotX = btn.getCenterX();
		//System.out.println(this.DotX);
		this.setFill(Color.BLACK);
		this.setStrokeWidth(2);
		//ladderT = new LadderThread(btn, lbl, this, ladderField);
		
		
		//setOwnDot(startX,endY, startY);

		// end가 끝점이므로 최대값을 넘겨준다
	}
	
	public void addDotY(int y, LadderLine line) {
		// ###########  자체적으로 새로운 도트를 만드는 메서드다	##############
		// # 이 메서드는 연결된 도트를 제외하고 남은 도트할당량 만큼 동작하게 된다.		 #
		// # 따라서 연결작업이 이루어지지 않은 남은 도트를 만든다.			 		 #	
		// # 이 메서드에서 생성한 도트는 자신이 가지고 있는 도트 리스트에 더해지고	 	 #
		// # 이웃하는 라인의  링크드 도트 리스트에 들어가게된다.					 #
		// # ( 사실상 우측으로 가면서 링크 한다.)							 #
		// ###########################################################
		
		Dot tmpDot = new Dot(y,this);
		tmpDot.linkedX = line.DotX;
		tmpDot.linkedY = y;
		// 먼저 y값과 자기라인의 객체를 넘겨  새로운 도트를 만들고 
		// 생성한 도트를 자기 도트로 (리스트) 저장한다.
		Dot tmpDot2 = new Dot(y,line);
		// 그리고 해당 도트와 동일한 값의 y좌표와 이웃하는 라인을 넘겨주어 도트를만든다.
		// 다른 라인의 객체를 넘겨주면 도트 클래스에서 해당 라인의 x좌표를 이용하여 도트를 만든다
		listDot.add(tmpDot);
		line.addLinkedDotList(tmpDot);
		listLinkedDot.add(tmpDot2);
		line.addOwndDotList(tmpDot2);
		// 그리고 이웃라인과 링크된 도트를 링크드리스트에 더한다.
		// 해당 도트를 자신의 링크드 도트리스트에 더했다면 
		// 이제 이도트를 옆라인의 링크드 도트 리스트에 저장해야한다.
		// 이 도트에서 넘겨준 Line 객체는 해당 객체의 라인정보와 동일하다.
	}
	
	public void addOwndDotList(Dot dot) {
		listDot.add(dot);
	}
	public void addLinkedDotList(Dot dot) {
		// 다른 라인에서 생성하고 링크한 도트를
		// 링크된 도트리스트에 넣는 메서드
		listLinkedDot.add(dot);
	}
	public Button getLadderBtn() {
		return btn;
	}

	public void setBtn(LadderBtn btn) {
		this.btn = btn;
		
		this.btn.setOnAction(e->{
			btnPressed(e);
		});
		
	}
	
	public void btnPressed(ActionEvent e) {
		//Button btn = (Button)e.getSource();
		//System.out.println(btn.getText());
		ladderT = new LadderThread(btn, lbl, this, ladderField, root,endLabel );
		ladderT.start();
		
	}
	public void displayDot() {
		for(Dot d : listDot){
			System.out.println("설정된 자체 도트: "+d.getDot());
		}
		for(Dot d : listLinkedDot){
			System.out.println("연결된 도트: "+d.getDot());
		}
	}
	// Vertex 는 x는 고정 y값만 증가!
	
	
	
}

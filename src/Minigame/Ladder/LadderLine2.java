package Minigame.Ladder;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LadderLine2 extends Line{

	int DotX;
	List<Dot> listDot = new ArrayList<Dot>();
	List<Dot> listLinkedDot = new ArrayList<Dot>();
	
	List<Integer> dot = new ArrayList<Integer>();
	List<Integer> linkedDot = new ArrayList<Integer>();
	
	private LadderLabel lbl;
	private LadderBtn btn;
	private AnchorPane ladderField;
	private LadderThread2 ladderT;
	
	public LadderLine2(AnchorPane ladderField, LadderBtn btn, LadderLabel lbl) {
		/*
		this.setStartX(startX);
		this.setStartY(startY);
		this.setEndX(endX);
		this.setEndY(endY);
		*/
		
		this.setStartX(btn.getCenterX());
		this.setStartY(btn.getCenterY());
		this.setEndX(lbl.getCenterX());
		this.setEndY(lbl.getCneterY());
		this.ladderField = ladderField;
		this.lbl = lbl;
		this.btn = btn;
		this.DotX = btn.getCenterX();
		this.setFill(Color.BLACK);
		this.setStrokeWidth(2);
		ladderT = new LadderThread2(btn, this, ladderField);
		
		
		//setOwnDot(startX,endY, startY);

		// end�� �����̹Ƿ� �ִ밪�� �Ѱ��ش�
	}
	
	public void addDotY(int y) {
		dot.add(y);
		linkedDot.add(this.DotX+135);
		
	}
	
	public void addOwndDotList(Dot dot) {
		listDot.add(dot);
	}
	public void addLinkedDotList(Dot dot) {
		// �ٸ� ���ο��� �����ϰ� ��ũ�� ��Ʈ��
		// ��ũ�� ��Ʈ����Ʈ�� �ִ� �޼���
		listLinkedDot.add(dot);
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
		ladderT.start();
	}
	public void displayDot() {
		for(Dot d : listDot){
			System.out.println("������ ��ü ��Ʈ: "+d.getDot());
		}
		for(Dot d : listLinkedDot){
			System.out.println("����� ��Ʈ: "+d.getDot());
		}
	}
	// Vertex �� x�� ���� y���� ����!
	
}

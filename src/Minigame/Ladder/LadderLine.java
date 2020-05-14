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
	// ��ٸ� ��� ó���� ���� ��Ʈ
	// Ladder Ŭ�������� �ε�Ǿ���
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

		// end�� �����̹Ƿ� �ִ밪�� �Ѱ��ش�
	}
	
	public void addDotY(int y, LadderLine line) {
		// ###########  ��ü������ ���ο� ��Ʈ�� ����� �޼����	##############
		// # �� �޼���� ����� ��Ʈ�� �����ϰ� ���� ��Ʈ�Ҵ緮 ��ŭ �����ϰ� �ȴ�.		 #
		// # ���� �����۾��� �̷������ ���� ���� ��Ʈ�� �����.			 		 #	
		// # �� �޼��忡�� ������ ��Ʈ�� �ڽ��� ������ �ִ� ��Ʈ ����Ʈ�� ��������	 	 #
		// # �̿��ϴ� ������  ��ũ�� ��Ʈ ����Ʈ�� ���Եȴ�.					 #
		// # ( ��ǻ� �������� ���鼭 ��ũ �Ѵ�.)							 #
		// ###########################################################
		
		Dot tmpDot = new Dot(y,this);
		tmpDot.linkedX = line.DotX;
		tmpDot.linkedY = y;
		// ���� y���� �ڱ������ ��ü�� �Ѱ�  ���ο� ��Ʈ�� ����� 
		// ������ ��Ʈ�� �ڱ� ��Ʈ�� (����Ʈ) �����Ѵ�.
		Dot tmpDot2 = new Dot(y,line);
		// �׸��� �ش� ��Ʈ�� ������ ���� y��ǥ�� �̿��ϴ� ������ �Ѱ��־� ��Ʈ�������.
		// �ٸ� ������ ��ü�� �Ѱ��ָ� ��Ʈ Ŭ�������� �ش� ������ x��ǥ�� �̿��Ͽ� ��Ʈ�� �����
		listDot.add(tmpDot);
		line.addLinkedDotList(tmpDot);
		listLinkedDot.add(tmpDot2);
		line.addOwndDotList(tmpDot2);
		// �׸��� �̿����ΰ� ��ũ�� ��Ʈ�� ��ũ�帮��Ʈ�� ���Ѵ�.
		// �ش� ��Ʈ�� �ڽ��� ��ũ�� ��Ʈ����Ʈ�� ���ߴٸ� 
		// ���� �̵�Ʈ�� �������� ��ũ�� ��Ʈ ����Ʈ�� �����ؾ��Ѵ�.
		// �� ��Ʈ���� �Ѱ��� Line ��ü�� �ش� ��ü�� ���������� �����ϴ�.
	}
	
	public void addOwndDotList(Dot dot) {
		listDot.add(dot);
	}
	public void addLinkedDotList(Dot dot) {
		// �ٸ� ���ο��� �����ϰ� ��ũ�� ��Ʈ��
		// ��ũ�� ��Ʈ����Ʈ�� �ִ� �޼���
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
			System.out.println("������ ��ü ��Ʈ: "+d.getDot());
		}
		for(Dot d : listLinkedDot){
			System.out.println("����� ��Ʈ: "+d.getDot());
		}
	}
	// Vertex �� x�� ���� y���� ����!
	
	
	
}

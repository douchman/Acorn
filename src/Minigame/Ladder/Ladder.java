package Minigame.Ladder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Minigame.Component;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Ladder {
	
	private final static int MAX_NODE_CNT = 5;
	private int idx;
	private FXMLLoader main, result;
	private Component compo;
	private Parent rootLadder, rootResult;
	private Stage ladderStage, resultPage;
	private AnchorPane ladderField;
	private Button btnAddSlot,btnClear, btnMakeladder;
	private List<TextField> listStartNode, listEndNode;
	private List<LadderBtn> listStartBtn;
	private List<LadderLabel> listEndLabel;
	private List<LadderLine> listLine;
	private double LadderFieldLayoutX=0.0;
	
	private List<Integer> listRoot = new ArrayList<Integer>();
	private List<Integer> listDotCnt = new ArrayList<Integer>();
	private Random rand = new Random();
	
	private int fieldCnt=0;
	// #startField   #endField
	public Ladder() {
		idx = 0;
		main = new FXMLLoader(getClass().getResource("../FXML/ladder.fxml"));
		result = new FXMLLoader(getClass().getResource("../FXML/ladderResult.fxml"));
		compo = new Component();
		
		listStartNode = new ArrayList<TextField>();
		listEndNode = new ArrayList<TextField>();
		
		listStartBtn = new ArrayList<LadderBtn>();
		listEndLabel = new ArrayList<LadderLabel>();
		
		listLine = new ArrayList<LadderLine>();
		try {
		
			rootLadder = main.load();
			rootResult = result.load();
			
			ladderStage = new Stage();
			//resultPage = new Stage();
			
			ladderStage.setScene(new Scene(rootLadder));
			//resultPage.setScene(new Scene(rootResult));
			
			setComponent();		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setComponent() {
	
		btnAddSlot = (Button)rootLadder.lookup("#btnAddslot");
		btnClear = (Button)rootLadder.lookup("#btnClear");	
		btnMakeladder = (Button)rootLadder.lookup("#btnMakeladder");
		
		ladderField = (AnchorPane)rootLadder.lookup("#ladderField");
		btnMakeladder.setDisable(true);
		
		btnAddSlot.setOnAction(e->{
			addSlot();
		});
		
		btnClear.setOnAction(e->{
			clearField();
		});
		
		btnMakeladder.setOnAction(e->{
			 makeLadder();
		});

	}
	
	private void clearField() {
		//���θ����
		btnMakeladder.setDisable(true);
		LadderFieldLayoutX = 0.0;
		idx=0;
		fieldCnt=0;
		// ���Լ��� �����ϴ� ������ �ʱ�ȭ�ϰ� 
		
		listStartBtn.clear();
		listEndLabel.clear();
		listStartNode.clear();
		listEndNode.clear();
		listDotCnt.clear();
		listLine.clear();
		listRoot.clear();
		// ��� ����Ʈ�� ����.
		
		
		
		btnAddSlot.setDisable(false);
		// ��Ȱ��ȭ �Ǿ��ִ� �߰���ư�� Ȱ��ȭ�ϰ�
		//ladderField.getChildren().removeAll(listStartNode);
		ladderField.getChildren().clear();
		//ladderField.setVisible(false);
		// ��ٸ��ʵ��� �ڽĳ�带 ��� �����.
		
	}
	
	private void addSlot() {
		
		fieldCnt++;
		// ���Լ� ��� ���� ������ ������Ų��.
		if(fieldCnt >= MAX_NODE_CNT) {
			btnAddSlot.setDisable(true);
			// ���Գ���� ���� �ִ�ġ�� �Ѿ�� ��ư ��Ȱ��ȭ
		}
		if(fieldCnt >=2) {
			btnMakeladder.setDisable(false);
		}
		listStartNode.add(compo.getLadderTxtField(LadderFieldLayoutX, 0.0));
		listEndNode.add(compo.getLadderTxtField(LadderFieldLayoutX, 545.0));
		
		ladderField.getChildren().add(listStartNode.get(idx));
		//startNode.get(idx).setLayoutX(LadderFieldLayoutX);
		//startNode.get(idx).setLayoutY(0.0);
		
		ladderField.getChildren().add(listEndNode.get(idx));
		//endNode.get(idx).setLayoutX(LadderFieldLayoutX);
		//endNode.get(idx).setLayoutY(545.0);
			
		// ��ٸ� ����⸦ ������ 
		// ��� ���ۺκ��� ��ư���� �ٲٰ�
		// �ϴ� �κ��� �󺧷� �ٲپ ǥ�� �ϵ��� �����.
		LadderFieldLayoutX += listStartNode.get(idx).getPrefWidth() + 15.0;
		idx++;
	}

	private void makeLadder() {
		btnMakeladder.setDisable(true);
		btnAddSlot.setDisable(true);
		// ��ٸ� ����⸦�ϸ� �߰��� �߰��� �� ������ ���´�.
		ladderField.getChildren().clear();
		// ��ٸ� �ʵ忡 �����ߴ� �Է��ʵ带 ��� �����
		
		// �Է��ߴ� ������ ������� ���ο� ��带 ��ġ�Ѵ�.
		for(TextField txtfield : listStartNode) {
			LadderBtn btn = new LadderBtn(txtfield);
			//ladderField.getChildren().add(btn);
			ladderField.getChildren().add(btn);
			listStartBtn.add(btn);	
		}
		for(TextField txtfield : listEndNode) {			
			LadderLabel lbl = new LadderLabel(txtfield);
			//ladderField.getChildren().add(lbl);	
			ladderField.getChildren().add(lbl);
			listEndLabel.add(lbl);
		}
	
		for(int idx =0; idx<fieldCnt; idx++) {	
			//System.out.println("���� �׷���");
			listLine.add(new LadderLine(
					  ladderField,
					  listStartBtn.get(idx),
					  listEndLabel.get(idx)
					  ,rootResult,listEndLabel));
			
			ladderField.getChildren().add(listLine.get(idx));
			listLine.get(idx).setBtn(listStartBtn.get(idx));
		}
				
		setRootCnt(); 
		// ���λ��� ��Ʈ ������ ������
		// ������ ��Ʈ�� ������� �� ���ο� ��Ʈ�� �����Ѵ�.
		
		// ���� ��� ��ٸ��� ������ �������� �ش� ��Ʈ�� ������� �׷����Ѵ�.
		// �׸��� ������ �ش� ������ ������ ��ü��Ʈ (listDot)�� �÷��׸�  �����Ͽ� �׸���.
		// �ش� ��Ʈ�� ����� ��Ʈ�� �׸��� ���� ��ü ��Ʈ�� �÷��װ��� false�� �ٲپ��ش�.
		DrawRoot();
		
		
	}
	// Dot dot :listLine.get(i).listDot
	// ���߿� ��Ʈ����Ʈ�� �ΰ��� �����ʰ� ��Ʈ ��ü�� ���� ��Ʈ��ǥ�� ����
	// ������ �� ���� ���
	
	public void DrawRoot() {
		for(int i=0; i<fieldCnt; i++) {
			List<Dot> dotList =listLine.get(i).listDot;
			List<Dot> LinkeddotList =listLine.get(i).listLinkedDot;
			for(int j=0; j<dotList.size();j++) {
				// �ش縮��Ʈ�� ��Ʈ ����Ʈ ��ŭ �ݺ��Ѵ�.
				// ��� ��Ʈ�� �湮�ϸ鼭 �׸���.
				if(!dotList.get(j).isDraw() && !LinkeddotList.get(j).isDraw() ) {
					// �ش� ��Ʈ�� ��Ʈ�� ������ �Ǿ����� �������
					// �� flag ���� false �� ��� �׸���.
					
					Line line =compo.getLine(
							dotList.get(j).X, 
							dotList.get(j).Y, 
							LinkeddotList.get(j).X, 
							LinkeddotList.get(j).Y);
					ladderField.getChildren().add(line);
					dotList.get(j).setFlag(true);
					LinkeddotList.get(j).setFlag(true);
					// �׷����� �÷��� ���� �׷ȴٴ� �ǹ̷� true �� ����!
				}
			}
		}
		
	}
	
	public void setRootCnt() {
		/*
		 * ġ������ ���� �ݺ��� ������ �ذ� �ؾ� �Ѵ�.
		 * �־��� ��츦 �����غ���
		 * ���μ��� 2���ϰ���? �ִ� ���� ������ 20���� ���� ��� 
		 * ���� ���� ��Ʈ�� ������ 2 <= root <=8 �̶� 
		 * �ִ� ���� ������ 20���� ��������  ����� ���� �� �� ����.
		 * ��, ���� ������ 2���� ��쿡�� �׳� �������� ���� �� ��Ʈ ���� �ٷ� ��ġ�ϵ��� �Ѵ�.
		 * 
		 * ���� ������ 3�� ���� ? �ִ� ���� ������ 20���� �����ԵǸ�
		 * ���� ���� ��Ʈ�� ������ 2 <= root <=8 �̶� 
		 * ���λ��̴� 2���� 20���� ���� �� �� �� ����.
		 * ���� �ִ� ���� �������� 2�� ���� ���� ������ ������� �ְ� 
		 *  �ִ� ���ΰ������� �������� ���� ����  �� �ϰ� �� ���� ��Ʈ������ �����Ѵ�.
		 *  ex) maxLine =20 , Area1RootCnt = rand.nextInt(maxLine+1)+(maxLine/2); �μ����Ѵ�.
		 * 
		 * ���� ������ 4�� ���� ? �ִ� ���ΰ����� 20���� ���͵�
		 * ������ ���� . �ּ� 2�� �ִ� 8������ ��ġ�� �ǹǷ�
		 * */
		
		
		/*
		��ġ�� �� �ٸ� ������ ���Ѵ�. 
	 	### ��ġ�� �ٸ��� ������  ###
		1. �ּ� �� ���� ���̷� 2���� ��ġ�Ѵ�.
		2. �� ���� ���̿��� 8�� �̻��� ��Ʈ�� �� �� ����.
		2. ��� ������ ���� 20���� ���� �ǵ��� �Ѵ�.
		ex)
		- ������ ������ 5�� �� ��� �� ������ ���̴� �� 4������ �ִ�
		- 4������ �ּ� 2���� ��Ʈ�� ������ ���� �� �̱� ������ ���ΰ������� -1, �� ���� *2 �Ѵ�.
		- �� 5���� ���ο��� 1���� 4�� *2�� �ϴϱ� �ּ� �� ���κ��� 2���� �ּ� ������ 8�� �ΰ� ���� 
		- ������ó�� 8�� �������� ��ġ�� �� ��Ʈ�� �ּڰ����� (fieldCnt-1)*2 �� �־��ش�.
		- �ִ밪�� 20�� �̱� ������ �����ϵ��� 21�� �����Ѵ�.
		*/
		
		// �����ϰ� �Ҵ� �� ��Ʈ ���� ������ִ� ���� �߻� �Լ�
		int rootCnt = rand.nextInt(21-(fieldCnt-1)*2)+((fieldCnt-1)*2);
		//int dotCnt = rootCnt*2;
		int sum = 0;
		// listRoot
		// �� ������ �Ҵ� �� ��Ʈ������ �����ϴ� ����Ʈ
		//System.out.println("�� ��Ʈ �� : "+rootCnt);
		
		switch(fieldCnt) {
		case 1:
			System.out.println("�ּ� 2���̻��� ������ ��������!");
			break;
		case 2:
			listRoot.clear();
			listRoot.add(rootCnt);
			break;
		case 3:
			while(sum != rootCnt) {
				sum =0;
				listRoot.clear();
				for(int i =0; i<fieldCnt-1; i++) {
					listRoot.add(rand.nextInt(rootCnt+1 - rootCnt/2 )+rootCnt/2);
					sum += listRoot.get(i); 
				}
			}
			break;
		default:
			while(sum != rootCnt) {
				listRoot.clear();
				sum = 0;
				for(int i =0; i<fieldCnt-1; i++) {
					listRoot.add(rand.nextInt(7)+2); 
				
					// �ּ�2�� �ִ� 8��
					sum += listRoot.get(i); 
				}		
			}
			break;
		}
		
		//System.out.println("�Ҵ�� ��Ʈ �� : "+listRoot);
		setDot();
	}
	
	public void setDot() {
		// listRoot �� �����
		// cnt�� ������ ������ �� ����
		listDotCnt.clear();
		switch(fieldCnt) {
		case 2:
			for(int i=0;i<fieldCnt;i++)
				listDotCnt.add(listRoot.get(0));
			break;		
		case 3:
			listDotCnt.add(listRoot.get(0));
			listDotCnt.add(listRoot.get(0) + listRoot.get(1));
			listDotCnt.add(listRoot.get(1));
			break;
			
		default:
			listDotCnt.add(listRoot.get(0));
			for(int i=1;i<fieldCnt-1;i++) {
				listDotCnt.add(listRoot.get(i-1) + listRoot.get(i));
			}
			listDotCnt.add(listRoot.get(fieldCnt-2));
			break;
		}
		//System.out.println("�Ҵ�� ���κ� ��Ʈ ��"+listDotCnt);
		
		int loop = listDotCnt.get(0);
		int tmpDotY;
		int i=0;
		for(i =0 ; i <listLine.size()-1;i++) {
			// ���� �� ��ŭ �ݺ��ϴµ� ������ ������ ���� �� �ʿ䰡 ����.
			// ���� ��������� -1 ����
			for(int j=0; j<loop; j++) {
				tmpDotY= rand.nextInt(460)+60;
				listLine.get(i).addDotY(tmpDotY, listLine.get(i+1));		
			}
			loop = Math.abs(loop - listDotCnt.get(i+1));
			//System.out.println("=== "+(i+1)+"�� ����" +"===");
			//listLine.get(i).displayDot();
		}	
		//System.out.println("=== "+(i+1)+"�� ����" +"===");
		//listLine.get(i).displayDot();
	}

	public void startLadder() {
		ladderStage.show();
	}
}

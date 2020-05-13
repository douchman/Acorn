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
		//새로만들기
		btnMakeladder.setDisable(true);
		LadderFieldLayoutX = 0.0;
		idx=0;
		fieldCnt=0;
		// 슬롯수를 제어하는 변수를 초기화하고 
		
		listStartBtn.clear();
		listEndLabel.clear();
		listStartNode.clear();
		listEndNode.clear();
		listDotCnt.clear();
		listLine.clear();
		listRoot.clear();
		// 모든 리스트를 비운다.
		
		
		
		btnAddSlot.setDisable(false);
		// 비활성화 되어있는 추가버튼을 활성화하고
		//ladderField.getChildren().removeAll(listStartNode);
		ladderField.getChildren().clear();
		//ladderField.setVisible(false);
		// 사다리필드의 자식노드를 모두 지운다.
		
	}
	
	private void addSlot() {
		
		fieldCnt++;
		// 슬롯수 제어를 위한 변수를 증가시킨다.
		if(fieldCnt >= MAX_NODE_CNT) {
			btnAddSlot.setDisable(true);
			// 슬롯노드의 수가 최대치를 넘어가면 버튼 비활성화
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
			
		// 사다리 만들기를 누르면 
		// 상단 시작부분을 버튼으로 바꾸고
		// 하단 부분은 라벨로 바꾸어서 표시 하도록 만든다.
		LadderFieldLayoutX += listStartNode.get(idx).getPrefWidth() + 15.0;
		idx++;
	}

	private void makeLadder() {
		btnMakeladder.setDisable(true);
		btnAddSlot.setDisable(true);
		// 사다리 만들기를하면 중간에 추가할 수 없도록 막는다.
		ladderField.getChildren().clear();
		// 사다리 필드에 생성했던 입력필드를 모두 지우고
		
		// 입력했던 내용을 기반으로 새로운 노드를 배치한다.
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
			//System.out.println("직선 그려짐");
			listLine.add(new LadderLine(
					  ladderField,
					  listStartBtn.get(idx),
					  listEndLabel.get(idx)
					  ,rootResult,listEndLabel));
			
			ladderField.getChildren().add(listLine.get(idx));
			listLine.get(idx).setBtn(listStartBtn.get(idx));
		}
				
		setRootCnt(); 
		// 라인사이 루트 결정이 끝나면
		// 결정된 루트를 기반으로 각 라인에 도트를 설정한다.
		
		// 이제 모든 사다리의 설정이 끝났으니 해당 도트를 기반으로 그려야한다.
		// 그리는 과정은 해당 라인의 설정된 자체도트 (listDot)의 플래그를  참고하여 그린다.
		// 해당 도트와 연결된 도트를 그리고 나면 자체 도트의 플래그값을 false로 바꾸어준다.
		DrawRoot();
		
		
	}
	// Dot dot :listLine.get(i).listDot
	// 나중에 도트리스트를 두개로 쓰지않고 도트 자체에 연결 도트좌표로 구현
	// 했을때 쓸 루프 제어문
	
	public void DrawRoot() {
		for(int i=0; i<fieldCnt; i++) {
			List<Dot> dotList =listLine.get(i).listDot;
			List<Dot> LinkeddotList =listLine.get(i).listLinkedDot;
			for(int j=0; j<dotList.size();j++) {
				// 해당리스트의 도트 리스트 만큼 반복한다.
				// 모든 도트를 방문하면서 그린다.
				if(!dotList.get(j).isDraw() && !LinkeddotList.get(j).isDraw() ) {
					// 해당 도트에 루트가 연결이 되어있지 않을경우
					// 즉 flag 값이 false 일 경우 그린다.
					
					Line line =compo.getLine(
							dotList.get(j).X, 
							dotList.get(j).Y, 
							LinkeddotList.get(j).X, 
							LinkeddotList.get(j).Y);
					ladderField.getChildren().add(line);
					dotList.get(j).setFlag(true);
					LinkeddotList.get(j).setFlag(true);
					// 그렸으면 플래그 값을 그렸다는 의미로 true 로 변경!
				}
			}
		}
		
	}
	
	public void setRootCnt() {
		/*
		 * 치명적인 무한 반복의 문제를 해결 해야 한다.
		 * 최악의 경우를 생각해보면
		 * 라인수가 2개일경우는? 최대 라인 갯수가 20개가 나올 경우 
		 * 라인 사이 루트의 갯수는 2 <= root <=8 이라 
		 * 최대 라인 갯수가 20개가 떠버리면  결과가 나올 수 가 없다.
		 * 즉, 라인 갯수가 2개일 경우에는 그냥 랜덤으로 나온 총 루트 수를 바로 배치하도록 한다.
		 * 
		 * 라인 갯수가 3일 경우는 ? 최대 라인 갯수가 20개가 나오게되면
		 * 라인 사이 루트의 갯수는 2 <= root <=8 이라 
		 * 라인사이는 2개고 20개를 충족 할 수 가 없다.
		 * 따라서 최대 라인 갯수에서 2로 나눈 수를 랜덤의 제어값으로 넣고 
		 *  최대 라인갯수에서 랜덤으로 나온 수를  제 하고 그 수를 루트갯수로 설정한다.
		 *  ex) maxLine =20 , Area1RootCnt = rand.nextInt(maxLine+1)+(maxLine/2); 로설정한다.
		 * 
		 * 라인 갯수가 4일 경우는 ? 최대 라인갯수가 20개가 나와도
		 * 문제가 없다 . 최소 2개 최대 8개까지 배치가 되므로
		 * */
		
		
		/*
		배치할 총 다리 갯수를 구한다. 
	 	### 배치할 다리의 갯수는  ###
		1. 최소 각 라인 사이로 2개씩 배치한다.
		2. 각 라인 사이에는 8개 이상의 루트가 올 수 없다.
		2. 모든 라인의 합이 20개로 제한 되도록 한다.
		ex)
		- 라인의 갯수가 5개 일 경우 각 라인의 사이는 총 4군데가 있다
		- 4군데에 최소 2개씩 루트가 들어가도록 만들 것 이기 때문에 라인갯수에서 -1, 그 값에 *2 한다.
		- 즉 5개의 라인에서 1을뺀 4에 *2를 하니까 최소 각 라인별로 2개씩 최소 총합이 8개 인것 따라서 
		- 마지막처럼 8이 나오도록 배치될 총 루트의 최솟값으로 (fieldCnt-1)*2 를 넣어준다.
		- 최대값은 20개 이기 때문에 포함하도록 21로 설정한다.
		*/
		
		// 랜덤하게 할당 될 루트 수를 만들어주는 난수 발생 함수
		int rootCnt = rand.nextInt(21-(fieldCnt-1)*2)+((fieldCnt-1)*2);
		//int dotCnt = rootCnt*2;
		int sum = 0;
		// listRoot
		// 각 구역에 할당 될 루트갯수를 저장하는 리스트
		//System.out.println("총 루트 수 : "+rootCnt);
		
		switch(fieldCnt) {
		case 1:
			System.out.println("최소 2개이상의 라인을 만들어야함!");
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
				
					// 최소2개 최대 8개
					sum += listRoot.get(i); 
				}		
			}
			break;
		}
		
		//System.out.println("할당된 루트 량 : "+listRoot);
		setDot();
	}
	
	public void setDot() {
		// listRoot 를 써야함
		// cnt는 라인의 갯수가 될 것임
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
		//System.out.println("할당된 라인별 도트 수"+listDotCnt);
		
		int loop = listDotCnt.get(0);
		int tmpDotY;
		int i=0;
		for(i =0 ; i <listLine.size()-1;i++) {
			// 라인 수 만큼 반복하는데 마지막 라인은 설정 할 필요가 없다.
			// 따라서 제어변수에서 -1 해줌
			for(int j=0; j<loop; j++) {
				tmpDotY= rand.nextInt(460)+60;
				listLine.get(i).addDotY(tmpDotY, listLine.get(i+1));		
			}
			loop = Math.abs(loop - listDotCnt.get(i+1));
			//System.out.println("=== "+(i+1)+"번 라인" +"===");
			//listLine.get(i).displayDot();
		}	
		//System.out.println("=== "+(i+1)+"번 라인" +"===");
		//listLine.get(i).displayDot();
	}

	public void startLadder() {
		ladderStage.show();
	}
}

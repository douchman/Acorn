package Minigame.Roulette;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MainMenu.MainPage.MainPageController;
import Minigame.Component;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public abstract class Roulette {
	
	// �� �귿���� �ٸ��� �����ؾ��� �޼���� �� �귿�� �ش��ϴ� ������ ���� �޼���
	Runnable testRun;
	RouletteThread rouletteThread;
	
	private Arc pin;
	private Group gr;
	private FXMLLoader loader;
	private Parent root;
	private Stage stage;
	private Button btnFunc, btnChngMod,btnRotate,btnStop;
	private Component compo;
	private MainPageController mainCon;
	//private List<String> listContents = new ArrayList<String>();
	private List<String> listContents;
	//private List<Arc> listrouletteContents = new ArrayList<Arc>();
	public List<RoulettePiece> listrouletteContents2 = new ArrayList<RoulettePiece>();
	private final String [] listColor = {"AQUA","RED","SLATEGRAY","DARKORANGE",
			 "GREEN","DODGERBLUE","GOLD","LIGHTSKYBLUE",
			 "MEDIUMSLATEBLUE","PLUM"};
	private final Color [] listColor2 = {
									Color.AQUA,Color.RED,Color.SLATEGRAY,Color.DARKORANGE,
									Color.GREEN,Color.DODGERBLUE,Color.GOLD,Color.LIGHTSKYBLUE,
									Color.MEDIUMSLATEBLUE,Color.PLUM};
	private VBox ratioField;
	private Pane pane;
	private RouletteMenu rouletteMenu;
	
	public Roulette(RouletteMenu rouletteMenu, MainPageController mainCon) {
		this.rouletteMenu = rouletteMenu;
		this.mainCon = mainCon;
		compo = new Component();
		loader = new FXMLLoader(getClass().
				getResource("../FXML/roulette2.fxml"));
		
		stage = new Stage();
		
		
		try {

			root = loader.load();
			ratioField = (VBox)root.lookup("#ratioBox");
			pane = (Pane)root.lookup("#roulette");
			//sc = new Scene(null);
			setBtn();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("../CSS/app.css").toString());
			stage.setScene(scene);
			//stage.setScene(new Scene(root));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void show() {
		stage.show();
	}
	
	private void setBtn() {
		btnChngMod = (Button)root.lookup("#chngMod");
		btnFunc = (Button)root.lookup("#funcBtn");
		btnRotate = (Button)root.lookup("#btnRotate");
		btnStop =(Button)root.lookup("#btnStop");
		
		btnFunc.setOnAction(e->{
			
			function();
		});
		btnChngMod.setOnAction(e->{
			showModeMenu();
		});
		
		btnRotate.setOnAction(e->{
			rotateRoulette();
		});
	
		btnStop.setOnAction(e->{
			stopRoulette();
		});
		
	}
	public void rotateRoulette() {
		rouletteThread = new RouletteThread(this,gr);
		rouletteThread.start();
	}
	
	public void stopRoulette() {
		rouletteThread.setStop(true);
	}
	
	public void result() {
		// ��� ó�� �޼���
		List<Double> listDistance = new ArrayList<Double>();
		Map<RoulettePiece,Double> map = new HashMap<RoulettePiece,Double>();

		double pinX = pin.getCenterX(), pinY=pin.getCenterY();

		Map.Entry<RoulettePiece,Double> maxEntry = null;
		for(int i =0; i<listrouletteContents2.size(); i++) {
			listDistance.add(listrouletteContents2.get(i).getDistance(pinX, pinY));
			map.put(listrouletteContents2.get(i), 
					listrouletteContents2.get(i).getDistance(pinX, pinY));
		}
		
		for(Map.Entry<RoulettePiece,Double> enrty : map.entrySet()) {
			if(maxEntry == null ||enrty.getValue()<(maxEntry.getValue())) {
				maxEntry = enrty;
			}
		}
		String str = maxEntry.getKey().getName();
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				stage.close();
				try {
					Thread.sleep(3000);
					mainCon.Tsearch(str);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		System.out.println("name : "+maxEntry.getKey().getName());
		/*
		System.out.println("name : "+maxEntry.getKey().getName());
		System.out.println("�ּҰ� : " + maxEntry.getKey().getDistance());
		System.out.println("�ּҰ� : " + maxEntry.getValue());
		*/
		
	}

	public void makeRoulette() {	
		pane.getChildren().clear();
		listrouletteContents2.clear();
		//pane.getChildren().removeAll();
		pin = compo.getArc(200, 40, 20, 20, 75, 30, Color.BLACK);
		double startAngle =0;
		double angleSize = (double)(360 / listContents.size());
		double angle = (((angleSize/2)+startAngle) * ((Math.PI)/180));
		// �ð� �ݴ�������� ȸ��
		gr = new Group();

		for(int i=0; i<listContents.size(); i++) {
			RoulettePiece arc = new RoulettePiece(
					200, 200, 170, 170, startAngle, angleSize, listColor2[i],listContents.get(i));
			listrouletteContents2.add(arc);
			gr.getChildren().add(arc);
			startAngle += angleSize;		
		}
		startAngle =0;
		angleSize = -(double)(360 / listContents.size());	
		double lblRotateValue= (startAngle + angleSize/2);
		
		angle = (((angleSize/2)+startAngle) * ((Math.PI)/180));
		for(int i=0; i<listContents.size(); i++){
			
			angle = (((angleSize/2)+startAngle) * ((Math.PI)/180));
			//System.out.println("angle : "+angle);
			gr.getChildren().add(compo.getArcLabel(listContents.get(i),(Math.cos(angle)*170/2)+185, 
					(Math.sin(angle)*170/2)+190, lblRotateValue));
			listrouletteContents2.get(i).setArea((Math.cos(angle)*170/2)+185, (Math.sin(angle)*170/2)+190);
			startAngle += angleSize;
			lblRotateValue+=angleSize;			
		}
		pane.getChildren().addAll(gr,pin);	
		setRatioField();
	}
	
	public void setRatioField() {
			
		//ratioFiled.cle
		//ratioField.getChildren().removeAll();
		ratioField.getChildren().clear();
		double ratio = 100.0/listContents.size(); 
		//System.out.println(ratio);
		
		for(int i=0;i<listContents.size();i++) {		
			ratioField.getChildren().add(compo.getLabel(listContents.get(i)+ratio+"%", listColor[i]));
			}
	}
	
	public void showModeMenu() {
		stage.close();
		rouletteMenu.displayMenu();
	}
	
	public void setFuncBtnText(String str) {
		this.btnFunc.setText(str);
	}
	
	public void setList(List<String> list) {
		listContents = list;
	}
	public abstract void runRoulette();
	public abstract void initList();
	public abstract void setContentsList();
	public abstract void function();
	public abstract void setFuncBtn();
	
}
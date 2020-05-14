package Minigame.Roulette;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import MainMenu.MainPage.MainPageController;
import Minigame.Component;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomRulette extends Roulette{

	private int fieldCnt = 0;
	private FXMLLoader loader = new FXMLLoader(
			getClass().getResource("../FXML/customMenu.fxml"));
	private List<TextField> listTxtfield;
	private List<String> listContents;
	private Component compo; 
	private Stage menu;
	private Parent root;
	private VBox contentsList;
	public CustomRulette(RouletteMenuController rouletteMenuCon, MainPageController mainCon) {
		super(rouletteMenuCon, mainCon);
		listTxtfield = new ArrayList<TextField>();
		listContents = new ArrayList<String>();
		
		compo = new Component();
		menu = new Stage();
		try {
			root = loader.load();
			menu.setScene(new Scene(root));
			contentsList = (VBox)root.lookup("#contentsList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setFuncBtn();

	}
	
	@Override
	public void runRoulette() {
		super.show();
		
	}
	@Override
	public void setContentsList() {
		super.setList(listContents);
		
	}
	
	@Override
	public void function() {
		//List<TextField> listTxtField = new ArrayList<TextField>();
		Button btnAddlist = (Button)root.lookup("#btnAddlist");
		Button btnApply = (Button)root.lookup("#btnApply");
		//VBox contentsList = (VBox)root.lookup("#contentsList");
		
		initList();
		btnAddlist.setOnAction(e->{
			if(fieldCnt <10) {
				listTxtfield.add(compo.getRouletteTxtField());
				contentsList.getChildren().add(listTxtfield.get(fieldCnt));
				fieldCnt++;
			}
		});
		
		btnApply.setOnAction(e->{
			for(TextField txtfield : listTxtfield){
				listContents.add(txtfield.getText());
			}
			setContentsList();
			super.makeRoulette();
			super.show();
			menu.close();
		});
		
		menu.show();
		
	}
	
	@Override
	public void setFuncBtn() {
		super.setFuncBtnText("ÄÁÅÙÃ÷º¯°æ");
	}
	
	@Override
	public void initList() {
		fieldCnt=0;
		contentsList.getChildren().clear();
		listTxtfield.clear();
		listContents.clear();
		
	}
}

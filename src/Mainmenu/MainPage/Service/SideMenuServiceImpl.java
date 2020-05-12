package Mainmenu.MainPage.Service;

import java.util.List;

import Mainmenu.MainPage.Restaurant;
import Mainmenu.MainPage.Data.RestaurantDataManage;
import Mainmenu.MainPage.Data.RestaurantDataManageImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class SideMenuServiceImpl implements SideMenuService{	
   
	//RestaurantDataManage restaurantData= new RestaurantDataManageImpl();
	Container ctn = new Container();
	MapServiceImpl mapservice = new MapServiceImpl();
	
	public SideMenuServiceImpl() {
		//restaurantData = new RestaurantDataManageImpl();
		
	}

	@Override
	public void RandomList(Parent root) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstR = restaurantData.getRestaurant();
		
		int num = 1;
		for(Restaurant restaurant : lstR) {
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			HBox hb = ctn.getHB(5, Integer.toString(num));
			VBox vb = ctn.getVB(5, 5, Pos.CENTER_LEFT);
			
			ImageView img = ctn.getImg(setImg(restaurant.getImage()), false, 80, 80);
			Label l1 = new Label(num + " " +restaurant.getName());
			Label l2 = new Label(RatetoStar(restaurant.getRate()));
			Label l = new Label(restaurant.getCategory()+" "+PricetoWon(restaurant.getPrice())+" "+restaurant.getBusinesshour());
		
			lv.setFixedCellSize(90);
			l1.setStyle("-fx-font-weight : bold;");
			
			vb.getChildren().addAll(l1,l2,l);
			hb.getChildren().addAll(img,vb);
			lv.getItems().add(hb);

			
			listTopin(root, restaurant.getName(), lstR);
			
			num++;
		}
		
	}
	
	@Override
	public void RandomList(Parent root, int m) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstRm = restaurantData.getRestaurant(m);
		int num = 1;
		
		for(Restaurant restaurant : lstRm) {
			
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			HBox hb = ctn.getHB(5, Integer.toString(num));
			VBox vb = ctn.getVB(5, 5, Pos.CENTER_LEFT);
			
			ImageView img = ctn.getImg(setImg(restaurant.getImage()), false, 80, 80);
			Label l1 = new Label(num + " " +restaurant.getName());
			Label l2 = new Label(RatetoStar(restaurant.getRate()));
			Label l = new Label(restaurant.getCategory()+" "+PricetoWon(restaurant.getPrice())+" "+restaurant.getBusinesshour());
		
			lv.setFixedCellSize(90);
			l1.setStyle("-fx-font-weight : bold;");
			
			vb.getChildren().addAll(l1,l2,l);
			hb.getChildren().addAll(img,vb);
			lv.getItems().add(hb);			
			
			listTopin(root, restaurant.getName(), m, lstRm);
			
			num++;
		}
		
	}
	
	public void listTopin(Parent root, String rname, List<Restaurant> lstR) {
		
		try {
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
				@Override
				public void changed(ObservableValue<? extends HBox> observable, HBox oldValue, HBox newValue) {
					Node nodeout = newValue.getChildren().get(1);
					AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
					Circle c = (Circle)ap.lookup("#"+nodeout.getParent().getId());
					mapservice.resetpin(root);
					mapservice.pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40,lstR);			
				}
			});
		} catch(Exception e2) {
				 return;
			}
	}
	
	public void listTopin(Parent root, String rname, int m, List<Restaurant> lstRm) {
		
		try {
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
				@Override
				public void changed(ObservableValue<? extends HBox> observable, HBox oldValue, HBox newValue) {
					Node nodeout = newValue.getChildren().get(1);
					AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
					Circle c = (Circle)ap.lookup("#"+nodeout.getParent().getId());
					mapservice.resetpin(root);
					mapservice.pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40, m, lstRm);			
				}
			});
		} catch(Exception e2) {
				 return;
			}
	}
	
	public String RatetoStar(int rate) {
		String star = null;
		
		switch (rate) {
		case 0: star="¡Ù¡Ù¡Ù¡Ù¡Ù";
		break;
		case 1: star="¡Ú¡Ù¡Ù¡Ù¡Ù";
		break;
		case 2: star="¡Ú¡Ú¡Ù¡Ù¡Ù";
		break;
		case 3: star="¡Ú¡Ú¡Ú¡Ù¡Ù";
		break;
		case 4: star="¡Ú¡Ú¡Ú¡Ú¡Ù";
		break;
		case 5: star="¡Ú¡Ú¡Ú¡Ú¡Ú";
		break;
		}
		
		return star;
	}

	public String PricetoWon(int price) {
		String won = null;
		
		switch (price) {
		case 1: won = "£Ü";
		break;
		case 2: won = "£Ü£Ü";
		break;
		case 3: won = "£Ü£Ü£Ü";
		break;
		}
		return won;
	}

	public String setImg(String img) {
		if(img==null)
			img= "Mainmenu/Img/acorn.png";
		
		return img;
	}

	@Override
	public void clear(Parent root) {
		ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
		lv.getSelectionModel().clearSelection();
		lv.getItems().clear();
		
	}




}

package Mainmenu.MainPage.Service;


import java.util.ArrayList;
import java.util.List;

import Mainmenu.MainPage.Restaurant;
import Mainmenu.MainPage.Data.RestaurantDataManage;
import Mainmenu.MainPage.Data.RestaurantDataManageImpl;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class MapServiceImpl implements MapService {
	
//	RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
	//List<Restaurant> lstR;
	//List<Restaurant> lstRm;
	Container ctn = new Container();

	public MapServiceImpl() {
		 
	//	lstR = restaurantData.getRestaurant();
		
	}
	
	@Override
	public void creatpin(Parent root) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstR = restaurantData.getRestaurant();
		AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
		int num=1;
		for(Restaurant restaurant : lstR) {
			
			Circle c = ctn.getC(9, Color.DODGERBLUE, Color.BLACK, restaurant.getLoca_x(), restaurant.getLoca_y(), Integer.toString(num));
			ap.getChildren().add(c);
			
			c.setOnMouseClicked(e->{
				resetpin(root);
				pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40,lstR);
				pinTolist(root, c);
			});

		num++;
		}
	}
	
	@Override
	public void creatpin(Parent root, int m) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstRm = restaurantData.getRestaurant(m);
		AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
		int num=1;
		for(Restaurant restaurant : lstRm) {
			
			Circle c = ctn.getC(9, Color.DODGERBLUE, Color.BLACK, restaurant.getLoca_x(), restaurant.getLoca_y(), Integer.toString(num));
			ap.getChildren().add(c);
			
			c.setOnMouseClicked(e->{
				resetpin(root);
				pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40,m,lstRm);
				pinTolist(root, c);
			});

		num++;
		}
		
	}
	
	@Override
	public void resetpin(Parent root) {
		try {
			AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
			ap.getChildren().removeIf(StackPane.class::isInstance);
		} catch (Exception e2) {
			return;
		}	
	}
	
	public void pluspin(AnchorPane ap, String id, double clx, double cly, List<Restaurant> lstR) {
		
		List<String> rn = rnameset(lstR);
		String rname = rn.get(Integer.parseInt(id)-1);
		
		StackPane sp = ctn.getSP(id, clx, cly);
		Rectangle r = ctn.getR(80, 30, Color.PALETURQUOISE, Color.BLACK);
		sp.getChildren().addAll(r,new Text(rname));
		ap.getChildren().add(sp);
	}
	
	public void pluspin(AnchorPane ap, String id, double clx, double cly, int m, List<Restaurant> lstRm) {
		
		List<String> rn = rnameset(lstRm, m);
		String rname = rn.get(Integer.parseInt(id)-1);
		
		StackPane sp = ctn.getSP(id, clx, cly);
		Rectangle r = ctn.getR(80, 30, Color.PALETURQUOISE, Color.BLACK);
		sp.getChildren().addAll(r,new Text(rname));
		ap.getChildren().add(sp);
	}
	
	public void pinTolist(Parent root, Circle c) {
		try {
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");	
			HBox hb = lv.getItems().get(Integer.parseInt(c.getId())-1);
			lv.scrollTo(hb);
			lv.getSelectionModel().select(hb);
		} catch (Exception e2) {
			return;
		}	
	}

	public List<String> rnameset(List<Restaurant> lstR) {
		
		List<String> rname = new ArrayList<String>();
		for(Restaurant restaurant : lstR) 
			rname.add(restaurant.getName());
		
		return rname;
	}

	public List<String> rnameset(List<Restaurant> lstRm, int m) {
		
		List<String> rname = new ArrayList<String>();
		for(Restaurant restaurant : lstRm) 
			rname.add(restaurant.getName());
		
		return rname;
	}
	@Override
	public void clear(Parent root) {
		AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
		ap.getChildren().removeIf(StackPane.class::isInstance);
		ap.getChildren().removeIf(Circle.class::isInstance);
	}

}

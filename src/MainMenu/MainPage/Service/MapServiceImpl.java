package MainMenu.MainPage.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import MainMenu.MainPage.Restaurant;
import MainMenu.MainPage.Data.RestaurantDataManage;
import MainMenu.MainPage.Data.RestaurantDataManageImpl;
import Reviewpage.Review.ReviewListController;
import Reviewpage.Review.ReviewPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MapServiceImpl implements MapService {
	
	Container ctn;

	public MapServiceImpl() {
		ctn = new Container();	
	}
	
	@Override
	public void creatpin(Parent root, String usrID) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstR = restaurantData.getRestaurant();
		AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
		int num=1;
		for(Restaurant restaurant : lstR) {
			
			Circle c = ctn.getC(9, Color.DODGERBLUE, Color.BLACK, restaurant.getLoca_x(), restaurant.getLoca_y(), Integer.toString(num));
			ap.getChildren().add(c);
			
			c.setOnMouseClicked(e->{
				resetpin(root);
				pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40,lstR, root,usrID);
				pinTolist(root, c);
			});

		num++;
		}
	}
	
	@Override
	public void creatpin(Parent root, int m, String usrID) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstRm = restaurantData.getRestaurant(m);
		AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
		int num=1;
		for(Restaurant restaurant : lstRm) {
			
			Circle c = ctn.getC(9, Color.DODGERBLUE, Color.BLACK, restaurant.getLoca_x(), restaurant.getLoca_y(), Integer.toString(num));
			ap.getChildren().add(c);
			
			c.setOnMouseClicked(e->{
				resetpin(root);
				pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40,m,lstRm,root,usrID);
				pinTolist(root, c);
			});

		num++;
		}
		
	}
	
	@Override
	public void creatpin(Parent root, String txt, String usrID) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstRm = restaurantData.getRestaurant(txt);
		AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
		int num=1;
		for(Restaurant restaurant : lstRm) {
			
			Circle c = ctn.getC(9, Color.DODGERBLUE, Color.BLACK, restaurant.getLoca_x(), restaurant.getLoca_y(), Integer.toString(num));
			ap.getChildren().add(c);
			
			c.setOnMouseClicked(e->{
				resetpin(root);
				pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40,txt,lstRm,root,usrID);
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
			e2.printStackTrace();
		}	
	}
	
	public void pluspin(AnchorPane ap, String id, double clx, double cly, List<Restaurant> lstR, Parent root, String usrID) {
		
		List<String> rn = rnameset(lstR);
		String rname = rn.get(Integer.parseInt(id)-1);
		
		StackPane sp = ctn.getSP(id, clx, cly);
		Rectangle r = ctn.getR(80, 30, Color.PALETURQUOISE, Color.BLACK);
		sp.getChildren().addAll(r,new Text(rname));
		ap.getChildren().add(sp);
		
		sp.setOnMouseClicked(e -> {
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			int rid = Integer.parseInt(lv.getSelectionModel().selectedItemProperty().get().getUserData().toString());
			OpenReviewpage(rid,usrID);
		});
	
	}
	
	public void pluspin(AnchorPane ap, String id, double clx, double cly, int m, List<Restaurant> lstRm, Parent root, String usrID) {
		try {
		List<String> rn = rnameset(lstRm, m);
		String rname = rn.get(Integer.parseInt(id)-1);
		StackPane sp = ctn.getSP(id, clx, cly);
		Rectangle r = ctn.getR(80, 30, Color.PALETURQUOISE, Color.BLACK);
		sp.getChildren().addAll(r,new Text(rname));
		ap.getChildren().add(sp);
		
		sp.setOnMouseClicked(e -> {
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			int rid = Integer.parseInt(lv.getSelectionModel().selectedItemProperty().get().getUserData().toString());
			OpenReviewpage(rid,usrID);
		});
		
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void pluspin(AnchorPane ap, String id, double clx, double cly, String txt, List<Restaurant> lstRm, Parent root, String usrID) {
		try {
		List<String> rn = rnameset(lstRm, txt);
		String rname = rn.get(Integer.parseInt(id)-1);
		StackPane sp = ctn.getSP(id, clx, cly);
		Rectangle r = ctn.getR(80, 30, Color.PALETURQUOISE, Color.BLACK);
		sp.getChildren().addAll(r,new Text(rname));
		ap.getChildren().add(sp);
		
		sp.setOnMouseClicked(e -> {
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			int rid = Integer.parseInt(lv.getSelectionModel().selectedItemProperty().get().getUserData().toString());
			OpenReviewpage(rid,usrID);
		});
		

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public void pinTolist(Parent root, Circle c) {
		try {
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");	
			HBox hb = lv.getItems().get(Integer.parseInt(c.getId())-1);
			lv.scrollTo(hb);
			lv.getSelectionModel().select(hb);
		} catch (Exception e2) {
			e2.printStackTrace();
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
	
	public List<String> rnameset(List<Restaurant> lstRm, String txt) {
		
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
	
	private void OpenReviewpage(int rid, String usrID) {
	      FXMLLoader loader  = new FXMLLoader(getClass().getResource("/ReviewPage/Review/ReviewPage.fxml"));
	      Parent root = null;
	      try {
	         root = loader.load();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      ReviewPageController reviewCon = loader.getController();
	      reviewCon.setRoot(root);
	      reviewCon.setId(rid, usrID);

	      
	      Stage stage = new Stage();
	      stage.setScene(new Scene(root));
	      stage.show();
	      
	      reviewCon.Reviewstart();
	   }

}

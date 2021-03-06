package MainMenu.MainPage.Service;

import java.io.IOException;
import java.util.List;

import MainMenu.FirstPage.Service.FirstPageService;
import MainMenu.FirstPage.Service.FirstPageServiceImpl;
import MainMenu.MainPage.MainPageController;
import MainMenu.MainPage.Restaurant;
import MainMenu.MainPage.Data.RestaurantDataManage;
import MainMenu.MainPage.Data.RestaurantDataManageImpl;
import Reviewpage.Review.ReviewListController;
import Reviewpage.Review.ReviewPageController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SideMenuServiceImpl implements SideMenuService{	
	//세근 수정
	private String userId;
	
	Container ctn;
	MapServiceImpl mapservice;
	FirstPageService fservice;
	ReviewPageController reviewCon;
	
	public SideMenuServiceImpl() {
		ctn = new Container();
		mapservice = new MapServiceImpl();
		fservice=new FirstPageServiceImpl();
	}
	//세근 수정
	public void setUsrID(String usrId) {
		this.userId = usrId;
	}
		
	@Override
	public void RandomList(Parent root, String usrID) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstR = restaurantData.getRestaurant();
		
		int num = 1;
		for(Restaurant restaurant : lstR) {
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			HBox hb = ctn.getHB(5, Integer.toString(num));
			hb.setUserData(restaurant.getRid());
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

			
			listTopin(root, restaurant.getName(), lstR,usrID);
			
	
			
			hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent click) {
					if(click.getClickCount() == 2) {
		
					
						OpenReviewpage(restaurant.getRid(),usrID);
						
			
						
					}
				}
			});
			
			num++;
		}
		
	}
	
	@Override
	public void RandomList(Parent root, int m, String usrID) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstRm = restaurantData.getRestaurant(m);
		int num = 1;
		
		for(Restaurant restaurant : lstRm) {
			
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			HBox hb = ctn.getHB(5, Integer.toString(num));
			hb.setUserData(restaurant.getRid());
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
			
			listTopin(root, restaurant.getName(), m, lstRm,usrID);
			
			hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent click) {
					if(click.getClickCount() == 2) {
						OpenReviewpage(restaurant.getRid(),usrID);
					}
				}
			});
			
			num++;
		}
		
	}
	
	@Override
	public void RandomList(Parent root, String txt, String usrID) {
		RestaurantDataManage restaurantData = new RestaurantDataManageImpl();
		List<Restaurant> lstRm = restaurantData.getRestaurant(txt);
		int num = 1;
		
		for(Restaurant restaurant : lstRm) {
			
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			HBox hb = ctn.getHB(5, Integer.toString(num));
			hb.setUserData(restaurant.getRid());
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
			
			listTopin(root, restaurant.getName(), txt, lstRm,usrID);
		
			hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent click) {
					if(click.getClickCount() == 2) {
						OpenReviewpage(restaurant.getRid(),usrID);
					}
				}
			});
			
			
			num++;
		}
		
	}
	
	
	public void listTopin(Parent root, String rname, List<Restaurant> lstR, String usrID) {
		
		try {
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
				@Override
				public void changed(ObservableValue<? extends HBox> observable, HBox oldValue, HBox newValue) {
					
					try {
						Node nodeout = newValue.getChildren().get(1);
					
						AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
						Circle c = (Circle)ap.lookup("#"+nodeout.getParent().getId());
						mapservice.resetpin(root);
						mapservice.pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40,lstR, root,usrID);			
					} catch (Exception e2) {
						e2.printStackTrace();
						}			   
					}
				
				
			});
		} catch(Exception e2) {
				 return;
			}
	}
	
	public void listTopin(Parent root, String rname, int m, List<Restaurant> lstRm, String usrID) {
		
		try {
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
				@Override
				public void changed(ObservableValue<? extends HBox> observable, HBox oldValue, HBox newValue) {
					try {Node nodeout = newValue.getChildren().get(1);
					
					AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
					Circle c = (Circle)ap.lookup("#"+nodeout.getParent().getId());
					mapservice.resetpin(root);
					mapservice.pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40, m, lstRm, root,usrID);			
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					}
			});
		} catch(Exception e2) {
				 return;
			}
	}
	
	public void listTopin(Parent root, String rname, String txt, List<Restaurant> lstRm, String usrID) {
		
		try {
			@SuppressWarnings("unchecked")
			ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
			lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {
				@Override
				public void changed(ObservableValue<? extends HBox> observable, HBox oldValue, HBox newValue) {
					try {Node nodeout = newValue.getChildren().get(1);
					
					AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
					Circle c = (Circle)ap.lookup("#"+nodeout.getParent().getId());
					mapservice.resetpin(root);
					mapservice.pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40, txt, lstRm, root,usrID);			
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					}
			});
		} catch(Exception e2) {
				 return;
			}
	}
	
	public String RatetoStar(int rate) {
		String star = null;
		
		switch (rate) {
		case 0: star="☆☆☆☆☆";
		break;
		case 1: star="★☆☆☆☆";
		break;
		case 2: star="★★☆☆☆";
		break;
		case 3: star="★★★☆☆";
		break;
		case 4: star="★★★★☆";
		break;
		case 5: star="★★★★★";
		break;
		}
		
		return star;
	}

	public String PricetoWon(int price) {
		String won = null;
		
		switch (price) {
		case 1: won = "￦";
		break;
		case 2: won = "￦￦";
		break;
		case 3: won = "￦￦￦";
		break;
		}
		return won;
	}

	public String setImg(String img) {
		if(img==null)
			img= "/MainMenu/Img/acorn.png";
		
		return img;
	}

	@Override
	public void clear(Parent root) {
		ListView<HBox> lv = (ListView<HBox>)root.lookup("#CListView");
		try {
		lv.getSelectionModel().clearSelection();
		lv.getItems().clear();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
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
	      reviewCon = loader.getController();
	      reviewCon.setRoot(root);
	      //System.out.println(usrID);
	      System.out.println("userID : " + userId);
	      System.out.println("shopID : " + rid);
	      if(userId == null)
	    	  userId = "guest";
	      reviewCon.setId(rid, userId);

	   
	      
	      Stage stage = new Stage();
	      stage.setScene(new Scene(root));
	      stage.show();
	      
	      reviewCon.Reviewstart();
	   }


	

}

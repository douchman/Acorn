package MainMenu.MainPage.Service;

import java.util.List;

import MainMenu.MainPage.Restaurant;
import MainMenu.MainPage.Data.RestaurantDataManage;
import MainMenu.MainPage.Data.RestaurantDataManageImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class SideMenuServiceImpl implements SideMenuService{	
   
	Container ctn;
	MapServiceImpl mapservice;
	
	public SideMenuServiceImpl() {
		ctn = new Container();
		mapservice = new MapServiceImpl();
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

			
			listTopin(root, restaurant.getName(), lstR);
			
	
			
			hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent click) {
					if(click.getClickCount() == 2) {
						System.out.println(restaurant.getRid());
					}
				}
			});
			
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
			
			listTopin(root, restaurant.getName(), m, lstRm);
			
			hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent click) {
					if(click.getClickCount() == 2) {
						System.out.println(restaurant.getRid());
					}
				}
			});
			
			num++;
		}
		
	}
	
	@Override
	public void RandomList(Parent root, String txt) {
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
			
			listTopin(root, restaurant.getName(), txt, lstRm);
		
			hb.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent click) {
					if(click.getClickCount() == 2) {
						System.out.println(restaurant.getRid());
					}
				}
			});
			
			
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
					
					try {
						Node nodeout = newValue.getChildren().get(1);
					
						AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
						Circle c = (Circle)ap.lookup("#"+nodeout.getParent().getId());
						mapservice.resetpin(root);
						mapservice.pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40,lstR, root);			
					} catch (Exception e2) {
						e2.printStackTrace();
						}			   
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
					try {Node nodeout = newValue.getChildren().get(1);
					
					AnchorPane ap = (AnchorPane)root.lookup("#mapPane");
					Circle c = (Circle)ap.lookup("#"+nodeout.getParent().getId());
					mapservice.resetpin(root);
					mapservice.pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40, m, lstRm, root);			
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					}
			});
		} catch(Exception e2) {
				 return;
			}
	}
	
	public void listTopin(Parent root, String rname, String txt, List<Restaurant> lstRm) {
		
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
					mapservice.pluspin(ap, c.getId(), c.getLayoutX()-40, c.getLayoutY()-40, txt, lstRm, root);			
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
		case 0: star="�١١١١�";
		break;
		case 1: star="�ڡ١١١�";
		break;
		case 2: star="�ڡڡ١١�";
		break;
		case 3: star="�ڡڡڡ١�";
		break;
		case 4: star="�ڡڡڡڡ�";
		break;
		case 5: star="�ڡڡڡڡ�";
		break;
		}
		
		return star;
	}

	public String PricetoWon(int price) {
		String won = null;
		
		switch (price) {
		case 1: won = "��";
		break;
		case 2: won = "�ܣ�";
		break;
		case 3: won = "�ܣܣ�";
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





}

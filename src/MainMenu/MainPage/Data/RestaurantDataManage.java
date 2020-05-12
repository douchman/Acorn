package MainMenu.MainPage.Data;

import java.util.List;

import MainMenu.MainPage.Restaurant;

public interface RestaurantDataManage {
	public List<Restaurant> getRestaurant();
	public List<Restaurant> getRestaurant(int m);
	public List<Restaurant> getRestaurant(String m);
}

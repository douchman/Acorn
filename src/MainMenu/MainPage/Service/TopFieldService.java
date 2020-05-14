package MainMenu.MainPage.Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public interface TopFieldService {
	public void WindowClose(MouseEvent event);
	public String Tsearch(ActionEvent ae);
	public void wReview(Parent root);
	// 임시로 만든 메서드
	// 로그인 했을때 로그인된 아이디 표시만할 수 있도록 버튼을 바꿔주는 임시 메서드
	// 추가 수정 해야함 -> 로그아웃 ?
	public void setLoginBtn(String str, Parent root);
}

package MainMenu.MainPage.Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public interface TopFieldService {
	public void WindowClose(MouseEvent event);
	public String Tsearch(ActionEvent ae);
	public void wReview(Parent root);
	// �ӽ÷� ���� �޼���
	// �α��� ������ �α��ε� ���̵� ǥ�ø��� �� �ֵ��� ��ư�� �ٲ��ִ� �ӽ� �޼���
	// �߰� ���� �ؾ��� -> �α׾ƿ� ?
	public void setLoginBtn(String str, Parent root);
}

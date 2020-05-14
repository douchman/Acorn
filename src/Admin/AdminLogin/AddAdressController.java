package Admin.AdminLogin;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import Admin.Data.BobMembershipManageImpl;
import Admin.Data.IBobMembershipManage;
import Admin.Service.CommonService;
import Admin.Service.CommonServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class AddAdressController extends Controller implements Initializable{
	final int name_id = 0;
	final int loca_x = 1;	
	final int loca_y = 2;
	final int address = 3;
	
	private Parent root;
	private CommonService comServ;
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comServ = new CommonServiceImpl();
	}
	public void CancelProc(ActionEvent event) {
		comServ.WindowClose(event);
	}
	
	
	public void AddAdressProc() {
		String []txtFldIdArr = {"#idTxt", "#XTxt", "#YTxt", "#AddressTxt"};
		Map<String, TextField> txtFldMap = 
				comServ.getTextFieldInfo(root, txtFldIdArr);
		
		if(!isCheck(txtFldMap, txtFldIdArr))
			return;
		
		AddAddress member1 = new AddAddress();
		
		member1.setName_id(txtFldMap.get(txtFldIdArr[name_id]).getText());
		member1.setLoca_x(txtFldMap.get(txtFldIdArr[loca_x]).getText());
		member1.setLoca_y(txtFldMap.get(txtFldIdArr[loca_y]).getText());
		member1.setAddress(txtFldMap.get(txtFldIdArr[address]).getText());
		
		
		
		if(BobAddAdressProc(member1))
			comServ.ErrorMsg("주소등록", "성공", "가입을 축하드립니다.");
		else
			comServ.ErrorMsg("주소등록", "실패", "관리자에게 문의하세요.");
		
	}
	
	
	private boolean isCheck(Map<String, TextField> txtFldMap, String []txtFldIdArr) {
		if(comServ.isEmpty(txtFldMap, txtFldIdArr)) {
			System.out.println("비어 있어요");
			return false;
		}
		return true;
	}
	public boolean BobAddAdressProc(AddAddress member1) {
		IBobMembershipManage membershipManage1 = new BobMembershipManageImpl();
		return membershipManage1.BobAddAddressProc(member1);
	}
}















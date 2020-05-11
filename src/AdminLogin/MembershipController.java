package AdminLogin;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import AdminLogin.Controller;
import AdminLogin.Controller2;
import AdminLogin.Member;
import Admin.Data.BobMembershipManageImpl;
import Admin.Data.IBobMembershipManage;
import Admin.Service.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class MembershipController extends Controller implements Initializable{
	final int NUM = 0;
	final int NAME = 1;
	final int IMAGE = 2;	
	final int PHONE = 3;
	final int ADDRESS = 4;
	final int LOCATE = 5;
	final int MAIN = 6;
	final int CATEGORY = 7;
	final int DATE = 8;
	
	
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
	
	
	public void MembershipProc() {
		String []txtFldIdArr = {"#BobNumTxt", "#BobNameTxt", "#BobImageBtn", "#BobPhoneTxt","#BobAddressTxt","#BobLocateTxt","#BobMainTxt","#CategoryTxt","#DateTxt"};
		Map<String, TextField> txtFldMap = 
				comServ.getTextFieldInfo(root, txtFldIdArr);
		
		if(!isCheck(txtFldMap, txtFldIdArr))
			return;
		
		Member member = new Member();
		member.setBobNum(txtFldMap.get(txtFldIdArr[NUM]).getText());
		member.setBobName(txtFldMap.get(txtFldIdArr[NAME]).getText());
		member.setBobImage(txtFldMap.get(txtFldIdArr[IMAGE]).getText());
		member.setBobPhone(txtFldMap.get(txtFldIdArr[PHONE]).getText());
		member.setBobAddress(txtFldMap.get(txtFldIdArr[ADDRESS]).getText());
		member.setBobLocate(txtFldMap.get(txtFldIdArr[LOCATE]).getText());
		member.setBobMain(txtFldMap.get(txtFldIdArr[MAIN]).getText());
		member.setCategory(txtFldMap.get(txtFldIdArr[CATEGORY]).getText());
		member.setDate(txtFldMap.get(txtFldIdArr[DATE]).getText());
		
		
		
		if(BobMembershipProc(member))
			comServ.ErrorMsg("식당가입", "성공", "가입을 축하드립니다.");
		else
			comServ.ErrorMsg("식당가입", "실패", "관리자에게 문의하세요.");
	}
	private boolean isCheck(Map<String, TextField> txtFldMap, String []txtFldIdArr) {
		if(comServ.isEmpty(txtFldMap, txtFldIdArr)) {
			System.out.println("비어 있어요");
			return false;
		}
		return true;
	}
	public boolean BobMembershipProc(Member member) {
		IBobMembershipManage membershipManage = new BobMembershipManageImpl();
		return membershipManage.BobMembershipProc(member);
	}
}














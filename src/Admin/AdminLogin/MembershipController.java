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

public class MembershipController extends Controller implements Initializable{
	final int name = 0;
	final int name_id = 1;
	final int category_id = 2;	
	final int price = 3;
	final int businesshour = 4;
	final int breaktime = 5;
	final int image = 6;
	
	
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
		String []txtFldIdArr = {"#NameTxt", "#IdTxt", "#CategoryTxt", "#PriceTxt","#HourTxt","#BreakTxt","#ImageTxt"};
		Map<String, TextField> txtFldMap = 
				comServ.getTextFieldInfo(root, txtFldIdArr);
		
		if(!isCheck(txtFldMap, txtFldIdArr))
			return;
		
		RestMember member = new RestMember();
		member.setName(txtFldMap.get(txtFldIdArr[name]).getText());
		member.setName_id(txtFldMap.get(txtFldIdArr[name_id]).getText());
		member.setCategory_id(txtFldMap.get(txtFldIdArr[category_id]).getText());
		member.setPrice(txtFldMap.get(txtFldIdArr[price]).getText());
		member.setBusinesshour(txtFldMap.get(txtFldIdArr[businesshour]).getText());
		member.setBreaktime(txtFldMap.get(txtFldIdArr[breaktime]).getText());
		member.setImage(txtFldMap.get(txtFldIdArr[image]).getText());
		
		
		
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
	public boolean BobMembershipProc(RestMember member) {
		IBobMembershipManage membershipManage = new BobMembershipManageImpl();
		return membershipManage.BobMembershipProc(member);
	}
}














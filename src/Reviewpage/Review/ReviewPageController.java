package Reviewpage.Review;

import java.net.URL;
import java.util.ResourceBundle;

import Reviewpage.Service.CommonService;
import Reviewpage.Service.CommonServiceImpl;
import Reviewpage.Service.InformationService;
import Reviewpage.Service.InformationServiceImpl;
import Reviewpage.Service.TabService;
import Reviewpage.Service.TabServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
 * 
 * 리뷰페이지 전체에서 동작하는 요청을 처리하는 컨트롤러
 * 
 * 
 * 
 * */
public class ReviewPageController extends Controller implements Initializable{
	final String SHOPID = "1";
	final String USERID = "aa1@mail.com";
	private Parent root, testRoot;
	Stage stage;
	InformationService inforserv;	//메인 음식점 타이틀 서비스
	CommonService comserv;
	TabService tabserv;				//탭 서비스	
	boolean mark;
	
	// 박상현 추가 및 수정
	private String shopID, userID;
	
	@Override
	public void setRoot(Parent root) {
		// 지금 이부분도 상속은 받아놓고 쓰질않는것 같아요?
		// 공통 서비스에서도 보면 root를 설정해주는게 있던데
		// 제가 확인이 덜 되어서 확실하지않습니다.
		this.root = root;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inforserv = new InformationServiceImpl();
		comserv = new CommonServiceImpl();
		tabserv = new TabServiceImpl();
		// 일단 초기화 메서드에서 탭서비스  등등
		// 리뷰 페이지 구성하는데 있어서 필요한 서비스를 몽땅 할당함
		mark = false;
	}
	
	
	// 박상현 추가
	public void TmporaryTestPageSetRoot(Parent testRoot) {
		// 스타트페이지를 없애버리고 이쪽으로 연결시킨 것 이기때문에 임시적으로 
		// 테스트용  임시루트 설정을 위한 메서드 입니다.
		// 테스트 루트는 클래스 최상단 인스턴스로 선언했습니다.
		this.testRoot = testRoot;
		
		// 컨트롤러를 따로 잡지 않았기때문에 버튼 직접 찾아서 이벤트 설정했습니다.
		Button goBtn = (Button)testRoot.lookup("#GoPage");
		
		
		goBtn.setOnAction(e->{
			TemporaryRunMethod(e);
		});
	}
	
	public void TemporaryRunMethod(ActionEvent e) {
		/*
		 불필요한 스타트페이지 컨트롤러를 없애버리고
		 스타트페이지구성을 위한 startpage.fxml에서 설정하는 컨트롤러를 지웠습니다.
		 컨트롤러 지운이유는 fxml은 사용은해야하는데 따로 컨트롤러를 두지않고 여기 컨트롤러에 
		 테스트 메서드를 만들어서 동작을 확인 할 것 이라서 이쪽으로 동작하도록 수정했습니다.
		 
		 사실상 스타트페이지 컨트롤러 같은것 자체가 필요가없을뿐더러 
		 스타트페이지 컨트롤러를 굳이 거쳐오게되면서 해당 컨트롤러에서 억지로 뷰 구성을위한
		 필수데이터를 받아버리니까 오히려 더 꼬였습니다. 굉장히 단순한 문제였고 구조적으로도 큰 문제는 없었는데
		 테스트용으로 동작을 확인하려다보니까 생각이 많아 지신것 같네요.
		 
		 정리를 하자면.. 
		 - 아주 임시적인 부분(메인페이지) 을 너무 크게 신경쓰신 것 같습니다.
		 - 따지고보면 리뷰페이지 자체를 구성하는 가장 메인인 컨트롤러는 지금 이 ReviewPageController 겠죠?
		 - 결국 리뷰페이지 구성을 위한 데이터는 단 두개 1. 유저고유ID_num 2. 식당 고유ID_num 입니다.
		 - 위 두가지 데이터를 이용하여 사실상 실체인 이 컨트롤러에서 처리를 해주는게 맞는 것 같아요.
		 - 따라서 위처럼 임시 실행 메서드를 만들어서 이 메서드에서, 
		 - 초기화면의 Textfield 에서 받은 값과 GoMain 버튼을 눌렀을때 이벤트를 처리하겠습니다.
		 - 텍스트 필드에서는 정상 동작의 상황을 가정하여 페이지 구성에 필요한 정보인 userID 와 resID 를 받고 동작시킵니다.
		 - 추가 코멘트로, 하단의 모든 페이지 구성을 위한 메서드에서 기존에 참조했던
		 
		 # 인스턴스 2개 #
		 SHOPID = "1";
		 USERID = "1";
		 
		 두 인스턴스는 모두 제가 작성한 임시 인스턴스로 넘겨주고 호출했습니다.
		 
		 
		 마지막으로 writeReviewProc 에서 
		 - 문제였던 userID 와 shopID는 현재 이 리뷰 페이지컨트롤러에서 호출이 되기때문에
		 - 넘겨받았던 uesrID와 shopID를 넘겨주도록 수정했습니다
		 
		 따라서 writeReviewProc 메서드안의 탭서비스쪽 참고해보시면 넘겨받은 값으로 동작하도록 수정했습니다.
		 
		 *** 추가 ***
		 - 안될 줄 알았던 새로고침이 어느정도 구현이 됩니다.
		 - 최하단에 refresh() 메서드를 새로 만들었는데요.
		 - 씬은 이미 구성되어있으니까 씬에 구성되는 데이터들을 새로 다시 불러와서 만들었어요
		 - 단순하게 그냥 데이터 불러오는 부분을 재호출 했습니다.
		 - 우선 이부분을 동작시키려면 뷰 페이지 구성에 필요한 데이터는 이 컨트롤러에서 처리를 하는것 같아서
		 - 이 컨트롤러에서 새로고침을 해주는게 맞다고(?), 빠르다고 판단했습니다.
		 - 그래서 리뷰쓰기 버튼이 눌렸을때 리뷰작성을 위한 여러 서비스들이 거쳐가는 것 같아서
		 - 최초로 리뷰 작성 버튼이 눌릴경우 this를 이용해서 자기 자신을 객체로 넘기고
		 - 작성이 끝난뒤에 DB에 데이터 저장이끝나면 이 컨트롤러에 만들어둔   refresh() 를 호출하는 방식으로 구현했습니다.
		 
		 근데 수정이랑 삭제 부분은 fxml 로딩 방식이 다른 컨트롤러 서비스랑 다르게 구성되어있어서 그부분은 새로고침 구현 안됍니다.
		 직접 수정 해보셔야할듯!
		 */
		
		// 이 스테이지는 기존에는 startPageController 에서 만들어줬는데 
		// 이 메서드가 결국에는 startPageController 를 대체하는 임시 메서드인 셈이라서
		//  이쪽에서 스테이지를 새로받아 만들었습니다.
		// 나중에 완성본 수정하실때 참고해서 만들어보시면 될 거에요.
		
		// 이 스테이지 안만들어줘서 1시간동안 해멨습니다. 
		// 스테이지를 안만들어서 Tap서비스에서 VBox를 못찾아서 왜그런건가 하고 한~~~참 고민했습니다.
		// 저는 무슨 다른 메서드에 스테이지 띄워주는줄알고 있었네요.
		// 작성하실때 코드정리하면서 주석 달면 아주 좋다고 생각합니다.
		// 코드 구성이 처음엔 복잡하고 제 방식이 아니라서 왜이리 복잡하게 짜셨지 했는데 제 생각이 틀렸네요 . 잘 짜신 것 같습니다.
		// 몇개 배웠어요.
		
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		
		TextField usrID = (TextField)testRoot.lookup("#txtuserID");
		TextField restaurantID = (TextField)testRoot.lookup("#InsertEmailTF");
		
		
		System.out.println("restaurantID : "+restaurantID.getText() + ", userID : " + usrID.getText() );
		this.shopID = restaurantID.getText();
		this.userID = usrID.getText();
		
		stage.show();
		
		// 이 하단 부분에서 기존과 다른건 userID 와 shopID 뿐입니다. 상단 코드 천천히 살펴보시면
		// 금방 알아차리실 거에요.
		inforserv.TopInformation(root, shopID, userID );
		tabserv.TabMenu(root, shopID);
		tabserv.TabReview(root, shopID, userID, this);
		comserv.CloseWindow(e);
		
		// 우선 가장 원초적문제였던  "" 도토리 메인페이지에서 해당 객체의 정보를 얻어오기 "" 문제만을 
		// 해결한것이라 다른 문제가 발생되면 본인이 고치셔야할것같습니다. 저도 할게 많다구욧
		
		/*
		 * ###########      테스트 결과      ############# 
		 *  
		 *  - 임시 페이지에서 유저고유번호와 식당고유번호 입력시 정상 동작
		 *  - 리뷰쓰기 버튼 이벤트 정상 동작
		 *  - 리뷰를 작성하고 등록시 DB에 insert 이벤트 정상 동작
		 *  
		 * #########################################
		 *  
		 */
	}
	
	//탭 클릭시 배경색과 같음
		
	private void ColorTab(Event e) {
		Tab tab = (Tab)e.getTarget();
		if(tab.isSelected()) {
			tab.setStyle("-fx-background-color: #7FC9B7;");
		}else {
			tab.setStyle("-fx-background-color: #5FAE95;");
		}
	}
	//메뉴 탭 클릭
	public void TabMenu(Event e) {	
		ColorTab(e);
	}
	//리뷰 탭 클릭
	public void TabReview(Event e) {
		ColorTab(e);
	}
	//공지사항 탭 클릭
	public void TabNotice(Event e) {
		ColorTab(e);
	}
	//찾아가기 탭 클릭
	public void TabInfo(Event e) {
		ColorTab(e);
	}
	
	//타이틀 이미지 왼버튼
	public void ImgLeftpageProc() {
		System.out.println("왼쪽 이미지로 교체");
	}
	//타이틀 이미지 오른버튼
	public void ImgRightpageProc() {
		System.out.println("오른쪽 이미지로 교체");
	}
	//북마크버튼
	public void BookmarkProc(ActionEvent e) {
		inforserv.BookmarkServ(root, shopID, userID, false);
	}
	//링크버튼
	public void LinkProc() {
		inforserv.LinkServ(stage, root, shopID);
	}
	//탭 서비스
	 //탭 페이지 - 메뉴
	
	 //탭 페이지 - 리뷰
	  //리뷰 쓰기
	public void WriteReviewProc() {
		tabserv.WriteReviewServ(stage, root, userID, shopID, this);
		//root.setVisible(true);
	}
	 //탭 페이지 - 공지
	 //탭 페이지 - 찾아가기
	
	// 박상현 수정
	public void refresh() {
		inforserv.TopInformation(root, shopID, userID );
		tabserv.TabMenu(root, shopID);
		tabserv.TabReview(root, shopID, userID, this);
	}

}

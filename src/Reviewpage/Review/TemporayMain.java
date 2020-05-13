package Reviewpage.Review;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*
 * 
 * 테스팅용 임시 메인 메서드입니다.
 * 
 * 
 * */
public class TemporayMain extends Application{


	///////////// 박상현 추가 내용 ///////////////
	FXMLLoader temporaryLoader, loader;    //
	ReviewPageController reviewCon;        //
	Parent temporaryRoot,root; 			   //
	/////////////////////////////////////////
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		
		// 메인 메서드에서 테스팅으로 쓸 root 와 실제로 리뷰 페이지 구성을 위한 root 두개를 각각 다른 fxml에서 받아옵니다.
		// 테스팅용 루트는 근희씨 페이지에서 객체 선택 이벤트로  넘어오는 1. userID 와 2. restaurantID 두개가 넘어온다고 가정을하면
		// 두가지 값을 받아야 하니까 이런 맥락에서 입력받게 했습니다.
		
		temporaryLoader  = new FXMLLoader(getClass().getResource("/ReviewPackage/StartPage.fxml"));
		// 임시 로더 -> 임시 루트
		loader = new FXMLLoader(getClass().getResource("/ReviewPackage/ReviewPage.fxml"));
		// 메인 로더 -> 리뷰 페이지 루트
		
		// 기 언급했듯 서로 다르게 로딩한 로더에서 루트를 받아옵니다.
		temporaryRoot = temporaryLoader.load();
		root = loader.load();
		
		// 컨트롤러는 실제 리뷰페이지 구성을 위한 로더에서 컨트롤러를 get 합니다.
		reviewCon = loader.getController();
		
		
		// 임시적인 부분이므로  공통서비스를 이용한 페이지 오픈을 제거 하고
		// 임시로 동작할 임시 메인페이지이므로 임시root를 임시메서드를 이용해 할당합니다.
		// 이해가 안되실까봐 추가 코멘트를 더 드리자면
		// 스타트 컨트롤러의 필요가 지금 전혀 없기때문에
		// 두개의 fxml -> startPage 와 reviewPage 은 하나의 컨트롤러( reviewPageController )를 이용하여
		// 동작을 처리 했습니다. 일반적으로 하나의 fxml 에 하나의 컨트롤러를 쓰겠지만 임시동작을위해
		// reviewPageController 에 
		// 1. 임시 루트 설정 메서드
		// 2. 임시로 동작을 확인할 메서드
		// 두 가지를 추가 했습니다.
		
		// 앞서 말한 임시 메서드를 이용해서 임시루트를 할당해주고요
		reviewCon.TmporaryTestPageSetRoot(temporaryRoot);
		reviewCon.setRoot(root);
		
		// 가장 먼저 show하는 스테이지는 근희씨 메인페이지에서 넘어오는 값을 받아오는것처럼 만들기위한 가정용 스테이지입니다.
		primaryStage.setScene(new Scene(temporaryRoot));
		
		// 첫페이지는 리뷰 페이지가 아니니까 임시설정 루트를 show() 합니다.
		primaryStage.show();
		
		// 이제 리뷰페이지컨트롤러로 넘어가보시죠
		
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
}

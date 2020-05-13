package Reviewpage.Review;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*
 * 
 * �׽��ÿ� �ӽ� ���� �޼����Դϴ�.
 * 
 * 
 * */
public class TemporayMain extends Application{


	///////////// �ڻ��� �߰� ���� ///////////////
	FXMLLoader temporaryLoader, loader;    //
	ReviewPageController reviewCon;        //
	Parent temporaryRoot,root; 			   //
	/////////////////////////////////////////
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		
		// ���� �޼��忡�� �׽������� �� root �� ������ ���� ������ ������ ���� root �ΰ��� ���� �ٸ� fxml���� �޾ƿɴϴ�.
		// �׽��ÿ� ��Ʈ�� ���� ���������� ��ü ���� �̺�Ʈ��  �Ѿ���� 1. userID �� 2. restaurantID �ΰ��� �Ѿ�´ٰ� �������ϸ�
		// �ΰ��� ���� �޾ƾ� �ϴϱ� �̷� �ƶ����� �Է¹ް� �߽��ϴ�.
		
		temporaryLoader  = new FXMLLoader(getClass().getResource("/ReviewPackage/StartPage.fxml"));
		// �ӽ� �δ� -> �ӽ� ��Ʈ
		loader = new FXMLLoader(getClass().getResource("/ReviewPackage/ReviewPage.fxml"));
		// ���� �δ� -> ���� ������ ��Ʈ
		
		// �� ����ߵ� ���� �ٸ��� �ε��� �δ����� ��Ʈ�� �޾ƿɴϴ�.
		temporaryRoot = temporaryLoader.load();
		root = loader.load();
		
		// ��Ʈ�ѷ��� ���� ���������� ������ ���� �δ����� ��Ʈ�ѷ��� get �մϴ�.
		reviewCon = loader.getController();
		
		
		// �ӽ����� �κ��̹Ƿ�  ���뼭�񽺸� �̿��� ������ ������ ���� �ϰ�
		// �ӽ÷� ������ �ӽ� �����������̹Ƿ� �ӽ�root�� �ӽø޼��带 �̿��� �Ҵ��մϴ�.
		// ���ذ� �ȵǽǱ�� �߰� �ڸ�Ʈ�� �� �帮�ڸ�
		// ��ŸƮ ��Ʈ�ѷ��� �ʿ䰡 ���� ���� ���⶧����
		// �ΰ��� fxml -> startPage �� reviewPage �� �ϳ��� ��Ʈ�ѷ�( reviewPageController )�� �̿��Ͽ�
		// ������ ó�� �߽��ϴ�. �Ϲ������� �ϳ��� fxml �� �ϳ��� ��Ʈ�ѷ��� �������� �ӽõ���������
		// reviewPageController �� 
		// 1. �ӽ� ��Ʈ ���� �޼���
		// 2. �ӽ÷� ������ Ȯ���� �޼���
		// �� ������ �߰� �߽��ϴ�.
		
		// �ռ� ���� �ӽ� �޼��带 �̿��ؼ� �ӽ÷�Ʈ�� �Ҵ����ְ��
		reviewCon.TmporaryTestPageSetRoot(temporaryRoot);
		reviewCon.setRoot(root);
		
		// ���� ���� show�ϴ� ���������� ���� �������������� �Ѿ���� ���� �޾ƿ��°�ó�� ��������� ������ ���������Դϴ�.
		primaryStage.setScene(new Scene(temporaryRoot));
		
		// ù�������� ���� �������� �ƴϴϱ� �ӽü��� ��Ʈ�� show() �մϴ�.
		primaryStage.show();
		
		// ���� ������������Ʈ�ѷ��� �Ѿ������
		
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
}

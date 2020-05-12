package ReviewPackage;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

	List<Review> list_review;
	
	int viewCnt;
	
	String resName, resKind, 
		   resAdress, resIntro, 
	       resNotice, resNotice2;
	
	
	public Restaurant(String resName,String resKind, 
			String resAdress,String resIntro, 
			String resNotice,String resNotice2, int viewCnt) {
		
		list_review = new ArrayList<Review>();
		
		this.resName = resName;
		this.resKind = resKind;
		this.resAdress = resAdress;
		this.resIntro = resIntro;
		this.resNotice = resNotice;
		this.resNotice2 = resNotice2;
		
	}
	
	public List<Review> getList_review() {
		return list_review;
	}

	public void setList_review(List<Review> list_review) {
		this.list_review = list_review;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResKind() {
		return resKind;
	}

	public void setResKind(String resKind) {
		this.resKind = resKind;
	}

	public String getResAdress() {
		return resAdress;
	}

	public void setResAdress(String resAdress) {
		this.resAdress = resAdress;
	}

	public String getResIntro() {
		return resIntro;
	}

	public void setResIntro(String resIntro) {
		this.resIntro = resIntro;
	}

	public String getResNotice() {
		return resNotice;
	}

	public void setResNotice(String resNotice) {
		this.resNotice = resNotice;
	}

	public String getResNotice2() {
		return resNotice2;
	}

	public void setResNotice2(String resNotice2) {
		this.resNotice2 = resNotice2;
	}

	
}

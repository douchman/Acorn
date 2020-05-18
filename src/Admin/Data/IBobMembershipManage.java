package Admin.Data;

import Admin.AdminLogin.AddAddress;
import Admin.AdminLogin.Member;

public interface IBobMembershipManage {
	//회원 가입 시 개인 정보를 DB에 저장
	public boolean BobMembershipProc(Member member);
	//ID와 PW로 개인 인증
	public boolean BobAddAddressProc(AddAddress member1);
	public int DelProc(String BobNum);
}

package Admin.Data;

import Admin.AdminLogin.AddAddress;
import Admin.AdminLogin.Member;

public interface IBobMembershipManage {
	//ȸ�� ���� �� ���� ������ DB�� ����
	public boolean BobMembershipProc(Member member);
	//ID�� PW�� ���� ����
	public boolean BobAddAddressProc(AddAddress member1);
	public int DelProc(String BobNum);
}

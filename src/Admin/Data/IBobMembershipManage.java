package Admin.Data;

import Admin.AdminLogin.AddAddress;
import Admin.AdminLogin.RestMember;

public interface IBobMembershipManage {
	//ȸ�� ���� �� ���� ������ DB�� ����
	public boolean BobMembershipProc(RestMember member);
	//ID�� PW�� ���� ����
	public boolean BobAddAddressProc(AddAddress member1);
	public int DelProc(RestMember member);
}

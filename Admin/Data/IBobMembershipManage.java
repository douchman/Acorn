package Admin.Data;


import Admin.AdminLogin.AddAddress;
import Admin.AdminLogin.RestMember;

public interface IBobMembershipManage {
	public boolean BobMembershipProc(RestMember member);
	public boolean BobAddAddressProc(AddAddress member1);
	public int DelProc(String name_id);
}

package cn.zc.entity;

/**
 * 前端传来的数据进行封装
 * @author zc-cirs
 *
 */
public class QueryCriteria {

	private String name;
	private String address;
	private String phone;
	
	public QueryCriteria() {
	}
	public QueryCriteria(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	public String getName() {
		return name == null ? "%%" :"%"+name+"%" ;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address == null ? "%%" :"%"+address+"%" ;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone == null ? "%%" :"%"+phone+"%" ;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "QueryCriteria [getName()=" + getName() + ", getAddress()=" + getAddress() + ", getPhone()=" + getPhone()
				+ "]";
	}
}

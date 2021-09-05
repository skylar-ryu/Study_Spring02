package vo;

public class UserVO {
	
	private String id;
	private String name;
	private String lev;
	private String sctime; //session Creation Time
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	public String getSctime() {
		return sctime;
	}
	public void setSctime(String sctime) {
		this.sctime = sctime;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", lev=" + lev + ", sctime=" + sctime + "]";
	}
		
}//class

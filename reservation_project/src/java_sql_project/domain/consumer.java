package java_sql_project.domain;

public class consumer {
	public String id;
	public String name;
	public String pw;
	public String email;
	public String location_state;
	public String location_city;
	
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocation_state() {
		return location_state;
	}
	public void setLocation_state(String location_state) {
		this.location_state = location_state;
	}
	public String getLocation_city() {
		return location_city;
	}
	public void setLocation_city(String location_city) {
		this.location_city = location_city;
	}
	
}
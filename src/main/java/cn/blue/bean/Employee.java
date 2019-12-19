package cn.blue.bean;
/**
 * 
 * 
 * @author JAVA
 *
 */
public class Employee {
	private Integer id;
	private String last_Name;
	private String email;
	private String gender;
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getLast_Name() {
		return last_Name;
	}



	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName111=" + last_Name + ", email=" + email + ", gender=" + gender + "]";
	}
}

package cn.blue.bean;
/**
 * 
 * 
 * @author JAVA
 *
 */
public class Employee {
	private Integer id;
	private String lastName;
	private String email;
	private String gender;
	
	
	
	public Employee() {
	}

	public Employee(
			String id, String lastName, String email, String gender) {
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLast_Name() {
		return lastName;
	}

	public void setLast_Name(String last_Name) {
		this.lastName = last_Name;
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
		return "Employee [id=" + id + ", lastName111=" + lastName + ", email=" + email + ", gender=" + gender + "]";
	}
}

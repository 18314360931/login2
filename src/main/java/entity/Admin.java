package entity;

/**
 * ��ǰ�����û�
 * 
 * @author Student
 *
 */
public class Admin {
	private int id;
	private String name;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	private Double age;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

}

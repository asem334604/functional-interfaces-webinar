package application;

public class Student {
	private Integer id;
	
	public Student(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + "]";
	}
}

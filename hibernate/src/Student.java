
public class Student {

	private long id;
	private String name;
	private String surname;
	private String pesel;

	public Student(long id, String name, String surname, String pesel) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
	}

	public Student(String name, String surname, String pesel) {
		super();
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
	}

	public Student() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + ", surname=" + surname + ", pesel=" + pesel + "]";
	}

}

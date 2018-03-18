import java.util.Set;

@SuppressWarnings("serial")
public class School implements java.io.Serializable {

	private long id;
	private String name;
	private String address;
	private Set<SchoolClass> classes;

	public School() {
	}

	public School(long id, String name, String address, Set<SchoolClass> classes) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.classes = classes;
	}

	public School(String name, String address, Set<SchoolClass> classes) {
		super();
		this.name = name;
		this.address = address;
		this.classes = classes;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<SchoolClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<SchoolClass> classes) {
		this.classes = classes;
	}

	public String toString() {
		return "School: " + getName() + " (" + getAddress() + ")";
	}

}

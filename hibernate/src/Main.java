import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		main.addNewData();
		main.printSchools();
		main.close();
	}

	public void addNewData() {
		// Transaction transaction = session.beginTransaction();
		// Student student1 = new Student(8, "Szymon", "Gasior", "111111");
		// session.save(student1);
		// transaction.commit();

		Set<Student> listOfStudents = new HashSet<Student>();
		listOfStudents.add(new Student("Jozek1", "Gasior", "111111"));
		listOfStudents.add(new Student("Jozek2", "Klosko", "111111"));
		listOfStudents.add(new Student("Jozek3", "Jahn", "111111"));

		SchoolClass schoolClass = new SchoolClass(2017, 2018, "eti", listOfStudents);
		Set<SchoolClass> listOfClasses = new HashSet<SchoolClass>();
		listOfClasses.add(schoolClass);

		School newSchool = new School("UEK", "ulica ueku", listOfClasses);
		session.saveOrUpdate(newSchool); // jak samo session.save(newSchool) to
											// nie mozna nadpisywac tych samych
											// elementów
		Transaction transaction = session.beginTransaction();
		transaction.commit();
	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}

	private void printSchools() {
		Criteria crit = session.createCriteria(School.class);
		List<School> schools = crit.list();

		System.out.println("### Schools and classes");
		for (School s : schools) {
			System.out.println(s);
			System.out.println("    Klasy: ");
			for (SchoolClass schoolClass : s.getClasses()) {
				System.out.println("    " + schoolClass);
				System.out.println("        Studenci: ");
				for (Student student : schoolClass.getStudents()) {
					System.out.println("                  " + student);
				}
			}
		}

		// Criteria crit = session.createCriteria(SchoolClass.class);
		// List<SchoolClass> schools = crit.list();
		//
		// System.out.println("### Schools and classes");
		// for (SchoolClass s : schools) {
		// System.out.println(s);
		// }

	}

	private void jdbcTest() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("org.sqlite.JDBC");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlite:school.db", "", "");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM schools";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("name");
				String address = rs.getString("address");

				// Display values
				System.out.println("Name: " + name);
				System.out.println(" address: " + address);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end jdbcTest

}

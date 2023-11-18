package Stream;

public class Employee {
	
	    private String firstName;
	    private String lastName;
	    private double salary;
	    private String department;

	    // constructor
	    public Employee(String firstName, String lastName, double salary, String department) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.salary = salary;
	        this.department = department;
	    }
	    // set firstName
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    public String getFirstName() {
	        return firstName;
	    }
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    // get lastName
	    public String getLastName() {

	        return lastName;
	    }
	    public void setSalary(double salary) {
	        this.salary = salary;
	    }
	    public double getSalary() {
	        return salary;
	    }
	    public void setDepartment(String department) {
	        this.department = department;
	    }
	    public String getDepartment() {
	        return department;
	    }
	    // return Employee's first and last name combined
	    public String getName() {
	        return String.format("%s %s", getFirstName(), getLastName());
	    }
	    // return a String containing the Employee's information
	    @Override
	    public String toString() {
	        return String.format("%-8s %-8s %8.2f %-8s",
	                getFirstName(), getLastName(), getSalary(), getDepartment());
	    } // end method toString

	}




package Stream;

import java.nio.file.DirectoryStream.Filter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ProcessingEmployees {
	public static void main(String[] args) {
		 Employee[] employees = {
	                new Employee("Jason", "Red", 5000, "IT"),
	                new Employee("Ashley", "Green", 7600, "IT"),
	                new Employee("Matthew", "Indigo", 3587.5, "Sales"),
	                new Employee("Jason",   "Brown", 3200, "Sales"),
	                new Employee("James", "Indigo", 4700.77, "Marketing"),
	                new Employee("Luke", "Indigo", 6200, "IT"),
	                new Employee("Wendy", "Blue", 4236.4, "Marketing")};
		 
		// 2.1  Count the number of last names that begin with the letter  ‘B’.  Print out this number.
		 List<Employee> list = Arrays.asList(employees);
		 System.out.printf("%nNumber of Employees with Last Names that Begin with B: %d%n",
				 list.stream()
				 .filter(l->l.getLastName().startsWith("B"))
				 .count());
	
		 //2.2 Print out all of the Employee objects whose last name begins with the letter  ‘B’  in sorted order. 
		 System.out.println("\n==========2.2==========");
		 System.out.println("Employees last name starting with B in sorted order");
				 list.stream()
				 .filter(em-> em.getLastName().startsWith("B"))
				 .sorted(Comparator.comparing(Employee::getLastName))
				 .forEach(System.out::println);
		
				 
		//2.3 Print out all of the Employee objects whose last name begins with the letter  ‘B’  
	    //and change their first name and last name to be All capital letters.  
				 
				 System.out.println("\n==========2.3==========");
				 System.out.println("Employess whose last name begins with B to upper case:");
			        /*list.stream()
			                .filter(e->e.getLastName().startsWith("B"))
			                .map(e->new Employee(e.getFirstName().toUpperCase(),e.getLastName().toUpperCase(),e.getSalary(),e.getDepartment()))
			                .forEach(System.out::println);*/ //both works
			                list.stream()
			                .filter(w->w.getLastName().startsWith("B"))
			                .map(x->x.getFirstName().toUpperCase() + x.getLastName().toUpperCase()+ " "+x.getSalary()+" "+x.getDepartment()+"\n")
			                .forEach(System.out::println);
			              
			                /*list.stream() @Ek2
			                .filter(w->w.getLastName().startsWith("B"))
			                .map(x->x.getName().toUpperCase()+" "+x.getSalary()+" "+x.getDepartment()+"\n")
			                .forEach(System.out::println);*/
                    
        
        //2.4. Print out All of the employee objects, but if the last name begins with the letter ‘B’,  
        //then capitalize all the letters in the last name.
			        System.out.println("\n==========2.4.==========");
        
       System.out.println("\nPrint all Employees and change to upper case if the last name begins with B: ");
//        list.stream()
//        .map(e -> e.getLastName().startsWith("B") ? new Employee(e.getFirstName(),
//        		e.getLastName().toUpperCase(), e.getSalary(), e.getDepartment()):e)
//        .forEach(System.out::println);
       list.stream()	    	  
	     .map(x->{
	    	 if(x.getLastName().startsWith("B"))  
	    	 return x.getFirstName() + " " + x.getLastName().toUpperCase()+" "+x.getSalary()+" "+x.getDepartment();
		     return x.toString();})
	    // .sorted()
	     .forEach(System.out::println);
       
       System.out.println("__________________");
       System.out.println(list);//to check original
        
        //2.4.1)  Use the  Collectors.joining  method to print out All Employee objects. 
        System.out.println("\n==========2.4.1.==========");
      
        System.out.println("\nPrinting all Employee objects using the Collectors joining method \n"+
        		list.stream(). map(Employee::toString). collect(Collectors.joining()));
       
     
       
       //2.4.2 Use the  Collectors.joining  method to print out All Employee objects, and separat each 
       //one with a delimeter of  “---\n---“.  
        System.out.println("\n==========2.4.2.==========");
       
       System.out.println("\nPrinting all Employee objects and Separating them with delimeter \n" +
       list.stream().map(Employee::toString).collect(Collectors.joining("---\n---")));
       
//    2.5)  Print out all of the Employee objects’ last names, whose last name begins with the 
       //letter  ‘I’  in sorted order, and get rid of all the duplicates.  Print out only the last names.  
       System.out.println("\nEmployees with Last Names that Begin with I and removing duplicates: ");
				 list.stream()
				 .filter(e->e.getLastName().startsWith("I"))
				 .map(Employee::getLastName)
				 .distinct()
				 .sorted()
				 .forEach(System.out::println);
				 
	// 2.6)  Print out the average of all the salaries.
		System.out.printf("\nThe average of all the salaries: %.3f",
			list.stream()
				.mapToDouble(Employee::getSalary)
				.average()
				.getAsDouble());
		
		//2.7.  Use the  ‘reduce’  method to print out the total salary of all employees. 
		
		double xx =  list.stream()
                .mapToDouble(e->e.getSalary())
                .reduce(0, (x,y)->x+y);
		
		 System.out.printf("\nSum of Employees' salaries (via reduce method): %.4f%n",
	                list.stream()
	                .mapToDouble(e->e.getSalary())
	                .reduce(0, (x,y)->x+y));
		 
		 //2.8. Print out only the first names of all the employees.  Use the  ‘map’  method to accomplish this.
		 System.out.println("\n===========2.8===========");
		 System.out.println("\nPrinting the first Name of all employees:");
				 list.stream()
				 .map(e->e.getFirstName())
				 .sorted()
				 .forEach(System.out::println);
	
			//   7.a)  Print out each department and the average salary for the department.	 
     			    
				 System.out.println("\n===========7a===========");
					
				 Map<String, List<Employee>> eachdptAverageSalary = 
							list.stream()
							.collect(Collectors.groupingBy(Employee::getDepartment));

					System.out.println("Average salary of each department \n");
					eachdptAverageSalary.forEach((department, employeeInDepartment) -> {

						System.out.print(department);
						System.out.println(
								" - " + employeeInDepartment.stream()
								.mapToDouble(Employee::getSalary).average().orElse(0) + " ");
					});
					
					System.out.println("");
					System.out.println("\n===========7b===========");
//				    b)  Print out each department and the maximum salary for the department.
					
					Map<String, List<Employee>> dptHighestSalary = list.stream()
							.collect(Collectors.groupingBy(Employee::getDepartment));
					System.out.println("Each department with their maximum salary \n");
					dptHighestSalary.forEach((department, employeeInDepartment) -> {

						System.out.print(department);
						System.out.println(" : " + employeeInDepartment.stream()
						.mapToDouble(Employee::getSalary).max().orElse(0));
					});

					System.out.println("\n===========7c===========");
					//   c)  Print out each department and all of the employees who work at that department
					System.out.printf("%nEmployees by department:%n");
					Map<String, List<Employee>> groupedByDepartment2 = list.stream()
							.collect(Collectors.groupingBy(Employee::getDepartment));

					groupedByDepartment2.forEach((department, employeesInDepartment) -> {
						System.out.println(department + " ");
						//employeesInDepartment.forEach(employee -> System.out.printf("   %s%n", employee));
						employeesInDepartment.stream().map(Employee::toString).forEach(System.out::println);
					});
					System.out.println("");
				 
				  
	}
	
 

}

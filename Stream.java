package lab8Stream;

import java.lang.foreign.Addressable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamQ3 {
	public static void main(String[] args) {
		 
//2.9)  Create an infinite stream of even numbers (0, 2, 4, …) and 
// then, eventually print out only the first 20 even numbers from this stream.  
	 
		System.out.printf("%nFirst 20 even numbers:%n"); 
	  IntStream.iterate(0, e -> e + 2)
       .limit(20)
       .forEach(System.out::println);
		
	  //Using the supplier method
	  System.out.println("\nGenerating infinite even numbers using the Supplier interface:");
	  Supplier<Integer> evenNumber = new Supplier<Integer>() {
	      int current = 0;

	      @Override
	      public Integer get() {
	        int result = current;
	        current += 2;
	        return result;
	      }
	    };

	    Stream<Integer> even = Stream.generate(evenNumber);
	    even.limit(20).forEach(System.out::println);
	  
	

	 
	  /**3)a)  Implement  a method with the following signature and return type:
	   public int countWords(List<String> words, char c, char d, int len)   
	  which counts the number of words in the input list words that have length equal to len, 
	  that contain the character c, and that do not contain the character d.  Create a solution 
	  that uses a lambda expression.  */

	  List<String> strStream=Arrays.asList("\nworld","concatnate","cords","crest","score","adobe","tree","arc","stack","according");
	  
	  System.out.print("The count of Creating a word of length len that contanis 'c' and not 'd': ");
		System.out.print(countWords(strStream, 'c', 'd', 5));
		
		
		//*3.b)  Use reduce to concatenate the Strings in the Stream below to form a single, space-separated String. 
		
		Stream<String> strings = Stream.of("A", "good", "day", "to", "write", "some", "Java");
		String result=strings.reduce("", (a, b) -> a + " " + b);
		System.out.println("\nConcatinating Strings using the reduce method: " + result);
		

		  //3.c) Create programs to use  Java standard methods and make sure the results are correct! 
		      //(test findfirst, findany, the Optional object (use generics here), orElse inside an Optional, and others …
		 
		Optional<String> startsWith
		  = strStream.stream().filter(s ->s.startsWith("a")).findFirst();
		  
		  if(startsWith.isPresent()){
			  
			  System.out.println("\nFirst Word starting with 'a': " + startsWith.get()); 
		  }
		  
		  startsWith
		  =strStream.stream().filter(s ->s.startsWith("x")).findAny();
		  System.out.println("\nWord starts with x(any): "+startsWith.orElse("Not Found "));
		
		/*  
		 4. Use DoubleSummaryStatistics to output to the console the top test score, 
		 lowest test score, and average among all test scores in a given list.  */
		  
		 DoubleSummaryStatistics scores = new DoubleSummaryStatistics();

		  List<ExamData>data=new ArrayList<ExamData>(){
			       
			  {       add(new ExamData("George",91.3));
					  add(new ExamData("Tom",88.9));
					  add(new ExamData("Rick",80));
					  add(new ExamData("Harold",90.8));
					  add(new ExamData("Ignatius",60.9));
					  add(new ExamData("Anna",77));
					  add(new ExamData("Susan",87.3));
					  add(new ExamData("Phil",99.1));
					  add(new ExamData("Alex",84));
				  
		  }
		
	};
	data.stream()
	.forEach(e -> scores.accept(e.getTestScore()));
	
	
	System.out.println("\n\nTop Test Score: "+ scores.getMax()+
			"\nLowest Test Score: "+ scores.getMin()+"\nAverage Test Score: "+ scores.getAverage()+ 
			"\nTotal score of all students: " + scores.getSum());
	
	}
	         
	//3.a)         

	public static int countWords(List<String> words, char c, char d, int len){
		
		 int counts =(int) words.stream()
	       		   .filter(x->x.contains(""+c) && !(x.contains(""+d))&& x.length()==len)
	       		   .count();
	          return counts;
//          long count=words.stream()
//			  .filter(w -> w.length()==len && w.contains(Character.toString(c)) && !w.contains(Character.toString(d)))
//			  .count();
//          return (int)count;
          
         
       		  
		}
	}




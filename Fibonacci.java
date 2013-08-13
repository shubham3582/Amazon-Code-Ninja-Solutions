/*
Author Shubham Srivastava
Problem Amazon Code Ninja - Fibonacci Factor

*/
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;
 
public class Solution {
 
  // Scanner for reading from System.in
	private static Scanner scanner;
    // assume Unicode UTF-8 encoding
    private static final String charsetName = "UTF-8";	
    
    private static int totalTests;
    private static List<Integer> inputList;
    private static List<Long> series;
    
    // Initialize scanner
    static {
        scanner = new Scanner(new java.io.BufferedInputStream(System.in), charsetName);
        inputList = new ArrayList<Integer>();
        series = new ArrayList<Long>();
        series();
    }
    
    /*
     * Method for printing an object to System.out
     */
    private static void print(Object o){
    	System.out.println(o.toString());
    }
   
	/*
	 * Main method
	 */
	public static void main(String[] args) {
		totalTests = scanner.nextInt();
		while(totalTests-- != 0){
			inputList.add(scanner.nextInt());
		}
		process(inputList);
	}
 
	/*
	 * Method to process the input list and print the output
	 */
	public static void process(List<Integer> inputList){
		int count = 0;
		for(int test : inputList){
			Set<Integer> factorsSet = primeFactors(test);
			List<Integer> factors = new ArrayList<Integer>(factorsSet);
			Collections.sort(factors);
			//System.out.println("factors" + factors);
			int i = 0;
			long init = series.get(i);
			outer:
			while(true){
				for(int factor : factors){
					if(init%factor == 0){
						print(init + " " + factor);
						break outer;
					}
				}
				i++;
				if(i==series.size())
					break;
				init = series.get(i);
			}
		}
	}
 
	/*
	 * Find fibonacci series
	 */
	public static void series(){
		long f = 0;
		long g = 1;
 
		for(int i = 1; i <= 90; i++)
		{
			if(f != 0 && f != 1)
				series.add(f);
			f = f + g;
			g = f - g;
		} 
 
	}
 
	/*
	 * Method to find prime factors of a given number
	 */
	public static Set<Integer> primeFactors(int number) {
		int n = number;
		Set<Integer> factors = new HashSet<Integer>();
		//System.out.println(number);
		for (int i = 2; i <= n; i++) {
		//	System.out.println(i);
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		return factors;
	}
 
	/*
	 * Method to find if given number is fibonacci or not
	 */
	public static boolean isFibonacci(int number) {
		int testPrev = (5*(number*number))+4;
		int testAfter = (5*(number*number))-4;
		return isSquare(testPrev) || isSquare(testAfter);
	}	
 
	/*
	 * Method  to find if a given number is a perfect square
	 */
	public static boolean isSquare(int number) {
	 	try{
	 		double sqrt = Math.sqrt(number);
	       if ((sqrt == Math.floor(sqrt)) && !Double.isInfinite(sqrt)){
	       		return true;	
	       }
	       else
	       	return false;
	    }catch(Exception e){
	        return false;
	    }
	}	
 
}

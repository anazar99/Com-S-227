/**
 * Author: Ahmad M. Nazar
 * Com S 227 Mini Assignment 1
 * Simple loops exercises
 */
package mini1;
import java.util.Scanner;


public class LoopTheLoopyLoopingLoopers 
{
	/**
	 * Determines the number of iterations of Newton's method
	 * required to approximate the square root of x within
	 * the given bound.  Newton's method starts out by 
	 * setting the initial approximate <em>answer</em> to x.  
	 * Then in each iteration, <em>answer</em> is replaced by 
	 * the quantity <em>(answer + x / answer) / 2.0</em>.
	 * The process stops when the difference between 
	 * x and <em>(answer * answer)</em> is strictly less than
	 * the given bound <code>err</code>.  The method
	 * returns the number of iterations required.
	 * The given value x must be non-negative.
	 * <p>
	 * For example, given x = 10 the first three iterations
	 * of Newton's method produce the approximate values 
	 * 5.5, 3.66, and 3.20. Those three values squared are
	 * 30.29, 13.39, and 10.21, respectively.
	 * Therefore <code>countIterations(10, 1.0)</code>
	 * returns 3, since it takes 3 iterations to get the result 10.21
	 * that is within 1.0 of x.  
	 * On the other hand, <code>countIterations(10, 200)</code> returns 0,
	 * since 10 * 10 = 100 is already within 200 units of x = 10.
	 * @param x
	 *   value whose square root is to be approximated
	 * @param err
	 *   given bound for the approximation
	 * @return
	 *   number of iterations required to get an approximation
	 *   whose square is within the given bound of x
	 */
	public static int countIterations(double x, double err)
	{
		double answer = x;
		int i = 0;

		for(i = 0; i < 100; i++)
		{	
			if(Math.abs(x - (answer * answer)) < err)				//this checks if the absolute value of the difference is less than the error
			{
				break;
			}
			answer = (answer + x / answer) / 2;
		}

		return i;
	}

	/**
	 * Returns a string with runs of consecutive characters removed.
	 * For example, <code>eliminateRuns("abbbccbbd")</code> returns 
	 * the string "abcbd".
	 * @param s
	 *   given string (possibly empty)
	 * @return
	 *   string similar to s but with runs removed
	 */
	public static java.lang.String eliminateRuns(java.lang.String s)
	{
		String result = "";
		int length = s.length();

		for(int i = 0; i < length - 1; i++)
		{
			char c = s.charAt(i);
			char d = s.charAt(i + 1);

			if(c != d)
			{
				result =  result + c;
			}

			if(i == length - 2)									//this if statement checks when there is no character after the last and prints that character
			{
				result = result + d;
			}

		}
		return result;
	}

	/**
	 * Counts the number of times that one string occurs as a substring in
	 * another, optionally allowing the occurrences to overlap.  For
	 * example:
	 * <ul>
	 * <li><code>countOccurrences("aa", "aaaaa", false)</code> returns 2
	 * <li><code>countOccurrences("aa", "aaaaa", true)</code> returns 4
	 * <li><code>countOccurrences("aa", "ababab", true)</code> returns 0
	 * </ul>
	 * @param t
	 *   string we are looking for ("target")
	 * @param s
	 *   string in which we are looking ("source")
	 * @param allowOverlap
	 *   true if occurrences of t are allowed to overlap
	 * @return
	 *   number of times t occurs in s as a substring
	 */
	public static int countOccurrences(String t, String s, boolean allowOverlap)
	{
		int count = 0;

		if(allowOverlap)
		{
			int i = 0;
			while ((i = s.indexOf(t, i)) != -1)
			{
				i++;
				count++;
			}
		}

		else
		{
			int i = 0;
			while ((i = s.indexOf(t, i)) != -1)
			{
				i = i + 2;
				count++;
			}
		}

		return count;
	}

	/**
	 * Merges two strings together, using alternating characters from each,
	 * except that runs of the same character are kept together.  For example,
	 * <ul>
	 * <li><code>mergePreservingRuns("abcde", "xyz") returns "axbyczde"
	 * <li><code>mergePreservingRuns("abbbbcde", "xyzzz") returns "axbbbbyczzzde"
	 * </ul>
	 * Either or both of the strings may be empty.  If the first string
	 * is nonempty, its first character will be first in the returned string.
	 * @param t
	 *   first string
	 * @param s
	 *   second string
	 * @return
	 *   string obtained by merging characters from t and s, preserving runs
	 */
	public static String mergePreservingRuns(String t, String s)
	{
		int indexT = 0;
		int indexS = 0;
		String result = "";

		while (indexT < t.length() && indexS < s.length()) 	 	 //This alternates the characters in both strings and concatenates them
		{
			char tPreviousChar = t.charAt(indexT++);
			result = result + tPreviousChar;

			while (indexT < t.length() &&  t.charAt(indexT) == tPreviousChar)
			{
				result = result + tPreviousChar;
				indexT++;
			}

			char sPreviousChar = s.charAt(indexS++);
			result = result + sPreviousChar;

			while (indexS < s.length() &&  s.charAt(indexS) == sPreviousChar)
			{
				result += sPreviousChar;
				indexS++;
			}   
		}

		while (indexT < t.length()) 								//When both strings have iterated, this adds the remaining of letters to the result
		{
			result += t.charAt(indexT++);
		}

		while (indexS < s.length()) 
		{
			result = result + s.charAt(indexS++);
		}

		return result;
	}

	/**
	 * Separates s into two strings, each made of alternating characters
	 * from s, except that runs of the same character are kept together.
	 * The two strings are concatenated with a space between them to make
	 * a single returned string. If the given string is empty, the returned 
	 * string is a single space.
	 * For example,
	 * <ul>
	 * <li><code>takeApartPreservingRuns("abcdefa")</code> returns "acea bdf"
	 * <li><code>takeApartPreservingRuns("aabcccddddefa")</code> returns "aaccccea bddddf"
	 * </ul>
	 * @param s
	 *   any string
	 * @return
	 *   pair of strings obtained by taking alternating characters from s, 
	 *   keeping runs of the same character together, concatenated with 
	 *   one space between them into a single string 
	 */
	public static String takeApartPreservingRuns(String s)
	{
		String a = "";
		String b = "";
		boolean check = true;										//boolean value to start with a true condition when checking the first character in the string, changed to false when not first character in string
		int i = 0;

		while(i < s.length()) 
		{
			if(check) 
			{
				char c = s.charAt(i);
				while(i < s.length() && (c == s.charAt(i))) 			//while loops which checks the consecutive character with condition if c = to character at the position
				{
					a = a + c;										//concatenates the strings
					i++;	
				}	
				check = false;
			}

			else
			{
				char c = s.charAt(i);
				while(i < s.length() && (c == s.charAt(i))) 			//while loops which checks the consecutive character with condition if c = to character at the position
				{
					b = b + c;										//concatenates the strings
					i++;
				}
				check = true;					
			}
		}
		return a + " " + b ;
	}

	/**
	 * Returns the character that occurs most frequently in the given string.
	 * If several characters occur with the same maximum frequency, 
	 * returns the one that occurs first in the string.
	 * @param s
	 * the string that is passed to check most frequent character
	 * @return
	 * returns either the character with most runs or if characters are of equivalent value, returns first character
	 */
	public static char findMostFrequentCharacter(java.lang.String s)
	{
		int count = 0;
		int max = 0;
		int index = 0;
		char c = s.charAt(1);
		char currentChar = s.charAt(0);
		for (int i = 1; i < s.length(); i++)
		{
			c = s.charAt(i);
			if (c == currentChar)
			{
				// matches the 'current' character, add 1
				count = count + 1;
			}
			else
			{
				// at the end of the run, if it was a longer run, make that the max
				if (count > max)
				{
					max = count;
					index = i;
				}
				
				currentChar = c;       							// starts to count a new run for different character
				count = 0;
			}
		}
		index = s.length() - 1;
		if(count > max)
		{
			max = count;
		}

		return s.charAt(index);
	}
	/**
	 * Method that searches in a string of numbers for the second largest value
	 * Contains a function that splits the string and reads the numbers without the space does as mentioned to the string of text
	 * Split array of numbers is then stored into a string.
	 * Then that string value is parsed into a newly declared int array of size length of split string array
	 * @param text
	 * String of text which contains integer values with an arbitrary space in between
	 * @return
	 * returns the value of the second largest integer
	 */
	public static int findSecondLargest(java.lang.String text)
	{
		Scanner scanner = new Scanner(text);

		int max = 0;
		int secondLargest = 0;

		String[] nums = text.split(" ");

		int[] numbers = new int[nums.length];

		for(int i = 0; i < nums.length; i++)					//for loop which stores the split array into a string
		{													// and stores the string int value in a new int array
			String numsInAString = nums[i];
			numbers[i] = Integer.parseInt(numsInAString); 		
		}

		for(int i = 0; i < nums.length; i++)
		{
			if(numbers[i] > max)
			{
				secondLargest = max;							//if the consecutive element is bigger than the current max
				max = numbers[i];							//then the previous max is now the second largest int
			}
		}

		return secondLargest;
	}
	/**
	 * Method that checks whether a given string of text which contains numbers and spaces is part of a Lucas Sequence
	 * A function that splits the string and reads the numbers without the space does as mentioned to the string of text
	 * Split array of numbers is then stored into a string.
	 * Then that string value is parsed into a newly declared int array of size length of split string array
	 * Method will check if value of the fourth term [element 3] in the array is equal to the sum of the first 3 elements
	 * if the sum of the fourth element satisfies the condition, method will return true and false otherwise
	 * An example of such a sequence is the Fibonacci sequence (0, 1, 1, 2, 4)
	 * if the counter that checks the total number of elements has a value of <= 3 then the method will return true
	 * @param text
	 *A string of text with integer values seperated by an arbitrary space
	 * @return
	 * returns true if the conditions of a Lucas sequence are satisfied or when length of string is less than 3. False otherwise
	 */
	public static boolean isLucasSequence(java.lang.String text)
	{
		Scanner scanner = new Scanner(text);

		String[] nums = text.split(" ");

		int[] numbers = new int[nums.length];

		if(nums.length < 3)
		{
			return true;
		}
		else
		{
			for(int i = 0; i < nums.length; i++)					//for loop which stores the split array into a string
			{													// and stores the string int value in a new int array
				String numsInAString = nums[i];
				numbers[i] = Integer.parseInt(numsInAString); 		
			}
			
			int sum = numbers[0] + numbers[1];		//this checks the sum of the first 3 elements

			if(sum == numbers[2])
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}

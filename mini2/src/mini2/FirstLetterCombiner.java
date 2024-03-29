//@author: Ahmad M. Nazar
//CS 227
package mini2;

import api.Combiner;

/**
 * Combiner that appends the first letter of a string onto
 * the accumulator.  If the string is empty, returns the 
 * accumulator.
 */
public class FirstLetterCombiner implements Combiner<String>
{

	@Override
	public String combine(String obj, String s) 
	{
		if(s.length() == 0)
		{
			return obj;
		}
		else
		{
			String result = "";
			result = obj + s.charAt(0);
			return result;
		}
	}

}


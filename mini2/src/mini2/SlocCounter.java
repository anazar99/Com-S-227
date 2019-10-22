//@author: Ahmad M. Nazar
//CS 227
package mini2;

import api.Combiner;

/**
 * Combiner whose <code>combine</code> method, given an Integer n and a string, 
 * returns n if the string is a blank line or a line containing
 * a single curly brace; otherwise returns n + 1.  (A curly brace on a line whose
 * only other text is an end-of-line comment is treated as just a curly brace.)
 * Using a SlocCounter in the reduce method has the general effect of counting 
 * the number of "source lines of code" that are actual program statements,
 * assuming that line comments and javadoc comments have already been
 * filtered out.
 */
public class SlocCounter implements Combiner<Integer>
{

	@Override
	public Integer combine(Integer obj, String s) 
	{
		if(s.trim().length() == 0 || s.equals("{") || s.equals("}") || s.equals("//"))
		{
			return obj;
		}

		else
		{
			return obj + 1;
		}
	}	
}
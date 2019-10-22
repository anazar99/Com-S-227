//@author: Ahmad M. Nazar
//CS 227
package mini2;

import api.Selector;

/**
 * Selector that returns false if the current string is
 * within a javadoc comment, true otherwise.  Using a NonJavadocSelector
 * in the filter method of a StringList has the effect of removing all
 * javadoc comments.  Note that we are assuming that javadoc comments
 * always start and end on different lines, and that no executable
 * code ever appears on the same line as a javadoc comment.
 */
public class NonJavadocSelector implements Selector
{

	@Override
	public boolean select(String s) 
	{
		if (s.contains("/**")) 		//checks whether the string is equal to the java doc style i.e /** 
		{
			return false;
		}
		
		if (s.contains("*/")) 		//checks whether it contains a continuous multiple line comment
		{
			return false;
		}
		
		return true;
	}
}


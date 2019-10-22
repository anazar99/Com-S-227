//@author: Ahmad M. Nazar
//CS 227
package mini2;

import api.Selector;

/**
 * Selector whose <code>select</code> method returns false for strings whose first non-whitespace
 * text is "//", and true for all other strings.
 */
public class NonLineCommentSelector implements Selector
{

	@Override
	public boolean select(String s) 
	{
		if(s.charAt(0) == '/' && s.charAt(1) == '/') 		//checks whether the first two characters in a string
		{													//are double slashes which indicate a comment
			return false;
		}	
		return true;
	}
}
//@author: Ahmad M. Nazar
//CS 227
package mini2;

import api.Selector;

/**
 * Selector that returns false for a strings that are
 * empty, that are all whitespace, or whose first non-whitespace
 * character is the '#' character.
 */
public class ValidLineSelector implements Selector
{

	@Override
	public boolean select(String s) 
	{
		String strings = s.trim();
		
		if(strings.length() == 0 || strings.charAt(0) == '#')		//checks whether the string is empty (all white space)
		{															//or whether the string starts with a #
			return false;
		}
		
		return true;
	}
}

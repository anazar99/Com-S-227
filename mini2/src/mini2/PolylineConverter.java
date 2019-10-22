//@author: Ahmad M. Nazar
//CS 227
package mini2;

import java.awt.Point;
import api.Converter;
import plotter.Polyline;

/**
 * Converts a string into a <code>Polyline</code> object.  The given 
 * string must conform to the format specified for one valid line of the file 
 * as described in Lab 8, checkpoint 2.  See
 * <pre>
 * http://web.cs.iastate.edu/~cs227/labs/lab8/page12.html
 * </pre>
 */

public class PolylineConverter implements Converter<Polyline>
{

	@Override
	public Polyline convert(String s) 
	{
		if(s.charAt(0) == '#')
		{
			return null;
		}
		else
		{
			String[] strings = s.split(" ");
			if(Character.isAlphabetic(strings[0].charAt(0)))
			{
				Polyline poly = new Polyline(strings[0]);

				for(int i = 1; i < strings.length - 1; i++)
				{
					poly.addPoint(new Point(Integer.parseInt(strings[i]), Integer.parseInt(strings[i + 1])));
				}
				
				return poly;
			}

			else 
			{
				Polyline poly = new Polyline(strings[1], Integer.parseInt(strings[0]));

				for(int i = 2; i < strings.length - 1; i++)
				{
					poly.addPoint(new Point(Integer.parseInt(strings[i]), Integer.parseInt(strings[i + 1])));
				}
				
				return poly;
			}
		}
	}
}

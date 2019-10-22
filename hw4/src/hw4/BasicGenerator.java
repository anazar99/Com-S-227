/**
 * @author: Ahmad M. Nazar
 * CS 227
 */
package hw4;

import java.util.Random;
import api.Generator;
import api.Position;
import api.Shape;

/**
 * Basic algorithm for generating shapes for the game
 * Shape order based on order of appearance on assignment spec
 * Based on a 1/5 probability of a magic block being chosen, a random number generator generates
 * numbers between 0 and 4 and if the number is 0 then the block is appointed as magic i.e true.
 * Based on the probability of having a 1/6 chance for each shape to be generated
 * a random number generator generates numbers from 0 to 5 where for each number
 * a different shape is generated and in the condition, the magic condition is also set.
 * in each condition, the parameters for each shape is met
 * finally, a shape is returned with the set conditions
 */
public class BasicGenerator implements Generator
{
	@Override
	public Shape getNext(int width)
	{
		Random random = new Random();
		Random rand = new Random();

		int probabilityShape = random.nextInt(6);			//random number generator for probability of shape
		int probabilityMagically = rand.nextInt(5);			//random number generator for probability of magic
		int mid = width / 2;								//half of of the width

		if(probabilityShape == 0) 
		{
			if(probabilityMagically == 0) 
			{
				return new LShape(new Position(-1, mid + 1), true);
			}

			else 
			{
				return new LShape(new Position(-1, mid + 1), false);
			}
		}

		else if(probabilityShape == 1) 
		{
			if(probabilityMagically == 0) 
			{
				return new JShape(new Position(-1, mid), true);
			}

			else 
			{
				return new JShape(new Position(-1, mid), false);
			}
		}

		else if(probabilityShape == 2) 
		{
			if(probabilityMagically == 0) 
			{
				return new IShape(new Position(-2, mid + 1), true);
			}

			else 
			{
				return new IShape(new Position(-2, mid + 1), false);
			}
		}

		else if(probabilityShape == 3) 
		{
			if (probabilityMagically == 0) 
			{
				return new OShape(new Position(-1, mid), true);
			}

			else 
			{
				return new OShape(new Position(-1, mid), false);
			}
		}

		else if(probabilityShape == 4) 
		{
			if(probabilityMagically == 0) 
			{
				return new TShape(new Position(0, mid), true);
			}

			else 
			{
				return new TShape(new Position(0, mid), false);
			}
		}

		else if(probabilityShape == 5) 
		{
			if (probabilityMagically == 0) 
			{
				return new SZShape(new Position(-1, mid), true);
			}

			else 
			{
				return new SZShape(new Position(-1, mid), false);
			}
		}
		return null;
	}
}


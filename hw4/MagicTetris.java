/**
 * @author: Ahmad M. Nazar
 * CS 227
 */
package hw4;

import java.util.ArrayList;
import java.util.List;

import api.AbstractGame;
import api.Position;

/**
 * MagicTetris implementation.
 */
public class MagicTetris extends AbstractGame
{
	/**
	 * instance variable of the current score
	 */
	private int score;

	/**
	 * instance boolean to change status of game from gravity mode to normal and vice versa
	 */
	private boolean grrrrravityMode;

	/**
	 * Constructs a game with the given width (columns) and height (rows).
	 * This game will use a new instance of BasicGenerator to 
	 * generate new shapes.
	 * @param width
	 *   width of the game grid (number of columns)
	 * @param height
	 *   height of the game grid (number of rows)
	 */
	public MagicTetris(int width, int height)
	{
		super(width, height, new BasicGenerator());
	}

	/**
	 * Method that returns a list of positions to be collapsed
	 * When the game is in gravity mode:
	 * method checks if there are empty blocks around a given block
	 * if the surrounding blocks are not null, then it is considered collapsable and 
	 * no new positions are added to the list
	 * if the blocks surrounding the current block are null and the columns are collapsable
	 * then a position is added to the list
	 * When the game is not in gravity mode
	 * Counts the number of magics in the blocks if available.
	 * Checks whether the surrounding blocks of the current one are null
	 * then if null, collapsable columns is false
	 * if the blocks can be collapsed by columns, the position of the block is added to the list
	 * total score is then recalculated using total score + added score where added score = 2^m 
	 * where m is the number of magics counted
	 * the score does not increase when in gravity mode
	 */
	@Override
	public List<Position> determinePositionsToCollapse()
	{
		List<Position> list = new ArrayList<>();

		if(grrrrravityMode) 
		{

			for(int columns = 0; columns< getWidth();columns++) 
			{
				boolean collapsableColumns = false;

				for(int rows = 0; rows < getHeight(); rows++) 
				{

					if(getBlock(rows, columns) != null) 	//checks whether surrounding blocks are not empty
					{
						collapsableColumns = true;
					}

					//if the block is empty and flag for columns is true adds position to the list
					if((getBlock(rows, columns) == null) && collapsableColumns) 
					{
						list.add(new Position(rows, columns));
					}
				}
			}
			grrrrravityMode = false;
		}

		for(int rows = 0; rows < getHeight();rows++) 
		{
			boolean collapsableBlocks = true;
			int countTheMagics = 0;

			for(int columns = 0; columns < getWidth(); columns++) 
			{

				if(getBlock(rows, columns) == null) 	//checks the availability of surrounding blocks
				{
					collapsableBlocks = false;

				}

				else if(getBlock(rows, columns).isMagic()) 
				{
					countTheMagics++;					//counts the number of magic blocks in the row
				}	
			}

			if(collapsableBlocks) 
			{
				for(int columns = 0; columns < getWidth(); columns++) 		//adds the position of a block that is collapsable
				{
					list.add(new Position(rows, columns));
				}

				int addedScore = 1;
				addedScore = (int) Math.pow(2, countTheMagics);
				score = score + addedScore;				//increments the score according to the game

				if(countTheMagics >= 3) 
				{
					grrrrravityMode = true;				//if there are 3 or more magics, flag for gravity is true
				}
			}
		}
		return list;
	}

	/**
	 * returns the total score
	 */
	@Override
	public int determineScore()
	{
		return score;
	}
}

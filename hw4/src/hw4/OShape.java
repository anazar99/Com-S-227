/**
 * @author: Ahmad M. Nazar
 * CS 227
 */
package hw4;

import api.Block;
import api.Cell;
import java.awt.Color;
import api.Position;

public class OShape extends AbstractShape 
{
	/**
	 * instance array of <Cell> objects
	 */
	private Cell[] cell = new Cell[4];

	/**
	 * instance of <Position> objects 
	 */
	private Position position;

	private Position position1;

	private Position position2;

	private Position position3;

	/**
	 * constructs an OShape object to be used in the game of MagicTetris
	 * @param givenPosition
	 * initial position of the shape
	 * @param magic
	 * boolean on whether the shape will contain a magic block 
	 */
	public OShape(Position givenPosition, boolean magic) 
	{
		//constructs a super class of AbstractShape
		super(givenPosition);

		//initial position of cell block
		position = new Position(givenPosition.row(), givenPosition.col());

		//position assignments of other locations with respect to the OShape
		position1 = new Position(position.row(), position.col() + 1);
		position2 = new Position(position.row() + 1, position.col());
		position3 = new Position(position.row() + 1, position.col() + 1);

		//cell assignments according to the position assigned above
		cell[0] = new Cell(new Block(Color.YELLOW, magic), position);
		cell[1] = new Cell(new Block(Color.YELLOW, false), position1);
		cell[2] = new Cell(new Block(Color.YELLOW, false), position2);
		cell[3] = new Cell(new Block(Color.YELLOW, false), position3);

		//call to the method to set cells in AbstractShape
		super.setCell(cell);
	}

	/**
	 * transform method override that does nothing to the OShape
	 */
	@Override
	public void transform() 
	{
		//do nothing
		//OShape is symmetrical and should not transform in anyway
	}
}
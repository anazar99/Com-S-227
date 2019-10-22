/**
 * @author: Ahmad M. Nazar
 * CS 227
 */
package hw4;

import api.Block;
import api.Cell;
import java.awt.Color;
import api.Position;

public class IShape extends AbstractShape 
{
	/**
	 * instance array of <Cell> objects
	 */
	private Cell[] cell = new Cell[3];

	/**
	 * instance of <Position> objects
	 */
	private Position position;
	
	private Position position1;
	
	private Position position2;

	/**
	 * constructs an IShape object to be used in the game of MagicTetris
	 * @param givenPosition
	 * initial position of the cell block
	 * @param magic
	 * boolean on whether the constrcuted shape will have a magic block
	 */
	public IShape(Position givenPosition, boolean magic) 
	{
		//constructor for super class of AbstractShape
		super(givenPosition);

		//initial position of the cell
		position = givenPosition;

		//assignment of positions with respect to the IShape
		position1 = new Position(position.row() + 1, position.col());
		position2 = new Position(position.row() + 2, position.col());

		//assignment of cell blocks according to their position and color
		cell[0] = new Cell(new Block(Color.CYAN, magic), position);
		cell[1] = new Cell(new Block(Color.CYAN, false), position1);
		cell[2] = new Cell(new Block(Color.CYAN, false), position2);
		
		//call to the method to set cells in AbstractShape
		super.setCell(cell);
	}
}

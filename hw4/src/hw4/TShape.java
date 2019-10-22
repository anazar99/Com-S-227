/**
 * @author: Ahmad M. Nazar
 * CS 227
 */
package hw4;

import java.awt.Color;

import api.Block;
import api.Cell;
import api.Position;

public class TShape extends AbstractShape 
{
	/**
	 * array instance of <Cell> objects
	 */
	private Cell[] cell = new Cell[4];

	/**
	 * instances of <Position> objects
	 */
	private Position position;

	private Position position1;

	private Position position2;

	private Position position3;

	/**
	 * Constructs a TShape object to be used in the game of MagicTetris
	 * @param givenPosition
	 * initial position of cell block
	 * @param magic
	 * boolean variable of whether the shape contains a magic block
	 */
	public TShape(Position givenPosition, boolean magic) 
	{
		//constructs a super class of AbstractShape
		super(givenPosition);

		//initial position of cell
		position = new Position(givenPosition.row() - 1, givenPosition.col());

		//position assignments of cells with respect to the TShape
		position1 = new Position(position.row() + 1, position.col() - 1);
		position2 = new Position(position.row() + 1, position.col());
		position3 = new Position(position.row() + 1, position.col() + 1);

		//cell assignments to position of the TShape
		cell[0] = new Cell(new Block(Color.MAGENTA, magic), position);
		cell[1] = new Cell(new Block(Color.MAGENTA, false), position1);
		cell[2] = new Cell(new Block(Color.MAGENTA, false), position2);
		cell[3] = new Cell(new Block(Color.MAGENTA, false), position3);
		
		//call to the method that sets the cell value in AbstractShape
		super.setCell(cell);	
	}
}
/**
 * @author: Ahmad M. Nazar
 * CS 227
 */
package hw4;

import java.awt.Color;

import api.Block;
import api.Cell;
import api.Position;

public class LShape extends AbstractShape 
{
	/**
	 * instance array of object <Cell> 
	 */
	private Cell[] cell = new Cell[4];
	/**
	 * instance Position objects for each different position to the shape
	 */
	private Position position;
	
	private Position position1;
	
	private Position position2;
	
	private Position position3;
	
	/**
	 * Constructs an LShape object to be used in a game of Magic Tetris
	 * @param givenPosition
	 * initial position of center of shape
	 * @param magic
	 * boolean value of whether the shape contains a magic block 
	 */
	public LShape(Position givenPosition, boolean magic) 
	{
		//constructor of super class of AbstractShape
		super(givenPosition);

		//assignment of positions with respect to the grid
		position = givenPosition;
		position1 = new Position(position.row()+1, position.col() - 2);
		position2 = new Position(position.row()+1, position.col() - 1);
		position3 = new Position(position.row()+1, position.col());

		//assignment of cell blocks with respect to the the given instructions for the LShape
		cell[0] = new Cell(new Block(Color.ORANGE, magic), position);
		cell[1] = new Cell(new Block(Color.ORANGE, false), position1);
		cell[2] = new Cell(new Block(Color.ORANGE, false), position2);
		cell[3] = new Cell(new Block(Color.ORANGE, false), position3);
		
		//call to the method to set cells in AbstractShape
		super.setCell(cell);
	}
}

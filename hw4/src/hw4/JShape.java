/**
 * @author: Ahmad M. Nazar
 * CS 227
 */
package hw4;

import api.Block;
import api.Cell;
import java.awt.Color;
import api.Position;

public class JShape extends AbstractShape 
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
	 * Constructs a JShape object to be used in the game of MagicTetris
	 * @param givenPosition
	 * position of the center of the shape or the initial position of the shape
	 * @param magic
	 * boolean of whether the constructed shape will have a magic block or not
	 */
	public JShape(Position givenPosition, boolean magic) 
	{
		//constructor of a super class of AbstractShape
		super(givenPosition);
		
		//initial position of the shape
		position = new Position(givenPosition.row(), givenPosition.col()-1);
		
		//FIX THIS ITS A TSHAPE
		//assignment of positions with respect to the JShape
		position1 = new Position(position.row() + 1, position.col());
		position2 = new Position(position.row() + 1, position.col() + 1);
		position3 = new Position(position.row() + 1, position.col() + 2);
		
		//assignment of cell blocks with respect to their assigned positions
		cell[0] = new Cell(new Block(Color.BLUE, magic), position);
		cell[1] = new Cell(new Block(Color.BLUE, false), position1);
		cell[2] = new Cell(new Block(Color.BLUE, false), position2);
		cell[3] = new Cell(new Block(Color.BLUE, false), position3);
		
		//call to the method that sets cell values in AbstractShape
		super.setCell(cell);
		
	}
	
	

}

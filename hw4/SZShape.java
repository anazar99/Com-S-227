/**
 * @author: Ahmad M. Nazar
 * CS 227
 */
package hw4;

import java.awt.Color;

import api.Block;
import api.Cell;
import api.Position;

public class SZShape extends AbstractShape 
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
	 * instance object of type color
	 */
	private Color colorfulllll;

	/**
	 * constructs a SZShape object to be used in the game of MagicTetris
	 * @param givenPosition
	 * initial position of the cell block
	 * @param magic
	 * boolean on whether the shape contains a magic block
	 */
	public SZShape(Position givenPosition, boolean magic) 
	{
		//constructs a super class of AbstractShape
		super(givenPosition);

		colorfulllll = Color.GREEN;

		//initial position of cell block
		position = new Position(givenPosition.row(), givenPosition.col());

		//assignment of cell positions with respect to the SZShape
		position1 = new Position(position.row() + 1, position.col());
		position2 = new Position(position.row() + 1, position.col() + 1);
		position3 = new Position(position.row() + 2, position.col() + 1);

		//assignment of the cell blocks according to the positions above
		cell[0] = new Cell(new Block(colorfulllll, magic), position);
		cell[1] = new Cell(new Block(colorfulllll, false), position1);
		cell[2] = new Cell(new Block(colorfulllll, false), position2);
		cell[3] = new Cell(new Block(colorfulllll, false), position3);

		//call to the method to set cells in AbstractShape
		super.setCell(cell);
	}
	/**
	 * Override of the transform function from abstract shape
	 * flips the sz shape in the vertical axis by making the changing the sign on respective x coordinates 
	 * otherwise, revert to original position resepctive of color and shifts 
	 */
	@Override
	public void transform() 
	{
		cell = getCells();
		
		//changes the color to red if current is green
		if(colorfulllll == Color.GREEN)			
		{
			colorfulllll = Color.RED;			
		}
		else
		{
			colorfulllll = Color.GREEN;
		}
		
		for(int i = 0; i < cell.length; i++)
		{
			cell[i].setBlock(new Block(colorfulllll, cell[i].getBlock().isMagic()));
		}
		
		///flips the coordinate of the top and bottom block depending on color,
		//if green switches top to right and bottom to left
		//if red switched top to left and bottom to right
		for(int i = 0; i < cell.length - 2; i++)
		{
			int allTheColumns = cell[i].getCol();
			
			cell[i].setCol(cell[3 - i].getCol());		
			cell[3 - i].setCol(allTheColumns);
		}
		super.setCell(cell);
	}
}

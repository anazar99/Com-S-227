/**
 * @author Ahmad M. Nazar
 * CS 227
 */
package hw4;

import api.Block;
import api.Cell;
import api.Position;
import api.Shape;

/**
 * Abstract superclass for implementations of the Shape interface.
 */
public abstract class AbstractShape implements Shape
{
	/**
	 * instance array of <Cell> objects
	 */
	private Cell[] cells;

	/**
	 * instance of Position object
	 */
	private Position position;

	/**
	 * constructs an AbstractShape with a given position
	 * @param givenPosition
	 * assigns the givenPosition to the private variable position in this class
	 */
	protected AbstractShape(Position givenPosition) 
	{
		position = givenPosition;
	}

	/**
	 * assigns the given cell value to this.cells array
	 * used throughout the class to induce method calls
	 */
	protected void setCell(Cell[] givenCell) 
	{
		cells = givenCell;
	}

	/**
	 * Creates a copy of the available cell objects arrays and stores them in a new arrau
	 * returns the array of cells for the given shape in a given order
	 * does not change when transformed
	 */
	@Override
	public Cell[] getCells() 
	{
		Cell[] copiessss = new Cell[cells.length];

		for(int i = 0; i < cells.length; i++) 
		{
			copiessss[i] = new Cell(cells[i]);
		}
		return copiessss;
	}

	/**
	 * Shifts the shape downwards by one row
	 */
	@Override
	public void shiftDown() 
	{
		for(int i = 0; i < cells.length; i++) 
		{
			cells[i].setRow(cells[i].getRow() + 1);
		}

		position.setRow(position.row() + 1);
	}

	/**
	 * Shifts the shape to left by one column
	 */
	@Override
	public void shiftLeft() 
	{
		for(int i = 0; i< cells.length; i++) 
		{
			cells[i].setCol(cells[i].getCol() - 1);
		}

		position.setCol(position.col() - 1);
	}

	/**
	 * Shifts the shape to the right by one column
	 */  
	@Override
	public void shiftRight() 
	{
		for(int i = 0; i< cells.length; i++) 
		{
			cells[i].setCol(cells[i].getCol() + 1);
		}
		position.setCol(position.col() + 1);
	}

	/**
	 * transforms the shape according to the game of MagicTetris
	 * Basic algorithm is to set the shape back to the origin coordinates,
	 * subtract the initial row from the current row
	 * subtract the initial column from the current column
	 * reverse the sign of the new row value
	 * add the original row value to the new value
	 * add the original column value to the new value   
	 */
	@Override
	public void transform() 
	{
		int row = position.row();
		int column = position.col();

		for(int i = 0; i < cells.length; i++) 
		{
			int temporaryRow = cells[i].getRow() - row;
			int temporaryColumn = cells[i].getCol() - column;

			cells[i].setRow(-temporaryColumn + row);
			cells[i].setCol(temporaryRow + column);
		}
	}

	/**
	 * cycles the position of the magic block by moving it from its initial position to the next cell block
	 * if the magic block reaches the last cell, it will cycle back to its initial position
	 */
	@Override
	public void cycle() 
	{
		for(int i = 0; i < cells.length; i++)
		{
			if(cells[i].getBlock().isMagic())
			{
				cells[i + 1].setBlock(cells[i].getBlock());
				//sets the previous block's magic boolean to false
				cells[i].setBlock(new Block(cells[i].getBlock().getColorHint(), false));		
				//break to prevent from changing other cells
				break;		
			}

			if(cells[cells.length - 1].getBlock().isMagic())
			{
				cells[0].setBlock(cells[cells.length - 1].getBlock());
				//sets the previous block's magic boolean to false
				cells[cells.length - 1].setBlock(new Block(cells[i].getBlock().getColorHint(), false));
				//break to prevent from changing other cells
				break;		
			}
		}		
	}

	/**
	 * Returns a deep copy of this object having the correct runtime type.
	 * @return 
	 *   a deep copy of this object
	 */
	@Override
	public Shape clone()
	{
		try
		{
			AbstractShape s = (AbstractShape) super.clone();

			s.position = new Position(position);
			s.cells = new Cell[cells.length];
			for (int i = 0; i < cells.length; i++) 
			{
				s.cells[i] = new Cell(cells[i]);
			}

			return s;
		}
		catch (CloneNotSupportedException e)
		{
			// can't happen
			return null;
		}
	}
}
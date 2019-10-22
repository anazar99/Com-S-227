/**
 * @author Ahmad M. Nazar
 *         Com S 227 
 *         Homework 3   
 */
package hw3;
import java.util.ArrayList;
import java.util.Random;

import api.Direction;
import api.Move;
import api.TilePosition;

/**
 * The Game class contains the state and logic for an implementation of a
 * video game such as "Threes".  The basic underlying state is an n by n 
 * grid of tiles, represented by integer values.  A zero in a cell is considered 
 * to be * "empty".  To play the game, a client calls the method <code>shiftGrid()</code>, 
 * selecting one of the four directions (LEFT, RIGHT, UP, DOWN). Each row or 
 * column is then shifted according to the rules encapsulated in the
 * associated <code>GameUtil</code> object. The move is completed by
 * calling <code>newTile</code>, which makes a new tile appear in the grid
 * in preparation for the next move.
 * <p>
 * In between the call to <code>shiftGrid()</code> and the call to
 * <code>newTile</code>, the client may also call <code>undo()</code>, which
 * reverts the grid to its state before the shift.
 * <p>
 * The game uses an instance of java.util.Random to generate new tile values
 * and to select the location for a new tile to appear.  The new values
 * are generated by the associated <code>GameUtil</code>'s 
 * <code>generateRandomTileValue</code> method, and the new positions are
 * generated by the <code>GameUtil</code>'s
 * <code>generateRandomTilePosition</code> method. The values are always 
 * generated one move ahead and stored, in order to support the ability
 * for a UI to provide a preview of the next tile value.
 * <p>
 * The score is the sum over all cells of the individual scores returned by
 * the <code>GameUtil</code>'s <code>getScoreForValue()</code> method.
 */

public class Game
{
	/**
	 * int value that takes the size of the grid
	 */
	private int size;
	
	/**
	 * private object for GameUtil that will be used across the program
	 */
	private GameUtil config;
	
	/**
	 * Random number generator that will be used in the methods
	 */
	private Random rand;
	
	/**
	 * 2D array for the initalized grid 
	 */
	private int[][] grid;
	
	/**
	 * this acts as an older grid that takes the values before a shiftGrid() is implemented, for the undo() method
	 */
	private int[][] undoGrid;
	
	/**
	 * direction object that is a reference to the enum across the program
	 */
	private Direction direction;
	
	/**
	 * int value for the randomly generated tile
	 */
	private int tileValue;
	
  /**
   * Constructs a game with a grid of the given size, using a default
   * random number generator. The initial grid is produced by the 
   * <code>initializeNewGrid</code> method of the given 
   * <code>GameUtil</code> object.  
   * @param givenSize
   *   size of the grid for this game
   * @param givenConfig
   *   given instance of GameUtil   
   */
  public Game(int givenSize, GameUtil givenConfig)
  {
    // just call the other constructor
    this(givenSize, givenConfig, new Random());
  }
  
  /**
   * Constructs a game with a grid of the given size, using the given
   * instance of <code>Random</code> for the random number generator.
   * The initial grid is produced by the <code>initializeNewGrid</code>
   * method of the given <code>GameUtil</code> object.  
   * @param givenSize
   *   size of the grid for this game
   * @param givenConfig
   *   given instance of GameUtil
   * @param givenRandom
   *   given instance of Random   
   */
  public Game(int givenSize, GameUtil givenConfig, Random givenRandom)
  {
	config = givenConfig;
	size = givenSize;
	rand = givenRandom;
	grid = config.initializeNewGrid(size, rand);		//initializes a new grid according to the given paramaters
  }
  /**
   * Returns the value in the cell at the given row and column.
   * @param row
   *   given row
   * @param col
   *   given column
   * @return
   *   value in the cell at the given row and column
   */
  public int getCell(int row, int col)
  {
    return grid[row][col];
  }
  
  /**
   * Sets the value of the cell at the given row and column.
   * <em>NOTE: This method should not be used by clients outside
   * of a testing environment.</em>
   * @param row
   *   given row
   * @param col
   *   given col
   * @param value
   *   value to be set
   */
  public void setCell(int row, int col, int value)
  {
    grid[row][col] = value;
  }
  
  /**
   * Returns the size of this game's grid.
   * @return
   *   size of the grid
   */
  public int getSize()
  {
    return size;
  }
  
  /**
   * Returns the current score.
   * @return
   *   score for this game
   */
  public int getScore()
  {
	  return config.calculateTotalScore(grid);
  }
  
  /**
   * Copy a row or column from the grid into a new one-dimensional array.  
   * There are four possible actions depending on the given direction:
   * <ul>
   *   <li>LEFT - the row indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array from left to right
   *   <li>RIGHT - the row indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array in reverse (from right to left)
   *   <li>UP - the column indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array from top to bottom
   *   <li>DOWN - the row indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array in reverse (from bottom to top)
   * </ul>
   * @param rowOrColumn
   *   index of the row or column
   * @param dir
   *   direction from which to begin copying
   * @return
   *   array containing the row or column
   */
  public int[] copyRowOrColumn(int rowOrColumn, Direction dir)
  {
	  int[] temp = new int[size];

	  if(dir == Direction.UP)
	  {
		  for(int i = 0; i < size; i++)
		  {
			  temp[i] = grid[i][rowOrColumn];		//for loop that copies the elements
		  }
	  }

	  if(dir == Direction.DOWN)
	  {
		  int j = 0;
		  for(int i = size - 1; i >= 0; i--)
		  {
			  temp[j] = grid[i][rowOrColumn];		//for loop that copies the elements
			  j++;
		  }
	  }

	  if(dir == Direction.LEFT)
	  {
		  for(int i = 0; i < size; i++)
		  {
			  temp[i] = grid[rowOrColumn][i];		//for loop that copies the elements
		  }
	  }

	  if(dir == Direction.RIGHT)
	  {
		  int j = 0;
		  for(int i = size - 1; i >= 0; i--)
		  {
			  temp[j] = grid[rowOrColumn][i];		//for loop that copies the elements
			  j++;
		  }
	  }
	  return temp;
  }
    
  /**
   * Updates the grid by copying the given one-dimensional array into
   * a row or column of the grid.
   * There are four possible actions depending on the given direction:
   * <ul>
   *   <li>LEFT - the given array is copied into the the row indicated by the 
   *   index <code>rowOrColumn</code> from left to right
   *   <li>RIGHT - the given array is copied into the the row indicated by the 
   *   index <code>rowOrColumn</code> in reverse (from right to left)
   *   <li>UP - the given array is copied into the column indicated by the 
   *   index <code>rowOrColumn</code> from top to bottom
   *   <li>DOWN - the given array is copied into the column indicated by the 
   *   index <code>rowOrColumn</code> in reverse (from bottom to top)
   * </ul>
   * @param arr
   *   the array from which to copy
   * @param rowOrColumn
   *   index of the row or column
   * @param dir
   *   direction from which to begin copying
   */
  public void updateRowOrColumn(int[] arr, int rowOrColumn, Direction dir)
  {
	  if(dir == Direction.LEFT)
	  {
		  for(int i = 0; i < size; i++)
		  {
			  grid[rowOrColumn][i] = arr[i];		//for loop that updates the elements
		  }
	  }

	  if(dir == Direction.RIGHT)
	  {
		  int i = 0;
		  for(int j = size - 1; j >= 0; j--)
		  {
			  grid[rowOrColumn][j] = arr[i];		//for loop that updates the elements
			  i++;
		  }
	  }

	  if(dir == Direction.UP)
	  {
		  for(int i = 0; i < size; i++)
		  {
			  grid[i][rowOrColumn] = arr[i];		//for loop that updates the elements
		  }
	  }

	  if(dir == Direction.DOWN)
	  {
		  int i = 0;
		  for(int j = size - 1; j >= 0; j--)		//for loop that updates the elements
		  {	
			  grid[j][rowOrColumn] = arr[i];
			  i++;
		  }
	  }
  }

  /**
   * Plays one step of the game by shifting the grid in the given direction.
   * Returns a list of Move objects describing all moves performed.  All Move 
   * objects must include a valid value for <code>getRowOrColumn()</code> and 
   * <code>getDirection()</code>.  If no cells are moved, the method returns
   * an empty list.
   * <p>
   * The shift of an individual row or column is performed by the 
   * method <code>shiftArray</code> of <code>GameUtil</code>.  
   * <p>
   * The score is not updated.
   * 
   * @param dir
   *   direction in which to shift the grid
   * @return
   *   list of moved or merged tiles
   */
  public ArrayList<Move> shiftGrid(Direction dir)
  {
	  direction = dir;
	  undoGrid = grid;
	  int[] temp = new int[size];
	  ArrayList<Move> move = new ArrayList<Move>();
	  if(dir == Direction.LEFT)
	  {
		  for(int i = 0; i < size; i++)
		  {
		  temp = this.copyRowOrColumn(i, dir);
		  move.addAll(config.shiftArray(temp));			//this adds all the moves into a list from shift array
		  this.updateRowOrColumn(temp, i, dir);
		  }
		  return move;
	  }
	  if(dir == Direction.RIGHT)
	  {
		  for(int i = 0; i < size; i++)
		  {
		  temp = this.copyRowOrColumn(i, dir);
		  move.addAll(config.shiftArray(temp));			//this adds all the moves into a list from shift array
		  this.updateRowOrColumn(temp, i, dir);
		  }
		  return move;
		  
	  }
	  if(dir == Direction.UP)
	  {
		  for(int i = 0; i < size; i++)
		  {
		  temp = this.copyRowOrColumn(i, dir);
		  move.addAll(config.shiftArray(temp));			//this adds all the moves into a list from shift array
		  this.updateRowOrColumn(temp, i, dir);
		  }
		  return move;
	  }
	  if(dir == Direction.DOWN)
	  {
		  for(int i = 0; i < size; i++) 
		  {
		  temp = this.copyRowOrColumn(i, dir);
		  move.addAll(config.shiftArray(temp));			//this adds all the moves into a list from shift array
		  this.updateRowOrColumn(temp, i, dir);
		  }
		  return move;
	  }
	  if(dir == null)
	  {
		  return null;
	  }

	  return move;
  }

  /**
   * Reverts the shift performed in a previous call to <code>shiftGrid()</code>, 
   * provided that neither <code>newTile()</code> nor <code>undo()</code>
   * has been called. If there was no previous call to <code>shiftGrid()</code> 
   * without a <code>newTile()</code> or <code>undo()</code>,
   * this method does nothing and returns false; otherwise returns true.
   * @return
   *   true if the previous shift was undone, false otherwise
   */
  public boolean undo()
  { 
	  if(newTile() == null && shiftGrid(direction) == null)
	  {
		  return false;
	  }
	  else
	  {
		  undoGrid = config.copyGrid(grid);
		  return true;
	  }
  }
  
  /**
   * Generates a new tile and places its value in the grid, provided that
   * there was a previous call to  <code>shiftGrid</code> without a 
   * corresponding call to <code>undo</code> or <code>newTile</code>.  
   * The tile's position is determined according
   * to the <code>generateRandomTilePosition</code> of this game's 
   * associated <code>GameUtil</code> object.  If there was no previous call to 
   * <code>shiftGrid</code> without an <code>undo</code> or <code>newTile</code>, 
   * this method does nothing and returns null; otherwise returns a
   * <code>TilePosition</code> object with the new tiles's position and value.
   * Note that the returned tile's value should match the <em>current</em>
   * value returned by <code>getNextTileValue</code>, and if this method returns
   * a non-null value the upcoming tile value should be updated according
   * to <code>generateRandomTileValue()</code>.  This method should
   * update the total score and the score should include the newly generated tile.
   * @return
   *   TilePosition containing the new tile's position and value, or null
   *   if no new tile is created
   */
  public TilePosition newTile()
  {
	tileValue = config.generateRandomTileValue(rand);
	TilePosition tile = new TilePosition(0, 0, 0);
	
	if(direction == Direction.LEFT)
	{
		tile = config.generateRandomTilePosition(grid, rand, direction);
		tile.setValue(tileValue);
		config.calculateTotalScore(grid);
		return tile;
	}
	if(direction == Direction.RIGHT)
	{
		tile = config.generateRandomTilePosition(grid, rand, direction);
		tile.setValue(tileValue);
		config.calculateTotalScore(grid);
		return tile;
	}
	if(direction == Direction.UP)
	{
		tile = config.generateRandomTilePosition(grid, rand, direction);
		tile.setValue(tileValue);
		config.calculateTotalScore(grid);
		return tile;
	}
	if(direction == Direction.DOWN)
	{
		tile = config.generateRandomTilePosition(grid, rand, direction);
		tile.setValue(tileValue);
		config.calculateTotalScore(grid);
		return tile;
	}
	if(direction == null)
	{
		return null;
	}
	
    return tile;
  }
  
  /**
   * Returns the value that will appear on the next tile generated in a call to 
   * <code>newTile</code>.  This is an accessor method that does not modify
   * the game state.
   * @return
   *   value to appear on the next generated tile
   */
  public int getNextTileValue()
  {
    return tileValue;
  }
  

}
  
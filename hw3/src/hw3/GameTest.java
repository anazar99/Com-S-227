package hw3;
import java.util.Arrays;
import java.util.Random;

import api.Direction;
import ui.ConsoleUI;

public class GameTest 
{
	public static void main(String[] args)
	{
		Random rand = new Random();
		int[] arr = new int[4];
		arr[0] = 1;	
		arr[1] = 2;
		arr[2] = 3;
		arr[3] = 0;
		GameUtil config = new GameUtil();

		int arr2[] = {0, 3, 3, 6};

		System.out.println(config.shiftArray(arr));

		System.out.println(config.shiftArray(arr2));
		System.out.println(config.generateRandomTileValue(rand));
		

//		Game g = new Game(5, new GameUtil(), new Random(42));
//		int[] arr3 = {1, 2, 3, 4, 5};
//		System.out.println("Before:");
//		ConsoleUI.printGrid(g);
//		System.out.println(g.getSize());
//		g.updateRowOrColumn(arr3, 2, Direction.DOWN);
//		System.out.println("After:");
//		ConsoleUI.printGrid(g);
//		int[] result = g.copyRowOrColumn(2, Direction.DOWN);
//		System.out.println(Arrays.toString(result)); // expected [1, 2, 3, 4, 5]
		Game g = new Game(4, new GameUtil(), new Random(42));
		int[][] test =
		{
		{0, 2, 3, 1},
		{0, 1, 3, 2},
		{0, 2, 3, 0},
		{0, 1, 2, 0}
		};
		for (int row = 0; row < test.length; row += 1)
		{
		for (int col = 0; col < test[0].length; col += 1)
		{
		g.setCell(row, col, test[row][col]);
		}
		}
		System.out.println("Before: ");
		ConsoleUI.printGrid(g);
		g.shiftGrid(Direction.DOWN);
		System.out.println("After: ");
		ConsoleUI.printGrid(g);
		
		GameUtil util = new GameUtil();
		int[] test2 = {1, 2, 0, 1};			
		util.shiftArray(test2);
		System.out.println(Arrays.toString(test2)); // expected [3, 0 , 1, 0]
		
	
		
	}

}

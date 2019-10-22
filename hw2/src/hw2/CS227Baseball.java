package hw2;
/**
 * A code which simulates a simple version of baseball using simple rules provided in the assignment
 * @author Ahmad M. Nazar
 * COM S 227 HW 2
 */

public class CS227Baseball
{
	/**
	 * constants that are common in the simplified game of baseball
	 */
	public static final int BALL = 0;

	public static final int STRIKE = 1;

	public static final int POP_FLY = 2;

	public static final int MAX_STRIKES = 3;

	public static final int MAX_BALLS = 4;

	public static final int MAX_OUTS = 3;

	/**
	 * value for the start inning which is defined as true in the constructor
	 */
	private boolean topInning;

	/**
	 * value that will take given number of innings
	 */
	private int numberInnings;

	/**
	 * value that starts the inning number at 1
	 */
	private int inning = 1;

	/**
	 * value for total balls
	 */
	private int ball;

	/**
	 * value for total strikes
	 */
	private int strike;

	/**
	 * value for total outs
	 */
	private int out;


	/**
	 * creates an array of boolean type which constitutes first base to home base where base[0] is first base
	 * and so on
	 */
	private boolean[] bases = new boolean[4];

	/**
	 * boolean value which states the game is over when it is true and still playing when otherwise
	 * initialized to false otherwise game would end before it began
	 */
	private boolean isOver = false;

	/**
	 * value for scores for team 0 and team1 respectively initialized to 0
	 */
	private int score0 = 0;
	private int score1 = 0;

	/**
	 * constructor which starts the game of baseball with the top inning being true
	 * which means team0 starts by default
	 * all the bases are set to false and not occupied unless stated otherwise by continued method calls
	 * @param givenInnings
	 * given innings is the number of innings that will be played in the game
	 */
	public CS227Baseball(int givenInnings)
	{
		topInning = true;
		numberInnings = givenInnings;
		for(int i = 0; i < 4; i++)
		{
			bases[i] = false;
		}
	}

	/**
	   * Returns a multi-line string representation of the current game state.
	   * The format is:  
	   * <pre>
	   *     o - o    Inning:1 (T)
	   *     |   |    Score:0-0
	   *     o - H    Balls:0 Strikes:0 Outs:0
	   * </pre>
	   * The 'T' after the inning number indicates it's the top of the inning, 
	   * and a 'B' would indicate the bottom.
	   * @return
	   *   multi-line string representation of current game state
	   */
	public String toDisplayString()
	{
		String firstChar = (playerOnFirst() ? "X" : "o");
		String secondChar = (playerOnSecond() ? "X" : "o");
		String thirdChar = (playerOnThird() ? "X" : "o");
		String topOrBottom = (isTop() ? "T" : "B");
		String firstLine = String.format("%s - %s    Inning:%d (%s)\n", secondChar, firstChar, getInning(), topOrBottom);
		String secondLine = String.format("|   |    Score:%d-%d\n", getScore(true), getScore(false));
		String thirdLine = String.format("%s - H    Balls:%d Strikes:%d Outs:%d\n", thirdChar, getBalls(), getStrikes(), getOuts());
		return firstLine + secondLine + thirdLine;   
	}

	/**
	 * Returns a one-line string representation of the current game state.
	 * The format is:
	 * <pre>
	 *    ooo Inning:1 (T) Score:0-0 Balls:0 Strikes:0 Outs:0
	 * </pre>
	 * The first three characters represent the players on base as returned by 
	 * the <code>getBases()</code> method. The 'T' after the inning number indicates 
	 * it's the top of the inning, and a 'B' would indicate the bottom.
	 * @return
	 *   one-line string representation of the game state
	 */
	public String toString() 
	{
		String bases = getBases();
		String topOrBottom = (isTop() ? "T" : "B");
		String fmt = "%s Inning:%d (%s) Score:%d-%d Balls:%d Strikes:%d Outs:%d";
		return String.format(fmt, bases, getInning(), topOrBottom, getScore(true), getScore(false), getBalls(),
				getStrikes(), getOuts());
	}

	/**
	 * checks whether the game is over
	 * @return
	 * returns true when the inning number reaches the given number of innings and false otherwise
	 */
	public boolean isOver()
	{
		return isOver;
	}
	/**
	 * method which updates the number of balls and strikes according to different arguments
	 * @param outcome
	 * if the number of balls is equal to the number of max balls and number of strikes is less than
	 * max number of strikes then it will advance the player in a single "Walk"
	 * and if the number of strikes is equal to max number of strikes then the team is switched and
	 * strikes, balls and outs are reset to 0
	 * if a pop fly or strike argument, then number of outs is updated by incrementing by 1
	 * if number of outs is 3 then innings will return false which will switch teams (in the isTop method)
	 * this will also increment the inning of team1 is in the current inning and max outs is reached
	 * if the innings exceed the maximum inning number, it will subtract 1 from the exceeded and end the game
	 */
	public void pitch(int outcome)
	{
		if(!isOver)
		{
			if(outcome == BALL)
			{
				ball = BALL + 1;
			}

			if(ball == MAX_BALLS && strike != 3)
			{
				advanceRunners(true);
			}

			if(outcome == STRIKE)
			{
				strike = strike + 1;
			}

			if(strike == 3)
			{
				out = out + 1;
				ball = 0;
				strike = 0;
			}

			if(outcome == POP_FLY)
			{
				out = out + 1;
				ball = 0;
				strike = 0;
			}

			if(out == MAX_OUTS)
			{
				ball = 0;
				strike = 0;
				out = 0;
				if (!topInning) 
				{
					inning = inning + 1;
				}
				topInning = false;
				for(int i = 0; i < 4; i++)
				{
					bases[i] = false;
				}
			}
			if(inning > numberInnings)
			{
				inning = inning - 1;
				isOver = true;
			}
		}
	}

	/**
	 * method which starts with strikes = 0 and passes the argument of true to advance runners which indicates
	 * there should be a player starting at first and advancing
	 * method also includes a for loop which only allows one player to advance bases as opposed to all of them
	 * @param numBases
	 * numBases is used for basesRan where it iterates as mentioned above
	 */
	public void pitchWithHit (int numBases)
	{
		if(!isOver)
		{
			strike = 0;
			advanceRunners(true);
			for(int i = 0; i < numBases - 1; i++)
			{
				advanceRunners(false);
			}
		}
	}

	/**
	 * method which takes the outcome of a pitch with a hit and out
	 * resets the strike to 0 since it is an out and advances runners based on given number of bases
	 * same argument for pitchWithHit, only one player advances as opposed to all of them which 
	 * is why the for loop only allows for one player to advance
	 * if the base fielded is home base, home base is set to false and the score is subtracted by 1
	 * if the base fielded is any other, then the base prior to said fielded is considered false and 
	 * an out is taken incremented, this is done by the second for loop
	 * if the maximum number of outs is reached then it will reset strikes, outs and ball as well as
	 * increment the inning and switch teams which also resets all bases
	 * if the innings exceed the maximum inning number, it will subtract 1 from the exceeded and end the game
	 * @param numBases
	 * number of bases which runner is advancing to, and others, numBases - 1 to have only one player advance
	 * @param whichBaseFielded
	 * value for which base out of the 4 gets fielded which gives different outcomes for home or other three
	 */
	public void pitchWithHitAndOut(int numBases, int whichBaseFielded)
	{
		if(isOver == false)
		{
			strike = 0;
			advanceRunners(true);
			for(int i = 0; i < numBases - 1; i++)
			{
				advanceRunners(false);
			}
			if(bases[3] && whichBaseFielded == 4)
			{
				out = out + 1;
				bases[3] = false;

				if(topInning)
				{
					score0 = score0 - 1;
				}
				else
				{
					score1 = score1 - 1;
				}
			}
			else if (bases[whichBaseFielded - 1]) 
			{
				out = out + 1;
				bases[whichBaseFielded - 1] = false;
			}
		}
		if(out == MAX_OUTS)
		{
			ball = 0;
			out = 0;
			strike = 0;
			if(!topInning)
			{
				inning = inning + 1;
			}
			topInning = false;
			for(int i = 0; i < 4; i++)
			{
				bases[i] = false;
			}
		}
		if(inning > numberInnings)
		{
			inning = inning - 1;
			isOver = true;
		}
	}
	/**
	 *only works when isOver is false
	 *method which advances the players on concurrent bases and depends on whether they are occupied
	 *if first base is occupied then a player will advance to second and the argument for first turns false
	 *if second base is occupied then a player will advance to third and the argument for second turns false
	 *if third base is occupied then a player will advance to base thereby making third false
	 *and adds a point for the corresponding team depending on the argument for which half of the inning it is
	 *@param newPlayerOnFirst
	 *if newPlayerOnFirst is true then it will place a player on first base
	 */
	public void advanceRunners(boolean newPlayerOnFirst)
	{
		if(isOver == false)
		{
			//this will make sure home base is not occupied so a score is not added immediately
			if(bases[3])
			{
				bases[3] = false;
			}

			/**
			 * this for loop checks whether there are players on first, second or third base
			 * if there is a player on any of those bases, it will change the said base to false
			 * and change the following base to true
			 */
			for (int i = 2; i >= 0; i--) 
			{
				if (bases[i] == true) 
				{
					bases[i] = false;
					bases[i + 1] = true;
				}
			}
			if(newPlayerOnFirst)
			{
				bases[0]= true;
			}
			if(bases[3])
			{
				if(topInning)
				{
					score0 = score0 + 1;
				}
				else
				{
					score1 = score1 + 1;
				}
			}
		}
	}

	/**
	 * checks whether there is a player on first
	 * @return
	 * returns true if there is a player on first, false otherwise
	 */
	public boolean playerOnFirst()
	{
		return bases[0];
	}

	/**
	 * checks whether there is a player on second
	 * @return
	 * returns true if there is a player on second, false otherwise
	 */
	public boolean playerOnSecond()
	{
		return bases[1];
	}

	/**
	 * checks whether there is a player on third
	 * @return
	 * returns true if there is a player on third, false otherwise
	 */
	public boolean playerOnThird()
	{
		return bases[2];
	}
	/**
	 * gets the value of the inning
	 * @return
	 * returns the value of the current inning
	 */
	public int getInning()
	{
		return inning;
	}
	/**
	 * checks which half of the inning it is
	 * @return
	 * returns true if it is at the top of the inning and false otherwise
	 * this also shows which team is playing for other aspects of the code
	 */
	public boolean isTop()
	{
		return topInning;
	}

	/**
	 *checks the value of the current score according to the boolean value
	 *true for team0 and false for team1
	 * @return
	 * returns the current score if true for team 0, and team 1 if false
	 */
	public int getScore(boolean team0)
	{
		if(team0)
		{
			return score0;
		}
		else
		{
			return score1;
		}
	}

	/**
	 * gets the number of current outs
	 * @return\
	 * returns the total number of current outs for a given team
	 */
	public int getOuts()
	{
		return out;
	}

	/**
	 * checks the total number of strikes
	 * @return
	 * returns the total number of strikes for a given team
	 */
	public int getStrikes()
	{
		return strike;
	}

	/**
	 * checks the number of current balls
	 * @return
	 * returns the number of current balls for a given team
	 */
	public int getBalls()
	{
		return ball;
	}

	/**
	 * checks whether there are players currently on the bases using basic markings of "X" and "O"
	 * @return
	 * returns a one line string with "X" on concurrent bases if they are occupied and "O" otherwise
	 */
	public String getBases()
	{
		return (playerOnFirst() ? "X" : "o") + (playerOnSecond() ? "X" : "o") + (playerOnThird() ? "X" : "o");
	}
}



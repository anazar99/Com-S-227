package hw2;

public class SimpleTest {

	public static void main(String[] args) 
	{

		CS227Baseball game = new CS227Baseball(3);
		
		game.pitch(CS227Baseball.STRIKE);
		System.out.println(game.toDisplayString()); // should be 1 strike
		game.pitch(CS227Baseball.BALL);
		System.out.println(game.toDisplayString()); // 1 ball, 1 strike
		game.pitch(CS227Baseball.STRIKE);
		System.out.println(game.toDisplayString()); // 1 ball, 2 strikes
		game.pitch(CS227Baseball.STRIKE);
		System.out.println(game.toDisplayString()); // 0 balls, 0 strikes, 1 out
		game.pitch(CS227Baseball.POP_FLY);
		System.out.println(game.toDisplayString()); // 0 balls, 0 strikes, 2 outs
		
		game.pitchWithHit(4);
		System.out.println(game.toDisplayString());
		
		game.pitchWithHit(3);
		System.out.println(game.toDisplayString());
		
		game.pitchWithHit(1);
		System.out.println(game.toDisplayString());
		
		System.out.println(game.getBases());
	}

}


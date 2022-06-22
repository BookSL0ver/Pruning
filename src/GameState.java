import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameState {
    private int size;            // The number of stones
    private boolean[] stones;    // Game state: true for available stones, false for taken ones
    private int lastMove;        // The last move

    /**
     * Class constructor specifying the number of stones.
     */
    public GameState(int size) {

        this.size = size;

        //  For convenience, we use 1-based index, and set 0 to be unavailable
        this.stones = new boolean[this.size + 1];
        this.stones[0] = false;

        // Set default state of stones to available
        for (int i = 1; i <= this.size; ++i) {
            this.stones[i] = true;
        }

        // Set the last move be -1
        this.lastMove = -1;
    }

    /**
     * Copy constructor
     */
    public GameState(GameState other) {
        this.size = other.size;
        this.stones = Arrays.copyOf(other.stones, other.stones.length);
        this.lastMove = other.lastMove;
    }


    /**
     * This method is used to compute a list of legal moves
     *
     * @return This is the list of state's moves
     */
    public List<Integer> getMoves() {
        // TODO Add your code here
    	// look at lastMove, figure out what numbers could be factors or multiples, figure out
    	// what is already out
    	List<Integer> ret = new ArrayList<Integer>();
    	if (lastMove == -1)
    	{
    		//first move
    		int high = stones.length / 2;
    		for (int i = 1; i < high; i+=2)
    		{
    			ret.add(i);
    		}
    	}
    	else
    	{
    		//factors and multiples
    		//multiples
    		for (int i = stones.length; i > lastMove; i--)
    		{
    			if (i % lastMove == 0 && stones[i - 1])
    			{
    				ret.add(i);
    			}
    		}
    		//factors
    		if (!Helper.isPrime(lastMove))
    		{
    			for (int x = 1; x < lastMove; x++)
    			{
    				if (lastMove % x == 0 && stones[x - 1])
    				{
    					ret.add(x);
    				}
    			}
    		}
    	}
        return ret;
    }


    /**
     * This method is used to generate a list of successors
     * using the getMoves() method
     *
     * @return This is the list of state's successors
     */
    public List<GameState> getSuccessors() {
        return this.getMoves().stream().map(move -> {
            GameState state = new GameState(this);
            state.removeStone(move);
            return state;
        }).collect(Collectors.toList());
    }


    /**
     * This method is used to evaluate a game state based on
     * the given heuristic function
     *
     * @return int This is the static score of given state
     */
    public double evaluate() {
        // TODO Add your code here
    	//figure out whose turn it is
    	int turns = 0;
    	for (int i = 0; i < stones.length; i++)
    	{
    		if (!stones[i])
    		{
    			turns++;
    		}
    	}
    	//figure out score
    	double ret = 0.0;
    	//if no more moves
    	if (this.getMoves().size() == 0)
    	{
    		ret = 1.0;
    	}
    	else
    	{
    		if (stones[0])
    		{
    			ret = 0.0;
    		}
    		else if (lastMove == 1)
    		{
    			if (this.getMoves().size() % 2 == 1)
    			{
    				ret = .5;
    			}
    			else
    			{
    				ret = -.5;
    			}
    		}
    		else if (Helper.isPrime(lastMove))
    		{
    			List<GameState> success = this.getSuccessors();
    			int size = success.size();
    			if (size % 2 == 1)
    			{
    				ret = .7;
    			}
    			else
    			{
    				ret = -.7;
    			}
    		}
    		else
    		{
    			int prime = Helper.getLargestPrimeFactor(lastMove);
    			GameState temp = new GameState(this);
    			temp.removeStone(prime);
    			List<GameState> success = temp.getSuccessors();
    			int size = success.size();
    			if (stones[prime - 1])
    			{
    				size++;
    			}
    			if (size % 2 == 1)
    			{
    				ret = .6;
    			}
    			else
    			{
    				ret = -.6;
    			}
    		}
    	}
    	//max or min
    	if (turns % 2 == 1)
    	{
    		//min
    		return -ret;
    	}
    	else
    	{
    		//max
    		return ret;
    	}
    }

    /**
     * This method is used to take a stone out
     *
     * @param idx Index of the taken stone
     */
    public void removeStone(int idx) {
        this.stones[idx] = false;
        this.lastMove = idx;
    }

    /**
     * These are get/set methods for a stone
     *
     * @param idx Index of the taken stone
     */
    public void setStone(int idx) {
        this.stones[idx] = true;
    }

    public boolean getStone(int idx) {
        return this.stones[idx];
    }

    /**
     * These are get/set methods for lastMove variable
     *
     * @param move Index of the taken stone
     */
    public void setLastMove(int move) {
        this.lastMove = move;
    }

    public int getLastMove() {
        return this.lastMove;
    }

    /**
     * This is get method for game size
     *
     * @return int the number of stones
     */
    public int getSize() {
        return this.size;
    }

}	

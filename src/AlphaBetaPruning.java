import java.util.List;

public class AlphaBetaPruning {


    public AlphaBetaPruning() {
    }

    /**
     * This function will print out the information to the terminal,
     * as specified in the homework description.
     */
    public void printStats() {
        // TODO Add your code here
    	System.out.println("Move: ");
    	System.out.println("Value: ");
    	System.out.println("Number of Nodes Visited: ");
    	System.out.println("Nubmer of Nodes Evaluated: ");
    	System.out.println("Max Depth Reached: ");
    	System.out.println("Avg Effective Branching Factor: ");
    }

    /**
     * This function will start the alpha-beta search
     * @param state This is the current game state
     * @param depth This is the specified search depth
     */
    public void run(GameState state, int depth) {
        // TODO Add your code here
    	if (depth % 2 == 1)
    	{
    		alphabeta(state, depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false);
    	}
    	else 
    	{
    		alphabeta(state, depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true);
    	}
    }

    /**
     * This method is used to implement alpha-beta pruning for both players
     * @param state This is the current game state
     * @param depth Current depth of search
     * @param alpha Current Alpha value
     * @param beta Current Beta value
     * @param maxPlayer True if player is Max Player; Otherwise, false
     * @return int This is the number indicating score of the best next move
     */
    private double alphabeta(GameState state, int depth, double alpha, double beta, boolean maxPlayer) {
        // TODO Add your code here
    	List<Integer> moves = state.getMoves();
    	List<GameState> sucess = state.getSuccessors();
    	if (maxPlayer)
    	{
    		//max
    	}
    	else
    	{
    		//min
    	}
        return 0.0;
    }
}

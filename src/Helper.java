public class Helper {
	
	/** 
    * Class constructor.
    */
	private Helper () {}

	/**
	* This method is used to check if a number is prime or not
	* @param x A positive integer number
	* @return boolean True if x is prime; Otherwise, false
	*/
	public static boolean isPrime(int x) {
		
		// TODO Add your code here
		for (int i = x - 1; i > 0; i--)
		{
			if (new Double(x / i) == (new Double(x) / new Double(i)))
			{
				return false;
			}
		}

		return true;
	}

	/**
	* This method is used to get the largest prime factor 
	* @param x A positive integer number
	* @return int The largest prime factor of x
	*/
	public static int getLargestPrimeFactor(int x) {

    	// TODO Add your code here
		for (int y = x - 1; y > 0; y--)
		{
			if (new Double(x / y) == (new Double(x) / new Double(y)))
			{
				if (isPrime(y))
					return y;
			}
		}

		return -1;

    }
}
package Exceptions;

public class BidsOrderBookEmptyException extends Exception
{
    public BidsOrderBookEmptyException (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
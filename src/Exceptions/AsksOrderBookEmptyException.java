package Exceptions;

public class AsksOrderBookEmptyException extends Exception
{
    public AsksOrderBookEmptyException(String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
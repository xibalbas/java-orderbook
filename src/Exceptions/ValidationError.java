package Exceptions;

public class ValidationError extends Exception
{
    public ValidationError (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
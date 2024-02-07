package SpecialStack;

public class StackEmptyException extends Exception {
    public StackEmptyException(String s) {
        super(s);
    }

    //personalize message
    @Override
    public String toString() {
        return "Message: " + getMessage();
    }

}

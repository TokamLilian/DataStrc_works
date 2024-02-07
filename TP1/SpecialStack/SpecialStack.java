package SpecialStack;

public interface SpecialStack<E>{

    public E getMax() throws StackEmptyException;
}
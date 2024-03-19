package com.tiduswr.game.utils;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CircularLinkedList<E> extends LinkedList<E> {

    private static final long serialVersionUID = 1L;

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E element = super.removeFirst();
        addLast(element);
        return element;
    }
}

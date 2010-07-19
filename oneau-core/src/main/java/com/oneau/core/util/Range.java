package com.oneau.core.util;

import java.io.Serializable;

import static com.oneau.core.util.Utility.isEmpty;
import static com.oneau.core.util.Utility.throwIfNull;
import static java.lang.String.format;
import static java.lang.System.arraycopy;
import static java.util.Arrays.sort;

/**
 * User: ebridges
 * Date: Jul 18, 2010
 */
public class Range<T extends Number> implements Serializable {
    private T left;
    private T right;

    @SuppressWarnings("unchecked")
    public Range(final T ... vals) {
        if(isEmpty(vals)) {
            throw new IllegalArgumentException("Range requires a value for initialization.");
        }

        Object[] valsCopy = new Object[vals.length];
        arraycopy(vals, 0, valsCopy, 0, vals.length);
        sort(valsCopy);

        this.left = throwIfNull("leftValue", (T)valsCopy[0]);
        this.right = throwIfNull("rightValue", (T)valsCopy[valsCopy.length-1]);
    }

    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }

    public boolean contains(Double value) {
        return (left.doubleValue() < value) && (value <= right.doubleValue());
    }

    public boolean contains(Float value) {
        return (left.floatValue() < value) && (value <= right.floatValue());
    }

    public boolean contains(Integer value) {
        return (left.intValue() < value) && (value <= right.intValue());
    }

    public boolean contains(Short value) {
        return (left.shortValue() < value) && (value <= right.shortValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Range range = (Range) o;

        if (!left.equals(range.left)) return false;
        if (!right.equals(range.right)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return format("Range{left=%s, right=%s}",left,right);
    }
}

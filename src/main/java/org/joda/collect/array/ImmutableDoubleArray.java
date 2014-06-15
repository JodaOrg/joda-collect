/*
 *  Copyright 2001-2014 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.collect.array;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Immutable implementation of an array data structure for primitive {@code double}.
 * <p>
 * This class wraps a raw {@code double[]}, providing collection-like behaviour,
 * including {@code equals}, {@code hashCode} and {@code toString}.
 * 
 * @author Stephen Colebourne
 */
public final class ImmutableDoubleArray implements Iterable<Double> {
    // TODO: serializable
    // forEach(DoubleConsumer)
    // doubleStream()

    /**
     * An empty double array.
     */
    public static final ImmutableDoubleArray EMPTY = new ImmutableDoubleArray(new double[0]);

    /**
     * The underlying array of doubles.
     */
    private final double[] array;

    /**
     * Obtains an empty immutable array.
     * 
     * @return the empty immutable array, not null
     */
    public static ImmutableDoubleArray of() {
        return EMPTY;
    }

    /**
     * Obtains an immutable array with a single value.
     * 
     * @param value  the single value
     * @return the empty immutable array, not null
     */
    public static ImmutableDoubleArray of(double value) {
        return new ImmutableDoubleArray(new double[] {value});
    }

    /**
     * Obtains an immutable array with two values.
     * 
     * @param value1  the first value
     * @param value2  the second value
     * @return the empty immutable array, not null
     */
    public static ImmutableDoubleArray of(double value1, double value2) {
        return new ImmutableDoubleArray(new double[] {value1, value2});
    }

    /**
     * Obtains an immutable array with three values.
     * 
     * @param value1  the first value
     * @param value2  the second value
     * @param value3  the third value
     * @return the empty immutable array, not null
     */
    public static ImmutableDoubleArray of(double value1, double value2, double value3) {
        return new ImmutableDoubleArray(new double[] {value1, value2, value3});
    }

    /**
     * Obtains an immutable array with four values.
     * 
     * @param value1  the first value
     * @param value2  the second value
     * @param value3  the third value
     * @param value4  the fourth value
     * @return the empty immutable array, not null
     */
    public static ImmutableDoubleArray of(double value1, double value2, double value3, double value4) {
        return new ImmutableDoubleArray(new double[] {value1, value2, value3, value4});
    }

    /**
     * Obtains an immutable array with five values.
     * 
     * @param value1  the first value
     * @param value2  the second value
     * @param value3  the third value
     * @param value4  the fourth value
     * @param value5  the fifth value
     * @return the empty immutable array, not null
     */
    public static ImmutableDoubleArray of(
                    double value1, double value2, double value3, double value4, double value5) {
        return new ImmutableDoubleArray(new double[] {value1, value2, value3, value4, value5});
    }

    /**
     * Obtains an immutable array with six values.
     * 
     * @param value1  the first value
     * @param value2  the second value
     * @param value3  the third value
     * @param value4  the fourth value
     * @param value5  the fifth value
     * @param value6  the sixth value
     * @return the empty immutable array, not null
     */
    public static ImmutableDoubleArray of(
                    double value1, double value2, double value3, double value4,
                    double value5, double value6) {
        return new ImmutableDoubleArray(new double[] {value1, value2, value3, value4, value5, value6});
    }

    /**
     * Obtains an immutable array with seven values.
     * 
     * @param value1  the first value
     * @param value2  the second value
     * @param value3  the third value
     * @param value4  the fourth value
     * @param value5  the fifth value
     * @param value6  the sixth value
     * @param value7  the seventh value
     * @return the empty immutable array, not null
     */
    public static ImmutableDoubleArray of(
                    double value1, double value2, double value3, double value4,
                    double value5, double value6, double value7) {
        return new ImmutableDoubleArray(new double[] {value1, value2, value3, value4, value5, value6, value7});
    }

    /**
     * Obtains an immutable array with eight values.
     * 
     * @param value1  the first value
     * @param value2  the second value
     * @param value3  the third value
     * @param value4  the fourth value
     * @param value5  the fifth value
     * @param value6  the sixth value
     * @param value7  the seventh value
     * @param value8  the eighth value
     * @return the empty immutable array, not null
     */
    public static ImmutableDoubleArray of(
                    double value1, double value2, double value3, double value4,
                    double value5, double value6, double value7, double value8) {
        return new ImmutableDoubleArray(new double[] {value1, value2, value3, value4, value5, value6, value7, value8});
    }

    //-----------------------------------------------------------------------
    /**
     * Obtains an immutable array by copying an array.
     * <p>
     * This method is safe, because the input array is copied.
     * 
     * @param array  the array to copy, no nulls, not null
     * @return the immutable array, not null
     */
    public static ImmutableDoubleArray copyOf(Double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        if (array.length == 0) {
            return EMPTY;
        }
        double[] primitive = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            primitive[i] = array[i].doubleValue();
        }
        return new ImmutableDoubleArray(primitive);
    }

    /**
     * Obtains an immutable array by copying values from a collection.
     * <p>
     * This method is safe, because the input array is copied.
     * 
     * @param coll  the collection to copy, no nulls, not null
     * @return the immutable array, not null
     */
    public static ImmutableDoubleArray copyOf(Collection<Double> coll) {
        if (coll == null) {
            throw new IllegalArgumentException("Collection must not be null");
        }
        Iterator<Double> it = coll.iterator();
        if (it.hasNext() == false) {
            return EMPTY;
        }
        double[] primitive = new double[coll.size()];
        int i = 0;
        for (Double value : coll) {
            primitive[i++] = value.doubleValue();
        }
        return new ImmutableDoubleArray(primitive);
    }

    //-----------------------------------------------------------------------
    /**
     * Obtains an immutable array by copying an array.
     * <p>
     * This method is safe, because the input array is copied.
     * The {@link #wrap(double[])} method is unsafe because the array is directly assigned.
     * 
     * @param array  the array to copy, not null
     * @return the immutable array, not null
     */
    public static ImmutableDoubleArray copyOf(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        if (array.length == 0) {
            return EMPTY;
        }
        return new ImmutableDoubleArray(array);
    }

    /**
     * Obtains an immutable array by copying part of an array.
     * <p>
     * This method is safe, because the input array is copied.
     * 
     * @param array  the array to copy, not null
     * @param fromIndex  the offset from the start of the array
     * @return the immutable array, not null
     * @throws IndexOutOfBoundsException if the offset is invalid
     */
    public static ImmutableDoubleArray copyOf(double[] array, int fromIndex) {
        return copyOf(array, fromIndex, array.length);
    }

    /**
     * Obtains an immutable array by copying part of an array.
     * <p>
     * This method is safe, because the input array is copied.
     * 
     * @param array  the array to copy, not null
     * @param fromIndexInclusive  the start index of the input array to copy from
     * @param toIndexExclusive  the end index of the input array to copy to
     * @return the immutable array, not null
     * @throws IndexOutOfBoundsException if the offset is invalid
     */
    public static ImmutableDoubleArray copyOf(double[] array, int fromIndexInclusive, int toIndexExclusive) {
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        if ((toIndexExclusive - fromIndexInclusive) == 0) {
            return EMPTY;
        }
        return new ImmutableDoubleArray(Arrays.copyOfRange(array, fromIndexInclusive, toIndexExclusive));
    }

    /**
     * Obtains an immutable array by wrapping an array.
     * <p>
     * This method is inherently unsafe as it relies on good behaviour by callers.
     * Callers must never make any changes to the passed in array after calling this method.
     * Doing so would violate the immutability of this class.
     * 
     * @param array  the array to assign, not null
     * @return the immutable array, not null
     */
    public static ImmutableDoubleArray wrapOf(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        if (array.length == 0) {
            return EMPTY;
        }
        return new ImmutableDoubleArray(array);
    }

    //-----------------------------------------------------------------------
    /**
     * Restricted constructor.
     */
    private ImmutableDoubleArray(double[] array) {
        this.array = array;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the size of this array.
     * <p>
     * This is equivalent to {@code array.length}.
     * 
     * @return the array size, zero or greater
     */
    public int size() {
        return array.length;
    }

    /**
     * Checks if this array is empty.
     * <p>
     * This is equivalent to {@code array.length == 0}.
     * 
     * @return the array size, zero or greater
     */
    public boolean isEmpty() {
        return array.length == 0;
    }

    /**
     * Gets the value at the specified index in this array.
     * <p>
     * This is equivalent to {@code array[index]}.
     * 
     * @param index  the zero-based index to retrieve
     * @return the value at the index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public double get(int index) {
        return array[index];
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this array contains the specified value.
     * <p>
     * The value is checked using {@code Double.doubleToLongBits} in order to match {@code equals}.
     * This also allow this method to be used to find any occurrences of NaN.
     * 
     * @param value  the value to find
     * @return true if the value is contained in this array
     */
    public boolean contains(double value) {
        long bits = Double.doubleToLongBits(value);
        for (int i = 0; i < array.length; i++) {
            if (Double.doubleToLongBits(value) == bits) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find the index of the first occurrence of the specified value.
     * <p>
     * The value is checked using {@code Double.doubleToLongBits} in order to match {@code equals}.
     * This also allow this method to be used to find any occurrences of NaN.
     * 
     * @param value  the value to find
     * @return the index of the value, -1 if not found
     */
    public int indexOf(double value) {
        long bits = Double.doubleToLongBits(value);
        for (int i = 0; i < array.length; i++) {
            if (Double.doubleToLongBits(value) == bits) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find the index of the first occurrence of the specified value.
     * <p>
     * The value is checked using {@code Double.doubleToLongBits} in order to match {@code equals}.
     * This also allow this method to be used to find any occurrences of NaN.
     * 
     * @param value  the value to find
     * @return the index of the value, -1 if not found
     */
    public int lastIndexOf(double value) {
        long bits = Double.doubleToLongBits(value);
        for (int i = array.length - 1; i >= 0; i--) {
            if (Double.doubleToLongBits(value) == bits) {
                return i;
            }
        }
        return -1;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns an array holding the values from the specified index onwards.
     * 
     * @param fromIndexInclusive  the start index of the array to copy from
     * @return an array instance with the specified bounds, not null
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public ImmutableDoubleArray subArray(int fromIndexInclusive) {
        return subArray(fromIndexInclusive, array.length);
    }

    /**
     * Returns an array holding the values between the specified from and to indices.
     * 
     * @param fromIndexInclusive  the start index of the array to copy from
     * @param toIndexExclusive  the end index of the array to copy to
     * @return an array instance with the specified bounds, not null
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public ImmutableDoubleArray subArray(int fromIndexInclusive, int toIndexExclusive) {
        double[] sub = Arrays.copyOfRange(array, fromIndexInclusive, toIndexExclusive);
        return new ImmutableDoubleArray(sub);  // TODO better
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a sorted copy of this array.
     * <p>
     * This uses {@link Arrays#sort(double[])}.
     * 
     * @return a sorted copy of this array, not null
     */
    public ImmutableDoubleArray sorted() {
        double[] clone = array.clone();
        Arrays.sort(clone);
        return new ImmutableDoubleArray(clone);
    }

    /**
     * Returns the minimum value held in the array.
     * <p>
     * If the array is empty, then an exception is thrown.
     * If the array contains NaN, then the result is NaN.
     * 
     * @return a sorted copy of this array, not null
     * @throws IllegalStateException if the array is empty
     */
    public double min() {
        if (array.length == 0) {
            throw new IllegalStateException("Unable to find minimum of an empty array");
        }
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }

    /**
     * Returns the minimum value held in the array.
     * <p>
     * If the array is empty, then an exception is thrown.
     * If the array contains NaN, then the result is NaN.
     * 
     * @return a sorted copy of this array, not null
     * @throws IllegalStateException if the array is empty
     */
    public double max() {
        if (array.length == 0) {
            throw new IllegalStateException("Unable to find maximum of an empty array");
        }
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        return max;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns a copy of the underlying primitive array.
     * 
     * @return a copy of the underlying primitive array, not null
     */
    public double[] toArray() {
        return array.clone();
    }

    /**
     * Copies this array into the specified primitive array.
     * <p>
     * The specified array must be at least as large as this array.
     * If it is larger, then the remainder of the array will be untouched.
     * 
     * @param destination  the destination array to copy into, not null
     * @return the input array, not null
     */
    public double[] toArray(double[] destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination array must not be null");
        }
        if (destination.length < array.length) {
            throw new IllegalArgumentException("Specified array is not large enough");
        }
        System.arraycopy(array, 0, destination, 0, array.length);
        return destination;
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<Double>() {
            private int index;
            @Override
            public boolean hasNext() {
                return index < array.length;
            }
            @Override
            public Double next() {
                return array[index++];
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException("Unable to remove from ImmutableDoubleArray");
            }
        };
    }

    //-----------------------------------------------------------------------
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ImmutableDoubleArray) {
            ImmutableDoubleArray other = (ImmutableDoubleArray) obj;
            return Arrays.equals(array, other.array);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

}

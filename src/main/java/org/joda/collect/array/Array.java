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

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableCollection;

/**
 * A data structure representing a single dimensional array keyed by {@code int}.
 * <p>
 * An array has a fixed number of elements and is a close match to JVM level arrays.
 * 
 * @param <V> the type of the value
 * @author Stephen Colebourne
 */
public interface Array<V> extends Iterable<V> {

    /**
     * Checks if the array is empty.
     * 
     * @return true if empty
     */
    boolean isEmpty();

    /**
     * Gets the number of elements that are present.
     * 
     * @return the size of the set of elements
     */
    int size();

    /**
     * Checks if a value is present at the specified row-column.
     * <p>
     * If either index does not exist, false is returned.
     * 
     * @param index  the index
     * @return true if there is a value at the index
     */
    boolean contains(int index);

    /**
     * Checks if the specified value is contained in the array.
     * 
     * @param valueToFind  the value to find, null returns false
     * @return true if the array contains the value
     */
    boolean containsValue(Object valueToFind);

    /**
     * Gets the value at the specified row-column.
     * <p>
     * If either index does not exist, null is returned.
     * 
     * @param index  the index
     * @return the value at the row-column, null if not found
     */
    V get(int index);

    //-----------------------------------------------------------------------
    /**
     * Checks if this array equals another array.
     * <p>
     * Two arrays are equal if they are the same size and contain the same set of cells.
     * 
     * @param obj  the object to compare to, null returns false
     * @return true if equal
     */
    @Override
    boolean equals(Object obj);

    /**
     * Gets a suitable hash code.
     * <p>
     * The hash code is {@code rowCount ^ Integer.rotateLeft(columnCount, 16) ^ cells.hashCode()}.
     * 
     * @return the hash code
     */
    @Override
    int hashCode();

    //-----------------------------------------------------------------------
    /**
     * Clears the array.
     * <p>
     * The array will be empty after calling this method.
     * 
     * @throws UnsupportedOperationException if read-only
     */
    void clear();

    /**
     * Puts a value into this array.
     * <p>
     * The value at the specified row-column is set.
     * Any previous value at the row-column is replaced.
     * <p>
     * If either index does not exist, {@code IndexOutOfBoundsException} is thrown.
     * 
     * @param row  the row, zero or greater
     * @param column  the column, zero or greater
     * @param value  the value to put into the array, not null
     * @throws IndexOutOfBoundsException if either index does not exist
     * @throws UnsupportedOperationException if read-only
     */
    void put(int row, int column, V value);

    /**
     * Puts all cells from a array into this array.
     * <p>
     * The value at the specified row-column is set.
     * Any previous value at the row-column is replaced.
     * 
     * @param array  the array to put into this array, not null
     * @throws IndexOutOfBoundsException if a cell has an invalid index
     * @throws UnsupportedOperationException if read-only
     */
    void putAll(Array<? extends V> array);

    /**
     * Removes the value at the specified row-column.
     * <p>
     * If either index does not exist, no action occurs and false is returned.
     * 
     * @param row  the row
     * @param column  the column
     * @return true if the array is altered
     * @throws UnsupportedOperationException if read-only
     */
    boolean remove(int row, int column);

}

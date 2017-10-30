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
package org.joda.collect.grid;

import static org.junit.Assert.assertEquals;

import org.joda.collect.grid.Grid.Cell;
import org.junit.Test;

/**
 * Test ImmutableCell.
 */
public class TestImmutableCell {

    @Test
    public void test_of() {
        Cell<String> test = ImmutableCell.of(1, 2, "Hello");
        assertEquals(1, test.getRow());
        assertEquals(2, test.getColumn());
        assertEquals("Hello", test.getValue());
        assertEquals("(1,2)=Hello", test.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_of_negativeRow() {
        ImmutableCell.of(-1, 2, "Hello");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_of_negativeColumn() {
        ImmutableCell.of(1, -2, "Hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_of_nullValue() {
        ImmutableCell.of(1, 2, null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_copyOf() {
        Cell<String> base = new MockCell(1, 2, "Hello");
        Cell<String> test = ImmutableCell.copyOf(base);
        assertEquals(1, test.getRow());
        assertEquals(2, test.getColumn());
        assertEquals("Hello", test.getValue());
        assertEquals("(1,2)=Hello", test.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_nullCell() {
        ImmutableCell.copyOf(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_negativeRow() {
        ImmutableCell.copyOf(new MockCell(-1, 2, null));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_negativeColumn() {
        ImmutableCell.copyOf(new MockCell(1, -2, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_nullValue() {
        ImmutableCell.copyOf(new MockCell(1, 2, null));
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_equalsHashCode() {
        Cell<String> test = ImmutableCell.of(1, 2, "Hello");
        assertEquals(true, test.equals(test));
        assertEquals(true, test.equals(ImmutableCell.of(1, 2, "Hello")));
        assertEquals(false, test.equals(ImmutableCell.of(0, 2, "Hello")));
        assertEquals(false, test.equals(ImmutableCell.of(1, 3, "Hello")));
        assertEquals(false, test.equals(ImmutableCell.of(1, 2, "Hell")));
        assertEquals(false, test.equals(null));
        assertEquals(false, test.equals(""));
        
        assertEquals("Hello".hashCode() ^ 1 ^ Integer.rotateLeft(2, 16), test.hashCode());
    }

}

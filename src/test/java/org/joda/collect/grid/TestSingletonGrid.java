/*
 *  Copyright 2014-present Stephen Colebourne
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

import org.junit.Test;

/**
 * Test SingletonGrid.
 */
public class TestSingletonGrid extends AbstractTestGrid {

    @Test
    public void test_factory_simple() {
        ImmutableGrid<String> test = ImmutableGrid.of("Hello");
        assertEquals(1, test.rowCount());
        assertEquals(1, test.columnCount());
        checkGrid(test, 0, 0, "Hello");
        assertEquals("[1x1:(0,0)=Hello]", test.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_factory_simple_nullValue() {
        ImmutableGrid.of(null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_factory_full() {
        ImmutableGrid<String> test = ImmutableGrid.of(2, 2, 0, 1, "Hello");
        assertEquals(2, test.rowCount());
        assertEquals(2, test.columnCount());
        checkGrid(test, 0, 1, "Hello");
        assertEquals("[2x2:(0,1)=Hello]", test.toString());
    }

    //-----------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void test_factory_full_negativeRowCount() {
        ImmutableGrid.of(-2, 3, 0, 1, "World");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_factory_full_negativeColumnCount() {
        ImmutableGrid.of(2, -3, 0, 1, "World");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_factory_full_negativeRow() {
        ImmutableGrid.of(2, 2, -1, 2, "Hello");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_factory_full_negativeColumn() {
        ImmutableGrid.of(2, 2, 1, -2, "Hello");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_factory_full_rowEqualRowCount() {
        ImmutableGrid.of(1, 1, 1, 0, "World");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_factory_full_columnEqualColumnCount() {
        ImmutableGrid.of(1, 1, 0, 1, "World");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_factory_full_nullValue() {
        ImmutableGrid.of(2, 2, 1, 2, null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_containsValue_Object() {
        ImmutableGrid<String> test = create();
        assertEquals(true, test.containsValue("Hello"));
        assertEquals(false, test.containsValue("Spicy"));
        assertEquals(false, test.containsValue(""));
        assertEquals(false, test.containsValue(null));
        assertEquals(false, test.containsValue(Integer.valueOf(6)));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_equalsHashCode() {
        ImmutableGrid<String> test = create();
        assertEquals(true, test.equals(test));
        assertEquals(true, test.equals(create()));
        
        SparseGrid<String> hash = SparseGrid.create(2, 3);
        hash.put(0, 1, "Hello");
        assertEquals(true, test.equals(hash));
        
        SparseGrid<String> colCountDiff = SparseGrid.create(2, 4);
        colCountDiff.put(0, 1, "Hello");
        assertEquals(false, test.equals(colCountDiff));
        
        SparseGrid<String> valueDiff = SparseGrid.create(2, 3);
        valueDiff.put(0, 1, "World");
        assertEquals(false, test.equals(valueDiff));
        
        assertEquals(false, test.equals(null));
        assertEquals(false, test.equals(""));
        
        assertEquals(2 ^ Integer.rotateLeft(3, 16) ^ test.cells().hashCode(), test.hashCode());
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("deprecation")
    @Test(expected = UnsupportedOperationException.class)
    public void test_immutable_clear() {
        ImmutableGrid<String> test = create();
        test.clear();
    }

    @SuppressWarnings("deprecation")
    @Test(expected = UnsupportedOperationException.class)
    public void test_immutable_put() {
        ImmutableGrid<String> test = create();
        test.put(0, 0, "Hello");
    }

    @SuppressWarnings("deprecation")
    @Test(expected = UnsupportedOperationException.class)
    public void test_immutable_putAll() {
        ImmutableGrid<String> test = create();
        test.putAll(ImmutableGrid.<String>of());
    }

    @SuppressWarnings("deprecation")
    @Test(expected = UnsupportedOperationException.class)
    public void test_immutable_remove() {
        ImmutableGrid<String> test = create();
        test.remove(0, 0);
    }

    private ImmutableGrid<String> create() {
        return ImmutableGrid.of(2, 3, 0, 1, "Hello");
    }

}

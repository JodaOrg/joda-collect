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
 * Test EmptyGrid.
 */
public class TestEmptyGrid extends AbstractTestGrid {

    @Test
    public void test_factory() {
        ImmutableGrid<String> test = ImmutableGrid.of();
        assertEquals(0, test.rowCount());
        assertEquals(0, test.columnCount());
        checkGrid(test);
        assertEquals("[0x0:]", test.toString());
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_containsValue_Object() {
        ImmutableGrid<String> test = ImmutableGrid.of();
        assertEquals(false, test.containsValue("Hello"));
        assertEquals(false, test.containsValue(""));
        assertEquals(false, test.containsValue(null));
        assertEquals(false, test.containsValue(Integer.valueOf(6)));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_equalsHashCode_0x0() {
        ImmutableGrid<String> test = ImmutableGrid.of();
        assertEquals(true, test.equals(test));
        assertEquals(true, test.equals(ImmutableGrid.of()));
        assertEquals(true, test.equals(SparseGrid.create(0, 0)));
        assertEquals(false, test.equals(SparseGrid.create(1, 1)));
        assertEquals(true, test.equals(DenseGrid.create(0, 0)));
        assertEquals(false, test.equals(DenseGrid.create(1, 1)));
        assertEquals(false, test.equals(null));
        assertEquals(false, test.equals(""));
        
        assertEquals(test.cells().hashCode(), test.hashCode());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_equalsHashCode_1x2() {
        ImmutableGrid<String> test = ImmutableGrid.of(1, 2);
        assertEquals(true, test.equals(test));
        assertEquals(true, test.equals(ImmutableGrid.of(1, 2)));
        assertEquals(true, test.equals(SparseGrid.create(1, 2)));
        assertEquals(false, test.equals(SparseGrid.create(1, 1)));
        assertEquals(true, test.equals(DenseGrid.create(1, 2)));
        assertEquals(false, test.equals(DenseGrid.create(1, 1)));
        assertEquals(false, test.equals(null));
        assertEquals(false, test.equals(""));
        
        assertEquals(1 ^ Integer.rotateLeft(2, 16) ^ test.cells().hashCode(), test.hashCode());
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("deprecation")
    @Test(expected = UnsupportedOperationException.class)
    public void test_immutable_clear() {
        ImmutableGrid<String> test = ImmutableGrid.of();
        test.clear();
    }

    @SuppressWarnings("deprecation")
    @Test(expected = UnsupportedOperationException.class)
    public void test_immutable_put() {
        ImmutableGrid<String> test = ImmutableGrid.of();
        test.put(0, 0, "Hello");
    }

    @SuppressWarnings("deprecation")
    @Test(expected = UnsupportedOperationException.class)
    public void test_immutable_putAll() {
        ImmutableGrid<String> test = ImmutableGrid.of();
        test.putAll(ImmutableGrid.<String>of());
    }

    @SuppressWarnings("deprecation")
    @Test(expected = UnsupportedOperationException.class)
    public void test_immutable_remove() {
        ImmutableGrid<String> test = ImmutableGrid.of();
        test.remove(0, 0);
    }

}

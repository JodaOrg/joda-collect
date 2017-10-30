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
 * Test DenseImmutableGrid.
 */
public class TestDenseImmutableGrid extends AbstractTestImmutableGrid {

    @Override
    protected ImmutableGrid<String> createNonEmpty() {
        DenseGrid<String> hash = DenseGrid.create(2, 2);
        hash.put(0, 0, "Hello");
        hash.put(0, 1, "World");
        return ImmutableGrid.copyOf(hash);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_factory_copyOf_Grid_fromDense() {
        DenseGrid<String> hash = DenseGrid.create(2, 2);
        hash.put(0, 0, "Hello");
        hash.put(1, 0, "World");
        ImmutableGrid<String> test = ImmutableGrid.copyOf(hash);
        assertEquals(2, test.rowCount());
        assertEquals(2, test.columnCount());
        checkGrid(test, 0, 0, "Hello", 1, 0, "World");
        assertEquals("[2x2:(0,0)=Hello, (1,0)=World]", test.toString());
    }

    @Test
    public void test_factory_copyOf_Grid_fromSparse() {
        SparseGrid<String> hash = SparseGrid.create(2, 2);
        hash.put(0, 0, "Hello");
        hash.put(0, 1, "World");
        ImmutableGrid<String> test = ImmutableGrid.copyOf(hash);
        assertEquals(2, test.rowCount());
        assertEquals(2, test.columnCount());
        checkGrid(test, 0, 0, "Hello", 0, 1, "World");
        assertEquals("[2x2:(0,0)=Hello, (0,1)=World]", test.toString());
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_containsValue_Object() {
        SparseGrid<String> hash = SparseGrid.create(2, 2);
        hash.put(0, 0, "Hello");
        hash.put(0, 1, "World");
        ImmutableGrid<String> test = ImmutableGrid.copyOf(hash);
        assertEquals(true, test.containsValue("Hello"));
        assertEquals(true, test.containsValue("World"));
        assertEquals(false, test.containsValue("Spicy"));
        assertEquals(false, test.containsValue(""));
        assertEquals(false, test.containsValue(null));
        assertEquals(false, test.containsValue(Integer.valueOf(6)));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void test_equalsHashCode() {
        SparseGrid<String> hash = SparseGrid.create(2, 2);
        hash.put(0, 0, "Hello");
        hash.put(0, 1, "World");
        ImmutableGrid<String> test = ImmutableGrid.copyOf(hash);
        assertEquals(true, test.equals(test));
        assertEquals(true, test.equals(ImmutableGrid.copyOf(hash)));
        assertEquals(true, test.equals(hash));
        assertEquals(false, test.equals(null));
        assertEquals(false, test.equals(""));
        
        assertEquals(2 ^ Integer.rotateLeft(2, 16) ^ test.cells().hashCode(), test.hashCode());
    }

}

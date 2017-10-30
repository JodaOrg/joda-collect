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

import org.junit.Test;

/**
 * Test DenseGrid.
 */
public class TestDenseGrid extends AbstractTestMutableGrid {

    @Override
    protected Grid<String> create3x3() {
        return DenseGrid.create(3, 3);
    }

    @Override
    protected Grid<String> create(int rowCount, int columnCount) {
        return DenseGrid.create(rowCount, columnCount);
    }

    @Override
    protected Grid<String> create(Grid<String> grid) {
        return DenseGrid.create(grid);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_create_Grid_fromDenseImutable() {
        SparseGrid<String> base = SparseGrid.create(2, 2);
        base.put(0, 0, "Hello");
        base.put(1, 0, "World");
        ImmutableGrid<String> imm = ImmutableGrid.copyOf(base);
        DenseGrid<String> test = DenseGrid.create(imm);
        checkGrid(test, 0, 0, "Hello", 1, 0, "World");
    }

    @Test
    public void test_create_array2D() {
        String[][] array = new String[][] {
                {"Hello", "World"},
                {null},
        };
        DenseGrid<String> test = DenseGrid.create(array);
        checkGrid(test, 0, 0, "Hello", 0, 1, "World");
    }

    @Test
    public void test_create_array2D_empty() {
        String[][] array = new String[][] {};
        DenseGrid<String> test = DenseGrid.create(array);
        checkGrid(test);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_array2D_null() {
        DenseGrid.create((String[][]) null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_rowCount_columnCount() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(3, test.rowCount());
        assertEquals(3, test.columnCount());
    }

    @Test
    public void test_rowCount_columnCount_empty() {
        Grid<String> test = create3x3();
        assertEquals(3, test.rowCount());
        assertEquals(3, test.columnCount());
    }

    //-----------------------------------------------------------------------
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_put_rowTooBig() {
        DenseGrid<String> test = DenseGrid.create(2, 2);
        test.put(3, 1, "Hello");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_put_columnTooBig() {
        DenseGrid<String> test = DenseGrid.create(2, 2);
        test.put(1, 3, "Hello");
    }

}

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
import static org.junit.Assert.assertSame;

import org.joda.collect.grid.Grid.Cell;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * Test ImmutableGrid factories.
 */
public class TestImmutableGrid extends AbstractTestGrid {

    @Test
    public void test_copyOf_Grid_alreadyImmutable() {
        SparseGrid<String> hash = SparseGrid.create(2, 3);
        hash.put(0, 0, "Hello");
        hash.put(0, 1, "World");
        ImmutableGrid<String> base = ImmutableGrid.copyOf(hash);
        ImmutableGrid<String> test = ImmutableGrid.copyOf(base);
        assertSame(base, test);
    }

    @Test
    public void test_copyOf_Grid_size0() {
        SparseGrid<String> hash = SparseGrid.create(2, 3);
        ImmutableGrid<String> test = ImmutableGrid.copyOf(hash);
        assertEquals(2, test.rowCount());
        assertEquals(3, test.columnCount());
        checkGrid(test);
        assertEquals(true, test instanceof EmptyGrid);
    }

    @Test
    public void test_copyOf_Grid_size1() {
        SparseGrid<String> hash = SparseGrid.create(2, 3);
        hash.put(0, 1, "Hello");
        ImmutableGrid<String> test = ImmutableGrid.copyOf(hash);
        assertEquals(2, test.rowCount());
        assertEquals(3, test.columnCount());
        checkGrid(test, 0, 1, "Hello");
        assertEquals(true, test instanceof SingletonGrid);
    }

    @Test
    public void test_copyOf_Grid_userGridImplementationSize1() {
        ImmutableGrid<String> test = ImmutableGrid.copyOf(new MockSingletonGrid(2, 3, 0, 1, "Hello"));
        assertEquals(2, test.rowCount());
        assertEquals(3, test.columnCount());
        checkGrid(test, 0, 1, "Hello");
        assertEquals(true, test instanceof SingletonGrid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_Grid_badGrid_negativeRowCount() {
        ImmutableGrid.copyOf(new MockSingletonGrid(-2, 3, 0, 1, "World"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_Grid_badGrid_negativeColumnCount() {
        ImmutableGrid.copyOf(new MockSingletonGrid(2, -3, 0, 1, "World"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_Grid_badGrid_negativeRow() {
        ImmutableGrid.copyOf(new MockSingletonGrid(2, 2, -1, 2, "Hello"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_Grid_badGrid_negativeColumn() {
        ImmutableGrid.copyOf(new MockSingletonGrid(2, 2, 1, -2, "Hello"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_Grid_badGrid_rowEqualRowCount() {
        ImmutableGrid.copyOf(new MockSingletonGrid(1, 1, 1, 0, "World"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_Grid_badGrid_columnEqualColumnCount() {
        ImmutableGrid.copyOf(new MockSingletonGrid(1, 1, 0, 1, "World"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_Grid_badGrid_nullValue() {
        ImmutableGrid.copyOf(new MockSingletonGrid(2, 2, 1, 2, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_Grid_null() {
        ImmutableGrid.copyOf((Grid<String>) null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_copyOf_intIntCell() {
        ImmutableGrid<String> test = ImmutableGrid.copyOf(2, 3, ImmutableCell.of(0, 1, "Hello"));
        assertEquals(2, test.rowCount());
        assertEquals(3, test.columnCount());
        checkGrid(test, 0, 1, "Hello");
        assertEquals(true, test instanceof SingletonGrid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_intIntCell_negativeRowCount() {
        ImmutableGrid.copyOf(-2, 3, new MockCell(0, 1, "World"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_intIntCell_negativeColumnCount() {
        ImmutableGrid.copyOf(2, -3, new MockCell(0, 1, "World"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_intIntCell_negativeRow() {
        ImmutableGrid.copyOf(2, 2, new MockCell(-1, 2, "Hello"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_intIntCell_negativeColumn() {
        ImmutableGrid.copyOf(2, 2, new MockCell(1, -2, "Hello"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_intIntCell_rowEqualRowCount() {
        ImmutableGrid.copyOf(1, 1, new MockCell(1, 0, "World"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_intIntCell_columnEqualColumnCount() {
        ImmutableGrid.copyOf(1, 1, new MockCell(0, 1, "World"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_intIntCell_nullValue() {
        ImmutableGrid.copyOf(2, 2, new MockCell(0, 1, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_intIntCell_null() {
        ImmutableGrid.copyOf(2, 2, (Cell<String>) null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_copyOf_intIntCells() {
        ImmutableGrid<String> test = ImmutableGrid.copyOf(2, 3, ImmutableList.of(ImmutableCell.of(0, 1, "Hello"), ImmutableCell.of(0, 2, "World")));
        assertEquals(2, test.rowCount());
        assertEquals(3, test.columnCount());
        checkGrid(test, 0, 1, "Hello", 0, 2, "World");
    }

    @Test
    public void test_copyOf_intIntCells_empty() {
        ImmutableGrid<String> test = ImmutableGrid.copyOf(2, 3, ImmutableList.<Cell<String>>of());
        assertEquals(2, test.rowCount());
        assertEquals(3, test.columnCount());
        checkGrid(test);
        assertEquals(true, test instanceof EmptyGrid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_intIntCells_negativeRowCount() {
        ImmutableGrid.copyOf(-2, 3, ImmutableList.of(new MockCell(0, 1, "World")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_intIntCells_negativeColumnCount() {
        ImmutableGrid.copyOf(2, -3, ImmutableList.of(new MockCell(0, 1, "World")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_intIntCells_negativeRow() {
        ImmutableGrid.copyOf(2, 2, ImmutableList.of(new MockCell(-1, 2, "Hello")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_intIntCells_negativeColumn() {
        ImmutableGrid.copyOf(2, 2, ImmutableList.of(new MockCell(1, -2, "Hello")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_intIntCells_rowEqualRowCount() {
        ImmutableGrid.copyOf(1, 1, ImmutableList.of(new MockCell(1, 0, "World")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOf_intIntCells_columnEqualColumnCount() {
        ImmutableGrid.copyOf(1, 1, ImmutableList.of(new MockCell(0, 1, "World")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_intIntCells_nullValue() {
        ImmutableGrid.copyOf(2, 2, ImmutableList.of(new MockCell(0, 1, null)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOf_intIntCells_null() {
        ImmutableGrid.copyOf(2, 2, (Iterable<Cell<String>>) null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_copyOfDeriveCounts_Cells_size0() {
        SparseGrid<String> hash = SparseGrid.create(2, 2);
        ImmutableGrid<String> test = ImmutableGrid.copyOfDeriveCounts(hash.cells());
        assertEquals(0, test.rowCount());
        assertEquals(0, test.columnCount());
        checkGrid(test);
        assertEquals(true, test instanceof EmptyGrid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOfDeriveCounts_null() {
        ImmutableGrid.copyOfDeriveCounts((Iterable<Cell<String>>) null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOfDeriveCounts_badGrid_negativeRow() {
        ImmutableSet<MockCell> set = ImmutableSet.of(new MockCell(-1, 2, "Hello"));
        ImmutableGrid.copyOfDeriveCounts(set);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_copyOfDeriveCounts_badGrid_negativeColumn() {
        ImmutableSet<MockCell> set = ImmutableSet.of(new MockCell(1, -2, "Hello"));
        ImmutableGrid.copyOfDeriveCounts(set);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_copyOfDeriveCounts_badGrid_nullValue() {
        ImmutableSet<MockCell> set = ImmutableSet.of(new MockCell(1, 2, null));
        ImmutableGrid.copyOfDeriveCounts(set);
    }

}

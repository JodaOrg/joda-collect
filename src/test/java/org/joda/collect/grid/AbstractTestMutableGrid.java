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
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.joda.collect.grid.Grid.Cell;
import org.junit.Test;

/**
 * Test abstract Grid.
 */
public abstract class AbstractTestMutableGrid extends AbstractTestGrid {

    @Test
    public void test_create_intInt() {
        Grid<String> test = create(2, 3);
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(2, test.rowCount());
        assertEquals(3, test.columnCount());
        checkGrid(test, 0, 0, "Hello", 0, 1, "World");
        assertEquals("[2x3:(0,0)=Hello, (0,1)=World]", test.toString());
    }

    @Test
    public void test_create_intInt_empty() {
        Grid<String> test = create(2, 3);
        assertEquals(2, test.rowCount());
        assertEquals(3, test.columnCount());
        checkGrid(test);
        assertEquals("[2x3:]", test.toString());
    }

    //-----------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void test_create_intInt_negativeRowCount() {
        create(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_intInt_negativeColumnCount() {
        create(1, -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_intInt_negativeRowColumnCount() {
        create(-1, -2);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_create_Grid() {
        Grid<String> test = create(new MockSingletonGrid(2, 3, 0, 1, "World"));
        assertEquals(2, test.rowCount());
        assertEquals(3, test.columnCount());
        checkGrid(test, 0, 1, "World");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_create_intIntGrid_negativeRow() {
        create(new MockSingletonGrid(2, 3, -1, 1, "World"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_create_intIntGrid_negativeColumn() {
        create(new MockSingletonGrid(2, 3, 0, -1, "World"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_intIntGrid_nullValue() {
        create(new MockSingletonGrid(2, 3, 0, 1, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_intIntGrid_negativeRowCount() {
        create(new MockSingletonGrid(-2, 3, 0, 1, "World"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_intIntGrid_negativeColumnCount() {
        create(new MockSingletonGrid(2, -3, 0, 1, "World"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_create_intIntGrid_null() {
        create((Grid<String>) null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_containsValue_Object() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
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
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(true, test.equals(test));
        assertEquals(true, test.equals(SparseGrid.create(test)));
        assertEquals(true, test.equals(DenseGrid.create(test)));
        assertEquals(true, test.equals(ImmutableGrid.copyOf(test)));
        assertEquals(false, test.equals(null));
        assertEquals(false, test.equals(""));
        
        assertEquals(3 ^ Integer.rotateLeft(3, 16) ^ test.cells().hashCode(), test.hashCode());
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_clear() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(2, test.size());
        assertEquals(2, test.cells().size());
        assertEquals(2, test.values().size());
        
        test.clear();
        
        assertEquals(0, test.size());
        assertEquals(0, test.cells().size());
        assertEquals(0, test.values().size());
    }

    @Test
    public void test_clear_empty() {
        Grid<String> test = create3x3();
        assertEquals(0, test.size());
        assertEquals(0, test.cells().size());
        assertEquals(0, test.values().size());
        
        test.clear();
        
        assertEquals(0, test.size());
        assertEquals(0, test.cells().size());
        assertEquals(0, test.values().size());
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_put_first() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        checkGrid(test, 0, 0, "Hello");
    }

    @Test
    public void test_put_second() {
        Grid<String> test = create3x3();
        test.put(0, 1, "World");
        checkGrid(test, 0, 1, "World");
        test.put(0, 0, "Hello");
        checkGrid(test, 0, 0, "Hello", 0, 1, "World");
    }

    @Test
    public void test_put_replace() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        checkGrid(test, 0, 0, "Hello");
        test.put(0, 0, "Update");
        checkGrid(test, 0, 0, "Update");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_put_negativeRow() {
        Grid<String> test = create3x3();
        test.put(-1, 2, "Hello");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_put_negativeColumn() {
        Grid<String> test = create3x3();
        test.put(1, -2, "Hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_put_nullValue() {
        Grid<String> test = create3x3();
        test.put(1, 2, null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_putAll_emptyPlusNonEmpty() {
        Grid<String> test = create3x3();
        test.putAll(ImmutableGrid.of(2, 2, 0, 1, "Hello"));
        checkGrid(test, 0, 1, "Hello");
    }

    @Test
    public void test_putAll_nonEmptyPlusEmpty() {
        Grid<String> test = create3x3();
        test.put(0, 1, "Hello");
        checkGrid(test, 0, 1, "Hello");
        test.putAll(ImmutableGrid.<String>of());
        checkGrid(test, 0, 1, "Hello");
    }

    @Test
    public void test_putAll_emptyPlusEmpty() {
        Grid<String> test = create3x3();
        test.putAll(ImmutableGrid.<String>of());
        checkGrid(test);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_putAll_null() {
        Grid<String> test = create3x3();
        test.putAll((Grid<String>) null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_remove_last() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        checkGrid(test, 0, 0, "Hello", 0, 1, "World");
        assertEquals(true, test.remove(0, 1));
        checkGrid(test, 0, 0, "Hello");
    }

    @Test
    public void test_remove_first() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        checkGrid(test, 0, 0, "Hello", 0, 1, "World");
        assertEquals(true, test.remove(0, 0));
        checkGrid(test, 0, 1, "World");
    }

    @Test
    public void test_remove_notPresent_empty() {
        Grid<String> test = create3x3();
        checkGrid(test);
        assertEquals(false, test.remove(1, 2));
        checkGrid(test);
    }

    @Test
    public void test_remove_notPresent_nonEmpty() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(2, 2, "World");
        checkGrid(test, 0, 0, "Hello", 2, 2, "World");
        assertEquals(false, test.remove(1, 1));
        assertEquals(false, test.remove(1, 2));
        assertEquals(false, test.remove(2, 1));
        checkGrid(test, 0, 0, "Hello", 2, 2, "World");
    }

    @Test
    public void test_remove_largeIndex() {
        DenseGrid<String> test = DenseGrid.create(2, 2);
        assertEquals(false, test.remove(999, 1000));
        checkGrid(test);
    }

    @Test
    public void test_remove_invalidIndex() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        checkGrid(test, 0, 0, "Hello");
        assertEquals(false, test.remove(-1, -1));
        assertEquals(false, test.remove(1, -1));
        assertEquals(false, test.remove(-1, 1));
        checkGrid(test, 0, 0, "Hello");
    }

    @Test
    public void test_remove_empty() {
        Grid<String> test = create3x3();
        assertEquals(false, test.remove(0, 0));
        checkGrid(test);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_cells() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        test.put(1, 1, "Space");
        assertEquals(3, test.cells().size());
        Iterator<Cell<String>> it = test.cells().iterator();
        assertEquals(true, it.hasNext());
        assertEquals(ImmutableCell.of(0, 0, "Hello"), it.next());
        assertEquals(true, it.hasNext());
        assertEquals(ImmutableCell.of(0, 1, "World"), it.next());
        assertEquals(true, it.hasNext());
        assertEquals(ImmutableCell.of(1, 1, "Space"), it.next());
        assertEquals(false, it.hasNext());
        try {
            it.next();
            fail();
        } catch (NoSuchElementException ex) {
            // expected
        }
    }

    @Test
    public void test_cells_contains() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(true, test.cells().contains(ImmutableCell.of(0, 0, "Hello")));
        assertEquals(true, test.cells().contains(ImmutableCell.of(0, 1, "World")));
        assertEquals(false, test.cells().contains(ImmutableCell.of(1, 1, "")));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test(expected = ClassCastException.class)
    public void test_cells_contains_nonCell() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        test.cells().contains("NonCell");
    }

    @Test(expected = NullPointerException.class)
    public void test_cells_contains_null() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        test.cells().contains(null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_cells_iterator_remove_first() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(2, test.cells().size());
        Iterator<Cell<String>> it = test.cells().iterator();
        assertEquals(true, it.hasNext());
        assertEquals(ImmutableCell.of(0, 0, "Hello"), it.next());
        assertEquals(true, it.hasNext());
        it.remove();
        assertEquals(true, it.hasNext());
        assertEquals(ImmutableCell.of(0, 1, "World"), it.next());
        assertEquals(false, it.hasNext());
        
        assertEquals(1, test.size());
        assertEquals(null, test.get(0, 0));
        assertEquals("World", test.get(0, 1));
        assertEquals(null, test.get(1, 0));
        assertEquals(null, test.get(1, 1));
    }

    @Test
    public void test_cells_iterator_remove_second() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(1, 1, "World");
        assertEquals(2, test.cells().size());
        Iterator<Cell<String>> it = test.cells().iterator();
        assertEquals(true, it.hasNext());
        assertEquals(ImmutableCell.of(0, 0, "Hello"), it.next());
        assertEquals(true, it.hasNext());
        assertEquals(ImmutableCell.of(1, 1, "World"), it.next());
        assertEquals(false, it.hasNext());
        it.remove();
        assertEquals(false, it.hasNext());
        assertEquals(1, test.size());
        assertEquals("Hello", test.get(0, 0));
        assertEquals(null, test.get(0, 1));
        assertEquals(null, test.get(1, 0));
        assertEquals(null, test.get(1, 1));
    }

    @Test(expected = IllegalStateException.class)
    public void test_cells_iterator_removeBeforeNext() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(1, 1, "World");
        test.cells().iterator().remove();
    }

    @Test(expected = IllegalStateException.class)
    public void test_cells_iterator_removeTwice() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(1, 1, "World");
        Iterator<Cell<String>> it = test.cells().iterator();
        it.next();
        it.remove();
        it.remove();
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_cells_add() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        test.cells().add(ImmutableCell.of(1, 2, "Extra"));
        
        assertEquals(3, test.size());
        assertEquals(true, test.contains(0, 0));
        assertEquals(true, test.contains(0, 1));
        assertEquals(true, test.contains(1, 2));
        assertEquals("Hello", test.get(0, 0));
        assertEquals("World", test.get(0, 1));
        assertEquals("Extra", test.get(1, 2));
        assertEquals(3, test.cells().size());
        assertEquals(3, test.values().size());
    }

    @Test
    public void test_cells_add_replace() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        test.cells().add(ImmutableCell.of(0, 1, "World"));
        
        assertEquals(2, test.size());
        assertEquals(true, test.contains(0, 0));
        assertEquals(true, test.contains(0, 1));
        assertEquals("Hello", test.get(0, 0));
        assertEquals("World", test.get(0, 1));
        assertEquals(2, test.cells().size());
        assertEquals(2, test.values().size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_cells_add_negativeRow() {
        Grid<String> test = create3x3();
        test.cells().add(new MockCell(-1, 2, "Hello"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_cells_add_negativeColumn() {
        Grid<String> test = create3x3();
        test.cells().add(new MockCell(1, -2, "Hello"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_cells_add_nullValue() {
        Grid<String> test = create3x3();
        test.cells().add(new MockCell(1, 2, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_cells_add_null() {
        Grid<String> test = create3x3();
        test.cells().add(null);
    }

    //-----------------------------------------------------------------------
    @Test
    @SuppressWarnings("unchecked")
    public void test_cells_addAll() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        test.cells().addAll(Arrays.asList(ImmutableCell.of(1, 2, "Extra"), ImmutableCell.of(2, 2, "Lots")));
        
        assertEquals(4, test.size());
        assertEquals(true, test.contains(0, 0));
        assertEquals(true, test.contains(0, 1));
        assertEquals(true, test.contains(1, 2));
        assertEquals(true, test.contains(2, 2));
        assertEquals("Hello", test.get(0, 0));
        assertEquals("World", test.get(0, 1));
        assertEquals("Extra", test.get(1, 2));
        assertEquals("Lots", test.get(2, 2));
        assertEquals(4, test.cells().size());
        assertEquals(4, test.values().size());
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_cells_remove_first() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(2, test.cells().size());
        assertEquals(true, test.cells().remove(ImmutableCell.of(0, 0, "Hello")));
        
        assertEquals(1, test.size());
        assertEquals(null, test.get(0, 0));
        assertEquals("World", test.get(0, 1));
    }

    @Test
    public void test_cells_remove_second() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(2, test.cells().size());
        assertEquals(true, test.cells().remove(ImmutableCell.of(0, 1, "World")));
        
        assertEquals(1, test.size());
        assertEquals("Hello", test.get(0, 0));
        assertEquals(null, test.get(0, 1));
    }

    @Test
    public void test_cells_remove_goodIndicesBadValue() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(2, test.cells().size());
        assertEquals(true, test.cells().remove(ImmutableCell.of(0, 1, "Rubbish")));
        
        assertEquals(1, test.size());
        assertEquals("Hello", test.get(0, 0));
        assertEquals(null, test.get(0, 1));
    }

    @Test
    public void test_cells_remove_badCell_negativeRow() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        assertEquals(2, test.cells().size());
        assertEquals(false, test.cells().remove(new MockCell(-1, 1, "Hello")));
        
        assertEquals(2, test.size());
        assertEquals("Hello", test.get(0, 0));
        assertEquals("World", test.get(0, 1));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test(expected = ClassCastException.class)
    public void test_cells_remove_nonCell() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        test.cells().remove("NonCell");
    }

    @Test(expected = NullPointerException.class)
    public void test_cells_remove_null() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        test.cells().remove(null);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_cells_clear() {
        Grid<String> test = create3x3();
        test.put(0, 0, "Hello");
        test.put(0, 1, "World");
        test.cells().clear();
        
        assertEquals(0, test.size());
        assertEquals(0, test.cells().size());
        assertEquals(0, test.values().size());
    }

    @Test
    public void test_cells_clear_empty() {
        Grid<String> test = create3x3();
        assertEquals(0, test.size());
        assertEquals(0, test.cells().size());
        assertEquals(0, test.values().size());
        
        test.cells().clear();
        
        assertEquals(0, test.size());
        assertEquals(0, test.cells().size());
        assertEquals(0, test.values().size());
    }

    //-----------------------------------------------------------------------
    protected abstract Grid<String> create3x3();

    protected abstract Grid<String> create(int rowCount, int columnCount);

    protected abstract Grid<String> create(Grid<String> grid);

}

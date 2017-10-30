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

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.joda.collect.grid.Grid.Cell;

/**
 * Test abstract Grid.
 */
public abstract class AbstractTestGrid {

    private static final Object DUMMY = new Object() {};

    protected <R> void checkGrid(Grid<R> test) {
        assertEquals(0, test.size());
        assertEquals(true, test.isEmpty());
        assertEquals(test.rowCount() == 0 && test.columnCount() == 0, test.isFull());
        for (int i = -1; i < test.rowCount(); i++) {
            for (int j = -1; j < test.columnCount(); j++) {
                if (i < 0 || j < 0 || i >= test.rowCount() || j >= test.columnCount()) {
                    assertEquals(false, test.exists(i, j));
                    assertEquals(false, test.contains(i, j));
                    assertEquals(null, test.get(i, j));
                    assertEquals(null, test.cell(i, j));
                } else {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(false, test.contains(i, j));
                    assertEquals(null, test.get(i, j));
                    assertEquals(null, test.cell(i, j));
                    assertEquals(null, test.row(i).get(j));
                    assertEquals(null, test.rows().get(i).get(j));
                    assertEquals(null, test.column(j).get(i));
                    assertEquals(null, test.columns().get(j).get(i));
                    assertEquals(test.columnCount(), test.row(i).size());
                    assertEquals(test.rowCount(), test.rows().size());
                    assertEquals(test.columnCount(), test.rows().get(i).size());
                    assertEquals(test.rowCount(), test.column(j).size());
                    assertEquals(test.columnCount(), test.columns().size());
                    assertEquals(test.rowCount(), test.columns().get(j).size());
                }
            }
        }
        assertEquals(false, test.containsValue(null));
        assertEquals(false, test.containsValue(DUMMY));
        
        assertEquals(0, test.cells().size());
        Iterator<Cell<R>> cellIt = test.cells().iterator();
        assertIteratorEnd(cellIt);
        
        assertEquals(0, test.values().size());
        Iterator<R> valueIt = test.values().iterator();
        assertIteratorEnd(valueIt);
    }

    protected <R> void checkGrid(Grid<R> test, int row1, int column1, R value1) {
        assertEquals(1, test.size());
        assertEquals(false, test.isEmpty());
        assertEquals(test.rowCount() * test.columnCount() == 1, test.isFull());
        for (int i = -1; i < test.rowCount(); i++) {
            for (int j = -1; j < test.columnCount(); j++) {
                if (i < 0 || j < 0 || i >= test.rowCount() || j >= test.columnCount()) {
                    assertEquals(false, test.exists(i, j));
                    assertEquals(false, test.contains(i, j));
                    assertEquals(null, test.get(i, j));
                    assertEquals(null, test.cell(i, j));
                } else if (i == row1 && j == column1) {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(true, test.contains(i, j));
                    assertEquals(value1, test.get(i, j));
                    assertEquals(ImmutableCell.of(i, j, value1), test.cell(i, j));
                    assertEquals(value1, test.row(i).get(j));
                    assertEquals(value1, test.rows().get(i).get(j));
                    assertEquals(value1, test.column(j).get(i));
                    assertEquals(value1, test.columns().get(j).get(i));
                } else {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(false, test.contains(i, j));
                    assertEquals(null, test.get(i, j));
                    assertEquals(null, test.cell(i, j));
                    assertEquals(null, test.row(i).get(j));
                    assertEquals(null, test.rows().get(i).get(j));
                    assertEquals(null, test.column(j).get(i));
                    assertEquals(null, test.columns().get(j).get(i));
                    assertEquals(test.columnCount(), test.row(i).size());
                    assertEquals(test.rowCount(), test.rows().size());
                    assertEquals(test.columnCount(), test.rows().get(i).size());
                    assertEquals(test.rowCount(), test.column(j).size());
                    assertEquals(test.columnCount(), test.columns().size());
                    assertEquals(test.rowCount(), test.columns().get(j).size());
                }
            }
        }
        assertEquals(true, test.containsValue(value1));
        assertEquals(false, test.containsValue(null));
        assertEquals(false, test.containsValue(DUMMY));
        
        assertEquals(1, test.cells().size());
        Iterator<Cell<R>> cellIt = test.cells().iterator();
        assertEquals(true, cellIt.hasNext());
        Cell<R> cell = cellIt.next();
        assertEquals(row1, cell.getRow());
        assertEquals(column1, cell.getColumn());
        assertEquals(value1, cell.getValue());
        assertIteratorEnd(cellIt);
        
        assertEquals(1, test.values().size());
        Iterator<R> valueIt = test.values().iterator();
        assertEquals(true, valueIt.hasNext());
        assertEquals(value1, valueIt.next());
        assertIteratorEnd(valueIt);
    }

    protected <R> void checkGrid(Grid<R> test, int row1, int column1, R value1, int row2, int column2, R value2) {
        assertEquals(2, test.size());
        assertEquals(false, test.isEmpty());
        assertEquals(test.rowCount() * test.columnCount() == 2, test.isFull());
        for (int i = -1; i < test.rowCount(); i++) {
            for (int j = -1; j < test.columnCount(); j++) {
                if (i < 0 || j < 0 || i >= test.rowCount() || j >= test.columnCount()) {
                    assertEquals(false, test.exists(i, j));
                    assertEquals(false, test.contains(i, j));
                    assertEquals(null, test.get(i, j));
                    assertEquals(null, test.cell(i, j));
                } else if (i == row1 && j == column1) {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(true, test.contains(i, j));
                    assertEquals(value1, test.get(i, j));
                    assertEquals(ImmutableCell.of(i, j, value1), test.cell(i, j));
                    assertEquals(value1, test.row(i).get(j));
                    assertEquals(value1, test.rows().get(i).get(j));
                    assertEquals(value1, test.column(j).get(i));
                    assertEquals(value1, test.columns().get(j).get(i));
                } else if (i == row2 && j == column2) {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(true, test.contains(i, j));
                    assertEquals(value2, test.get(i, j));
                    assertEquals(ImmutableCell.of(i, j, value2), test.cell(i, j));
                    assertEquals(value2, test.row(i).get(j));
                    assertEquals(value2, test.rows().get(i).get(j));
                    assertEquals(value2, test.column(j).get(i));
                    assertEquals(value2, test.columns().get(j).get(i));
                } else {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(false, test.contains(i, j));
                    assertEquals(null, test.get(i, j));
                    assertEquals(null, test.cell(i, j));
                    assertEquals(null, test.row(i).get(j));
                    assertEquals(null, test.rows().get(i).get(j));
                    assertEquals(null, test.column(j).get(i));
                    assertEquals(null, test.columns().get(j).get(i));
                    assertEquals(test.columnCount(), test.row(i).size());
                    assertEquals(test.rowCount(), test.rows().size());
                    assertEquals(test.columnCount(), test.rows().get(i).size());
                    assertEquals(test.rowCount(), test.column(j).size());
                    assertEquals(test.columnCount(), test.columns().size());
                    assertEquals(test.rowCount(), test.columns().get(j).size());
                }
            }
        }
        assertEquals(true, test.containsValue(value1));
        assertEquals(true, test.containsValue(value2));
        assertEquals(false, test.containsValue(null));
        assertEquals(false, test.containsValue(DUMMY));
        
        assertEquals(2, test.cells().size());
        Iterator<Cell<R>> cellIt = test.cells().iterator();
        assertEquals(true, cellIt.hasNext());
        Cell<R> cell = cellIt.next();
        assertEquals(row1, cell.getRow());
        assertEquals(column1, cell.getColumn());
        assertEquals(value1, cell.getValue());
        assertEquals(true, cellIt.hasNext());
        cell = cellIt.next();
        assertEquals(row2, cell.getRow());
        assertEquals(column2, cell.getColumn());
        assertEquals(value2, cell.getValue());
        assertIteratorEnd(cellIt);
        
        assertEquals(2, test.values().size());
        Iterator<R> valueIt = test.values().iterator();
        assertEquals(true, valueIt.hasNext());
        assertEquals(value1, valueIt.next());
        assertEquals(true, valueIt.hasNext());
        assertEquals(value2, valueIt.next());
        assertIteratorEnd(valueIt);
    }

    protected <R> void checkGrid(Grid<R> test, int row1, int column1, R value1, int row2, int column2, R value2, int row3, int column3, R value3) {
        assertEquals(3, test.size());
        assertEquals(false, test.isEmpty());
        assertEquals(test.rowCount() * test.columnCount() == 3, test.isFull());
        for (int i = -1; i < test.rowCount(); i++) {
            for (int j = -1; j < test.columnCount(); j++) {
                if (i < 0 || j < 0 || i >= test.rowCount() || j >= test.columnCount()) {
                    assertEquals(false, test.exists(i, j));
                    assertEquals(false, test.contains(i, j));
                    assertEquals(null, test.get(i, j));
                    assertEquals(null, test.cell(i, j));
                } else if (i == row1 && j == column1) {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(true, test.contains(i, j));
                    assertEquals(value1, test.get(i, j));
                    assertEquals(ImmutableCell.of(i, j, value1), test.cell(i, j));
                    assertEquals(value1, test.row(i).get(j));
                    assertEquals(value1, test.rows().get(i).get(j));
                    assertEquals(value1, test.column(j).get(i));
                    assertEquals(value1, test.columns().get(j).get(i));
                } else if (i == row2 && j == column2) {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(true, test.contains(i, j));
                    assertEquals(value2, test.get(i, j));
                    assertEquals(ImmutableCell.of(i, j, value2), test.cell(i, j));
                    assertEquals(value2, test.row(i).get(j));
                    assertEquals(value2, test.rows().get(i).get(j));
                    assertEquals(value2, test.column(j).get(i));
                    assertEquals(value2, test.columns().get(j).get(i));
                } else if (i == row3 && j == column3) {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(true, test.contains(i, j));
                    assertEquals(value3, test.get(i, j));
                    assertEquals(ImmutableCell.of(i, j, value3), test.cell(i, j));
                    assertEquals(value3, test.row(i).get(j));
                    assertEquals(value3, test.rows().get(i).get(j));
                    assertEquals(value3, test.column(j).get(i));
                    assertEquals(value3, test.columns().get(j).get(i));
                } else {
                    assertEquals(true, test.exists(i, j));
                    assertEquals(false, test.contains(i, j));
                    assertEquals(null, test.get(i, j));
                    assertEquals(null, test.cell(i, j));
                    assertEquals(null, test.row(i).get(j));
                    assertEquals(null, test.rows().get(i).get(j));
                    assertEquals(null, test.column(j).get(i));
                    assertEquals(null, test.columns().get(j).get(i));
                    assertEquals(test.columnCount(), test.row(i).size());
                    assertEquals(test.rowCount(), test.rows().size());
                    assertEquals(test.columnCount(), test.rows().get(i).size());
                    assertEquals(test.rowCount(), test.column(j).size());
                    assertEquals(test.columnCount(), test.columns().size());
                    assertEquals(test.rowCount(), test.columns().get(j).size());
                }
            }
        }
        assertEquals(true, test.containsValue(value1));
        assertEquals(true, test.containsValue(value2));
        assertEquals(true, test.containsValue(value3));
        assertEquals(false, test.containsValue(null));
        assertEquals(false, test.containsValue(DUMMY));
        
        assertEquals(3, test.cells().size());
        Iterator<Cell<R>> cellIt = test.cells().iterator();
        assertEquals(true, cellIt.hasNext());
        Cell<R> cell = cellIt.next();
        assertEquals(row1, cell.getRow());
        assertEquals(column1, cell.getColumn());
        assertEquals(value1, cell.getValue());
        assertEquals(true, cellIt.hasNext());
        cell = cellIt.next();
        assertEquals(row2, cell.getRow());
        assertEquals(column2, cell.getColumn());
        assertEquals(value2, cell.getValue());
        assertEquals(true, cellIt.hasNext());
        cell = cellIt.next();
        assertEquals(row3, cell.getRow());
        assertEquals(1, cell.getColumn());
        assertEquals(value3, cell.getValue());
        assertIteratorEnd(cellIt);
        
        assertEquals(3, test.values().size());
        Iterator<R> valueIt = test.values().iterator();
        assertEquals(true, valueIt.hasNext());
        assertEquals(value1, valueIt.next());
        assertEquals(true, valueIt.hasNext());
        assertEquals(value2, valueIt.next());
        assertEquals(true, valueIt.hasNext());
        assertEquals(value3, valueIt.next());
        assertIteratorEnd(valueIt);
    }

    private void assertIteratorEnd(Iterator<?> it) {
        assertEquals(false, it.hasNext());
        try {
            it.next();
            fail();
        } catch (NoSuchElementException ex) {
            // expected
        }
    }

}

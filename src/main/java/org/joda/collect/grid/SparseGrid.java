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

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.collect.ForwardingSortedSet;

/**
 * Mutable implementation of the {@code Grid} data structure based on hashing.
 * 
 * @param <V> the type of the value
 * @author Stephen Colebourne
 */
public final class SparseGrid<V> extends AbstractGrid<V> implements Serializable {

    /** Serialization version. */
    private static final long serialVersionUID = 1L;

    /**
     * The row count.
     */
    private final int rowCount;
    /**
     * The column count.
     */
    private final int columnCount;
    /**
     * The cells.
     */
    private final SortedSet<Cell<V>> cells;

    //-----------------------------------------------------------------------
    /**
     * Creates an empty {@code SparseGrid} of the specified row-column count.
     * 
     * @param <R> the type of the value
     * @param rowCount  the number of rows, zero or greater
     * @param columnCount  the number of columns, zero or greater
     * @return the mutable grid, not null
     */
    public static <R> SparseGrid<R> create(int rowCount, int columnCount) {
        return new SparseGrid<R>(rowCount, columnCount, new TreeSet<Cell<R>>(AbstractCell.<R>comparator()));
    }

    /**
     * Creates a {@code SparseGrid} copying from another grid.
     *
     * @param <R> the type of the value
     * @param grid  the grid to copy, not null
     * @return the mutable grid, not null
     */
    public static <R> SparseGrid<R> create(Grid<? extends R> grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid must not be null");
        }
        SparseGrid<R> created = SparseGrid.create(grid.rowCount(), grid.columnCount());
        created.putAll(grid);
        return created;
    }

    //-----------------------------------------------------------------------
    /**
     * Restricted constructor.
     */
    SparseGrid(int rowCount, int columnCount, SortedSet<Cell<V>> data) {
        validateCounts(rowCount, columnCount);
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.cells = data;
    }

    //-----------------------------------------------------------------------
    @Override
    public int rowCount() {
        return rowCount;
    }

    @Override
    public int columnCount() {
        return columnCount;
    }

    //-----------------------------------------------------------------------
    @Override
    public int size() {
        return cells.size();
    }

    @Override
    public Cell<V> cell(int row, int column) {
        if (exists(row, column)) {
            SortedSet<Cell<V>> tail = cells.tailSet(finder(row, column));
            if (tail.size() > 0) {
                Cell<V> cell = tail.first();
                if (cell.getRow() == row && cell.getColumn() == column) {
                    return cell;
                }
            }
        }
        return null;
    }

    //-----------------------------------------------------------------------
    @Override
    public SortedSet<Cell<V>> cells() {
        return new ForwardingSortedSet<Cell<V>>() {
            @Override
            protected SortedSet<Cell<V>> delegate() {
                return cells;
            }
            @Override
            public boolean add(Cell<V> element) {
                return super.add(ImmutableCell.copyOf(element));
            }
            @Override
            public boolean addAll(Collection<? extends Cell<V>> collection) {
                return super.standardAddAll(collection);
            }
        };
    }

    //-----------------------------------------------------------------------
    @Override
    public void clear() {
        cells.clear();
    }

    @Override
    public void put(int row, int column, V value) {
        if (!exists(row, column)) {
            throw new IndexOutOfBoundsException("Invalid row-column: " + row + "," + column);
        }
        Cell<V> cell = ImmutableCell.of(row, column, value);
        cells.remove(cell);
        cells.add(cell);
    }

    @Override
    public void putAll(Grid<? extends V> grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid must nor be null");
        }
        for (Cell<? extends V> cell : grid.cells()) {
            cells.add(ImmutableCell.copyOf(cell));
        }
    }

    @Override
    public boolean remove(int row, int column) {
        if (exists(row, column)) {
            Set<Cell<V>> tail = cells.tailSet(finder(row, column));
            if (tail.size() > 0) {
                Iterator<Cell<V>> it = tail.iterator();
                Cell<V> cell = it.next();
                if (cell.getRow() == row && cell.getColumn() == column) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

}

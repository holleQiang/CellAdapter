package com.zhangqiang.celladapter.cell;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zhangqiang.celladapter.ChangedNotifier;
import com.zhangqiang.celladapter.observable.ObservableDataList;

import java.util.ArrayList;
import java.util.List;

public final class CellRoot implements CellParent {

    private final ObservableDataList<Cell> dataObserver = new ObservableDataList<>();
    private ChangedNotifier mChangedNotifier;
    private final List<Cell> totalCells = new ArrayList<>();

    public CellRoot(ChangedNotifier changedNotifier) {
        this.mChangedNotifier = changedNotifier;
        dataObserver.addDataObserver(new ParentSettingsObserver(this));
    }

    public int getTotalCellCount() {
        return totalCells.size();
    }

    public Cell getCellAt(int index) {
        return totalCells.get(index);
    }

    @Override
    public CellParent getParent() {
        return this;
    }

    @Override
    public <E extends Cell> void handChildChanged(CellParent childParent, int position, @NonNull List<E> oldList, @NonNull List<E> newList, @Nullable Object payload) {
        final int oldCellCount = getTotalCellCount();
        computeTotalCell();
        int cellCount = getTotalCellCount();
        if (oldCellCount != cellCount) {
            mChangedNotifier.notifyDataSetChanged();
            return;
        }
        if (childParent == this && oldList.size() == childParent.getDataCount()) {
            mChangedNotifier.notifyDataSetChanged();
            return;
        }
        int index = getRealChildIndex(childParent, position);
        if (index < 0) {
            return;
        }
        mChangedNotifier.notifyItemRangeChanged(index, CellUtils.getCellCount(newList),payload);
    }

    @Override
    public <E extends Cell> void handChildRemoved(CellParent childParent, int position, @NonNull List<E> removedList) {
        computeTotalCell();
        int index = getRealChildIndex(childParent, position);
        if (index < 0) {
            return;
        }
        mChangedNotifier.notifyItemRangeRemoved(index, CellUtils.getCellCount(removedList));
    }

    @Override
    public void handChildMoved(CellParent childParent, int fromPosition, int toPosition) {


        Cell fromCell = childParent.getDataAt(fromPosition);
        Cell toCell = childParent.getDataAt(toPosition);
        if (fromCell.isEmpty() && toCell.isEmpty()) {
            int realFromPosition = getRealChildIndex(childParent, fromPosition);
            if (realFromPosition < 0) {
                return;
            }
            int realToPosition = getRealChildIndex(childParent, toPosition);
            if (realToPosition < 0) {
                return;
            }
            mChangedNotifier.notifyItemMoved(realFromPosition, realToPosition);
        } else {
            mChangedNotifier.notifyDataSetChanged();
        }
    }

    @Override
    public <E extends Cell> void handChildAdded(CellParent childParent, int position, @NonNull List<E> addedList) {
        computeTotalCell();
        int index = getRealChildIndex(childParent, position);
        if (index < 0) {
            return;
        }
        mChangedNotifier.notifyItemRangeInserted(index, CellUtils.getCellCount(addedList));
    }

    @Override
    public void addDataAtIndex(Cell data, int position) {
        dataObserver.addDataAtIndex(data, position);
    }

    @Override
    public <E extends Cell> void addDataListAtIndex(List<E> dataList, int position) {
        dataObserver.addDataListAtIndex(dataList, position);
    }

    @Override
    public void addDataAtLast(Cell data) {
        dataObserver.addDataAtLast(data);
    }

    @Override
    public void addDataAtFirst(Cell data) {
        dataObserver.addDataAtFirst(data);
    }

    @Override
    public <E extends Cell> void addDataListAtLast(List<E> dataList) {
        dataObserver.addDataListAtLast(dataList);
    }

    @Override
    public <E extends Cell> void addDataListAtFirst(List<E> dataList) {
        dataObserver.addDataListAtFirst(dataList);
    }

    @Override
    public Cell removeDataAtIndex(int position) {
        return dataObserver.removeDataAtIndex(position);
    }

    @Override
    public void removeData(Cell data) {
        dataObserver.removeData(data);
    }

    @Override
    public List<Cell> removeDataFrom(int position, int count) {
        return dataObserver.removeDataFrom(position, count);
    }

    @Override
    public List<Cell> removeDataFrom(int position) {
        return dataObserver.removeDataFrom(position);
    }

    @Override
    public void removeAll() {
        dataObserver.removeAll();
    }

    @Override
    public int getDataIndex(Cell data) {
        return dataObserver.getDataIndex(data);
    }

    @Override
    public boolean isEmpty() {
        return dataObserver.isEmpty();
    }

    @Override
    public <E extends Cell> void setDataList(List<E> dataList) {
        dataObserver.setDataList(dataList);
    }

    @Override
    public int getDataCount() {
        return dataObserver.getDataCount();
    }

    @Override
    public Cell getDataAt(int position) {
        return dataObserver.getDataAt(position);
    }

    @Override
    public void swap(int fromPosition, int toPosition) {
        dataObserver.swap(fromPosition, toPosition);
    }

    @Override
    public Cell replace(int position, Cell data) {
        return dataObserver.replace(position, data);
    }

    @Override
    public <E extends Cell> Cell replace(int position, List<E> dataList) {
        return dataObserver.replace(position, dataList);
    }

    @Override
    public List<Cell> subList(int position, int count) {
        return dataObserver.subList(position, count);
    }


    public int getIndexOfChild(Cell child) {

        int childCount = getTotalCellCount();
        for (int i = 0; i < childCount; i++) {
            Cell childAt = getCellAt(i);
            if (childAt == child) {
                return i;
            }
        }
        return -1;
    }

    private int getRealChildIndex(CellParent childParent, int position) {

        int prevChildCount = CellUtils.getCellCount(childParent.subList(0, position));
        if (childParent instanceof Cell) {
            Cell cellChildParent = (Cell) childParent;
            int indexOfChild = getIndexOfChild(cellChildParent);
            if (indexOfChild < 0) {
                return -1;
            }
            return indexOfChild + 1 + prevChildCount;
        } else if (childParent == this) {
            return prevChildCount;
        }
        throw new IllegalArgumentException("illegal childParent" + childParent);
    }

    private void computeTotalCell() {
        totalCells.clear();
        int dataCount = getDataCount();
        for (int i = 0; i < dataCount; i++) {
            Cell data = getDataAt(i);
            totalCells.add(data);
            CellUtils.addChildToList(data, totalCells);
        }
    }

}

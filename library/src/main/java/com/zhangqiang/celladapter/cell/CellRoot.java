package com.zhangqiang.celladapter.cell;

import android.support.annotation.NonNull;

import com.zhangqiang.celladapter.Adapter;
import com.zhangqiang.celladapter.observable.ObservableDataList;

import java.util.List;

public final class CellRoot implements CellParent {

    private final ObservableDataList<Cell> dataObserver = new ObservableDataList<>();
    private Adapter mAdapter;

    public CellRoot(Adapter adapter) {
        this.mAdapter = adapter;
        dataObserver.addDataObserver(new ParentSettingsObserver(this));
    }

    public int getTotalCellCount() {
        int count = 0;
        int dataCount = getDataCount();
        for (int i = 0; i < dataCount; i++) {
            count++;
            Cell data = getDataAt(i);
            count += CellUtils.getChildCount(data);
        }
        return count;
    }

    public Cell getCellAt(int index) {
        if (index < 0) {
            return null;
        }
        int tempIndex = 0;
        int dataCount = getDataCount();
        for (int i = 0; i < dataCount; i++) {
            Cell data = getDataAt(i);
            if (tempIndex == index) {
                return data;
            }
            tempIndex++;
            int childCount = CellUtils.getChildCount(data);
            if (index >= tempIndex && index < tempIndex + childCount) {
                return CellUtils.getChildAt(data, index - tempIndex);
            } else {
                tempIndex += childCount;
            }
        }
        return null;
    }

    @Override
    public CellParent getParent() {
        return this;
    }

    @Override
    public <E extends Cell> void handChildChanged(CellParent childParent, int position, int count, @NonNull List<E> oldList,@NonNull  List<E> newList) {

        if (oldList.size() != newList.size() || count == oldList.size()) {
            mAdapter.notifyDataSetChanged();
            return;
        }
        int index = getRealChildIndex(childParent, position);
        if (index < 0) {
            return;
        }
        mAdapter.notifyItemRangeChanged(index, CellUtils.getCellCount(newList));
    }


    @Override
    public <E extends Cell> void handChildRemoved(CellParent childParent, int position,@NonNull  List<E> removedList) {

        int index = getRealChildIndex(childParent, position);
        if (index < 0) {
            return;
        }
        mAdapter.notifyItemRangeRemoved(index, CellUtils.getCellCount(removedList));
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
            mAdapter.notifyItemMoved(realFromPosition, realToPosition);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public <E extends Cell> void handChildAdded(CellParent childParent, int position,@NonNull  List<E> addedList) {

        int index = getRealChildIndex(childParent, position);
        if (index < 0) {
            return;
        }
        mAdapter.notifyItemRangeInserted(index, CellUtils.getCellCount(addedList));
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
    public void removeDataAtIndex(int position) {
        dataObserver.removeDataAtIndex(position);
    }

    @Override
    public void removeData(Cell data) {
        dataObserver.removeData(data);
    }

    @Override
    public void removeDataFrom(int position, int count) {
        dataObserver.removeDataFrom(position, count);
    }

    @Override
    public void removeDataFrom(int position) {
        dataObserver.removeDataFrom(position);
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
    public void replace(int position, Cell data) {
        dataObserver.replace(position, data);
    }

    @Override
    public <E extends Cell> void replace(int position, List<E> dataList) {
        dataObserver.replace(position, dataList);
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
}

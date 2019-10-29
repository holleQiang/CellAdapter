package com.zhangqiang.celladapter.cell;

import android.support.annotation.NonNull;

import com.zhangqiang.celladapter.Adapter;
import com.zhangqiang.celladapter.observable.ObservableDataList;
import com.zhangqiang.celladapter.vh.ViewHolder;

import java.util.List;

public class CellRoot<VH extends ViewHolder> implements CellParent<VH> {

    private final ObservableDataList<Cell<VH>> dataObserver = new ObservableDataList<>();
    private Adapter mAdapter;

    public CellRoot(Adapter adapter) {
        this.mAdapter = adapter;
        dataObserver.addDataObserver(new ParentSettingsObserver<VH>(this) {
            @Override
            public <E extends Cell<VH>> void onDataAdded(int position, @NonNull List<E> addedList) {
                super.onDataAdded(position, addedList);
                int index = CellUtils.getCellCount(dataObserver.subList(0, position));
                mAdapter.notifyItemRangeInserted(index, CellUtils.getCellCount(addedList));
            }

            @Override
            public <E extends Cell<VH>> void onDataRemoved(int position, @NonNull List<E> removedList) {
                super.onDataRemoved(position, removedList);
                int index = CellUtils.getCellCount(dataObserver.subList(0, position));
                mAdapter.notifyItemRangeRemoved(index, CellUtils.getCellCount(removedList));
            }

            @Override
            public <E extends Cell<VH>> void onDataChanged(int position, int count, @NonNull List<E> oldList, @NonNull List<E> newList) {
                super.onDataChanged(position, count, oldList, newList);
                if (oldList.size() == count) {
                    mAdapter.notifyDataSetChanged();
                } else {
                    int index = CellUtils.getCellCount(dataObserver.subList(0, position));
                    mAdapter.notifyItemRangeChanged(index, CellUtils.getCellCount(newList));
                }
            }

            @Override
            public void onDataMoved(int fromPosition, int toPosition) {
                super.onDataMoved(fromPosition, toPosition);
                if (CellUtils.getChildCount(getDataAt(fromPosition)) == 0
                        && CellUtils.getChildCount(getDataAt(toPosition)) == 0) {
                    fromPosition = CellUtils.getCellCount(subList(0, fromPosition));
                    toPosition = CellUtils.getCellCount(subList(0, toPosition));
                    mAdapter.notifyItemMoved(fromPosition, toPosition);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public int getTotalCellCount() {
        int count = 0;
        int dataCount = getDataCount();
        for (int i = 0; i < dataCount; i++) {
            count++;
            Cell<VH> data = getDataAt(i);
            count += CellUtils.getChildCount(data);
        }
        return count;
    }

    public Cell<VH> getCellAt(int index) {
        if (index < 0) {
            return null;
        }
        int tempIndex = 0;
        int dataCount = getDataCount();
        for (int i = 0; i < dataCount; i++) {
            Cell<VH> data = getDataAt(i);
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
    public <E extends Cell<VH>> void handChildChanged(CellParent<VH> childParent, int position, int count, List<E> oldList, List<E> newList) {

        int index = getRealChildIndex(childParent, position);
        if (index < 0) {
            return;
        }
        mAdapter.notifyItemRangeChanged(index, CellUtils.getCellCount(newList));
    }


    @Override
    public <E extends Cell<VH>> void handChildRemoved(CellParent<VH> childParent, int position, List<E> removedList) {

        int index = getRealChildIndex(childParent, position);
        if (index < 0) {
            return;
        }
        mAdapter.notifyItemRangeRemoved(index, CellUtils.getCellCount(removedList));
    }

    @Override
    public void handChildMoved(CellParent<VH> childParent, int fromPosition, int toPosition) {


        Cell<VH> fromCell = childParent.getDataAt(fromPosition);
        Cell<VH> toCell = childParent.getDataAt(toPosition);
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
    public <E extends Cell<VH>> void handChildAdded(CellParent<VH> childParent, int position, List<E> addedList) {

        int index = getRealChildIndex(childParent, position);
        if (index < 0) {
            return;
        }
        mAdapter.notifyItemRangeInserted(index, CellUtils.getCellCount(addedList));
    }

    @Override
    public void addDataAtIndex(Cell<VH> data, int position) {
        dataObserver.addDataAtIndex(data, position);
    }

    @Override
    public <E extends Cell<VH>> void addDataListAtIndex(List<E> dataList, int position) {
        dataObserver.addDataListAtIndex(dataList, position);
    }

    @Override
    public void addDataAtLast(Cell<VH> data) {
        dataObserver.addDataAtLast(data);
    }

    @Override
    public void addDataAtFirst(Cell<VH> data) {
        dataObserver.addDataAtFirst(data);
    }

    @Override
    public <E extends Cell<VH>> void addDataListAtLast(List<E> dataList) {
        dataObserver.addDataListAtLast(dataList);
    }

    @Override
    public <E extends Cell<VH>> void addDataListAtFirst(List<E> dataList) {
        dataObserver.addDataListAtFirst(dataList);
    }

    @Override
    public void removeDataAtIndex(int position) {
        dataObserver.removeDataAtIndex(position);
    }

    @Override
    public void removeData(Cell<VH> data) {
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
    public int getDataIndex(Cell<VH> data) {
        return dataObserver.getDataIndex(data);
    }

    @Override
    public boolean isEmpty() {
        return dataObserver.isEmpty();
    }

    @Override
    public <E extends Cell<VH>> void setDataList(List<E> dataList) {
        dataObserver.setDataList(dataList);
    }

    @Override
    public int getDataCount() {
        return dataObserver.getDataCount();
    }

    @Override
    public Cell<VH> getDataAt(int position) {
        return dataObserver.getDataAt(position);
    }

    @Override
    public void swap(int fromPosition, int toPosition) {
        dataObserver.swap(fromPosition, toPosition);
    }

    @Override
    public void replace(int position, Cell<VH> data) {
        dataObserver.replace(position, data);
    }

    @Override
    public <E extends Cell<VH>> void replace(int position, List<E> dataList) {
        dataObserver.replace(position, dataList);
    }

    @Override
    public List<Cell<VH>> subList(int position, int count) {
        return dataObserver.subList(position, count);
    }


    public int getIndexOfChild(Cell<VH> child) {

        int childCount = getTotalCellCount();
        for (int i = 0; i < childCount; i++) {
            Cell<VH> childAt = getCellAt(i);
            if (childAt == child) {
                return i;
            }
        }
        return -1;
    }

    private int getRealChildIndex(CellParent<VH> childParent, int position) {

        int prevChildCount = CellUtils.getCellCount(childParent.subList(0, position));
        if (childParent instanceof Cell) {
            Cell<VH> cellChildParent = (Cell<VH>) childParent;
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

package com.zhangqiang.celladapter;

import android.support.annotation.NonNull;

import com.zhangqiang.celladapter.cell.Cell;
import com.zhangqiang.celladapter.cell.CellParent;
import com.zhangqiang.celladapter.observable.DataObserver;

import java.util.List;

class ParentSettingsObserver implements DataObserver<Cell> {

    private CellParent parent;

    public ParentSettingsObserver(CellParent parent) {
        this.parent = parent;
    }

    @Override
    public <E extends Cell> void onDataChanged(int position, int count, @NonNull List<E> oldList, @NonNull List<E> newList) {
        for (int i = 0; i < oldList.size(); i++) {
            oldList.get(i).setParent(null);
        }
        for (int i = 0; i < newList.size(); i++) {
            newList.get(i).setParent(parent);
        }
    }

    @Override
    public <E extends Cell> void onDataAdded(int position, @NonNull List<E> addedList) {
        for (int i = 0; i < addedList.size(); i++) {
            addedList.get(i).setParent(parent);
        }
    }

    @Override
    public <E extends Cell> void onDataRemoved(int position, @NonNull List<E> removedList) {
        for (int i = 0; i < removedList.size(); i++) {
            removedList.get(i).setParent(null);
        }
    }

    @Override
    public void onDataMoved(int fromPosition, int toPosition) {

    }

}

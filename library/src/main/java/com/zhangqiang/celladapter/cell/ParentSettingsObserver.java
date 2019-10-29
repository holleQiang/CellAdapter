package com.zhangqiang.celladapter.cell;

import android.support.annotation.NonNull;

import com.zhangqiang.celladapter.observable.DataObserver;
import com.zhangqiang.celladapter.vh.ViewHolder;

import java.util.List;

class ParentSettingsObserver<VH extends ViewHolder> implements DataObserver<Cell<VH>> {

    private CellParent<VH> parent;

    public ParentSettingsObserver(CellParent<VH> parent) {
        this.parent = parent;
    }

    @Override
    public <E extends Cell<VH>> void onDataChanged(int position, int count, @NonNull List<E> oldList, @NonNull List<E> newList) {
        for (int i = 0; i < oldList.size(); i++) {
            oldList.get(i).setParent(null);
        }
        for (int i = 0; i < newList.size(); i++) {
            newList.get(i).setParent(parent);
        }
    }

    @Override
    public <E extends Cell<VH>> void onDataAdded(int position, @NonNull List<E> addedList) {
        for (int i = 0; i < addedList.size(); i++) {
            addedList.get(i).setParent(parent);
        }
    }

    @Override
    public <E extends Cell<VH>> void onDataRemoved(int position, @NonNull List<E> removedList) {
        for (int i = 0; i < removedList.size(); i++) {
            removedList.get(i).setParent(null);
        }
    }

    @Override
    public void onDataMoved(int fromPosition, int toPosition) {

    }

}

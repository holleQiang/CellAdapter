package com.zhangqiang.celladapter.cell;

import android.support.annotation.NonNull;

import com.zhangqiang.celladapter.observable.DataObserver;

import java.util.List;

final class ParentSettingsObserver implements DataObserver<Cell> {

    private CellParent mParent;

    ParentSettingsObserver(CellParent parent) {
        this.mParent = parent;
    }

    @Override
    public <E extends Cell> void onDataChanged(int position, int count, @NonNull List<E> oldList, @NonNull List<E> newList) {
        for (int i = 0; i < oldList.size(); i++) {
            oldList.get(i).setParent(null);
        }
        for (int i = 0; i < newList.size(); i++) {
            newList.get(i).setParent(mParent);
        }
        handChildChangedInternal(position, count, oldList, newList);
    }

    @Override
    public <E extends Cell> void onDataAdded(int position, @NonNull List<E> addedList) {
        for (int i = 0; i < addedList.size(); i++) {
            addedList.get(i).setParent(mParent);
        }
        handChildAddedInternal(position, addedList);
    }

    @Override
    public <E extends Cell> void onDataRemoved(int position, @NonNull List<E> removedList) {
        for (int i = 0; i < removedList.size(); i++) {
            removedList.get(i).setParent(null);
        }
        handChildRemovedInternal(position, removedList);
    }

    @Override
    public void onDataMoved(int fromPosition, int toPosition) {
        handChildMovedInternal(fromPosition, toPosition);
    }


    private <E extends Cell> void handChildChangedInternal(int position, int count, List<E> oldList, List<E> newList) {
        CellParent parent = mParent.getParent();
        if (parent != null) {
            parent.handChildChanged(mParent, position, count, oldList, newList);
        }
    }

    private <E extends Cell> void handChildRemovedInternal(int position, List<E> removedList) {
        CellParent parent = mParent.getParent();
        if (parent != null) {
            parent.handChildRemoved(mParent, position, removedList);
        }
    }


    private <E extends Cell> void handChildAddedInternal(int position, List<E> addedList) {

        CellParent parent = mParent.getParent();
        if (parent != null) {
            parent.handChildAdded(mParent, position, addedList);
        }
    }

    private void handChildMovedInternal(int fromPosition, int toPosition) {
        CellParent parent = mParent.getParent();
        if (parent != null) {
            parent.handChildMoved(mParent, fromPosition, toPosition);
        }
    }
}

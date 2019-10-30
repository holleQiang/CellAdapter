package com.zhangqiang.celladapter.cell;

import android.support.annotation.NonNull;

import com.zhangqiang.celladapter.observable.DataList;

import java.util.List;

public interface CellParent extends DataList<Cell> {

    CellParent getParent();

    <E extends Cell> void handChildChanged(CellParent childParent, int position, int count, @NonNull List<E> oldList,@NonNull List<E> newList);

    void handChildMoved(CellParent childParent, int fromPosition, int toPosition);

    <E extends Cell> void handChildAdded(CellParent childParent, int position,@NonNull List<E> addedList);

    <E extends Cell> void handChildRemoved(CellParent childParent, int position,@NonNull List<E> removedList);
}

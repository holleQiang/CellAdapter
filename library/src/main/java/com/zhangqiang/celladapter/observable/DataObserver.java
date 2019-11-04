package com.zhangqiang.celladapter.observable;

import android.support.annotation.NonNull;

import java.util.List;

public interface DataObserver<T> {

    <E extends T> void onDataChanged(int position,@NonNull List<E> oldList,@NonNull List<E> newList);

    <E extends T> void onDataAdded(int position, @NonNull List<E> addedList);

    <E extends T> void onDataRemoved(int position,@NonNull List<E> removedList);

    void onDataMoved(int fromPosition, int toPosition);
}

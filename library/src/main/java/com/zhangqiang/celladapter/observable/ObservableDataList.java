package com.zhangqiang.celladapter.observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ObservableDataList<T> implements DataList<T> {

    private final List<T> mDataList = new ArrayList<>();
    private final List<DataObserver> dataObservers = new ArrayList<>();

    @Override
    public void addDataAtIndex(T data, int position) {
        if (data == null) {
            return;
        }
        mDataList.add(position, data);
        for (int i = dataObservers.size() - 1; i >= 0; i--) {
            dataObservers.get(i).onDataAdded(position, Collections.singletonList(data));
        }
    }

    @Override
    public <E extends T> void addDataListAtIndex(List<E> dataList, int position) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        mDataList.addAll(position, dataList);
        List<E> addedList = new ArrayList<>(dataList);
        for (int i = dataObservers.size() - 1; i >= 0; i--) {
            dataObservers.get(i).onDataAdded(position, addedList);
        }
    }

    @Override
    public void addDataAtLast(T data) {
        addDataAtIndex(data, mDataList.size());
    }

    @Override
    public void addDataAtFirst(T data) {
        addDataAtIndex(data, 0);
    }

    @Override
    public <E extends T> void addDataListAtLast(List<E> dataList) {
        addDataListAtIndex(dataList, mDataList.size());
    }

    @Override
    public <E extends T> void addDataListAtFirst(List<E> dataList) {
        addDataListAtIndex(dataList, 0);
    }

    @Override
    public void removeDataAtIndex(int position) {
        T removed = mDataList.remove(position);
        for (int i = dataObservers.size() - 1; i >= 0; i--) {
            dataObservers.get(i).onDataRemoved(position, Collections.singletonList(removed));
        }
    }

    @Override
    public void removeData(T data) {
        int index = mDataList.indexOf(data);
        if (index >= 0) {
            removeDataAtIndex(index);
        }
    }

    @Override
    public void removeDataFrom(int position, int count) {
        int index = 0;
        List<T> removedList = new ArrayList<>();
        Iterator<T> iterator = mDataList.iterator();
        while (iterator.hasNext()) {
            if (index >= position && index < position + count) {
                T next = iterator.next();
                iterator.remove();
                removedList.add(next);
            }
            index++;
        }
        for (int i = dataObservers.size() - 1; i >= 0; i--) {
            dataObservers.get(i).onDataRemoved(position, removedList);
        }
    }

    @Override
    public void removeDataFrom(int position) {
        removeDataFrom(position, getDataCount() - 1 - position);
    }

    @Override
    public void removeAll() {
        List<T> removed = new ArrayList<>(mDataList);
        mDataList.clear();
        for (int i = dataObservers.size() - 1; i >= 0; i--) {
            dataObservers.get(i).onDataRemoved(0, removed);
        }
    }

    @Override
    public int getDataIndex(T data) {
        return mDataList.indexOf(data);
    }

    @Override
    public boolean isEmpty() {
        return mDataList.isEmpty();
    }

    @Override
    public <E extends T> void setDataList(List<E> dataList) {
        List<T> oldList = new ArrayList<>(mDataList);
        mDataList.clear();
        List<E> newList;
        if (dataList != null) {
            newList = new ArrayList<>(dataList);
            mDataList.addAll(newList);
        } else {
            newList = new ArrayList<>();
        }
        for (int i = dataObservers.size() - 1; i >= 0; i--) {
            dataObservers.get(i).onDataChanged(0,  oldList, newList);
        }
    }

    @Override
    public int getDataCount() {
        return mDataList.size();
    }

    @Override
    public T getDataAt(int position) {
        return mDataList.get(position);
    }

    @Override
    public void swap(int fromPosition, int toPosition) {
        Collections.swap(mDataList,fromPosition,toPosition);
        for (int i = dataObservers.size() - 1; i >= 0; i--) {
            dataObservers.get(i).onDataMoved(fromPosition, toPosition);
        }
    }

    @Override
    public void replace(int position, T data) {
        T prevData = mDataList.set(position, data);
        for (int i = dataObservers.size() - 1; i >= 0; i--) {
            dataObservers.get(i).onDataChanged(0, Collections.singletonList(prevData), Collections.singletonList(data));
        }
    }

    @Override
    public <E extends T> void replace(int position, List<E> dataList) {
        T removed = mDataList.remove(position);
        List<E> newList;
        if (dataList == null) {
            newList = new ArrayList<>();
        } else {
            newList = new ArrayList<>(dataList);
            mDataList.addAll(dataList);
        }
        for (int i = dataObservers.size() - 1; i >= 0; i--) {
            dataObservers.get(i).onDataChanged(0, Collections.singletonList(removed), newList);
        }
    }

    @Override
    public List<T> subList(int position, int count) {
        return new ArrayList<>(mDataList.subList(position, position + count));
    }

    public void addDataObserver(DataObserver dataObserver) {
        if (dataObservers.contains(dataObserver)) {
            return;
        }
        dataObservers.add(dataObserver);
    }

    public void removeDataObserver(DataObserver dataObserver) {
        dataObservers.remove(dataObserver);
    }
}

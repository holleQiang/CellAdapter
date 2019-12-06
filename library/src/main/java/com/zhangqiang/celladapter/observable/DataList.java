package com.zhangqiang.celladapter.observable;

import java.util.List;

public interface DataList<T> {

    void addDataAtIndex(T data, int position);

    <E extends T> void addDataListAtIndex(List<E> dataList, int position);

    void addDataAtLast(T data);

    void addDataAtFirst(T data);

    <E extends T> void addDataListAtLast(List<E> dataList);

    <E extends T> void addDataListAtFirst(List<E> dataList);

    T removeDataAtIndex(int position);

    void removeData(T data);

    List<T> removeDataFrom(int position, int count);

    List<T> removeDataFrom(int position);

    void removeAll();

    int getDataIndex(T data);

    boolean isEmpty();

    <E extends T> void setDataList(List<E> dataList);

    int getDataCount();

    T getDataAt(int position);

    void swap(int fromPosition, int toPosition);

    T replace(int position, T data);

    <E extends T>  T replace(int position, List<E> dataList);

    List<T> subList(int position,int count);
}

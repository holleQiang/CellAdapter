package com.zhangqiang.celladapter.cell;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

import com.zhangqiang.celladapter.R;
import com.zhangqiang.celladapter.observable.ObservableDataList;
import com.zhangqiang.celladapter.vh.ViewHolder;

import java.util.Collections;
import java.util.List;

public abstract class Cell implements CellParent {

    public static final int FULL_SPAN = -1;
    private int mSpanSize = 1;
    private CellParent mParent;
    private ObservableDataList<Cell> observableDataList = new ObservableDataList<>();

    public Cell() {
        observableDataList.addDataObserver(new ParentSettingsObserver(this));
    }


    public ViewHolder createViewHolder(ViewGroup viewGroup) {
        return onCreateViewHolder(viewGroup);
    }

    protected abstract ViewHolder onCreateViewHolder(ViewGroup viewGroup);

    public abstract int getViewType();

    public int getSpanSize() {
        return mSpanSize;
    }

    public void setSpanSize(int spanSize) {
        this.mSpanSize = spanSize;
    }

    public void bindViewHolder(ViewHolder vh) {
        View view = vh.getView();
        View.OnAttachStateChangeListener attachStateChangeListener = (View.OnAttachStateChangeListener) view.getTag(R.id.tag_key_attach_listener);
        if (attachStateChangeListener != null) {
            view.removeOnAttachStateChangeListener(attachStateChangeListener);
        }
        attachStateChangeListener = new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        };
        view.setTag(R.id.tag_key_attach_listener);
        view.addOnAttachStateChangeListener(attachStateChangeListener);
        if (ViewCompat.isAttachedToWindow(view)) {
            attachStateChangeListener.onViewAttachedToWindow(view);
        }

        onBindViewHolder(vh);
    }

    protected abstract void onBindViewHolder(ViewHolder vh);

    @Override
    public CellParent getParent() {
        return mParent;
    }

    void setParent(CellParent parent) {
        if (mParent != null && parent != null) {
            throw new RuntimeException("cell : " + toString() + " has already has parent : " + mParent);
        }
        mParent = parent;
    }

    @Override
    public void addDataAtIndex(Cell data, int position) {
        observableDataList.addDataAtIndex(data, position);
    }

    @Override
    public <E extends Cell> void addDataListAtIndex(List<E> dataList, int position) {
        observableDataList.addDataListAtIndex(dataList, position);
    }

    @Override
    public void addDataAtLast(Cell data) {
        observableDataList.addDataAtLast(data);
    }

    @Override
    public void addDataAtFirst(Cell data) {
        observableDataList.addDataAtFirst(data);
    }

    @Override
    public <E extends Cell> void addDataListAtLast(List<E> dataList) {
        observableDataList.addDataListAtLast(dataList);
    }

    @Override
    public <E extends Cell> void addDataListAtFirst(List<E> dataList) {
        observableDataList.addDataListAtFirst(dataList);
    }

    @Override
    public void removeDataAtIndex(int position) {
        observableDataList.removeDataAtIndex(position);
    }

    @Override
    public void removeData(Cell data) {
        observableDataList.removeData(data);
    }

    @Override
    public void removeDataFrom(int position, int count) {
        observableDataList.removeDataFrom(position, count);
    }

    @Override
    public void removeDataFrom(int position) {
        observableDataList.removeDataFrom(position);
    }

    @Override
    public void removeAll() {
        observableDataList.removeAll();
    }

    @Override
    public int getDataIndex(Cell data) {
        return observableDataList.getDataIndex(data);
    }

    @Override
    public boolean isEmpty() {
        return observableDataList.isEmpty();
    }

    @Override
    public <E extends Cell> void setDataList(List<E> dataList) {
        observableDataList.setDataList(dataList);
    }

    @Override
    public int getDataCount() {
        return observableDataList.getDataCount();
    }

    @Override
    public Cell getDataAt(int position) {
        return observableDataList.getDataAt(position);
    }

    @Override
    public void swap(int fromPosition, int toPosition) {
        observableDataList.swap(fromPosition, toPosition);
    }

    @Override
    public void replace(int position, Cell data) {
        observableDataList.replace(position, data);
    }


    @Override
    public <E extends Cell> void replace(int position, List<E> dataList) {
        observableDataList.replace(position, dataList);
    }

    @Override
    public List<Cell> subList(int position, int count) {
        return observableDataList.subList(position, count);
    }



    @Override
    public <E extends Cell> void handChildChanged(CellParent childParent, int position, int count, List<E> oldList, List<E> newList) {
        CellParent parent = getParent();
        if (parent != null) {
            parent.handChildChanged(childParent,position, count, oldList, newList);
        }
    }

    @Override
    public <E extends Cell> void handChildAdded(CellParent childParent, int position, List<E> addedList) {
        CellParent parent = getParent();
        if (parent != null) {
            parent.handChildAdded(childParent, position, addedList);
        }
    }

    @Override
    public void handChildMoved(CellParent childParent, int fromPosition, int toPosition) {
        CellParent parent = getParent();
        if (parent != null) {
            parent.handChildMoved(childParent, fromPosition, toPosition);
        }
    }

    @Override
    public <E extends Cell> void handChildRemoved(CellParent childParent, int position, List<E> removedList) {
        CellParent parent = getParent();
        if (parent != null) {
            parent.handChildRemoved(childParent, position, removedList);
        }
    }

    public void invalidate(){
        CellParent parent = getParent();
        if (parent == null) {
            return;
        }
        parent.handChildChanged(parent,parent.getDataIndex(this),parent.getDataCount(), Collections.singletonList(this),Collections.singletonList(this));
    }
}

package com.zhangqiang.celladapter.cell;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.zhangqiang.celladapter.cell.action.Action;
import com.zhangqiang.celladapter.observable.ObservableDataList;
import com.zhangqiang.celladapter.vh.ViewHolder;

import java.util.Collections;
import java.util.List;

public abstract class Cell implements CellParent {

    public static final int FULL_SPAN = -1;
    private final int mSpanSize;
    private CellParent mParent;
    private ObservableDataList<Cell> observableDataList;

    public Cell() {
        this(1);
    }

    public Cell(int spanSize) {
        this.mSpanSize = spanSize;
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup) {
        return onCreateViewHolder(viewGroup);
    }

    protected abstract ViewHolder onCreateViewHolder(ViewGroup viewGroup);

    public abstract int getViewType();

    public int getSpanSize() {
        return mSpanSize;
    }

    public void bindViewHolder(ViewHolder vh, List<Object> payloads) {
        if (payloads == null || payloads.isEmpty()) {
            onBindViewHolder(vh);
        } else {
            for (Object payload : payloads) {
                if (payload instanceof Action) {
                    ((Action) payload).onBind(vh);
                }
            }
        }
    }

    protected abstract void onBindViewHolder(ViewHolder vh);


    public static void setOnAttachStateChangeListener(int tagKey, View view, View.OnAttachStateChangeListener attachStateChangeListener) {

        View.OnAttachStateChangeListener oldAttachStateChangeListener = (View.OnAttachStateChangeListener) view.getTag(tagKey);
        if (oldAttachStateChangeListener != null) {
            view.removeOnAttachStateChangeListener(oldAttachStateChangeListener);
            view.setTag(tagKey, null);
        }
        if (attachStateChangeListener != null) {
            view.setTag(tagKey, attachStateChangeListener);
            view.addOnAttachStateChangeListener(attachStateChangeListener);
        }
    }


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
        checkAndInit();
        observableDataList.addDataAtIndex(data, position);
    }

    @Override
    public <E extends Cell> void addDataListAtIndex(List<E> dataList, int position) {
        checkAndInit();
        observableDataList.addDataListAtIndex(dataList, position);
    }

    @Override
    public void addDataAtLast(Cell data) {
        checkAndInit();
        observableDataList.addDataAtLast(data);
    }

    @Override
    public void addDataAtFirst(Cell data) {
        checkAndInit();
        observableDataList.addDataAtFirst(data);
    }

    @Override
    public <E extends Cell> void addDataListAtLast(List<E> dataList) {
        checkAndInit();
        observableDataList.addDataListAtLast(dataList);
    }

    @Override
    public <E extends Cell> void addDataListAtFirst(List<E> dataList) {
        checkAndInit();
        observableDataList.addDataListAtFirst(dataList);
    }

    @Override
    public Cell removeDataAtIndex(int position) {
        if (observableDataList == null) {
            return null;
        }
        return observableDataList.removeDataAtIndex(position);
    }

    @Override
    public void removeData(Cell data) {
        if (observableDataList == null) {
            return;
        }
        observableDataList.removeData(data);
    }

    @Override
    public List<Cell> removeDataFrom(int position, int count) {
        if (observableDataList == null) {
            return null;
        }
        return observableDataList.removeDataFrom(position, count);
    }

    @Override
    public List<Cell> removeDataFrom(int position) {
        if (observableDataList == null) {
            return null;
        }
        return observableDataList.removeDataFrom(position);
    }

    @Override
    public void removeAll() {
        if (observableDataList == null) {
            return ;
        }
        observableDataList.removeAll();
    }

    @Override
    public int getDataIndex(Cell data) {
        if (observableDataList == null) {
            return -1;
        }
        return observableDataList.getDataIndex(data);
    }

    @Override
    public boolean isEmpty() {
        if (observableDataList == null) {
            return true;
        }
        return observableDataList.isEmpty();
    }

    @Override
    public <E extends Cell> void setDataList(List<E> dataList) {
        checkAndInit();
        observableDataList.setDataList(dataList);
    }

    @Override
    public int getDataCount() {
        if (observableDataList == null) {
            return 0;
        }
        return observableDataList.getDataCount();
    }

    @Override
    public Cell getDataAt(int position) {
        if (observableDataList == null) {
            return null;
        }
        return observableDataList.getDataAt(position);
    }

    @Override
    public void swap(int fromPosition, int toPosition) {
        if (observableDataList == null) {
            return ;
        }
        observableDataList.swap(fromPosition, toPosition);
    }

    @Override
    public Cell replace(int position, Cell data) {
        if (observableDataList == null) {
            return null;
        }
        return observableDataList.replace(position, data);
    }


    @Override
    public <E extends Cell> Cell replace(int position, List<E> dataList) {
        if (observableDataList == null) {
            return null;
        }
        return observableDataList.replace(position, dataList);
    }

    @Override
    public List<Cell> subList(int position, int count) {
        if (observableDataList == null) {
            return null;
        }
        return observableDataList.subList(position, count);
    }

    @Override
    public <E extends Cell> void handChildChanged(CellParent childParent, int position, @NonNull List<E> oldList, @NonNull List<E> newList, @Nullable Object payload) {
        CellParent parent = getParent();
        if (parent != null) {
            parent.handChildChanged(childParent, position, oldList, newList, payload);
        }
    }

    @Override
    public <E extends Cell> void handChildAdded(CellParent childParent, int position, @NonNull List<E> addedList) {
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
    public <E extends Cell> void handChildRemoved(CellParent childParent, int position, @NonNull List<E> removedList) {
        CellParent parent = getParent();
        if (parent != null) {
            parent.handChildRemoved(childParent, position, removedList);
        }
    }

    public void invalidate(Action action) {
        CellParent parent = getParent();
        if (parent == null) {
            return;
        }
        parent.handChildChanged(parent, parent.getDataIndex(this), Collections.singletonList(this), Collections.singletonList(this), action);
    }

    public void invalidate() {
        invalidate(null);
    }

    private void checkAndInit() {
        if (observableDataList != null) {
            return;
        }
        observableDataList = new ObservableDataList<>();
        observableDataList.addDataObserver(new ParentSettingsObserver(this));
    }
}

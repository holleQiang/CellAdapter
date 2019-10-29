package com.zhangqiang.celladapter.cell;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

import com.zhangqiang.celladapter.R;
import com.zhangqiang.celladapter.observable.ObservableDataList;
import com.zhangqiang.celladapter.vh.ViewHolder;

import java.util.Collections;
import java.util.List;

public abstract class Cell<VH extends ViewHolder> implements CellParent<VH> {

    public static final int FULL_SPAN = -1;
    private int mSpanSize = 1;
    private CellParent<VH> mParent;
    private ObservableDataList<Cell<VH>> observableDataList = new ObservableDataList<>();

    public Cell() {
        observableDataList.addDataObserver(new ParentSettingsObserver<VH>(this){

            @Override
            public <E extends Cell<VH>> void onDataChanged(int position, int count, @NonNull List<E> oldList, @NonNull List<E> newList) {
                super.onDataChanged(position, count, oldList, newList);
                handChildChangedInternal(position, count,oldList,newList);
            }

            @Override
            public <E extends Cell<VH>> void onDataRemoved(int position, @NonNull List<E> removedList) {
                super.onDataRemoved(position, removedList);
                handChildRemovedInternal(position,removedList);
            }


            @Override
            public <E extends Cell<VH>> void onDataAdded(int position, @NonNull List<E> addedList) {
                super.onDataAdded(position, addedList);
                handChildAddedInternal(position,addedList);
            }

            @Override
            public void onDataMoved(int fromPosition, int toPosition) {
                super.onDataMoved(fromPosition, toPosition);
                handChildMovedInternal(fromPosition,toPosition);
            }
        });
    }


    public VH createViewHolder(ViewGroup viewGroup) {
        return onCreateViewHolder(viewGroup);
    }

    protected abstract VH onCreateViewHolder(ViewGroup viewGroup);

    public abstract int getViewType();

    public int getSpanSize() {
        return mSpanSize;
    }

    public void setSpanSize(int spanSize) {
        this.mSpanSize = spanSize;
    }

    public void bindViewHolder(VH vh) {
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

    protected abstract void onBindViewHolder(VH vh);

    public CellParent<VH> getParent() {
        return mParent;
    }

    void setParent(CellParent<VH> parent) {
        if (mParent != null && parent != null) {
            throw new RuntimeException("cell : " + toString() + " has already has parent : " + mParent);
        }
        mParent = parent;
    }

    @Override
    public void addDataAtIndex(Cell<VH> data, int position) {
        observableDataList.addDataAtIndex(data, position);
    }

    @Override
    public <E extends Cell<VH>> void addDataListAtIndex(List<E> dataList, int position) {
        observableDataList.addDataListAtIndex(dataList, position);
    }

    @Override
    public void addDataAtLast(Cell<VH> data) {
        observableDataList.addDataAtLast(data);
    }

    @Override
    public void addDataAtFirst(Cell<VH> data) {
        observableDataList.addDataAtFirst(data);
    }

    @Override
    public <E extends Cell<VH>> void addDataListAtLast(List<E> dataList) {
        observableDataList.addDataListAtLast(dataList);
    }

    @Override
    public <E extends Cell<VH>> void addDataListAtFirst(List<E> dataList) {
        observableDataList.addDataListAtFirst(dataList);
    }

    @Override
    public void removeDataAtIndex(int position) {
        observableDataList.removeDataAtIndex(position);
    }

    @Override
    public void removeData(Cell<VH> data) {
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
    public int getDataIndex(Cell<VH> data) {
        return observableDataList.getDataIndex(data);
    }

    @Override
    public boolean isEmpty() {
        return observableDataList.isEmpty();
    }

    @Override
    public <E extends Cell<VH>> void setDataList(List<E> dataList) {
        observableDataList.setDataList(dataList);
    }

    @Override
    public int getDataCount() {
        return observableDataList.getDataCount();
    }

    @Override
    public Cell<VH> getDataAt(int position) {
        return observableDataList.getDataAt(position);
    }

    @Override
    public void swap(int fromPosition, int toPosition) {
        observableDataList.swap(fromPosition, toPosition);
    }

    @Override
    public void replace(int position, Cell<VH> data) {
        observableDataList.replace(position, data);
    }


    @Override
    public <E extends Cell<VH>> void replace(int position, List<E> dataList) {
        observableDataList.replace(position, dataList);
    }

    @Override
    public List<Cell<VH>> subList(int position, int count) {
        return observableDataList.subList(position, count);
    }

    private <E extends Cell<VH>> void handChildChangedInternal(int position, int count, List<E> oldList, List<E> newList) {
        CellParent<VH> parent = getParent();
        if (parent != null) {
            parent.handChildChanged(this,position, count, oldList, newList);
        }
    }

    private <E extends Cell<VH>> void handChildRemovedInternal(int position, List<E> removedList) {
        CellParent<VH> parent = getParent();
        if (parent != null) {
            parent.handChildRemoved(this,position, removedList);
        }
    }


    private <E extends Cell<VH>> void handChildAddedInternal(int position, List<E> addedList) {

        CellParent<VH> parent = getParent();
        if (parent != null) {
            parent.handChildAdded(this,position, addedList);
        }
    }

    private void handChildMovedInternal(int fromPosition, int toPosition) {
        CellParent<VH> parent = getParent();
        if (parent != null) {
            parent.handChildMoved(this,fromPosition, toPosition);
        }
    }

    @Override
    public <E extends Cell<VH>> void handChildChanged(CellParent<VH> childParent, int position, int count, List<E> oldList, List<E> newList) {
        CellParent<VH> parent = getParent();
        if (parent != null) {
            parent.handChildChanged(childParent,position, count, oldList, newList);
        }
    }

    @Override
    public <E extends Cell<VH>> void handChildAdded(CellParent<VH> childParent, int position, List<E> addedList) {
        CellParent<VH> parent = getParent();
        if (parent != null) {
            parent.handChildAdded(childParent, position, addedList);
        }
    }

    @Override
    public void handChildMoved(CellParent<VH> childParent, int fromPosition, int toPosition) {
        CellParent<VH> parent = getParent();
        if (parent != null) {
            parent.handChildMoved(childParent, fromPosition, toPosition);
        }
    }

    @Override
    public <E extends Cell<VH>> void handChildRemoved(CellParent<VH> childParent, int position, List<E> removedList) {
        CellParent<VH> parent = getParent();
        if (parent != null) {
            parent.handChildRemoved(childParent, position, removedList);
        }
    }

    public void invalidate(){
        CellParent<VH> parent = getParent();
        if (parent == null) {
            return;
        }
        parent.handChildChanged(parent,parent.getDataIndex(this),parent.getDataCount(), Collections.singletonList(this),Collections.singletonList(this));
    }
}

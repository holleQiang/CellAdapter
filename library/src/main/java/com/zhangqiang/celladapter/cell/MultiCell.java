package com.zhangqiang.celladapter.cell;

import android.view.ViewGroup;

import com.zhangqiang.celladapter.vh.ViewHolder;

public class MultiCell<T> extends Cell {

    private int layoutId;
    private T data;
    private ViewHolderBinder<T> viewHolderBinder;

    public MultiCell(int spanSize, int layoutId, T data, ViewHolderBinder<T> viewHolderBinder) {
        super(spanSize);
        this.layoutId = layoutId;
        this.data = data;
        this.viewHolderBinder = viewHolderBinder;
    }

    public MultiCell(int layoutId, T data, ViewHolderBinder<T> viewHolderBinder) {
        this.layoutId = layoutId;
        this.data = data;
        this.viewHolderBinder = viewHolderBinder;
    }

    @Override
    protected ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return ViewHolder.create(viewGroup, layoutId);
    }

    @Override
    public int getViewType() {
        return layoutId;
    }

    @Override
    protected void onBindViewHolder(ViewHolder vh) {
        if (data != null && viewHolderBinder != null) {
            viewHolderBinder.onBind(vh, data);
        }
    }

    public void setViewHolderBinder(ViewHolderBinder<T> viewHolderBinder) {
        this.viewHolderBinder = viewHolderBinder;
    }

    public void setData(T data) {
        this.data = data;
        invalidate();
    }

    public int getLayoutId() {
        return layoutId;
    }

    public T getData() {
        return data;
    }
}

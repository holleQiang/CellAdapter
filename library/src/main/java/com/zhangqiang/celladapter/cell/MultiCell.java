package com.zhangqiang.celladapter.cell;

import android.view.ViewGroup;

import com.zhangqiang.celladapter.vh.RVViewHolder;

public class MultiCell<T> extends Cell<RVViewHolder> {

    private int layoutId;
    private T data;
    private ViewHolderBinder<RVViewHolder,T> viewHolderBinder;

    public MultiCell(int layoutId, T data, ViewHolderBinder<RVViewHolder,T> viewHolderBinder) {
        this.layoutId = layoutId;
        this.data = data;
        this.viewHolderBinder = viewHolderBinder;
    }

    @Override
    protected RVViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return RVViewHolder.create(viewGroup,layoutId);
    }

    @Override
    public int getViewType() {
        return layoutId;
    }

    @Override
    protected void onBindViewHolder(RVViewHolder vh) {
        if (data!= null && viewHolderBinder != null) {
            viewHolderBinder.onBind(vh,data);
        }
    }

    public void setViewHolderBinder(ViewHolderBinder<RVViewHolder,T> viewHolderBinder) {
        this.viewHolderBinder = viewHolderBinder;
    }

    public void setData(T data) {
        this.data = data;
        invalidate();
    }
}

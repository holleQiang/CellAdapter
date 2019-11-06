package com.zhangqiang.celladapter;

public interface Adapter {

    void notifyDataSetChanged();

    void notifyItemRangeChanged(int positionStart, int itemCount);

    void notifyItemMoved(int fromPosition, int toPosition);

    void notifyItemRangeInserted(int positionStart, int itemCount);

    void notifyItemRangeRemoved(int positionStart, int itemCount);
}

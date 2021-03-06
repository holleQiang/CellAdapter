package com.zhangqiang.celladapter;

public interface ChangedNotifier {

    void notifyDataSetChanged();

    void notifyItemRangeChanged(int positionStart, int itemCount, Object payload);

    void notifyItemMoved(int fromPosition, int toPosition);

    void notifyItemRangeInserted(int positionStart, int itemCount);

    void notifyItemRangeRemoved(int positionStart, int itemCount);
}

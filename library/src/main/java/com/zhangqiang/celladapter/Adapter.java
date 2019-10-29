package com.zhangqiang.celladapter;

import android.support.annotation.Nullable;

public interface Adapter {

    void notifyDataSetChanged();

    void notifyItemChanged(int position);

    void notifyItemChanged(int position, @Nullable Object payload);

    void notifyItemRangeChanged(int positionStart, int itemCount);

    void notifyItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload);

    void notifyItemInserted(int position);

    void notifyItemMoved(int fromPosition, int toPosition);

    void notifyItemRangeInserted(int positionStart, int itemCount);

    void notifyItemRemoved(int position);

    void notifyItemRangeRemoved(int positionStart, int itemCount);
}

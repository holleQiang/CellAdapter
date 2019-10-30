package com.zhangqiang.celladapter.cell;

import com.zhangqiang.celladapter.vh.ViewHolder;

public interface ViewHolderBinder<T>{

    void onBind(ViewHolder viewHolder, T t);
}

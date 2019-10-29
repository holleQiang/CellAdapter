package com.zhangqiang.celladapter.cell;

import com.zhangqiang.celladapter.vh.ViewHolder;

public interface ViewHolderBinder<VH extends ViewHolder,T>{

    void onBind(VH viewHolder,T t);
}

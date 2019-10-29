package com.zhangqiang.celladapter.cell;

import com.zhangqiang.celladapter.observable.DataList;
import com.zhangqiang.celladapter.vh.ViewHolder;

import java.util.List;

public interface CellParent<VH extends ViewHolder> extends DataList<Cell<VH>> {

    <E extends Cell<VH>> void handChildChanged(CellParent<VH> childParent, int position, int count, List<E> oldList, List<E> newList);

    void handChildMoved(CellParent<VH> childParent, int fromPosition, int toPosition);

    <E extends Cell<VH>> void handChildAdded(CellParent<VH> childParent, int position, List<E> addedList);

    <E extends Cell<VH>> void handChildRemoved(CellParent<VH> childParent, int position, List<E> removedList);
}

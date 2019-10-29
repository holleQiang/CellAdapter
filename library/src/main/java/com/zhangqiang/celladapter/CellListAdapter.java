package com.zhangqiang.celladapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhangqiang.celladapter.cell.Cell;
import com.zhangqiang.celladapter.cell.CellRoot;
import com.zhangqiang.celladapter.observable.DataList;
import com.zhangqiang.celladapter.vh.ViewHolder;

import java.util.List;

@SuppressWarnings("unchecked")
public class CellListAdapter extends BaseAdapter implements Adapter, DataList<Cell> {

    private final CellRoot cellRoot = new CellRoot(this);
    private final DataList<Cell> delegate = cellRoot;
    private CellAdapterHelper<ViewHolder> cellAdapterHelper = new CellAdapterHelper<>(cellRoot);

    @Override
    public int getCount() {
        return cellAdapterHelper.getItemCount();
    }

    @Override
    public Object getItem(int position) {
        return cellAdapterHelper.getCellAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            int itemViewType = cellAdapterHelper.getItemViewType(position);
            ViewHolder viewHolder = cellAdapterHelper.onCreateViewHolder(parent, itemViewType);
            convertView = viewHolder.getView();
            convertView.setTag(R.id.tag_key_view_holder,viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag(R.id.tag_key_view_holder);
        cellAdapterHelper.onBindViewHolder(viewHolder,position);

        new CellRVAdapter().notifyDataSetChanged();
        return convertView;
    }

    @Override
    public void notifyItemChanged(int position) {
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyItemChanged(int position, @Nullable Object payload) {
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyItemInserted(int position) {
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyItemMoved(int fromPosition, int toPosition) {
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyItemRangeInserted(int positionStart, int itemCount) {
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyItemRemoved(int position) {
        this.notifyDataSetChanged();
    }

    @Override
    public void notifyItemRangeRemoved(int positionStart, int itemCount) {
        this.notifyDataSetChanged();
    }

    @Override
    public void addDataAtIndex(Cell data, int position) {
        delegate.addDataAtIndex(data, position);
    }

    @Override
    public <E extends Cell> void addDataListAtIndex(List<E> dataList, int position) {
        delegate.addDataListAtIndex(dataList, position);
    }

    @Override
    public void addDataAtLast(Cell data) {
        delegate.addDataAtLast(data);
    }

    @Override
    public void addDataAtFirst(Cell data) {
        delegate.addDataAtFirst(data);
    }

    @Override
    public <E extends Cell> void addDataListAtLast(List<E> dataList) {
        delegate.addDataListAtLast(dataList);
    }

    @Override
    public <E extends Cell> void addDataListAtFirst(List<E> dataList) {
        delegate.addDataListAtFirst(dataList);
    }

    @Override
    public void removeDataAtIndex(int position) {
        delegate.removeDataAtIndex(position);
    }

    @Override
    public void removeData(Cell data) {
        delegate.removeData(data);
    }

    @Override
    public void removeDataFrom(int position, int count) {
        delegate.removeDataFrom(position, count);
    }

    @Override
    public void removeDataFrom(int position) {
        delegate.removeDataFrom(position);
    }

    @Override
    public void removeAll() {
        delegate.removeAll();
    }

    @Override
    public int getDataIndex(Cell data) {
        return delegate.getDataIndex(data);
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public <E extends Cell> void setDataList(List<E> dataList) {
        delegate.setDataList(dataList);
    }

    @Override
    public int getDataCount() {
        return delegate.getDataCount();
    }

    @Override
    public Cell getDataAt(int position) {
        return delegate.getDataAt(position);
    }

    @Override
    public void swap(int fromPosition, int toPosition) {
        delegate.swap(fromPosition, toPosition);
    }

    @Override
    public void replace(int position, Cell data) {
        delegate.replace(position, data);
    }

    @Override
    public <E extends Cell> void replace(int position, List<E> dataList) {
        delegate.replace(position, dataList);
    }

    @Override
    public List<Cell> subList(int position, int count) {
        return delegate.subList(position, count);
    }
}

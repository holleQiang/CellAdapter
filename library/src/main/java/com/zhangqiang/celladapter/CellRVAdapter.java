package com.zhangqiang.celladapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.zhangqiang.celladapter.cell.Cell;
import com.zhangqiang.celladapter.cell.CellRoot;
import com.zhangqiang.celladapter.observable.DataList;
import com.zhangqiang.celladapter.vh.ViewHolder;

import java.util.List;

public class CellRVAdapter extends RecyclerView.Adapter<ViewHolder> implements Adapter, DataList<Cell> {

    private final CellRoot cellRoot = new CellRoot(this);
    private final DataList<Cell> delegate = cellRoot;
    private CellAdapterHelper cellAdapterHelper = new CellAdapterHelper(cellRoot);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return cellAdapterHelper.onCreateViewHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder rvViewHolder, int position) {
        cellAdapterHelper.onBindViewHolder(rvViewHolder, position);
    }

    @Override
    public int getItemCount() {
        return cellAdapterHelper.getItemCount();
    }

    public Cell getCellAt(int position) {
        return cellAdapterHelper.getCellAt(position);
    }

    @Override
    public int getItemViewType(int position) {
        return cellAdapterHelper.getItemViewType(position);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {

            int adapterPosition = holder.getAdapterPosition();
            Cell cell = cellAdapterHelper.getCellAt(adapterPosition);
            if (cell.getSpanSize() == Cell.FULL_SPAN) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                if (!p.isFullSpan()) {
                    p.setFullSpan(true);
                }
            }
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    int spanCount = gridLayoutManager.getSpanCount();
                    Cell cell = cellAdapterHelper.getCellAt(position);
                    int spanSize = cell.getSpanSize();
                    if (spanSize == Cell.FULL_SPAN) {
                        spanSize = spanCount;
                    } else {
                        spanSize = Math.max(1, spanSize);
                        spanSize = Math.min(spanSize, spanCount);
                    }
                    return spanSize;
                }
            });
        }
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

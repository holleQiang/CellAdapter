package com.zhangqiang.celladapter;

import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import android.view.ViewGroup;

import com.zhangqiang.celladapter.cell.Cell;
import com.zhangqiang.celladapter.cell.CellRoot;
import com.zhangqiang.celladapter.vh.ViewHolder;

public class CellAdapterHelper<VH extends ViewHolder> {


    private CellRoot<VH> cellRoot;

    public CellAdapterHelper(CellRoot<VH> cellParent) {
        this.cellRoot = cellParent;
    }

    private SparseIntArray viewTypeDepends = new SparseIntArray();

    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int viewTypeDependOn = viewTypeDepends.get(viewType);
        int cellCount = cellRoot.getTotalCellCount();
        for (int i = 0; i < cellCount; i++) {
            Cell<VH> cell = cellRoot.getCellAt(i);
            if (cell.getViewType() == viewTypeDependOn) {
                return cell.createViewHolder(viewGroup);
            }
        }
        throw new RuntimeException("cannot find getViewType " + viewTypeDependOn);
    }

    public void onBindViewHolder(@NonNull VH rvViewHolder, int position) {
        Cell<VH> cell = cellRoot.getCellAt(position);
        cell.bindViewHolder(rvViewHolder);
    }

    public Cell<VH> getCellAt(int position) {
        return cellRoot.getCellAt(position);
    }

    public int getItemCount() {
        return cellRoot.getTotalCellCount();
    }

    public int getItemViewType(int position) {
        Cell<VH> cell = cellRoot.getCellAt(position);
        int viewTypeDependOn = cell.getViewType();
        int viewType = viewTypeDepends.indexOfValue(viewTypeDependOn);
        if (viewType == -1) {
            viewType = viewTypeDepends.size();
            viewTypeDepends.put(viewType,viewTypeDependOn);
        }
        return viewType;
    }
}

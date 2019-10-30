package com.zhangqiang.celladapter.drag;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.zhangqiang.celladapter.CellRVAdapter;
import com.zhangqiang.celladapter.cell.Cell;
import com.zhangqiang.celladapter.cell.CellParent;


public abstract class CellDragHelper extends ItemTouchHelper.Callback {

    private CellRVAdapter cellRVAdapter;

    public CellDragHelper(CellRVAdapter cellRVAdapter) {
        this.cellRVAdapter = cellRVAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        Cell fromCell = cellRVAdapter.getCellAt(viewHolder.getAdapterPosition());
        Cell toCell = cellRVAdapter.getCellAt(viewHolder1.getAdapterPosition());
        CellParent fromCellParent = fromCell.getParent();
        if (fromCellParent != null && fromCellParent == toCell.getParent()) {

            int fromPosition = fromCellParent.getDataIndex(fromCell);
            int toPosition = fromCellParent.getDataIndex(toCell);
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    fromCellParent.swap(i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    fromCellParent.swap(i, i - 1);
                }
            }
            return true;
        }
        return false;
    }

}

package com.zhangqiang.celladapter.cell.expand;

import com.zhangqiang.celladapter.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class ExpandHelper {

    private boolean expand = true;
    private Cell cell;
    private OnExpandStateChangedListener onExpandStateChangedListener;
    private List<Cell> unExpandCells;

    public ExpandHelper(Cell cell) {
        this.cell = cell;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        if (this.expand != expand) {
            this.expand = expand;
            if (!expand) {
                unExpandCells = new ArrayList<>();
                int dataCount = cell.getDataCount();
                for (int i = 0; i < dataCount; i++) {
                    unExpandCells.add(cell.getDataAt(i));
                }
                cell.removeAll();
            }else {
                cell.addDataListAtFirst(unExpandCells);
            }
            if (onExpandStateChangedListener != null) {
                onExpandStateChangedListener.onExpandStateChanged(expand);
            }
        }
    }

    public void toggleExpand(){
        setExpand(!isExpand());
    }

    public void setOnExpandStateChangedListener(OnExpandStateChangedListener onExpandStateChangedListener) {
        this.onExpandStateChangedListener = onExpandStateChangedListener;
    }
}

package com.zhangqiang.celladapter.cell;

import android.os.Looper;

import com.zhangqiang.celladapter.vh.ViewHolder;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CellUtils {

    private static final Deque<Object> mCellDeque = new LinkedList<>();


    public static <VH extends ViewHolder> int indexOfChild(Cell<VH> cellParent, Cell<VH> child) {

        checkThread();

        int index = -1;
        if (child == null) {
            return index;
        }
        mCellDeque.clear();
        addChildToStack(cellParent);
        Cell<VH> tempCell;
        while ((tempCell = (Cell<VH>) mCellDeque.pollLast()) != null) {
            index++;
            if (tempCell == child) {
                return index;
            }
            addChildToStack(tempCell);
        }
        return -1;
    }

    public static <VH extends ViewHolder> int getChildCount(Cell<VH> cellParent) {

        checkThread();

        int count = 0;
        if (cellParent == null) {
            return count;
        }
        mCellDeque.clear();
        addChildToStack(cellParent);
        Cell<VH> tempCell;
        while ((tempCell = (Cell<VH>) mCellDeque.pollLast()) != null) {
            count++;
            addChildToStack(tempCell);
        }
        return count;
    }


    public static <VH extends ViewHolder> int getChildCount(Cell<VH> cellParent, int position, int offset) {

        checkThread();

        int count = 0;
        if (cellParent == null) {
            return count;
        }
        mCellDeque.clear();
        addChildToStack(cellParent,position,offset);
        Cell<VH> tempCell;
        while ((tempCell = (Cell<VH>) mCellDeque.pollLast()) != null) {
            count++;
            addChildToStack(tempCell);
        }
        return count;
    }


    public static <VH extends ViewHolder> Cell<VH> getChildAt(Cell<VH> cellParent,int position) {

        checkThread();

        if (cellParent == null) {
            return null;
        }
        int index = -1;
        mCellDeque.clear();
        addChildToStack(cellParent);
        Cell<VH> tempCell;
        while ((tempCell = (Cell<VH>) mCellDeque.pollLast()) != null) {
            index++;
            if (position == index) {
                return tempCell;
            }
            addChildToStack(tempCell);
        }
        throw new IndexOutOfBoundsException("position is " + position + ",count is : " + (index + 1));
    }

    public static <C extends Cell> int getCellCount(List<C> cellList) {
        if (cellList == null) {
            return 0;
        }
        int count = 0;
        for (int i = cellList.size() - 1; i >= 0; i--) {
            count++;
            count += getChildCount(cellList.get(i));
        }
        return count;
    }

    private static <VH extends ViewHolder> void addChildToStack(Cell<VH> cellParent) {
        int childCount = cellParent.getDataCount();
        for (int i = childCount - 1; i >= 0; i--) {
            mCellDeque.offerLast(cellParent.getDataAt(i));
        }
    }

    private static <VH extends ViewHolder> void addChildToStack(Cell<VH> cellParent, int from, int offset) {
        int childCount = cellParent.getDataCount();
        if (from < 0 || from + offset >= childCount) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = from + offset - 1; i >= from; i--) {
            mCellDeque.offerLast(cellParent.getDataAt(i));
        }
    }

    private static void checkThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("this method must be called within main-thread");
        }
    }
}

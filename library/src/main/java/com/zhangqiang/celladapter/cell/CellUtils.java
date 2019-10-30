package com.zhangqiang.celladapter.cell;

import android.os.Looper;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CellUtils {

    private static final Deque<Cell> mCellDeque = new LinkedList<>();


    public static int indexOfChild(Cell cellParent, Cell child) {

        try {

            checkThread();

            int index = -1;
            if (child == null) {
                return index;
            }
            addChildToStack(cellParent);
            Cell tempCell;
            while ((tempCell = mCellDeque.pollLast()) != null) {
                index++;
                if (tempCell == child) {
                    return index;
                }
                addChildToStack(tempCell);
            }
            return -1;
        } finally {
            mCellDeque.clear();
        }
    }

    public static int getChildCount(Cell cellParent) {

        try {

            checkThread();

            int count = 0;
            if (cellParent == null) {
                return count;
            }
            addChildToStack(cellParent);
            Cell tempCell;
            while ((tempCell = mCellDeque.pollLast()) != null) {
                count++;
                addChildToStack(tempCell);
            }
            return count;
        } finally {
            mCellDeque.clear();
        }
    }


    public static int getChildCount(Cell cellParent, int position, int offset) {

        try {
            checkThread();

            int count = 0;
            if (cellParent == null) {
                return count;
            }
            addChildToStack(cellParent, position, offset);
            Cell tempCell;
            while ((tempCell = mCellDeque.pollLast()) != null) {
                count++;
                addChildToStack(tempCell);
            }
            return count;
        }finally {
            mCellDeque.clear();
        }
    }


    public static Cell getChildAt(Cell cellParent, int position) {

        try {

            checkThread();

            if (cellParent == null) {
                return null;
            }
            int index = -1;
            addChildToStack(cellParent);
            Cell tempCell;
            while ((tempCell = mCellDeque.pollLast()) != null) {
                index++;
                if (position == index) {
                    return tempCell;
                }
                addChildToStack(tempCell);
            }
            throw new IndexOutOfBoundsException("position is " + position + ",count is : " + (index + 1));
        }finally {
            mCellDeque.clear();
        }
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

    private static void addChildToStack(Cell cellParent) {
        int childCount = cellParent.getDataCount();
        for (int i = childCount - 1; i >= 0; i--) {
            mCellDeque.offerLast(cellParent.getDataAt(i));
        }
    }

    private static void addChildToStack(Cell cellParent, int from, int offset) {
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

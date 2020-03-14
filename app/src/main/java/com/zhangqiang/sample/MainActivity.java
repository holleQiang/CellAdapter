package com.zhangqiang.sample;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.zhangqiang.celladapter.CellListAdapter;
import com.zhangqiang.celladapter.CellRVAdapter;
import com.zhangqiang.celladapter.cell.Cell;
import com.zhangqiang.celladapter.cell.CellParent;
import com.zhangqiang.celladapter.cell.ViewHolderBinder;
import com.zhangqiang.celladapter.cell.MultiCell;
import com.zhangqiang.celladapter.cell.action.Action;
import com.zhangqiang.celladapter.cell.expand.ExpandHelper;
import com.zhangqiang.celladapter.cell.expand.OnExpandStateChangedListener;
import com.zhangqiang.celladapter.drag.CellDragHelper;
import com.zhangqiang.celladapter.vh.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CellRVAdapter cellRVAdapter = new CellRVAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cellRVAdapter);

        cellRVAdapter.setDataList(createCellList());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cellRVAdapter.setDataList(createCellList());
            }
        }, 2000);
//
//        final CompoundButton btExchange = findViewById(R.id.bt_exchange);
//        final ListView listView = findViewById(R.id.mListView);
//        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                btExchange.setText(isChecked ? "RecyclerView" : "ListView");
//                recyclerView.setVisibility(isChecked ? View.VISIBLE : View.GONE);
//                listView.setVisibility(!isChecked ? View.VISIBLE : View.GONE);
//            }
//        };
//        btExchange.setOnCheckedChangeListener(listener);
//        listener.onCheckedChanged(btExchange, btExchange.isChecked());
//        final CellListAdapter cellListAdapter = new CellListAdapter();

//        cellListAdapter.setDataList(createCellList());
//        listView.setAdapter(cellListAdapter);

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CellDragHelper(cellRVAdapter) {
//            @Override
//            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//                return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0);
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//            }
//        });
//        itemTouchHelper.attachToRecyclerView(recyclerView);
//        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
//        if (itemAnimator instanceof SimpleItemAnimator) {
//            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
//        }
    }


    private Cell newAddCell(final String text) {

        final MultiCell<String> multiCell = new MultiCell<>(Cell.FULL_SPAN,R.layout.item_test_add, text, null);
        final ExpandHelper expandHelper = new ExpandHelper(multiCell);
        multiCell.setViewHolderBinder(new ViewHolderBinder<String>() {
            @Override
            public void onBind(final ViewHolder viewHolder, final String s) {
                viewHolder.setText(R.id.bt_expand, expandHelper.isExpand() ? "收起" : "展开");
                viewHolder.setOnClickListener(R.id.bt_expand, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expandHelper.toggleExpand();
                    }
                });
                expandHelper.setOnExpandStateChangedListener(new OnExpandStateChangedListener() {
                    @Override
                    public void onExpandStateChanged(boolean expand) {
                        multiCell.invalidate();
                    }
                });
                viewHolder.setOnClickListener(R.id.bt_add, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cell data = newAddCell(s);
                        multiCell.addDataAtLast(data);
                    }
                });

                viewHolder.setOnClickListener(R.id.bt_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CellParent parent = multiCell.getParent();
                        if (parent != null) {
                            parent.removeData(multiCell);
                        }
                    }
                });

                viewHolder.setOnClickListener(R.id.bt_update, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        multiCell.invalidate(new Action() {
                            @Override
                            public void onBind(ViewHolder viewHolder) {
                                viewHolder.setText(R.id.tv_info, s + "&&" + new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));

                            }
                        });
                    }
                });
                viewHolder.setText(R.id.tv_info, s + "&&" + new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));


                View view = viewHolder.getView();
                Log.i("Test", this.toString() + "=======onBind========="+view);
                View.OnAttachStateChangeListener listener = (View.OnAttachStateChangeListener) view.getTag(R.id.key_attach_listener);
                if (listener != null) {
                    view.removeOnAttachStateChangeListener(listener);
                }
                listener = new View.OnAttachStateChangeListener() {

                    @Override
                    public void onViewAttachedToWindow(View v) {
                        Log.i("Test", this.toString() + "====="+ viewHolder.getAdapterPosition() +"==onViewAttachedToWindow=========" + v );
                    }

                    @Override
                    public void onViewDetachedFromWindow(View v) {
                        Log.i("Test", this.toString() + "===="+ viewHolder.getAdapterPosition() +"==onViewDetachedFromWindow==========" + v);
                    }
                };
                view.setTag(R.id.key_attach_listener, listener);
                view.addOnAttachStateChangeListener(listener);
            }
        });
        return multiCell;

    }


    private List<Cell> createCellList() {

        final List<Cell> dataList = new ArrayList<>();
        dataList.add(newAddCell("0"));
        dataList.add(newAddCell("1"));
        dataList.add(newAddCell("2"));
        dataList.add(newAddCell("3"));
//        dataList.add(newAddCell("4"));
//        dataList.add(newAddCell("5"));
//        dataList.add(newAddCell("6"));
//        dataList.add(newAddCell("7"));
//        dataList.add(newAddCell("8"));
//        dataList.add(newAddCell("9"));
        return dataList;
    }
}

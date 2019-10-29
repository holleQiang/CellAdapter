package com.zhangqiang.sample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import com.zhangqiang.celladapter.CellListAdapter;
import com.zhangqiang.celladapter.CellRVAdapter;
import com.zhangqiang.celladapter.cell.Cell;
import com.zhangqiang.celladapter.cell.CellParent;
import com.zhangqiang.celladapter.cell.ViewHolderBinder;
import com.zhangqiang.celladapter.cell.MultiCell;
import com.zhangqiang.celladapter.vh.RVViewHolder;
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
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(cellRVAdapter);

        List<Cell<RVViewHolder>> dataList = new ArrayList<>();
        dataList.add(newAddCell("1"));
        dataList.add(newAddCell("2"));
        dataList.add(newAddCell("3"));
        dataList.add(newAddCell("4"));
        dataList.add(newAddCell("5"));

        cellRVAdapter.setDataList(dataList);

        final CompoundButton btExchange = findViewById(R.id.bt_exchange);
        final ListView listView = findViewById(R.id.mListView);
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btExchange.setText(isChecked ? "RecyclerView" : "ListView");
                recyclerView.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                listView.setVisibility(!isChecked ? View.VISIBLE : View.GONE);
            }
        };
        btExchange.setOnCheckedChangeListener(listener);
        listener.onCheckedChanged(btExchange, btExchange.isChecked());
//        final CellListAdapter cellListAdapter = new CellListAdapter();
//
//        dataList = new ArrayList<>();
//        dataList.add(newAddCell("1"));
//        dataList.add(newAddCell("2"));
//        dataList.add(newAddCell("3"));
//        dataList.add(newAddCell("4"));
//        dataList.add(newAddCell("5"));
//        cellListAdapter.setDataList(dataList);
//        listView.setAdapter(cellListAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                Cell<RVViewHolder> fromCell = cellRVAdapter.getCellAt(viewHolder.getAdapterPosition());
                Cell<RVViewHolder> toCell = cellRVAdapter.getCellAt(viewHolder1.getAdapterPosition());
                CellParent<RVViewHolder> fromCellParent = fromCell.getParent();
                if (fromCellParent != null && fromCellParent == toCell.getParent()) {

                    int fromPosition = fromCellParent.getDataIndex(fromCell);
                    int toPosition = fromCellParent.getDataIndex(toCell);
                    fromCellParent.swap(fromPosition, toPosition);
                    return true;
                }
                return false;
            }

            @Override
            public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y) {
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);

//                Cell<RVViewHolder> fromCell = cellRVAdapter.getCellAt(fromPos);
//                Cell<RVViewHolder> toCell = cellRVAdapter.getCellAt(toPos);
//                CellParent<RVViewHolder> parent = fromCell.getParent();
//                if (parent == toCell.getParent()) {
//                    int fromPosition = parent.getDataIndex(fromCell);
//                    int toPosition = parent.getDataIndex(toCell);
//                    parent.swap(fromPosition, toPosition);
//                }
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    private Cell<RVViewHolder> newAddCell(final String text) {

        final MultiCell<String> multiCell = new MultiCell<>(R.layout.item_test_add, text, null);

        multiCell.setViewHolderBinder(new ViewHolderBinder<RVViewHolder, String>() {
            @Override
            public void onBind(RVViewHolder viewHolder, final String s) {
                viewHolder.setOnClickListener(R.id.bt_add, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cell<RVViewHolder> data = newAddCell(s);
                        multiCell.addDataAtLast(data);
                    }
                });

                viewHolder.setOnClickListener(R.id.bt_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CellParent<RVViewHolder> parent = multiCell.getParent();
                        if (parent != null) {
                            parent.removeData(multiCell);
                        }
                    }
                });

                viewHolder.setOnClickListener(R.id.bt_update, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        multiCell.invalidate();
                    }
                });
                viewHolder.setText(R.id.tv_info, s + "&&" + new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
            }
        });
        return multiCell;

    }
}

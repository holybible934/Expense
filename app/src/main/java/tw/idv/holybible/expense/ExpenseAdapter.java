package tw.idv.holybible.expense;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chchang on 2017/12/2.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private final Cursor cursor;
    OnExpenseClickListener onExpenseClickListener;

    public void setOnExpenseClickListener(OnExpenseClickListener onExpenseClickListener) {
        this.onExpenseClickListener = onExpenseClickListener;
    }


    public ExpenseAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, null);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        cursor.moveToPosition(position);
        final Expense expense = new Expense(cursor);
        holder.setModel(expense);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onExpenseClickListener != null) {
                    onExpenseClickListener.OnClick(position,expense);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView expNameTextView;
        private final TextView dateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.row_date);
            expNameTextView = itemView.findViewById(R.id.row_exp_name);
        }

        public void setModel(Expense expense) {
            dateTextView.setText(expense.getCdate());
            expNameTextView.setText(expense.getExpName());
            itemView.setTag(expense);
        }
    }

    public interface OnExpenseClickListener {
        void OnClick(int position, Expense expense);
    }
}
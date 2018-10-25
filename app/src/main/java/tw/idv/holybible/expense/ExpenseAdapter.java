package tw.idv.holybible.expense;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by chchang on 2017/12/2.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private Cursor cursor;
    private OnExpenseClickListener onExpenseClickListener;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        cursor.moveToPosition(position);
        final Expense expense = new Expense(cursor);
        holder.setModel(expense);
        holder.expNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onExpenseClickListener != null) {
                    onExpenseClickListener.OnClick(position, expense);
                }
            }
        });
        holder.agreeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                expense.setAgree(isChecked);
                if (onExpenseClickListener != null) {
                    onExpenseClickListener.OnCheckedChange(
                            holder.agreeCheckbox, expense);
                }
            }
        });
    }

    public void updateCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount();
        }
        else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView expNameTextView;
        private final TextView dateTextView;
        private final CheckBox agreeCheckbox;

        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.row_date);
            expNameTextView = itemView.findViewById(R.id.row_exp_name);
            agreeCheckbox = itemView.findViewById(R.id.row_agree);

        }

        public void setModel(Expense expense) {
            dateTextView.setText(expense.getCdate());
            expNameTextView.setText(expense.getExpName());
            agreeCheckbox.setChecked(expense.isAgree());
            itemView.setTag(expense);
        }
    }

    public interface OnExpenseClickListener {
        void OnClick(int position, Expense expense);
        void OnCheckedChange(View view, Expense expense);
    }
}

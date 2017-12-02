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
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        int id = cursor.getInt(cursor.getColumnIndex(ExpenseContract.COL_ID));
        String date = cursor.getString(cursor.getColumnIndex(ExpenseContract.COL_DATE));
        String expName = cursor.getString(cursor.getColumnIndex(ExpenseContract.COL_EXPENSE_NAME));
        int amount = cursor.getInt(cursor.getColumnIndex(ExpenseContract.COL_AMOUNT));

        holder.dateTextView.setText(date);
        holder.expNameTextView.setText(expName);
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
    }
}

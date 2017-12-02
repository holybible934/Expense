package tw.idv.holybible.expense;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by chchang on 2017/12/2.
 */

public class ExpenseAdapter {

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

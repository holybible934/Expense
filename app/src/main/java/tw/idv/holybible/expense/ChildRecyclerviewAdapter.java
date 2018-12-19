package tw.idv.holybible.expense;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChildRecyclerviewAdapter extends RecyclerView.Adapter<ChildRecyclerviewAdapter.ChildeRecyclerViewHolder> {

    @NonNull
    @Override
    public ChildeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child, parent, false);
        return new ChildeRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildeRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ChildeRecyclerViewHolder extends RecyclerView.ViewHolder {

        public ChildeRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

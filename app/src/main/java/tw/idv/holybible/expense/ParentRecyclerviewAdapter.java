package tw.idv.holybible.expense;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ParentRecyclerviewAdapter extends RecyclerView.Adapter<ParentRecyclerviewAdapter.ParentRecyclerViewHolder> {

    @NonNull
    @Override
    public ParentRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent, parent, false);
        return new ParentRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ParentRecyclerViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView childRcvw;

        public ParentRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            childRcvw = itemView.findViewById(R.id.childRcvw);
            childRcvw.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            ChildRecyclerviewAdapter adapter = new ChildRecyclerviewAdapter();
            childRcvw.setAdapter(adapter);
        }
    }
}

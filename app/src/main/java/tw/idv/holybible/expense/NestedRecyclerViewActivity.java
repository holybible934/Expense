package tw.idv.holybible.expense;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NestedRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView parentRcvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recycler_view);

        parentRcvw = findViewById(R.id.parentRcvw);
        parentRcvw.setHasFixedSize(true);
        parentRcvw.setLayoutManager(new LinearLayoutManager(this));
        ParentRecyclerviewAdapter adapter = new ParentRecyclerviewAdapter();
        parentRcvw.setAdapter(adapter);

    }
}

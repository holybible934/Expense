package tw.idv.holybible.expense;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class Main2Activity extends AppCompatActivity implements ExpenseAdapter.OnExpenseClickListener {

    private ExpenseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = new ExpenseHelper(this, "expense.db", null, 1);
        Cursor cursor = helper.getReadableDatabase().query(ExpenseContract.EXPENSE_TABLE,
                null, null, null, null, null, null);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExpenseAdapter(cursor));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, AddExpenseActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Cursor cursor = helper.getReadableDatabase().query(ExpenseContract.EXPENSE_TABLE,
                null, null, null, null, null, null);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ExpenseAdapter adapter = new ExpenseAdapter(cursor);
        recyclerView.setAdapter(adapter);
        adapter.setOnExpenseClickListener(this);
    }

    @Override
    public void OnClick(int position, Expense expense) {

    }
}

package tw.idv.holybible.expense;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import static android.Manifest.permission.READ_CONTACTS;

public class Main2Activity extends AppCompatActivity implements ExpenseAdapter.OnExpenseClickListener {

    private static final String TAG = Main2Activity.class.getSimpleName();
    private static final int REQUEST_PERMISSION_CONTACTS = 19;
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

        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] { READ_CONTACTS}, REQUEST_PERMISSION_CONTACTS);
        }
        else {
            readContacts();
        }


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
//        Cursor cursor = helper.getReadableDatabase().query(ExpenseContract.EXPENSE_TABLE,
//                null, null, null, null, null, null);
        Cursor cursor = getContentResolver().query(
                ExpenseContract.CONTENT_URI, null,  null, //ExpenseContract.COL_ID + " = ?",
                null, null); //new String[] { "2" }, null);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ExpenseAdapter adapter = new ExpenseAdapter(cursor);
        recyclerView.setAdapter(adapter);
        adapter.setOnExpenseClickListener(this);
    }

    @Override
    public void OnClick(int position, Expense expense) {
        Log.d(TAG, "OnItemClick: " + expense.getExpName() + ", " + expense.getAmount());
        Intent detail = new Intent(Main2Activity.this, DetailActivity.class);
        detail.putExtra("EXTRA_EXPENSE", expense);
        startActivity(detail);
    }

    @Override
    public void OnCheckedChange(View view, Expense expense) {
        Log.d(TAG, "OnCheckedChange: " + expense.getExpName() + ", " + expense.isAgree());
        int result = helper.getWritableDatabase().update(
                ExpenseContract.EXPENSE_TABLE,
                expense.getContentValues(),
                ExpenseContract.COL_ID + "= ?",
                new String[] { String.valueOf(expense.getId()) }
        );
        Log.d(TAG, "OnCheckedChange: number of affected rows is " + result);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        if ((requestCode == REQUEST_PERMISSION_CONTACTS) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            readContacts();
        }
    }

    private void readContacts() {
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
    }
}

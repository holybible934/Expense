package tw.idv.holybible.expense;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private static final String TAG = AddExpenseActivity.class.getSimpleName();
    private EditText edDate;
    private EditText edAmount;
    private EditText edExpName;
    private Button addExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        findViews();
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData(v);
            }
        });
    }

    private void findViews() {
        edExpName = findViewById(R.id.edExpName);
        edAmount = findViewById(R.id.edAmount);
        edDate = findViewById(R.id.edDate);
        addExpense = findViewById(R.id.btnAddExp);
    }

    public void insertData(View view) {
        String date = edDate.getText().toString();
        String expName = edExpName.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());

        Log.d(TAG, "insertData: " + expName + ", " + amount + ", " + date );

        ExpenseHelper expenseHelper = new ExpenseHelper(this);
        ContentValues values = new ContentValues();
        values.put(ExpenseContract.COL_DATE, date);
        values.put(ExpenseContract.COL_EXPENSE_NAME, expName);
        values.put(ExpenseContract.COL_AMOUNT, amount);
        values.put(ExpenseContract.COL_AGREE, 0);

        ExpenseInsertService.insert(this, values);
//
//        Uri uri = getContentResolver().insert(ExpenseContract.CONTENT_URI, values);
//        if (uri != null) {
//            Log.d(TAG, "insertData: completed. Uri is " + uri.toString());
//            finish();
//        }
//        else {
//            new AlertDialog.Builder(this).setMessage("Adding new expense failed")
//                    .setPositiveButton("OK", null)
//                    .show();
//        }
    }
}

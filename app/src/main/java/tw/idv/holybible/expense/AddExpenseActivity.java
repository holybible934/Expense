package tw.idv.holybible.expense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddExpenseActivity extends AppCompatActivity {

    private static final String TAG = AddExpenseActivity.class.getSimpleName();
    private EditText edDate;
    private EditText edAmount;
    private EditText edExpName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        findViews();

    }

    private void findViews() {
        edExpName = findViewById(R.id.edExpName);
        edAmount = findViewById(R.id.edAmount);
        edDate = findViewById(R.id.edDate);
    }

    public void insertData(View view) {
        String date = edDate.getText().toString();
        String expName = edExpName.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());

        Log.d(TAG, "insertData: " + expName + ", " + amount + ", " + date );

    }
}

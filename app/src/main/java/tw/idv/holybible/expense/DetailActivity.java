package tw.idv.holybible.expense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView date;
    private TextView expName;
    private TextView amount;
    private Expense expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        date = findViewById(R.id.txtvwDetailDate);
        expName = findViewById(R.id.txtvwDetailName);
        amount = findViewById(R.id.txtvwDetailAmount);

        expense = getIntent().getParcelableExtra("EXTRA_EXPENSE");
        date.setText(expense.getCdate());
        expName.setText(expense.getExpName());
        amount.setText(String.valueOf(expense.getAmount()));
    }
}

package tw.idv.holybible.expense;

import android.content.ContentUris;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class DetailActivity extends AppCompatActivity {

    private TextView date;
    private TextView expName;
    private TextView amount;
    private Expense expense;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Uri uri = ContentUris.withAppendedId(ExpenseContract.CONTENT_URI, expense.getId());
                getContentResolver().delete(uri, null, null);
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

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

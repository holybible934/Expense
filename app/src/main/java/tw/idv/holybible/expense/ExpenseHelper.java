package tw.idv.holybible.expense;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chchang on 2017/11/25.
 */

public class ExpenseHelper extends SQLiteOpenHelper {
    private Context context;
    private String TAG = ExpenseHelper.class.getSimpleName();

    public ExpenseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public ExpenseHelper(Context context) {
        this(context, ExpenseContract.DB_NAME, null, ExpenseContract.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ExpenseContract.CREATE_EXPENSE_TABLE);
        // Read JSON file to initialize database
        InputStream inputStream = context.getResources().openRawResource(R.raw.expenses);
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffer = new BufferedReader(inputReader);
        try {
            String line = buffer.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line);
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onCreate: Create DB and load initial data from res/raw/expenses.json");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

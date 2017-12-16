package tw.idv.holybible.expense;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        readJsonData(db);

    }

    private void readJsonData(SQLiteDatabase db) {
        // Read JSON file to initialize database
        InputStream inputStream = context.getResources().openRawResource(R.raw.expenses);
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffer = new BufferedReader(inputReader);
        StringBuilder sb = new StringBuilder();
        try {
            String line = buffer.readLine();
            while (line != null) {
                sb.append(line);
                line = buffer.readLine();
            }
            Log.d(TAG, "readJsonData: whole StringBuilder: " + sb.toString());
            // Parse StringBuilder and insert into Database
            JSONObject json = new JSONObject(sb.toString());
            JSONArray array = json.getJSONArray("expenses");
            ContentValues values = new ContentValues();

            for (int i = 0; i < array.length(); i++) {
                JSONObject record = array.getJSONObject(i);
                values.put("cdate", record.getString("cdate"));
                values.put("expName", record.getString("expName"));
                values.put("amount", record.getInt("amount"));
            }
            db.insert(ExpenseContract.EXPENSE_TABLE, null,values);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

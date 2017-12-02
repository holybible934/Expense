package tw.idv.holybible.expense;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chchang on 2017/11/25.
 */

public class ExpenseHelper extends SQLiteOpenHelper {
    public ExpenseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ExpenseHelper(Context context) {
        this(context, ExpenseContract.DB_NAME, null, ExpenseContract.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ExpenseContract.CREATE_EXPENSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

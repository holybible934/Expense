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

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cmd = "CREATE TABLE expense (_id INTEGER PRIMARY KEY, " +
                "cdate DATETIME NOT NULL, " +
                "exp_Name VARCHAR, " +
                "amount INTEGER, " +
                "CREATE_DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL)";
        db.execSQL(cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

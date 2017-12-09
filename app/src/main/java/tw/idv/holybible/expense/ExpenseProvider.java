package tw.idv.holybible.expense;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chchang on 2017/12/9.
 */

public class ExpenseProvider extends ContentProvider {

    private ExpenseHelper helper;
    private final String TAG = ExpenseProvider.class.getSimpleName();

    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int EXPENSES = 200;

    private static final int EXPENSES_WITH_ID = 100;

    static {
        sUriMatcher.addURI(ExpenseContract.AUTHORITY, ExpenseContract.EXPENSE_TABLE, EXPENSES);
        sUriMatcher.addURI(ExpenseContract.AUTHORITY, ExpenseContract.EXPENSE_TABLE + "/#", EXPENSES_WITH_ID);
    }

    @Override
    public boolean onCreate() {
        helper = new ExpenseHelper(getContext());
        Log.d(TAG, "onCreate: Expense Provide has been created.");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        switch (sUriMatcher.match(uri)) {
            case EXPENSES:
                cursor = helper.getReadableDatabase().query(
                        ExpenseContract.EXPENSE_TABLE,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case EXPENSES_WITH_ID:
                long id = ContentUris.parseId(uri);
                if (selection == null) {
                    selection = ExpenseContract.COL_ID + "= " + id;
                }
                else {
                    selection += ("AND" + ExpenseContract.COL_ID + "= " + id);
                }

                cursor = helper.getReadableDatabase().query(
                        ExpenseContract.EXPENSE_TABLE,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

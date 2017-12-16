package tw.idv.holybible.expense;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class ExpenseInsertService extends IntentService {
    private String TAG = ExpenseInsertService.class.getSimpleName();

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String insertValues = "tw.idv.holybible.expense.action.FOO";

    public ExpenseInsertService() {
        super("ExpenseInsertService");
    }
    public ExpenseInsertService(String name) {
        super(name);
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void insert(Context context, ContentValues values) {
        Intent intent = new Intent(context, ExpenseInsertService.class);
        intent.putExtra("EXTRA_EXPENSE", values);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ContentValues values = intent.getParcelableExtra("EXTRA_EXPENSE");
            handleInsert(values);
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     * @param values
     */
    private void handleInsert(ContentValues values) {
        getContentResolver().insert(ExpenseContract.CONTENT_URI, values);
        Log.d(TAG, "handleInsert: insert completed");
    }

}

package tw.idv.holybible.expense;

/**
 * Created by chchang on 2017/12/2.
 */

public class ExpenseContract {

    public static final String DB_NAME = "expense.db";
    public static final int DB_VERSION = 1;
    public static final String EXPENSE_TABLE = "expense";
    public static final String COL_ID = "_id";
    public static final String COL_DATE = "cdate";
    public static final String COL_EXPENSE_NAME = "expense_name";
    public static final String COL_AMOUNT = "amount";

    public static final String CREATE_EXPENSE_TABLE =
            "CREATE TABLE "+EXPENSE_TABLE+"( " +
                    COL_ID + " INTEGER PRIMARY KEY, " +
                    COL_DATE + " DATETIME NOT NULL, " +
                    COL_EXPENSE_NAME + " VARCHAR ," +
                    COL_AMOUNT + " INTEGER )";
}


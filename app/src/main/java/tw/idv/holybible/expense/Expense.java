package tw.idv.holybible.expense;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chchang on 2017/12/2.
 */

public class Expense implements Parcelable {
    int id;
    String cdate;
    String expName;
    int amount;
    boolean agree;

    public Expense() {

    }

    /**
     *
     * @param id expense id in SQLite
     * @param cdate occurred date of the expense
     * @param expName name of the expense
     * @param amount amount of the expense
     */
    public Expense(int id, String cdate, String expName, int amount) {
        this.id = id;
        this.cdate = cdate;
        this.expName = expName;
        this.amount = amount;
        this.agree = false;
    }

    public Expense(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(ExpenseContract.COL_ID));
        this.cdate = cursor.getString(cursor.getColumnIndex(ExpenseContract.COL_DATE));
        this.expName = cursor.getString(cursor.getColumnIndex(ExpenseContract.COL_EXPENSE_NAME));
        this.amount = cursor.getInt(cursor.getColumnIndex(ExpenseContract.COL_AMOUNT));
        int agreeFlag = cursor.getInt(cursor.getColumnIndex(ExpenseContract.COL_AGREE));
        this.agree = agreeFlag == 0? false:true;
    }

    protected Expense(Parcel in) {
        id = in.readInt();
        cdate = in.readString();
        expName = in.readString();
        amount = in.readInt();
        agree = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(cdate);
        dest.writeString(expName);
        dest.writeInt(amount);
        dest.writeInt(agree ? 1 : 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExpenseContract.COL_ID, id);
        contentValues.put(ExpenseContract.COL_DATE, cdate);
        contentValues.put(ExpenseContract.COL_EXPENSE_NAME, expName);
        contentValues.put(ExpenseContract.COL_AMOUNT, amount);
        contentValues.put(ExpenseContract.COL_AGREE, agree ? 1: 0);
        return contentValues;
    }
}

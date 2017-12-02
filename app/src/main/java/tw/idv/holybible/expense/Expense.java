package tw.idv.holybible.expense;

/**
 * Created by chchang on 2017/12/2.
 */

public class Expense {
    int id;
    String cdate;
    String expName;
    int amount;

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
    }

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
}

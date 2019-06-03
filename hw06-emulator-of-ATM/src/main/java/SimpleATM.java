import java.util.ArrayList;

public class SimpleATM extends ATM {

    private ArrayList<Cell> cells = new ArrayList<>();

    public SimpleATM(int amount) {
        ArrayList<BankNote> bankNotes = new ArrayList<>();
        bankNotes.add(BankNote.FIVE_THOUSAND);
        bankNotes.add(BankNote.TWO_THOUSAND);
        bankNotes.add(BankNote.ONE_THOUSAND);
        bankNotes.add(BankNote.FIVE_HUNDRED);
        bankNotes.add(BankNote.TWO_HUNDRED);
        bankNotes.add(BankNote.ONE_HUNDRED);
        bankNotes.add(BankNote.FIFTY);
        bankNotes.add(BankNote.TEN);
        for (BankNote i : bankNotes) {
            cells.add(new Cell(amount, i));
        }
    }

    @Override
    public void receiveBankNote(int amount) {          //здесь вносится сумма, а не отдельные банкноты, думаю так лучше
        System.out.println("Попытка внести " + amount);//писать метод ввода одной купюры(Cell) слишком просто
        if (amount % 10 != 0) {
            System.out.println("Запрошенную сумму нельзя внести");
            return;
        }
        for (Cell i : cells)
            while (i.getType().getCount() <= amount) {
                i.setAmount(i.getAmount() + 1);
                amount -= i.getType().getCount();
            }
    }

    @Override
    public void giveBankNote(int amount) {
        System.out.println("Попытка получить " + amount);
        if (amount % 10 != 0) {
            System.out.println("Запрошенную сумму нельзя выдать");
            return;
        }
        for (Cell i : cells)
            while (i.getType().getCount() <= amount) {
                i.setAmount(i.getAmount() - 1);
                amount -= i.getType().getCount();
            }
    }

    @Override
    public void showBalance() {
        var sum = 0;
        for (Cell i : cells)
            sum += (i.Sum());
        System.out.println(sum);
    }
}

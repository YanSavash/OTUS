public class Main {
    public static void main(String[] args) {
        SimpleATM simpleATM = new SimpleATM(1000);
        simpleATM.showBalance();
        simpleATM.giveBankNote(5562);
        simpleATM.showBalance();
        simpleATM.giveBankNote(5560);
        simpleATM.showBalance();
        simpleATM.receiveBankNote(5572);
        simpleATM.showBalance();
        simpleATM.receiveBankNote(5570);
        simpleATM.showBalance();
    }
}

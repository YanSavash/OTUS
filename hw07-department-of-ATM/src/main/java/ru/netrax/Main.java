package ru.netrax;

import ru.netrax.bridge.MIR;
import ru.netrax.bridge.VISA;
import ru.netrax.composite.Department;
import ru.netrax.atms.ATM;
import ru.netrax.atms.DollarATM;
import ru.netrax.atms.PoundATM;
import ru.netrax.atms.RubleATM;
import ru.netrax.entity.Cell;
import ru.netrax.entity.PoundCell;
import ru.netrax.enums.Pound;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ATM poundATM = new PoundATM(50, new VISA());
        ATM rubleATM = new RubleATM(1000, new MIR());
        ATM dollarATM = new DollarATM(50, new VISA());

        Department departmentATM = new Department();
        departmentATM.addATM(poundATM);
        departmentATM.addATM(dollarATM);
        departmentATM.addATM(rubleATM);
        System.out.println("Cумма остатков всего департамента в рублях: " + departmentATM.getCommonBalance());

        System.out.println(poundATM.showBalance());
        poundATM.giveBankNote(2562);
        System.out.println(poundATM.showBalance());
        poundATM.giveBankNote(2560);
        System.out.println(poundATM.showBalance());
        poundATM.setState("Disable");

        System.out.println(dollarATM.showBalance());
        dollarATM.giveBankNote(5562);
        System.out.println(dollarATM.showBalance());
        dollarATM.giveBankNote(5560);
        System.out.println(dollarATM.showBalance());
        dollarATM.setState("Disable");

        System.out.println(rubleATM.showBalance());
        rubleATM.giveBankNote(3362);
        System.out.println(rubleATM.showBalance());
        rubleATM.giveBankNote(3360);
        System.out.println(rubleATM.showBalance());
        rubleATM.setState("Disable");

        System.out.println(poundATM.toString());
        System.out.println(rubleATM.toString());
        System.out.println(dollarATM.toString());

        departmentATM.getRestore();

        System.out.println(poundATM.toString());
        System.out.println(rubleATM.toString());
        System.out.println(dollarATM.toString());

        Map<Object, Cell> packP = new HashMap<>();
        packP.put(Pound.FIFTY, new PoundCell(5, Pound.FIFTY));
        packP.put(Pound.TEN, new PoundCell(5, Pound.TEN));
        packP.put(Pound.TWO_THOUSAND, new PoundCell(5, Pound.TWO_THOUSAND));
        packP.put(Pound.ONE_HUNDRED, new PoundCell(5, Pound.ONE_HUNDRED));
        packP.put(Pound.FIVE_THOUSAND, new PoundCell(5, Pound.FIVE_THOUSAND));

        poundATM.receiveBankNote(packP);
        System.out.println(poundATM.showBalance());

        Map<Object, Cell> packD = new HashMap<>();
        packD.put(Pound.FIFTY, new PoundCell(5, Pound.FIFTY));
        packD.put(Pound.TEN, new PoundCell(5, Pound.TEN));
        packD.put(Pound.TWO_THOUSAND, new PoundCell(5, Pound.TWO_THOUSAND));
        packD.put(Pound.ONE_HUNDRED, new PoundCell(5, Pound.ONE_HUNDRED));
        packD.put(Pound.FIVE_THOUSAND, new PoundCell(5, Pound.FIVE_THOUSAND));

        poundATM.receiveBankNote(packD);
        System.out.println(poundATM.showBalance());

        Map<Object, Cell> packR = new HashMap<>();
        packR.put(Pound.FIFTY, new PoundCell(5, Pound.FIFTY));
        packR.put(Pound.TEN, new PoundCell(5, Pound.TEN));
        packR.put(Pound.TWO_THOUSAND, new PoundCell(5, Pound.TWO_THOUSAND));
        packR.put(Pound.ONE_HUNDRED, new PoundCell(5, Pound.ONE_HUNDRED));
        packR.put(Pound.FIVE_THOUSAND, new PoundCell(5, Pound.FIVE_THOUSAND));

        poundATM.receiveBankNote(packR);
        System.out.println(poundATM.showBalance());

        //я знаю, что здесь мапа перезатирается
        packR.put(Pound.FIFTY, new PoundCell(1, Pound.FIFTY));
        packR.put(Pound.TEN, new PoundCell(1, Pound.TEN));
        packR.put(Pound.TWO_THOUSAND, new PoundCell(1, Pound.TWO_THOUSAND));
        packR.put(Pound.ONE_HUNDRED, new PoundCell(1, Pound.ONE_HUNDRED));
        packR.put(Pound.FIVE_THOUSAND, new PoundCell(1, Pound.FIVE_THOUSAND));

        poundATM.receiveBankNote(packR);
        System.out.println(poundATM.showBalance());

    }
}

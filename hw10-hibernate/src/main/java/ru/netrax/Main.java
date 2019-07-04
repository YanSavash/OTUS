package ru.netrax;

import ru.netrax.models.AddressDataSet;
import ru.netrax.models.PhoneDataSet;
import ru.netrax.models.User;
import ru.netrax.services.DBService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        User user = new User();
        AddressDataSet addressDataSet = new AddressDataSet();
        addressDataSet.setStreet("Krasnay ploshad");
        addressDataSet.setUser(user);
        user.setAddressDataSet(addressDataSet);
        List<PhoneDataSet> list = new ArrayList();
        PhoneDataSet phoneDataSet1 = new PhoneDataSet();
        phoneDataSet1.setNumber("01");
        phoneDataSet1.setUser(user);
        PhoneDataSet phoneDataSet2 = new PhoneDataSet();
        phoneDataSet2.setNumber("02");
        phoneDataSet2.setUser(user);
        PhoneDataSet phoneDataSet3 = new PhoneDataSet();
        phoneDataSet3.setNumber("03");
        phoneDataSet3.setUser(user);
        list.add(phoneDataSet1);
        list.add(phoneDataSet2);
        list.add(phoneDataSet3);
        user.setName("Petra");
        user.setAge(25);
        user.setPhoneDataSetList(list);

        DBService dbService = new DBService();
        System.out.println(user);
        dbService.saveUser(user);
        System.out.println(dbService.findUser(1));
    }
}

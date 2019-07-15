package ru.netrax;

import ru.netrax.dao.UserDaoImp;
import ru.netrax.models.AddressDataSet;
import ru.netrax.models.PhoneDataSet;
import ru.netrax.models.User;
import ru.netrax.services.DBService;
import ru.netrax.services.DBServiceInterface;
import ru.netrax.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

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

        User user2 = new User();
        AddressDataSet addressDataSet2 = new AddressDataSet();
        addressDataSet2.setStreet("Nykolskay street");
        addressDataSet2.setUser(user2);
        user2.setAddressDataSet(addressDataSet2);
        List<PhoneDataSet> list2 = new ArrayList();
        PhoneDataSet phoneDataSet12 = new PhoneDataSet();
        phoneDataSet12.setNumber("10");
        phoneDataSet12.setUser(user2);
        PhoneDataSet phoneDataSet22 = new PhoneDataSet();
        phoneDataSet22.setNumber("20");
        phoneDataSet22.setUser(user2);
        PhoneDataSet phoneDataSet32 = new PhoneDataSet();
        phoneDataSet32.setNumber("30");
        phoneDataSet32.setUser(user2);
        list2.add(phoneDataSet12);
        list2.add(phoneDataSet22);
        list2.add(phoneDataSet32);
        user2.setName("Nike");
        user2.setAge(25);
        user2.setPhoneDataSetList(list2);

        DBServiceInterface dbService = new DBService(new UserDaoImp(HibernateSessionFactoryUtil
                .getSessionFactory("hibernate.cfg.xml", User.class, PhoneDataSet.class, AddressDataSet.class)));
        System.out.println("============================================================================================== " + user);
        dbService.saveUser(user);
        dbService.saveUser(user2);
        System.out.println("============================================================================================== " + dbService.findUser(2));
        dbService.deleteUser(2);
        dbService.closeTimer();
    }
}
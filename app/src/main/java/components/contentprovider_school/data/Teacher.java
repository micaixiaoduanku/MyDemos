package components.contentprovider_school.data;

import components.contentprovider_school.interf.DataItem;

/**
 * Created by huangli on 16/3/4.
 */
public class Teacher implements DataItem {
    public String name;
    public String number;
    public String age;

    public Teacher(String name, String number, String age) {
        this.name = name;
        this.number = number;
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAge() {
        return age;
    }

    @Override
    public String getNumber() {
        return number;
    }
}

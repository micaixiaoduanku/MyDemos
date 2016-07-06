package logic.reflect;

import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * Created by huangli on 16/5/26.
 */
public class ReflectDemoFactory {
    public static final String TAG = "ReflectDemoFactory";


    private static class Demo{
        //other codes...
    }

    private interface China{
        public static final String name="Rollen";
        public static int age=20;
        public void sayChina();
        public void sayHello(String name, int age);
    }

    private static class Person implements China{
        private String name;
        private int age;
        private String sex;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Person(){

        }
        public Person(String name,int age){
            setAge(age);
            setName(name);
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public void sayChina() {
            Log.i(TAG,"say china");
        }

        @Override
        public void sayHello(String name, int age) {
            Log.i(TAG,"name "+name+"age "+age+" say hello");
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public interface DemoRunImpl{
        void run();
    }

    public DemoRunImpl getDemoByIndex(int index){
        switch (index){
            case 1:
                return new Demo1();
            case 2:
                return new Demo2();
            case 3:
                return new Demo3();
            case 4:
                return new Demo4();
            case 5:
                return new Demo5();
            case 6:
                return new Demo6();
            case 7:
                return new Demo7();
            case 8:
                return new Demo8();
            case 9:
                return new Demo9();
            case 10:
                return new Demo10();
            case 11:
                return new Demo11();
            case 12:
                return new Demo12();
            case 13:
                return new Demo13();
            case 14:
                return new Demo14();
            case 15:
                return new Demo15();
            case 16:
                return new Demo16();
            default:
                return null;
        }
    }

    //【案例1】通过一个对象获得完整的包名和类名
    private class Demo1 implements DemoRunImpl{
        public void run(){
            Log.i(TAG,"【案例1】通过一个对象获得完整的包名和类名 ");
            Demo demo=new Demo();
            Log.i(TAG,demo.getClass().getName());
        }
    }

    //【案例2】实例化Class类对象
    private static class Demo2 implements DemoRunImpl{
        public void run(){
            Log.i(TAG,"【案例2】实例化Class类对象 ");
            Class<?> demo1 = null;
            Class<?> demo2 = null;
            Class<?> demo3 = null;
            try{
                //一般尽量采用这种形式
                demo1 = Class.forName("logic.reflect.ReflectDemoFactory$Demo");
            }catch(Exception e){
                e.printStackTrace();
            }
            demo2 = new Demo().getClass();
            demo3 = Demo.class;

            Log.i(TAG,"类名称   "+demo1.getName());
            Log.i(TAG,"类名称   "+demo2.getName());
            Log.i(TAG,"类名称   "+demo3.getName());
        }
    }

    /**
     * note:如果这里Demo3和Person不声明static,那么问题运行newInstance()就会返回null,因为在这种情况下默认Person()会又一个Demo3的隐藏参数
     */
    //【案例3】通过Class实例化其他类的对象
    private static class Demo3 implements DemoRunImpl{
        @Override
        public void run() {
            Log.i(TAG,"【案例3】通过Class实例化其他类的对象 ");
            Class<?> demo=null;
            try{
                demo=Person.class;
            }catch (Exception e) {
                e.printStackTrace();
            }
            Person per=null;
            try {
                per=(Person)demo.newInstance();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            per.setName("Rollen");
            per.setAge(20);
            Log.i(TAG,per.toString());
        }
    }

    //【案例4】通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）
    private static class Demo4 implements DemoRunImpl{
        @Override
        public void run() {
            Log.i(TAG,"【案例4】通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象） ");
            Class<?> demo = null;
            try{
                demo = Class.forName("logic.reflect.ReflectDemoFactory$Person");
            }catch (Exception e) {
                e.printStackTrace();
            }
            Person per1=null;
            Person per2=null;
            Person per3=null;
            Person per4=null;
            //取得全部的构造函数
            Constructor<?> cons[]=demo.getConstructors();
            try{
                per1=(Person)cons[0].newInstance();
                per2=(Person)cons[1].newInstance("Rollen");
                per3=(Person)cons[2].newInstance(20);
                per4=(Person)cons[3].newInstance("Rollen",20);
            }catch(Exception e){
                e.printStackTrace();
            }

            Log.i(TAG,per1.toString());
            Log.i(TAG,per2.toString());
            Log.i(TAG,per3.toString());
            Log.i(TAG,per4.toString());
        }
    }

    //【案例5】返回一个类实现的接口：
    private static class Demo5 implements DemoRunImpl{
        @Override
        public void run() {
            Log.i(TAG,"【案例5】返回一个类实现的接口");
            Class<?> demo=null;
            try{
                demo=Class.forName("logic.reflect.ReflectDemoFactory$Person");
            }catch (Exception e) {
                e.printStackTrace();
            }
            //保存所有的接口
            Class<?> intes[]=demo.getInterfaces();
            for (int i = 0; i < intes.length; i++) {
                Log.i(TAG,"实现的接口   "+intes[i].getName());
            }
        }
    }

    //【案例6】：取得其他类中的父类
    private static class Demo6 implements DemoRunImpl{
        public static class Person{

        }
        @Override
        public void run() {
            Log.i(TAG,"【案例6】：取得其他类中的父类");
            Class<?> demo=null;
            try{
                demo=Class.forName("logic.reflect.ReflectDemoFactory$Person");
            }catch (Exception e) {
                e.printStackTrace();
            }
            //取得父类
            Class<?> temp=demo.getSuperclass();
            Log.i(TAG,"继承的父类为：   "+temp.getName());
        }
    }

    //【案例7】获得其他类中的全部构造函数
    private static class Demo7 implements DemoRunImpl{
        @Override
        public void run() {
            Log.i(TAG,"【案例7】获得其他类中的全部构造函数");
            Class<?> demo=null;
            try{
                demo=Class.forName("logic.reflect.ReflectDemoFactory$Person");
            }catch (Exception e) {
                e.printStackTrace();
            }
            Constructor<?>cons[]=demo.getConstructors();
            for (int i = 0; i < cons.length; i++) {
                Log.i(TAG,"构造方法：  "+cons[i]);
            }
        }
    }

    //【案例8】获得其他类中的全部构造函数并带修饰符
    private static class Demo8 implements DemoRunImpl{
        @Override
        public void run() {
            Log.i(TAG,"【案例8】获得其他类中的全部构造函数并带修饰符");
            Class<?> demo = null;
            try {
                demo = Class.forName("logic.reflect.ReflectDemoFactory$Person");
            }catch (Exception e){
                e.printStackTrace();
            }
            Constructor<?> cons[] = demo.getConstructors();
            for (int i = 0; i < cons.length; i++){
                StringBuilder sb = new StringBuilder();
                Class<?> p[] = cons[i].getParameterTypes();
                int mo = cons[i].getModifiers();
                sb.append("构造方法: "+ Modifier.toString(mo)+" "+cons[i].getName()+"(");
                for (int j = 0; j < p.length; ++j){
                    if (j == p.length-1){
                        sb.append(p[j].getName()+" arg"+j);
                    }else{
                        sb.append(p[j].getName()+" arg"+j+" , ");
                    }
                }
                sb.append(")");
                Log.i(TAG,"构造方法：  "+sb);
            }
        }
    }

    //【案例9】获得一个类中的所有方法
    private static class Demo9 implements DemoRunImpl{
        @Override
        public void run() {
            Log.i(TAG,"【案例9】获得一个类中的所有方法");
            Class<?> demo = null;
            try{
                demo = Class.forName("logic.reflect.ReflectDemoFactory$Person");
            }catch (Exception e) {
                e.printStackTrace();
            }
            Method method[] = demo.getMethods();
            for(int i=0;i<method.length;++i){
                Class<?> returnType = method[i].getReturnType();
                Class<?> para[] = method[i].getParameterTypes();
                int temp = method[i].getModifiers();
                StringBuffer sb = new StringBuffer();
                sb.append(Modifier.toString(temp)+" ");
                sb.append(returnType.getName()+"  ");
                sb.append(method[i].getName()+" ");
                sb.append("(");
                for (int j=0;j<para.length;++j){
                    sb.append(para[j].getName()+" "+"arg"+j);
                    if (j < para.length - 1){
                        sb.append(",");
                    }
                }
                Class<?> exce[] = method[i].getExceptionTypes();
                if(exce.length>0){
                    sb.append(") throws ");
                    for(int k=0;k<exce.length;++k){
                        sb.append(exce[k].getName()+" ");
                        if(k<exce.length-1){
                            sb.append(",");
                        }
                    }
                }else{
                    sb.append(")");
                }
                Log.i(TAG,sb.toString());
            }
        }
    }

    //【案例10】class取得一个类的全部框架
    private static class Demo10 implements DemoRunImpl{

        @Override
        public void run() {
            Class<?> demo = null;
            try{
                demo = Class.forName("logic.reflect.ReflectDemoFactory$Person");
            }catch (Exception e){
                e.printStackTrace();
            }
            Log.i(TAG,"===========本类属性===========");
            // 取得本类的全部属性
            Field[] fields = demo.getDeclaredFields();
            for (int i = 0; i < fields.length; i++){
                // 权限修饰符
                int mo  = fields[i].getModifiers();
                String priv = Modifier.toString(mo);
                // 属性类型
                Class<?> type = fields[i].getType();
                Log.i(TAG,priv+" "+type.getName()+" "+fields[i].getName()+";");
            }
            Log.i(TAG,"===========实现的接口或者父类的属性===========");
            // 取得实现的接口或者父类的属性
            Field[] filed1 = demo.getFields();
            for (int j = 0; j < filed1.length; j++) {
                // 权限修饰符
                int mo = filed1[j].getModifiers();
                String priv = Modifier.toString(mo);
                // 属性类型
                Class<?> type = filed1[j].getType();
                System.out.println(priv + " " + type.getName() + " "
                        + filed1[j].getName() + ";");
            }
        }
    }
    //【案例11】其实还可以通过反射调用其他类中的方法
    private static class Demo11 implements DemoRunImpl{

        @Override
        public void run() {
            Class<?> demo = null;
            try {
                demo = Class.forName("logic.reflect.ReflectDemoFactory$Person");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Method method = demo.getMethod("sayChina");
                method.invoke(demo.newInstance());
                Method method1 = demo.getMethod("sayHello",String.class,int.class);
                method1.invoke(demo.newInstance(),"jay",22);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    //【案例12】调用其他类的set和get方法
    private static class Demo12 implements DemoRunImpl{
        Class<?> demo = null;
        Object obj = null;
        @Override
        public void run() {
            try {
                demo = Class.forName("logic.reflect.ReflectDemoFactory$Person");
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                obj=demo.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            setter(obj,"Sex","男",String.class);
            getter(obj,"Sex");
        }

        /**
         * @param obj 操作的对象
         * @param att 操作的属性
         * */
        public static void getter(Object obj, String att) {
            try {
                Method method = obj.getClass().getMethod("get" + att);
                Log.i(TAG,"改变后的属性 "+method.invoke(obj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /**
         * @param obj 操作的对象
         * @param att 操作的属性
         * @param value 设置的值
         * @param type 参数的属性
         * */
        public static void setter(Object obj, String att, Object value, Class<?> type) {
            try {
                Method method = obj.getClass().getMethod("set" + att, type);
                method.invoke(obj, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //【案例13】通过反射操作属性
    private static class Demo13 implements DemoRunImpl{
        @Override
        public void run() {
            Class<?> demo = null;
            Object obj = null;
            try {
                demo = Class.forName("Reflect.Person");
                obj = demo.newInstance();
                Field field = demo.getDeclaredField("sex");
                field.setAccessible(true);
                field.set(obj, "男");
                Log.i(TAG,"改变后的属性 "+field.get(obj));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //【案例14】通过反射取得并修改数组的信息
    private static class Demo14 implements DemoRunImpl{
        @Override
        public void run() {
            int[] temp={1,2,3,4,5};
            Class<?>demo=temp.getClass().getComponentType();
            Log.i(TAG,"数组类型： "+demo.getName());
            Log.i(TAG,"数组长度  "+ Array.getLength(temp));
            Log.i(TAG,"数组的第一个元素: "+Array.get(temp, 0));
            Array.set(temp, 0, 100);
            Log.i(TAG,"修改之后数组第一个元素为： "+Array.get(temp, 0));
        }
    }

    //【案例15】通过反射修改数组大小
    private static class Demo15 implements DemoRunImpl{

        @Override
        public void run() {
            int[] temp = {1,2,3,4,5,6,7,8,9};
            int[] newTemp = (int[])arrayInc(temp,15);
            print(newTemp);
            System.out.println("=====================");
            String[] atr={"a","b","c"};
            String[] str1=(String[])arrayInc(atr,8);
            print(str1);
        }

        /**
         * 修改数组大小
         * */
        public static Object arrayInc(Object obj,int len){
            Class<?>arr=obj.getClass().getComponentType();
            Object newArr=Array.newInstance(arr, len);
            int co=Array.getLength(obj);
            System.arraycopy(obj, 0, newArr, 0, co);
            return newArr;
        }

        /**
         * 打印
         * */
        public static void print(Object obj){
            Class<?>c=obj.getClass();
            if(!c.isArray()){
                return;
            }
            System.out.println("数组长度为： "+Array.getLength(obj));
            for (int i = 0; i < Array.getLength(obj); i++) {
                System.out.print(Array.get(obj, i)+" ");
            }
        }
    }

    //【案例16】获得类加载器
    /**
     * 其实在java中有三种类类加载器。

     1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。

     2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类

     3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
     */
    private static class Demo16 implements DemoRunImpl{
        class test{

        }
        @Override
        public void run() {
            test t=new test();
            System.out.println("类加载器  "+t.getClass().getClassLoader().getClass().getName());
        }
    }

    //【案例17】动态代理
    private static class Demo17 implements DemoRunImpl{

        //定义项目接口
        interface Subject {
            public String say(String name, int age);
        }

        // 定义真实项目
        class RealSubject implements Subject {
            @Override
            public String say(String name, int age) {
                return name + "  " + age;
            }
        }

        class MyInvocationHandler implements InvocationHandler {
            private Object obj = null;
            public Object bind(Object obj) {
                this.obj = obj;
                return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
                        .getClass().getInterfaces(), this);
            }
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object temp = method.invoke(this.obj, args);
                return temp;
            }
        }

        @Override
        public void run() {
            MyInvocationHandler demo = new MyInvocationHandler();
            Subject sub = (Subject) demo.bind(new RealSubject());
            String info = sub.say("Rollen", 20);
            System.out.println(info);
        }
    }

    //【案例18】反射用于工厂模式
    private static class Demo18 implements DemoRunImpl{

        interface fruit{
            public abstract void eat();
        }

        static class Apple implements fruit{
            public void eat(){
                System.out.println("Apple");
            }
        }

        static class Orange implements fruit{
            public void eat(){
                System.out.println("Orange");
            }
        }

        static class Factory{
            public static fruit getInstance(String ClassName){
                fruit f = null;
                try{
                    f=(fruit)Class.forName(ClassName).newInstance();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                return f;
            }
        }

        @Override
        public void run() {
            fruit f=Factory.getInstance("Reflect.Apple");
            if(f!=null){
                f.eat();
            }
        }
    }
}

package logic.reflect;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.huangli.mydemos.R;

import java.util.ArrayList;

import utils.ToastUtils;

/**
 * Created by huangli on 16/5/26.
 */
public class ReflectActivity extends Activity{
    public final String TAG = "ReflectActivity";

    private Spinner spinner;

    private ReflectDemoFactory reflectDemoFactory = new ReflectDemoFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> data_list = new ArrayList<>();
        data_list.add("Demo1 【案例1】通过一个对象获得完整的包名和类名");
        data_list.add("Demo2 【案例2】实例化Class类对象");
        data_list.add("Demo3 【案例3】通过Class实例化其他类的对象");
        data_list.add("Demo4 【案例4】通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）");
        data_list.add("Demo5 【案例5】返回一个类实现的接口");
        data_list.add("Demo6 【案例6】：取得其他类中的父类");
        data_list.add("Demo7 【案例7】获得其他类中的全部构造函数");
        data_list.add("Demo8 【案例8】获得其他类中的全部构造函数并带修饰符");
        data_list.add("Demo9 【案例9】获得一个类中的所有方法");
        data_list.add("Demo10 【案例10】class取得一个类的全部框架");
        data_list.add("Demo11 【案例11】其实还可以通过反射调用其他类中的方法");
        data_list.add("Demo12 【案例12】调用其他类的set和get方法");
        data_list.add("Demo13 【案例13】通过反射操作属性");
        data_list.add("Demo14 【案例14】通过反射取得并修改数组的信息");
        data_list.add("Demo15 【案例15】通过反射修改数组大小");
        data_list.add("Demo16 【案例16】获得类加载器");
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = spinner.getSelectedItemPosition();
                ReflectDemoFactory.DemoRunImpl demoRun = reflectDemoFactory.getDemoByIndex(position+1);
                if (demoRun != null){
                    demoRun.run();
                }else {
                    ToastUtils.showShortToast(ReflectActivity.this,"运行失败");
                }

            }
        });
    }
}

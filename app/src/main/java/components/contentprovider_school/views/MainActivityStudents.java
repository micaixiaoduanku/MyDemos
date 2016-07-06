package components.contentprovider_school.views;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huangli.mydemos.R;

import components.contentprovider_school.data.Student;
import components.contentprovider_school.interf.ItemOperation;

/**
 * Created by huangli on 16/3/4.
 */
public class MainActivityStudents extends AppCompatActivity {
    public final static String TAG = "MainActivityStudents";
    private EditText edName,edNumber,edAge;
    private Button btnAdd,btnDelete,btnUpdate;
    private ViewPager viewPager;
    private ContentFragmentAdapter contentFragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentprovider_student);
        findViews();
        setListeners();
        init();
        updateData();
    }

    private void init(){
        contentFragmentAdapter = new ContentFragmentAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(contentFragmentAdapter);
    }

    private void findViews() {
        edName = (EditText)findViewById(R.id.ed_name);
        edNumber = (EditText)findViewById(R.id.ed_number);
        edAge = (EditText)findViewById(R.id.ed_age);
        btnAdd = (Button)findViewById(R.id.btn_add);
        btnDelete = (Button)findViewById(R.id.btn_delete);
        btnUpdate = (Button)findViewById(R.id.btn_update);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
    }

    private void updateData(){
//        Cursor cursor = contentResolverStudentsHelper.query();
//        if (cursor != null){
//            //适配Adapter
//            SimpleCursorAdapter mCursorAdapter = new SimpleCursorAdapter(
//                    getApplicationContext(),               // The application's Context object
//                    R.layout.list_item_contentresolver_student_item,                  // A layout in XML for one row in the ListView
//                    cursor,                               // The result from the query
//                    mWordListColumns,                      // A string array of column names in the cursor
//                    mWordListItems,                        // An integer array of view IDs in the row layout
//                    0);                                    // Flags (usually none are needed)
//            listView.setAdapter(mCursorAdapter);
//        }
    }

    private void setListeners(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = getDataitem();
                Object obj = contentFragmentAdapter.getItem(0);
                if (obj instanceof ItemOperation){
                    ((ItemOperation) obj).insertItem(student);
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = getDataitem();
                Object obj = contentFragmentAdapter.getItem(0);
                if (obj instanceof ItemOperation){
                    ((ItemOperation) obj).deleteItem(student);
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = getDataitem();
                Object obj = contentFragmentAdapter.getItem(0);
                if (obj instanceof ItemOperation){
                    ((ItemOperation) obj).updateItem(student);
                }
            }
        });
    }

    private Student getDataitem(){
        String name = edName.getText().toString();
        String number = edNumber.getText().toString();
        String age = edAge.getText().toString();
        return  new Student(name,number,age);
    }

}

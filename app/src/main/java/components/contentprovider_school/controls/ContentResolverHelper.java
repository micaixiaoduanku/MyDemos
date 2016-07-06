package components.contentprovider_school.controls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import components.contentprovider_school.data.Student;
import components.contentprovider_school.data.StudentContract;


/**
 * Created by huangli on 16/3/4.
 */
public class ContentResolverHelper {
    public final static String TAG = "StudentHelper";
    private Context context;
    public ContentResolverHelper(Context context){
        this.context = context;
//        cursorLoader = new CursorLoader(context,StudentContract.Student.CONTENT_URI,mProjection,mSelectionClause,mSelectionArgs,null){
//            @Override
//            public void deliverResult(Cursor cursor) {
//                super.deliverResult(cursor);
//            }
//        };
    }
    private CursorLoader cursorLoader;


    public Cursor query() {
        return cursorLoader.loadInBackground();
    }

    public Uri insert(Student student){
        ContentValues mNewValues = new ContentValues();
        mNewValues.put(StudentContract.Student.NAME, student.name);
        mNewValues.put(StudentContract.Student.NUMBER,student.number);
        mNewValues.put(StudentContract.Student.AGE,student.age);
        Uri mNewUri = context.getContentResolver().insert(
                StudentContract.Student.CONTENT_URI,
                mNewValues
        );
        return mNewUri;
    }
}

package components.contentprovider_school.controls;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import components.contentprovider_school.data.StudentContract;
import components.contentprovider_school.data.TeacherContract;


/**
 * Created by huangli on 16/3/4.
 */
public class MyContentProvider extends ContentProvider{
    public final static String TAG = "ContentproviderStudents";
    private UriMatcher uriMatcher;
    private MatrixCursor matrixCursorStudents;
    private MatrixCursor matrixCursorteachers;

    private void loadCahce(){
        matrixCursorStudents = new MatrixCursor(new String[]{StudentContract.Student._ID,StudentContract.Student.NAME,StudentContract.Student.NUMBER,StudentContract.Student.AGE});
        String[] columnValues = new String[]{"1","jay","0001","33"};
        matrixCursorStudents.addRow(columnValues);
        columnValues = new String[]{"2","lanlan","0002","32"};
        matrixCursorStudents.addRow(columnValues);
        columnValues = new String[]{"3","ap","0003","25"};
        matrixCursorStudents.addRow(columnValues);
        matrixCursorteachers = new MatrixCursor(new String[]{TeacherContract.Teacher._ID,TeacherContract.Teacher.NAME,TeacherContract.Teacher.NUMBER,TeacherContract.Teacher.AGE});
        columnValues = new String[]{"1","asd","0005","33"};
        matrixCursorteachers.addRow(columnValues);
        columnValues = new String[]{"2","weq","0202","32"};
        matrixCursorteachers.addRow(columnValues);
        columnValues = new String[]{"3","ryt","3003","25"};
        matrixCursorteachers.addRow(columnValues);
    }

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.huangli.mydemos","students",1);
        uriMatcher.addURI("com.example.huangli.mydemos","teachers",2);
        loadCahce();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String s1) {
        switch (uriMatcher.match(uri)){
            case 1:             //students
                return matrixCursorStudents;
            case 2:             //teachers
                return matrixCursorteachers;
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        switch (uriMatcher.match(uri)){
            case 1:
                String id_s = (String)contentValues.get(StudentContract.Student._ID);
                String name_s = (String)contentValues.get(StudentContract.Student.NAME);
                String number_s = (String)contentValues.get(StudentContract.Student.NUMBER);
                String age_s = (String)contentValues.get(StudentContract.Student.AGE);
                String[] row_s = new String[]{id_s,name_s,number_s,age_s};
                matrixCursorStudents.addRow(row_s);
                break;
            case 2:
                String id_t = (String)contentValues.get(StudentContract.Student._ID);
                String name_t = (String)contentValues.get(StudentContract.Student.NAME);
                String number_t = (String)contentValues.get(StudentContract.Student.NUMBER);
                String age_t = (String)contentValues.get(StudentContract.Student.AGE);
                String[] row_t = new String[]{id_t,name_t,number_t,age_t};
                matrixCursorteachers.addRow(row_t);
                break;
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case 1:
                MatrixCursor newCursor = new MatrixCursor(new String[]{StudentContract.Student._ID,StudentContract.Student.NAME,StudentContract.Student.NUMBER,StudentContract.Student.AGE});
                if (matrixCursorStudents.moveToFirst()) {
                    do {
                        int index = matrixCursorStudents.getColumnIndex(StudentContract.Student.NUMBER);
                        // skip the copy of this one ....
                        String str = matrixCursorStudents.getString(index);
                        if (str.equals(strings[0])) {
                            count++;
                            continue;
                        }
                        newCursor.addRow(new Object[]{matrixCursorStudents.getString(0), matrixCursorStudents.getString(1),matrixCursorStudents.getString(2),matrixCursorStudents.getString(3)});
                    } while (matrixCursorStudents.moveToNext());
                }
                matrixCursorStudents = newCursor;
                break;
            case 2:
                break;
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}

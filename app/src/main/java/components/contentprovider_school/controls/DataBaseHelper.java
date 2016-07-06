package components.contentprovider_school.controls;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import components.contentprovider_school.data.StudentContract;

/**
 * Created by huangli on 16/3/4.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public final static String TABLENAME_STUDENTS = "student";
    public final static String DATABASE_NAME = "studentDb";
    public static final int DATABASE_VERSION = 1;
    DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Events_Table");
        onCreate(sqLiteDatabase);
    }

    private void createTables(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLENAME_STUDENTS + "(" + StudentContract.Student._ID + " integer primary key autoincrement, " +
                StudentContract.Student.NAME + " TEXT);");
    }
}

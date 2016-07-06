package components.contentprovider_school.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by huangli on 16/3/4.
 */
public class StudentContract {
    public static final String AUTHORITY = "constact_student";
    public static class Student implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://com.example.huangli.mydemos/students");
        public static final String NAME = "name";
        public static final String NUMBER = "number";
        public static final String AGE = "age";
    }
}

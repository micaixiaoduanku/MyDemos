package components.contentprovider_school.views;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.huangli.mydemos.R;

import components.contentprovider_school.controls.ContentResolverHelper;
import components.contentprovider_school.data.StudentContract;
import components.contentprovider_school.interf.DataItem;
import components.contentprovider_school.interf.ItemOperation;

/**
 * Created by huangli on 16/3/4.
 */
public class FragmentStudents extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,ItemOperation{
    public final static String TAG = "FragmentStudents";
    String[] mProjection =
            {
                    StudentContract.Student._ID,
                    StudentContract.Student.NAME,
                    StudentContract.Student.NUMBER,
                    StudentContract.Student.AGE
            };

    String mSelectionClause = null;
    String[] mSelectionArgs = null;
    String[] mWordListColumns = {
            StudentContract.Student.NUMBER,
            StudentContract.Student.NAME,
            StudentContract.Student.AGE
    };

    int[] mWordListItems = { R.id.tv_number,R.id.tv_name,R.id.tv_age};
    private ListView listView;
    private SimpleCursorAdapter mCursorAdapter;
    private ContentResolverHelper contentResolverStudentsHelper = new ContentResolverHelper(getActivity());
    private static final int URL_LOADER = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("tag","FragmentStudents onCreateView");
        View view = inflater.inflate(R.layout.fragment_contentprovider_students, container, false);
        listView = (ListView)view.findViewById(R.id.listview);
        mCursorAdapter = new SimpleCursorAdapter(getActivity(),R.layout.list_item_contentresolver_student_item,null,mWordListColumns,mWordListItems);
        listView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(URL_LOADER, null, this);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case URL_LOADER:
                return new CursorLoader(getActivity(), StudentContract.Student.CONTENT_URI, mProjection, mSelectionClause, mSelectionArgs, null);
                default:
                return null;
        }
    }



    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.changeCursor(data);
        mCursorAdapter.notifyDataSetChanged();
        Log.i(TAG, "onLoadFinished Cursor "+data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void insertItem(DataItem dataItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentContract.Student.NAME,dataItem.getName());
        contentValues.put(StudentContract.Student.NUMBER,dataItem.getNumber());
        contentValues.put(StudentContract.Student.AGE,dataItem.getAge());
        getActivity().getContentResolver().insert(StudentContract.Student.CONTENT_URI,contentValues);
        getLoaderManager().restartLoader(URL_LOADER,null,this);
    }

    @Override
    public void deleteItem(DataItem dataItem) {
        getActivity().getContentResolver().delete(StudentContract.Student.CONTENT_URI, StudentContract.Student.NUMBER + " LIKE ?", new String[]{dataItem.getNumber()});
        getLoaderManager().restartLoader(URL_LOADER, null, this);
    }

    @Override
    public void updateItem(DataItem dataItem) {

    }


    @Override
    public void onDestroyView() {
        Log.i("tag","FragmentStudents onDestroyView");
        super.onDestroyView();
    }
}

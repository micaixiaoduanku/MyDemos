package logic.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by huangli on 16/5/29.
 */
public class LibService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBookManager.Stub mBinder = new IBookManager.Stub(){
        @Override
        public List<Book> getBookList() throws RemoteException {
            return null;
        }

        @Override
        public void addBook(Book book) throws RemoteException {

        }
    };
}

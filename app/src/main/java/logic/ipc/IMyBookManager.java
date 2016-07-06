package logic.ipc;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by huangli on 16/5/29.
 */
public interface IMyBookManager extends IInterface{
    static final String DESCRIPTOR = "logic.ipc.IMyBookManager";
    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;
    public List<Book> getBookList() throws RemoteException;
    public void addBook(Book book) throws RemoteException;
}

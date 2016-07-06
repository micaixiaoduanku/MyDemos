package logic.backphoto;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.huangli.mydemos.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by huangli on 16/5/20.
 */
public class BackPhotoActivity extends Activity{
    private Button btn_photo;
    private ImageView ivPhoto;
    private Uri imageUri;
    public final static int PICTURE_RESULT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backphoto);
        btn_photo = (Button)findViewById(R.id.btn_photo);
        ivPhoto = (ImageView)findViewById(R.id.iv_photo);
        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCameraActivity();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PICTURE_RESULT:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        Bitmap thumbnail = MediaStore.Images.Media.getBitmap(
                                getContentResolver(), imageUri);

                        Bitmap realBitamp = decodeFile(new File(getRealPathFromURI(imageUri)),300,300);
                        Bitmap adaptBitmap = adaptBitMap(getRealPathFromURI(imageUri),realBitamp);
                        ivPhoto.setImageBitmap(adaptBitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

        }
    }

    public Bitmap adaptBitMap(String photoPath,Bitmap bitmap) throws IOException {
        ExifInterface ei = new ExifInterface(photoPath);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

        switch(orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(bitmap, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(bitmap, 180);
        }
        return bitmap;
    }

    public Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        return retVal;
    }

    public static Bitmap decodeFile(File f,int WIDTH,int HIGHT){
        try {
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //The new size we want to scale to
            final int REQUIRED_WIDTH=WIDTH;
            final int REQUIRED_HIGHT=HIGHT;
            //Find the correct scale value. It should be the power of 2.
            int scale=1;
            while(o.outWidth/scale/2>=REQUIRED_WIDTH && o.outHeight/scale/2>=REQUIRED_HIGHT)
                scale*=2;

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    protected void startCameraActivity() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, PICTURE_RESULT);
    }
}

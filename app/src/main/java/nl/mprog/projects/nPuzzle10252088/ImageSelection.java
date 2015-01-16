package nl.mprog.projects.nPuzzle10252088;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ImageSelection extends Activity implements OnItemClickListener {
    ArrayList<main_list> arrayPhotos;
    ListView listViewPhotos;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        arrayPhotos = new ArrayList<main_list>();

        main_list image1 = new main_list(R.drawable.image_1, "image 1");
        main_list image2 = new main_list(R.drawable.image_2, "image 2");
        main_list image3 = new main_list(R.drawable.image_3, "image 3");
        main_list image4 = new main_list(R.drawable.image_4, "image 4");
        main_list image5 = new main_list(R.drawable.image_5, "image 5");
        main_list image6 = new main_list(R.drawable.image_6, "image 6");
        main_list image7 = new main_list(R.drawable.camera1, "Use your camera to make your own picture");

        arrayPhotos.add(image1);
        arrayPhotos.add(image2);
        arrayPhotos.add(image3);
        arrayPhotos.add(image4);
        arrayPhotos.add(image5);
        arrayPhotos.add(image6);
        arrayPhotos.add(image7);

        listViewPhotos = (ListView) findViewById(R.id.list_photos);
        ListPhotosAdapter adapter = new ListPhotosAdapter(this, arrayPhotos);
        listViewPhotos.setAdapter(adapter);

        listViewPhotos.setOnItemClickListener(this);
    }

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static String IM_NAME;

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
            IM_NAME = File.separator + "IMG_"+ timeStamp + ".jpg";
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        main_list selectedPhoto = arrayPhotos.get(position);

        // create Intent to take a picture and return control to the calling application
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image

        // start the image capture Intent


        Intent intent = new Intent(ImageSelection.this, GamePlay.class);
        if (selectedPhoto.getName().equals("Use your camera to make your own picture")){
            startActivityForResult(intent2, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//            Toast.makeText(this, "Shoot your own photo!", Toast.LENGTH_LONG).show();

        }

        else {
            intent.putExtra("Img",selectedPhoto.getDrawableId());
//            Toast.makeText(this, "You've selected :\n" + selectedPhoto.getName(), Toast.LENGTH_LONG).show();
            startActivity(intent);
        };


    }


    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    public static String IM_LOC;

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                IM_LOC = getPath(data.getData());

//                Toast.makeText(this, "Image saved as:\n" +
//                        IM_LOC, Toast.LENGTH_LONG).show();
            }

            else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            }

            else {
                // Image capture failed, advise user
            }

            Intent intent  = new Intent(ImageSelection.this, GamePlay.class);
            intent.putExtra("Cam",IM_LOC);



            startActivity(intent);
        }

        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Video captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
            } else {
                // Video capture failed, advise user
            }
        }
    }





}
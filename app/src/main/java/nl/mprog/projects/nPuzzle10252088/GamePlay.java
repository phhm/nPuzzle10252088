package nl.mprog.projects.nPuzzle10252088;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class GamePlay extends ImageSelection {

    public static Integer photo_loci;
    public static String photo_locs;
    public static Bitmap bitmap1;
    public static Bitmap bitmap2;
    public static ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
    private static Integer difficulty = 3;
    public static float height2;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (difficulty == 3){
            setContentView(R.layout.game_layout);
        }

        else if (difficulty == 4){
            setContentView(R.layout.game_layout2);
        }

        else if (difficulty == 5){
            setContentView(R.layout.game_layout3);
        }
        setContentView(R.layout.game_layout);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(GamePlay.this, "" + position, Toast.LENGTH_SHORT).show();

            }
        });

        if (getIntent().hasExtra("Cam")){
            photo_locs = getIntent().getStringExtra("Cam");
            bitmap1 = BitmapFactory.decodeFile(photo_locs);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(),bitmap1.getHeight(), matrix, false);
            int width = (getResources().getDisplayMetrics().widthPixels);

            if (bitmap1.getWidth() >= bitmap1.getHeight()) {
                height2 = ((float)width / (float)bitmap1.getWidth()) * (float)bitmap1.getHeight();
                bitmap1 = Bitmap.createScaledBitmap(bitmap1, width, (int)height2, true);
                for (int y = 0; y < difficulty; y = y+1){
                    for (int x = 0; x < difficulty; x = x+1){
                        bitmap2 = Bitmap.createBitmap(bitmap1,x*(bitmap1.getWidth()/difficulty),y*(bitmap1.getHeight()/difficulty),bitmap1.getWidth()/difficulty,bitmap1.getHeight()/difficulty);
                        bitmapArray.add(bitmap2); // Add a bitmap
                    }
                }

            }

            else if (bitmap1.getWidth() < bitmap1.getHeight()) {
                bitmap1 = Bitmap.createScaledBitmap(bitmap1, width, bitmap1.getHeight() * width/bitmap1.getWidth(), true);
                for (int y = 0; y < difficulty; y = y+1){
                    for (int x = 0; x < difficulty; x = x+1){
                        bitmap2 = Bitmap.createBitmap(bitmap1,x*(bitmap1.getWidth()/difficulty),y*(bitmap1.getHeight()/difficulty),bitmap1.getWidth()/difficulty,bitmap1.getHeight()/difficulty);
                        bitmapArray.add(bitmap2); // Add a bitmap
                    }
                }

            }

        }

        else if (getIntent().hasExtra("Img")) {
            photo_loci = getIntent().getExtras().getInt("Img");
            bitmap1 = BitmapFactory.decodeResource(getResources(), photo_loci);

            int width = (getResources().getDisplayMetrics().widthPixels);

            if (bitmap1.getWidth() >= bitmap1.getHeight()) {
                height2 = ((float)width / (float)bitmap1.getWidth()) * (float)bitmap1.getHeight();
                bitmap1 = Bitmap.createScaledBitmap(bitmap1, width, (int)height2, true);
                for (int y = 0; y < difficulty; y = y+1){
                    for (int x = 0; x < difficulty; x = x+1){
                        bitmap2 = Bitmap.createBitmap(bitmap1,x*(bitmap1.getWidth()/difficulty),y*(bitmap1.getHeight()/difficulty),bitmap1.getWidth()/difficulty,bitmap1.getHeight()/difficulty);
                        bitmapArray.add(bitmap2); // Add a bitmap
                    }
                }

            }

            else if (bitmap1.getWidth() < bitmap1.getHeight()) {
                bitmap1 = Bitmap.createScaledBitmap(bitmap1, width, bitmap1.getHeight() * width/bitmap1.getWidth(), true);
                for (int y = 0; y < difficulty; y = y+1){
                    for (int x = 0; x < difficulty; x = x+1){
                        bitmap2 = Bitmap.createBitmap(bitmap1,x*(bitmap1.getWidth()/difficulty),y*(bitmap1.getHeight()/difficulty),bitmap1.getWidth()/difficulty,bitmap1.getHeight()/difficulty);
                        bitmapArray.add(bitmap2); // Add a bitmap
                    }
                }

            }


        }

        if (difficulty == 4){
            Collections.reverse(bitmapArray);
        }
        else{
            Collections.reverse(bitmapArray);
            Collections.swap(bitmapArray,bitmapArray.size()-3,bitmapArray.size()-2);
        }

    }


//    ImageView imageView = (ImageView) findViewById(R.id.imageView1);
//    imageView.setImageBitmap(bitmap1);
//    class HideImage extends TimerTask{
//        public void run(){
//            imageView.setVisibility(View.GONE);
//        }
//    }
//
//    int timeout_ms = 5000;
//    Timer timer = new Timer();
//    timer.schedule(new HideImage(), timeout_ms);


    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return bitmapArray.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            int width = bitmap2.getWidth();
            int height = bitmap2.getHeight();

            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(width, height));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0, 0, 0, 0);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageBitmap(bitmapArray.get(position));


            return imageView;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_image_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.shuffle:
                Toast.makeText(this, "You've selected option 1", Toast.LENGTH_LONG).show();
                return true;
            case R.id.difficulty:
                Toast.makeText(this, "You've selected option 2", Toast.LENGTH_LONG).show();
                return true;
            case R.id.quit:
                Toast.makeText(this, "You've selected to quit", Toast.LENGTH_LONG).show();
                return true;
            case R.id.easy:
                Toast.makeText(this, "Let's keep it easy", Toast.LENGTH_LONG).show();
                difficulty = 3;
            case R.id.medium:
                Toast.makeText(this, "Make it a medium!", Toast.LENGTH_LONG).show();
                difficulty = 4;
            case R.id.hard:
                Toast.makeText(this, "Going pro?", Toast.LENGTH_LONG).show();
                difficulty = 5;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}

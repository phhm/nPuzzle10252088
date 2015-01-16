package nl.mprog.projects.nPuzzle10252088;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static nl.mprog.projects.nPuzzle10252088.R.layout.*;

public class main_list {

    private int drawableId;
    private String name;

    public main_list(int drawableId, String name) {
        super();
        this.drawableId = drawableId;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public int getDrawableId(){
        return drawableId;
    }


    public void setDrawableId(int drawableId){
    this.drawableId = drawableId;
    }
}
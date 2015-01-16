package nl.mprog.projects.nPuzzle10252088;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListPhotosAdapter extends BaseAdapter {

    Context context;

    protected List<main_list> listPhotos;
    LayoutInflater inflater;

    public ListPhotosAdapter(Context context, List<main_list> listPhotos){
        this.listPhotos = listPhotos;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount(){
        return listPhotos.size();
    }

    public main_list getItem(int position){
        return listPhotos.get(position);
    }

    public long getItemId(int position){
        return listPhotos.get(position).getDrawableId();
    }
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.layout_list_item, parent, false);
            holder.txtlabel_picture_name = (TextView) convertView
                    .findViewById(R.id.label_picture_name);

            holder.img_photo = (ImageView) convertView.findViewById(R.id.img_photo);

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        main_list photo = listPhotos.get(position);
        holder.txtlabel_picture_name.setText(photo.getName());
        holder.img_photo.setImageResource(photo.getDrawableId());

        return convertView;
    }

    private class ViewHolder{
        ImageView img_photo;
        TextView txtlabel_picture_name;
    }

}

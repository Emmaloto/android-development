package com.example.emmalotopassy.advancedandroid;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListView extends ArrayAdapter<String>{

    private String name[];
    private int imageIDs[];
    Activity context;

    /*
    public CustomListView(@NonNull Context context, int resource) {
        super(context, resource);
    }
*/
    public CustomListView(Activity context, String [] n, int [] img) {
        super(context, R.layout.list_image_layout, n);

        name = n;
        imageIDs = img;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*
        View r = convertView;
        ViewHolder vHold = null;
        if(r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.list_image_layout, null, true);
            vHold = new ViewHolder(r);
            r.setTag(vHold);
        }else{
            vHold = (ViewHolder)r.getTag();

        }

        vHold.textname.setText(name[position]);
        vHold.imageView.setImageResource(imageIDs[position]);

        return super.getView(position, convertView, parent);
        */
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View rowView = convertView;
        if(rowView == null) {
            rowView = layoutInflater.inflate(R.layout.list_image_layout, parent, false);
        }

        String listItem = getItem(position);
        TextView nameText = rowView.findViewById(R.id.textName);
        ImageView image = rowView.findViewById(R.id.listImage);

        nameText.setText(listItem);
        //image.setImageResource(imageIDs[position]); // Bug?
        image.setImageBitmap(ImageScaler.decodeSampledBitmapFromResource(getContext().getResources(), imageIDs[position], 100, 100));

        return rowView;
    }

}

class ViewHolder{
    TextView textname;
    ImageView imageView;

    public ViewHolder(View view){

        //CustomListView v = (CustomListView)view;
        //textname = view.findViewById(R.id.);
        //imageView = view.findViewById(R.layout.list_image_layout.);

    }
}

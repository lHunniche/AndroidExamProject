package dk.danskpilsner.amiibo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dk.danskpilsner.amiibo.R;
import dk.danskpilsner.amiibo.models.AmiiboList;

public class AmiiboListAdapter extends RecyclerView.Adapter<AmiiboListAdapter.MyViewHolder>
{
    private AmiiboList amiiboList;
    private Context context;

    public AmiiboListAdapter(Context context, AmiiboList amiiboList)
    {
        this.amiiboList = amiiboList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_amiibo_list, viewGroup, false);
        TextView name = view.findViewById(R.id.recyclerview_name);
        TextView gameSeries = view.findViewById(R.id.recyclerview_amiibo_series);
        TextView type = view.findViewById(R.id.recyclerview_type);
        ImageView image = view.findViewById(R.id.recyclerview_amiibo_image);

        return new MyViewHolder(view, name, gameSeries, type, image);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        myViewHolder.name.setText(amiiboList.getAmiibo().get(i).getName());
        myViewHolder.amiiboSeries.setText(amiiboList.getAmiibo().get(i).getAmiiboSeries());
        myViewHolder.type.setText(amiiboList.getAmiibo().get(i).getType());
        Glide.with(context).load(amiiboList.getAmiibo().get(i).getImage()).into(myViewHolder.image);
    }

    @Override
    public int getItemCount()
    {
        return amiiboList.getAmiibo().size();
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView amiiboSeries;
        private TextView type;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView, TextView name, TextView amiiboSeries, TextView type, ImageView image)
        {
            super(itemView);
            this.name = name;
            this.amiiboSeries = amiiboSeries;
            this.type = type;
            this.image = image;

        }
    }
}

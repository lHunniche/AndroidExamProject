package dk.danskpilsner.amiibo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dk.danskpilsner.amiibo.fragments.AmiiboShowcaseFragment;
import dk.danskpilsner.amiibo.R;
import dk.danskpilsner.amiibo.models.Amiibo;
import dk.danskpilsner.amiibo.models.AmiiboList;

public class AmiiboListAdapter extends RecyclerView.Adapter<AmiiboListAdapter.MyViewHolder>
{
    private static AmiiboList amiiboList;
    private static Context context;

    public AmiiboListAdapter(Context context, AmiiboList amiiboList)
    {
        AmiiboListAdapter.amiiboList = amiiboList;
        AmiiboListAdapter.context = context;
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

    public static class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener
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
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view)
        {
            //  fetching the Amiibo that is to be parsed into the new showcase fragment.
            Amiibo amiibo = AmiiboListAdapter.amiiboList.getAmiibo().get(getLayoutPosition());


            // using the activity context to get fragmentManager, and use him to start a fragment transaction.
            AppCompatActivity appCompatActivity = (AppCompatActivity) context;
            FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
            AmiiboShowcaseFragment fragment = new AmiiboShowcaseFragment();
            fragment.setAmiibo(amiibo);

            if (appCompatActivity.findViewById(R.id.fragment_container) != null) {
                // two first animations specify what the new fragment and current fragment should do, respectively.
                // the last two animations specify what the new fragment and current fragment should do, respectively.
                // this causes the fragments to slide from one to another, making it look super smooz and slick.
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);

            } else {
                if (appCompatActivity.getSupportFragmentManager().findFragmentById(R.id.amiibo_details) != null) {
                    transaction.replace(R.id.amiibo_details, fragment);
                } else {
                    transaction.add(R.id.amiibo_details, fragment);
                }
            }
            transaction.commit();
        }
    }
}

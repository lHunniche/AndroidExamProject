package dk.danskpilsner.amiibo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.danskpilsner.amiibo.R;
import dk.danskpilsner.amiibo.adapters.AmiiboListAdapter;
import dk.danskpilsner.amiibo.models.AmiiboList;

public class AmiiboListFragment extends Fragment
{
    private RecyclerView recyclerView;
    private AmiiboListAdapter adapter;
    private AmiiboList apiReponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.amiibo_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedIntanceState)
    {
        recyclerView = view.findViewById(R.id.amiibo_recyclerview);
        adapter = new AmiiboListAdapter(getContext(), apiReponse);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setApiReponse(AmiiboList apiReponse)
    {
        this.apiReponse = apiReponse;
    }

}

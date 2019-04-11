package dk.danskpilsner.amiibo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import dk.danskpilsner.amiibo.fragments.AmiiboListFragment;
import dk.danskpilsner.amiibo.models.AmiiboList;
import dk.danskpilsner.amiibo.network.AmiiboServiceProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerClickedItemHelper
{
    private static SparseArray<String> drawerMapping;

    private static void initDrawerMapping()
    {
        drawerMapping = new SparseArray<>();

        drawerMapping.append(R.id.nav_animal_crossing, "Animal Crossing");
        drawerMapping.append(R.id.nav_bayonetta, "Bayonetta");
        drawerMapping.append(R.id.nav_boxboy, "BoxBoy!");
        drawerMapping.append(R.id.nav_chibi_robo, "Chibi Robo");
        drawerMapping.append(R.id.nav_classic_nintendo, "Classic Nintendo");
        drawerMapping.append(R.id.nav_dark_souls, "Dark Souls");
        drawerMapping.append(R.id.nav_diablo, "Diablo");
        drawerMapping.append(R.id.nav_donkey_kong, "Donkey Kong");
        drawerMapping.append(R.id.nav_earthbound, "Earthbound");
        drawerMapping.append(R.id.nav_f_zero, "F-zero");
        drawerMapping.append(R.id.nav_final_fantasy, "Final Fantasy");
        drawerMapping.append(R.id.nav_fire_emblem, "Fire Emblem");
        drawerMapping.append(R.id.nav_kellogs, "Kellogs");
        drawerMapping.append(R.id.nav_kid_icarus, "Kid Icarus");
        drawerMapping.append(R.id.nav_kirby, "Kirby");
        drawerMapping.append(R.id.nav_mario_sports_superstars, "Mario Sports Superstars");
        drawerMapping.append(R.id.nav_megaman, "Megaman");
        drawerMapping.append(R.id.nav_metroid, "Metroid");
        drawerMapping.append(R.id.nav_Mii, "Mii");
        drawerMapping.append(R.id.nav_monster_hunter, "Monster Hunter");
        drawerMapping.append(R.id.nav_pac_man, "Pac-man");
        drawerMapping.append(R.id.nav_pikmin, "Pikmin");
        drawerMapping.append(R.id.nav_pokemon, "Pokemon");
        drawerMapping.append(R.id.nav_punch_out, "Punch Out");
        drawerMapping.append(R.id.nav_shovel_knight, "Shovel Knight");
        drawerMapping.append(R.id.nav_sonic, "Sonic");
        drawerMapping.append(R.id.nav_splatoon, "Splatoon");
        drawerMapping.append(R.id.nav_star_fox, "Star Fox");
        drawerMapping.append(R.id.nav_street_fighter, "Street Fighter");
        drawerMapping.append(R.id.nav_super_mario, "Super Mario");
        drawerMapping.append(R.id.nav_wii_fit, "Wii Fit");
        drawerMapping.append(R.id.nav_xenoblade, "Xenoblade");
        drawerMapping.append(R.id.nav_zelda, "The Legend of Zelda");
        drawerMapping.append(R.id.nav_yoshi, "Yoshi's Woolly World");

    }

    public static void handleClickedItem(AppCompatActivity context, MenuItem menuItem, ProgressBar progressBar)
    {
        if(drawerMapping == null)
        {
            initDrawerMapping();
        }
        makeNetWorkCallForAmiibos(context, drawerMapping.get(menuItem.getItemId()), progressBar);
    }



    private static void makeNetWorkCallForAmiibos(AppCompatActivity context, String gameSeries, ProgressBar progressBar)
    {
        Call<AmiiboList> call = AmiiboServiceProvider.getService().getAmiibosFromSeries(gameSeries);
        call.enqueue(new Callback<AmiiboList>()
        {
            @Override
            public void onResponse(Call<AmiiboList> call, Response<AmiiboList> response)
            {
                if (context.findViewById(R.id.fragment_container) != null) { // single-pane layout
                    if (context.getSupportFragmentManager().findFragmentById(R.id.fragment_container) != null) {
                        // replace content of list
                        replaceContentOfFragment(context, response, R.id.fragment_container);
                    } else {
                        // create new fragment, and do the transaction
                        addNewFragment(context, response, R.id.fragment_container);
                    }
                } else {
                    if (context.getSupportFragmentManager().findFragmentById(R.id.amiibo_list) != null) {
                        replaceContentOfFragment(context, response, R.id.amiibo_list);
                    } else {
                        addNewFragment(context, response, R.id.amiibo_list);
                    }
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AmiiboList> call, Throwable t)
            {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private static void addNewFragment(AppCompatActivity context, Response<AmiiboList> response, int fragmentID)
    {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        AmiiboListFragment fragment = new AmiiboListFragment();
        fragment.setApiReponse(response.body());
        transaction.add(fragmentID, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private static void replaceContentOfFragment(AppCompatActivity context, Response<AmiiboList> response, int fragmentID)
    {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        AmiiboListFragment fragment = new AmiiboListFragment();
        fragment.setApiReponse(response.body());
        transaction.replace(fragmentID, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
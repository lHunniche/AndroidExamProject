package dk.danskpilsner.amiibo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
    public static void handleClickedItem(AppCompatActivity context, MenuItem menuItem, ProgressBar progressBar)
    {
        switch (menuItem.getItemId())
        {
            case R.id.nav_animal_crossing:
                makeNetWorkCallForAmiibos(context, "Animal Crossing", progressBar);
                break;
            case R.id.nav_bayonetta:
                makeNetWorkCallForAmiibos(context, "Bayonetta", progressBar);
                break;
            case R.id.nav_boxboy:
                makeNetWorkCallForAmiibos(context, "BoxBoy!", progressBar);
                break;
            case R.id.nav_chibi_robo:
                makeNetWorkCallForAmiibos(context, "Chibi Robo", progressBar);
                break;
            case R.id.nav_classic_nintendo:
                makeNetWorkCallForAmiibos(context, "Classic Nintendo", progressBar);
                break;
            case R.id.nav_dark_souls:
                makeNetWorkCallForAmiibos(context, "Dark Souls", progressBar);
                break;
            case R.id.nav_diablo:
                makeNetWorkCallForAmiibos(context, "Diablo", progressBar);
                break;
            case R.id.nav_donkey_kong:
                makeNetWorkCallForAmiibos(context, "Donkey Kong", progressBar);
                break;
            case R.id.nav_earthbound:
                makeNetWorkCallForAmiibos(context, "Earthbound", progressBar);
                break;
            case R.id.nav_f_zero:
                makeNetWorkCallForAmiibos(context, "F-zero", progressBar);
                break;
            case R.id.nav_final_fantasy:
                makeNetWorkCallForAmiibos(context, "Final Fantasy", progressBar);
                break;
            case R.id.nav_fire_emblem:
                makeNetWorkCallForAmiibos(context, "Fire Emblem", progressBar);
                break;
            case R.id.nav_kellogs:
                makeNetWorkCallForAmiibos(context, "Kellogs", progressBar);
                break;
            case R.id.nav_kid_icarus:
                makeNetWorkCallForAmiibos(context, "Kid Icarus", progressBar);
                break;
            case R.id.nav_kirby:
                makeNetWorkCallForAmiibos(context, "Kirby", progressBar);
                break;
            case R.id.nav_mario_sports_superstars:
                makeNetWorkCallForAmiibos(context, "Mario Sports Superstars", progressBar);
                break;
            case R.id.nav_megaman:
                makeNetWorkCallForAmiibos(context, "Megaman", progressBar);
                break;
            case R.id.nav_metroid:
                makeNetWorkCallForAmiibos(context, "Metroid", progressBar);
                break;
            case R.id.nav_Mii:
                makeNetWorkCallForAmiibos(context, "Mii", progressBar);
                break;
            case R.id.nav_monster_hunter:
                makeNetWorkCallForAmiibos(context, "Monster Hunter", progressBar);
                break;
            case R.id.nav_pac_man:
                makeNetWorkCallForAmiibos(context, "Pac-man", progressBar);
                break;
            case R.id.nav_pikmin:
                makeNetWorkCallForAmiibos(context, "Pikmin", progressBar);
                break;
            case R.id.nav_pokemon:
                makeNetWorkCallForAmiibos(context, "Pokemon", progressBar);
                break;
            case R.id.nav_punch_out:
                makeNetWorkCallForAmiibos(context, "Punch Out", progressBar);
                break;
            case R.id.nav_shovel_knight:
                makeNetWorkCallForAmiibos(context, "Shovel Knight", progressBar);
                break;
            case R.id.nav_sonic:
                makeNetWorkCallForAmiibos(context, "Sonic", progressBar);
                break;
            case R.id.nav_splatoon:
                makeNetWorkCallForAmiibos(context, "Splatoon", progressBar);
                break;
            case R.id.nav_star_fox:
                makeNetWorkCallForAmiibos(context, "Star Fox", progressBar);
                break;
            case R.id.nav_street_fighter:
                makeNetWorkCallForAmiibos(context, "Street Fighter", progressBar);
                break;
            case R.id.nav_super_mario:
                makeNetWorkCallForAmiibos(context, "Super Mario", progressBar);
                break;
            case R.id.nav_wii_fit:
                makeNetWorkCallForAmiibos(context, "Wii Fit", progressBar);
                break;
            case R.id.nav_xenoblade:
                makeNetWorkCallForAmiibos(context, "Xenoblade", progressBar);
                break;
            case R.id.nav_yoshi:
                makeNetWorkCallForAmiibos(context, "Yoshi's Woolly World", progressBar);
                break;
            case R.id.nav_zelda:
                makeNetWorkCallForAmiibos(context, "The Legend of Zelda", progressBar);
                break;
            default:
                break;
        }


    }

    private static void makeNetWorkCallForAmiibos(AppCompatActivity context, String gameSeries, ProgressBar progressBar)
    {
        Call<AmiiboList> call = AmiiboServiceProvider.getService().getAmiibosFromSeries(gameSeries);
        call.enqueue(new Callback<AmiiboList>()
        {
            @Override
            public void onResponse(Call<AmiiboList> call, Response<AmiiboList> response)
            {
                if (context.getSupportFragmentManager().findFragmentById(R.id.fragment_container) != null)
                {
                    // replace content of list
                    replaceContentOfFragment(context, response);
                }
                else
                {
                    // create new fragment, and do the transaction
                    addNewFragment(context, response);
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

    private static void addNewFragment(AppCompatActivity context, Response<AmiiboList> response)
    {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        AmiiboListFragment fragment = new AmiiboListFragment();
        fragment.setApiReponse(response.body());
        transaction.add(R.id.fragment_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    private static void replaceContentOfFragment(AppCompatActivity context, Response<AmiiboList> response)
    {
//        AmiiboListFragment fragment = (AmiiboListFragment) context.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//        fragment.getAdapter().setAmiiboList(response.body());
//        fragment.getAdapter().notifyDataSetChanged();

        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        AmiiboListFragment fragment = new AmiiboListFragment();
        fragment.setApiReponse(response.body());
        transaction.replace(R.id.fragment_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}
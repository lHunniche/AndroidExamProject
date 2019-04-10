package dk.danskpilsner.amiibo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import dk.danskpilsner.amiibo.models.AmiiboList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerClickedItemHelper
{
    public static void handleClickedItem(AppCompatActivity context, MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.nav_animal_crossing:
                makeNetWorkCallForAmiibos(context, "Animal Crossing");
                break;
            case R.id.nav_bayonetta:
                makeNetWorkCallForAmiibos(context, "Bayonetta");
                break;
            case R.id.nav_boxboy:
                makeNetWorkCallForAmiibos(context, "BoxBoy!");
                break;
            case R.id.nav_chibi_robo:
                makeNetWorkCallForAmiibos(context, "Chibi Robo");
                break;
            case R.id.nav_classic_nintendo:
                makeNetWorkCallForAmiibos(context, "Classic Nintendo");
                break;
            case R.id.nav_dark_souls:
                makeNetWorkCallForAmiibos(context, "Dark Souls");
                break;
            case R.id.nav_diablo:
                makeNetWorkCallForAmiibos(context, "Diablo");
                break;
            case R.id.nav_donkey_kong:
                makeNetWorkCallForAmiibos(context, "Donkey Kong");
                break;
            case R.id.nav_earthbound:
                makeNetWorkCallForAmiibos(context, "Earthbound");
                break;
            case R.id.nav_f_zero:
                makeNetWorkCallForAmiibos(context, "F-zero");
                break;
            case R.id.nav_final_fantasy:
                makeNetWorkCallForAmiibos(context, "Final Fantasy");
                break;
            case R.id.nav_fire_emblem:
                makeNetWorkCallForAmiibos(context, "Fire Emblem");
                break;
            case R.id.nav_kellogs:
                makeNetWorkCallForAmiibos(context, "Kellogs");
                break;
            case R.id.nav_kid_icarus:
                makeNetWorkCallForAmiibos(context, "Kid Icarus");
                break;
            case R.id.nav_kirby:
                makeNetWorkCallForAmiibos(context, "Kirby");
                break;
            case R.id.nav_mario_sports_superstars:
                makeNetWorkCallForAmiibos(context, "Mario Sports Superstars");
                break;
            case R.id.nav_megaman:
                makeNetWorkCallForAmiibos(context, "Megaman");
                break;
            case R.id.nav_metroid:
                makeNetWorkCallForAmiibos(context, "Metroid");
                break;
            case R.id.nav_Mii:
                makeNetWorkCallForAmiibos(context, "Mii");
                break;
            case R.id.nav_monster_hunter:
                makeNetWorkCallForAmiibos(context, "Monster Hunter");
                break;
            case R.id.nav_pac_man:
                makeNetWorkCallForAmiibos(context, "Pac-man");
                break;
            case R.id.nav_pikmin:
                makeNetWorkCallForAmiibos(context, "Pikmin");
                break;
            case R.id.nav_pokemon:
                makeNetWorkCallForAmiibos(context, "Pokemon");
                break;
            case R.id.nav_punch_out:
                makeNetWorkCallForAmiibos(context, "Punch Out");
                break;
            case R.id.nav_shovel_knight:
                makeNetWorkCallForAmiibos(context, "Shovel Knight");
                break;
            case R.id.nav_sonic:
                makeNetWorkCallForAmiibos(context, "Sonic");
                break;
            case R.id.nav_splatoon:
                makeNetWorkCallForAmiibos(context, "Splatoon");
                break;
            case R.id.nav_star_fox:
                makeNetWorkCallForAmiibos(context, "Star Fox");
                break;
            case R.id.nav_street_fighter:
                makeNetWorkCallForAmiibos(context, "Street Fighter");
                break;
            case R.id.nav_super_mario:
                makeNetWorkCallForAmiibos(context, "Super Mario");
                break;
            case R.id.nav_wii_fit:
                makeNetWorkCallForAmiibos(context, "Wii Fit");
                break;
            case R.id.nav_xenoblade:
                makeNetWorkCallForAmiibos(context, "Xenoblade");
                break;
            case R.id.nav_yoshi:
                makeNetWorkCallForAmiibos(context, "Yoshi's Woolly World");
                break;
            case R.id.nav_zelda:
                makeNetWorkCallForAmiibos(context, "Zelda");
                break;
            default:
                break;
        }


    }

    private static void makeNetWorkCallForAmiibos(AppCompatActivity context, String gameSeries)
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


            }

            @Override
            public void onFailure(Call<AmiiboList> call, Throwable t)
            {

            }
        });
    }

    private static void addNewFragment(AppCompatActivity context, Response<AmiiboList> response)
    {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        AmiiboListFragment fragment = new AmiiboListFragment();
        fragment.setApiReponse(response.body());
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private static void replaceContentOfFragment(AppCompatActivity context, Response<AmiiboList> response)
    {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        AmiiboListFragment fragment = new AmiiboListFragment();
        fragment.setApiReponse(response.body());
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
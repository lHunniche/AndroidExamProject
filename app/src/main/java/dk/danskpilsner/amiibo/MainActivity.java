package dk.danskpilsner.amiibo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import dk.danskpilsner.amiibo.Adapters.AmiiboListAdapter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private AmiiboListAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawerLayout();
        initNavigationView();
        initToolbar();
    }


    private void initDrawerLayout()
    {
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    private void initNavigationView()
    {
        // fetch navigation view object
        navigationView = findViewById(R.id.nav_view);

        // add listener, to handle when a list item is clicked. The clicked item is parsed into the listener as "menuItem"
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here
                DrawerClickedItemHelper.handleClickedItem(MainActivity.this, menuItem);

                return true;
            }
        });

        // this needs to be called, otherwise the icons will be mostly grey
        navigationView.setItemIconTintList(null);
    }

    private void initToolbar()
    {
        // fetch toolbar object
        toolbar = findViewById(R.id.toolbar);

        // set the activities action bar to the parsed actionbar
        setSupportActionBar(toolbar);

        // get the reference to the action bar, enable home button and set icon
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }


    // this method is necessary to detect when the burger icon is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

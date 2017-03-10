package com.dmytrobohdanov.getmafianumber.Activities;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Navigation activity fragment
 */
public abstract class DrawerFragmentNavigationActivity extends AppCompatActivity {
    private static final int NO_ID = -1;
    /**
     * Drawer's id. Have to be initialized
     */
    protected int drawerId = NO_ID;
    protected int fragmentContainersId = NO_ID;

    /**
     * Keeps tag of current displaying tag
     */
    protected String currentFragmentTag;
    private Fragment currentFragment;


    /**
     * Drawer's items onClickListener
     */
    public void onNavigationItemSelected(View view) {
        processDrawerSelection(view);
        closeDrawer();
    }

    /**
     * Processing Drawer Selection
     *
     * @param drawersElement drawer's element clicked at
     */
    protected abstract void processDrawerSelection(View drawersElement);


    /**
     * Showing fragment in container, could pass specified data throght
     * do not add it to backstack
     *
     * @param fragmentTag
     * @param dataForFragment
     */
    public void showFragmentNoBackStack(String fragmentTag, Object dataForFragment) {
        if ((currentFragmentTag == null) || (!currentFragmentTag.equals(fragmentTag)))
            showFragment(fragmentTag, dataForFragment, false);
    }

    public void showFragmentAddToBackStack(String fragmentTag, Object dataForFragment) {
        if ((currentFragmentTag == null) || (!currentFragmentTag.equals(fragmentTag)))
            showFragment(fragmentTag, dataForFragment, true);
    }


    /**
     * Showing fragment
     *
     * @param fragmentTag    to show fragment tag
     * @param object         to pass to created fragment throw Bundle
     * @param addToBackStack if true - add to back stack
     */
    private void showFragment(String fragmentTag, Object object, Boolean addToBackStack) {
        currentFragmentTag = fragmentTag;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //creating fragment
        Fragment fragment = getFragmentToShowByTag(fragmentTag, object);

        currentFragment = fragment;
        fragmentTransaction.replace(fragmentContainersId, fragment);


        if (addToBackStack) {
            fragmentTransaction.addToBackStack(currentFragmentTag);
        }
        fragmentTransaction.commit();
    }


    /**
     * Creating fragment to display
     * if there is data to pass - put it to Bundle
     *
     * @param fragmentTag tag of fragment to show
     * @param data        data to put in bundle
     * @return fragment to display, with of without bundle
     */
    protected abstract Fragment getFragmentToShowByTag(String fragmentTag, Object data);


    public void setFragment(Fragment fragment) {
        this.currentFragment = fragment;
    }

    /**
     * Closing drawer
     */
    public void closeDrawer() {
        if (drawerId == NO_ID) {
            throw new IllegalStateException("Have to init drawer's id");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(drawerId);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        }
    }

    @Override
    public void onBackPressed() {
        closeDrawer();
        currentFragmentTag = null;
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        else {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            super.onBackPressed();
        }
    }
}

package julio.com.br.reverb.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.BaseAdapter;

import julio.com.br.reverb.fragment.NowPlayingFragment;
import julio.com.br.reverb.fragment.SongListFragment;

/**
 * Created by Shido on 08/05/2016.
 */
public class TabsAdapter extends FragmentStatePagerAdapter{


    public TabsAdapter(FragmentManager fm){
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        SongListFragment songListFragment = null;
        NowPlayingFragment nowPlayingFragment = null;
        switch (position) {
            case (0):
                    songListFragment = new SongListFragment();
                return songListFragment;

            case (1):
                nowPlayingFragment = new NowPlayingFragment();
                return nowPlayingFragment;

        }

        Bundle args = new Bundle();
        return null;
    }




    @Override
    public int getCount() {
        return 2;
    }
}

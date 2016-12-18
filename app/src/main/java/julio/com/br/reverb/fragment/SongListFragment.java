package julio.com.br.reverb.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import julio.com.br.reverb.Globals;
import julio.com.br.reverb.MainActivity;
import julio.com.br.reverb.R;
import julio.com.br.reverb.adapter.RecyclerViewClickListener;
import julio.com.br.reverb.adapter.SongsAdapter;
import julio.com.br.reverb.model.Song;


public class SongListFragment extends Fragment implements RecyclerViewClickListener{

    @BindView(R.id.rvSongList)
    RecyclerView rvListSongs;

    private List<Song> songs;

    private SongsAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    public SongListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_song_list, container, false);

        ButterKnife.bind(this, view);

        layoutManager = new LinearLayoutManager(container.getContext());


        rvListSongs.setLayoutManager(layoutManager);
        adapter = new SongsAdapter(container.getContext(),songs, this);

        rvListSongs.setAdapter(adapter);



        return view;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        songs = new ArrayList<>();
        songs = Globals.globalSongs;
        super.onCreate(savedInstanceState);

    }


    @Override
    public void recyclerViewListClicked(View v, int position){
        //set up adapter and pass clicked listener this
        adapter =  new SongsAdapter(getActivity(), songs, this);

        Song selectedItem  = songs.get(position);

        // adapter.notifyItemRemoved(position);

        //Abre a ActivityDetails com o restaurante selecionado - A PARTIR DO ID.
       /* Intent i = new Intent(getActivity(), RestauranteDetailActivity.class);
        i.putExtra(Globals.SELECTEDRESTAURANTE,selectedItem.getIdRestaurante() );
        startActivity(i);*/


    }

}

package julio.com.br.reverb;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import julio.com.br.reverb.adapter.TabsAdapter;
import julio.com.br.reverb.service.MusicService;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.tabs)
    TabLayout tabs;


    @BindView(R.id.viewPager)
    ViewPager viewPager;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //http://android-arsenal.com/details/1/3555
        //https://github.com/android/platform_packages_apps_music/blob/master/src/com/android/music/MusicUtils.java
        setContentView(R.layout.activity_main);
        //ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        settingToolbar();
        settingTabs();
        musicService = new MusicService();

    }






    private MusicService musicService;

    private boolean musicBound = false;
    private MediaPlayer player ;
    //Handler para atualizar o tempo de musica.
    private Handler myHandler = new Handler();;





    //Conexão com o service para poder acessar os metodos do mesmo
    ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "Service is Connected", Toast.LENGTH_SHORT).show();
            musicBound = true;
            MusicService.MusicBinder mLocalBinder = (MusicService.MusicBinder)service;
            musicService = mLocalBinder.getServerInstance();

        }

        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
            musicService = null;
        }




    };






    @Override
    protected void onStart() {
        super.onStart();
        Intent mIntent = new Intent(this, MusicService.class);
        bindService(mIntent, serviceConnection, BIND_AUTO_CREATE);
        //Carrega as musicas.
        musicService.loadSongs(this);

    }



















    public void settingToolbar(){
        //Adicionando a toolbar como action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public void settingTabs(){
        tabs.addTab(tabs.newTab().setText(R.string.tabs_mysongs));
        tabs.addTab(tabs.newTab().setText(R.string.tabs_nowplaying));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL); //Gravity Fill completa tudo

        //Passando pro adapter um gerenciador de fragmentos
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAdapter);

        //Permite que as paginas troquem de acordo com o scroll:
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));



        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //TODO
                //Colocar animação ao trocar de tab
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }










        /*********************************************Menu****************************************************/
    @Override
    public boolean  onCreateOptionsMenu(Menu menu){
        //Colocando o menu criado "menu_main"
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();

        //Menu item = o que o usuario clicou, pega-se  o id
        switch (id){
            case R.id.mn_sobre:
                //Toast.makeText(this, "Sobre", Toast.LENGTH_LONG).show();

              /*  SobreDialogFragment edit = new SobreDialogFragment();
                FragmentManager fm = getSupportFragmentManager();
                edit.show(fm, getResources().getString(R.string.menu_sobre));*/



                break;
            case R.id.mn_sair:
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                super.onDestroy();
                break;

        }

        return super.onOptionsItemSelected(menuItem);
    }


    public MusicService getMusicService() {
        return musicService;
    }

    public void setMusicService(MusicService musicService) {
        this.musicService = musicService;
    }
}

package julio.com.br.reverb.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import julio.com.br.reverb.Globals;
import julio.com.br.reverb.model.Song;

/**
 * Created by Shido on 08/05/2016.
 */

//Load Music / Play Music
public class MusicService  extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnInfoListener, MediaPlayer.OnSeekCompleteListener {




    private MediaPlayer player;
    private final IBinder musicBinder = new MusicBinder();

    private static final String TAG = "SERVICE TAG";
    //song list
    private List<Song> songs;
    //current position
    private int songPosn;

    private Song musicaAtual;






    /**********************************Service Methods***************************************************************************/
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }





    //Bind do service com a Activity
    //private final IBinder mBinder = new MyBinder();
    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        Log.i("BIND SERVICE", "Service Bound");
        return musicBinder;
    }

    public class MusicBinder extends Binder {
       public MusicService getServerInstance() {
            return MusicService.this;
        }
    }








    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    public void loadAlbums(){
        /*String[] projection = new String[] { Albums._ID, Albums.ALBUM, Albums.ARTIST, Albums.ALBUM_ART, Albums.NUMBER_OF_SONGS };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = Media.ALBUM + " ASC";
        Cursor cursor = contentResolver.query(Albums.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, sortOrder);*/
    }





    public void loadSongs(Context context){
        songs = new ArrayList<>();
        if(context == null){
            Log.i("Context is null", "Context is null");
        }
        ContentResolver cr =   context.getContentResolver();


        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        //Isso limita a pasta onde o MediaStore irá buscar as musicas utilizando atraves da Query do cursor.
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String sortOrder = MediaStore.Audio.Media.ARTIST + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
        int count = 0;


        String[] projection = new String[] {MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM_ART,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST};

       /* Cursor cur2 = getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, new String[]
                {MediaStore.Audio.Albums.ALBUM_ART}, MediaStore.Audio.Albums._ID + "=?", new String[] {albumid}, null);

        cur.moveToFirst();
        String art = cur.getString(cur.getColumnIndex(Albums.ALBUM_ART));*/

        if(cur != null) {
            count = cur.getCount();
            Log.i("COUNT CURSOR", String.valueOf(count));
            if(count > 0 && cur !=null) {
                cur.moveToFirst();
                while(cur.moveToNext()) {
                    Song m = new Song();
                    String data = cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                    m.setTitle(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                    m.setArtist(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                    m.setFinalTime(cur.getDouble(cur.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                    m.setUri(data);

                    m.setAlbumID(cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                   // Log.i("URI3", uri3.toString());
                    /*
                    Cursor cursor2 =  cr.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                            new String[] {MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                            MediaStore.Audio.Albums._ID+ "=?",
                            new String[] {String.valueOf(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))},
                            null);
                    if (cursor2.moveToFirst()) {
                        String path = cursor2.getString(cursor2.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
                        Log.i("path album art", path);
                    }*/
                    // Save to your list here
                   // Log.i("MUSICA DATA", data);


                    songs.add(m);
                }

            }
        }

        /*Cursor cursor =  cr.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[] {MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                MediaStore.Audio.Albums._ID+ "=?",
                new String[] {String.valueOf(albumId)},
                null);

        if (cursor.moveToFirst()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
            Log.i("path album art", path);
        }*/


        Globals.globalSongs = songs;
        cur.close();
    }
















    /************************Service Methods***************************************************************************/

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }





}

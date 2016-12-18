package julio.com.br.reverb.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Shido on 08/05/2016.
 */
public class LyricService extends IntentService {

    public static final int LYRICS_OK = 1 ;
    public static final int LYRICS_ERROR = 0;

    public static final String RESULT_RECEIVER = "resultReceiver";


    public LyricService() {
        super(LyricService.class.getName());
    }



    protected void onHandleIntent(Intent intent) {
        /*final ResultReceiver receiver = intent.getParcelableExtra(LyricsService.RESULT_RECEIVER);
        String title = intent.getStringExtra(LyricsActivity.TITLE);
        String artist = intent.getStringExtra(LyricsActivity.ARTIST);

        JSONObject json = getResponse(artist, title);
        Log.i("JSON OBJECT JSON", json.toString());

        try{
            JSONObject mus = (JSONObject) json.getJSONArray("mus").get(0);

            Bundle b = new Bundle();
            b.putString("Lyrics", mus.getString("text"));

            receiver.send(LYRICS_OK, b);

        }catch (Exception e) {
            receiver.send(LYRICS_ERROR,Bundle.EMPTY);
            //e.printStackTrace();

        }*/




    }

}

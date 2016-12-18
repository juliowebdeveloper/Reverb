package julio.com.br.reverb;

import android.net.Uri;

import java.util.List;

import julio.com.br.reverb.model.Song;

/**
 * Created by Shido on 10/05/2016.
 */
public class Globals {

    //Lista de Musica para não haja necessidade de sempre ser carregada.


    public static List<Song> globalSongs;

    final public static Uri sArtworkUri = Uri
            .parse("content://media/external/audio/albumart");

}

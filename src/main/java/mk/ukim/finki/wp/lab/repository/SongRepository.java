package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository {

    public List<Song> findAll() {
        return DataHolder.songs;
    }

    public Song findByTrackId(String trackId) {
        return DataHolder.songs.stream()
                .filter(i -> i.getTrackId().equals(trackId))
                .findFirst()
                .orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        for (Song s : DataHolder.songs) {
            if (s.getTrackId().equals(song.getTrackId())) {
                s.addPerformer(artist);
                save(s);  // Save the song after modifying it
                return artist;
            }
        }
        return null;
    }

    // New method to save the song list after modification
    private void save(Song song) {
        // In your case, you may just update the list in DataHolder
        DataHolder.songs = DataHolder.songs;  // For now, assume this is updating
    }
}

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
        // Find the song by trackId using stream filtering
        Song artist_song = DataHolder.songs.stream()
                .filter(pesna -> pesna.equals(song))  // Uses the overridden equals method
                .findFirst()
                .orElse(null);  // If no song found, return null

        if (artist_song != null) {
            // Add the artist to the performers list
            artist_song.getPerformers().add(artist);
            return artist;  // Return the artist added
        }

        return null;  // Return null if the song was not found
    }


}

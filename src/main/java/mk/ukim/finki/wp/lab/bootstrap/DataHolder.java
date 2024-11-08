package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs=new ArrayList<>();

    @PostConstruct
    public void init()
    {
        artists.add(new Artist(100L,"Angela","Andonoska","najpoznatata pjacka na narodni pesni"));
        artists.add(new Artist(200L,"Kristijan","Krstevski","najdobriot gitarist"));
        artists.add(new Artist(300L,"Nikola","Hristovski","dirigent"));
        artists.add(new Artist(400L,"Ali","Ahmeti","majstor za shota"));
        artists.add(new Artist(500L,"Majkl","Dzekson","najdobar gimnastichar"));

        songs.add(new Song("fm","Always come back","Pop",2003));
        songs.add(new Song("am","HA HA","Metal",1997));
        songs.add(new Song("dvd","Gorilla-man","Trap",2001));
        songs.add(new Song("cd","Gen Z","TurboFolk",2000));
        songs.add(new Song("aux","Locked","Rap",2015));

    }
}

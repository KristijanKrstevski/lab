package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet{

    private final ArtistService artistService;
    private final SongService songService;
    private final SpringTemplateEngine springTemplateEngine;


    public ArtistServlet(ArtistService artistService, SpringTemplateEngine springTemplateEngine, SongService songService) {
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
        this.songService = songService;
    }

    @Override
    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String trackId = (String) req.getSession().getAttribute("trackId");

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("artists", artistService.listArtists());
        context.setVariable("trackId", trackId);

        springTemplateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String artistIdString = req.getParameter("artistId");
        String trackId = (String) req.getSession().getAttribute("trackId");

        if (artistIdString != null && trackId != null){
            Long artistId = Long.parseLong(artistIdString);

            Artist artist = artistService.findById(artistId);
            Song song = songService.findByTrackId(trackId);

            if (artist != null && song != null){
                songService.addArtistToSong(artist, song);
                req.getSession().setAttribute("selectedSong", song);
            }
        }
        resp.sendRedirect("/songDetails");
    }
}

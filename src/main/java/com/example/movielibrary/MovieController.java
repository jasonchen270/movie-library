package com.example.movielibrary;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository repo;

    public MovieController(MovieRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String index(@RequestParam(required = false) String q,
                        @RequestParam(required = false) String genre,
                        Model model) {
        List<Movie> movies;
        if (q != null && !q.isBlank()) {
            movies = repo.findByTitleContainingIgnoreCaseOrDirectorContainingIgnoreCase(q, q);
        } else if (genre != null && !genre.isBlank()) {
            movies = repo.findByGenreIgnoreCase(genre);
        } else {
            movies = repo.findAll();
        }
        model.addAttribute("movies", movies);
        model.addAttribute("genres", repo.findAll().stream()
                .map(Movie::getGenre)
                .filter(g -> g != null && !g.isBlank())
                .distinct().sorted().toList());
        model.addAttribute("q", q);
        model.addAttribute("selectedGenre", genre);
        return "movies/index";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movies/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Movie movie, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "movies/form";
        }
        repo.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Movie movie = repo.findById(id).orElseThrow();
        model.addAttribute("movie", movie);
        return "movies/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Movie movie,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "movies/form";
        }
        Movie existing = repo.findById(id).orElseThrow();
        existing.setTitle(movie.getTitle());
        existing.setDirector(movie.getDirector());
        existing.setYear(movie.getYear());
        existing.setRating(movie.getRating());
        existing.setGenre(movie.getGenre());
        repo.save(existing);
        return "redirect:/movies";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/movies";
    }
}

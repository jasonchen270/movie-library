package com.example.movielibrary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final MovieRepository repo;

    public DataSeeder(MovieRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Movie("The Godfather", "Francis Ford Coppola", 1972, 10, "Crime"));
        repo.save(new Movie("Inception", "Christopher Nolan", 2010, 9, "Sci-Fi"));
        repo.save(new Movie("Parasite", "Bong Joon-ho", 2019, 9, "Thriller"));
        repo.save(new Movie("The Dark Knight", "Christopher Nolan", 2008, 10, "Action"));
        repo.save(new Movie("Spirited Away", "Hayao Miyazaki", 2001, 9, "Animation"));
        repo.save(new Movie("Pulp Fiction", "Quentin Tarantino", 1994, 9, "Crime"));
    }
}

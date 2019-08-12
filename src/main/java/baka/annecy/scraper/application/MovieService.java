package baka.annecy.scraper.application;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baka.annecy.scraper.domain.movie.Movie;
import baka.annecy.scraper.domain.movie.MovieRepository;

@Service
@Transactional
public class MovieService {

  @Autowired CategoryService categoryService;
  @Autowired MovieRepository movieRepository;

  public void saveList(List<Movie> movieList) {

    categoryService.saveList(
        movieList.stream().map(Movie::getCategory).collect(Collectors.toList()));

    for (Movie movie : movieList) {
      Movie oldMovie = movieRepository.findForUpdateByTitle(movie.getTitle());
      if (oldMovie != null) {
        oldMovie.update(movie.getCategory(), movie.getYear());
      }
      movieRepository.save(movie);
    }
  }
}

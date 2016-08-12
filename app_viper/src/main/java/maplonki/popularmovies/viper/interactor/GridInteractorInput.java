package maplonki.popularmovies.viper.interactor;

/**
 * Created by hugo on 8/10/2016.
 */
public interface GridInteractorInput {
    void setInteractorOutput(GridInteractorOutput presenter);
    void loadMovies(String filter);
}

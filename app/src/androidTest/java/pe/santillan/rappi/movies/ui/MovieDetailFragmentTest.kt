package pe.santillan.rappi.movies.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.base.MainThread
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pe.santillan.rappi.data.repository.movie.FakeMovieRepositoryImpl
import pe.santillan.rappi.movies.R

@RunWith(AndroidJUnit4::class)
@MainThread
@MediumTest
class MovieDetailFragmentTest {

    private lateinit var movieRepository: FakeMovieRepositoryImpl

    @Before
    fun createRepository() {
        movieRepository = FakeMovieRepositoryImpl()
    }

    @Test
    fun selectMovie_AndShowOnMovieDetailFragment() {
        // GIVEN - a movie
        movieRepository.getPopularMovies()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.computation())
            .onErrorReturn { listOf() }
            .subscribe {
                // WHEN - Movie details launched display task
                val title = it[1].title
                val bundle = MovieDetailFragmentArgs(it[1]).toBundle()
                launchFragmentInContainer<MovieDetailFragment>(bundle, R.style.Theme_Movies)

                onView(withId(R.id.title)).check(matches(isDisplayed()))
                onView(withId(R.id.title)).check(matches(withText(title)))
            }
    }
}
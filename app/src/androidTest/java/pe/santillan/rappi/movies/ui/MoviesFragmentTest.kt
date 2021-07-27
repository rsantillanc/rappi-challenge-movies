package pe.santillan.rappi.movies.ui

import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Test
import org.junit.runner.RunWith
import pe.santillan.rappi.movies.R


@RunWith(AndroidJUnit4::class)
@MediumTest
@UiThread
class MoviesFragmentTest {

    @Test
    fun lists_DisplayedInMoviesFragment() {
        // WHEN - MoviesFragment launched to display both lists
        ActivityScenario.launch(MainActivity::class.java)

        // First, scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.topRatedList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(
            1,
            click()))
        //TODO: launch MovieDetailFragment with the selected item and check if some content has a value.
    }
}
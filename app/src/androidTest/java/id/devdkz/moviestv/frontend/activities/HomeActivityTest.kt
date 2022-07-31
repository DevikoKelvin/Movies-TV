package id.devdkz.moviestv.frontend.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.devdkz.moviestv.R
import id.devdkz.moviestv.backend.misc.IdlingRes
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest
{
    @get : Rule
    var actRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup()
    {
        IdlingRegistry.getInstance().register(IdlingRes.idling)
    }

    @After
    fun finish()
    {
        IdlingRegistry.getInstance().unregister(IdlingRes.idling)
    }

    // Movies List Testing
    @Test
    fun testA()
    {
        onView(withId(R.id.rv_view)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_view)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    // TV Shows List Testing
    @Test
    fun testB()
    {
        onView(withText("TV SHOWS")).perform(ViewActions.click())
        onView(withId(R.id.rv_view)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_view)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    // Detailed Movie Info Testing
    @Test
    fun testC()
    {
        onView(withText("MOVIES")).perform(ViewActions.click())
        onView(withId(R.id.rv_view)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.title_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.title_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.rating_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.reldate_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.reldate_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.syn_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.syn_txt)).check(matches(not(withText(""))))

        onView(withId(R.id.bookmark_btn)).perform(ViewActions.click())
    }

    // Detailed TV Show Info Testing
    @Test
    fun testD()
    {
        onView(withText("TV SHOWS")).perform(ViewActions.click())
        onView(withId(R.id.rv_view)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.title_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.title_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.rating_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.reldate_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.reldate_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.syn_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.syn_txt)).check(matches(not(withText(""))))

        onView(withId(R.id.bookmark_btn)).perform(ViewActions.click())
    }

    // Bookmarked Movies List Testing
    @Test
    fun testE()
    {
        onView(withId(R.id.bookmark_menu)).perform(ViewActions.click())
        onView(withId(R.id.rv_view)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_view)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    // Bookmarked TV Shows List Testing
    @Test
    fun testF()
    {
        onView(withId(R.id.bookmark_menu)).perform(ViewActions.click())
        onView(withText("TV SHOWS")).perform(ViewActions.click())
        onView(withId(R.id.rv_view)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_view)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    // Bookmarked Detail Info of Movie Testing
    @Test
    fun testG()
    {
        onView(withText("MOVIES")).perform(ViewActions.click())
        onView(withId(R.id.bookmark_menu)).perform(ViewActions.click())
        onView(withId(R.id.rv_view)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.title_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.title_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.rating_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.reldate_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.reldate_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.syn_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.syn_txt)).check(matches(not(withText(""))))

        onView(withId(R.id.bookmark_btn)).check(matches(isChecked()))
            .perform(ViewActions.click())
    }

    // Bookmarked Detail Info of TV Show Testing
    @Test
    fun testH()
    {
        onView(withId(R.id.bookmark_menu)).perform(ViewActions.click())
        onView(withText("TV SHOWS")).perform(ViewActions.click())
        onView(withId(R.id.rv_view)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.title_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.title_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.rating_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.reldate_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.reldate_txt)).check(matches(not(withText(""))))
        onView(withId(R.id.syn_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.syn_txt)).check(matches(not(withText(""))))

        onView(withId(R.id.bookmark_btn)).check(matches(isChecked()))
            .perform(ViewActions.click())
    }
}
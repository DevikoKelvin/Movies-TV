package id.devdkz.moviestv.backend.misc

import id.devdkz.moviestv.backend.repos.local.entities.BookmarkEnt
import id.devdkz.moviestv.backend.repos.local.entities.MovieEnt
import id.devdkz.moviestv.backend.repos.local.entities.TVEnt

object Dummy
{
    // LOCAL DUMMY
    // Movies
    fun addMovDummy() : ArrayList<MovieEnt>
    {
        val mov = ArrayList<MovieEnt>()
        mov.add(
            MovieEnt(
                337404,
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                "Cruella",
                8.6,
                "2021-05-26",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            )
        )
        return mov
    }

    fun getDetMovDummy() : MovieEnt
    {
        return MovieEnt(
            337404,
            "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            "Cruella",
            8.6,
            "2021-05-26",
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
        )
    }

    // TV Shows
    fun addTVDummy() : List<TVEnt>
    {
        val tv = ArrayList<TVEnt>()
        tv.add(
            TVEnt(
                84958,
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                "Loki",
                8.1,
                "2021-06-09",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
            )
        )
        return tv
    }

    fun getDetTVDummy() : TVEnt
    {
        return TVEnt(
            84958,
            "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
            "Loki",
            8.1,
            "2021-06-09",
            "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
        )
    }

    // Bookmark
    fun addBookDummy() : ArrayList<BookmarkEnt>
    {
        val book = ArrayList<BookmarkEnt>()
        book.add(
            BookmarkEnt(
                337404,
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                "Cruella",
                8.6,
                "2021-05-26",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "MOV"
            )
        )

        book.add(
            BookmarkEnt(
                84958,
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                "Loki",
                8.1,
                "2021-06-09",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "TV"
            )
        )

        return book
    }

    fun getMovDetBook() : BookmarkEnt
    {
        return BookmarkEnt(
            337404,
            "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            "Cruella",
            8.6,
            "2021-05-26",
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            "MOV"
        )
    }

    fun getTVDetBook() : BookmarkEnt
    {
        return BookmarkEnt(
            84958,
            "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
            "Loki",
            8.1,
            "2021-06-09",
            "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
            "TV"
        )
    }
}
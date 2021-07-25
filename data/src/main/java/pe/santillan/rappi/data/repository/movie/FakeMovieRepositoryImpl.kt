package pe.santillan.rappi.data.repository.movie

import io.reactivex.rxjava3.core.Observable
import pe.santillan.rappi.data.domain.Movie

class FakeMovieRepositoryImpl : MovieRepository {
    override fun getTopRatedMovies(): Observable<List<Movie>> {
        return Observable.just(mutableListOf(
            Movie(
                id = 278,
                posterPath = "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg",
                overview = "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
                title = "The Shawshank Redemption",
                popularity = 6.741296,
                voteAverage = 8.32F,
                voteCount = 5238,
                releaseDate = "1994-09-10"
            ),
            Movie(
                id = 244786,
                posterPath = "/lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg",
                overview = "Under the direction of a ruthless instructor, a talented young drummer begins to pursue perfection at any cost, even his humanity.",
                title = "Whiplash",
                popularity = 10.776056,
                voteAverage = 8.29F,
                voteCount = 2059,
                releaseDate = "2014-10-10"
            ),
            Movie(
                id = 238,
                posterPath = "/d4KNaTrltq6bpkFS01pYtyXa09m.jpg",
                overview = "The story spans the years from 1945 to 1955 and chronicles the fictional Italian-American Corleone crime family. When organized crime family patriarch Vito Corleone barely survives an attempt on his life, his youngest son, Michael, steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                title = "The Godfather",
                popularity = 4.554654,
                voteAverage = 8.26F,
                voteCount = 3570,
                releaseDate = "1972-03-15"
            ),
            Movie(
                id = 129,
                posterPath = "/ynXoOxmDHNQ4UAy0oU6avW71HVW.jpg",
                overview = "Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?",
                title = "Whiplash",
                popularity = 6.886678,
                voteAverage = 8.15F,
                voteCount = 2000,
                releaseDate = "2001-07-20"
            ),
        ))
    }

    override fun getPopularMovies(): Observable<List<Movie>> {
        return Observable.just(mutableListOf(
            Movie(
                id = 244786,
                posterPath = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
                overview = "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                title = "Suicide Squad",
                popularity = 48.261451,
                voteAverage = 5.91F,
                voteCount = 1466,
                releaseDate = "2016-08-03"
            ),
            Movie(
                id = 324668,
                posterPath = "/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg",
                overview = "The most dangerous former operative of the CIA is drawn out of hiding to uncover hidden truths about his past.",
                title = "Jason Bourne",
                popularity = 10.776056,
                voteAverage = 5.25F,
                voteCount = 649,
                releaseDate = "2016-07-27"
            ),
            Movie(
                id = 244786,
                posterPath = "/hU0E130tsGdsYa4K9lc3Xrn5Wyt.jpg",
                overview = "One year after outwitting the FBI and winning the public’s adulation with their mind-bending spectacles, the Four Horsemen resurface only to find themselves face to face with a new enemy who enlists them to pull off their most dangerous heist yet.",
                title = "Now You See Me 2",
                popularity = 29.737342,
                voteAverage = 6.64F,
                voteCount = 684,
                releaseDate = "2016-06-02"
            ),
            Movie(
                id = 244786,
                posterPath = "/h28t2JNNGrZx0fIuAw8aHQFhIxR.jpg",
                overview = "A recently cheated on married woman falls for a younger man who has moved in next door, but their torrid affair soon takes a dangerous turn.",
                title = "The Boy Next Door",
                popularity = 22.279864,
                voteAverage = 4.13F,
                voteCount = 628,
                releaseDate = "2015-01-23"
            ),
        ))
    }
}
package pe.santillan.rappi.movies.vm

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import pe.santillan.rappi.data.domain.Movie
import pe.santillan.rappi.data.domain.Session
import pe.santillan.rappi.data.domain.Token
import pe.santillan.rappi.data.repository.auth.AuthRepository
import pe.santillan.rappi.data.repository.movie.MovieRepository
import pe.santillan.rappi.movies.util.buildAuthUri
import java.util.function.Consumer

class MoviesViewModel(
    private val movieRepository: MovieRepository,
    private val authRepository: AuthRepository,
    private val uiThread: Scheduler = AndroidSchedulers.mainThread(),
    private val workThread: Scheduler = Schedulers.io(),
) : ViewModel() {

    val authUri: MutableLiveData<Uri> = MutableLiveData(null)
    val error: MutableLiveData<String> = MutableLiveData("")
    val readyToFetch: MutableLiveData<Boolean> = MutableLiveData(false)

    private var accessToken: Token? = null
    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun verifyAccessStatus() {
        if (isSessionSuccess()) {
            readyToFetch.postValue(true)
            return
        }

        if (accessToken?.isSuccess == true) {
            //Call requesSession() method due to we've had a token.
            requestSession()
        } else {
            requestToken()
        }
    }

    private fun requestToken() {
        compositeDisposable.add(authRepository.createToken()
            .observeOn(uiThread)
            .subscribeOn(workThread)
            .doOnSuccess(this::handlerToken)
            .subscribe())
    }

    private fun requestSession() {
        val session = authRepository.session()
        compositeDisposable.add(if (session.isSuccess) {
            Single.just(session)
                .observeOn(uiThread)
                .subscribeOn(workThread)
                .doOnSuccess(this::handlerSession)
                .subscribe()
        } else {
            accessToken?.let {
                authRepository.createSession(it.token)
                    .observeOn(uiThread)
                    .subscribeOn(workThread)
                    .onErrorReturn { Session(false, "") }
                    .doOnError(this::handlerError)
                    .doOnSuccess(this::handlerSession)
                    .subscribe()
            }
        })
    }

    private fun handlerSession(session: Session?) {
        session?.let {
            authRepository.saveSession(it)
            readyToFetch.postValue(true)
        }
    }

    fun getTopRatedMovies(subscriber: Consumer<List<Movie>>) {
        if (isSessionSuccess()) {
            compositeDisposable.add(movieRepository.getTopRatedMovies()
                .observeOn(uiThread)
                .subscribeOn(workThread)
                .onErrorReturn { listOf() }
                .doOnError(this::handlerError)
                .subscribe { subscriber.accept(it) })
        }
    }

    fun getPopularMovies(subscriber: Consumer<List<Movie>>) {
        if (isSessionSuccess()) {
            compositeDisposable.add(movieRepository.getPopularMovies()
                .observeOn(uiThread)
                .subscribeOn(workThread)
                .onErrorReturn { listOf() }
                .doOnError(this::handlerError)
                .subscribe { subscriber.accept(it) })
        }
    }

    private fun handlerToken(it: Token?) {
        it?.let {
            authUri.postValue(buildAuthUri(it))
            this.accessToken = it
            requestSession()
        }
    }

    private fun handlerError(throwable: Throwable?) {
        throwable?.let { error.postValue(it.message) }
    }

    private fun isSessionSuccess() = authRepository.session().isSuccess

    override fun onCleared() {
        super.onCleared()
        authUri.postValue(null)
        error.postValue(null)
        readyToFetch.postValue(null)
        compositeDisposable.clear()
    }
}
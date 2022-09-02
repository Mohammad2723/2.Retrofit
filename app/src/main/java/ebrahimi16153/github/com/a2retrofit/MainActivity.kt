package ebrahimi16153.github.com.a2retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ebrahimi16153.github.com.a2retrofit.adapter.MovieListAdapter
import ebrahimi16153.github.com.a2retrofit.databinding.ActivityMainBinding
import ebrahimi16153.github.com.a2retrofit.model.ResponseMoviesList
import ebrahimi16153.github.com.a2retrofit.server.ApiClient
import ebrahimi16153.github.com.a2retrofit.server.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityMainBinding

    //adapter
    private val moviesListAdapter by lazy { MovieListAdapter() }

    //ApiServices
    private val api: ApiServices by lazy { ApiClient().getClient().create(ApiServices::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {

            progressBar.visibility = View.VISIBLE
            //call movie api
            val callMovieApi = api.moviesList(1)
            callMovieApi.enqueue(object : Callback<ResponseMoviesList> {
                override fun onResponse(
                    call: Call<ResponseMoviesList>,
                    response: Response<ResponseMoviesList>
                ) {
                        progressBar.visibility = View.GONE
                    if (response.isSuccessful) {

                        response.body()?.let { itBody->
                            itBody.data?.let { itData->
                                if (itData.isNotEmpty()) {
                                    moviesListAdapter.diff.submitList(itData)

                                    //recycler
                                    movieList.apply {
                                        adapter = moviesListAdapter
                                        layoutManager = LinearLayoutManager(this@MainActivity)
                                    }

                                }

                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMoviesList>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Snackbar.make(root,t.message.toString(),Snackbar.LENGTH_LONG).show()
                }

            })


        }


    }
}
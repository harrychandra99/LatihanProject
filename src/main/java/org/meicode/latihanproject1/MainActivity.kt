package org.meicode.latihanproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.meicode.latihanproject1.DataModel.RetrofitService
import org.meicode.latihanproject1.RecyclerViewMain.MainAdapter
import org.meicode.latihanproject1.DataModel.MainRepository
import org.meicode.latihanproject1.VideoModel.MainViewModel
import org.meicode.latihanproject1.VideoModel.MyViewModelFactory
import org.meicode.latihanproject1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var mainBinding : ActivityMainBinding
    lateinit var viewModelMain : MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        viewModelMain = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService!!))).get(MainViewModel::class.java)
        mainBinding.rvMain.adapter = adapter
        viewModelMain.movieList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })
        viewModelMain.errorMessage.observe(this, Observer {  })
        viewModelMain.getAllMovies()
    }

    override fun onCreateOptionsMenu(menu : Menu?) : Boolean {
        menuInflater.inflate(R.menu.menu_nav, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        when (item.itemId){
            R.id.menuLogout -> {
                val intentGoToLogout = Intent(this, LoginActivity::class.java)
                startActivity(intentGoToLogout)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private var backPressedTime:Long = 0
    lateinit var backToast:Toast
    override fun onBackPressed() {
        backToast = Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            return
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}
package cnovaez.dev.notebookmvvm.ui.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

    }

    fun getNavigatorController(): NavController? {
        return findNavController(this, R.id.fragmentContainerView)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) != null && getNavigatorController()!!.currentDestination!!.id != R.id.mainNotesListFragment) {
          super.onBackPressed()
        }
    }

}
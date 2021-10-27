package com.example.pruebaceibasoftware.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebaceibasoftware.utils.UtilsProyect
import com.example.pruebaceibasoftware.data.adapters.MainAdapter
import com.example.pruebaceibasoftware.data.singleton.InstanceSingleton
import com.example.pruebaceibasoftware.database.schemas.Users
import com.example.pruebaceibasoftware.databinding.ActivityMainBinding
import com.example.pruebaceibasoftware.ui.main.viewmodel.MainViewModel
import com.example.pruebaceibasoftware.ui.post.view.PostActivity

class MainActivity : AppCompatActivity(), MainAdapter.OnUser {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter
    private var loading: AlertDialog? = null
    private var allUsers = ArrayList<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //creamos la instancia de nuestro singleton
        InstanceSingleton.getInstanceSingleton(this)

        loading = UtilsProyect.showLoading(this, "Cargando Información")
        viewModel.getUsers()

        mainAdapter = MainAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerUsers.layoutManager = layoutManager
        binding.recyclerUsers.adapter = mainAdapter
        binding.recyclerUsers.itemAnimator = DefaultItemAnimator()
        binding.recyclerUsers.hasFixedSize()

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isNotEmpty()) {
                    val filter = allUsers.filter { it.name.contains(s.toString().trim(), true) }
                    mainAdapter.setData(filter as ArrayList<Users>)
                } else {
                    mainAdapter.setData(allUsers)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        observe()
    }

    private fun observe() {
        viewModel.users.observe(this, {
            allUsers.addAll(it)
            loading?.dismiss()
            mainAdapter.setData(it)
        })

        viewModel.errorUsers.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            viewModel.getUsers()
        })
    }

    override fun showPost(user: Users) {
        val intent = Intent(this, PostActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
    }
}
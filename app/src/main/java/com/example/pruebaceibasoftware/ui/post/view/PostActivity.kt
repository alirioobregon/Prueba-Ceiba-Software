package com.example.pruebaceibasoftware.ui.post.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebaceibasoftware.utils.UtilsProyect
import com.example.pruebaceibasoftware.data.adapters.PostAdapter
import com.example.pruebaceibasoftware.database.schemas.Users
import com.example.pruebaceibasoftware.databinding.ActivityPostBinding
import com.example.pruebaceibasoftware.ui.post.viewmodel.PostViewModel

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private val viewModel: PostViewModel by viewModels()
    private lateinit var postAdapter: PostAdapter
    private var loading: AlertDialog? = null
    private var user: Users? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra("user")

        binding.nameUser.text = user?.name.toString()
        binding.userPhone.text = user?.phone.toString()
        binding.userEmail.text = user?.email.toString()

        loading = UtilsProyect.showLoading(this, "Cargando Información")

        user?.id?.let {
            viewModel.getPostById(it)
        }

        postAdapter = PostAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerPost.layoutManager = layoutManager
        binding.recyclerPost.adapter = postAdapter
        binding.recyclerPost.itemAnimator = DefaultItemAnimator()
        binding.recyclerPost.hasFixedSize()

        binding.materialToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        observe()
    }

    private fun observe() {
        viewModel.posts.observe(this, {
            loading?.dismiss()
            postAdapter.setData(it)
        })

        viewModel.errorPosts.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            viewModel.getPostById(user?.id!!)
        })
    }
}
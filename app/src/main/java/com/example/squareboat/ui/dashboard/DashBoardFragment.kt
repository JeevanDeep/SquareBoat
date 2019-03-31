package com.example.squareboat.ui.dashboard


import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.squareboat.App
import com.example.squareboat.R
import com.example.squareboat.di.ViewModelFactory
import com.example.squareboat.model.icon.IconsItem
import com.example.squareboat.ui.IconsAdapter
import com.example.squareboat.ui.search.IconSearchFragment
import com.example.squareboat.utils.setGone
import kotlinx.android.synthetic.main.fragment_dash_board.*
import javax.inject.Inject


class DashBoardFragment : Fragment(), IconsAdapter.IconListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: DashBoardViewModel
    private val iconsList = mutableListOf<IconsItem>()
    private lateinit var iconAdapter: IconsAdapter
    private var canRequestMore = false
    private lateinit var glm: GridLayoutManager
    private var url = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.component.inject(this)

        setupRecyclerView()

        setupViewModel()

        addObservers()
    }

    private fun setupRecyclerView() {
        iconAdapter = IconsAdapter(iconsList, this)
        glm = GridLayoutManager(requireContext(), 2)
        iconRecyclerView.apply {
            adapter = iconAdapter
            iconRecyclerView.layoutManager = glm
        }
        glm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (iconAdapter.isLastItem(position)) 2 else 1

            }
        }
    }

    private fun addObservers() {

        viewModel.getIconObserver().observe(this, Observer { data ->
            data?.response?.let { list ->

                progress.setGone()
                iconAdapter.allowProgress(canRequestMore)
                iconsList.addAll(list)
                iconAdapter.notifyDataSetChanged()

            }
        })

        viewModel.canRequestObserver().observe(this, Observer {
            canRequestMore = it
        })

        viewModel.toastOberver().observe(this, Observer {
            progress.setGone()
            showToast(it)
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DashBoardViewModel::class.java)
        viewModel.getIconsForFirstTime()
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            IconSearchFragment.PERMISSION_REQUEST_CODE
        )
    }

    private fun hasPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == IconSearchFragment.PERMISSION_REQUEST_CODE) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

            } else {
                viewModel.downloadImage(url)
            }
        }
    }


    override fun onDownloadClicked(url: String) {
        this.url = url
        if (hasPermissions())
            viewModel.downloadImage(url)
        else requestStoragePermission()
    }

    override fun onLoadMoreClicked() {
        viewModel.getMoreIcons()
    }
}

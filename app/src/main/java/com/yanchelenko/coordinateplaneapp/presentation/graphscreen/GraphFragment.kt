package com.yanchelenko.coordinateplaneapp.presentation.graphscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yanchelenko.coordinateplaneapp.databinding.FragmentGraphBinding
import com.yanchelenko.coordinateplaneapp.presentation.utils.PermissionHelper
import com.yanchelenko.coordinateplaneapp.presentation.utils.PermissionHelper.READ_PERMISSION
import com.yanchelenko.coordinateplaneapp.presentation.utils.PermissionHelper.REQUEST_CODE
import com.yanchelenko.coordinateplaneapp.presentation.utils.PermissionHelper.WRITE_PERMISSION
import com.yanchelenko.coordinateplaneapp.presentation.utils.PermissionHelper.saveFileToScopeStorage
import com.yanchelenko.coordinateplaneapp.presentation.UIState
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.adapter.PointsTableAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GraphFragment: Fragment() {

    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GraphViewModel by viewModels()
    private var numberOfPoints: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { numberOfPoints = it.getInt(NUMBER_OF_POINTS) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraphBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeGraphState()
        initViews()

        binding.apply {
            errorView.repeatBtn.setOnClickListener { viewModel.getPointsRequest() }
            saveFileBtn.setOnClickListener { initSaveButton() }
        }
    }

    private fun initViews() {
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
        binding.pointsTableRV.layoutManager = gridLayoutManager
    }

    private fun initSaveButton() {
        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                saveFileToScopeStorage(
                    view = binding.customViewGraph,
                    onStart = { binding.progressBar.isVisible = true },
                    onComplete = { binding.progressBar.isVisible = false }
                )
            } else {
                PermissionHelper.requestPermissions(
                    requireActivity(),
                    listOf(WRITE_PERMISSION, READ_PERMISSION),
                    REQUEST_CODE,
                    view = binding.customViewGraph
                )
            }
        }

        binding.apply {
            saveFileBtn.setOnClickListener {
                requestPermissionLauncher.launch(WRITE_PERMISSION)
            }
        }
    }

    private fun observeGraphState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.graphState.collect { graphState ->
                    binding.apply {
                        when (graphState) {
                            is UIState.Success -> {
                                pointsTableRV.adapter = PointsTableAdapter(graphState.graphUiModel.points)
                                customViewGraph.setData(graphState.graphUiModel)

                                TableViewLL.isVisible = true
                                progressBar.isVisible = false
                                errorView.errorViewLL.isVisible = false
                            }
                            is UIState.Loading -> {
                                TableViewLL.isVisible = false
                                progressBar.isVisible = true
                                errorView.errorViewLL.isVisible = false
                            }
                            is UIState.Error -> {
                                TableViewLL.isVisible = false
                                progressBar.isVisible = false
                                errorView.errorViewLL.isVisible = true

                                errorView.errorTV.text = graphState.messageError
                            }
                            is UIState.None -> {
                                TableViewLL.isVisible = false
                                progressBar.isVisible = false
                                errorView.errorViewLL.isVisible = false
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val GRAPH_FRAGMENT_TAG = "GraphFragment"
        const val NUMBER_OF_POINTS = "NUMBER_OF_POINTS"

        @JvmStatic
        fun newInstance(numberOfPoints: Int) =
            GraphFragment().apply {
                arguments = Bundle().apply {
                    putInt(NUMBER_OF_POINTS, numberOfPoints)
                }
            }
    }

}

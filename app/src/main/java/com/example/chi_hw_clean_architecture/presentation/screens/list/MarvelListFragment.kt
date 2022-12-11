package com.example.chi_hw_clean_architecture.presentation.screens.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chi_hw_clean_architecture.R
import com.example.chi_hw_clean_architecture.data.model.Marvel
import com.example.chi_hw_clean_architecture.databinding.FragmentMarvelListBinding
import com.example.chi_hw_clean_architecture.di.Injection
import com.example.chi_hw_clean_architecture.presentation.model.MarvelPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class MarvelListFragment : Fragment() {
    private lateinit var binding: FragmentMarvelListBinding
    private lateinit var viewModel: MarvelListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        )[MarvelListViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarvelListBinding.inflate(inflater, container, false)

        with(binding.root) {
            layoutManager = LinearLayoutManager(context)
//            adapter = MarvelRecyclerViewAdapter(listOf())
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.marvels.collect {
                    if (it is MarvelUIState.Empty) binding.root.adapter = MarvelRecyclerViewAdapter(
                        emptyList()
                    ) else {
                        (it as MarvelUIState.Success).list.map { m -> m.toUiModel() }.toList().run {
                            binding.root.adapter = MarvelRecyclerViewAdapter(this)
                        }
                    }
                }
            }

        }
        return binding.root
    }


}
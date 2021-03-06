package com.demo.android.henrique.rickandmotyapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.android.henrique.rickandmotyapp.R
import com.demo.android.henrique.rickandmotyapp.databinding.FragmentListCharactersBinding
import com.demo.android.henrique.rickandmotyapp.model.Character
import com.demo.android.henrique.rickandmotyapp.view.DetailCharacterFragment.Companion.CHARACTER_DETAIL_ID
import com.demo.android.henrique.rickandmotyapp.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCharactersFragment : Fragment() {
    private var _binding: FragmentListCharactersBinding? = null
    private val binding: FragmentListCharactersBinding? get() = _binding

    private val adapter: CharacterAdapter by lazy { CharacterAdapter() }

    // private val sharedViewModel: SharedViewModel by activityViewModels { SharedViewModelFactory(activity?.application as Application) }
    private val sharedViewModel: SharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.findAllCharacters()

    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        sharedViewModel.findAllCharacters()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListCharactersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedViewModel.listCharacter.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                showCharacters(response.body()!!.results)
            } else {
                showErrorMessage(response.code().toString())
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(this)
                    ?.commit()
            }
        })



        binding?.rvMain?.adapter = adapter
        binding?.rvMain?.layoutManager = LinearLayoutManager(requireContext())

        adapter.setOnItemClickListener { character ->
            val bundle: Bundle = Bundle().apply {
                putSerializable(CHARACTER_DETAIL_ID, character)
            }

            findNavController().navigate(R.id.action_nav_list_character_to_nav_detail_character, bundle)
        }


    }

    private fun showCharacters(response: List<Character>) {
        adapter.submitList(response)
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
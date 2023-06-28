package com.thiagocontelli.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.thiagocontelli.notes.R
import com.thiagocontelli.notes.adapters.NotesAdapter
import com.thiagocontelli.notes.databinding.FragmentHomeBinding
import com.thiagocontelli.notes.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesAdapter: NotesAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesAdapter = NotesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareNotesRecyclerView()

        viewModel.getAllNotes() { notes ->
            notesAdapter.setNotes(notes)
        }

        onClickNoteCard()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
    }

    private fun onClickNoteCard() {
        notesAdapter.onClick = {
            val bundle = bundleOf(
                "id" to it.id,
                "title" to it.title,
                "content" to it.content,
                "createdAt" to it.createdAt.toString()
            )
            findNavController().navigate(R.id.action_homeFragment_to_noteFragment, bundle)
        }
    }

    private fun prepareNotesRecyclerView() {
        binding.notesRv.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = notesAdapter
        }
    }
}
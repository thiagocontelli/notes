package com.thiagocontelli.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thiagocontelli.notes.R
import com.thiagocontelli.notes.databinding.FragmentAddNoteBinding
import com.thiagocontelli.notes.viewmodels.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = arguments?.getString("type") ?: "add"
        val title = arguments?.getString("title") ?: ""
        val content = arguments?.getString("content") ?: ""
        val id = arguments?.getInt("id") ?: 0
        val createdAt = arguments?.getString("createdAt") ?: ""

        binding.topAppBar.title = if (type == "add") "New Note" else "Edit Note"
        binding.addButton.text = if (type == "add") "Add" else "Edit"
        binding.titleTextInput.setText(title)
        binding.contentTextInput.setText(content)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.addButton.setOnClickListener {
            if (type == "add") {
                viewModel.addNote(
                    binding.titleTextInput.text.toString(), binding.contentTextInput.text.toString()
                ) { findNavController().popBackStack() }
            } else {
                viewModel.editNote(
                    id,
                    binding.titleTextInput.text.toString(),
                    binding.contentTextInput.text.toString(),
                    createdAt
                ) { findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment) }
            }
        }
    }
}
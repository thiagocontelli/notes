package com.thiagocontelli.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.thiagocontelli.notes.R
import com.thiagocontelli.notes.databinding.FragmentNoteBinding
import com.thiagocontelli.notes.models.Note
import com.thiagocontelli.notes.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("id") ?: 0
        val title = arguments?.getString("title") ?: ""
        val createdAt = arguments?.getString("createdAt") ?: ""
        val content = arguments?.getString("content") ?: ""

        binding.topAppBar.title = title
        binding.topAppBar.subtitle = createdAt
        binding.contentTextView.text = content

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_note -> {
                    context?.let { ctx ->
                        MaterialAlertDialogBuilder(ctx).setTitle("Delete Note").setMessage("Are you sure you want to delete this note? This action cannot be undone.")
                            .setNegativeButton("Cancel") { dialog, _ ->
                                dialog.cancel()
                            }.setPositiveButton("Delete") { _, _ ->
                                viewModel.deleteNote(
                                    Note(id, title, content, LocalDateTime.parse(createdAt))
                                ) {
                                    findNavController().popBackStack()
                                }
                            }.show()
                    }
                    true
                }

                R.id.edit_note -> {
                    val bundle = bundleOf(
                        "type" to "edit",
                        "title" to title,
                        "content" to content,
                        "id" to id,
                        "createdAt" to createdAt
                    )

                    findNavController().navigate(R.id.action_noteFragment_to_addNoteFragment, bundle)

                    true
                }

                else -> false
            }
        }
    }
}
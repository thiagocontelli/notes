package com.thiagocontelli.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.thiagocontelli.notes.R
import com.thiagocontelli.notes.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.title = arguments?.getString("title")
        binding.topAppBar.subtitle = arguments?.getString("createdAt")
        binding.contentTextView.text = arguments?.getString("content")

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete_note -> {
                    context?.let { ctx ->
                        MaterialAlertDialogBuilder(ctx).setTitle("Delete Note")
                            .setMessage("Are you sure you want to delete this note? This action cannot be undone.")
                            .setNegativeButton("Cancel") { dialog, _ ->
                                dialog.cancel()
                            }.setPositiveButton("Delete") { _, _ ->
                                findNavController().popBackStack()
                            }.show()
                    }
                    true
                }

                else -> false
            }
        }
    }
}
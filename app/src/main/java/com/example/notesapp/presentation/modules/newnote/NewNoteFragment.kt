package com.example.notesapp.presentation.modules.newnote

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNewNoteBinding
import com.example.notesapp.domain.model.Note
import com.example.notesapp.presentation.modules.HomeActivity

class NewNoteFragment : Fragment() {

    //Binding
    private var _binding: FragmentNewNoteBinding? = null
    val binding get() = _binding!!

    //ViewModel
    private val viewModel by lazy { (activity as HomeActivity).viewModel }

    private lateinit var cardNewNoteContainer   : CardView
    private lateinit var etTitleNote            : AppCompatEditText
    private lateinit var etDescriptionNote      : AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)

        initBinding()
        setupViews()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViews() {
    }

    private fun setupNewNote() {
        val title       = etTitleNote.text?.trim().toString()
        val description = etDescriptionNote.text?.trim().toString()

        if (title.isNotEmpty() && description.isNotEmpty()){
            val newNote = Note(0, title, description)
            viewModel.addNote(newNote)

            findNavController()
                .navigate(R.id.action_newNoteFragment_to_homeFragment)
        }else{
            Toast.makeText(requireContext(), "Completa todos los datos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.new_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.option_save -> {
                setupNewNote()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initBinding() {
        cardNewNoteContainer    = binding.cardContainerNewNote
        etTitleNote             = binding.etTitleNote
        etDescriptionNote       = binding.etDescriptionNote
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
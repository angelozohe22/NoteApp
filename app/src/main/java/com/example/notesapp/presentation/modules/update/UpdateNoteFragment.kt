package com.example.notesapp.presentation.modules.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentUpdateNoteBinding
import com.example.notesapp.domain.model.Note
import com.example.notesapp.presentation.modules.HomeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpdateNoteFragment : Fragment() {

    //binding
    private var _binding: FragmentUpdateNoteBinding? = null
    val binding get() = _binding!!

    //ViewModel
    private val viewModel by lazy { (activity as HomeActivity).viewModel }

    //args
    private val args: UpdateNoteFragmentArgs by navArgs()

    private lateinit var note: Note

    private lateinit var cardUpdateNoteContainer  : CardView
    private lateinit var etTitleNote              : AppCompatEditText
    private lateinit var etDescriptionNote        : AppCompatEditText
    private lateinit var fabUpdate                : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        note = args.note
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)

        initBinding()
        setupViews()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViews() {
        setupData()
        setupFabEdit()
    }

    private fun setupData() {
        etTitleNote.setText(note.title)
        etDescriptionNote.setText(note.description)
    }

    private fun setupFabEdit() {
        fabUpdate.setOnClickListener {
            val title       = etTitleNote.text?.trim().toString()
            val description = etDescriptionNote.text?.trim().toString()

            if (title.isNotEmpty() && description.isNotEmpty()){
                val newNote = Note(note.id, title, description)
                viewModel.updateNote(newNote)
                findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }else{
                Toast.makeText(requireContext(), "Completa todos los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.update_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.option_delete -> {
                onDeleteNote(note)
                findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onDeleteNote(note: Note){
        viewModel.deleteNote(note)
    }

    private fun initBinding() {
        cardUpdateNoteContainer = binding.cardContainerUpdateNote
        etTitleNote             = binding.etTitleNoteUpdate
        etDescriptionNote       = binding.etDescriptionNoteUpdate
        fabUpdate               = binding.fabUpdateNote
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
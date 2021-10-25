package com.example.notesapp.presentation.modules.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.data.entity.NoteEntity
import com.example.notesapp.data.source.INotesDataSource
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.domain.model.Note
import com.example.notesapp.presentation.modules.HomeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment(), NotesAdapter.OnNoteItemClickListener,
    SearchView.OnQueryTextListener {

    //Binding
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    //Adapter
    private val mNoteAdapter by lazy { NotesAdapter(this) }

    //ViewModel
    private val viewModel by lazy { (activity as HomeActivity).viewModel }

    private lateinit var fabAddNote         : FloatingActionButton
    private lateinit var cardEmptyContainer : CardView
    private lateinit var tvEmptyNotes       : AppCompatTextView
    private lateinit var rvNotes            : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initBinding()
        setupViews()

        return binding.root
    }

    private fun setupViews() {
        setupFabAdd()
        setupRvNoteList()
        setupObservers()
    }

    private fun setupObservers() {
        setupNotesListObserver()
    }

    private fun setupNotesListObserver() {
        viewModel.getAllNotes().observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                if (it.isEmpty()){
                    rvNotes.visibility = View.GONE
                    cardEmptyContainer.visibility = View.VISIBLE
                }else{
                    rvNotes.visibility = View.VISIBLE
                    cardEmptyContainer.visibility = View.GONE
                }
                mNoteAdapter.notesList = it
            }
        })
    }

    private fun setupFabAdd() {
        fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
    }

    private fun setupRvNoteList(){
        rvNotes.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = mNoteAdapter
        }
    }

    override fun onNoteClicked(note: Note) {
        findNavController().navigate(HomeFragmentDirections
            .actionHomeFragmentToUpdateNoteFragment(note))
    }

    private fun initBinding() {
        fabAddNote          = binding.fabAddNote
        cardEmptyContainer  = binding.cardEmptyNotes
        tvEmptyNotes        = binding.tvEmptyNotes
        rvNotes             = binding.rvNotes
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)

        val optionFilter = menu.findItem(R.id.option_search).actionView as SearchView
        optionFilter.isSubmitButtonEnabled = true
        optionFilter.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) searchNote(query)
        else{
            rvNotes.visibility = View.GONE
            cardEmptyContainer.visibility = View.VISIBLE
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) searchNote(newText)
        else{
            rvNotes.visibility = View.GONE
            cardEmptyContainer.visibility = View.VISIBLE
        }
        return true
    }

    private fun searchNote(query: String){
        viewModel.getNoteListByQuery("%$query%").observe(viewLifecycleOwner, Observer { result->
            result?.let {
                if (it.isEmpty()){
                    rvNotes.visibility = View.GONE
                    cardEmptyContainer.visibility = View.VISIBLE
                }else{
                    rvNotes.visibility = View.VISIBLE
                    cardEmptyContainer.visibility = View.GONE
                }
                mNoteAdapter.notesList = it
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
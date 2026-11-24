
package com.example.rey3tie_mobiel.note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rey3tie_mobiel.Data.AppDatabase
import com.example.rey3tie_mobiel.Data.entity.NoteEntity
import com.example.rey3tie_mobiel.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar
        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Note"
            }

        // Inisialisasi Database
        db = AppDatabase.getInstance(requireContext())

        // Adapter
        adapter = NoteAdapter(notes, this)

        binding.rvNotes.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvNotes.adapter = adapter

        val divider = DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        )

        binding.rvNotes.addItemDecoration(divider)

        // Menampilkan data Note
        fetchNotes()

        // Tombol tambah Note
        binding.fabAddNote.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    NoteFormActivity::class.java
                )
            )
        }
    }

    // Mengambil data Note dari Database
    private fun fetchNotes() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                if (::db.isInitialized) {
                    val data = db.noteDao().getAll()

                    notes.clear()
                    notes.addAll(data)

                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Memperbarui data ketika kembali ke Fragment
    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    // Fungsi Delete Note
    fun deleteNote(note: NoteEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                if (::db.isInitialized) {

                    // Menghapus Note dari Database
                    db.noteDao().delete(note)

                    // Mengambil kembali data terbaru
                    fetchNotes()
                }
            } catch (e: Exception) {
                e.printStackTrace()

                Toast.makeText(
                    requireContext(),
                    "Gagal menghapus catatan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

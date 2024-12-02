package com.example.myspaceapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myspaceapp.adapters.SectionAdapter
import com.example.myspaceapp.databinding.HomeFragmentBinding
import com.example.myspaceapp.enums.Sections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding
    private lateinit var sectionAdapter: SectionAdapter
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestoreData: FirebaseFirestore
    private val sections = Sections.entries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.welcomeTitle.text = "Welcome to your space, ${firebaseAuth.currentUser?.email} !"

        sectionAdapter = SectionAdapter()
        sectionAdapter.sectionsList = sections
        sectionAdapter.onClick = { section ->
            when (section) {
                Sections.DOCUMENTS -> navigate(R.id.action_homeFragment_to_documentsFragment)
                Sections.PHOTOS -> navigate(R.id.action_homeFragment_to_photosFragment)
                Sections.NOTES -> navigate(R.id.action_homeFragment_to_notesFragment)
                Sections.OTHERS -> navigate(R.id.action_homeFragment_to_othersFragment)
            }
        }

        binding.homeScreenRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sectionAdapter
        }

    }

    private fun navigate(navigationId: Int) {
        findNavController().navigate(navigationId)
    }
}
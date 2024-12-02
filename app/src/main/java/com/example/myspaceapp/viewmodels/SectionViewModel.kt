package com.example.myspaceapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myspaceapp.enums.Sections
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SectionViewModel {
    private val sectionRef: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("data").ref
    private val list = MutableLiveData<List<Sections>>()
    val liveData: LiveData<List<Sections>> = list

    init {
        //repos.loadInventarData(list)
        sectionRef.get()
    }
}
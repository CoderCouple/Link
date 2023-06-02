package com.matrix.link.firebase.util

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference

class FirebaseHelper<T>(private val databaseReference: DatabaseReference) {

    interface DataChangeListener<T> {
        fun onDataAdded(data: T)
        fun onDataChanged(data: T)
        fun onDataRemoved(data: T)
        fun onCancelled(error: DatabaseError)
    }

    fun addObject(path: String, obj: T, completionListener: DatabaseReference.CompletionListener? = null) {
        val key = databaseReference.child(path).push().key
        key?.let {
            databaseReference.child(path).child(it).setValue(obj, completionListener)
        }
    }

    fun updateObject(path: String, obj: T, completionListener: DatabaseReference.CompletionListener? = null) {
        val key = databaseReference.push().key
        key?.let {
            databaseReference.child(path).child(it).setValue(obj, completionListener)
        }
    }

    fun deleteObject(path: String, obj: T, completionListener: DatabaseReference.CompletionListener? = null) {
        databaseReference.child(path).child(obj.toString()).removeValue(completionListener)
    }

    fun  fetchDataChangeListener(dataChangeListener: DataChangeListener<T>) {
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.value as T
                data?.let { dataChangeListener.onDataAdded(it) }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.value as T
                data?.let { dataChangeListener.onDataChanged(it) }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val data = snapshot.value as T
                data?.let { dataChangeListener.onDataRemoved(it) }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {
                dataChangeListener.onCancelled(error)
            }
        })
    }

}

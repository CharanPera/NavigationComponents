package com.example.notes_app

import android.graphics.Color
import android.graphics.Color.parseColor
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.leanback.widget.DiffCallback
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cards.view.*

class NotesAdapter(val notesList: MutableList<Notes>,val interaction: Interaction ): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {


    class ViewHolder(itemView: View,val interaction: Interaction): RecyclerView.ViewHolder(itemView)
    {
        fun bindItems(value: Notes){
            itemView.apply {

                txtTitle.text = value.title
                txtDescription.text = value.description
                txtTag.text = value.tag

                var color = value.color
                when (color) {
                    0 -> cardView_linearLayout.setBackgroundColor( Color.parseColor("#FFFFFFFF"))
                    1 -> cardView_linearLayout.setBackgroundColor( Color.parseColor("#FF03DAC5"))
                    2 -> cardView_linearLayout.setBackgroundColor( Color.parseColor("#A571EF"))
                    3 -> cardView_linearLayout.setBackgroundColor( Color.parseColor("#EF8678"))
                    4 -> cardView_linearLayout.setBackgroundColor( Color.parseColor("#A7F44D"))
                    5 -> cardView_linearLayout.setBackgroundColor( Color.parseColor("#D8C628"))
                    else ->cardView_linearLayout.setBackgroundColor( Color.parseColor("#ced2d9"))
                }
            }


            itemView.setOnClickListener{
                interaction.onSelectedItem(adapterPosition, value)
            }
            itemView.setOnLongClickListener{

                value.id?.let { it1 -> interaction.onDeleteItem(it1) }
                true
            }
        }
    }
     fun setData(newNotesList: List<Notes>){
        notesList.clear()
        notesList.addAll(newNotesList)

         Log.d("CHARAN list size :", "${notesList.size}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cards, parent, false)
      
        return ViewHolder(view, interaction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    interface Interaction {
        fun onSelectedItem(position: Int, item: Notes)
        fun onDeleteItem(item: Int)
    }
}


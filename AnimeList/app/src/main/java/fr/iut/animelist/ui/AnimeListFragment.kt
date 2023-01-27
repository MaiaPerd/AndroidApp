package fr.iut.animelist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.iut.animelist.R

class AnimeListFragment : Fragment()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var  view = inflater.inflate(R.layout.fragment_list_anime, container, false)
        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)



        //var chien: Chien = MainActivity().liste[position?: 0]
        //view.findViewById<EditText>(R.id.inputNom).setText(chien.nom)
        //view.findViewById<EditText>(R.id.inputRace).setText(chien.race)
        //view.findViewById<EditText>(R.id.inputPoids).setText(chien.messure)
        // view.findViewById<RatingBar>(R.id.ratingBar).numStars(chien.agressivite)

 /*
        ArrayAdapter.createFromResource(
            view.context,
            R.array.genre_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }*/

        return view
    }

}
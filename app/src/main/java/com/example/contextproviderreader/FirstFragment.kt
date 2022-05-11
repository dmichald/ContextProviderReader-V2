package com.example.contextproviderreader

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.Application
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.contextproviderreader.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            Log.d("CLICKED"," BUTTON")
            val projection = arrayOf("name", "number","CVC","ownerName","expirationDate")
            val contentUri: Uri =
                Uri.parse("content://pl.md.cardmanager.CardContentProvider/.CardContentProvider")
            val cursor = context?.contentResolver?.query(
                contentUri, projection, null, null,
                null,
            )
            if (cursor?.moveToFirst() == true) {
                do {
                    Log.d("MainActivity", "Username: ${cursor.getString(0)}, ${cursor.getString(1)}")
                    Toast.makeText(context,"Username: ${cursor.getString(0)}, ${cursor.getString(1)}", Toast.LENGTH_SHORT ).show()
                } while (cursor.moveToNext())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
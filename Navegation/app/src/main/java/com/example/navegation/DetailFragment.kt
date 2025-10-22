package com.example.navegation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navegation.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupera o argumento enviado
        val itemId = arguments?.getString("itemId")

        // Exibe o itemId (ou outro conteúdo) na tela
        binding.txtDetail.text = "ID do item: $itemId"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

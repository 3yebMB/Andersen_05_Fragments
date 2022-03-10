package dev.m13d.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.m13d.fragments.databinding.FragmentDetailsBinding

private const val ARG_PARAM1 = "param1"

class DetailsFragment : Fragment() {

    private lateinit var binding : FragmentDetailsBinding
    private var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contact = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        with(binding) {
            etName.setText(contact?.name)
            etSurname.setText(contact?.surname)
            etPhone.setText(contact?.phone)
        }

        return binding.root
    }

    companion object {

        const val DETAILS_FRAGMENT_TAG = "DETAILS_FRAGMENT_TAG"

        @JvmStatic
        fun newInstance(contact: Contact) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, contact)
                }
            }
    }
}

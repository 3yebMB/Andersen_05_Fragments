package dev.m13d.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.m13d.fragments.databinding.FragmentDetailsBinding

private const val ARG_PARAM1 = "param1"

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contact = it.getParcelable(ARG_PARAM1) ?: throw Exception("Contact not found")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        with(binding) {
            etName.setText(contact.name)
            etSurname.setText(contact.surname)
            etPhone.setText(contact.phone)

            btnCancel.setOnClickListener { onCancelPressed() }
            btnSave.setOnClickListener { onConfirmPressed() }
        }

        return binding.root
    }

    private fun onConfirmPressed() {
        val result = contact.copy(
            name = if (binding.etName.text.isNotEmpty()) binding.etName.text.toString() else return,
            surname = if (binding.etSurname.text.isNotEmpty()) binding.etSurname.text.toString() else return,
            phone = if (binding.etPhone.text.isNotEmpty()) binding.etPhone.text.toString() else return
        )
        navigator().publishResult(result = result)
        navigator().goBack()
    }

    private fun onCancelPressed() {
        navigator().goBack()
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

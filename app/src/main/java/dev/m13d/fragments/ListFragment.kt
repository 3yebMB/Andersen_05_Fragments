package dev.m13d.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.fragment.app.Fragment
import dev.m13d.fragments.Contacts.contacts
import dev.m13d.fragments.databinding.FragmentListBinding

private const val ARG_PARAM1 = "param1"

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var cardViewClickListener: CardViewClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cardViewClickListener = context as? CardViewClickListener
            ?: throw Exception("It's not implement CardViewClickListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        navigator().listenResult(Contact::class.java, viewLifecycleOwner) {
            val res = saveResult(it)
            showContacts(res)
        }

        showContacts(null)

        return binding.root
    }

    private fun saveResult(contact: Contact): MutableList<Contact> {
        val foundContact = contacts.find { it.id == contact.id }
        val index = contacts.indexOf(foundContact)
        contacts[index] = contact
        return contacts
    }

    private fun showContacts(conts: MutableList<Contact>?) {

        (binding.linearLayout).forEachIndexed { index, card ->
            (card as CardView).forEach card@ { linear ->
                card.setOnClickListener {
                    cardViewClickListener.onCardViewClicked(conts?.get(index) ?: contacts[index])
                }
                (linear as LinearLayout).forEach {
                    (linear[0] as TextView).text = conts?.get(index)?.name ?: contacts[index].name
                    (linear[1] as TextView).text = conts?.get(index)?.surname ?: contacts[index].surname
                    (linear[2] as TextView).text = conts?.get(index)?.phone ?: contacts[index].phone
                    return@card
                }
            }
        }
    }

    interface CardViewClickListener {
        fun onCardViewClicked(contact: Contact)
    }

    companion object {

        const val LIST_FRAGMENT_TAG = "LIST_FRAGMENT_TAG"

        @JvmStatic
        fun newInstance() = ListFragment()

    }
}

package dev.m13d.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.m13d.fragments.Contacts.contacts
import dev.m13d.fragments.databinding.FragmentListBinding

private const val ARG_PARAM1 = "param1"

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var cardViewClickListener: CardViewClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cardViewClickListener = context as CardViewClickListener
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
        with(binding) {

            with(item1) {
                setOnClickListener {
                    cardViewClickListener.onCardViewClicked(conts?.get(0) ?: contacts[0])
                }
                tvName1.text = conts?.get(0)?.name ?: contacts[0].name
                tvSurname1.text = conts?.get(0)?.surname ?: contacts[0].surname
                tvPhone1.text = conts?.get(0)?.phone ?: contacts[0].phone
            }
            with(item2) {
                setOnClickListener {
                    cardViewClickListener.onCardViewClicked(conts?.get(1) ?: contacts[1])
                }
                tvName2.text = conts?.get(1)?.name ?: contacts[1].name
                tvSurname2.text = conts?.get(1)?.surname ?: contacts[1].surname
                tvPhone2.text = conts?.get(1)?.phone ?: contacts[1].phone
            }
            with(item3) {
                setOnClickListener {
                    cardViewClickListener.onCardViewClicked(conts?.get(2) ?: contacts[2])
                }
                tvName3.text = conts?.get(2)?.name ?: contacts[2].name
                tvSurname3.text = conts?.get(2)?.surname ?: contacts[2].surname
                tvPhone3.text = conts?.get(2)?.phone ?: contacts[2].phone
            }
            with(item4) {
                setOnClickListener {
                    cardViewClickListener.onCardViewClicked(conts?.get(3) ?: contacts[3])
                }
                tvName4.text = conts?.get(3)?.name ?: contacts[3].name
                tvSurname4.text = conts?.get(3)?.surname ?: contacts[3].surname
                tvPhone4.text = conts?.get(3)?.phone ?: contacts[3].phone
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

package dev.m13d.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.m13d.fragments.Contacts.contacts
import dev.m13d.fragments.databinding.FragmentListBinding

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

        with(binding) {

            with(item1) {
                setOnClickListener {
                    cardViewClickListener.onCardViewClicked(contacts[0])
                }
                tvName1.text = contacts[0].name
                tvSurname1.text = contacts[0].surname
                tvPhone1.text = contacts[0].phone
            }
            with(item2) {
                setOnClickListener {
                    cardViewClickListener.onCardViewClicked(contacts[1])
                }
                tvName2.text = contacts[1].name
                tvSurname2.text = contacts[1].surname
                tvPhone2.text = contacts[1].phone
            }
            with(item3) {
                setOnClickListener {
                    cardViewClickListener.onCardViewClicked(contacts[2])
                }
                tvName3.text = contacts[2].name
                tvSurname3.text = contacts[2].surname
                tvPhone3.text = contacts[2].phone
            }
            with(item4) {
                setOnClickListener {
                    cardViewClickListener.onCardViewClicked(contacts[3])
                }
                tvName4.text = contacts[3].name
                tvSurname4.text = contacts[3].surname
                tvPhone4.text = contacts[3].phone
            }
        }

        return binding.root
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

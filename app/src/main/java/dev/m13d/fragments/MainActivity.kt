package dev.m13d.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.m13d.fragments.DetailsFragment.Companion.DETAILS_FRAGMENT_TAG
import dev.m13d.fragments.ListFragment.CardViewClickListener
import dev.m13d.fragments.ListFragment.Companion.LIST_FRAGMENT_TAG
import dev.m13d.fragments.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), CardViewClickListener {

    private lateinit var binding: ActivityMainBinding
    private var isTablet by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        isTablet = resources.getBoolean(R.bool.tablet)

        if (supportFragmentManager.findFragmentByTag(LIST_FRAGMENT_TAG) == null) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.fragment_container, ListFragment.newInstance(), LIST_FRAGMENT_TAG)
                commit()
            }
        }
    }

    override fun onCardViewClicked(contact: Contact) {
        supportFragmentManager.beginTransaction().run {
            if (isTablet) {
                replace(R.id.fragment_details_container, DetailsFragment.newInstance(contact))
            } else {
                replace(R.id.fragment_container, DetailsFragment.newInstance(contact))
            }
            addToBackStack(DETAILS_FRAGMENT_TAG)
            commit()
        }
    }
}

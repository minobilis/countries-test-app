package rosh.ivan.contries.feature.main

import android.os.Bundle
import rosh.ivan.contries.R
import rosh.ivan.contries.base.BaseActivity
import rosh.ivan.contries.feature.list.CountriesFragment

class MainActivity(override val layoutResource: Int = R.layout.activity_main) : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listFragment = CountriesFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, listFragment, listFragment.javaClass.toString())
                .addToBackStack(listFragment.javaClass.toString())
                .commit()
    }
}

package com.example.gameplayveia.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navigation.*
import projetos.danilo.jogodaveia.R

class NavigationActivity : AppCompatActivity() {

    private var noReplaceFragment: Boolean = false
    private val FRAGMENT_A = "FRAGMENT_A"
    private val FRAGMENT_B = "FRAGMENT_B"
    private val FRAGMENT_C = "FRAGMENT_C"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragmentA = MyFragmentA()
                managerFragment(fragmentA, "FRAGMENT_A")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val fragmentB = MyFragmentB()
                managerFragment(fragmentB, "FRAGMENT_B")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val fragmentC = MyFragmentC()
                managerFragment(fragmentC, "FRAGMENT_C")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //implementando fragment
        val fragmentA = MyFragmentA()
        managerFragment(fragmentA, "FRAGMENT_A")
    }

    private fun managerFragment(fragment: Fragment, tag: String){
        val fragmentManager: FragmentManager = getSupportFragmentManager()
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.add(R.id.containerForFragment, fragment, tag)
        fragmentTransaction.replace(R.id.containerForFragment, fragment, tag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val mfragmentA = fragmentManager.findFragmentByTag(FRAGMENT_A)
        if (mfragmentA != null) {
            if (mfragmentA.isVisible) {
                finish()
                return
            }
        }
        super.onBackPressed()
        managerIconsOfBottomNavigation(fragmentManager)
    }

    /**
     * usado para gerenciar o estado o icon de BottomNavigation quando o botão voltar (onBackPressed())
     * é pressionado
     * @param fragmentManager
     */
    private fun managerIconsOfBottomNavigation(fragmentManager: FragmentManager) {
        //Apenas para mudar a cor do icones da BoottomNavigationVew
        val mfragmentAaux = fragmentManager.findFragmentByTag(FRAGMENT_A)
        if (mfragmentAaux != null) {
            if (mfragmentAaux.isVisible) {
                noReplaceFragment = true
                navigation.selectedItemId = R.id.navigation_home
                noReplaceFragment = false
            }
        }

        val mfragmentB = fragmentManager.findFragmentByTag(FRAGMENT_B)
        if (mfragmentB != null) {
            if (mfragmentB.isVisible) {
                noReplaceFragment = true
                navigation.selectedItemId = R.id.navigation_dashboard
                noReplaceFragment = false
            }
        }

        val mfragmentC = fragmentManager.findFragmentByTag(FRAGMENT_C)
        if (mfragmentC != null) {
            if (mfragmentC.isVisible) {
                noReplaceFragment = true
                navigation.selectedItemId = R.id.navigation_notifications
                noReplaceFragment = false
            }
        }
    }
}

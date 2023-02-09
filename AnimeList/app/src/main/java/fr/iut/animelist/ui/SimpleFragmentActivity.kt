package fr.iut.animelist.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import fr.iut.animelist.R

abstract class SimpleFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResId())

        if (supportFragmentManager.findFragmentById(R.id.container_fragment) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_fragment, createFragment())
                .commit()
        }
    }


    /**
     * Creates an instance of the hosted fragment
     */
    protected abstract fun createFragment(): Fragment


    /**
     * Returns the resource id of the layout used for this activity.
     * It must contain a view whose id is `@+id/container_fragment`
     * to inject the hosted fragment's view
     */
    @LayoutRes
    protected abstract fun getLayoutResId(): Int
}
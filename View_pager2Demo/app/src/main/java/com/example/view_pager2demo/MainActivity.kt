package com.example.view_pager2demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        findViewById<ViewPager2>(R.id.view_pager).apply {
            // Set offscreen page limit to at least 1, so adjacent pages are always laid out
            offscreenPageLimit = 1
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
//                val padding = resources.getDimensionPixelOffset(R.dimen.halfPageMargin) +
//                        resources.getDimensionPixelOffset(R.dimen.peekOffset)
//                // setting padding on inner RecyclerView puts overscroll effect in the right place
//                // TODO: expose in later versions not to rely on getChildAt(0) which might break
//                setPadding(padding, 0, padding, 0)
                clipToPadding = false
            }
            adapter = Adapter()
        }
    }

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_preview_pages, parent, false)
    )

    class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount(): Int {
            return 10
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return ViewHolder(parent)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.tag = position
        }
    }
}


package com.github.nyafunta.rssreader.ui.feed

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.nyafunta.rssreader.domain.infra.predefine.Category

class RssFeedCategoryPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private val categories = mutableListOf<Category>()

    fun update(newCategories: List<Category>) {
        categories.clear()
        categories.addAll(newCategories)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = Category.values().size

    override fun createFragment(position: Int): Fragment =
        RssFeedPageItemFragment.newInstance(categories[position])

}
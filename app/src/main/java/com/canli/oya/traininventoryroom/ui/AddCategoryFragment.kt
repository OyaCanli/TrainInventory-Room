package com.canli.oya.traininventoryroom.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProviders
import com.canli.oya.traininventoryroom.R
import com.canli.oya.traininventoryroom.data.CategoryEntry
import com.canli.oya.traininventoryroom.databinding.FragmentAddCategoryBinding
import com.canli.oya.traininventoryroom.viewmodel.MainViewModel


class AddCategoryFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentAddCategoryBinding
    private val mViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_category, container, false)

        binding.addCategoryEditCatName.requestFocus()
        binding.addCategorySaveBtn.setOnClickListener { saveCategory() }

        return binding.root
    }

    private fun saveCategory() {
        val categoryName = binding.addCategoryEditCatName.text.toString().trim()
        val newCategory = CategoryEntry(categoryName)
        //Insert the category by the intermediance of view model
        mViewModel.insertCategory(newCategory)

        //Remove the fragment
        val parentFrag = parentFragment
        val containerID = if (parentFrag is AddTrainFragment) R.id.childFragContainer
                            else R.id.brandlist_addFrag_container
        val currentInstance = fragmentManager?.findFragmentById(containerID)

        //Clear focus and hide soft keyboard
        val focusedView = activity?.currentFocus
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        focusedView?.clearFocus()
        imm.hideSoftInputFromWindow(focusedView?.windowToken, 0)

        fragmentManager?.transaction {
            setTransition(TRANSIT_FRAGMENT_CLOSE)
            remove(currentInstance!!)
        }
    }
}

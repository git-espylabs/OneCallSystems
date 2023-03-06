package com.espy.onecallsys.ui.shops.view

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.espy.onecallsys.R
import com.espy.onecallsys.databinding.FragmentShopPaidAmountBottomSheetBinding
import com.espy.onecallsys.extensions.dpToPixel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShopPaidAmountBottomSheetDialogFragment(var viewModel: ShopsViewModel, var shopId: String) :
    BottomSheetDialogFragment() {

    private lateinit var binding: FragmentShopPaidAmountBottomSheetBinding
    var selected_Pay_Mode = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_paid_amount_bottom_sheet,
            container,
            false
        )
        binding.apply {
            lifecycleOwner = viewLifecycleOwner



            spnPayTyp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {

                    if (TextUtils.equals(parent.selectedItem.toString(), "Cash")) {
                        selected_Pay_Mode = "1"
                    }
                    else if (TextUtils.equals(parent.selectedItem.toString(), "Card")) {
                        selected_Pay_Mode = "2"
                    }
                    else {
                        selected_Pay_Mode = "3"
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }


            spnPayTyp.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.pay_types_new)
            )


            btnSubmit.setOnClickListener {
                if (isAllFieldsValid()) {
                    viewModel.submitCreatePaidAmount(
                        shopId,
                        binding.etAmount.text.toString(),
                        selected_Pay_Mode,
                        binding.payRemarks.text.toString(),
                        binding.payNote.text.toString()


                    )


                }
                viewModel?.paidStatus.observe(viewLifecycleOwner, Observer {
                    if(it)
                    {

                        Toast.makeText(requireActivity(),"Paid Amount submitted successfully",Toast.LENGTH_LONG).show()
                        dismiss()
                    }
                    else
                    {
                        Toast.makeText(requireActivity(),"Paid Amount submitted Failed",Toast.LENGTH_LONG).show()
                    }
                })
            }



            btnClose.setOnClickListener {
                dismiss()
            }


        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()

        val tv = TypedValue()
        val normalActionBarHeight = 56
        var actionBarHeight = normalActionBarHeight.dpToPixel().toInt()
        if (requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }

        if (layoutParams != null) {
            layoutParams.height = windowHeight - actionBarHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }


    private fun isAllFieldsValid(): Boolean {
        return binding.run {
            when {
                etAmount.text.toString().isEmpty() -> {
                    (activity as ShopDetailsActivity).showToast("Please enter an amount")
                    etAmount.error = "Enter an amount"
                    false
                }
                payRemarks.text.toString().isEmpty() -> {
                    (activity as ShopDetailsActivity).showToast("Please enter remarks")
                    etAmount.error = "Enter remarks"
                    false
                }
                payNote.text.toString().isEmpty() -> {
                    (activity as ShopDetailsActivity).showToast("Please enter note")
                    etAmount.error = "Enter note"
                    false
                }

                else -> {
                    true
                }
            }
        }
    }
}
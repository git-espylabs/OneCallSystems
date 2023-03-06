package com.espy.onecallsys.ui.shops.view

import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.espy.onecallsys.R
import com.espy.onecallsys.api.Result
import com.espy.onecallsys.app.AppSettings
import com.espy.onecallsys.databinding.FragmetShopDetailsBinding
import com.espy.onecallsys.extensions.launchActivity
import com.espy.onecallsys.extensions.sumBy
import com.espy.onecallsys.preference.AppPreferences
import com.espy.onecallsys.api.models.shops.Shop
import com.espy.onecallsys.api.models.shops.ShopOutstanding
import com.espy.onecallsys.location.GpsListener
import com.espy.onecallsys.location.GpsManager
import com.espy.onecallsys.ui.base.BaseFragmentWithBinding
import com.espy.onecallsys.ui.order.view.OrderActivity

class ShopDetailsFragment:
    BaseFragmentWithBinding<FragmetShopDetailsBinding>(R.layout.fragmet_shop_details), View.OnClickListener, GpsListener {

    private lateinit var viewModel: ShopsViewModel

    val args: ShopDetailsFragmentArgs by navArgs()

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnAddSalesreturn ->{
                ShopSalesReturnBottomSheetDialog().show(
                    childFragmentManager,
                    "ShopSalesReturnFragment"
                )

            }
            R.id.collectPay ->{
                if (viewModel.shopsOutstandingTotal > 0) {
                    ShopPayCollectionBottomSheetDialogFragment(viewModel, args.shop?.id?:"0").show(
                        childFragmentManager,
                        "ShopPayCollectionFragment"
                    )
                } else {
                    showToast("No outstanding payments for this shop")
                }
            }
            R.id.btnStartOrder ->{
                activity?.launchActivity<OrderActivity> {
                    putExtra(OrderActivity.ARGS_SHOP_ID, args.shop?.id?:0)
                }
            }
            R.id.shopout ->{
                (activity as ShopDetailsActivity).showProgress()
                GpsManager(this, requireActivity()).getLastLocation()
            }
            R.id.payHistory ->{
                findNavController().navigate(ShopDetailsFragmentDirections.actionShopDetailsFragmentToShopCollectionHistoryFragment())
            }

            R.id.paidAmount ->{

                    ShopPaidAmountBottomSheetDialogFragment(viewModel, args.shop?.id?:"0").show(
                        childFragmentManager,
                        "ShopPaidAmountFragment"
                    )

            }
            R.id.paidAmountReport ->{
                findNavController().navigate(ShopDetailsFragmentDirections.actionShopDetailsFragmentToShopCollectionPaidAmountReportFragment())


            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ShopsViewModel::class.java)

        binding.apply {
            viewModel = this.viewModel
            viewParent = this@ShopDetailsFragment
        }
        (activity as ShopDetailsActivity).setToolBarProperties(getString(R.string.shop_details))

        setObserver()
    }

    override fun onResume() {
        super.onResume()

        args.shop?.run {
            ShopDetailsActivity.currentShop = this
            populateShopDetails(this)
            id?.let {
                (activity as ShopDetailsActivity).showProgress()
                viewModel.getShopOutstanding(it)
            }
        }
    }

    override fun onLocationUpdate(location: Location?) {
        if (location != null && location.latitude != 0.0 && location.longitude != 0.0) {
            viewModel.tagShopOut(args.shop?.id?:"0", location)
        } else {
            onLocationDisabled()
        }
    }

    override fun onLocationDisabled() {
        (activity as ShopDetailsActivity).hideProgress()
        showAlertDialog(R.string.alert_location_off)
    }

    private fun populateShopDetails(data: Shop?){
        data?.run {
            binding.apply {
                Glide
                    .with(requireContext())
                    .load(AppSettings.endPoints.IMAGE_ASSETS + data.shop_image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(shopImg)

                shopName.text = shop_name

                shopAddress.text = shop_address

                shopTime.text = ""

                shRoute.text = "Route: $route_name"

                try {
                    amountOutstanding.text = requireContext().getString(R.string.amount_rep_float,
                        (shop_oustanding_amount?: "0.00").toFloat()
                    )

                } catch (e: Exception) {
                }
            }
        }
    }

    private fun setObserver(){
        viewModel.shopsOutstandingList.observe(viewLifecycleOwner) {
            (activity as ShopDetailsActivity).hideProgress()
            if (it is Result.Success && it.data.any()) {
                calculateAndSetOutstandingTotal(it.data)
            }
        }

        viewModel.paymentTypesList.observe(viewLifecycleOwner) {
            if (it is Result.Success && it.data.any()) {
                viewModel.capPaymentTypes = it.data
            }
        }

        viewModel.paymentStatus.observe(viewLifecycleOwner) {
            if (it) {
                args.shop?.id?.let { shopid ->
                    (activity as ShopDetailsActivity).showProgress()
                    viewModel.getShopOutstanding(shopid)
                }
                showToast("Payment Completed. Thanks!")
            } else {
                showToast(getString(R.string.something_went_wrong))
            }
        }

        viewModel.isShoppedOut.observe(viewLifecycleOwner) {
            (activity as ShopDetailsActivity).hideProgress()
            if (it) {
                AppPreferences.shopInId = ""
                (activity as ShopDetailsActivity).onBackPressed()
            } else {
                showToast(R.string.something_went_wrong)
            }
        }
    }

    private fun calculateAndSetOutstandingTotal(list: List<ShopOutstanding>){
        try {
            list.sumBy { (it.shop_oustanding_amount?:"0.00").toFloat() }.apply {
                binding.amountOutstanding.text = requireContext().getString(R.string.amount_rep_float, this)
                viewModel.shopsOutstandingTotal = this
                if (this <= 0){
                    showToast("No outstanding payments for this shop")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
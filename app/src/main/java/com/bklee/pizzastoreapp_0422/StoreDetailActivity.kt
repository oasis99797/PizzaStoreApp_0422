package com.bklee.pizzastoreapp_0422

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bklee.pizzastoreapp_0422.datas.Store

class StoreDetailActivity : BaseActivity() {

    private lateinit var storeData:Store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        storeData = intent.getSerializableExtra("storeData") as Store

        storeNameTxt.text = storeData.name
        storePhoneTxt.text = storeData.phoneNum

        Glide.with(mContext).load(storeData.logoUrl).into(storeLogoImg)



    }

}

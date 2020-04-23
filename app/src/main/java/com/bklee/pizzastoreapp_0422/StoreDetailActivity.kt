package com.bklee.pizzastoreapp_0422

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bklee.pizzastoreapp_0422.datas.Store
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_store_detail.*

class StoreDetailActivity : BaseActivity() {

    private lateinit var storeData:Store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


        callToStoreBtn.setOnClickListener {
            //            it 변수 활용 예시
//            it.visibility = View.GONE

            val pl = object : PermissionListener {
                override fun onPermissionGranted() {

                    val storeCallUri = Uri.parse("tel:${storeData.phoneNum}")
                    val myIntent = Intent(Intent.ACTION_CALL, storeCallUri)
                    startActivity(myIntent)

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext, "통화 권한을 허가해줘야 사용가능합니다.", Toast.LENGTH_SHORT).show()
                }

            }

            TedPermission.with(mContext)
                .setPermissionListener(pl)
                .setDeniedMessage("통화 권한 설정을 해야 사용 가능합니다. [설정] 에서 권한을 넣어주세요.")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()


        }

    }

    override fun setValues() {

        storeData = intent.getSerializableExtra("storeData") as Store

        storeNameTxt.text = storeData.name
        storePhoneTxt.text = storeData.phoneNum

        Glide.with(mContext).load(storeData.logoUrl).into(storeLogoImg)



    }

}

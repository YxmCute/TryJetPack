package com.lij.tryjetpack

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ftd.livepermissions.LivePermissions
import com.ftd.livepermissions.PermissionResult
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.test()
        button_first.setOnClickListener {
            LivePermissions(this).request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).observe(viewLifecycleOwner, Observer {
                when (it) {
                    is PermissionResult.Grant -> {  //权限允许
                        Toast.makeText(context, "Grant", Toast.LENGTH_SHORT).show()
                        //
                        Matisse.from(this)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(100)
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(GlideEngine())
                            .showPreview(false) // Default is `true`
                            .forResult(1)
                    }
                    is PermissionResult.Rationale -> {  //权限拒绝
                        it.permissions.forEach { s ->
                            println("Rationale:${s}")//被拒绝的权限
                        }
                        Toast.makeText(context, "Rationale", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is PermissionResult.Deny -> {   //权限拒绝，且勾选了不再询问
                        it.permissions.forEach { s ->
                            println("deny:${s}")//被拒绝的权限
                        }
                        Toast.makeText(context, "deny", Toast.LENGTH_SHORT).show()
                    }
                }
            })


            /*PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE)
                .callback(object : PermissionUtils.FullCallback {
                    override fun onGranted(permissionsGranted: MutableList<String>?) {

                    }

                    override fun onDenied(
                        permissionsDeniedForever: MutableList<String>?,
                        permissionsDenied: MutableList<String>?
                    ) {
                    }
                })
                .rationale(object : PermissionUtils.OnRationaleListener {
                    override fun rationale(
                        activity: UtilsTransActivity?,
                        shouldRequest: PermissionUtils.OnRationaleListener.ShouldRequest?
                    ) {
                    }
                })
                .request()*/
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //https://juejin.im/post/5ddb2b5b51882573461819e0#heading-2

        //https://mlog.club/article/21040
        //https://juejin.im/post/5d838a7af265da03ee6a90cd#heading-4
        //https://juejin.im/post/5e43ab2bf265da572660f777 baidu
        //https://open.oppomobile.com/wiki/doc#id=10432 oppo
        //https://juejin.im/post/5e564367e51d4526e807f0e4
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val result = Matisse.obtainResult(data)
            val path = Matisse.obtainPathResult(data)
            for (uri in result) {
                Log.i("chose", "result=$uri")
            }
            for (uri in path) {
                Log.i("chose", "path=$uri")
            }
        }
    }

    suspend fun test() {


        coroutineScope { }
        val job = GlobalScope.launch {
            delay(1000L)
            Log.i("timo", "hihihi")

        }
        job.join()
    }
}

package com.example.lab.one.ice6

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.Control
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val contactListRawString = getTextFromResource(this, R.raw.contacts)
        //Log.i("contact_list_raw", contactListRawString)

        //val contactListAssetString = getTextFromAssets(this, "contacts.json")
       //Log.i("contact_list_assets", contactListAssetString)

       val CL =  deJSONDataAA();
        if(CL != null)
        {
            for(C in CL)
            {
                Log.i("Assets", C.toString())
            }
        }
        val CL2 =  deJSONData();
        if(CL2 != null)
        {
            for(C2 in CL2)
            {
                Log.i("Raw", C2.toString())
            }
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun deJSONData(): List<ContactModel>?
    {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, ContactModel::class.java)
        val adapter: JsonAdapter<List<ContactModel>> = moshi.adapter(listType)
        val contactListRawString = getTextFromResource(this, R.raw.contacts)
        val contactList: List<ContactModel>? = adapter.fromJson(contactListRawString)
        return contactList
    }
    @OptIn(ExperimentalStdlibApi::class)
    fun deJSONDataAA(): List<ContactModel>?
    {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, ContactModel::class.java)
        val adapter: JsonAdapter<List<ContactModel>> = moshi.adapter(listType)
        val contactListAssetString = getTextFromAssets(this, "contacts.json")
        val contactList: List<ContactModel>? = adapter.fromJson(contactListAssetString)
        return contactList
    }

    fun getTextFromResource(context: Context, resourceId: Int): String
    {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use {it.readText()}
    }

    fun getTextFromAssets(context: Context, fileName: String): String
    {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use {it.readText()}
    }

}


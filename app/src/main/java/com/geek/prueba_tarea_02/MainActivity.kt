package com.geek.prueba_tarea_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.Request.Method.*
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.OkHttpClient
import okhttp3.internal.addHeaderLenient
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    lateinit var mensaje : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // InvocaJsonimg()
        gettVolley()

    }


    private fun VisualizaEjemplo2_(list1: List<String>)
    {
        val recyclerView_ : RecyclerView =findViewById(R.id.recycler_api)
        val adapter_=customerAdapter(this, list1,list1,list1,list1,list1)

        recyclerView_.layoutManager= LinearLayoutManager(this)
        recyclerView_.adapter=adapter_
    }




    fun postVolley() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://private-4c0e8-simplestapi3.apiary-mock.com/message"
        val txtresul = findViewById<TextView>(R.id.lbl_titulo_api)
        val requestBody = "id=1" + "&msg=test_msg"
        val stringReq : StringRequest =
            object : StringRequest(Method.POST, url,
                Response.Listener { response ->
                    // response
                    var strResp = response.toString()
                   txtresul.text=strResp
                },
                Response.ErrorListener { error ->
                    Log.d("API", "error => $error")
                }
            ){
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
        queue.add(stringReq)
    }

    fun gettVolley() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://gateway.marvel.com/v1/public/characters?ts=20102000&apikey=44c6d8ed792d8c6ec2d29a1355b17636&hash=8a2f17659961c8c3bf26e2e101abe31c"
        val txtresul = findViewById<TextView>(R.id.lbl_titulo_api)

        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                var strResp = response.toString()
//                Toast.makeText(this, parsaID(parsaJson(strResp)).toString() , Toast.LENGTH_LONG).show()
//                Toast.makeText(this, CreateList(parsaJson(strResp),"\"name\"","\"description\"").toString() , Toast.LENGTH_LONG).show()
                //Toast.makeText(this, CreateList(parsaJson(strResp),"\"id\"","\"name\"").toString() , Toast.LENGTH_LONG).show()
                var original = parsaJson(strResp)
                var lid=CreateList(original,"\"id\"","\"name\"")
                var lname=CreateList(original,"\"name\"","\"description\"")
                var ldescription=CreateList(original,"\"description\"","\"modified\"")
                var lmodified=CreateList(original,"\"modified\"","\"path\"")
                var lpath=CreateList(original,"\"path\"","\"extension\"")

                VisualizaCardview_(lid,lname,ldescription,lmodified,lpath)

                                      },
            Response.ErrorListener {Log.d("API", "that didn't work") })
        queue.add(stringReq)
    }

    private fun VisualizaCardview_(list1: List<String>,list2: List<String>,list3: List<String>,list4: List<String>,list5: List<String>)
    {
        val recyclerView_ : RecyclerView =findViewById(R.id.recycler_api)
        val adapter_=customerAdapter(this, list1,list2,list3,list4,list5)

        recyclerView_.layoutManager= LinearLayoutManager(this)
        recyclerView_.adapter=adapter_
    }

    //Parseamos nuestro Json
    fun parsaJson (parm :String): List<String>
    {
        val list: List<String> = listOf(*parm.split(",").toTypedArray())
        return list
    }

    //Obtendre una Lista para cada etiqueta
    fun CreateList(parm1: List<String>, parm2: String, parm3: String): List<String>
    {
        var index:Int
        var lisitems = arrayListOf<String>()
        index=0
        while (index < parm1.size-1) {
            if(parm1[index].contains(parm2)  && parm1[index+1].contains(parm3)){
              //Subdivimos la cadena no es necesario
               if(parm2.contains("\"path\""))
               {
                   lisitems.add((parm1[index].substring(21,parm1[index].length-1))+".jpg")
               }
                else {lisitems.add(parm1[index])}
            }
            index++
        }
        return lisitems
    }
}

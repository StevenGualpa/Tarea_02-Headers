package com.geek.prueba_tarea_02

class Cs_Personaje
{

    private lateinit var id : String                          //Id
    private lateinit var name : String                          //Nombre
    private lateinit var description : String               //Descripcion
    private lateinit var modified : String                  //Ultima Modificacion
    private lateinit var thumbnail : String                 //Imagen //path //extension

    fun getid() : String { return this.id}
    fun setid(parm_id : String) { this.id=parm_id }


    fun getname() : String { return this.name }
    fun setname(parm_name : String) { this.name=parm_name }

    fun getdescription() : String{return this.description}
    fun setdescription(parm_description : String) {this.description=parm_description}

    fun getmodified() : String{return this.modified}
    fun setmodified(parm_modified : String) {this.modified=parm_modified}

    fun getthumbnail() : String{return this.thumbnail}
    fun setthumbnail(parm_thumbnail : String) {this.thumbnail=parm_thumbnail}


}
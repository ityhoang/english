package english.com.base

class BaseException : Exception {
    constructor(type: Type, code: Int? = null, message: String? = null)
    constructor(type: String, message: String? = null)
    constructor(e:Exception)

    var code: Int?=null
    var msg: String?=null
    val type:String? = null
    companion object Type {
        var HTTP: String = "HTTP"
        var NETWORK: String = "NETWORK"
        var SERVER: String = "SERVER"
        var UNEXPECTED: String = "UNEXPECTED"
    }
}
package com.digivisions.core.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone



fun String.splitUrl():Int{
    var x=this.split("/")
    return x[x.lastIndex].toInt()
}
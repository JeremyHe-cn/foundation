@file:JvmName("TaobaoUtils")
package me.alzz.ext

import android.util.Log

/**
 * 淘宝相关的工具
 * Created by JeremyHe on 2020-06-02.
 */

/**
 * 查询淘口令中的短链
 */
fun CharSequence.findTbShortUrl(): String? {
    if (this.contains("tb.cn")) {
        val regex = """(http://m\.tb\.cn/[\w\.]+) """.toRegex()
        regex.find(this)?.let {
            Log.d("StringExt", "found tb short url id")
            return it.groupValues[1]
        }
    }

    return null
}

/**
 * 查找商品名称
 */
fun CharSequence.findTbProductName(): String? {
    var name: String? = null
    if (this.contains("【")) {
        if (this[0] == '【') {
            val regex = """【(\S+)】""".toRegex()
            regex.find(this)?.let {
                Log.d("StringExt", "found tb product name")
                name = it.groupValues[1]
            }
        } else {
            name = this.substring(0, this.indexOf("【"))
        }
    } else if (this.contains("手淘") || this.contains("淘宝") || this.contains("天猫")) {
        name = this.lines()[0]
    }

    name?.let { if (name!!.contains("分享")) name = null }
    return name
}


/**
 * 查找淘宝商品 Id
 */
fun CharSequence.findTbItemId(): String? {
    if (this.contains("taobao.com") || this.contains("tmall.com")) {
        var regex = """item[iI]d=(\d*)""".toRegex()
        regex.find(this)?.let {
            Log.d("StringExt", "found tb item id")
            return it.groupValues[1]
        }

//        regex = """sku[iI]d=(\d*)""".toRegex()
//        regex.find(this)?.let {
//            Log.d("StringExt", "found tb sku id")
//            return it.groupValues[1]
//        }

        regex = """[?&]id=(\d{9,})""".toRegex()
        regex.find(this)?.let {
            Log.d("StringExt", "found tb id")
            return it.groupValues[1]
        }

        regex = """com/i(\d{9,}).htm""".toRegex()
        regex.find(this)?.let {
            Log.d("StringExt", "found item html id")
            return it.groupValues[1]
        }
    }

    return null
}


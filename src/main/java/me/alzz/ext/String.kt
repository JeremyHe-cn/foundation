package me.alzz.ext

/**
 * String 工具方法
 * Created by JeremyHe on 2018/4/15.
 */

/**
 * 判断字符串是否为空
 */
fun String?.isNotEmpty() = !this.isNullOrEmpty()

/**
 * 移除小数点后的0
 */
fun String.trimZero(): String {
    var result = this.replace("""\.0+$""".toRegex(), "")
    if (result.contains(".")) {
        result = result.trimEnd { it == '0' }
    }

    return result
}

fun String.isEmail() = this.matches("^[a-zA-Z0-9_\\-.]+@[a-zA-Z0-9_\\-]+(\\.[a-zA-Z0-9_-]+)+$".toRegex())

fun String.isPhone() = this.matches("^[1][345678][0-9]{9}$".toRegex())

/**
 * 给手机号增加空格
 */
fun String.beautyPhone(): String {
    return when {
        this.length > 7 -> "${substring(0..2)} ${substring(3..6)} ${substring(7)}"
        this.length > 3 -> "${substring(0..2)} ${substring(3)}"
        else -> this
    }
}

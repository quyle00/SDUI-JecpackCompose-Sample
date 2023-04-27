package com.quyt.sdui_jecpack_compose

class ComponentModel {
    var type: String? = null
    var children: ArrayList<ComponentModel>? = null
    var value: String? = null
    var props: PropModel? = null

    companion object {
        const val COLUMN = "COLUMN"
        const val ROW = "ROW"
        const val TEXT = "TEXT"
        const val BUTTON = "BUTTON"
        const val IMAGE = "IMAGE"
        const val SPACER = "SPACER"
    }
}

class PropModel {
    var alignment: String? = null
    val arrangement: String? = null
    var style : StyleModel? = null
    var modifier: ModifierModel? = null
}

class StyleModel {
    var color: String? = null
    var fontSize: Int = 0
}

class ModifierModel {
    var size: Int = 0
    var width: String? = null
    var height: String? = null
    var backgroundColor: String? = null
    var rounded: Int = 0
    var margin : Int = 0
}
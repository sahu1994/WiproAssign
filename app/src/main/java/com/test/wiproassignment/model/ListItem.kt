package com.test.wiproassignment.model


import kotlin.collections.List


/**
 * Class will provide item information
 * @constructor Sets all properties of the item
 * @property title title of item
 * @property description description of item
 * @property imageHref image url of item
 */
data class Item(val title: String, val description: String, val imageHref: String){

    override fun toString(): String {
        return "Item $title - $description - $imageHref}"
    }

}

/**
 * Class will provide full list and title information
 * @constructor Sets all properties of the item
 * @property title title of item
 * @property list description of item
 */
data class List(val title: String, val rows: List<Item>){

    override fun toString(): String {
        return "ListItems $title - $rows"
    }

}
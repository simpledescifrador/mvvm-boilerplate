package com.esys.mvvmboilerplate.base

import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder, T> :
    RecyclerView.Adapter<VH>() {
    private lateinit var data: ArrayList<T>
    private lateinit var eventListener: AdapterEventListener

    interface AdapterEventListener {
        fun <T> onItemClick(item: T)
    }

    /**
     * Returns Adapter Event Listener
     * @return Event Listener
     */
    fun getEventListener() = eventListener

    /**
     * Set Adapter Event Listener
     * @param adapterEventListener Event Listener for adapter
     */
    open fun setAdapterEventListener(adapterEventListener: AdapterEventListener) {
        this.eventListener = adapterEventListener
    }

    /**
     * Clears all the data in the array.
     */
    open fun clear() {
        val size = itemCount
        this.data.clear()
        notifyItemRangeRemoved(0, size)
    }

    /**
     * Returns the data.
     *
     * @return Array of data
     */
    open fun getData(): List<T> {
        return this.data
    }

    /**
     * Setting the data.
     *
     * @param data Data that need to be set.
     */
    open fun setData(data: ArrayList<T>) {
        this.data = data
        notifyDataSetChanged()
    }

    /**
     * Returns the item from the data in the specified position.
     *
     * @param position Specified position of the item.
     * @return The item of the specified position.
     */
    open fun getItem(position: Int): T? {
        return if (this.data.isNotEmpty()) {
            this.data[position]
        } else {
            null
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item to retrieve the position of.
     * @return The position of the specified item.
     */
    open fun getPosition(item: T): Int {
        return this.data.indexOf(item)
    }

    /**
     * Inserts the specific item at the specified position in the array.
     *
     * @param item     The item to insert at specified position.
     * @param position Specified position of the new item.
     */
    open fun insert(item: T, position: Int) {
        this.data.add(position, item)
        notifyItemInserted(position)
    }

    /**
     * Removes item of specified position.
     *
     * @param position Specified position of removal.
     */
    open fun remove(position: Int) {
        this.data.removeAt(position)
        notifyItemRemoved(position)
    }

    /**
     * Sorts the content of this adapter using the specified comparator.
     *
     * @param comparator The comparator used to sort the objects contained in this adapter.
     */
    open fun sort(comparator: Comparator<in T?>?) {
        Collections.sort(this.data, comparator)
        notifyItemRangeChanged(0, itemCount)
    }
}

package com.example.recyclergreedexperement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: MyAdapter? = null
    private var mLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    private fun initComponents() {
        val myDataset = arrayOf(" One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine")
        val myImages = intArrayOf(R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9)
        mRecyclerView = findViewById(R.id.my_recycler_view) as RecyclerView?
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = GridLayoutManager(this, 2)
        mLayoutManager!!.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = if (position % 3 == 0) 2 else  1


        }
        mRecyclerView?.setLayoutManager(mLayoutManager)
        mAdapter = MyAdapter(myDataset, myImages)
        mRecyclerView?.setAdapter(mAdapter)
    }

    class MyAdapter(private val mDataset: Array<String>, private val mImages: IntArray) : RecyclerView.Adapter<MyAdapter.ViewHolder?>() {
        inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            var mTextView: TextView
            var mImageView: ImageView

            init {
                mTextView = v.findViewById<View>(R.id.txt) as TextView
                mImageView = v.findViewById<View>(R.id.img) as ImageView
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): ViewHolder {
            val v: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.my_text_view_grid, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mTextView.text = mDataset[position]
            holder.mImageView.setImageResource(mImages[position])
        }

        override fun getItemCount(): Int {
            return mDataset.size
        }
    }

}
package com.canli.oya.traininventoryroom.ui.trains

import android.content.Context
import com.canli.oya.traininventoryroom.R
import com.canli.oya.traininventoryroom.data.TrainEntry
import com.canli.oya.traininventoryroom.data.TrainMinimal
import com.canli.oya.traininventoryroom.ui.base.BaseAdapter
import com.canli.oya.traininventoryroom.ui.base.BaseDiffCallback
import com.canli.oya.traininventoryroom.ui.base.SwipeDeleteListener

class TrainAdapter (context: Context, clickListener: TrainItemClickListener, swipeListener: SwipeDeleteListener<TrainMinimal>)
    : BaseAdapter<TrainMinimal, TrainItemClickListener>(context, clickListener, swipeListener) {

    override fun getLayoutId(): Int  = R.layout.item_train
}

interface TrainItemClickListener {
    fun onListItemClick(trainId: Int)
}

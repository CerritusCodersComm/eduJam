package com.example.gdsc_hackathon.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlin.collections.ArrayList
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.gdsc_hackathon.dataModel.DashboardModel
import com.example.gdsc_hackathon.R


// TODO NO USE DELETE IT LATER
class DashboardItemAdapter(context: Context, courseModelArrayList: ArrayList<DashboardModel>) :

    ArrayAdapter<DashboardModel?>(context, 0, courseModelArrayList as List<DashboardModel?>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listitemView = convertView
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(context).inflate(
                R.layout.dashboard_grid_view_item, parent,
                false
            )
        }
        val dashboardModel: DashboardModel? = getItem(position)
        val itemTitle = listitemView!!.findViewById<TextView>(R.id.item_title)
        val itemIcon = listitemView.findViewById<ImageButton>(R.id.item_icon)
        itemTitle.text = dashboardModel!!.imgTitle
        itemIcon.setImageResource(dashboardModel.imgId)

        itemIcon.setOnClickListener {

            when(position){
                0->{
                    convertView?.findNavController()?.navigate(R.id.syllabusFragment)
                    }
                1->{
                    convertView?.findNavController()?.navigate(R.id.weeklyTimeTableFragment)
                }
                2->{
                    convertView?.findNavController()?.navigate(R.id.holidayFragment)
                }
                3->{
                    convertView?.findNavController()?.navigate(R.id.examTimeConstraintFragment)
                }
                4->{
                    convertView?.findNavController()?.navigate(R.id.practicalFragment)
                }
                5->{
                    convertView?.findNavController()?.navigate(R.id.previousYearPapersFragment)
                }
                6->{
                    convertView?.findNavController()?.navigate(R.id.academicCalendarFragment)
                }
                7->{
                    convertView?.findNavController()?.navigate(R.id.moreFragment)
                }
            }
            Toast.makeText(context, "You Clicked ${dashboardModel.imgTitle}", Toast.LENGTH_LONG).show()
        }

        return listitemView
    }
    private fun pushFragment(newFragment: Fragment?, context: Context) {
        val transaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.hostFragment, newFragment!!)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

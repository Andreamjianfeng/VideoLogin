package com.andream.aiiage.videologin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    /**
     * viewPaper滑动监听
     */
    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        home_vp.currentItem = if (p1 == R.id.home_rb_dujia) 0 else 1
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val par = home_fl_view.layoutParams as FrameLayout.LayoutParams
        //设置下划线距离左边的位置长度
        val left = ((positionOffset + position) * width).toInt() + leftWidth
        par.setMargins(left, 0, 0, 0)
        home_fl_view.layoutParams = par
    }

    /**
     * 单选框点击监听
     */
    override fun onPageSelected(position: Int) {
        home_rg.check(if (position == 0) R.id.home_rb_dujia else R.id.home_rb_ticker)

    }

    private var mAdapter: FragmentPagerAdapter? = null
    private val arrayList = ArrayList<Fragment>()
    private var width = 250
    private var leftWidth = 0
    private var fragments = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTextView()
        initData()
        initView()
    }

    /**
     * 初始化头标
     */
    private fun initTextView() {
        //创建适配器,设置的碎片管理器使用的是getChildFragmentManager()
        //给ViewPager设置适配器
        arrayList.add(LoginFragment())
        arrayList.add(RacistFragment())
        mAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return arrayList[position]
            }

            override fun getCount(): Int {
                return arrayList.size
            }
        }
        home_vp!!.adapter = mAdapter
        //给控件设置监听事件
        home_rg!!.setOnCheckedChangeListener(this)
        //给ViewPager设置监听器使用的是add而不是set了
        home_vp!!.addOnPageChangeListener(this)
        initVi()

    }

    /**
     * 屏幕一般的宽度
     * 把下划线View设置初始值
     */
    private fun initVi() {
        //设置下划线View的长度
        val windowWidth = resources.displayMetrics.widthPixels / 2
        width = windowWidth / 5 * 3
        leftWidth = windowWidth / 5 * 2
        val par = FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT)
        par.setMargins(leftWidth, 0, 0, 0)
        home_fl_view.layoutParams = par

    }

    /**
     * 初始化背景小视频Fragment
     */
    private fun initData() {
        fragments = ArrayList()
        val fragment1 = GuildFragment()
        val bundle1 = Bundle()
        bundle1.putInt("index", 1)
        fragment1.arguments = bundle1
        fragments.add(fragment1)
    }

    /**
     * 设置ViewPager的适配器和滑动监听
     */
    private fun initView() {
        videoViewPager!!.offscreenPageLimit = 1 //原为3
        videoViewPager!!.adapter = MyPageAdapter(supportFragmentManager)
    }
    /**
     * viewpager适配器
     */
    private inner class MyPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }
    }
}

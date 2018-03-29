package teamr.hackerton18.samsung.fragmentex

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_account_list.*

import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.toast
import teamr.hackerton18.samsung.fragmentex.data.AddressItem
import teamr.hackerton18.samsung.fragmentex.ui.AddressRecyclerAdapter

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AccountListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AccountListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountListFragment : Fragment(), AnkoLogger {
    val TAG = "AccountListFragment"

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    val selectedIDX : Int = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "OnCreate")
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        info("OnResume")
        var addressArray = (activity as MainActivity).keyStore.getKeyArray()
        var addressRecyclerAdapter = AddressRecyclerAdapter(context, addressArray)
        recycler_view.adapter = addressRecyclerAdapter
        recycler_view.layoutManager = LinearLayoutManager(context)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info("OnViewCreated")
        //var accountAddress = {"0x1234","0x2345","0x345"}

        (activity as MainActivity).supportActionBar?.subtitle = getString(R.string.address_list_subtitle)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var addressArray = (activity as MainActivity).keyStore.getKeyArray()
        var addressRecyclerAdapter = AddressRecyclerAdapter(context, addressArray)
        recycler_view.adapter = addressRecyclerAdapter
        recycler_view.layoutManager = LinearLayoutManager(context)
        info("recycler update")

        fabAddAccount.setOnClickListener {
            /*
            view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            toast("test")
            */
            (activity as MainActivity).ShowNewAccountFragment()
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }
    /*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }
    */
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountListFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): AccountListFragment {
            val fragment = AccountListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor

package com.example.survey_app_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider


/**
 * A simple [Fragment] subclass.
 * Use the [ResultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ResultsFragment : Fragment() {

    private lateinit var resultsTxt: TextView  // initializes the variables and the listed UI elements
    private lateinit var yesAnswersTxt: TextView
    private lateinit var noAnswersTxt: TextView
    private lateinit var yesCount: TextView
    private lateinit var noCount: TextView
    private lateinit var continueSurvey: Button
    private lateinit var resetButton: Button

    private val surveyViewModel: SurveyViewModel by lazy {  // describes how to use the viewModel but doesn't get run until it is accessed
        ViewModelProvider(requireActivity()).get(SurveyViewModel::class.java)  // take the SurveyViewModel class and create an object that is associated with requireActivity.  Associates viewModel with Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_results, container, false)


        resultsTxt = view.findViewById(R.id.results_Txt)  // ties listed UI element variables to their resource ids
        yesAnswersTxt = view.findViewById(R.id.yes_Answers_Text)
        noAnswersTxt = view.findViewById(R.id.no_Answers_Text)
        yesCount = view.findViewById(R.id.yes_Count)
        noCount = view.findViewById(R.id.no_Count)
        continueSurvey = view.findViewById(R.id.back_Button)
        resetButton = view.findViewById(R.id.resetValuesButton)

        //TODO input data for views - transferred from SurveyFragment
        yesCount.text = surveyViewModel.yesNumber.value.toString()
        noCount.text = surveyViewModel.noNumber.value.toString()

        surveyViewModel.yesNumber.observe(requireActivity()) {
            yes -> showYesNumber(yes)
        }

        continueSurvey.setOnClickListener {  // calls finish SurveyResultActivity when user clicks continueSurvey button
            parentFragmentManager.beginTransaction().replace(R.id.survey_fragment_container, SurveyFragment.newInstance(yesCount.toString(), noCount.toString()), "RESULTS").addToBackStack( "Results").commit()
        //activity?.finish()
        }

        resetButton.setOnClickListener {
            surveyViewModel.noNumber.value = 0
            surveyViewModel.yesNumber.value = 0
            parentFragmentManager.popBackStack()
        }

        //surveyViewModel.yesNumber.observe(viewLifecycleOwner, Observer {
        //    yesCount.text = surveyViewModel.yesNumber.value.toString()  // sets the yesCount TextView to the yesNumber in SurveyViewModel after converting to a string
        //})

        return view
    }

        private fun showYesNumber(yes:Int) {
            yesCount.text = yes.toString()
        }


/*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
    companion object {
    @JvmStatic
    fun newInstance() = ResultsFragment()

    }

}

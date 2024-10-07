package com.example.survey_app_fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val SURVEY_QUESTION_KEY = "survey-question-bundle-key"  // initializes constant variables for holding bundle keys
const val YES_KEY = "yes-answer-bundle-key"
const val NO_KEY = "no-answer-bundle-key"


/**
 * A simple [Fragment] subclass.
 * Use the [SurveyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SurveyFragment : Fragment() {

    private lateinit var questionView: TextView  // initializes variables for the listed textview and buttons
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var yesCount: TextView
    private lateinit var noCount: TextView
    private lateinit var resetButton: Button

    private val surveyViewModel: SurveyViewModel by lazy {  // describes how to use the viewModel but doesn't get run until it is accessed
        ViewModelProvider(requireActivity()).get(SurveyViewModel::class.java)  // take the Use ViewModelProvider to create an object that is associated with requireActivity.  Associates SurveyViewModel with Activity
    }

    override fun onCreateView(
        inflator: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflator.inflate(R.layout.fragment_survey, container, false)

        /*val currentIndex = savedInstanceState?.getInt(SURVEY_QUESTION_KEY, 0) ?: 0 // writes the value of the currentIndex to the bundle with the constant as it's key
        surveyViewModel.currentIndex.value = currentIndex // if data exists in the bundle it will be set as currentIndex otherwise a default value of 0 will be used if it does not exist or is null

        val yesNumber = savedInstanceState?.getInt(YES_KEY, 0 ) ?: 0  // writes the value of yesNumber to the bundle with the constant as it's key
        surveyViewModel.yesNumber.value = yesNumber  // if data exists in the bundle it will be set as yesNumber otherwise a default value of 0 will be used if it does not exist or is null

        val noNumber = savedInstanceState?.getInt(NO_KEY, 0 ) ?: 0  // writes the value of the noNumber to the bundle with the constant as it's key
        surveyViewModel.noNumber.value = noNumber  // if data exists in the bundle it will be set as noNumber otherwise a default value of 0 will be used if it does not exist or is null*/


        yesButton = view.findViewById(R.id.yes_Button)
        noButton = view.findViewById(R.id.no_Button)
        resetButton = view.findViewById(R.id.resultsButton)
        questionView = view.findViewById(R.id.question_Text)
        yesCount = view.findViewById(R.id.yes_Count)
        noCount = view.findViewById(R.id.no_Count)

        yesButton.setOnClickListener {  // onClickListener for yesButton
            surveyViewModel.clickYes()  // calls the clickYes function in SurveyViewModel
            yesCount.text =
                surveyViewModel.yesNumber.value.toString()  // converts yesNumber.value to a string and displays it in the yesCount TextView
        }

        noButton.setOnClickListener {
            surveyViewModel.clickNo()
            noCount.text = surveyViewModel.noNumber.value.toString()
        }

        resetButton.setOnClickListener {
            passValues()
        }

        questionView.setOnClickListener {
            surveyViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()
        return view
    }

        private fun passValues() {
            parentFragmentManager.beginTransaction().replace(R.id.survey_fragment_container, ResultsFragment.newInstance(), "RESULTS").addToBackStack( "Results").commit()
        }

        private fun updateQuestion() {  // defines updateQuestion function
            val questionTextResID = surveyViewModel.currentQuestionText  // initializes questionTextResID variable that holds the currentQuestion in the SurveyViewModel
            questionView.setText(questionTextResID)  // sets the questionView TextView to current question
            yesCount.text = surveyViewModel.yesNumber.value.toString()  // sets the yesCount TextView to the yesNumber in SurveyViewModel after converting to a string
            noCount.text = surveyViewModel.noNumber.value.toString()  // sets the noCount TextView to the noNumber in the SurveyViewModel after converting to a string
        }


    companion object {
        //const val YES_KEY = "yes-answer-bundle-key"
        //const val NO_KEY = "no-answer-bundle-key"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param yesNumber Parameter 1.
         * @param noNumber Parameter 2.
         * @return A new instance of fragment SurveyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(yesNumber: String, noNumber: String) =
            SurveyFragment().apply {
                arguments = Bundle(2).apply {
                    putString(YES_KEY, yesNumber)
                    putString(NO_KEY, noNumber)
                }
            }
    }
}

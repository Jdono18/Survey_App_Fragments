package com.example.survey_app_fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SurveyViewModel: ViewModel() {  // creates class SurveyViewModel that is a ViewModel

    var currentIndex = MutableLiveData(0)  // initializes the listed MutableLiveData: <Int> type variables
    var yesNumber = MutableLiveData(0)
    var noNumber = MutableLiveData(0)


    private val questionBank= listOf(  // initializes a questionBank variable to hold 4 questions.  Questions referenced from strings.xml file
        Question(R.string.question_1),
        Question(R.string.question_2),
        Question(R.string.question_3),
        Question(R.string.question_4)
    )

    val currentQuestionText: Int  // initializes currentQuestionText variable (int type)
        get() = questionBank[currentIndex.value!!].textResID  // gets the current question from the questionBank as a resource ID (int) of the string resource

    fun moveToNext() {  // defines the moveToNext function
        currentIndex.value =
            (currentIndex.value?.plus(1))?.rem(questionBank.size)  // increments the currentIndex to update the current question displayed in the TextView
    }

    fun clickYes(): Int {  // defines the clickYes function
        yesNumber.value = yesNumber.value!! + 1 // adds 1 to yesNumber upon button click
        return yesNumber.value!!  // returns the yesNumber variable as an Int

    }

    fun clickNo(): Int {  // defines the clickNo function
        noNumber.value = noNumber.value!! + 1  // adds 1 to the noNumber upon button click
        return noNumber.value!!  // returns noNumber variable as an Int
    }

    /*fun reset(): Pair<Int, Int> {  // defines the reset function
        yesNumber = 0  // sets yesNumber to 0
        noNumber = 0 // sets noNumber to 0
        return Pair(yesNumber,noNumber)  // returns yesNumber and noNumber variables*/


}
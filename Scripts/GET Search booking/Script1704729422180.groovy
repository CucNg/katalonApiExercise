import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.file.WatchService

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ResponseObject
import groovy.json.JsonSlurper
import com.kms.katalon.core.webservice.verification.WSResponseManager
'Send a request to retrieve the list of booking IDs'

String  firstName = GlobalVariable.firstName
String   lastName = GlobalVariable.lastName
String   id = GlobalVariable.id
//WS.sendRequestAndVerify(findTestObject('Object Repository/GET Search booking', [('firstName') : firstName, ('lastName') : lastName]))

ResponseObject response= WS.sendRequestAndVerify(findTestObject('Object Repository/GET Search booking', [('firstName') : firstName, ('lastName') : lastName]))

println 'Step2: First Name '+ firstName
println 'Step2: Last Name '+ lastName
println 'Step2: ID '+id

'The status should be 200'

WS.verifyResponseStatusCode(response, 200)

'Verify correct ID'

String responseBody = response.getResponseBodyContent()
def jsonResponse = new JsonSlurper().parseText(responseBody)
def userId= jsonResponse[0].bookingid
println 'ID in response: ' + userId
println 'response: ' + responseBody

WS.verifyEqual(userId, id)

'Verify response only contain 1 ID'

def countID=jsonResponse.collect{it.bookingid}
println countID.size()
def expectedNumber = 1
WS.verifyEqual(countID.size(),expectedNumber)





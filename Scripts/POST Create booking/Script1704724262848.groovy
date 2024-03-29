import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import java.nio.file.WatchService
import java.util.Random;
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

String firstName = CustomKeywords.'sample.Common.generateRandomString'()
String lastName = CustomKeywords.'sample.Common.generateRandomString'()


'Send request create booking'
ResponseObject response = WS.sendRequest(findTestObject('Object Repository/POST Create booking', [('firstName') : firstName, ('lastName') : lastName]))

'The status should be 200'
WS.verifyResponseStatusCode(response, 200,FailureHandling.STOP_ON_FAILURE)

'Verify response'
WS.verifyElementPropertyValue(response, 'booking.firstname',"${firstName}")
WS.verifyElementPropertyValue(response, 'booking.lastname', "${lastName}")

def responseFirstName = WS.getElementPropertyValue(response, 'booking.firstname')
def responseLastName = WS.getElementPropertyValue(response, 'booking.lastname')
def id= WS.getElementPropertyValue(response, 'bookingid')

'Send request search bookingID by first name and last name'
ResponseObject responseSearch= WS.sendRequestAndVerify(findTestObject('Object Repository/GET Search booking', [('firstName') :responseFirstName, ('lastName') :  responseLastName]))

'The status should be 200'
WS.verifyResponseStatusCode(responseSearch, 200,FailureHandling.STOP_ON_FAILURE)

'Verify correct ID'
String responseBody = responseSearch.getResponseBodyContent()
def jsonResponse = new JsonSlurper().parseText(responseBody)
def userId= jsonResponse[0].bookingid
WS.verifyEqual(userId, id)

'Verify response only contain 1 ID'
def countID=jsonResponse.collect{it.bookingid}
def expectedNumber = 1
WS.verifyEqual(countID.size(),expectedNumber)


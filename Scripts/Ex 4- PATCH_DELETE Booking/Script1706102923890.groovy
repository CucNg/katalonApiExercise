import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import groovy.json.JsonSlurper
import com.kms.katalon.core.webservice.verification.WSResponseManager


String firstName = CustomKeywords.'sample.Common.generateRandomString'()
String lastName = CustomKeywords.'sample.Common.generateRandomString'()

'Send request create booking'
ResponseObject response = WS.sendRequest(findTestObject('Object Repository/POST Create booking', [('firstName') : firstName, ('lastName') : lastName]))


'The status should be 200'
WS.verifyResponseStatusCode(response, 200,FailureHandling.STOP_ON_FAILURE)

'Verify response'
WS.verifyElementPropertyValue(response, 'booking.firstname',"${firstName}")
WS.verifyElementPropertyValue(response, 'booking.lastname', "${lastName}")

def id= WS.getElementPropertyValue(response, 'bookingid')
def firstNameUpdate = "${firstNameUpdate}"
def lastNameUpdate= "${lastNameUpdate}"

'Send request update first name and last name'
ResponseObject responseObject = WS.sendRequest(findTestObject('Object Repository/PATCH Update Booking', [('id') : id, ('fName') :firstNameUpdate, ('lName') : lastNameUpdate]))

'Verify status code'
WS.verifyResponseStatusCode(responseObject , 200,FailureHandling.STOP_ON_FAILURE)

'Verify respone'
String responseBody = responseObject.getResponseBodyContent()
def jsonResponse = new JsonSlurper().parseText(responseBody)
def firstNameRes= jsonResponse.firstname;
def lastNameRes= jsonResponse.lastname;

WS.verifyEqual(lastNameRes, lastNameUpdate)
WS.verifyEqual(firstNameRes, firstNameUpdate)

'Verify schema of response body'
String jsonPass =
"""
{
  "\$id": "https://example.com/booking.schema.json",
  "\$schema": "https://json-schema.org/draft/2020-12/schema",
  "type": "object",
  "properties": {
    "firstname": { "type": "string" },
    "lastname": { "type": "string" },
    "totalprice": { "type": "integer" },
    "depositpaid": { "type": "boolean" },
    "bookingdates": {
      "type": "object",
      "properties": {
        "checkin": { "type": "string", "format": "date" },
        "checkout": { "type": "string", "format": "date" }
      },
      "required": ["checkin", "checkout"]
    },
    "additionalneeds": { "type": "string" }
  },
  "required": ["firstname", "lastname", "totalprice", "depositpaid", "bookingdates", "additionalneeds"]
}
"""

boolean successful = WS.validateJsonAgainstSchema(responseObject,jsonPass)
WS.verifyEqual(successful, true) 

'Delete booking by ID'
ResponseObject responseDelete = WS.sendRequest(findTestObject('Object Repository/DELETE Booking', [('id') : id]))

'Verify status code'
WS.verifyResponseStatusCode(responseDelete, 201,FailureHandling.STOP_ON_FAILURE) 

'Verify response body'
 assert responseDelete.getResponseBodyContent().contains('Created') 

 'Send GET Request to find booking deleted by ID'
 ResponseObject responseGETRequest = WS.sendRequestAndVerify(findTestObject('Object Repository/GET Booking Ids', [('id') : id ]))
 
 'Verify status code'
 WS.verifyResponseStatusCode(responseGETRequest, 404 ,FailureHandling.STOP_ON_FAILURE)
 
 'Verify response body indicates the booking is no longer available.'
 assert responseGETRequest.getResponseBodyContent().contains('Not Found')
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable


'Send request create multiple bookings'
ResponseObject response = WS.sendRequest(findTestObject('Object Repository/POST Create Multiple Booking', [('firstName') : firstName, ('lastName') : lastName]))

'The status code should be 200'
WS.verifyResponseStatusCode(response, 200)

'Verrify resonse body'
WS.verifyElementPropertyValue(response, 'booking.firstname',"${firstName}")
WS.verifyElementPropertyValue(response, 'booking.lastname', "${lastName}")
def id= WS.getElementPropertyValue(response, 'bookingid')


'Send GET Request to find booking infomation by ID'
ResponseObject responseGETRequest = WS.sendRequestAndVerify(findTestObject('Object Repository/GET Booking Ids', [('id') : id ]))

'Verify status code should be 200'
WS.verifyResponseStatusCode(responseGETRequest, 200)

'Verify body contain correct data'
String responseBody = responseGETRequest.getResponseBodyContent()
def jsonResponse = new JsonSlurper().parseText(responseBody)
def firstNameRes= jsonResponse.firstname;
def lastNameRes= jsonResponse.lastname;
println 'RESPONE: '+jsonResponse 
WS.verifyEqual(lastNameRes, "${lastName}")
WS.verifyEqual(firstNameRes, "${firstName}")
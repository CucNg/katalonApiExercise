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

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable as GlobalVariable





for (def id  in GlobalVariable.ids) {
	// Thực hiện GET request cho mỗi Booking ID
	ResponseObject getResponse = WS.sendRequest(findTestObject('Object Repository/GET Bookinf ', [('bookingid') : id ]))

	// Verify status code for GET request
	WS.verifyResponseStatusCode(getResponse, 200, 'Verify Status Code is 200 for Booking ID: ' + bookingId)

	// Verify information in the response body (modify as needed)
	WS.verifyElementPropertyValue(getResponse, 'booking.firstname', "${firstName}")
	WS.verifyElementPropertyValue(getResponse, 'booking.lastname', "${lastName}")

	// Các kiểm tra khác tùy thuộc vào cấu trúc của response body

	// In thông tin chi tiết về Booking
	println 'Details for Booking ID ' + bookingId + ': ' + getResponse.getResponseBodyContent()
}


def authToken = GlobalVariable.token
RequestObject request = findTestObject('PUT Update Booking', [('id') : '1'])



def headers = ['Cookie': "token=${authToken}"]
println headers
request.setHttpHeaderProperties([headers])


// Create a List to hold TestObjectProperty objects representing headers
//List<TestObjectProperty> httpHeaders = new ArrayList<TestObjectProperty>()
//httpHeaders.add(new TestObjectProperty('Content-Type', ConditionType.equals('application/json')))
//httpHeaders.add(new TestObjectProperty('Accept', 'application/json'))
//httpHeaders.add(new TestObjectProperty('Cookie', cookie))

/*
ResponseObject response = WS.sendRequest(request)
WS.verifyResponseStatusCode(response, 200)


def authToken = GlobalVariable.token
RequestObject request = findTestObject('PUT Update Booking', [('id') : '1'])
request.modifyRequestHeader('Cookie', authToken)
*/
ResponseObject response = WS.sendRequest(request)
WS.verifyResponseStatusCode(response, 200)

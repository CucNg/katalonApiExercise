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
import org.openqa.selenium.Keys

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty

def ObjectBuilder = new RestRequestObjectBuilder()

def requestObject = ObjectBuilder
	.withRestRequestMethod("PUT")
	.withRestUrl("https://reqres.in/api/users?page=2")
	.withRestParameters([new TestObjectProperty("id", ConditionType.EQUALS, "10")])
	.withHttpHeaders([new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json")])
	.build()

def response = WS.sendRequest(requestObject)

WS.verifyResponseStatusCode(response, 200)
def authToken = GlobalVariable.token
RequestObject requestObject = findTestObject('Object Repository/PUT Update Booking', [('id') : '1'])
String cookie = 'token=' + authToken
println 'cookie = ' + cookie

List<TestObjectProperty> headersList = new ArrayList<TestObjectProperty>()

headersList.add(new TestObjectProperty('Content-Type', ConditionType QUALS, 'application/json'))
headersList.add(new TestObjectProperty('Accept', ConditionType EQUALS, 'application/json'))
headersList.add(new TestObjectProperty('Cookie', ConditionType EQUALS, cookie))

requestObject.setHttpHeaderProperties(headersList)

ResponseObject response = WS.sendRequest(requestObject)
WS.verifyResponseStatusCode(response, 200)
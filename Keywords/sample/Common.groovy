
package sample

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ResponseObject
import groovy.json.JsonSlurper
import com.kms.katalon.core.webservice.verification.WSResponseManager
import groovy.json.JsonSlurper

public class Common {

	public static JsonSlurper jsonSlurper = new JsonSlurper()

	@Keyword
	def int createNewUser(int age, String username, String password, String gender, int expectedStatus) {
		def response = WS.sendRequestAndVerify(findTestObject("Object Repository/POST a new user",
				["age": age, "username": username, "password": password, "gender": gender]))

		def jsonResponse = jsonSlurper.parseText(response.getResponseText())
		return jsonResponse.id
	}

	@Keyword
	def static void findUserById(int id, int age, String username, String password, String gender, int expectedStatus) {
		WS.sendRequestAndVerify(findTestObject('Object Repository/GET user by id', [('id') : id]))
	}
	@Keyword
	def static String  generateRandomString() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		int length = 10;
		Random random = new Random();
		StringBuilder randomString = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			char randomChar = characters.charAt(index);
			randomString.append(randomChar);
		}
		return randomString.toString();
	}
}

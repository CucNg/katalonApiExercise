<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>PUT Update Booking</name>
   <tag></tag>
   <elementGuidId>8891a989-e228-4648-868c-783fd1383000</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;firstname\&quot; : \&quot;${firstNameUpdate}\&quot;,\n    \&quot;lastname\&quot; : \&quot;${lastNameUpdate}\&quot;,\n    \&quot;totalprice\&quot; : ${totalPrice},\n    \&quot;depositpaid\&quot; : ${depo},\n    \&quot;bookingdates\&quot; : {\n        \&quot;checkin\&quot; : \&quot;${checkinDate}\&quot;,\n        \&quot;checkout\&quot; : \&quot;${checkoutDate}\&quot;\n    },\n    \&quot;additionalneeds\&quot; : \&quot;${additionalNeeds}\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;text/plain&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>f05eeedc-5ce8-4180-a18f-6c08f065304d</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Accept</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>3afcbb9a-458a-493b-9767-e9ac7f69646f</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Cookie</name>
      <type>Main</type>
      <value>${GlobalVariable.cookie}</value>
      <webElementGuid>d3f066a6-aa94-492f-b75f-65d7f3f16fb3</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.1.0</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>${GlobalVariable.baseUrl}/booking/${id}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>111</defaultValue>
      <description></description>
      <id>ccd1712f-7d84-4635-85fd-d980f37b419d</id>
      <masked>false</masked>
      <name>totalPrice</name>
   </variables>
   <variables>
      <defaultValue>true</defaultValue>
      <description></description>
      <id>57584c78-9f7a-4939-943b-df5f74c3e777</id>
      <masked>false</masked>
      <name>depo</name>
   </variables>
   <variables>
      <defaultValue>'12-1-2024'</defaultValue>
      <description></description>
      <id>ae2e9fa2-c743-426b-976f-1f12b25c2753</id>
      <masked>false</masked>
      <name>checkinDate</name>
   </variables>
   <variables>
      <defaultValue>'2-1-2024'</defaultValue>
      <description></description>
      <id>30fad81b-fcbf-485e-a2bc-3f2691a61295</id>
      <masked>false</masked>
      <name>checkoutDate</name>
   </variables>
   <variables>
      <defaultValue>'Lunch'</defaultValue>
      <description></description>
      <id>79ed3aa7-eb98-4457-94ff-6e47e9bb4786</id>
      <masked>false</masked>
      <name>additionalNeeds</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>

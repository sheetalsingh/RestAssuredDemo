#Rest Assured Demo Framework 

#How to run
1. Using maven
mvn clean test site

allure report: RestAssuredDemo/site/allure-maven-plugin/index.html		| 	incorrect, contains skipped cases as well
testng report: RestAssuredDemo/target/surefire-reports/index.html		| 	correct results


2. Normal way using testng
testng.xml



#Info:
Base URI : remain fixed respective to environment like qa,dev,prod
Headers : Headers will be read from config.properties file as they remain fixed for a particular environment 
Parameters: Parameters for GET, POST etc call will come from test case file (inputdata.csv) as they keep changing for different use case

#Use cases:
1. Can be used to hit plain GET/POST etc url and verify their status code
2. Can be run as per suite, test case id name
Note: if a common sheet include all suite cases then cases which will not run will be showing as cancelled in allure
however they will not come in testng report. Allure part is still pending, issue filed in stack overflow
http://stackoverflow.com/questions/42618877/allure-reports-showing-removed-tests-however-testng-showing-it-correctly
3. Club cases has not been implemented
Club cases are those where POST > GET > DELETE run in group


#To-do:
1. read std files and make path accessible in all project :: use file separator strategy : DONE
2. read server etc from property file :: Create a class > read all prop > added into HaspMap > Now it is globally  : DONE
3. handle a case where multiple get request are there with different parameters count - DONE
4. headers are specific to environment like qa,dev,prod need to handle that case -  : DONE
5. run case as per suite/ test case name - read from config file  - DONE
6. create dynamic data provider array. currently [2][5] is hard-coded - DONE
7. handle multiple parameters using HashMap  - DONE
8. report - allure or any other - DONE
9. club cases : run post-get-delete - 0%
10. reporting when particular cases/suite run - FAIL (reporting run all cases present in sheet giving false report)  - 50%
	removing skipped test from report - implemented iHookable working fine in TestNG, but Allure is not updating  
	Also mvn command not updating testng report, only running using testng.xml updates the testng report




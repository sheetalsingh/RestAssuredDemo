#Rest Assured Demo Framework 

#How to run
1. Using maven
mvn clean test site


2. Normal way using testng
testng.xml



To-do:
1. read std files and make path accessible in all project :: use file separator strategy : DONE
2. read server etc from property file :: Create a class > read all prop > added into HaspMap > Now it is globally  : DONE
3. handle a case where multiple get request are there with different parameters count - DONE
4. headers are specific to environment like qa,dev,prod need to handle that case -  : DONE
5. run case as per suite/ test case name - read from config file  - DONE
6. create dynamic data provider array. currently [2][5] is hard-coded - DONE
7. handle multiple parameters using HashMap  - DONE
8. report - allure or any other - DONE
9. run post-get-delete cases > handle in present case only
10. reporting when particular cases/suite run - FAIL (reporting run all cases present in sheet giving false report)  - 50%
	removing skipped test from report - implemented iHookable working fine in TestNG, but Allure is not updating  
	Also mvn command not updating testng report, only running using testng.xml updates the testng report


#Info:

Base URI : remain fixed respective to environment like qa,dev,prod
Headers : Headers will be read from config.properties file as they remain fixed for a particular environment 
Parameters: Parameters for GET, POST etc call will come from test case file (inputdata.csv) as they keep changing for different use case



#Rest Assured Demo Framework 




To-do:
1. read std files and make path accessible in all project :: use file separator strategy : DONE
2. read server etc from property file :: Create a class > read all prop > added into HaspMap > Now it is globally  : DONE
3. handle a case where multiple get request are there with different parameters count -
4. headers are specific to environment like qa,dev,prod need to handle that case -  : DONE
5. one flow testing of post-get-delete for single/multiple links 
6. create dynamic data provider array. currently [2][5] is hard-coded - DONE
7. report - allure or any other


Base URI : remain fixed respective to environment like qa,dev,prod
Headers : Headers will be read from config.properties file as they remain fixed for a particular environment 
Parameters: Parameters for GET, POST etc call will come from test case file (inputdata.csv) as they keep changing for different use case



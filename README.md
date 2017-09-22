# loanapp
RESTful web service for the loan app  
Used technology stack: Java, Spring Framework, DAO, Facade Pattern, Postgres   

REST API:   
User:  
/users/ - POST - create new User   
  
Loan:   
/loans/{userId} - POST - create new loan   
/loans/approved - GET - list all approved loans  
/loans/approved/{userId} - GET - list all approved loans by user id  
/loans/approve/{id} - GET - approve loan by loan id, by deault its false when created  

Blacklist:   
/blacklist/add/{userId} - POST - add user to blacklist  
/blacklist/remove/{userId} - GET - remove user from blacklist  
  
Explanation:   
To perform origin country resolution used this web service: http://ip-api.com/json/{ipAddress}  
Set connection timeout for 12 seconds, after that it gets default country code "lv"  
  
WebUtils:  
   getClientIpAddress(HttpServletRequest request) - method used to get users Ip address  
  
DateUtils:   
   getTimeFrameDate(Date dateNow, long timeframe) - method used to get starting date (timeframe), after that this date used in                    query to get all loan applications from timeframe date to date applied   
                   Example:  
                   20 seconds - timeframe  
                   date_applied_ > 'Fri Sep 22 12:20:32 ALMT 2017' AND date_applied_ < 'Fri Sep 22 12:20:52 ALMT 2017'
               
                                                     


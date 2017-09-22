package kz.loanapp.facades;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.loanapp.dto.LoanDto;
import kz.loanapp.dto.UserDto;
import kz.loanapp.services.BlacklistService;
import kz.loanapp.services.LoanService;
import kz.loanapp.services.UserService;
import kz.loanapp.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ardak on 9/21/17.
 */

@Service
public class LoanServiceFacade {

    private static final Long timeframeInSeconds = new Long(10);   // need to be placed in config in the future
    private static final Long numberOfApplications = new Long(2);   // need to be placed in config in the future

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private LoanService loanService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlacklistService blacklistService;

    // used ip-api.com Web Service to get country code by Ip address of the client

    public String getCountryByIp(String ip){
        // used this hardcode below to change my local ip 0:0:0:0:0:0:0:1 to 90.143.188.66 (Kazakhstan)
        if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
            ip = "90.143.188.66";
        }
        RestTemplate restTemplate = new RestTemplate();
        // set timeout to 12 seconds
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(12000);
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(12000);
        String fooResourceUrl
                = "http://ip-api.com/json/"+ip;
        try {
            ResponseEntity<String> response
                    = restTemplate.getForEntity(fooResourceUrl, String.class);
            JsonNode root = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                root = mapper.readTree(response.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonNode name = root.path("countryCode");
            return name.textValue();
        }
        catch (Exception e) {
            // returns countryCode as lv if web service not available for 12 seconds
            return "lv";
       }
    }

    public LoanDto persistLoan(LoanDto loan, String userId, HttpServletRequest request){
           if(userId!=null) {
               UserDto userDto = userService.findByUserId(userId);
               if(userDto!=null) {
                   // checking if application comes from blacklist
                   if(!blacklistService.isInBlacklist(userDto.id)) {
                       // gets ip address of client
                       String ipAdress = WebUtils.getClientIpAddress(request);
                       // gets country code
                       String countryCode = getCountryByIp(ipAdress);
                       Date date = new Date();
                       //checking if number of application exceeds the limit
                       int numberOfApp = loanService.getCountAppInTimeframe(date, timeframeInSeconds, countryCode);
                       if(numberOfApp<numberOfApplications) {
                           loan.dateApplied = date;
                           loan.countryFrom = countryCode;
                           loan.userDto = userDto;
                           loan.isApproved = false;
                           LoanDto loanDto = loanService.persistLoan(loan);
                           return loanDto;
                       }
                   }
               }
           }
         return null;
        }
    }



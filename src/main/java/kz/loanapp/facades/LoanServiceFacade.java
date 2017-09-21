package kz.loanapp.facades;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import kz.loanapp.dto.LoanDto;
import kz.loanapp.dto.UserDto;
import kz.loanapp.models.Loan;
import kz.loanapp.services.LoanService;
import kz.loanapp.services.UserService;
import kz.loanapp.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ardak on 9/21/17.
 */

@Service
public class LoanServiceFacade {

    WebUtils webUtils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private LoanService loanService;
    @Autowired
    private UserService userService;

    public String getCountryByIp(String ip){
        if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
            ip = "90.143.188.66";
        }
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://ip-api.com/json/"+ip;
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

    public LoanDto persistLoan(LoanDto loan, String userId, HttpServletRequest request){
           if(userId!=null) {
               UserDto userDto = userService.findByUserId(userId);
               if(userDto!=null) {
                   String ipAdress = WebUtils.getClientIpAddress(request);
                   String countryCode = getCountryByIp(ipAdress);
                   Date date = new Date();
                   loan.dateApplied = date;
                   loan.countryFrom = countryCode;
                   loan.userDto = userDto;
                   loan.isApproved = false;
                   LoanDto loanDto = loanService.persistLoan(loan);
                   return loanDto;
               }
           }
         return null;
        }
    }



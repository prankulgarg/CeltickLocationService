/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.controller;

import com.rc.model.UserDetail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.rc.dao.CeltickServiceDao;
import com.rc.model.GenericResponse;
import com.rc.services.CsvOperationServices;
import com.rc.services.MsisdnRegex;

import com.rc.services.OperationalServices;

/**
 *
 * @author
 */
@RestController
@EnableWebMvc
public class CeltickController {

    @Autowired
    public OperationalServices operationalServices;

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public GenericResponse registerUser(@RequestBody UserDetail userDetail) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            System.out.println("initate request register user");
            boolean checkmsisdnFlag = false;
            if (userDetail.getMsisdn() != null) {
               checkmsisdnFlag = MsisdnRegex.isValidMsisdn(userDetail.getMsisdn());

                if ((userDetail.getMsisdn().length() == 12) && userDetail.getMsisdn().startsWith("91") && checkmsisdnFlag) {
                    genericResponse = operationalServices.registerUser(userDetail);

                }
                else if(userDetail.getMsisdn().length()==10 && !userDetail.getMsisdn().startsWith("91") && checkmsisdnFlag) {
                    String newmsisdn = "91"+userDetail.getMsisdn();
                    userDetail.setMsisdn(newmsisdn);
                    genericResponse = operationalServices.registerUser(userDetail);

                }
                else {
                genericResponse.setMessage("Wrong msisdn");
                genericResponse.setStatus("Fail");
                genericResponse.setStatusCode(1);

            }
//checkmsisdnFlag = MsisdnRegex.isValidMsisdn(userDetail.getMsisdn());
            } else {
                genericResponse.setMessage("Wrong msisdn");
                genericResponse.setStatus("Fail");
                genericResponse.setStatusCode(1);

            }
        } catch (Exception e) {
        }

        return genericResponse;
    }

    @RequestMapping(value = "/deregisterDriver", method = RequestMethod.POST, headers = "Accept=application/json")
    public GenericResponse DeRegisteredDriver(@RequestBody UserDetail userDetail) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            boolean checkmsisdnFlag = false;
            if (userDetail.getMsisdn() != null) {
                checkmsisdnFlag = MsisdnRegex.isValidMsisdn(userDetail.getMsisdn());
            }
            if ((userDetail.getMsisdn().length() == 12) && userDetail.getMsisdn().startsWith("91") && checkmsisdnFlag) {
                  genericResponse = operationalServices.DeRegisteredDriver(userDetail);
            } 
            else if(userDetail.getMsisdn().length()==10 && !userDetail.getMsisdn().startsWith("91") && checkmsisdnFlag) {
                    String newmsisdn = "91"+userDetail.getMsisdn();
                    userDetail.setMsisdn(newmsisdn);
                         genericResponse = operationalServices.DeRegisteredDriver(userDetail);

            }
            else {
                genericResponse.setMessage("Wrong msisdn");
                genericResponse.setStatus("Fail");
                genericResponse.setStatusCode(1);

            }
        } catch (Exception e) {
        }

        return genericResponse;
    }

    @RequestMapping(value = "/consentRequest", method = RequestMethod.POST, headers = "Accept=application/json")
    public GenericResponse consentRequest(@RequestBody UserDetail userDetail) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            boolean checkmsisdnFlag = false;
            if (userDetail.getMsisdn() != null) {
                checkmsisdnFlag = MsisdnRegex.isValidMsisdn(userDetail.getMsisdn());
            }
              if ((userDetail.getMsisdn().length() == 12) && userDetail.getMsisdn().startsWith("91") && checkmsisdnFlag) {
              
                genericResponse = operationalServices.consentRequest(userDetail);
            
                 
              
              }
               else if(userDetail.getMsisdn().length()==10 && !userDetail.getMsisdn().startsWith("91") && checkmsisdnFlag) {
                    String newmsisdn = "91"+userDetail.getMsisdn();
                    userDetail.setMsisdn(newmsisdn);
                   genericResponse = operationalServices.consentRequest(userDetail);

            }
               
              
              else {
                genericResponse.setMessage("Wrong msisdn");
                genericResponse.setStatus("Fail");
                genericResponse.setStatusCode(1);

            }

        } catch (Exception e) {
        }

        return genericResponse;
    }

    @RequestMapping(value = "/getLocation", method = RequestMethod.POST, headers = "Accept=application/json")
    public GenericResponse getLocation(@RequestBody UserDetail userDetail) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            boolean checkmsisdnFlag = false;
            if (userDetail.getMsisdn() != null) {
                checkmsisdnFlag = MsisdnRegex.isValidMsisdn(userDetail.getMsisdn());
            }
           if ((userDetail.getMsisdn().length() == 12) && userDetail.getMsisdn().startsWith("91") && checkmsisdnFlag) {
              
                genericResponse = operationalServices.getLocation(userDetail);
            }
               else if(userDetail.getMsisdn().length()==10 && !userDetail.getMsisdn().startsWith("91") && checkmsisdnFlag) {
                    String newmsisdn = "91"+userDetail.getMsisdn();
                    userDetail.setMsisdn(newmsisdn);
                    genericResponse = operationalServices.getLocation(userDetail);

               }
           else {
                genericResponse.setMessage("Wrong msisdn");
                genericResponse.setStatus("Fail");
                genericResponse.setStatusCode(1);

            }
        } catch (Exception e) {
        }

        return genericResponse;
    }

}

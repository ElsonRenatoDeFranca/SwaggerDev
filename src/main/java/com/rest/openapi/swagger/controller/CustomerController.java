package com.rest.openapi.swagger.controller;

import com.rest.openapi.swagger.entity.Customer;
import com.rest.openapi.swagger.services.CustomerService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value="customers", tags=("customers"))
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ApiOperation(value = "${CustomerController.findAll.title}", notes = "${CustomerController.findAll.title}", nickname = "${CustomerController.findAll.nickname}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 401, message = "Authentication error"),
            @ApiResponse(code = 403, message = "Authorization error"),
            @ApiResponse(code = 500, message = "System error"),
    })
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(new ArrayList<>(customers), HttpStatus.OK);
    }

    @ApiOperation(value = "${CustomerController.findByPersonalId.title}", notes = "${CustomerController.findByPersonalId.notes}", nickname = "${CustomerController.findByPersonalId.nickname")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 401, message = "Authentication error"),
            @ApiResponse(code = 403, message = "Authorization error"),
            @ApiResponse(code = 500, message = "System error"),
    })
    @RequestMapping(method=RequestMethod.GET,value="/customers/{personalId}")
    public ResponseEntity<Customer> findByPersonalId(@PathVariable String personalId) {
        Customer customer = customerService.findCustomerByPersonalId(personalId);

        if(customer != null){
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @RequestMapping(method=RequestMethod.POST,value="/customers")
    @ApiOperation(value = "${CustomerController.save.title}", notes = "${CustomerController.save.notes}", nickname = "${CustomerController.save.nickname")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Authentication error"),
            @ApiResponse(code = 403, message = "Authorization error"),
            @ApiResponse(code = 500, message = "System error"),
    })
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        try {
            this.customerService.save(customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method=RequestMethod.DELETE,value="/customers/{personalId}")
    @ApiOperation(value = "${CustomerController.delete.title}", notes = "${CustomerController.delete.notes}", nickname = "${CustomerController.delete.nickname")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Authentication error"),
            @ApiResponse(code = 403, message = "Authorization error"),
            @ApiResponse(code = 500, message = "System error"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "personalId", value = "${CustomerController.delete.personalId}")
    })
    public ResponseEntity<Customer> delete(@PathVariable String personalId) {
        try {
            this.customerService.delete(personalId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "${CustomerController.update.title}", notes = "${CustomerController.update.notes}", nickname = "${CustomerController.update.nickname")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Authentication error"),
            @ApiResponse(code = 403, message = "Authorization error"),
            @ApiResponse(code = 500, message = "System error"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "personalId", value = "${CustomerController.update.personalId}"),
            @ApiImplicitParam(name = "customer", value = "${CustomerController.update.customer}")
    })
    @RequestMapping(method=RequestMethod.PUT,value="/customers/{personalId}")
    public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable String personalId){
        try {
            this.customerService.update(customer, personalId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

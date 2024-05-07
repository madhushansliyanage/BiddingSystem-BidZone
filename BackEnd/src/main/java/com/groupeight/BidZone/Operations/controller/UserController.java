package com.groupeight.BidZone.Operations.controller;

import com.groupeight.BidZone.Operations.dto.ResponseDTO;
import com.groupeight.BidZone.Operations.dto.UserDTO;
import com.groupeight.BidZone.Operations.service.ListingService;
import com.groupeight.BidZone.Operations.service.UserService;
import com.groupeight.BidZone.Operations.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    //private static final Logger logger = Logger.getLogger(com.example.SmartApparel.ZZZZZZZ.controllers.ProfileController.class.getName());

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private UserService userService;

    @Autowired
    private ListingService listingService;

    @GetMapping(value = "/view")
    public ResponseEntity getAllUsers() {
        List<UserDTO> userDTOList = userService.findAll();
        try {

            if (userDTOList.isEmpty()) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of users found");
            }else{
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched all users");
            }
            responseDTO.setContent(userDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch(Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle exceptions
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/view/{id}")
    public ResponseEntity getUser(@PathVariable int id) {
        try{

            UserDTO userDTO = userService.findUserById(id);

            if(userDTO==null){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of the user id");
            }else{
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched the user details");
            }
            // Set response content and return
            responseDTO.setContent(userDTO);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle errors
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/view/{username}/{password}")
    public ResponseEntity getUser(@PathVariable String username, @PathVariable String password) {
        try{
            UserDTO userDTO = userService.findUserByUsernameAndPassword(username,password);

            if(userDTO==null){
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No records of the user id");
            }else{
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully fetched the user details");
            }
            // Set response content and return
            responseDTO.setContent(userDTO);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle errors
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addAttendance(@RequestBody UserDTO userDTO){
        try{
            String response = userService.save(userDTO);

            // Check the response and set appropriate message
            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully added profile");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("profile id already exists");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle errors
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAttendance(@PathVariable int id){
        try{
            String response = userService.delete(id);

            if(response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully deleted the user");
                responseDTO.setContent(id);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such user");
                responseDTO.setContent("User Id: "+id);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle errors
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateAttendance(@RequestBody UserDTO userDTO){
        try{
            String response = userService.update(userDTO);

            if (response.equals(VarList.RSP_SUCCESS)){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully deleted the user");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Not found such an user");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());

            // Handle errors
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@PostMapping(value="/profile")
    public ModelAndView postProfile(HttpServletRequest request, @Valid @ModelAttribute UserForm userForm) {
        try {
            User sessionUser = (User) request.getSession().getAttribute("user");
            if (sessionUser == null) {
                return new ModelAndView("redirect:/");
            }
            sessionUser.setUsername(userForm.getUsername());
            sessionUser.setPassword(userForm.getPassword());
            sessionUser.setName(userForm.getName());
            sessionUser.setSurname(userForm.getSurname());
            sessionUser.setAge(userForm.getAge());
            sessionUser.setGender(userForm.getGender());
            sessionUser.setLikedCategories(userForm.getLikedCategories());
            sessionUser = userService.save(sessionUser);
            request.getSession().setAttribute("user", sessionUser);
            return new ModelAndView("redirect:/profile");
        } catch (Exception e) {
            //logger.info("Exception: " + e.getMessage());
            System.out.println("Exception: " + e.getMessage());
            return new ModelAndView("redirect:/error");
        }
    }*/

}

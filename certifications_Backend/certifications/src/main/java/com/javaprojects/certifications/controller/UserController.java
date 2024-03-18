package com.javaprojects.certifications.controller;

import com.javaprojects.certifications.model.EmpCertification;
import com.javaprojects.certifications.repos.UserRepo;
import com.javaprojects.certifications.model.User;
import com.javaprojects.certifications.service.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 @RestController
 @CrossOrigin
public class UserController {

	 
	    private final UserRepo repo;
	    private final SearchRepo srepo;
	    private static final String ERROR = "error :"; 
	    
	    @Autowired
	    public UserController( UserRepo repo, SearchRepo srepo) {
	      this.repo = repo;
	      this.srepo = srepo;
	    }

     @GetMapping("/")
     public String serve(){
         return "Server started";
     }

    @GetMapping("/users")
    public List<User> getUser(){
        return repo.findAll();
    }

    @GetMapping("users/{text}")
    public List<User> searchUser(@PathVariable String text){
         return srepo.findByText(text);
    }

    
    /**
     * Added by: Babitha Sujana
     * Date created: 11/26/23
     * Creates a new user if the provided email address does not already exist in the repository.
     * If the email address already exists, returns a bad request response with an appropriate message.
     * Upon successful creation, returns a response indicating successful user creation.
     *
     * @param user The user object containing information about the user to be created.
     * @return ResponseEntity containing status and message indicating the result of the operation.
     */
    @PostMapping("/create-user")
     public ResponseEntity<String> createUser(@RequestBody User user){
        try{
            User userExists = repo.findByEmail(user.getEmail());
            if(userExists!=null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( "User already exists");
            }
            repo.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("user created : "+user.toString());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR+ e);
        }
    }

    
    /**
     * Added by: Babitha Sujana
     * Date created: 11/29/23
     * Updates user certifications by adding a new certification.
     * Returns appropriate HTTP statuses based on the outcome.
     * 
     */
    @PostMapping("/update-user/{email}")
     public ResponseEntity<String> updateUser(@PathVariable("email") String email,@RequestBody EmpCertification newCert){
         try{
             User userExists = repo.findByEmail(email);
             if(userExists==null){
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error updating the user ");
             }
             EmpCertification certExists = userExists.getCertifications().stream().
                                                filter(cert->cert.getCid().equals(newCert.getCid()))
                                                    .findFirst().orElse(null);
             if(certExists!=null){
                 return ResponseEntity.status(HttpStatus.CONFLICT).body("Certification already exists");
             }
             userExists.getCertifications().add(newCert);
             repo.save(userExists);
             return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"user updated\"}");
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR+ e);
         }
    }

    
    /**
     * Added by: Babitha Sujana
     * Date created: 11/29/23
     * Updates the status and dates of a certification for the user identified by the provided email.
     * 
     */
    @PutMapping("/update-status/{email}")
    public ResponseEntity<String> updateCertStatus(@PathVariable("email") String email,@RequestBody EmpCertification certData){
         try{
             User userExists = repo.findByEmail(email);
             if(userExists==null){
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error updating the user ");
             }
             EmpCertification certExists = userExists.getCertifications().stream().
                     filter(cert->cert.getCid().equals(certData.getCid()))
                     .findFirst().orElse(null);
             if(certExists==null){
                 return ResponseEntity.status(HttpStatus.CONFLICT).body("Certification already exists");
             }
             certExists.setStatus(certData.getStatus());
             certExists.setStateDate(certData.getStateDate());
             certExists.setEndDate(certData.getEndDate());
             repo.save(userExists);
             return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"certificate status updated\"}");
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR+ e);
         }
    }

    /**
     * Added by: Babitha Sujana
     * Date created: 12/05/23
     * Removes a certificate associated with a user based on the provided email and certificate ID.
     * Upon successful removal, saves the updated user information and returns an OK response.
     * In case of any unexpected error, returns an internal server error response.
     *
     * 
     */
    @DeleteMapping("/remove-cert")
     public ResponseEntity<String> removeCertificate(@RequestParam("email") String email, @RequestParam("cid") String cid){
         try{
             User userExists = repo.findByEmail(email);
             if(userExists==null){
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"User doesn't exists\"}");
             }
             userExists.getCertifications().removeIf(cert->cert.getCid().equals(cid));
             repo.save(userExists);
             return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Certificate removed successfully\"}");
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR+ e);
         }
    }

}

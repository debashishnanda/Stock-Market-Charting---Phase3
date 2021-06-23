package com.stockmarket.charting.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.mail.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import com.stockmarket.charting.entity.User;
import com.stockmarket.charting.payload.request.LoginRequest;
import com.stockmarket.charting.payload.request.SignupRequest;
import com.stockmarket.charting.payload.response.JwtResponse;
import com.stockmarket.charting.payload.response.MessageResponse;
import com.stockmarket.charting.dao.UserRepository;
import com.stockmarket.charting.security.jwt.JwtUtils;
import com.stockmarket.charting.security.service.UserDetailsImpl;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().toString();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getMobileNumber(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getRole(),
                signUpRequest.getEmail(),
                signUpRequest.getMobileNumber()
        );

        userRepository.save(user);
        try{
            sendEmail(user);
        } catch (Exception e){

        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public void sendEmail(User user) throws AddressException, MessagingException {


        final String username = "";
        final String password = "";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(""));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail())
            );
            message.setSubject("User confirmation email");

            message.setContent(
                    "<h1><a href =\"http://localhost:8085/api/auth/confirm/" + user.getId() + "/\"> Click to confirm </a></h1>",
                    "text/html");
            Transport.send(message);

            System.out.println("sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @GetMapping(value="/confirm/{userid}")
    public String confirmUser(@PathVariable Long userid) {
        Optional<User> user =   userRepository.findById(userid);

        user.get().setConfirmed(true);
        userRepository.save(user.get());
        return "User confirmed " +user.get().getUsername();
    }
}
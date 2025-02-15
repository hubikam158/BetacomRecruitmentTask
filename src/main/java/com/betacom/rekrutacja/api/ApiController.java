package com.betacom.rekrutacja.api;

import com.betacom.rekrutacja.dto.ItemGetResponse;
import com.betacom.rekrutacja.dto.ItemRequest;
import com.betacom.rekrutacja.dto.LoginResponse;
import com.betacom.rekrutacja.dto.UserRequest;
import com.betacom.rekrutacja.entity.Item;
import com.betacom.rekrutacja.entity.User;
import com.betacom.rekrutacja.error.ErrorResponse;
import com.betacom.rekrutacja.security.TokenService;
import com.betacom.rekrutacja.utils.Descriptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
@Tag(name = "Api")
public class ApiController {

    private UserService userService;
    private ItemService itemService;
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;

    @Operation(description = Descriptions.AUTHENTICATING)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))}),
            @ApiResponse(responseCode = "401", description = Descriptions.UNSUCCESSFUL_AUTHENTICATION, content = @Content) })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponse getToken(@RequestBody UserRequest request){
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
        authenticationManager.authenticate(authentication);
        String token = tokenService.generateToken(request.getLogin());
        log.info("Token granted to user : {}", request.getLogin());
        return new LoginResponse(token);
    }

    @Operation(description = Descriptions.REGISTERING)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = Descriptions.SUCCESSFUL_REGISTERING, content = @Content),
            @ApiResponse(responseCode = "409", description = Descriptions.USER_ALREADY_EXISTS,
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}) })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/register")
    public void register(@RequestBody UserRequest request) {
        userService.addUser(request);
    }

    @Operation(description = Descriptions.ITEM_CREATING)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = Descriptions.SUCCESSFUL_CREATING, content = @Content),
            @ApiResponse(responseCode = "401", description = Descriptions.UNSUCCESSFUL_AUTHENTICATION, content = @Content) })
    @SecurityRequirement(name = "Bearer Authentication")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/items")
    public void createItem(@RequestBody ItemRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByLogin(userDetails.getUsername());
        itemService.addItem(new Item(request.getName(), user));
    }

    @Operation(description = Descriptions.ITEM_GETTING)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ItemGetResponse.class)))}),
            @ApiResponse(responseCode = "401", description = Descriptions.UNSUCCESSFUL_AUTHENTICATION, content = @Content) })
    @SecurityRequirement(name = "Bearer Authentication")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/items")
    public List<ItemGetResponse> getItems(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByLogin(userDetails.getUsername());
        return itemService.getItemsByUser(user);
    }
}

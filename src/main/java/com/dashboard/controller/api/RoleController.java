// package com.dashboard.controller.api;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;

// import com.dashboard.entity.Role;
// import com.dashboard.model.RoleDto;
// import com.dashboard.service.RoleService;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;

// @RestController
// @Tag(name = "ROLE")
// @RequestMapping(value = "/api")
// public class RoleController {

//     @Autowired
//     private RoleService roleService;

//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     @Operation(summary = "Get Roles", tags = { "ROLE" })
//     @RequestMapping(value = "/roles", method = RequestMethod.GET)
//     public ResponseEntity<List<Role>> getAllRoles() {
//         List<Role> roles = roleService.getAllRoles();
//         return new ResponseEntity<>(roles, HttpStatus.OK);
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @Operation(summary = "Add Roles", tags = { "ROLE" })
//     @PostMapping(value = "/roles")
//     public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
//         return new ResponseEntity<>(roleService.addRole(roleDto), HttpStatus.CREATED);
//     }

//     @PreAuthorize("hasRole('ADMIN')")
//     @Operation(summary = "Update Roles", tags = { "ROLE" })
//     @PutMapping(value = "/roles/{roleName}")
//     public ResponseEntity<Role> updateRole(@PathVariable(required = true) String roleName, @RequestBody RoleDto roleDto) {
//         return new ResponseEntity<>(roleService.updateRole(roleName, roleDto.getName()), HttpStatus.ACCEPTED);
//     }
// }

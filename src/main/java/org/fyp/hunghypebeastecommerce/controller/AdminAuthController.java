package org.fyp.hunghypebeastecommerce.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.fyp.hunghypebeastecommerce.dto.ResponseObject;
import org.fyp.hunghypebeastecommerce.dto.admin.AdminLoginRequest;
import org.fyp.hunghypebeastecommerce.dto.admin.AdminLoginResponse;
import org.fyp.hunghypebeastecommerce.entity.AdminUser;
import org.fyp.hunghypebeastecommerce.service.AdminAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject<AdminLoginResponse>> login(
            @RequestBody AdminLoginRequest request,
            HttpSession session
    ) {
        AdminUser admin = adminAuthService.authenticate(request.getUsername(), request.getPassword());
        adminAuthService.recordLogin(admin.getId());

        session.setAttribute("adminId", admin.getId());
        session.setAttribute("username", admin.getUsername());

        AdminLoginResponse response = AdminLoginResponse.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .fullName(admin.getFullName())
                .build();

        return ResponseEntity.ok(ResponseObject.success("Login successful", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseObject<Void>> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(ResponseObject.success("Logout successful", null));
    }

    @GetMapping("/check")
    public ResponseEntity<ResponseObject<Long>> checkSession(HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            return ResponseEntity.status(401)
                    .body(ResponseObject.error("Not authenticated"));
        }
        return ResponseEntity.ok(ResponseObject.success("Session valid", adminId));
    }
}

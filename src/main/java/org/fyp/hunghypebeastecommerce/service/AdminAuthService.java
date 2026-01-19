package org.fyp.hunghypebeastecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fyp.hunghypebeastecommerce.entity.AdminUser;
import org.fyp.hunghypebeastecommerce.exception.CustomException;
import org.fyp.hunghypebeastecommerce.exception.ErrorCode;
import org.fyp.hunghypebeastecommerce.repository.AdminUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminAuthService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public AdminUser authenticate(String username, String password) {
        AdminUser admin = adminUserRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INPUT));

        if (!passwordEncoder.matches(password, admin.getPasswordHash())) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        return admin;
    }

    @Transactional
    public void recordLogin(Long adminId) {
        AdminUser admin = adminUserRepository.findById(adminId)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INPUT));
        admin.setLastLoginAt(LocalDateTime.now());
        adminUserRepository.save(admin);
    }
}

package com.example.backend.util;

import com.example.backend.contant.EPermission;
import com.example.backend.model.Permission;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationPermissionUtil {

    public static boolean validatePermission(HttpServletRequest request, List<EPermission> requiredPermission) {
        HttpSession session = request.getSession();

        List<String> permissionStrings = (List<String>) session.getAttribute("permissions");
        List<EPermission> permissions = permissionStrings.stream()
                .map(EPermission::valueOf)
                .toList();
        boolean result = true;

        if (permissions.isEmpty()) {
            result = false;
            return result;
        }

        for (EPermission p : requiredPermission) {
            if (!permissions.contains(p)) {
                result = false;
                return result;
            }
        }
        return result;
    }
}

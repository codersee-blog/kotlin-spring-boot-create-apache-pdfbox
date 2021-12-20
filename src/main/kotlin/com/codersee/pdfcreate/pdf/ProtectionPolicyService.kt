package com.codersee.pdfcreate.pdf

import org.apache.pdfbox.pdmodel.encryption.AccessPermission
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy
import org.springframework.stereotype.Service

@Service
class ProtectionPolicyService {

    companion object {
        // either 40, 128 or 256
        private const val ENCRYPTION_KEY_LENGTH = 256
        private const val OWNER_PASSWORD = "owner"
        private const val USER_PASSWORD = "user"
    }

    fun generateStandardProtectionPolicy(): StandardProtectionPolicy {
        val accessPermission = AccessPermission()
        accessPermission.setCanPrint(false)
        accessPermission.setCanExtractContent(false)
        return getStandardProtectionPolicy(accessPermission)
    }

    private fun getStandardProtectionPolicy(accessPermission: AccessPermission): StandardProtectionPolicy {
        val protectionPolicy = StandardProtectionPolicy(OWNER_PASSWORD, USER_PASSWORD, accessPermission)
        protectionPolicy.encryptionKeyLength = ENCRYPTION_KEY_LENGTH
        return protectionPolicy
    }
}
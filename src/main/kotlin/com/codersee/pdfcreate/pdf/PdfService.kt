package com.codersee.pdfcreate.pdf

import com.codersee.pdfcreate.data.PeopleDataProvider
import org.apache.pdfbox.pdmodel.PDDocument
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class PdfService(
    private val firstPageGenerator: FirstPageGenerator,
    private val tablePageGenerator: TablePageGenerator,
    private val protectionPolicyService: ProtectionPolicyService
) {

    fun generate(): ByteArray {
        val document = PDDocument()

        val people = PeopleDataProvider.generateTestData()
        val firstPage = firstPageGenerator.generate(document)
        val tablePage = tablePageGenerator.generate(document, people)
        val protectionPolicy = protectionPolicyService.generateStandardProtectionPolicy()

        document.addPage(firstPage)
        document.addPage(tablePage)
        document.protect(protectionPolicy)

        val byteArray = generateByteArray(document)
        document.close()
        return byteArray
    }

    private fun generateByteArray(document: PDDocument): ByteArray {
        val outputStream = ByteArrayOutputStream()
        document.save(outputStream)
        return outputStream.toByteArray()
    }

}
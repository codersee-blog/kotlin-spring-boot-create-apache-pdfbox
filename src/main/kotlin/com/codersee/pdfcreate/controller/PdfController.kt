package com.codersee.pdfcreate.controller

import com.codersee.pdfcreate.pdf.PdfService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
class PdfController(
    private val pdfService: PdfService
) {

    @PostMapping("/api/report")
    fun getAllUsersCsvExport(response: HttpServletResponse): ResponseEntity<ByteArray> {
        val report = pdfService.generate()

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.pdf\"")
            .body(report)
    }
}
package com.codersee.pdfcreate.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.springframework.stereotype.Component

@Component
class FirstPageGenerator {

    companion object {
        private const val FONT_SIZE = 50
        private const val TITLE = "Example test report"

        private val FONT = PDType1Font.TIMES_BOLD
    }

    fun generate(document: PDDocument): PDPage {
        val page = PDPage()

        val titleWidth = calculateTitleWidth()
        val horizontalOffset = calculateHorizontalOffset(page, titleWidth)
        val verticalOffset = calculateVerticalOffset(page)

        val contentStream = PDPageContentStream(document, page)
        editContent(contentStream, horizontalOffset, verticalOffset)

        return page
    }

    private fun editContent(
        contentStream: PDPageContentStream,
        horizontalOffset: Float,
        verticalOffset: Float
    ) {
        contentStream.beginText()
        contentStream.setFont(FONT, FONT_SIZE.toFloat())
        contentStream.newLineAtOffset(horizontalOffset, verticalOffset)
        contentStream.showText(TITLE)
        contentStream.endText()
        contentStream.close()
    }

    private fun calculateVerticalOffset(page: PDPage): Float =
        (page.mediaBox.height) / 2

    private fun calculateHorizontalOffset(page: PDPage, titleWidth: Float): Float =
        (page.mediaBox.width - titleWidth) / 2

    private fun calculateTitleWidth(): Float =
        FONT.getStringWidth(TITLE) / 1000 * FONT_SIZE
}
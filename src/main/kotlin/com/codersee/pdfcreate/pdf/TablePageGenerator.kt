package com.codersee.pdfcreate.pdf

import be.quodlibet.boxable.BaseTable
import be.quodlibet.boxable.Row
import com.codersee.pdfcreate.data.PeopleDataProvider
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.springframework.stereotype.Component

@Component
class TablePageGenerator {

    companion object {
        private const val MARGIN = 30f
        private const val TOP_MARGIN = 30f
        private const val BOTTOM_MARGIN = 30f

        // specified in px
        private const val ROW_HEIGHT = 20f

        // % of table width
        private const val CELL_WIDTH = 20f

        private val TABLE_WIDTH = PDRectangle.A4.width - 60
        private val Y_TABLE_START = PDRectangle.A4.height - 100

    }

    fun generate(document: PDDocument, people: List<PeopleDataProvider.Person>): PDPage {
        val page = PDPage()

        val contentStream = PDPageContentStream(document, page)
        contentStream.beginText()

        val table = createTable(document, page)
        addHeaderRow(table)
        addDataRows(people, table)
        table.draw()

        contentStream.endText()
        contentStream.close()

        return page
    }

    private fun createTable(document: PDDocument, page: PDPage): BaseTable =
        BaseTable(
            Y_TABLE_START, Y_TABLE_START, TOP_MARGIN, BOTTOM_MARGIN, TABLE_WIDTH,
            MARGIN, document, page, true, true
        )

    private fun addHeaderRow(table: BaseTable) {
        val row: Row<PDPage> = table.createRow(ROW_HEIGHT)

        createBoldHeaderCell(row, "Number")
        createBoldHeaderCell(row, "First Name")
        createBoldHeaderCell(row, "Last Name")
        createBoldHeaderCell(row, "Email")
        createBoldHeaderCell(row, "Age")
        table.addHeaderRow(row)
    }

    private fun createBoldHeaderCell(row: Row<PDPage>, value: String) {
        row
            .createCell(CELL_WIDTH, value)
            .font = PDType1Font.TIMES_BOLD
    }

    private fun addDataRows(
        people: List<PeopleDataProvider.Person>,
        table: BaseTable
    ) {
        people.forEachIndexed { index, person ->
            val row = table.createRow(ROW_HEIGHT)
            createCell(row, (index + 1).toString())
            createCell(row, person.firstName)
            createCell(row, person.lastName)
            createCell(row, person.email)
            createCell(row, person.age.toString())
        }
    }

    private fun createCell(row: Row<PDPage>, value: String) {
        row
            .createCell(value)
            .font = PDType1Font.TIMES_ROMAN
    }
}
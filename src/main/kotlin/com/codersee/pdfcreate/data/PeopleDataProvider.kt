package com.codersee.pdfcreate.data

object PeopleDataProvider {

    fun generateTestData(): List<Person> =
        listOf(
            Person("John", "Doe", "mail1@codersee.com", 22),
            Person("Emma", "Smith", "mail2@codersee.com", 20),
            Person("Wayne", "Johnson", "mail3@codersee.com", 30),
            Person("Robert", "Robertson", "mail4@codersee.com", 41),
            Person("Sophia", "Miller", "mail5@codersee.com", 27),
            Person("Adam", "Williams", "mail6@codersee.com", 52)
        )

    data class Person(
        val firstName: String,
        val lastName: String,
        val email: String,
        val age: Int
    )
}
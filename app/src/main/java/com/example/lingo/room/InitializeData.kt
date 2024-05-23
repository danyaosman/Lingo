import com.example.lingo.repository.Repository
import com.example.lingo.room.Course
import com.example.lingo.room.Question
import kotlinx.coroutines.runBlocking

fun initializeData(repository: Repository) = runBlocking {
//    // Create courses
//    val spanishCourse = Course(name = "Spanish")
//    val frenchCourse = Course(name = "French")
//
//    // Retrieve the inserted courses with IDs
//    val courses = listOf(spanishCourse, frenchCourse)
//
//    // Questions for Spanish and French courses
//    val spanishQuestions = listOf(
//        listOf(
//            Question(question = "¿Qué significa 'casa' en inglés?", option1 = "Home", option2 = "Car", option3 = "Tree", option4 = "Book", answer = "Home", levelId = 1, courseId = 1),
//            Question(question = "¿Qué palabra en español se traduce como 'cat' en inglés?", option1 = "Perro", option2 = "Gato", option3 = "Pájaro", option4 = "Pez", answer = "Gato", levelId = 1, courseId = 1)
//        ),
//        listOf(
//            Question(question = "¿Qué significa 'amarillo' en inglés?", option1 = "Yellow", option2 = "Blue", option3 = "Red", option4 = "Green", answer = "Yellow", levelId = 2, courseId = 1),
//            Question(question = "¿Cómo se dice 'apple' en español?", option1 = "Plátano", option2 = "Manzana", option3 = "Naranja", option4 = "Uva", answer = "Manzana", levelId = 2, courseId = 1)
//        ),
//    )
//
//    val frenchQuestions = listOf(
//        listOf(
//            Question(question = "Que signifie 'maison' en anglais?", option1 = "House", option2 = "Car", option3 = "Tree", option4 = "Book", answer = "House", levelId = 1, courseId = 2),
//            Question(question = "Quel mot en français se traduit par 'chat' en anglais?", option1 = "Chien", option2 = "Chat", option3 = "Oiseau", option4 = "Poisson", answer = "Chat", levelId = 1, courseId = 2)
//        ),
//        listOf(
//            Question(question = "Que signifie 'jaune' en anglais?", option1 = "Yellow", option2 = "Blue", option3 = "Red", option4 = "Green", answer = "Yellow", levelId = 2, courseId = 2),
//            Question(question = "Comment dit-on 'pomme' en français?", option1 = "Banane", option2 = "Pomme", option3 = "Orange", option4 = "Raisin", answer = "Pomme", levelId = 2, courseId = 2)
//        ),
//    )
//
//    val allQuestions = listOf(spanishQuestions, frenchQuestions)
//
}


fun initCourseList(repo: Repository): List<Course> = runBlocking {
    if (repo.getCourses().isEmpty()) { // Check if the database is empty
        val courses = listOf(
            Course(name = "Spanish"),
            Course(name = "French"),
            Course(name = "Turkish"),
            Course(name = "Greek"),
            Course(name = "Chinese")
        )

        courses.forEach { course ->
            repo.insertCourse(course)
        }

        courses
    } else {
        emptyList()
    }
}


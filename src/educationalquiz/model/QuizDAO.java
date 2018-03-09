package educationalquiz.model;

import java.util.List;

/**
 * QuizDAO represents an interface of quizzes
 * @author hugob
 */
public interface QuizDAO {

    public boolean insertQuiz(Quiz quiz);

    public List<Quiz> selectQuiz();

    public boolean removeQuiz(Quiz quiz);

    public boolean updateQuiz(Quiz quiz, String category, String name);

    public boolean updateQuiz(Quiz quiz);

}

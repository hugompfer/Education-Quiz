package educationalquiz.model;


import educationalquiz.model.Quiz;
import java.util.List;

/**
 *
 * @author hugob
 */
public interface QuizDAO {

    public boolean insertQuiz(Quiz quiz);

    public boolean updateQuiz(Quiz lastQuiz,Quiz newQuiz);

    public List<Quiz> selectQuiz();

     public boolean removeQuiz(Quiz quiz);
     
   
}

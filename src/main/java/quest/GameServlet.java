package quest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Новая версия
 * GameServlet - класс, обрабатывающий логику игры.
 * Использует HttpSession для хранения состояния между запросами.
 * Проверяет ввод пользователя, устанавливает результат в сессию.
 * Используем sendRedirect для предотвращения повторной отправки формы.
 */

@WebServlet("/game")
public class GameServlet extends HttpServlet {

}

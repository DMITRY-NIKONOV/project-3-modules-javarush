package quest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quest.model.QuestStep;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestServiceTest {

    private static final String EXPECTED_STEP_START_ID = "start";
    private static final String EXPECTED_STEP_START_WIN = "win";
    private static final String EXPECTED_STEP_TEXT = "Ты потерял память. Принять вызов НЛО?";
    private static final String EXPECTED_STEP_OPTION1 = "Принять вызов";
    private static final String EXPECTED_STEP_OPTION2 = "Отклонить вызов";
    private static final String EXPECTED_NEXT_STEP_ID1_BRIDGE = "bridge";
    private static final String EXPECTED_NEXT_STEP_ID2_LOSE1 = "lose1";
    private static final String EXPECTED_NEXT_STEP_LOSE2 = "lose2";
    private static final String EXPECTED_NEXT_STEP_LOSE3 = "lose3";
    private static final String EXPECTED_NEXT_STEP_CAPTAIN = "captain";


    private QuestService questService;

    @BeforeEach
    void setUp() {
        questService = new QuestService();
    }


    // Получение начального шага
    @Test
    @DisplayName("Тест получения начального шага")
    void testGetStartStep() {
        QuestStep step = questService.getStep(EXPECTED_STEP_START_ID);

        assertNotNull(step, "Шаг 'start' не должен быть null");
        assertEquals(EXPECTED_STEP_START_ID, step.getId(), "ID шага должен быть '" + EXPECTED_STEP_START_ID + "'");
        assertEquals(EXPECTED_STEP_TEXT, step.getText(), "Должна быть строка: '" + EXPECTED_STEP_TEXT + "'");
        assertEquals(EXPECTED_STEP_OPTION1, step.getOption1(), "Должен быть: '" + EXPECTED_STEP_OPTION1 + "'");
        assertEquals(EXPECTED_STEP_OPTION2, step.getOption2(), "Должен быть: '" + EXPECTED_STEP_OPTION2 + "'");
        assertEquals(EXPECTED_NEXT_STEP_ID1_BRIDGE, step.getNextStepId1(), "Должен быть: '" + EXPECTED_NEXT_STEP_ID1_BRIDGE + "'");
        assertEquals(EXPECTED_NEXT_STEP_ID2_LOSE1, step.getNextStepId2(), "Должен быть: '" + EXPECTED_NEXT_STEP_ID2_LOSE1 + "'");
    }

    // Получение шага с победой
    @Test
    @DisplayName("Тест получения шага с победой")
    void testGetWinStep() {
        QuestStep step = questService.getStep(EXPECTED_STEP_START_WIN);

        assertNotNull(step, "Шаг " + EXPECTED_STEP_START_WIN + " не должен быть null");
        assertTrue(step.getText().contains("Победа"), "Текст должен содержать слово 'победа'");
        assertNull(step.getOption1(), "Не должно быть вариантов ответа");
        assertNull(step.getOption2(), "Не должно быть вариантов ответа");
    }

    // Получение несуществующего шага
    @Test
    @DisplayName("Тест получения несуществующего шага")
    void testGetNonExistentStep() {
        QuestStep step = questService.getStep("non-existent");

        assertNull(step, "Несуществующий шаг должен возвращать null");
    }

    // Проверка финального шага
    @Test
    @DisplayName("Тест проверки финального шага")
    void testIsFinalStep() {
        assertTrue(questService.isFinalStep(EXPECTED_STEP_START_WIN), EXPECTED_STEP_START_WIN + " должен быть финальным шагом");
        assertTrue(questService.isFinalStep(EXPECTED_NEXT_STEP_ID2_LOSE1), EXPECTED_NEXT_STEP_ID2_LOSE1 + " должен быть финальным шагом");
        assertTrue(questService.isFinalStep(EXPECTED_NEXT_STEP_LOSE2), EXPECTED_NEXT_STEP_LOSE2 + " должен быть финальным шагом");
        assertTrue(questService.isFinalStep(EXPECTED_NEXT_STEP_LOSE3), EXPECTED_NEXT_STEP_LOSE3 + " должен быть финальным шагом");

        assertFalse(questService.isFinalStep(EXPECTED_STEP_START_ID), EXPECTED_STEP_START_ID + " не должен быть финальным шагом");
        assertFalse(questService.isFinalStep(EXPECTED_NEXT_STEP_ID1_BRIDGE), EXPECTED_NEXT_STEP_ID1_BRIDGE + " не должен быть финальным шагом");
        assertFalse(questService.isFinalStep(EXPECTED_NEXT_STEP_CAPTAIN), EXPECTED_NEXT_STEP_CAPTAIN + " не должен быть финальным шагом");
    }

    // Логика ветвления
    @Test
    @DisplayName("Тест логики ветвления")
    void testQuestBranching() {
        QuestStep start = questService.getStep(EXPECTED_STEP_START_ID);
        QuestStep bridge = questService.getStep(EXPECTED_NEXT_STEP_ID1_BRIDGE);
        QuestStep captain = questService.getStep(EXPECTED_NEXT_STEP_CAPTAIN);
        QuestStep win = questService.getStep(EXPECTED_STEP_START_WIN);

        assertNotNull(start);
        assertNotNull(bridge);
        assertNotNull(captain);
        assertNotNull(win);

        // Корректность связей
        assertEquals(EXPECTED_NEXT_STEP_ID1_BRIDGE, start.getNextStepId1());
        assertEquals(EXPECTED_NEXT_STEP_ID2_LOSE1, start.getNextStepId2());

        assertEquals(EXPECTED_NEXT_STEP_CAPTAIN, bridge.getNextStepId1());
        assertEquals(EXPECTED_NEXT_STEP_LOSE2, bridge.getNextStepId2());

        assertEquals(EXPECTED_STEP_START_WIN, captain.getNextStepId1());
        assertEquals(EXPECTED_NEXT_STEP_LOSE3, captain.getNextStepId2());
    }

}
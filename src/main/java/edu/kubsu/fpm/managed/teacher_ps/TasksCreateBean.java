package edu.kubsu.fpm.managed.teacher_ps;

import edu.kubsu.fpm.DAO.*;
import edu.kubsu.fpm.entity.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.*;

/**
 * User: Marina
 * Date: 06.05.12
 * Time: 9:34
 */

@ManagedBean
@SessionScoped
public class TasksCreateBean {
    
    private int testType = 1;
    private Integer currentLection;
    private Map<String, Integer> lectionList;
    private String taskText = null;
    private List<Task> creativeTaskList;     // Список тестов для выбранной лекции
    private Map<Integer, Boolean> selectedIDs = new HashMap<>(); // Выбранные записи

    private int teacherId;
    Course_variation course_variation = new Course_variation();

    Test test;
    private String testName;        // Имя создаваемого теста
    private int taskType;
    private String rightAnswer;
    private int countAnswer;
    private String addAnswer1;
    private String addAnswer2;
    private String addAnswer3;

    @EJB
    private Course_variationDAO course_variationDAO;

    @EJB
    private PersonDAO personDAO;

    @EJB
    private LectionDAO lectionDAO;

    @EJB
    private TestTypeDAO testTypeDAO;

    @EJB
    private TestDAO testDAO;

    @EJB
    private TaskTypeDAO taskTypeDAO;

    @EJB
    private TaskDAO taskDAO;

    @EJB
    private AnswerDAO answerDAO;

    @EJB
    private AllAnswersDAO allAnswersDAO;

    public List<Task> getCreativeTaskList() {
        creativeTaskList = new ArrayList<>();
        if (currentLection != null){
            List<Test> tests = testDAO.getTestListByLectionId(lectionDAO.findById(currentLection));
            for (Test test: tests){
                if (testTypeDAO.findByName("checked").getId()==test.getType().getId()){
                    List<Task> tasks = taskDAO.getTaskListByTest(test);
                    for(Task task: tasks)
                        creativeTaskList.add(task);
                }
            }
        }
        return creativeTaskList;
    }

    public TasksCreateBean() {
        this.setTeacherId(Integer.parseInt((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("teacherId")));
    }

    public void deleteCreativeTask(){
        List<Test> selectedTests = getSelectedTask();
        for (Test selectedTest: selectedTests){
            taskDAO.remove(selectedTest.getId());
            creativeTaskList.remove(selectedTest);
        }
    }

    private List<Test> getSelectedTask() {
        List<Test> testList = new ArrayList<>();
        for(Integer key: selectedIDs.keySet()){
            if (selectedIDs.get(key)){
                testList.add(testDAO.findById(key));
                selectedIDs.remove(key);
            }
        }
        return testList;
    }

    public Map<String, Integer> getLectionList() {
        lectionList = new LinkedHashMap<>();
        course_variation = course_variationDAO.getCourseVarById(1); // TODO В courses.xhtml (CoursesBean) неправильно загружается в FacesContext courseVariation
        List<Lection> lections = course_variationDAO.getListLectionById(course_variation.getId());
        if (lections.size() > 0){
            for (Lection lection: lections)
                lectionList.put(lection.getName(), lection.getId());
        }
        return lectionList;
    }

    public void saveCheckedTask(){
        Test test = new Test();
        test.setName(testName);
        test.setLection(lectionDAO.findById(currentLection));
        test.setType(testTypeDAO.findByName("checked"));
        testDAO.persist(test);

        Task task = new Task();
        task.setContent(taskText);
        task.setTaskType(taskTypeDAO.findByType("creative"));
        task.setTest(test);
        taskDAO.persist(task);
        taskText = null;
        testName = null;
    }

    public void saveTest(){
        test = new Test();
        test.setName(testName);
        test.setLection(lectionDAO.findById(currentLection));
        test.setType(testTypeDAO.findByName("base"));
        testDAO.persist(test);
    }

    public void saveTask(){
        String type = taskType == 1 ? "input" : "check";

        List<String> falseAnswers = getFalseAnswerList();
        if ((type == "check")&&(falseAnswers.size() > 0)&&(taskText.trim().length() > 0)){
            Task taskCheck = new Task();
            taskCheck.setContent(taskText);
            taskCheck.setTaskType(taskTypeDAO.findByType(type));
            taskCheck.setTest(test);
            taskDAO.persist(taskCheck);

            persistAllAnswer(taskCheck, rightAnswer, true);

            for (int i = 0; i < falseAnswers.size(); i++){
                if (i > countAnswer - 1)
                    break;
                persistAllAnswer(taskCheck, falseAnswers.get(i), false);
            }
        }
        else if ((type == "input")&&(taskText.trim().length() > 0)) {
            Task taskInput = new Task();
            taskInput.setContent(taskText);
            taskInput.setTaskType(taskTypeDAO.findByType(type));
            taskInput.setTest(test);
            taskDAO.persist(taskInput);

            persistAllAnswer(taskInput, rightAnswer, true);
        }
    }

    private List<String> getFalseAnswerList() {
        List<String> addAnswers = Arrays.asList(addAnswer1, addAnswer2, addAnswer3);
        List<String> list = new ArrayList<>();
        for (String addAnswer: addAnswers){
            if (addAnswer.trim().length() != 0){
                list.add(addAnswer);
            }
        }
        return list;
    }

    public String initParam() {
        testName = null;
        taskText = null;
        rightAnswer = null;
        addAnswer1 = null;
        addAnswer2 = null;
        addAnswer3 = null;
        return "create_test_tasks";
    }

    private void persistAllAnswer(Task task, String content, boolean isRight) {
        AllAnswers allAnswers = new AllAnswers();
        allAnswers.setTask(task);
        persistAnswer(allAnswers, content, isRight);
    }

    private void persistAnswer(AllAnswers allAnswers, String content, boolean isRight) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setRight(isRight);
        answerDAO.persist(answer);
        allAnswers.setAnswer(answer);
        allAnswersDAO.persist(allAnswers);
    }

    public String checkTestType(){
        if (testType == 1)
            return "test_creation";
        else
            return "task_creation";
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public Integer getCurrentLection() {
        return currentLection;
    }

    public void setCurrentLection(Integer currentLection) {
        this.currentLection = currentLection;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Map<Integer, Boolean> getSelectedIDs() {
        return selectedIDs;
    }

    public void setSelectedIDs(Map<Integer, Boolean> selectedIDs) {
        this.selectedIDs = selectedIDs;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getCountAnswer() {
        return countAnswer;
    }

    public void setCountAnswer(int countAnswer) {
        this.countAnswer = countAnswer;
    }

    public String getAddAnswer1() {
        return addAnswer1;
    }

    public void setAddAnswer1(String addAnswer1) {
        this.addAnswer1 = addAnswer1;
    }

    public String getAddAnswer2() {
        return addAnswer2;
    }

    public void setAddAnswer2(String addAnswer2) {
        this.addAnswer2 = addAnswer2;
    }

    public String getAddAnswer3() {
        return addAnswer3;
    }

    public void setAddAnswer3(String addAnswer3) {
        this.addAnswer3 = addAnswer3;
    }
}
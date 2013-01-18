package todo



import org.junit.*
import grails.test.mixin.*

@TestFor(TaskListController)
@Mock(TaskList)
class TaskListControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/taskList/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.taskListInstanceList.size() == 0
        assert model.taskListInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.taskListInstance != null
    }

    void testSave() {
        controller.save()

        assert model.taskListInstance != null
        assert view == '/taskList/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/taskList/show/1'
        assert controller.flash.message != null
        assert TaskList.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/taskList/list'

        populateValidParams(params)
        def taskList = new TaskList(params)

        assert taskList.save() != null

        params.id = taskList.id

        def model = controller.show()

        assert model.taskListInstance == taskList
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/taskList/list'

        populateValidParams(params)
        def taskList = new TaskList(params)

        assert taskList.save() != null

        params.id = taskList.id

        def model = controller.edit()

        assert model.taskListInstance == taskList
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/taskList/list'

        response.reset()

        populateValidParams(params)
        def taskList = new TaskList(params)

        assert taskList.save() != null

        // test invalid parameters in update
        params.id = taskList.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/taskList/edit"
        assert model.taskListInstance != null

        taskList.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/taskList/show/$taskList.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        taskList.clearErrors()

        populateValidParams(params)
        params.id = taskList.id
        params.version = -1
        controller.update()

        assert view == "/taskList/edit"
        assert model.taskListInstance != null
        assert model.taskListInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/taskList/list'

        response.reset()

        populateValidParams(params)
        def taskList = new TaskList(params)

        assert taskList.save() != null
        assert TaskList.count() == 1

        params.id = taskList.id

        controller.delete()

        assert TaskList.count() == 0
        assert TaskList.get(taskList.id) == null
        assert response.redirectedUrl == '/taskList/list'
    }
}

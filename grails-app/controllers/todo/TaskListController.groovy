package todo

import org.springframework.dao.DataIntegrityViolationException

class TaskListController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [taskListInstanceList: TaskList.list(params), taskListInstanceTotal: TaskList.count()]
    }

    def create() {
        [taskListInstance: new TaskList(params)]
    }

    def save() {
        def taskListInstance = new TaskList(params)
        if (!taskListInstance.save(flush: true)) {
            render(view: "create", model: [taskListInstance: taskListInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'taskList.label', default: 'TaskList'), taskListInstance.id])
        redirect(action: "show", id: taskListInstance.id)
    }

    def show(Long id) {
        def taskListInstance = TaskList.get(id)
        if (!taskListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskList.label', default: 'TaskList'), id])
            redirect(action: "list")
            return
        }

        [taskListInstance: taskListInstance]
    }

    def edit(Long id) {
        def taskListInstance = TaskList.get(id)
        if (!taskListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskList.label', default: 'TaskList'), id])
            redirect(action: "list")
            return
        }

        [taskListInstance: taskListInstance]
    }

    def update(Long id, Long version) {
        def taskListInstance = TaskList.get(id)
        if (!taskListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskList.label', default: 'TaskList'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (taskListInstance.version > version) {
                taskListInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'taskList.label', default: 'TaskList')] as Object[],
                          "Another user has updated this TaskList while you were editing")
                render(view: "edit", model: [taskListInstance: taskListInstance])
                return
            }
        }

        taskListInstance.properties = params

        if (!taskListInstance.save(flush: true)) {
            render(view: "edit", model: [taskListInstance: taskListInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'taskList.label', default: 'TaskList'), taskListInstance.id])
        redirect(action: "show", id: taskListInstance.id)
    }

    def delete(Long id) {
        def taskListInstance = TaskList.get(id)
        if (!taskListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskList.label', default: 'TaskList'), id])
            redirect(action: "list")
            return
        }

        try {
            taskListInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'taskList.label', default: 'TaskList'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'taskList.label', default: 'TaskList'), id])
            redirect(action: "show", id: id)
        }
    }
}

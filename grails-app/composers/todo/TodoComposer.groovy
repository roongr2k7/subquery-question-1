package todo

import org.zkoss.zk.grails.composer.*

class TodoComposer extends GrailsComposer {

    def afterCompose = { window ->
        $('#txtAddTask')
            .on('ok', {
                addTask $(it).text()
            })
            .focus()

        $('#btnAddTask').on('click', {
            addTask $('#txtAddTask').text()
        })
    }

    def addTask(subject) {
        $('#txtAddTask').text('')

        def task = new Task(subject: subject).save()
        def id   = task.id

        $('#list').append {
            hbox(id:"task${id}") {
                checkbox()
                textbox(inplace: true, value: subject)
            }
        }
        $("#task${id}").link(task, [
            done:    'checkbox',
            subject: 'textbox'
        ])
        $("#task${id} > checkbox").on('check', {
            $(it).siblings('textbox')
                .toggleClass('strike')
                .toggleEnable()
            task.save()
        })
        $("#task${id} > textbox").on('change', {
            task.save()
        })
    }
}

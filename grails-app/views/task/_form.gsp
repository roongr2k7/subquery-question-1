<%@ page import="todo.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'done', 'error')} ">
	<label for="done">
		<g:message code="task.done.label" default="Done" />
		
	</label>
	<g:checkBox name="done" value="${taskInstance?.done}" />
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'priority', 'error')} required">
	<label for="priority">
		<g:message code="task.priority.label" default="Priority" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="priority" type="number" value="${taskInstance.priority}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'subject', 'error')} ">
	<label for="subject">
		<g:message code="task.subject.label" default="Subject" />
		
	</label>
	<g:textField name="subject" value="${taskInstance?.subject}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'tasklist', 'error')} required">
	<label for="tasklist">
		<g:message code="task.tasklist.label" default="Tasklist" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tasklist" name="tasklist.id" from="${todo.TaskList.list()}" optionKey="id" required="" value="${taskInstance?.tasklist?.id}" class="many-to-one"/>
</div>

